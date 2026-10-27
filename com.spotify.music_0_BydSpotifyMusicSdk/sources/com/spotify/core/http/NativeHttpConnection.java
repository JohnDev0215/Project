package com.spotify.core.http;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class NativeHttpConnection implements HttpConnection {
    public static final Companion Companion = new Companion(null);
    private static HttpConnectionFactory sFactory;
    private final HttpConnectionDelegate delegate;
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void initialize(HttpConnectionFactory httpConnectionFactory) {
            v03.j(httpConnectionFactory, "connectionFactory");
            NativeHttpConnection.sFactory = httpConnectionFactory;
        }
    }

    public NativeHttpConnection() {
        HttpConnectionFactory httpConnectionFactory = sFactory;
        if (httpConnectionFactory != null) {
            this.delegate = httpConnectionFactory.createDelegate();
        } else {
            v03.Q("sFactory");
            throw null;
        }
    }

    public static final void initialize(HttpConnectionFactory httpConnectionFactory) {
        Companion.initialize(httpConnectionFactory);
    }

    @Override // com.spotify.core.http.HttpConnection
    public void abort() {
        this.delegate.abort();
    }

    @Override // com.spotify.core.http.HttpConnection
    public boolean isRequestStarted() {
        return this.delegate.isRequestStarted();
    }

    @Override // com.spotify.core.http.HttpConnection
    public native void onBytesAvailable(byte[] bArr, int i);

    @Override // com.spotify.core.http.HttpConnection
    public native void onComplete();

    @Override // com.spotify.core.http.HttpConnection
    public native void onError(int i);

    @Override // com.spotify.core.http.HttpConnection
    public native void onHeaders(HttpResponse httpResponse);

    @Override // com.spotify.core.http.HttpConnection
    public native void onRedirect();

    @Override // com.spotify.core.http.HttpConnection
    public void send(HttpRequest httpRequest, HttpOptions httpOptions) {
        v03.j(httpRequest, "request");
        v03.j(httpOptions, "options");
        this.delegate.send(this, httpRequest, httpOptions);
    }
}
