package android.hardware.bydauto.rescue;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoRescueDevice extends AbsBYDAutoDevice {
    public static final int RESCUE_COMMAND_BUSY = -2147482647;
    public static final int RESCUE_COMMAND_FAILED = -2147482648;
    public static final int RESCUE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int RESCUE_COMMAND_SUCCESS = 0;
    public static final int RESCUE_COMMAND_TIMEOUT = -2147482646;
    protected static final String TAG = "BYDAutoRescueDevice";

    BYDAutoRescueDevice(Context con) {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoRescueDevice getInstance(Context con) {
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

    public int getInfoFromMCU() {
        throw new RuntimeException("Stub!");
    }

    public int setInfoToMCU(byte[] info) {
        throw new RuntimeException("Stub!");
    }

    public int getAirbagState() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
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

    public void registerListener(AbsBYDAutoRescueListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRescueListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoRescueListener l) {
        throw new RuntimeException("Stub!");
    }
}
