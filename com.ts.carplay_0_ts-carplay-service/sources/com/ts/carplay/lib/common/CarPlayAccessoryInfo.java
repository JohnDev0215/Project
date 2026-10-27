package com.ts.carplay.lib.common;

import android.location.GpsStatus;
import com.ts.carplay.lib.common.CarPlayInfo;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAccessoryInfo {
    public static final String ACCESSORY_FWREVISION = "1.0";
    public static final String ACCESSORY_HWREVISION = "1.0";
    public static final String ACCESSORY_MANU = "Marelli";
    public static final String ACCESSORY_MODEL = "R1L";
    public static final String ACCESSORY_NAME = "Marelli";
    public static final String ACCESSORY_SERIAL = "01234567";
    public static final short CARPLAY_OEMICON_104_SIZE = 104;
    public static final String INTERFACE_NAME = "usb0";
    public static final String OEMICON_LABLE = "marelli";
    public static final String OEMICON_PATH = "/data/images/carplay_oemicon.png";
    public static final char OEM_ICON_LENGTH = 1;
    public static final short PHY_SCREEN_HEIGHT = 480;
    public static final short PHY_SCREEN_WIDTH = 800;
    public static final char SCREEN_FPS = '<';
    public static final short SCREEN_HEIGHT = 480;
    public static final short SCREEN_WIDTH = 800;
    public static final short USB_COUNTRY_CODE_US = 33;
    public static final short USB_PRODUCT_TOUCH = 4660;
    public static final short USB_VENDOR_TOUCH = 4660;
    private static CarPlayAccessoryInfo sCarPlayAccessoryInfo = null;
    private boolean mVehicleParkBreak = true;
    private int mOdoMeter = 0;
    private GpsStatus mGpsStatus = null;
    public boolean mNightMode = false;
    public boolean mLimitedUi = false;
    private int mVehicleGearMode = 0;
    private CarPlayInfo mCarPlayInfo = new CarPlayInfo();

    public GpsStatus getGpsStatus() {
        return this.mGpsStatus;
    }

    public void setGpsStatus(GpsStatus status) {
        this.mGpsStatus = status;
    }

    public int getOdoMeter() {
        return this.mOdoMeter;
    }

    public void setOdoMeter(int meter) {
        this.mOdoMeter = meter;
    }

    public int getVGearMode() {
        return this.mVehicleGearMode;
    }

    public void setVgearMode(int mode) {
        this.mVehicleGearMode = mode;
    }

    public void setVparkBreak(boolean status) {
        this.mVehicleParkBreak = status;
    }

    public boolean getVparkBreak() {
        return this.mVehicleParkBreak;
    }

    public static CarPlayAccessoryInfo getInstance() {
        if (sCarPlayAccessoryInfo == null) {
            sCarPlayAccessoryInfo = new CarPlayAccessoryInfo();
        }
        return sCarPlayAccessoryInfo;
    }

    public int setCarPlayInfo(CarPlayInfo carPlayInfo) {
        this.mCarPlayInfo = carPlayInfo;
        return 0;
    }

    public int setNightMode(boolean mode) {
        this.mNightMode = mode;
        return 0;
    }

    public int setLimitedUi(boolean mode) {
        this.mLimitedUi = mode;
        return 0;
    }

    public CarPlayInfo getCarPlayInfo() {
        return this.mCarPlayInfo;
    }

    public boolean getNightMode() {
        return this.mNightMode;
    }

    public boolean getLimitedUi() {
        return this.mLimitedUi;
    }

    private void initAccessoryInfo() {
        this.mCarPlayInfo.mName = "Marelli";
        this.mCarPlayInfo.mModel = ACCESSORY_MODEL;
        this.mCarPlayInfo.mFwRevision = "1.0";
        this.mCarPlayInfo.mHwRevision = "1.0";
        this.mCarPlayInfo.mManufacturer = "Marelli";
        this.mCarPlayInfo.mSerial = ACCESSORY_SERIAL;
        CarPlayInfo.CarPlayOemIconInfo carPlayOemIconInfo = new CarPlayInfo.CarPlayOemIconInfo();
        carPlayOemIconInfo.mHeightPixel = CARPLAY_OEMICON_104_SIZE;
        carPlayOemIconInfo.mWidthPixel = CARPLAY_OEMICON_104_SIZE;
        carPlayOemIconInfo.mPreRendered = false;
        carPlayOemIconInfo.mFilePath = OEMICON_PATH;
        this.mCarPlayInfo.mCarPlayOemIconInfo = carPlayOemIconInfo;
        CarPlayInfo.CarPlayScreenInfo carPlayScreenInfo = new CarPlayInfo.CarPlayScreenInfo();
        carPlayScreenInfo.mHeightPixel = (short) 480;
        carPlayScreenInfo.mWidthPixel = (short) 800;
        carPlayScreenInfo.mHeightPhysical = (short) 480;
        carPlayScreenInfo.mWidthPhysical = (short) 800;
        carPlayScreenInfo.mFramesPerSec = SCREEN_FPS;
        carPlayScreenInfo.mInputDevice = (char) 1;
        this.mCarPlayInfo.mCarPlayScreenInfo = carPlayScreenInfo;
        CarPlayInfo.CarPlayHidTouchInfo carPlayHidTouchInfo = new CarPlayInfo.CarPlayHidTouchInfo();
        carPlayHidTouchInfo.mCountryCode = (short) 33;
        carPlayHidTouchInfo.mVendorId = (short) 4660;
        carPlayHidTouchInfo.mProductId = (short) 4660;
        carPlayHidTouchInfo.mLowFidelity = false;
        carPlayHidTouchInfo.mMultiTouch = false;
        carPlayHidTouchInfo.mLogicalMinX = (short) 0;
        carPlayHidTouchInfo.mLogicalMaxX = (short) 800;
        carPlayHidTouchInfo.mLogicalMinY = (short) 0;
        carPlayHidTouchInfo.mLogicalMaxY = (short) 480;
        this.mCarPlayInfo.mCarPlayHidTouchInfo = carPlayHidTouchInfo;
        CarPlayInfo.CarPlayHidKeyInfo carPlayHidKeyInfo = new CarPlayInfo.CarPlayHidKeyInfo();
        carPlayHidKeyInfo.mCountryCode = (short) 33;
        carPlayHidKeyInfo.mVendorId = (short) 4660;
        carPlayHidKeyInfo.mProductId = (short) 4660;
        carPlayHidKeyInfo.mKeyMask = (short) 100;
        this.mCarPlayInfo.mCarPlayHidKeyInfo = carPlayHidKeyInfo;
    }
}
