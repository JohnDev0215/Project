package android.hardware.bydauto.collectdata.tsimpl;

import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.collectdata.AbsBYDAutoCollectDataListener;
import android.util.Log;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.OnCloudChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CollectDataManagerImpl {
    private static final int S_INVALID = -1;
    protected static final String TAG = "DataPickImpl";
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
        StringBuilder sb;
        String string;
        int driverMotorSpeedRear;
        StringBuilder sb2;
        String str;
        int dCChargingFault;
        String str2;
        if (i != 559969290) {
            switch (i) {
                case CollectDataHalId.ID_DRIVER_MOTOR_SPEED_FRONT /* 557872128 */:
                case CollectDataHalId.ID_DRIVER_MOTOR_SPEED_REAR /* 557872129 */:
                    int driverMotorSpeedFront = carCollectDataPacket.getDriverMotorSpeedFront();
                    driverMotorSpeedRear = carCollectDataPacket.getDriverMotorSpeedRear();
                    Iterator<AbsBYDAutoCollectDataListener> it = this.mBYDAutoDataPickListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onDriverMotorSpeed(driverMotorSpeedFront, driverMotorSpeedRear);
                    }
                    sb2 = new StringBuilder();
                    sb2.append("ID_DRIVER_MOTOR_SPEED_FRONT value is ");
                    sb2.append(driverMotorSpeedFront);
                    str = " ID_DRIVER_MOTOR_SPEED_REAR value is ";
                    sb2.append(str);
                    sb2.append(driverMotorSpeedRear);
                    string = sb2.toString();
                    Log.v(TAG, string);
                    break;
                case CollectDataHalId.ID_DRIVER_MOTOR_TORQUE_FRONT /* 557872130 */:
                case CollectDataHalId.ID_DRIVER_MOTOR_TORQUE_REAR /* 557872131 */:
                    int driverMotorTorqueFront = carCollectDataPacket.getDriverMotorTorqueFront();
                    driverMotorSpeedRear = carCollectDataPacket.getDriverMotorTorqueRear();
                    Iterator<AbsBYDAutoCollectDataListener> it2 = this.mBYDAutoDataPickListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onDriverMotorTorque(driverMotorTorqueFront, driverMotorSpeedRear);
                    }
                    sb2 = new StringBuilder();
                    sb2.append("ID_DRIVER_MOTOR_TORQUE_FRONT value is ");
                    sb2.append(driverMotorTorqueFront);
                    str = " ID_DRIVER_MOTOR_TORQUE_REAR value is ";
                    sb2.append(str);
                    sb2.append(driverMotorSpeedRear);
                    string = sb2.toString();
                    Log.v(TAG, string);
                    break;
                case CollectDataHalId.ID_DRIVER_MOTOR_TEMPERATURE_FRONT /* 557872132 */:
                case CollectDataHalId.ID_DRIVER_MOTOR_TEMPERATURE_REAR /* 557872133 */:
                    int driverMotorTemperatureFront = carCollectDataPacket.getDriverMotorTemperatureFront();
                    driverMotorSpeedRear = carCollectDataPacket.getDriverMotorTemperatureRear();
                    Iterator<AbsBYDAutoCollectDataListener> it3 = this.mBYDAutoDataPickListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onDriverMotorTemperature(driverMotorTemperatureFront, driverMotorSpeedRear);
                    }
                    sb2 = new StringBuilder();
                    sb2.append("ID_DRIVER_MOTOR_TEMPERATURE_FRONT value is ");
                    sb2.append(driverMotorTemperatureFront);
                    str = " ID_DRIVER_MOTOR_TEMPERATURE_REAR value is ";
                    sb2.append(str);
                    sb2.append(driverMotorSpeedRear);
                    string = sb2.toString();
                    Log.v(TAG, string);
                    break;
                case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT /* 557872134 */:
                case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_VOLT_REAR /* 557872135 */:
                    int motorMCUGeneratrixVoltFront = carCollectDataPacket.getMotorMCUGeneratrixVoltFront();
                    driverMotorSpeedRear = carCollectDataPacket.getMotorMCUGeneratrixVoltRear();
                    Iterator<AbsBYDAutoCollectDataListener> it4 = this.mBYDAutoDataPickListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onMotorMCUGeneratrixVolt(motorMCUGeneratrixVoltFront, driverMotorSpeedRear);
                    }
                    sb2 = new StringBuilder();
                    sb2.append("ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT value is ");
                    sb2.append(motorMCUGeneratrixVoltFront);
                    str = " ID_MOTOR_MCU_GENERATRIX_VOLT_REAR value is ";
                    sb2.append(str);
                    sb2.append(driverMotorSpeedRear);
                    string = sb2.toString();
                    Log.v(TAG, string);
                    break;
                case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT /* 557872136 */:
                case CollectDataHalId.ID_MOTOR_MCU_GENERATRIX_CURRENT_REAR /* 557872137 */:
                    int motorMCUGeneratrixCurrentFront = carCollectDataPacket.getMotorMCUGeneratrixCurrentFront();
                    driverMotorSpeedRear = carCollectDataPacket.getMotorMCUGeneratrixCurrentRear();
                    Iterator<AbsBYDAutoCollectDataListener> it5 = this.mBYDAutoDataPickListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onMotorMCUGeneratrixCurrent(motorMCUGeneratrixCurrentFront, driverMotorSpeedRear);
                    }
                    sb2 = new StringBuilder();
                    sb2.append("ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is ");
                    sb2.append(motorMCUGeneratrixCurrentFront);
                    str = " ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is ";
                    sb2.append(str);
                    sb2.append(driverMotorSpeedRear);
                    string = sb2.toString();
                    Log.v(TAG, string);
                    break;
                default:
                    switch (i) {
                        case CollectDataHalId.ID_DC_CHARGING_FAULT /* 557872139 */:
                            dCChargingFault = carCollectDataPacket.getDCChargingFault();
                            Iterator<AbsBYDAutoCollectDataListener> it6 = this.mBYDAutoDataPickListeners.iterator();
                            while (it6.hasNext()) {
                                it6.next().onDCChargingFault(dCChargingFault);
                            }
                            sb = new StringBuilder();
                            str2 = "ID_DC_CHARGING_FAULT value is ";
                            break;
                        case CollectDataHalId.ID_WATER_TEMPERATION_ALARM /* 557872140 */:
                            dCChargingFault = carCollectDataPacket.getWaterTemperationAlarm();
                            Iterator<AbsBYDAutoCollectDataListener> it7 = this.mBYDAutoDataPickListeners.iterator();
                            while (it7.hasNext()) {
                                it7.next().onWaterTemperationAlarm(dCChargingFault);
                            }
                            sb = new StringBuilder();
                            str2 = "ID_WATER_TEMPERATION_ALARM value is ";
                            break;
                        case CollectDataHalId.ID_COLLISION /* 557872141 */:
                            dCChargingFault = carCollectDataPacket.getCollision();
                            Iterator<AbsBYDAutoCollectDataListener> it8 = this.mBYDAutoDataPickListeners.iterator();
                            while (it8.hasNext()) {
                                it8.next().onCollision(dCChargingFault);
                            }
                            sb = new StringBuilder();
                            str2 = "ID_COLLISION value is ";
                            break;
                    }
                    sb.append(str2);
                    sb.append(dCChargingFault);
                    break;
            }
        }
        float engineSpeed = carCollectDataPacket.getEngineSpeed();
        Iterator<AbsBYDAutoCollectDataListener> it9 = this.mBYDAutoDataPickListeners.iterator();
        while (it9.hasNext()) {
            it9.next().onEngineSpeed((int) engineSpeed);
        }
        sb = new StringBuilder();
        sb.append("ID_ENGINE_SPEED value is ");
        sb.append(engineSpeed);
        string = sb.toString();
        Log.v(TAG, string);
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

    /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
            android.hardware.bydauto.BYDAutoEventValue r13 = new android.hardware.bydauto.BYDAutoEventValue
            r13.<init>()
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            int r1 = r12.length
            int[] r1 = new int[r1]
            r13.intArrayValue = r1
            int r1 = r12.length
            r2 = -2147482648(0xffffffff800003e8, float:-1.401E-42)
            r3 = 1
            if (r1 >= r3) goto L19
            r13.intValue = r2
            goto L6d
        L19:
            int r1 = r12.length
            r3 = 0
            r4 = r3
        L1c:
            if (r4 >= r1) goto L6d
            r5 = r12[r4]
            if (r5 <= 0) goto L68
            java.lang.String r6 = "DataPickImpl"
            r7 = 1039(0x40f, float:1.456E-42)
            if (r5 == r7) goto L3b
            r7 = 1668(0x684, float:2.337E-42)
            if (r5 == r7) goto L36
            r7 = 3625(0xe29, float:5.08E-42)
            if (r5 == r7) goto L3b
            java.lang.String r5 = "case fail ! please check out"
            android.util.Log.v(r6, r5)
            goto L48
        L36:
            int r5 = r11.getCollision()
            goto L3f
        L3b:
            int r5 = r11.getDmVersionCode()
        L3f:
            r13.intValue = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r7, r5)
        L48:
            int r5 = r12.length
            r7 = r3
            r8 = r7
        L4b:
            if (r7 >= r5) goto L6a
            r9 = r12[r7]
            java.lang.Object r9 = r0.get(r9)     // Catch: java.lang.Exception -> L5e
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.Exception -> L5e
            int r9 = r9.intValue()     // Catch: java.lang.Exception -> L5e
            int[] r10 = r13.intArrayValue     // Catch: java.lang.Exception -> L5e
            r10[r8] = r9     // Catch: java.lang.Exception -> L5e
            goto L63
        L5e:
            java.lang.String r9 = "Feature id doesn't exist"
            android.util.Log.e(r6, r9)
        L63:
            int r8 = r8 + 1
            int r7 = r7 + 1
            goto L4b
        L68:
            r13.intValue = r2
        L6a:
            int r4 = r4 + 1
            goto L1c
        L6d:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.collectdata.tsimpl.CollectDataManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
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

    public void registerListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        Log.d(TAG, "registerListener is execute subscribeHvacCallback: " + absBYDAutoCollectDataListener);
        if (absBYDAutoCollectDataListener == null || this.mBYDAutoDataPickListeners.contains(absBYDAutoCollectDataListener)) {
            return;
        }
        this.mBYDAutoDataPickListeners.add(absBYDAutoCollectDataListener);
    }

    public void unRegisterListener(AbsBYDAutoCollectDataListener absBYDAutoCollectDataListener) {
        Log.d(TAG, "unregisterListener is execute unsubscribeHvacCallback: " + absBYDAutoCollectDataListener);
        if (absBYDAutoCollectDataListener == null) {
            return;
        }
        this.mBYDAutoDataPickListeners.remove(absBYDAutoCollectDataListener);
    }
}
