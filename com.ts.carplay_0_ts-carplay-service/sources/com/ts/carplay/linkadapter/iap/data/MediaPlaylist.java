package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlaylist {
    private int mIsFold;
    private int mIsGeniusMix;
    private int mIsRadio;
    private String mName;
    private String mParentPid;
    private String mPid;

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getParentPid() {
        return this.mParentPid;
    }

    public void setParentPid(String pid) {
        this.mParentPid = pid;
    }

    public int getIsRadio() {
        return this.mIsRadio;
    }

    public void setIsRadio(int isRadio) {
        this.mIsRadio = isRadio;
    }

    public int getIsFold() {
        return this.mIsFold;
    }

    public void setIsFold(int isFold) {
        this.mIsFold = isFold;
    }

    public int getIsGeniusMix() {
        return this.mIsGeniusMix;
    }

    public void setIsGeniusMix(int isGeniusMix) {
        this.mIsGeniusMix = isGeniusMix;
    }

    public String toString() {
        return "MediaPlaylist{mPid='" + this.mPid + "', mName='" + this.mName + "', mParentPid='" + this.mParentPid + "', mIsRadio=" + this.mIsRadio + ", mIsFold=" + this.mIsFold + ", mIsGeniusMix=" + this.mIsGeniusMix + '}';
    }
}
