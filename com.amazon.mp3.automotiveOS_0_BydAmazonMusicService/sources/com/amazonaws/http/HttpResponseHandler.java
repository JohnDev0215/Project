package com.amazonaws.http;

/* JADX INFO: loaded from: classes5.dex */
public interface HttpResponseHandler<T> {
    T handle(HttpResponse httpResponse) throws Exception;

    boolean needsConnectionLeftOpen();
}
