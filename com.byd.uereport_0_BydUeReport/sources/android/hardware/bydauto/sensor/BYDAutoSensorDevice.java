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
    public static final String TAG = "BYDAutoSensorDevice";
    public static final float TEMPERATURE_MAX = 125.0f;
    public static final float TEMPERATURE_MIN = -40.0f;
    private static BYDAutoSensorDevice sBYDAutoSensorDevice;
    private SensorManagerImpl mSensorManagerImpl;

    public BYDAutoSensorDevice(Context context) {
        super(context.getApplicationContext());
        this.mSensorManagerImpl = SensorManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoSensorDevice getInstance(Context context) {
        if (sBYDAutoSensorDevice == null) {
            sBYDAutoSensorDevice = new BYDAutoSensorDevice(context.getApplicationContext());
        }
        return sBYDAutoSensorDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mSensorManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mSensorManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SENSOR_GET;
    }

    public float getHumiditySensorValue() {
        return this.mSensorManagerImpl.getHumiditySensorValue();
    }

    public int getLightIntensity() {
        return this.mSensorManagerImpl.getLightIntensity();
    }

    public byte[] getSensorAxValue() {
        return this.mSensorManagerImpl.getSensorAxValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SENSOR_SET;
    }

    public int getSlope() {
        return this.mSensorManagerImpl.getSlope();
    }

    public float getTemperatureSensorValue() {
        return this.mSensorManagerImpl.getTemperatureSensorValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mSensorManagerImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, byte[] bArr, Object obj) {
        return this.mSensorManagerImpl.postEvent(i8, i9, bArr, obj);
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        this.mSensorManagerImpl.registerListener(absBYDAutoSensorListener);
    }

    public int setOrientationAngle(int i8) {
        return this.mSensorManagerImpl.setOrientationAngle(i8);
    }

    public void unregisterListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        this.mSensorManagerImpl.unregisterListener(absBYDAutoSensorListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        return this.mSensorManagerImpl.postEvent(i8, i9, i10, obj);
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener, int[] iArr) {
        this.mSensorManagerImpl.registerListener(absBYDAutoSensorListener, iArr);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, float f8, Object obj) {
        return this.mSensorManagerImpl.postEvent(i8, i9, f8, obj);
    }
}
