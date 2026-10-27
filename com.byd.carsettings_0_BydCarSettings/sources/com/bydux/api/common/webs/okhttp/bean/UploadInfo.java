package com.bydux.api.common.webs.okhttp.bean;

import java.util.Map;
import okhttp3.Call;

/* JADX INFO: loaded from: classes3.dex */
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

    public UploadInfo(int i, String str, String str2) {
        this(i, str, str2, null);
    }

    public Call getCall() {
        return this.mCall;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public int getFn() {
        return this.mFn;
    }

    public Map<String, String> getParamMap() {
        return this.mParamMap;
    }

    public long getProgress() {
        return this.mProgress;
    }

    public long getTotal() {
        return this.mTotal;
    }

    public int getUploadStatus() {
        return this.mUploadStatus;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setCall(Call call) {
        this.mCall = call;
    }

    public void setParamMap(Map<String, String> map) {
        this.mParamMap = map;
    }

    public void setProgress(long j) {
        this.mProgress = j;
    }

    public void setTotal(long j) {
        this.mTotal = j;
    }

    public void setUploadStatus(int i) {
        this.mUploadStatus = i;
    }

    public UploadInfo(int i, String str, String str2, Map<String, String> map) {
        this.mFn = 0;
        this.mUrl = null;
        this.mFilePath = null;
        this.mParamMap = null;
        this.mUploadStatus = 0;
        this.mTotal = 0L;
        this.mProgress = 0L;
        this.mCall = null;
        this.mFn = i;
        this.mUrl = str;
        this.mFilePath = str2;
        this.mParamMap = map;
        if (str2.endsWith("/")) {
            this.mFilePath += str.substring(str.lastIndexOf("/") + 1);
        }
    }
}
