package com.amazonaws.mobileconnectors.apigateway;

import com.amazonaws.http.HttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ApiResponse {
    private final HttpResponse response;

    ApiResponse(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    public Map<String, String> getHeaders() {
        return this.response.getHeaders();
    }

    public InputStream getContent() throws IOException {
        return this.response.getContent();
    }

    public InputStream getRawContent() throws IOException {
        return this.response.getRawContent();
    }

    public String getStatusText() {
        return this.response.getStatusText();
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }
}
