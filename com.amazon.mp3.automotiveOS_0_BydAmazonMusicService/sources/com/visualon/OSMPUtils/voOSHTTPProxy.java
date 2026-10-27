package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSHTTPProxy {
    private int mFlag;
    private Object mFlagData;
    private String mProxyHost;
    private int mProxyPort;

    public voOSHTTPProxy(String str, int i, int i2, Object obj) {
        this.mProxyHost = str;
        this.mProxyPort = i;
        this.mFlag = i2;
        this.mFlagData = obj;
    }

    public String getmProxyHost() {
        return this.mProxyHost;
    }

    public int getmProxyPort() {
        return this.mProxyPort;
    }

    public int getmFlag() {
        return this.mFlag;
    }

    public Object getmFlagData() {
        return this.mFlagData;
    }
}
