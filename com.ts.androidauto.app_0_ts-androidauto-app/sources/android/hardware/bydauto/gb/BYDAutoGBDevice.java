package android.hardware.bydauto.gb;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

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

    BYDAutoGBDevice(Context con) {
        super(con);
    }

    public static synchronized BYDAutoGBDevice getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new BYDAutoGBDevice(con);
        }
        return sInstance;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public int getTempMeterPercentAlarmValue() {
        return 0;
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return false;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoGBListener l) {
    }

    public void registerListener(AbsBYDAutoGBListener l, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoGBListener l) {
    }
}
