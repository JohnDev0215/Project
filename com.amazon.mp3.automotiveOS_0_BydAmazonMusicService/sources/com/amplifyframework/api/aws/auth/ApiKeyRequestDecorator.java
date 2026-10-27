package com.amplifyframework.api.aws.auth;

import androidx.annotation.NonNull;
import com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider;
import okhttp3.Request;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiKeyRequestDecorator implements RequestDecorator {
    private static final String X_API_KEY = "x-api-key";
    private final ApiKeyAuthProvider apiKeyProvider;

    public ApiKeyRequestDecorator(@NonNull ApiKeyAuthProvider apiKeyAuthProvider) {
        this.apiKeyProvider = apiKeyAuthProvider;
    }

    @Override // com.amplifyframework.api.aws.auth.RequestDecorator
    public Request decorate(Request request) {
        return request.newBuilder().addHeader(X_API_KEY, this.apiKeyProvider.getAPIKey()).build();
    }
}
