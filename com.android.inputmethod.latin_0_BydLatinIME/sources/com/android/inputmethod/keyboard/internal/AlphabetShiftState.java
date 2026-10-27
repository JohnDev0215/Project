package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class AlphabetShiftState {
    private static final int AUTOMATIC_SHIFTED = 3;
    private static final boolean DEBUG = false;
    private static final int MANUAL_SHIFTED = 1;
    private static final int MANUAL_SHIFTED_FROM_AUTO = 2;
    private static final int SHIFT_LOCKED = 4;
    private static final int SHIFT_LOCK_SHIFTED = 5;
    private static final String TAG = "AlphabetShiftState";
    private static final int UNSHIFTED = 0;
    private int mState = 0;

    private static String toString(int state) {
        return state != 0 ? state != 1 ? state != 2 ? state != 3 ? state != 4 ? state != 5 ? "UNKNOWN" : "SHIFT_LOCK_SHIFTED" : "SHIFT_LOCKED" : "AUTOMATIC_SHIFTED" : "MANUAL_SHIFTED_FROM_AUTO" : "MANUAL_SHIFTED" : "UNSHIFTED";
    }

    public void setShifted(boolean newShiftState) {
        int i = this.mState;
        if (!newShiftState) {
            if (i == 1 || i == 2 || i == 3) {
                this.mState = 0;
                return;
            } else {
                if (i != 5) {
                    return;
                }
                this.mState = 4;
                return;
            }
        }
        if (i == 0) {
            this.mState = 1;
        } else if (i == 3) {
            this.mState = 2;
        } else {
            if (i != 4) {
                return;
            }
            this.mState = 5;
        }
    }

    public void setShiftLocked(boolean newShiftLockState) {
        int i = this.mState;
        if (!newShiftLockState) {
            this.mState = 0;
        } else if (i == 0 || i == 1 || i == 2 || i == 3) {
            this.mState = 4;
        }
    }

    public void setAutomaticShifted() {
        this.mState = 3;
    }

    public boolean isShiftedOrShiftLocked() {
        return this.mState != 0;
    }

    public boolean isShiftLocked() {
        int i = this.mState;
        return i == 4 || i == 5;
    }

    public boolean isShiftLockShifted() {
        return this.mState == 5;
    }

    public boolean isAutomaticShifted() {
        return this.mState == 3;
    }

    public boolean isManualShifted() {
        int i = this.mState;
        return i == 1 || i == 2 || i == 5;
    }

    public boolean isManualShiftedFromAutomaticShifted() {
        return this.mState == 2;
    }

    public String toString() {
        return toString(this.mState);
    }
}
