package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSHTTPHeader {
    private int Flag;
    private Object FlagData;
    private String HeaderName;
    private String HeaderValue;

    public voOSHTTPHeader(String str, String str2, int i, Object obj) {
        this.HeaderName = str;
        this.HeaderValue = str2;
        this.Flag = i;
        this.FlagData = obj;
    }

    public String getHeaderName() {
        return this.HeaderName;
    }

    public String getHeaderValue() {
        return this.HeaderValue;
    }

    public int getFlag() {
        return this.Flag;
    }

    public Object getFlagData() {
        return this.FlagData;
    }
}
