package com.moneybase.stocktracker.networks;


import com.moneybase.stocktracker.dtos.system.RequestHeaders;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    private RequestHeaders requestHeaders;
    public RequestInterceptor(RequestHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original
                .newBuilder()
                .headers(Headers.of(requestHeaders.getHeaders()))
                .method(original.method(), original.body());
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }


}
