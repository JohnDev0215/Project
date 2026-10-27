package com.ts.android.carplay;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayInputManager {
    public static final int GENERIC = 5;
    public static final int INVALID = 0;
    public static final int KNOB = 2;
    public static final int LIMITEDUI_JAPANMAPS = 4;
    public static final int LIMITEDUI_MUSICLISTS = 3;
    public static final int LIMITEDUI_NONMUSICLISTS = 2;
    public static final int LIMITEDUI_SOFTKEYBOARD = 0;
    public static final int LIMITEDUI_SOFTPHONEKEYPAD = 1;
    public static final int MAX_GENERIC_HID = 10;
    public static final int MODE_CHARACTER = 1;
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_DIAL = 4;
    public static final int MODE_SCROLL = 2;
    public static final int MODE_SCROLLCHAR = 3;
    public static final int PLAYKEYS = 3;
    public static final int TELKEYS = 4;
    public static final int TOUCHPAD = 6;
    public static final int TOUCHSCREEN = 1;
    private static CarPlayInputManager mInstance = null;
    private CarPlayKnob knob;
    private CarPlayNative mNative;
    private CarPlayPlayKey playKey;
    private CarPlayTouchScreen screen;
    private CarPlayTelephoneKey telkey;
    private CarPlayTouchPad touchPad;

    public static abstract class InputDevice {
        protected CarPlayNative mNative = null;

        public abstract int getType();
    }

    private CarPlayInputManager() {
        this.screen = null;
        this.knob = null;
        this.telkey = null;
        this.playKey = null;
        this.touchPad = null;
        this.mNative = null;
        this.screen = new CarPlayTouchScreen();
        this.knob = new CarPlayKnob();
        this.telkey = new CarPlayTelephoneKey();
        this.playKey = new CarPlayPlayKey();
        this.touchPad = new CarPlayTouchPad();
        this.mNative = CarPlayNative.getInstance();
    }

    public static CarPlayInputManager getInstance() {
        if (mInstance == null) {
            mInstance = new CarPlayInputManager();
        }
        return mInstance;
    }

    public static final int BIT(int inputType) {
        return 1 << inputType;
    }

    public CarPlayTouchScreen getTouchScreen() {
        if (this.screen == null) {
            this.screen = new CarPlayTouchScreen();
        }
        return this.screen;
    }

    public CarPlayKnob getKnob() {
        if (this.knob == null) {
            this.knob = new CarPlayKnob();
        }
        return this.knob;
    }

    public CarPlayTelephoneKey getTelKey() {
        if (this.telkey == null) {
            this.telkey = new CarPlayTelephoneKey();
        }
        return this.telkey;
    }

    public CarPlayPlayKey getPlayKey() {
        if (this.playKey == null) {
            this.playKey = new CarPlayPlayKey();
        }
        return this.playKey;
    }

    public CarPlayTouchPad getTouchPad() {
        if (this.touchPad == null) {
            this.touchPad = new CarPlayTouchPad();
        }
        return this.touchPad;
    }

    public int setLimitedUi(boolean enable) throws Exception {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setLimitedUi(enable);
        }
        throw new Exception("mNative is null");
    }
}
