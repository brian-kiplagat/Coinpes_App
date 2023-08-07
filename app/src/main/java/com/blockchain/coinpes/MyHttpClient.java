package com.blockchain.coinpes;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import okhttp3.*;

public class MyHttpClient {

    public static String makeGetRequest(String url, Headers headers) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else if (response.code() >= 400 && response.code() < 500) {
            // Handle client and server errors
            return response.body().string();
        }else if (response.code() >= 500 && response.code() < 600) {
            // Handle client and server errors
            return response.body().string();
        } else {
            // Handle other cases
            return response.body().string();
        }

    }

    public static String makePostRequest(String url, Headers headers, String jsonBody) throws Exception {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else if (response.code() >= 400 && response.code() < 500) {
            // Handle client and server errors
            return response.body().string();
        }else if (response.code() >= 500 && response.code() < 600) {
            // Handle client and server errors
            return response.body().string();
        } else {
            // Handle other cases
            return response.body().string();
        }
    }
}

