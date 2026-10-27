package android.hardware.bydauto.signal;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSignalDevice extends AbsBYDAutoDevice {
    public static final int SIGNAL_COMMAND_BUSY = -2147482647;
    public static final int SIGNAL_COMMAND_FAILED = -2147482648;
    public static final int SIGNAL_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SIGNAL_COMMAND_SUCCESS = 0;
    public static final int SIGNAL_COMMAND_TIMEOUT = -2147482646;
    public static final int SIGNAL_STATUS_NORMAL = 1;
    public static final int SIGNAL_STATUS_WORSE = 2;
    protected static final String TAG = "BYDAutoSignaldevice";

    BYDAutoSignalDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoSignalDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public int setSignalStatus(int i) {
        throw new RuntimeException("Stub!");
    }
}
