package android.hardware.bydauto.sensor;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.sensor.tsimpl.SensorManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSensorDevice extends AbsBYDAutoDevice {
    public static final int AUTO_SLOPE_MAX = 60;
    public static final int AUTO_SLOPE_MIN = -60;
    public static final int AX_223 = 1;
    public static final int AX_OFFSET_223 = 2;
    public static final int G_SENSOR_OR_ANGLE_MAX = 359;
    public static final int G_SENSOR_OR_ANGLE_MIN = 0;
    public static final float HUMIDITY_MAX = 100.0f;
    public static final float HUMIDITY_MIN = 0.0f;
    public static final int LIGHT_INTENSITY_LEVEL1 = 1;
    public static final int LIGHT_INTENSITY_LEVEL2 = 2;
    public static final int LIGHT_INTENSITY_LEVEL3 = 3;
    public static final int LIGHT_INTENSITY_LEVEL4 = 4;
    public static final int LIGHT_INTENSITY_LEVEL5 = 5;
    public static final String PERMISSION_BYDAUTO_SENSOR_COMMON = "android.permission.BYDAUTO_SENSOR_COMMON";
    public static final String PERMISSION_BYDAUTO_SENSOR_GET = "android.permission.BYDAUTO_SENSOR_GET";
    public static final String PERMISSION_BYDAUTO_SENSOR_SET = "android.permission.BYDAUTO_SENSOR_SET";
    public static final int SENSOR_AX_INVALID = 0;
    public static final int SENSOR_AX_VALID = 1;
    public static final int SENSOR_COMMAND_BUSY = -2147482647;
    public static final int SENSOR_COMMAND_FAILED = -2147482648;
    public static final int SENSOR_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SENSOR_COMMAND_SUCCESS = 0;
    public static final int SENSOR_COMMAND_TIMEOUT = -2147482646;
    protected static final String TAG = "BYDAutoSensorDevice";
    public static final float TEMPERATURE_MAX = 125.0f;
    public static final float TEMPERATURE_MIN = -40.0f;
    private static BYDAutoSensorDevice sBYDAutoSensorDevice;
    private SensorManagerImpl mSensorManagerImpl;

    BYDAutoSensorDevice(Context con) {
        super(con.getApplicationContext());
        this.mSensorManagerImpl = SensorManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoSensorDevice getInstance(Context con) {
        if (sBYDAutoSensorDevice == null) {
            sBYDAutoSensorDevice = new BYDAutoSensorDevice(con.getApplicationContext());
        }
        return sBYDAutoSensorDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mSensorManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SENSOR_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SENSOR_GET;
    }

    public float getTemperatureSensorValue() {
        return this.mSensorManagerImpl.getTemperatureSensorValue();
    }

    public float getHumiditySensorValue() {
        return this.mSensorManagerImpl.getHumiditySensorValue();
    }

    public int getLightIntensity() {
        return this.mSensorManagerImpl.getLightIntensity();
    }

    public int setOrientationAngle(int value) {
        return this.mSensorManagerImpl.setOrientationAngle(value);
    }

    public int getSlope() {
        return this.mSensorManagerImpl.getSlope();
    }

    public byte[] getSensorAxValue() {
        return this.mSensorManagerImpl.getSensorAxValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, byte[] value, Object data) {
        return this.mSensorManagerImpl.postEvent(deviceType, eventType, value, data);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        return this.mSensorManagerImpl.postEvent(deviceType, eventType, value, data);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, float value, Object data) {
        return this.mSensorManagerImpl.postEvent(deviceType, eventType, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mSensorManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mSensorManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoSensorListener listener) {
        this.mSensorManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoSensorListener listener, int[] featureIDs) {
        this.mSensorManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoSensorListener listener) {
        this.mSensorManagerImpl.unregisterListener(listener);
    }
}
