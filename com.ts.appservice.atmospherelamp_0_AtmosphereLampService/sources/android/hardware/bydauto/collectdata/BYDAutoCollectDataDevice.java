package android.hardware.bydauto.collectdata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.collectdata.tsimpl.CollectDataManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoCollectDataDevice extends AbsBYDAutoDevice {
    public static final int S_FRONT_MOTOR = 1;
    public static final int S_REAR_MOTOR = 2;
    private static volatile CollectDataManagerImpl mDataPickImpl = null;
    public static volatile BYDAutoCollectDataDevice mInstance;

    public static BYDAutoCollectDataDevice getInstance(Context con) {
        if (mInstance == null) {
            synchronized (BYDAutoCollectDataDevice.class) {
                if (mInstance == null) {
                    mInstance = new BYDAutoCollectDataDevice(con.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private BYDAutoCollectDataDevice(Context con) {
        super(con);
        mDataPickImpl = CollectDataManagerImpl.getInstance(con.getApplicationContext());
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

    public int getDriverMotorSpeed(int motorPos) {
        return mDataPickImpl.getDriverMotorSpeed(motorPos);
    }

    @Deprecated
    public int getDriverMotorTorque(int motorPos) {
        return mDataPickImpl.getDriverMotorTorque(motorPos);
    }

    public int getDriverMotorTemperature(int motorPos) {
        return mDataPickImpl.getDriverMotorTemperature(motorPos);
    }

    @Deprecated
    public int getMotorMCUGeneratrixVolt(int motorPos) {
        return mDataPickImpl.getMotorMCUGeneratrixVolt(motorPos);
    }

    @Deprecated
    public int getMotorMCUGeneratrixCurrent(int motorPos) {
        return mDataPickImpl.getMotorMCUGeneratrixCurrent(motorPos);
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

    public void registerListener(AbsBYDAutoCollectDataListener listener) {
        mDataPickImpl.registerListener(listener);
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener listener) {
        mDataPickImpl.unRegisterListener(listener);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return mDataPickImpl.get(eventTypes, type);
    }
}
