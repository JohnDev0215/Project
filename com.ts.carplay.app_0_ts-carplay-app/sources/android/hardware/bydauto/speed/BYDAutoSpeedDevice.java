package android.hardware.bydauto.speed;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.speed.tsimpl.SpeedManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSpeedDevice extends AbsBYDAutoDevice {
    public static final float ACCELERATE_MAX = 31.0f;
    public static final float ACCELERATE_MIN = -30.0f;
    public static final int DATA_ACCELERATOR_DEPTH = 1;
    public static final int DATA_BRAKE_DEPTH = 2;
    public static final int DATA_FLAG_INVALID = 0;
    public static final int DATA_FLAG_VALID = 1;
    public static final int DATA_SPEED = 0;
    public static final int DEEP_PERSENT_MAX = 100;
    public static final int DEEP_PERSENT_MIN = 0;
    public static final float FUEL_ACCELERATE_MAX = 99.6f;
    public static final float FUEL_ACCELERATE_MIN = 0.0f;
    public static final String PERMISSION_BYDAUTO_SPEED_COMMON = "android.permission.BYDAUTO_SPEED_COMMON";
    public static final String PERMISSION_BYDAUTO_SPEED_GET = "android.permission.BYDAUTO_SPEED_GET";
    public static final String PERMISSION_BYDAUTO_SPEED_SET = "android.permission.BYDAUTO_SPEED_SET";
    public static final int SPEED_COMMAND_BUSY = -2147482647;
    public static final int SPEED_COMMAND_FAILED = -2147482648;
    public static final int SPEED_COMMAND_INVALID = -2147482645;
    public static final int SPEED_COMMAND_SUCCESS = 0;
    public static final int SPEED_COMMAND_TIMEOUT = -2147482646;
    public static final float SPEED_MAX = 282.0f;
    public static final float SPEED_MIN = 0.0f;
    protected static final String TAG = "BYDAutoSpeedDevice";
    private static volatile BYDAutoSpeedDevice mBYDAutoSpeedDevice;
    private SpeedManagerImpl mSpeedManagerImpl;
    protected static int mAccFlag = 0;
    protected static int mBrakeFlag = 0;
    protected static int mSpeedFlag = 0;

    private BYDAutoSpeedDevice(Context con) {
        super(con);
        this.mSpeedManagerImpl = SpeedManagerImpl.getInstance(con);
    }

    public static BYDAutoSpeedDevice getInstance(Context con) {
        if (mBYDAutoSpeedDevice == null) {
            synchronized (BYDAutoSpeedDevice.class) {
                if (mBYDAutoSpeedDevice == null) {
                    mBYDAutoSpeedDevice = new BYDAutoSpeedDevice(con.getApplicationContext());
                }
            }
        }
        return mBYDAutoSpeedDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SPEED_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SPEED_GET;
    }

    public double getCurrentSpeed() {
        return this.mSpeedManagerImpl.getCurrentSpeed();
    }

    public int getAccelerateDeepness() {
        return this.mSpeedManagerImpl.getAccelerateDeepness();
    }

    public float getSpeedValue() {
        return this.mSpeedManagerImpl.getSpeedValue();
    }

    public int getBrakeDeepness() {
        return this.mSpeedManagerImpl.getBrakeDeepness();
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return true;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        return true;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoSpeedListener listener) {
        this.mSpeedManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoSpeedListener listener, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoSpeedListener listener) {
        this.mSpeedManagerImpl.unregisterListener(listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mSpeedManagerImpl.getDevicetype();
    }

    public float getAverrageSpeed() {
        return this.mSpeedManagerImpl.getAverrageSpeed();
    }

    public float getAccelerateValue() {
        return this.mSpeedManagerImpl.getAccelerateValue();
    }

    public float getSpeedFromGateway() {
        return this.mSpeedManagerImpl.getSpeedFromGateway();
    }

    public int getDataFlag(int item) {
        return this.mSpeedManagerImpl.getDataFlag(item);
    }

    public float getFuelAccelerateDeepness() {
        return this.mSpeedManagerImpl.getFuelAccelerateDeepness();
    }

    public double getIPBVehicleSpeedStatus() {
        return this.mSpeedManagerImpl.getIpbVehicleSpeedStatusR();
    }

    public double getSpeedAliveCounter() {
        return this.mSpeedManagerImpl.getSpeedAliveCounterR();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mSpeedManagerImpl.get(eventTypes, type);
    }

    public int getAccelerateDeepnessSignificantBit() {
        return this.mSpeedManagerImpl.getAccelerateDeepnessSignificantBit();
    }
}
