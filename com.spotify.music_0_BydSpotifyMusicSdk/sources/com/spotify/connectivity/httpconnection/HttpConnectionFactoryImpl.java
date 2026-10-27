package com.spotify.connectivity.httpconnection;

import com.spotify.core.http.HttpConnectionDelegate;
import com.spotify.core.http.HttpConnectionFactory;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public class HttpConnectionFactoryImpl implements HttpConnectionFactory {
    private final OkHttpClient mHttpClient;

    public HttpConnectionFactoryImpl(OkHttpClient okHttpClient) {
        this.mHttpClient = okHttpClient;
    }

    @Override // com.spotify.core.http.HttpConnectionFactory
    public HttpConnectionDelegate createDelegate() {
        return new HttpConnectionImpl(this.mHttpClient);
    }
}
