package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayPlayKey extends CarPlayInputManager.InputDevice {
    public static final int KEY_ACBACK = 6;
    public static final int KEY_ACHOME = 5;
    public static final int KEY_ACNAVI = 7;
    public static final int KEY_NEXTTRACK = 2;
    public static final int KEY_PAUSE = 1;
    public static final int KEY_PLAY = 0;
    public static final int KEY_PLAYPAUSE = 4;
    public static final int KEY_PREVTRACK = 3;

    public CarPlayPlayKey() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayInputManager.InputDevice
    public int getType() {
        return 3;
    }

    public int sendInput(int key, boolean pressed) throws Exception {
        if (this.mNative != null) {
            return this.mNative.inputKeys(key, pressed);
        }
        throw new Exception("mNative is null");
    }
}
