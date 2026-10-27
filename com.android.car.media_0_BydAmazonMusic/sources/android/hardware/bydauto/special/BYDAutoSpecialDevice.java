package android.hardware.bydauto.special;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSpecialDevice extends AbsBYDAutoDevice {
    public static final int SPECIAL_COMMAND_BUSY = -2147482647;
    public static final int SPECIAL_COMMAND_FAILED = -2147482648;
    public static final int SPECIAL_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SPECIAL_COMMAND_SUCCESS = 0;
    public static final int SPECIAL_COMMAND_TIMEOUT = -2147482646;
    public static final String TAG = "BYDAutoSpecialDevice";

    public BYDAutoSpecialDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoSpecialDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
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

    public byte[] getWheelDirection() {
        throw new RuntimeException("Stub!");
    }

    public byte[] getWheelSpeed() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSpecialListener absBYDAutoSpecialListener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSpecialListener absBYDAutoSpecialListener) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSpecialListener absBYDAutoSpecialListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
