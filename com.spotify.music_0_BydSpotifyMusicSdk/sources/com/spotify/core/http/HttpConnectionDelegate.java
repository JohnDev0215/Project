package com.spotify.core.http;

/* JADX INFO: loaded from: classes.dex */
public interface HttpConnectionDelegate {
    void abort();

    boolean isRequestStarted();

    void send(HttpConnection httpConnection, HttpRequest httpRequest, HttpOptions httpOptions);
}
