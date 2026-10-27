package com.ts.car.timesync;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TimeSyncData {
    private ArrayList<ClockSourceInfo> mClockSourceList = new ArrayList<>();
    private boolean mEnableReSync;
    private boolean mEnableSetKernelTime;
    private boolean mEnableSetKernelTimeZone;
    private boolean mHasRtc;
    private int mSkewThreshold;

    public void setSkewThreshold(int skewThreshold) {
        this.mSkewThreshold = skewThreshold;
    }

    public int getSkewThreshold() {
        return this.mSkewThreshold;
    }

    public void setHasRtc(boolean hasRtc) {
        this.mHasRtc = hasRtc;
    }

    public boolean getHasRtc() {
        return this.mHasRtc;
    }

    public void setEnableSetKernelTime(boolean enableSetKernel) {
        this.mEnableSetKernelTime = enableSetKernel;
    }

    public boolean getEnableSetKernelTime() {
        return this.mEnableSetKernelTime;
    }

    public void setEnableSetKernelTimeZone(boolean enableSetKernel) {
        this.mEnableSetKernelTimeZone = enableSetKernel;
    }

    public boolean getEnableSetKernelTimeZone() {
        return this.mEnableSetKernelTimeZone;
    }

    public void setEnableReSync(boolean enableReSync) {
        this.mEnableReSync = enableReSync;
    }

    public boolean getEnableReSync() {
        return this.mEnableReSync;
    }

    public void setClockSourceList(ArrayList<ClockSourceInfo> list) {
        this.mClockSourceList = list;
    }

    public ArrayList<ClockSourceInfo> getClockSourceList() {
        return this.mClockSourceList;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("TimeSyncData{");
        sb.append("mSkewThreshold = ");
        sb.append(this.mSkewThreshold);
        sb.append(" ");
        sb.append("mHasRtc = ");
        sb.append(this.mHasRtc);
        sb.append(" ");
        sb.append("mEnableSetKernelTime = ");
        sb.append(this.mEnableSetKernelTime);
        sb.append(" ");
        sb.append("mEnableSetKernelTimeZone = ");
        sb.append(this.mEnableSetKernelTimeZone);
        sb.append(" ");
        sb.append("mEnableReSync = ");
        sb.append(this.mEnableReSync);
        sb.append(" ");
        sb.append("\nmClockSourceList{");
        for (int i = 0; i < this.mClockSourceList.size(); i++) {
            sb.append("[");
            sb.append(this.mClockSourceList.get(i).toString());
            sb.append("]\n");
        }
        sb.append("} }");
        return sb.toString();
    }
}
