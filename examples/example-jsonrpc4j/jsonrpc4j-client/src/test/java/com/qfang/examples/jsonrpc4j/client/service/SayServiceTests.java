package com.qfang.examples.jsonrpc4j.client.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: liaozhicheng
 * @date: 2020-02-18
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SayServiceTests {

    @Autowired
    private SayService sayService;

    @Test
    public void multiply() {
        int r = sayService.multiply(5, 6);
        assertThat(r).isEqualTo(30);
    }

    @Test
    public void say() {
        String r = sayService.say();
        assertThat(r).startsWith("say hello");
    }

}
