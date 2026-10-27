package com.loostone.libtuning.data.bean;

/* JADX INFO: loaded from: classes.dex */
public final class HidItem {
    private int hidCode;
    private int keyCode;
    private int lastState = -1;
    private int micIndex;

    public HidItem(int i, int i2, int i3) {
        this.micIndex = i;
        this.hidCode = i2;
        this.keyCode = i3;
    }

    public final int getHidCode() {
        return this.hidCode;
    }

    public final int getKeyCode() {
        return this.keyCode;
    }

    public final int getLastState() {
        return this.lastState;
    }

    public final int getMicIndex() {
        return this.micIndex;
    }

    public final void setHidCode(int i) {
        this.hidCode = i;
    }

    public final void setKeyCode(int i) {
        this.keyCode = i;
    }

    public final void setLastState(int i) {
        this.lastState = i;
    }

    public final void setMicIndex(int i) {
        this.micIndex = i;
    }
}
