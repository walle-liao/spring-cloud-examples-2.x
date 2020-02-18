package com.qfang.examples.jsonrpc4j.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author: liaozhicheng
 * @date: 2020-02-18
 * @since 1.0
 */
@JsonRpcService("/com.qfang.examples.jsonrpc4j.api.SayApi")
public interface SayApi {

    int multiplier(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);

    String say();

    String say(String prefix);

}
