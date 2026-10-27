package com.ecarx.xui.adaptapi.bt.pbap;

/* JADX INFO: loaded from: classes2.dex */
public interface IPbapCallback {
    void onPbapDownloadNotify(String str, int i, int i2, int i3);

    void onPbapServiceReady();

    void onPbapStateChanged(String str, int i, int i2, int i3, int i4);

    void retPbapDownloadedCallLog(String str, String str2, String str3, String str4, String str5, int i, String str6);

    void retPbapDownloadedContact(PbapContact pbapContact);
}
