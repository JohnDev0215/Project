package android.hardware.bydauto.collision;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoCollisionDevice extends AbsBYDAutoDevice {
    public static final int COLLISION_COMMAND_BUSY = -2147482647;
    public static final int COLLISION_COMMAND_FAILED = -2147482648;
    public static final int COLLISION_COMMAND_INVALID_VALUE = -2147482645;
    public static final int COLLISION_COMMAND_SUCCESS = 0;
    public static final int COLLISION_COMMAND_TIMEOUT = -2147482646;
    public static final int COLLISION_SIGNAL = 2;
    public static final int NORMAL_SIGNAL = 1;
    protected static final String TAG = "BYDAutoCollisionDevice";

    public BYDAutoCollisionDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoCollisionDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public byte[] getCollisionInfo() {
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoCollisionListener absBYDAutoCollisionListener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoCollisionListener absBYDAutoCollisionListener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoCollisionListener absBYDAutoCollisionListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
