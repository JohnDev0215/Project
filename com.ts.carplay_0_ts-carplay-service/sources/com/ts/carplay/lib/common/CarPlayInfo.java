package com.ts.carplay.lib.common;

import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayInfo {
    public static final int CARPLAY_KEY_HOME = 1;
    public static final int CARPLAY_KEY_NEXTTRACK = 5;
    public static final int CARPLAY_KEY_PAUSE = 3;
    public static final int CARPLAY_KEY_PLAY = 2;
    public static final int CARPLAY_KEY_PLAYPAUSE = 4;
    public static final int CARPLAY_KEY_PREVTRACK = 6;
    public static final int CARPLAY_OEMICON_104_SIZE = 104;
    public static final int CARPLAY_SPEECH_INVALID = -1;
    public static final int CARPLAY_SPEECH_NONE = 0;
    public static final int CARPLAY_SPEECH_RECOGNIZING = 2;
    public static final int CARPLAY_SPEECH_SPEAKING = 1;
    public static final int CARPLAY_TRISTATE_FALSE = 0;
    public static final int CARPLAY_TRISTATE_INVALID = -1;
    public static final int CARPLAY_TRISTATE_TRUE = 1;
    public String mBluetoothId;
    public CarPlayHidKeyInfo mCarPlayHidKeyInfo;
    public CarPlayHidTouchInfo mCarPlayHidTouchInfo;
    public CarPlayOemIconInfo mCarPlayOemIconInfo;
    public CarPlayScreenInfo mCarPlayScreenInfo;
    public String mConfigFile;
    public String mFwRevision;
    public String[] mHidLanguages;
    public String mHwRevision;
    public int mInputMask;
    public boolean mInterfaceName;
    public int mLimitedUielements;
    public String mManufacturer;
    public String mModel;
    public String mName;
    public boolean mOemIconEnabled;
    public String mOemIconLabel;
    public String mOsInfo;
    public String mPlatformInfo;
    public boolean mRightHandMode = false;
    public String mSerial;
    public boolean mSettingsIconPath;
    public String mUniqueId;

    public static class CarPlayHidKeyInfo {
        public short mCountryCode;
        public short mKeyMask;
        public short mProductId;
        public String mUnid;
        public short mVendorId;
    }

    public static class CarPlayHidTouchInfo {
        public short mCountryCode;
        public short mLogicalMaxX;
        public short mLogicalMaxY;
        public short mLogicalMinX;
        public short mLogicalMinY;
        public boolean mLowFidelity;
        public boolean mMultiTouch;
        public short mProductId;
        public String mUnid;
        public short mVendorId;
    }

    public static class CarPlayOemIconInfo {
        public String mFilePath;
        public short mHeightPixel;
        public boolean mPreRendered;
        public short mWidthPixel;
    }

    public static class CarPlayScreenInfo {
        public static final int CARPLAY_INPUT_GENERIC = 5;
        public static final int CARPLAY_INPUT_INVALID = 0;
        public static final int CARPLAY_INPUT_KNOB = 2;
        public static final int CARPLAY_INPUT_PLAYKEYS = 3;
        public static final int CARPLAY_INPUT_TELKEYS = 4;
        public static final int CARPLAY_INPUT_TOUCHPAD = 6;
        public static final int CARPLAY_INPUT_TOUCHSCREEN = 1;
        public char mFramesPerSec;
        public short mHeightPhysical;
        public short mHeightPixel;
        public char mInputDevice;
        public String mUnid;
        public short mWidthPhysical;
        public short mWidthPixel;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
    }
}
