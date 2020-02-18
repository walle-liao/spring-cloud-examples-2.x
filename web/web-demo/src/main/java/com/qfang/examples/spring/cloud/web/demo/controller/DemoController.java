package com.qfang.examples.spring.cloud.web.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * @author: liaozhicheng
 * @date: 2019-07-20
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/demo/*", produces = {"application/json;charset=UTF-8"})
@Slf4j
public class DemoController {


    /**
     * callback 返回值
     *
     * @return
     */
    @GetMapping("/callable")
    public Callable<String> callable() {
        Callable<String> task = callableTask();
        log.info("controller back ...");
        return task;
    }


    private Callable<String> callableTask() {
        return () -> {
            Thread.sleep(5000);
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
            log.info("task end ...");
            return currentTime;
        };
    }

    /**
     * DeferredResult 返回值
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/deferred")
    public DeferredResult<String> deferred() {
        log.info("接收请求，开始处理...");

        // 建立 DeferredResult 对象，设置超时时间，以及超时返回超时结果
        DeferredResult<String> result = new DeferredResult<>(6000L, "timeout error");

        Callable<String> task = callableTask();
        CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                return "call error";
            }
        }).whenCompleteAsync((r, throwable) -> result.setResult(r));

        result.onTimeout(() -> {
            log.info("调用超时");
        });

        result.onCompletion(() -> {
            log.info("调用完成");
        });

        log.info("接收任务线程完成并退出");
        return result;
    }


    @RequestMapping(value = "/sseEmitter", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public SseEmitter sseEmitterCall() {
        // SseEmitter用于异步返回多个结果，直到调用sseEmitter.complete()结束返回
        SseEmitter sseEmitter = new SseEmitter();
        Thread t = new Thread(new TestRun(sseEmitter));
        t.start();
        return sseEmitter;
    }

    private class TestRun implements Runnable {
        private SseEmitter sseEmitter;
        private int times = 0;

        TestRun(SseEmitter sseEmitter) {
            this.sseEmitter = sseEmitter;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    log.info("当前times=" + times);
                    sseEmitter.send(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
                    times++;
                    Thread.sleep(1000);
                    if (times > 3) {
                        log.info("发送finish事件");
                        sseEmitter.send(SseEmitter.event().name("finish").id("6666").data("哈哈"));
                        log.info("调用complete");
                        sseEmitter.complete();
                        log.info("complete！times=" + times);
                        break;
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}