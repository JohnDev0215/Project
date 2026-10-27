package com.ts.car.timesync;

/* JADX INFO: loaded from: classes.dex */
public class ClockSourceInfo {
    private boolean mIsEnable;
    private boolean mIsInternalSource;
    private String mName;
    private int mPriority;

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public void setIsEnable(boolean isEnable) {
        this.mIsEnable = isEnable;
    }

    public boolean getIsEnable() {
        return this.mIsEnable;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setIsInternalSource(boolean isInternalSource) {
        this.mIsInternalSource = isInternalSource;
    }

    public boolean getIsInternalSource() {
        return this.mIsInternalSource;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ClockSourceInfo{");
        sb.append("mName = ");
        sb.append(this.mName);
        sb.append(" ");
        sb.append("mIsEnable = ");
        sb.append(this.mIsEnable);
        sb.append(" ");
        sb.append("mPriority = ");
        sb.append(this.mPriority);
        sb.append(" ");
        sb.append("mIsInternalSource = ");
        sb.append(this.mIsInternalSource);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }
}
