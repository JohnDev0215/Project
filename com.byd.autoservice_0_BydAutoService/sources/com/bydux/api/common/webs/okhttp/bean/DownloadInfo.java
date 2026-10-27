package com.bydux.api.common.webs.okhttp.bean;

import okhttp3.Call;

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
    private Call mCall = null;

    public DownloadInfo(int i3, String str, String str2) {
        this.mFn = 0;
        this.mUrl = null;
        this.mFilePath = null;
        this.mFn = i3;
        this.mUrl = str;
        this.mFilePath = str2;
        if (str2.endsWith("/")) {
            this.mFilePath += str.substring(str.lastIndexOf("/") + 1);
        }
    }

    public Call getCall() {
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

    public void setCall(Call call) {
        this.mCall = call;
    }

    public void setDownloadStatus(int i3) {
        this.mDownloadStatus = i3;
    }

    public void setProgress(long j3) {
        this.mProgress = j3;
    }

    public void setTotal(long j3) {
        this.mTotal = j3;
    }
}
