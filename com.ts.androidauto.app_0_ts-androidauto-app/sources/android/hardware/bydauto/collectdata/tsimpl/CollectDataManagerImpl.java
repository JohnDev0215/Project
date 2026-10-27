package android.hardware.bydauto.collectdata.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.collectdata.AbsBYDAutoCollectDataListener;
import android.util.Log;
import android.util.SparseArray;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.OnCloudChangedListener;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CollectDataManagerImpl extends AbsTsMyCar {
    private static final int S_INVALID = -1;
    protected static final String TAG = "DataPickImpl";
    public static volatile CollectDataManagerImpl mInstance;
    private Context mContext;
    private int[] mFeatureIds;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoCollectDataListener> mBYDAutoDataPickListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    public static CollectDataManagerImpl getInstance(Context con) {
        if (mInstance == null) {
            synchronized (CollectDataManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new CollectDataManagerImpl(con.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private CollectDataManagerImpl(Context con) {
        this.mContext = con;
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCloudChangedListener(new OnCloudChangedListener() { // from class: android.hardware.bydauto.collectdata.tsimpl.CollectDataManagerImpl.1
            public void onCloudChanged(int id, int areaId, CarCloudPacket packet) {
            }

            public void onRemoteChanged(int id, int areaId, CarRemotePacket packet) {
            }

            public void onCollectDataChanged(int id, int areaId, CarCollectDataPacket packet) {
                if (!CollectDataManagerImpl.this.mBYDAutoDataPickListeners.isEmpty()) {
                    try {
                        CollectDataManagerImpl.this.collectData(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(CollectDataManagerImpl.TAG, "collectData is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectData(int id, int areaId, CarCollectDataPacket packet) {
        if (id != 559969290) {
            switch (id) {
                case 557872128:
                case 557872129:
                    int driverMotorSpeedFront = packet.getDriverMotorSpeedFront();
                    int driverMotorSpeedRear = packet.getDriverMotorSpeedRear();
                    for (AbsBYDAutoCollectDataListener listener : this.mBYDAutoDataPickListeners) {
                        listener.onDriverMotorSpeed(driverMotorSpeedFront, driverMotorSpeedRear);
                    }
                    Log.v(TAG, "ID_DRIVER_MOTOR_SPEED_FRONT value is " + driverMotorSpeedFront + " ID_DRIVER_MOTOR_SPEED_REAR value is " + driverMotorSpeedRear);
                    break;
                case 557872130:
                case 557872131:
                    int driverMotorTorqueFront = packet.getDriverMotorTorqueFront();
                    int driverMotorTorqueRear = packet.getDriverMotorTorqueRear();
                    for (AbsBYDAutoCollectDataListener listener2 : this.mBYDAutoDataPickListeners) {
                        listener2.onDriverMotorTorque(driverMotorTorqueFront, driverMotorTorqueRear);
                    }
                    Log.v(TAG, "ID_DRIVER_MOTOR_TORQUE_FRONT value is " + driverMotorTorqueFront + " ID_DRIVER_MOTOR_TORQUE_REAR value is " + driverMotorTorqueRear);
                    break;
                case 557872132:
                case 557872133:
                    int driverMotorTemperatureFront = packet.getDriverMotorTemperatureFront();
                    int driverMotorTemperatureRear = packet.getDriverMotorTemperatureRear();
                    for (AbsBYDAutoCollectDataListener listener3 : this.mBYDAutoDataPickListeners) {
                        listener3.onDriverMotorTemperature(driverMotorTemperatureFront, driverMotorTemperatureRear);
                    }
                    Log.v(TAG, "ID_DRIVER_MOTOR_TEMPERATURE_FRONT value is " + driverMotorTemperatureFront + " ID_DRIVER_MOTOR_TEMPERATURE_REAR value is " + driverMotorTemperatureRear);
                    break;
                case 557872134:
                case 557872135:
                    int mcuGeneratrixVoltFront = packet.getMotorMCUGeneratrixVoltFront();
                    int mcuGeneratrixVoltRear = packet.getMotorMCUGeneratrixVoltRear();
                    for (AbsBYDAutoCollectDataListener listener4 : this.mBYDAutoDataPickListeners) {
                        listener4.onMotorMCUGeneratrixVolt(mcuGeneratrixVoltFront, mcuGeneratrixVoltRear);
                    }
                    Log.v(TAG, "ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT value is " + mcuGeneratrixVoltFront + " ID_MOTOR_MCU_GENERATRIX_VOLT_REAR value is " + mcuGeneratrixVoltRear);
                    break;
                case 557872136:
                case 557872137:
                    int currentFront = packet.getMotorMCUGeneratrixCurrentFront();
                    int currentRear = packet.getMotorMCUGeneratrixCurrentRear();
                    for (AbsBYDAutoCollectDataListener listener5 : this.mBYDAutoDataPickListeners) {
                        listener5.onMotorMCUGeneratrixCurrent(currentFront, currentRear);
                    }
                    Log.v(TAG, "ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + currentFront + " ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + currentRear);
                    break;
                default:
                    switch (id) {
                        case 557872139:
                            int dcChargingFault = packet.getDCChargingFault();
                            for (AbsBYDAutoCollectDataListener listener6 : this.mBYDAutoDataPickListeners) {
                                listener6.onDCChargingFault(dcChargingFault);
                            }
                            Log.v(TAG, "ID_DC_CHARGING_FAULT value is " + dcChargingFault);
                            break;
                        case 557872140:
                            int waterTemperationAlarm = packet.getWaterTemperationAlarm();
                            for (AbsBYDAutoCollectDataListener listener7 : this.mBYDAutoDataPickListeners) {
                                listener7.onWaterTemperationAlarm(waterTemperationAlarm);
                            }
                            Log.v(TAG, "ID_WATER_TEMPERATION_ALARM value is " + waterTemperationAlarm);
                            break;
                        case 557872141:
                            int collision = packet.getCollision();
                            for (AbsBYDAutoCollectDataListener listener8 : this.mBYDAutoDataPickListeners) {
                                listener8.onCollision(collision);
                            }
                            Log.v(TAG, "ID_COLLISION value is " + collision);
                            break;
                    }
                    break;
            }
        }
        float engineSpeed = packet.getEngineSpeed();
        for (AbsBYDAutoCollectDataListener listener9 : this.mBYDAutoDataPickListeners) {
            listener9.onEngineSpeed((int) engineSpeed);
        }
        Log.v(TAG, "ID_ENGINE_SPEED value is " + engineSpeed);
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return 0;
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is execute");
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Deprecated
    public int getDriveMoterSerial() {
        Log.v(TAG, "getDriveMoterSerial is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriveMoterSerial();
    }

    public int getDriverMotorSpeed(int motorPos) {
        Log.v(TAG, "getDriverMotorSpeed is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorSpeed(motorPos);
    }

    @Deprecated
    public int getDriverMotorTorque(int motorPos) {
        Log.v(TAG, "getDriverMotorTorque is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorTorque(motorPos);
    }

    public int getDriverMotorTemperature(int motorPos) {
        Log.v(TAG, "getDriverMotorTemperature is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorTemperature(motorPos);
    }

    @Deprecated
    public int getMotorMCUGeneratrixVolt(int motorPos) {
        Log.v(TAG, "getMotorMCUGeneratrixVolt is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getMotorMCUGeneratrixVolt(motorPos);
    }

    @Deprecated
    public int getMotorMCUGeneratrixCurrent(int motorPos) {
        Log.v(TAG, "getMotorMCUGeneratrixCurrent is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getMotorMCUGeneratrixCurrent(motorPos);
    }

    @Deprecated
    public double getEngineSpeed() {
        Log.v(TAG, "getEngineSpeed is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1.0d;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getEngineSpeed();
    }

    public int getDCChargingFault() {
        Log.v(TAG, "getDCChargingFault is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDCChargingFault();
    }

    public int getWaterTemperationAlarm() {
        Log.v(TAG, "getWaterTemperationAlarm is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getWaterTemperationAlarm();
    }

    public int getCollision() {
        Log.v(TAG, "getCollision is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getCollision();
    }

    public int getDmVersionCode() {
        Log.v(TAG, "getDmVersionCode is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDmVersionCode();
    }

    public void registerListener(AbsBYDAutoCollectDataListener listener) {
        Log.d(TAG, "registerListener is execute subscribeCollectCallback: " + listener);
        if (listener == null) {
            return;
        }
        if (!this.mBYDAutoDataPickListeners.contains(listener)) {
            this.mBYDAutoDataPickListeners.add(listener);
        }
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCollectCallback();
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener listener) {
        Log.d(TAG, "unregisterListener is execute unsubscribeHvacCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mBYDAutoDataPickListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 1039) {
                        bydAutoEventValue.intValue = getDmVersionCode();
                        sparseArray.put(1039, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 1668) {
                        bydAutoEventValue.intValue = getCollision();
                        sparseArray.put(1668, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 3625) {
                        Log.v(TAG, "case fail ! please check out");
                    } else {
                        bydAutoEventValue.intValue = getDmVersionCode();
                        sparseArray.put(3625, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        return 0;
    }
}
