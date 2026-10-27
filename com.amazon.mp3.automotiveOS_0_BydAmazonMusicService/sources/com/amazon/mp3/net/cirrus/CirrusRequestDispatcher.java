package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CirrusRequestDispatcher extends BaseRequestDispatcher {
    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return "CirrusLibraryServiceExternal";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return "com.amazon.cirrus.libraryservice";
    }

    protected CirrusHttpClient createCirrusHttpClient() {
        return new CirrusHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected JsonHttpClient getClient() {
        return createCirrusHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected void setUseHttpsOverride(HttpRequestBuilder builder) {
        builder.setUseHttps(true);
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject request) {
        return createAwsRequest(requestType, request);
    }
}
