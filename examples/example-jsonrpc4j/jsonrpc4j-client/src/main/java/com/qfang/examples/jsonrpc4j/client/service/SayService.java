package com.qfang.examples.jsonrpc4j.client.service;

import com.qfang.examples.jsonrpc4j.api.SayApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: liaozhicheng
 * @date: 2020-02-18
 * @since 1.0
 */
@Service
public class SayService {

    @Autowired
    private SayApi sayApi;

    public int multiply(int a, int b) {
        return sayApi.multiplier(a, b);
    }

    public String say() {
        return sayApi.say();
    }

}
