package android.hardware.bydauto.vehicledata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoVehicleDataDevice extends AbsBYDAutoDevice {
    public static final int DATA_TYPE_BIG_DATA = 3;
    public static final int DATA_TYPE_GB = 2;
    public static final int DATA_TYPE_MQTT = 5;
    public static final int DATA_TYPE_OTA = 1;
    public static final int DATA_TYPE_YUN = 4;
    public static final String TAG = "BYDAutoVehicleDataDevice";
    public static final int VEHICLE_DATA_COMMAND_BUSY = -2147482647;
    public static final int VEHICLE_DATA_COMMAND_FAILED = -2147482648;
    public static final int VEHICLE_DATA_COMMAND_INVALID_VALUE = -2147482645;
    public static final int VEHICLE_DATA_COMMAND_SUCCESS = 0;
    public static final int VEHICLE_DATA_COMMAND_TIMEOUT = -2147482646;

    public BYDAutoVehicleDataDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoVehicleDataDevice getInstance(Context context) {
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener) {
        throw new RuntimeException("Stub!");
    }

    public int sendRegisterTable(int i, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int sendVehicleDataAcq(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int sendWholeFrameData(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
