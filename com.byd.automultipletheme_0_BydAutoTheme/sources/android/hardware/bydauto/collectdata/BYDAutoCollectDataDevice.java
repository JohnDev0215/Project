package android.hardware.bydauto.collectdata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.collectdata.tsimpl.CollectDataManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoCollectDataDevice extends AbsBYDAutoDevice {
    public static final int S_FRONT_MOTOR = 1;
    public static final int S_REAR_MOTOR = 2;
    private static volatile CollectDataManagerImpl mDataPickImpl;
    public static volatile BYDAutoCollectDataDevice mInstance;

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public static BYDAutoCollectDataDevice getInstance(Context context) {
        if (mInstance == null) {
            synchronized (BYDAutoCollectDataDevice.class) {
                if (mInstance == null) {
                    mInstance = new BYDAutoCollectDataDevice(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private BYDAutoCollectDataDevice(Context context) {
        super(context);
        mDataPickImpl = CollectDataManagerImpl.getInstance(context.getApplicationContext());
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return mDataPickImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return mDataPickImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return mDataPickImpl.getGetPermission();
    }

    @Deprecated
    public int getDriveMoterSerial() {
        return mDataPickImpl.getDriveMoterSerial();
    }

    public int getDriverMotorSpeed(int i) {
        return mDataPickImpl.getDriverMotorSpeed(i);
    }

    @Deprecated
    public int getDriverMotorTorque(int i) {
        return mDataPickImpl.getDriverMotorTorque(i);
    }

    public int getDriverMotorTemperature(int i) {
        return mDataPickImpl.getDriverMotorTemperature(i);
    }

    @Deprecated
    public int getMotorMCUGeneratrixVolt(int i) {
        return mDataPickImpl.getMotorMCUGeneratrixVolt(i);
    }

    @Deprecated
    public int getMotorMCUGeneratrixCurrent(int i) {
        return mDataPickImpl.getMotorMCUGeneratrixCurrent(i);
    }

    @Deprecated
    public double getEngineSpeed() {
        return mDataPickImpl.getEngineSpeed();
    }

    public int getDCChargingFault() {
        return mDataPickImpl.getDCChargingFault();
    }

    public int getWaterTemperationAlarm() {
        return mDataPickImpl.getWaterTemperationAlarm();
    }

    public int getCollision() {
        return mDataPickImpl.getCollision();
    }

    public int getDmVersionCode() {
        return mDataPickImpl.getDmVersionCode();
    }

    public void registerListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        mDataPickImpl.registerListener(absBYDAutoCollectDataListener);
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        mDataPickImpl.unRegisterListener(absBYDAutoCollectDataListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return mDataPickImpl.get(iArr, cls);
    }
}
