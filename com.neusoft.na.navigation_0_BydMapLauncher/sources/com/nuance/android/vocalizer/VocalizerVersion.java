package com.nuance.android.vocalizer;

/* JADX INFO: loaded from: classes.dex */
public class VocalizerVersion {
    public int mBuild;
    public int mBuildDay;
    public String mBuildInfo;
    public int mBuildMonth;
    public int mBuildYear;
    public int mMajor;
    public int mMinor;

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("Vocalizer Embedded v").append(this.mMajor).append(".").append(this.mMinor).append(".").append(this.mBuild).append(" (").append(this.mBuildYear).append("/").append(this.mBuildMonth).append("/").append(this.mBuildDay).append(") ");
        String str = this.mBuildInfo;
        return sbAppend.append((str == null || str.length() <= 0) ? "" : this.mBuildInfo).toString();
    }
}
