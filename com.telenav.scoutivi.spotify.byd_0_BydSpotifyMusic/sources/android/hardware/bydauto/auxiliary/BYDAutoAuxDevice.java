package android.hardware.bydauto.auxiliary;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

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

    BYDAutoAuxDevice(Context context) {
        super(context);
    }

    public static synchronized BYDAutoAuxDevice getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BYDAutoAuxDevice(context);
        }
        return sInstance;
    }

    public void getAllStatus() {
    }

    public int getAuxConnectStatus() {
        return 1;
    }

    public int getAuxSignalStatus() {
        return 1;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 1;
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 1;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoAuxListener absBYDAutoAuxListener) {
    }

    public void registerListener(AbsBYDAutoAuxListener absBYDAutoAuxListener, int[] iArr) {
    }

    public void setAllStatus() {
    }

    public void unregisterListener(AbsBYDAutoAuxListener absBYDAutoAuxListener) {
    }
}
