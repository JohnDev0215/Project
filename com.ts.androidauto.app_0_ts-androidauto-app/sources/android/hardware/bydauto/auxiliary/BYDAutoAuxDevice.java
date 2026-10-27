package android.hardware.bydauto.auxiliary;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoAuxDevice extends AbsBYDAutoDevice {
    public static final int AUX_COMMAND_BUSY = -2147482647;
    public static final int AUX_COMMAND_FAILED = -2147482648;
    public static final int AUX_COMMAND_INVALID_VALUE = -2147482645;
    public static final int AUX_COMMAND_SUCCESS = 0;
    public static final int AUX_COMMAND_TIMEOUT = -2147482646;
    public static final int AUX_CONNECTED = 1;
    public static final int AUX_DISCONNECTED = 0;
    public static final int AUX_NO_SIGNAL = 0;
    public static final int AUX_SIGNAL = 1;
    protected static final String TAG = "BYDAutoAuxDevice";
    private static BYDAutoAuxDevice sInstance;

    BYDAutoAuxDevice(Context con) {
        super(con);
    }

    public static synchronized BYDAutoAuxDevice getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new BYDAutoAuxDevice(con);
        }
        return sInstance;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 1;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public int getAuxConnectStatus() {
        return 1;
    }

    public int getAuxSignalStatus() {
        return 1;
    }

    public void setAllStatus() {
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 1;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoAuxListener l) {
    }

    public void registerListener(AbsBYDAutoAuxListener l, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoAuxListener l) {
    }
}
