package android.hardware.bydauto.gb;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoGBDevice extends AbsBYDAutoDevice {
    public static final int GB_COMMAND_BUSY = -2147482647;
    public static final int GB_COMMAND_FAILED = -2147482648;
    public static final int GB_COMMAND_INVALID_VALUE = -2147482645;
    public static final int GB_COMMAND_SUCCESS = 0;
    public static final int GB_COMMAND_TIMEOUT = -2147482646;
    public static final int GB_WATE_TEMP_ALARM_NO = 0;
    public static final int GB_WATE_TEMP_ALARM_YES = 1;
    protected static final String TAG = "BYDAutoGBDevice";
    private static BYDAutoGBDevice sInstance;

    BYDAutoGBDevice(Context context) {
        super(context);
    }

    public static synchronized BYDAutoGBDevice getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BYDAutoGBDevice(context);
        }
        return sInstance;
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    public int getTempMeterPercentAlarmValue() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        return false;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, byte[] bArr, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoGBListener absBYDAutoGBListener) {
    }

    public void registerListener(AbsBYDAutoGBListener absBYDAutoGBListener, int[] iArr) {
    }

    public void unregisterListener(AbsBYDAutoGBListener absBYDAutoGBListener) {
    }
}
