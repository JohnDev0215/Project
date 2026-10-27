package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayKnob extends CarPlayInputManager.InputDevice {
    public static final int KEY_BACK = 3;
    public static final int KEY_HOME = 2;
    public static final int KEY_SELECT = 1;

    public CarPlayKnob() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayInputManager.InputDevice
    public int getType() {
        return 2;
    }

    public int sendInput(int knobKey, int x, int y, int wheelMovement) throws Exception {
        if (this.mNative != null) {
            return this.mNative.inputKnob(knobKey, x, y, wheelMovement);
        }
        throw new Exception("mNative is null");
    }
}
