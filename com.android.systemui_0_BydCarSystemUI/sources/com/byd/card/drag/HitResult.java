package com.byd.card.drag;

/* JADX INFO: loaded from: classes.dex */
public class HitResult {
    public boolean buttonRemove;
    public boolean inIcon;
    public int index;

    public HitResult() {
        this.index = -1;
    }

    public HitResult(int index, boolean buttonRemove) {
        this.index = -1;
        this.index = index;
        this.buttonRemove = buttonRemove;
    }
}
