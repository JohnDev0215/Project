package android.hardware.bydauto.bigdata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoBigDataDevice extends AbsBYDAutoDevice {
    public static final int BIGDATA_COMMAND_BUSY = -2147482647;
    public static final int BIGDATA_COMMAND_FAILED = -2147482648;
    public static final int BIGDATA_COMMAND_INVALID_VALUE = -2147482645;
    public static final int BIGDATA_COMMAND_SUCCESS = 0;
    public static final int BIGDATA_COMMAND_TIMEOUT = -2147482646;
    public static final String TAG = "BYDAutoBigDataDevice";

    public BYDAutoBigDataDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoBigDataDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
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
    public boolean postEvent(int i2, int i3, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoBigDataListener absBYDAutoBigDataListener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoBigDataListener absBYDAutoBigDataListener) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoBigDataListener absBYDAutoBigDataListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
