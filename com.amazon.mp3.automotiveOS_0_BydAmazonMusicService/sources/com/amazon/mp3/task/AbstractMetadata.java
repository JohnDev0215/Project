package com.amazon.mp3.task;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractMetadata {
    private Object mExtraData;
    private boolean mLowPriority;
    private boolean mPrecacheRequest;
    private boolean mUpdateOnly;

    public abstract String getKey();

    public boolean isLowPriority() {
        return this.mLowPriority;
    }

    public void setLowPriority(boolean lowPriority) {
        this.mLowPriority = lowPriority;
    }

    public void setPrecacheRequest(boolean value) {
        this.mPrecacheRequest = value;
    }

    public boolean isPrecacheRequest() {
        return this.mPrecacheRequest;
    }

    public void setExtraData(Object extraData) {
        this.mExtraData = extraData;
    }

    public void setUpdateOnly() {
        this.mUpdateOnly = true;
    }

    public boolean isUpdateOnly() {
        return this.mUpdateOnly;
    }
}
