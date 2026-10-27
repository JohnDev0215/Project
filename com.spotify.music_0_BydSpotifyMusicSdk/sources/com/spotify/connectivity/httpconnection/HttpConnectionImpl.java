package com.spotify.connectivity.httpconnection;

import com.spotify.core.http.HttpConnection;
import com.spotify.core.http.HttpConnectionDelegate;
import com.spotify.core.http.HttpOptions;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Util;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class HttpConnectionImpl implements HttpConnectionDelegate {
    private static final int BUFFER_SIZE = 8192;
    private volatile Call mCall;
    private final OkHttpClient mHttpClient;
    private volatile boolean mIsAborted;
    private volatile Request mRequest;

    public HttpConnectionImpl(OkHttpClient okHttpClient) {
        this.mHttpClient = okHttpClient;
    }

    private static String getMediaType(Map<String, String> map) {
        String str = map.get("Content-Type");
        return str != null ? str : HttpConnection.kDefaultContentType;
    }

    private OkHttpClient mutateHttpClient(HttpOptions httpOptions) {
        OkHttpClient okHttpClient = this.mHttpClient;
        if (okHttpClient.H != httpOptions.getTimeout() && okHttpClient.I != httpOptions.getTimeout()) {
            OkHttpClient.Builder builderE = okHttpClient.e();
            long timeout = httpOptions.getTimeout();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            v03.j(timeUnit, "unit");
            builderE.z = Util.b(timeout, timeUnit);
            builderE.A = Util.b(httpOptions.getTimeout(), timeUnit);
            okHttpClient = new OkHttpClient(builderE);
        }
        if (okHttpClient.G != httpOptions.getConnectTimeout()) {
            OkHttpClient.Builder builderE2 = okHttpClient.e();
            long connectTimeout = httpOptions.getConnectTimeout();
            TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
            v03.j(timeUnit2, "unit");
            builderE2.y = Util.b(connectTimeout, timeUnit2);
            okHttpClient = new OkHttpClient(builderE2);
        }
        if (okHttpClient.f93p == httpOptions.isFollowRedirects()) {
            return okHttpClient;
        }
        OkHttpClient.Builder builderE3 = okHttpClient.e();
        builderE3.h = httpOptions.isFollowRedirects();
        return new OkHttpClient(builderE3);
    }

    @Override // com.spotify.core.http.HttpConnectionDelegate
    public void abort() {
        this.mIsAborted = true;
        if (this.mCall != null) {
            this.mCall.cancel();
        }
    }

    @Override // com.spotify.core.http.HttpConnectionDelegate
    public boolean isRequestStarted() {
        return this.mRequest != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0091  */
    @Override // com.spotify.core.http.HttpConnectionDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void send(final com.spotify.core.http.HttpConnection r7, com.spotify.core.http.HttpRequest r8, com.spotify.core.http.HttpOptions r9) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.connectivity.httpconnection.HttpConnectionImpl.send(com.spotify.core.http.HttpConnection, com.spotify.core.http.HttpRequest, com.spotify.core.http.HttpOptions):void");
    }
}
