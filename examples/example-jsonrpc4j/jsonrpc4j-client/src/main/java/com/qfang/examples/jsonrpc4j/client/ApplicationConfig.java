package com.qfang.examples.jsonrpc4j.client;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.qfang.examples.jsonrpc4j.api.SayApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ApplicationConfig {

    private static final String endpoint = "http://127.0.0.1:8082/com.qfang.examples.jsonrpc4j.api.SayApi";

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>();
        try {
            url = new URL(endpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public SayApi sayClientApi(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), SayApi.class, jsonRpcHttpClient);
    }

}
