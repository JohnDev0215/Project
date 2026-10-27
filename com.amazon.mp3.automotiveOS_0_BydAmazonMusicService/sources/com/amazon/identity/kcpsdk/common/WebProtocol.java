package com.amazon.identity.kcpsdk.common;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum WebProtocol {
    WebProtocolHttp("http"),
    WebProtocolHttps("https");

    private final String mValue;

    WebProtocol(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
