package android.hardware.bydauto.collectdata.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.collectdata.AbsBYDAutoCollectDataListener;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.OnCloudChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CollectDataManagerImpl extends AbsTsMyCar {
    private static final int S_INVALID = -1;
    public static final String TAG = "DataPickImpl";
    public static volatile CollectDataManagerImpl mInstance;
    private Context mContext;
    private int[] mFeatureIds;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoCollectDataListener> mBYDAutoDataPickListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private CollectDataManagerImpl(Context context) {
        this.mContext = context;
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCloudChangedListener(new OnCloudChangedListener() { // from class: android.hardware.bydauto.collectdata.tsimpl.CollectDataManagerImpl.1
            public void onBinderStateChanged(boolean z) {
            }

            public void onCloudChanged(int i, int i2, CarCloudPacket carCloudPacket) {
            }

            public void onCollectDataChanged(int i, int i2, CarCollectDataPacket carCollectDataPacket) {
                if (CollectDataManagerImpl.this.mBYDAutoDataPickListeners.isEmpty()) {
                    return;
                }
                try {
                    CollectDataManagerImpl.this.collectData(i, i2, carCollectDataPacket);
                } catch (Exception unused) {
                    Log.e(CollectDataManagerImpl.TAG, "collectData is error");
                }
            }

            public void onRemoteChanged(int i, int i2, CarRemotePacket carRemotePacket) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectData(int i, int i2, CarCollectDataPacket carCollectDataPacket) {
        if (i == 559969290) {
            float engineSpeed = carCollectDataPacket.getEngineSpeed();
            Iterator<AbsBYDAutoCollectDataListener> it = this.mBYDAutoDataPickListeners.iterator();
            while (it.hasNext()) {
                it.next().onEngineSpeed((int) engineSpeed);
            }
            Log.v(TAG, "ID_ENGINE_SPEED value is " + engineSpeed);
        }
        switch (i) {
            case CollectDataHalId.ID_DRIVER_MOTOR_SPEED_FRONT /* 557872128 */:
            case CollectDataHalId.ID_DRIVER_MOTOR_SPEED_REAR /* 557872129 */:
                int driverMotorSpeedFront = carCollectDataPacket.getDriverMotorSpeedFront();
                int driverMotorSpeedRear = carCollectDataPacket.getDriverMotorSpeedRear();
                Iterator<AbsBYDAutoCollectDataListener> it2 = this.mBYDAutoDataPickListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onDriverMotorSpeed(driverMotorSpeedFront, driverMotorSpeedRear);
                }
                Log.v(TAG, "ID_DRIVER_MOTOR_SPEED_FRONT value is " + driverMotorSpeedFront + " ID_DRIVER_MOTOR_SPEED_REAR value is " + driverMotorSpeedRear);
                break;
            case CollectDataHalId.ID_DRIVER_MOTOR_TORQUE_FRONT /* 557872130 */:
            case CollectDataHalId.ID_DRIVER_MOTOR_TORQUE_REAR /* 557872131 */:
                int driverMotorTorqueFront = carCollectDataPacket.getDriverMotorTorqueFront();
                int driverMotorTorqueRear = carCollectDataPacket.getDriverMotorTorqueRear();
                Iterator<AbsBYDAutoCollectDataListener> it3 = this.mBYDAutoDataPickListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriverMotorTorque(driverMotorTorqueFront, driverMotorTorqueRear);
                }
                Log.v(TAG, "ID_DRIVER_MOTOR_TORQUE_FRONT value is " + driverMotorTorqueFront + " ID_DRIVER_MOTOR_TORQUE_REAR value is " + driverMotorTorqueRear);
                break;
            case CollectDataHalId.ID_DRIVER_MOTOR_TEMPERATURE_FRONT /* 557872132 */:
            case CollectDataHalId.ID_DRIVER_MOTOR_TEMPERATURE_REAR /* 557872133 */:
                int driverMotorTemperatureFront = carCollectDataPacket.getDriverMotorTemperatureFront();
                int driverMotorTemperatureRear = carCollectDataPacket.getDriverMotorTemperatureRear();
                Iterator<AbsBYDAutoCollectDataListener> it4 = this.mBYDAutoDataPickListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onDriverMotorTemperature(driverMotorTemperatureFront, driverMotorTemperatureRear);
                }
                Log.v(TAG, "ID_DRIVER_MOTOR_TEMPERATURE_FRONT value is " + driverMotorTemperatureFront + " ID_DRIVER_MOTOR_TEMPERATURE_REAR value is " + driverMotorTemperatureRear);
                break;
            case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT /* 557872134 */:
            case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_VOLT_REAR /* 557872135 */:
                int motorMCUGeneratrixVoltFront = carCollectDataPacket.getMotorMCUGeneratrixVoltFront();
                int motorMCUGeneratrixVoltRear = carCollectDataPacket.getMotorMCUGeneratrixVoltRear();
                Iterator<AbsBYDAutoCollectDataListener> it5 = this.mBYDAutoDataPickListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onMotorMCUGeneratrixVolt(motorMCUGeneratrixVoltFront, motorMCUGeneratrixVoltRear);
                }
                Log.v(TAG, "ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT value is " + motorMCUGeneratrixVoltFront + " ID_MOTOR_MCU_GENERATRIX_VOLT_REAR value is " + motorMCUGeneratrixVoltRear);
                break;
            case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT /* 557872136 */:
            case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_CURRENT_REAR /* 557872137 */:
                int motorMCUGeneratrixCurrentFront = carCollectDataPacket.getMotorMCUGeneratrixCurrentFront();
                int motorMCUGeneratrixCurrentRear = carCollectDataPacket.getMotorMCUGeneratrixCurrentRear();
                Iterator<AbsBYDAutoCollectDataListener> it6 = this.mBYDAutoDataPickListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onMotorMCUGeneratrixCurrent(motorMCUGeneratrixCurrentFront, motorMCUGeneratrixCurrentRear);
                }
                Log.v(TAG, "ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + motorMCUGeneratrixCurrentFront + " ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + motorMCUGeneratrixCurrentRear);
                break;
            default:
                switch (i) {
                    case CollectDataHalId.ID_DC_CHARGING_FAULT /* 557872139 */:
                        int dCChargingFault = carCollectDataPacket.getDCChargingFault();
                        Iterator<AbsBYDAutoCollectDataListener> it7 = this.mBYDAutoDataPickListeners.iterator();
                        while (it7.hasNext()) {
                            it7.next().onDCChargingFault(dCChargingFault);
                        }
                        Log.v(TAG, "ID_DC_CHARGING_FAULT value is " + dCChargingFault);
                        break;
                    case CollectDataHalId.ID_WATER_TEMPERATION_ALARM /* 557872140 */:
                        int waterTemperationAlarm = carCollectDataPacket.getWaterTemperationAlarm();
                        Iterator<AbsBYDAutoCollectDataListener> it8 = this.mBYDAutoDataPickListeners.iterator();
                        while (it8.hasNext()) {
                            it8.next().onWaterTemperationAlarm(waterTemperationAlarm);
                        }
                        Log.v(TAG, "ID_WATER_TEMPERATION_ALARM value is " + waterTemperationAlarm);
                        break;
                    case CollectDataHalId.ID_COLLISION /* 557872141 */:
                        int collision = carCollectDataPacket.getCollision();
                        Iterator<AbsBYDAutoCollectDataListener> it9 = this.mBYDAutoDataPickListeners.iterator();
                        while (it9.hasNext()) {
                            it9.next().onCollision(collision);
                        }
                        Log.v(TAG, "ID_COLLISION value is " + collision);
                        break;
                }
                break;
        }
    }

    public static CollectDataManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CollectDataManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new CollectDataManagerImpl(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 1039) {
                        int dmVersionCode = getDmVersionCode();
                        bYDAutoEventValue.intValue = dmVersionCode;
                        sparseArray.put(1039, Integer.valueOf(dmVersionCode));
                    } else if (i == 1668) {
                        int collision = getCollision();
                        bYDAutoEventValue.intValue = collision;
                        sparseArray.put(1668, Integer.valueOf(collision));
                    } else if (i != 3625) {
                        Log.v(TAG, "case fail ! please check out");
                    } else {
                        int dmVersionCode2 = getDmVersionCode();
                        bYDAutoEventValue.intValue = dmVersionCode2;
                        sparseArray.put(3625, Integer.valueOf(dmVersionCode2));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getCollision() {
        Log.v(TAG, "getCollision is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getCollision();
    }

    public int getDCChargingFault() {
        Log.v(TAG, "getDCChargingFault is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDCChargingFault();
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is execute");
        return this.mTsManagerImpl.getCarCloudAdapterManager() == null ? -1 : 0;
    }

    public int getDmVersionCode() {
        Log.v(TAG, "getDmVersionCode is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCloudAdapterManager().getDmVersionCode();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDriveMoterSerial() {
        Log.v(TAG, "getDriveMoterSerial is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriveMoterSerial();
    }

    public int getDriverMotorSpeed(int i) {
        Log.v(TAG, "getDriverMotorSpeed is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorSpeed(i);
    }

    public int getDriverMotorTemperature(int i) {
        Log.v(TAG, "getDriverMotorTemperature is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorTemperature(i);
    }

    @Deprecated
    public int getDriverMotorTorque(int i) {
        Log.v(TAG, "getDriverMotorTorque is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getDriverMotorTorque(i);
    }

    @Deprecated
    public double getEngineSpeed() {
        Log.v(TAG, "getEngineSpeed is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1.0d;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getEngineSpeed();
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        return "";
    }

    @Deprecated
    public int getMotorMCUGeneratrixCurrent(int i) {
        Log.v(TAG, "getMotorMCUGeneratrixCurrent is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getMotorMCUGeneratrixCurrent(i);
    }

    @Deprecated
    public int getMotorMCUGeneratrixVolt(int i) {
        Log.v(TAG, "getMotorMCUGeneratrixVolt is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getMotorMCUGeneratrixVolt(i);
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is execute");
        return "";
    }

    public int getWaterTemperationAlarm() {
        Log.v(TAG, "getWaterTemperationAlarm is execute");
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            return -1;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getWaterTemperationAlarm();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        Log.d(TAG, "registerListener is execute subscribeCollectCallback: " + absBYDAutoCollectDataListener);
        if (absBYDAutoCollectDataListener == null) {
            return;
        }
        if (!this.mBYDAutoDataPickListeners.contains(absBYDAutoCollectDataListener)) {
            this.mBYDAutoDataPickListeners.add(absBYDAutoCollectDataListener);
        }
        registerConnectListener(this.mContext, absBYDAutoCollectDataListener);
        TsManagerImpl.getInstance(this.mContext).registerCollectCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        Log.d(TAG, "unregisterListener is execute unsubscribeHvacCallback: " + absBYDAutoCollectDataListener);
        if (absBYDAutoCollectDataListener == null) {
            return;
        }
        this.mBYDAutoDataPickListeners.remove(absBYDAutoCollectDataListener);
        unregisterConnectListener(absBYDAutoCollectDataListener);
    }
}
