package com.amplifyframework.api.aws;

import com.amplifyframework.api.ApiException;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes5.dex */
interface InterceptorFactory {
    Interceptor create(ApiConfiguration apiConfiguration) throws ApiException;
}
