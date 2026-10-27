package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayTelephoneKey extends CarPlayInputManager.InputDevice {
    public static final int KEY_0 = 5;
    public static final int KEY_1 = 6;
    public static final int KEY_2 = 7;
    public static final int KEY_3 = 8;
    public static final int KEY_4 = 9;
    public static final int KEY_5 = 10;
    public static final int KEY_6 = 11;
    public static final int KEY_7 = 12;
    public static final int KEY_8 = 13;
    public static final int KEY_9 = 14;
    public static final int KEY_DELETE = 0;
    public static final int KEY_DROP = 3;
    public static final int KEY_FLASH = 2;
    public static final int KEY_HOOKSWITCH = 1;
    public static final int KEY_MUTE = 4;
    public static final int KEY_POUND = 16;
    public static final int KEY_STAR = 15;

    public CarPlayTelephoneKey() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayInputManager.InputDevice
    public int getType() {
        return 4;
    }

    public int sendInput(int key, boolean pressed) throws Exception {
        if (this.mNative != null) {
            return this.mNative.inputTelKeys(key, pressed);
        }
        throw new Exception("mNative is null");
    }
}
