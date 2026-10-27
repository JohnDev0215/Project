package com.amplifyframework.api.aws.auth;

import com.amplifyframework.api.ApiException;
import okhttp3.Request;

/* JADX INFO: loaded from: classes5.dex */
public class TokenRequestDecorator implements RequestDecorator {
    private static final String AUTHORIZATION = "authorization";
    private final TokenSupplier tokenSupplier;

    @FunctionalInterface
    public interface TokenSupplier {
        String get();
    }

    public TokenRequestDecorator(TokenSupplier tokenSupplier) {
        this.tokenSupplier = tokenSupplier;
    }

    @Override // com.amplifyframework.api.aws.auth.RequestDecorator
    public final Request decorate(Request request) throws ApiException.ApiAuthException {
        return request.newBuilder().addHeader(AUTHORIZATION, this.tokenSupplier.get()).build();
    }
}
