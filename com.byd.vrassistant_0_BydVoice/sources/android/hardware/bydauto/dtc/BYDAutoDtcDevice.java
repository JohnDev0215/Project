package android.hardware.bydauto.dtc;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDtcDevice extends AbsBYDAutoDevice {
    public static final int DTC_COMMAND_BUSY = -2147482647;
    public static final int DTC_COMMAND_FAILED = -2147482648;
    public static final int DTC_COMMAND_INVALID_VALUE = -2147482645;
    public static final int DTC_COMMAND_SUCCESS = 0;
    public static final int DTC_COMMAND_TIMEOUT = -2147482646;
    protected static final String TAG = "BYDAutoDtcDevice";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BYDAutoDtcDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDtcDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    public int sendFaultCodeData(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }
}
