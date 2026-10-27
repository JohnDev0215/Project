package com.amazon.music.recents;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.ContentAccessType;
import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private final ContentAccessType contentAccessType;
    private final Marketplace marketPlace;
    private final RequestInterceptor requestInterceptor;

    public Configuration(RequestInterceptor requestInterceptor, Marketplace marketplace, ContentAccessType contentAccessType) {
        this.requestInterceptor = requestInterceptor;
        this.marketPlace = marketplace;
        this.contentAccessType = contentAccessType;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public Marketplace getMarketPlace() {
        return this.marketPlace;
    }

    public ContentAccessType getContentAccessType() {
        return this.contentAccessType;
    }
}
