package com.byd.card.drag;

/* JADX INFO: loaded from: classes.dex */
public abstract class JiggleModeActivator {
    public static final int STATE_JIGGLE = 2;
    public static final int STATE_JIGGLE_FADE_IN = 1;
    public static final int STATE_JIGGLE_FADE_OUT = 3;
    public static final int STATE_UNJIGGLE = 0;
    public static final int TIME_ACTIVATE = 600;
    private int state;

    public abstract boolean isJigglable();

    public abstract void jiggle();

    public abstract void unjiggle();

    public int getState() {
        return this.state;
    }

    public boolean isJiggling() {
        return this.state == 2;
    }

    public boolean isUnjiggled() {
        return this.state == 0;
    }

    public void setState(int state) {
        this.state = state;
    }
}
