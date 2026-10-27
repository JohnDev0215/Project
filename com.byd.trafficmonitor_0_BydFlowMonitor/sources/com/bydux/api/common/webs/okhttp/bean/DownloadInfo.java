package com.bydux.api.common.webs.okhttp.bean;

import f.e;

/* JADX INFO: loaded from: classes.dex */
public class DownloadInfo {
    public static final int DOWNLOAD_CANCEL = 4;
    public static final int DOWNLOAD_ERROR = 6;
    public static final int DOWNLOAD_IDLE = 0;
    public static final int DOWNLOAD_ING = 2;
    public static final int DOWNLOAD_OVER = 5;
    public static final int DOWNLOAD_PAUSE = 3;
    public static final int DOWNLOAD_READY = 1;
    private String mFilePath;
    private int mFn;
    private String mUrl;
    private int mDownloadStatus = 0;
    private long mTotal = 0;
    private long mProgress = 0;
    private e mCall = null;

    public DownloadInfo(int i2, String str, String str2) {
        this.mFn = 0;
        this.mUrl = null;
        this.mFilePath = null;
        this.mFn = i2;
        this.mUrl = str;
        this.mFilePath = str2;
        if (str2.endsWith("/")) {
            this.mFilePath += str.substring(str.lastIndexOf("/") + 1);
        }
    }

    public e getCall() {
        return this.mCall;
    }

    public int getDownloadStatus() {
        return this.mDownloadStatus;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public int getFn() {
        return this.mFn;
    }

    public long getProgress() {
        return this.mProgress;
    }

    public long getTotal() {
        return this.mTotal;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setCall(e eVar) {
        this.mCall = eVar;
    }

    public void setDownloadStatus(int i2) {
        this.mDownloadStatus = i2;
    }

    public void setProgress(long j) {
        this.mProgress = j;
    }

    public void setTotal(long j) {
        this.mTotal = j;
    }
}
