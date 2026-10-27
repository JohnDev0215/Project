package com.bydux.api.common.webs.okhttp.bean;

import okhttp3.Call;

/* JADX INFO: loaded from: classes2.dex */
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

    public DownloadInfo(int fn, String url, String filePath) {
        this.mFn = 0;
        this.mUrl = null;
        this.mFilePath = null;
        this.mFn = fn;
        this.mUrl = url;
        this.mFilePath = filePath;
        if (filePath.endsWith("/")) {
            this.mFilePath += url.substring(url.lastIndexOf("/") + 1);
        }
    }

    public int getFn() {
        return this.mFn;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void setDownloadStatus(int status) {
        this.mDownloadStatus = status;
    }

    public int getDownloadStatus() {
        return this.mDownloadStatus;
    }

    public void setTotal(long total) {
        this.mTotal = total;
    }

    public long getTotal() {
        return this.mTotal;
    }

    public void setProgress(long progress) {
        this.mProgress = progress;
    }

    public long getProgress() {
        return this.mProgress;
    }

    public void setCall(Call call) {
        this.mCall = call;
    }

    public Call getCall() {
        return this.mCall;
    }
}
