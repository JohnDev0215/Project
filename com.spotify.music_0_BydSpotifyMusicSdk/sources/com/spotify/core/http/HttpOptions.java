package com.spotify.core.http;

/* JADX INFO: loaded from: classes.dex */
public final class HttpOptions {
    private final long connectTimeout;
    private final boolean isFollowRedirects;
    private final long timeout;

    public HttpOptions(long j, long j2, boolean z) {
        this.timeout = j;
        this.connectTimeout = j2;
        this.isFollowRedirects = z;
    }

    public final long getConnectTimeout() {
        return this.connectTimeout;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final boolean isFollowRedirects() {
        return this.isFollowRedirects;
    }

    public HttpOptions(long j, long j2) {
        this(j, j2, true);
    }
}
