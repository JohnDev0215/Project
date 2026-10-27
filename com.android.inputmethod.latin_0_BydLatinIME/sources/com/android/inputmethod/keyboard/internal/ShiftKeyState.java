package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
final class ShiftKeyState extends ModifierKeyState {
    private static final int IGNORING = 4;
    private static final int PRESSING_ON_SHIFTED = 3;

    public ShiftKeyState(String name) {
        super(name);
    }

    @Override // com.android.inputmethod.keyboard.internal.ModifierKeyState
    public void onOtherKeyPressed() {
        int i = this.mState;
        if (i == 1) {
            this.mState = 2;
        } else if (i == 3) {
            this.mState = 4;
        }
    }

    public void onPressOnShifted() {
        int i = this.mState;
        this.mState = 3;
    }

    public boolean isPressingOnShifted() {
        return this.mState == 3;
    }

    public boolean isIgnoring() {
        return this.mState == 4;
    }

    @Override // com.android.inputmethod.keyboard.internal.ModifierKeyState
    public String toString() {
        return toString(this.mState);
    }

    @Override // com.android.inputmethod.keyboard.internal.ModifierKeyState
    protected String toString(int state) {
        return state != 3 ? state != 4 ? super.toString(state) : "IGNORING" : "PRESSING_ON_SHIFTED";
    }
}
