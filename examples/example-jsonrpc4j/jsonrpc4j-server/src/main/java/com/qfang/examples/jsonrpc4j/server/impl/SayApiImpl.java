package com.qfang.examples.jsonrpc4j.server.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.qfang.examples.jsonrpc4j.api.SayApi;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: liaozhicheng
 * @date: 2020-02-18
 * @since 1.0
 *
 * @see SayApi
 */
@Service
@AutoJsonRpcServiceImpl
public class SayApiImpl implements SayApi {

    /**
     * 可以用浏览器直接测试
     *
     * http://127.0.0.1:8082/com.qfang.examples.jsonrpc4j.api.SayApi?method=say&id=1
     * http://127.0.0.1:8082/com.qfang.examples.jsonrpc4j.api.SayApi?method=say&id=1&params=WyJhYmMiXQ==  // params: Base64.encode(["abc"]) = "WyJhYmMiXQ=="
     * http://127.0.0.1:8082/com.qfang.examples.jsonrpc4j.api.SayApi?method=multiplier&id=1&params=WzUsIDZd
     */

    @Override
    public int multiplier(int a, int b) {
        return a * b;
    }

    @Override
    public String say() {
        return String.format("say hello, %s", LocalDateTime.now());
    }

    @Override
    public String say(String prefix) {
        return String.format("%s %s: say hello", prefix, LocalDateTime.now());
    }
}
