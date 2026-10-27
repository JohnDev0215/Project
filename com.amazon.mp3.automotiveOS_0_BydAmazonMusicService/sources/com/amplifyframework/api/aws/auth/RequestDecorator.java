package com.amplifyframework.api.aws.auth;

import com.amplifyframework.api.ApiException;
import okhttp3.Request;

/* JADX INFO: loaded from: classes5.dex */
public interface RequestDecorator {
    Request decorate(Request request) throws ApiException.ApiAuthException;
}
