package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayTouchScreen extends CarPlayInputManager.InputDevice {
    public static final boolean EVENT_MOVED = true;
    public static final boolean EVENT_RELEASED = false;
    public static final boolean EVENT_TOUCHED = true;
    private CarPlayNative mNative;

    public CarPlayTouchScreen() {
        this.mNative = null;
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayInputManager.InputDevice
    public int getType() {
        return 1;
    }

    public int sendInput(int x, int y, boolean pressed) throws Exception {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.inputTouch(x, y, pressed);
        }
        throw new Exception("mNative is null");
    }

    public int translateX(int x, int startCoordinate, int endCoordinate) {
        return 0;
    }

    public int translateY(int y, int startCoordinate, int endCoordinate) {
        return 0;
    }

    public static final class Touch {
        public boolean mTouch;
        public int mX;
        public int mY;

        public Touch(int x, int y, boolean touch) {
            this.mX = x;
            this.mY = y;
            this.mTouch = touch;
        }
    }
}
