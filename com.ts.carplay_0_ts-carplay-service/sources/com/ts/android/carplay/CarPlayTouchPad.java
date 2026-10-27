package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayTouchPad extends CarPlayInputManager.InputDevice {
    public CarPlayTouchPad() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayInputManager.InputDevice
    public int getType() {
        return 6;
    }

    public int sendInput(int x, int y, boolean touched) throws Exception {
        return 0;
    }
}
