package com.amazon.music.inappmessaging.external.model;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageMetricsAttributes {
    public final boolean isFTU;
    public final String pageType;
    public final String reasonType;

    public DynamicMessageMetricsAttributes() {
        this.pageType = null;
        this.reasonType = null;
        this.isFTU = false;
    }

    public DynamicMessageMetricsAttributes(final String pageType, final String reasonType) {
        this(pageType, reasonType, false);
    }

    public DynamicMessageMetricsAttributes(final String pageType, final String reasonType, final boolean isFTU) {
        this.pageType = pageType;
        this.reasonType = reasonType;
        this.isFTU = isFTU;
    }

    public String getPageType() {
        return this.pageType;
    }

    public String getReasonType() {
        return this.reasonType;
    }

    public boolean getIsFTU() {
        return this.isFTU;
    }
}
