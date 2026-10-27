package com.byd.vrdownload.exception;

/* JADX INFO: loaded from: classes2.dex */
public class NetException extends Exception {
    String code;
    String definedMsg;
    String netMsg;

    public NetException(String str, String str2, String str3) {
        this.code = str;
        this.netMsg = str2;
        this.definedMsg = str3;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "NetException{code='" + this.code + "', netMsg='" + this.netMsg + "', definedMsg='" + this.definedMsg + "'}";
    }
}
