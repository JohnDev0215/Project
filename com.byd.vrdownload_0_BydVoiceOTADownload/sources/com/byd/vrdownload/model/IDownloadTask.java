package com.byd.vrdownload.model;

/* JADX INFO: loaded from: classes.dex */
public interface IDownloadTask {
    String getFileName();

    int getId();

    long getSofarBytes();

    byte getStatus();

    long getTotalBytes();

    boolean isReDownload();
}
