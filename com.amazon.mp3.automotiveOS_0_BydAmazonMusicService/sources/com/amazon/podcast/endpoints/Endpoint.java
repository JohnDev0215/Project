package com.amazon.podcast.endpoints;

/* JADX INFO: loaded from: classes5.dex */
public final class Endpoint {
    private final String target;
    private final String url;

    public Endpoint(final String url, final String target) {
        this.url = url;
        this.target = target;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTarget() {
        return this.target;
    }
}
