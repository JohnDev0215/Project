package com.amazon.mp3.playlist.service;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class Configuration {
    private final URL mEndpoint;
    private final RequestInterceptor mRequestInterceptor;

    public Configuration(final RequestInterceptor requestInterceptor, final URL endpoint) {
        this.mRequestInterceptor = requestInterceptor;
        this.mEndpoint = endpoint;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.mRequestInterceptor;
    }

    public URL getEndpoint() {
        return this.mEndpoint;
    }
}
