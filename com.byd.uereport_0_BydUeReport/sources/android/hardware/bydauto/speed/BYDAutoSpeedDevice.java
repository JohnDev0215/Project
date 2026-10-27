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
    public static final String TAG = "BYDAutoSpeedDevice";
    public static int mAccFlag;
    private static volatile BYDAutoSpeedDevice mBYDAutoSpeedDevice;
    public static int mBrakeFlag;
    public static int mSpeedFlag;
    private SpeedManagerImpl mSpeedManagerImpl;

    private BYDAutoSpeedDevice(Context context) {
        super(context);
        this.mSpeedManagerImpl = SpeedManagerImpl.getInstance(context);
    }

    public static BYDAutoSpeedDevice getInstance(Context context) {
        if (mBYDAutoSpeedDevice == null) {
            synchronized (BYDAutoSpeedDevice.class) {
                if (mBYDAutoSpeedDevice == null) {
                    mBYDAutoSpeedDevice = new BYDAutoSpeedDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoSpeedDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mSpeedManagerImpl.get(iArr, cls);
    }

    public int getAccelerateDeepness() {
        return this.mSpeedManagerImpl.getAccelerateDeepness();
    }

    public int getAccelerateDeepnessSignificantBit() {
        return this.mSpeedManagerImpl.getAccelerateDeepnessSignificantBit();
    }

    public float getAccelerateValue() {
        return this.mSpeedManagerImpl.getAccelerateValue();
    }

    public void getAllStatus() {
    }

    public float getAverrageSpeed() {
        return this.mSpeedManagerImpl.getAverrageSpeed();
    }

    public int getBrakeDeepness() {
        return this.mSpeedManagerImpl.getBrakeDeepness();
    }

    public double getCurrentSpeed() {
        return this.mSpeedManagerImpl.getCurrentSpeed();
    }

    public int getDataFlag(int i8) {
        return this.mSpeedManagerImpl.getDataFlag(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mSpeedManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public float getFuelAccelerateDeepness() {
        return this.mSpeedManagerImpl.getFuelAccelerateDeepness();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SPEED_GET;
    }

    public double getIPBVehicleSpeedStatus() {
        return this.mSpeedManagerImpl.getIpbVehicleSpeedStatusR();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SPEED_SET;
    }

    public double getSpeedAliveCounter() {
        return this.mSpeedManagerImpl.getSpeedAliveCounterR();
    }

    public float getSpeedFromGateway() {
        return this.mSpeedManagerImpl.getSpeedFromGateway();
    }

    public float getSpeedValue() {
        return this.mSpeedManagerImpl.getSpeedValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, float f8, Object obj) {
        return true;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, byte[] bArr, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        this.mSpeedManagerImpl.registerListener(absBYDAutoSpeedListener);
    }

    public void registerListener(AbsBYDAutoSpeedListener absBYDAutoSpeedListener, int[] iArr) {
    }

    public void unregisterListener(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        this.mSpeedManagerImpl.unregisterListener(absBYDAutoSpeedListener);
    }
}
