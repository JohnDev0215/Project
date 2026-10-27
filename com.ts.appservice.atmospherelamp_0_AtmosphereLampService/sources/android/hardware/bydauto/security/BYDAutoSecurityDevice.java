package android.hardware.bydauto.security;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSecurityDevice extends AbsBYDAutoDevice {
    public static final int SECURITY_COMMAND_BUSY = -2147482647;
    public static final int SECURITY_COMMAND_FAILED = -2147482648;
    public static final int SECURITY_COMMAND_SUCCESS = 0;
    public static final int SECURITY_COMMAND_TIMEOUT = -2147482646;
    public static final int SECURITY_STATE_SAFE = 0;
    public static final int SECURITY_STATE_WARNING = 1;
    protected static final String TAG = "BYDAutoSecurityDevice";

    BYDAutoSecurityDevice(Context con) {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoSecurityDevice getInstance(Context con) {
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

    public byte[] getSecurityState() {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
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

    public void registerListener(AbsBYDAutoSecurityListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSecurityListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSecurityListener l) {
        throw new RuntimeException("Stub!");
    }
}
