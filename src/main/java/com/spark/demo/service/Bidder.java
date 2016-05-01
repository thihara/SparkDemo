package com.spark.demo.service;

import com.spark.demo.util.ValidationUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Bidder implements Callable<String>{

    private static final PoolingHttpClientConnectionManager clientManager =  new PoolingHttpClientConnectionManager();

    private String jsonData;

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpPost httpPost;
    private final StringEntity requestEntity;

    public Bidder(String bidderURL, String jsonData){
        ValidationUtils.notEmpty(bidderURL, "Bidder endpoint URL");
        ValidationUtils.notEmpty(jsonData, "Bid request data");

        this.jsonData = jsonData;
        this.httpClient = HttpClients.custom().setConnectionManager(clientManager).build();
        this.context = HttpClientContext.create();
        this.requestEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        this.httpPost = new HttpPost(bidderURL);
        this.httpPost.setEntity(requestEntity);
    }

    @Override
    public String call() throws Exception {
        try (CloseableHttpResponse response = httpClient.execute(httpPost, context)) {

            return EntityUtils.toString(response.getEntity());
        }
    }
}
