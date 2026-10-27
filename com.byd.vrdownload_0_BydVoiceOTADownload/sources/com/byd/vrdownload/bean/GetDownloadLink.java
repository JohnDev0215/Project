package com.byd.vrdownload.bean;

/* JADX INFO: loaded from: classes.dex */
public class GetDownloadLink {
    String countryCode;
    String pkgKey;
    String reqId;

    public String getPkgKey() {
        return this.pkgKey;
    }

    public void setPkgKey(String str) {
        this.pkgKey = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }
}
