package com.byd.systemsettings.system.model;

/* JADX INFO: loaded from: classes2.dex */
public class DialogPagerState {
    private volatile boolean showing;

    public DialogPagerState(boolean z7) {
        this.showing = false;
        this.showing = z7;
    }

    public boolean isShowing() {
        return this.showing;
    }

    public void setShowing(boolean z7) {
        this.showing = z7;
    }

    public String toString() {
        return "DialogPagerState{showing=" + this.showing + '}';
    }
}
