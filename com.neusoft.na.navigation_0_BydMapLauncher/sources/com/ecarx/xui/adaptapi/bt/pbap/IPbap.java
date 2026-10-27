package com.ecarx.xui.adaptapi.bt.pbap;

/* JADX INFO: loaded from: classes2.dex */
public interface IPbap {
    int getPbapConnectionState();

    String getPbapDownloadingAddress();

    boolean isPbapDownloading();

    boolean isPbapServiceReady();

    boolean registerPbapCallback(IPbapCallback iPbapCallback);

    boolean reqPbapDownload(String str, int i, int i2);

    boolean reqPbapDownloadInterrupt(String str);

    boolean reqPbapDownloadRange(String str, int i, int i2, int i3, int i4);

    boolean setPbapDownloadNotify(int i);

    boolean unregisterPbapCallback(IPbapCallback iPbapCallback);
}
