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

    private BYDAutoCollectDataDevice(Context context) {
        super(context);
        mDataPickImpl = CollectDataManagerImpl.getInstance(context.getApplicationContext());
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return mDataPickImpl.get(iArr, cls);
    }

    public int getCollision() {
        return mDataPickImpl.getCollision();
    }

    public int getDCChargingFault() {
        return mDataPickImpl.getDCChargingFault();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return mDataPickImpl.getDevicetype();
    }

    public int getDmVersionCode() {
        return mDataPickImpl.getDmVersionCode();
    }

    @Deprecated
    public int getDriveMoterSerial() {
        return mDataPickImpl.getDriveMoterSerial();
    }

    public int getDriverMotorSpeed(int i3) {
        return mDataPickImpl.getDriverMotorSpeed(i3);
    }

    public int getDriverMotorTemperature(int i3) {
        return mDataPickImpl.getDriverMotorTemperature(i3);
    }

    @Deprecated
    public int getDriverMotorTorque(int i3) {
        return mDataPickImpl.getDriverMotorTorque(i3);
    }

    @Deprecated
    public double getEngineSpeed() {
        return mDataPickImpl.getEngineSpeed();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return mDataPickImpl.getGetPermission();
    }

    @Deprecated
    public int getMotorMCUGeneratrixCurrent(int i3) {
        return mDataPickImpl.getMotorMCUGeneratrixCurrent(i3);
    }

    @Deprecated
    public int getMotorMCUGeneratrixVolt(int i3) {
        return mDataPickImpl.getMotorMCUGeneratrixVolt(i3);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return mDataPickImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getWaterTemperationAlarm() {
        return mDataPickImpl.getWaterTemperationAlarm();
    }

    public void registerListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        mDataPickImpl.registerListener(absBYDAutoCollectDataListener);
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        mDataPickImpl.unRegisterListener(absBYDAutoCollectDataListener);
    }
}
