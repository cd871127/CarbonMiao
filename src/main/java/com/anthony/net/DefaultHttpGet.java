package com.anthony.net;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chend on 2017/6/15.
 */
public class DefaultHttpGet {

    public String get(String url) {
        CloseableHttpResponse response;
        HttpGet httpGet = new HttpGet(url);
        HttpContext context = HttpClientContext.create();
        String res = null;
        try {
            response = DefaultHttpClient.httpClient.execute(httpGet, context);
            if (200 != response.getStatusLine().getStatusCode()) {
                return null;
            }
            InputStream in = response.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            res = bufferedReader.readLine();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
