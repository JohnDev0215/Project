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
    public static final String TAG = "BYDAutoRescueDevice";

    public BYDAutoRescueDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoRescueDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public int getAirbagState() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    public int getInfoFromMCU() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRescueListener absBYDAutoRescueListener) {
        throw new RuntimeException("Stub!");
    }

    public int setInfoToMCU(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoRescueListener absBYDAutoRescueListener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRescueListener absBYDAutoRescueListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
