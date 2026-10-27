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
    protected static final String TAG = "BYDAutoVehicleDataDevice";
    public static final int VEHICLE_DATA_COMMAND_BUSY = -2147482647;
    public static final int VEHICLE_DATA_COMMAND_FAILED = -2147482648;
    public static final int VEHICLE_DATA_COMMAND_INVALID_VALUE = -2147482645;
    public static final int VEHICLE_DATA_COMMAND_SUCCESS = 0;
    public static final int VEHICLE_DATA_COMMAND_TIMEOUT = -2147482646;

    BYDAutoVehicleDataDevice(Context con) {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoVehicleDataDevice getInstance(Context con) {
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

    public int sendVehicleDataAcq(byte[] vehicleData) {
        throw new RuntimeException("Stub!");
    }

    public int sendRegisterTable(int type, byte[] table) {
        throw new RuntimeException("Stub!");
    }

    public int sendWholeFrameData(byte[] vehicleData) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
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

    public void registerListener(AbsBYDAutoVehicleDataListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoVehicleDataListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoVehicleDataListener l) {
        throw new RuntimeException("Stub!");
    }
}
