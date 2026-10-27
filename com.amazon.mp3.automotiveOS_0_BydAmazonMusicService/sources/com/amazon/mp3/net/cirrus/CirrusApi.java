package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.service.ServiceApi;

/* JADX INFO: loaded from: classes3.dex */
public enum CirrusApi implements ServiceApi {
    Version1("2011-03-01"),
    Version2("2011-06-01");

    private String mCode;

    CirrusApi(String code) {
        this.mCode = code;
    }

    @Override // com.amazon.mp3.net.service.ServiceApi
    public String getCode() {
        return this.mCode;
    }

    @Override // com.amazon.mp3.net.service.ServiceApi
    public String getPath() {
        return this != getDefaultVersion() ? this.mCode : "";
    }

    private CirrusApi getDefaultVersion() {
        return Version1;
    }
}
