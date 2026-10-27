package com.amazonaws.mobileconnectors.apigateway;

import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ApiRequest {
    private final Request<ApiResponse> request;

    public ApiRequest(String str) {
        this.request = new DefaultRequest(str);
    }

    public ApiRequest() {
        this.request = new DefaultRequest("");
    }

    public ApiRequest withHeaders(Map<String, String> map) {
        this.request.setHeaders(map);
        return this;
    }

    public ApiRequest addHeader(String str, String str2) {
        this.request.addHeader(str, str2);
        return this;
    }

    public Map<String, String> getHeaders() {
        return this.request.getHeaders();
    }

    public ApiRequest withPath(String str) {
        this.request.setResourcePath(str);
        return this;
    }

    public String getPath() {
        return this.request.getResourcePath();
    }

    public ApiRequest withParameters(Map<String, String> map) {
        this.request.setParameters(map);
        return this;
    }

    public Map<String, String> getParameters() {
        return this.request.getParameters();
    }

    public ApiRequest withParameter(String str, String str2) {
        this.request.addParameter(str, str2);
        return this;
    }

    public ApiRequest withHttpMethod(HttpMethodName httpMethodName) {
        this.request.setHttpMethod(httpMethodName);
        return this;
    }

    public HttpMethodName getHttpMethod() {
        return this.request.getHttpMethod();
    }

    public ApiRequest withBody(InputStream inputStream) {
        this.request.setContent(inputStream);
        return this;
    }

    public ApiRequest withBody(byte[] bArr) {
        this.request.setContent(new ByteArrayInputStream(bArr));
        return this;
    }

    public ApiRequest withBody(String str) {
        this.request.setContent(new ByteArrayInputStream(str.getBytes(StringUtils.UTF8)));
        return this;
    }

    Request<ApiResponse> getRequest() {
        return this.request;
    }
}
