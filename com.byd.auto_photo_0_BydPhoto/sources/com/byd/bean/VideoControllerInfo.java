package com.byd.bean;

/* JADX INFO: loaded from: classes.dex */
public class VideoControllerInfo {
    private boolean baseCurrent;
    private boolean fullscreen;
    private int opt;
    private int seekTime;

    public VideoControllerInfo(int i, int i2, boolean z) {
        this.opt = i;
        this.seekTime = i2;
        this.baseCurrent = z;
    }

    public VideoControllerInfo(int i, boolean z) {
        this.opt = i;
        this.fullscreen = z;
    }

    public int getOpt() {
        return this.opt;
    }

    public int getSeekTime() {
        return this.seekTime;
    }

    public boolean isBaseCurrent() {
        return this.baseCurrent;
    }

    public boolean isFullscreen() {
        return this.fullscreen;
    }

    public String toString() {
        return "VideoControllerInfo{opt=" + this.opt + ", fullscreen=" + this.fullscreen + ", seekTime=" + this.seekTime + ", baseCurrent=" + this.baseCurrent + '}';
    }
}
