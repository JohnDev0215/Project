package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
class ModifierKeyState {
    protected static final int CHORDING = 2;
    protected static final boolean DEBUG = false;
    protected static final int PRESSING = 1;
    protected static final int RELEASING = 0;
    protected static final String TAG = "ModifierKeyState";
    protected final String mName;
    protected int mState = 0;

    protected String toString(int state) {
        return state != 0 ? state != 1 ? state != 2 ? "UNKNOWN" : "CHORDING" : "PRESSING" : "RELEASING";
    }

    public ModifierKeyState(String name) {
        this.mName = name;
    }

    public void onPress() {
        this.mState = 1;
    }

    public void onRelease() {
        this.mState = 0;
    }

    public void onOtherKeyPressed() {
        if (this.mState == 1) {
            this.mState = 2;
        }
    }

    public boolean isPressing() {
        return this.mState == 1;
    }

    public boolean isReleasing() {
        return this.mState == 0;
    }

    public boolean isChording() {
        return this.mState == 2;
    }

    public String toString() {
        return toString(this.mState);
    }
}
