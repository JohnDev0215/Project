package com.bydux.api.common.webs.okhttp.bean;

import java.util.Map;
import okhttp3.Call;

/* JADX INFO: loaded from: classes2.dex */
public class UploadInfo {
    public static final int UPLOAD_CANCEL = 4;
    public static final int UPLOAD_ERROR = 6;
    public static final int UPLOAD_IDLE = 0;
    public static final int UPLOAD_ING = 2;
    public static final int UPLOAD_OVER = 5;
    public static final int UPLOAD_PAUSE = 3;
    public static final int UPLOAD_READY = 1;
    private Call mCall;
    private String mFilePath;
    private int mFn;
    private Map<String, String> mParamMap;
    private long mProgress;
    private long mTotal;
    private int mUploadStatus;
    private String mUrl;

    public UploadInfo(int fn, String url, String filePath) {
        this(fn, url, filePath, null);
    }

    public UploadInfo(int fn, String url, String filePath, Map<String, String> paramMap) {
        this.mUploadStatus = 0;
        this.mTotal = 0L;
        this.mProgress = 0L;
        this.mCall = null;
        this.mFn = fn;
        this.mUrl = url;
        this.mFilePath = filePath;
        this.mParamMap = paramMap;
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

    public void setParamMap(Map<String, String> paramMap) {
        this.mParamMap = paramMap;
    }

    public Map<String, String> getParamMap() {
        return this.mParamMap;
    }

    public void setUploadStatus(int status) {
        this.mUploadStatus = status;
    }

    public int getUploadStatus() {
        return this.mUploadStatus;
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
