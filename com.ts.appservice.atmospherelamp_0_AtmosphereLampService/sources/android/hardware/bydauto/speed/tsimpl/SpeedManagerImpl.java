package android.hardware.bydauto.speed.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.util.SparseArray;
import com.ts.lib.caradapter.sensor.OnCarSensorChangedListener;
import com.ts.lib.caradapter.sensor.SensorPacket;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SpeedManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 0;
    protected static final String TAG = "SpeedManagerImpl";
    private static volatile SpeedManagerImpl mSpeedManagerImpl;
    private Context mContext;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 1;
    private final CopyOnWriteArrayList<AbsBYDAutoSpeedListener> mAbsBYDAutoSpeedListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    private SpeedManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.speed.tsimpl.SpeedManagerImpl.1
            public void onCarSensorStateChanged(int id, int areaId, SensorPacket packet) {
                if (!SpeedManagerImpl.this.mAbsBYDAutoSpeedListeners.isEmpty()) {
                    try {
                        SpeedManagerImpl.this.sensorCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(SpeedManagerImpl.TAG, "sensorCallback is error");
                    }
                }
            }

            public void onCarSensorBinderChanged(boolean binderState) {
                Log.v(SpeedManagerImpl.TAG, "onCarSensorBinderChanged is " + binderState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sensorCallback(int id, int areaId, SensorPacket packet) {
        Log.i(TAG, "sensorCallback propertyId:" + id);
        switch (id) {
            case 557845760:
                int acceleRate = packet.getAccelerateDeepnessR();
                for (AbsBYDAutoSpeedListener listener : this.mAbsBYDAutoSpeedListeners) {
                    listener.onAccelerateDeepnessChanged(acceleRate);
                }
                break;
            case 557845761:
                int brakeDeepness = packet.getBrakeDeepnessR();
                for (AbsBYDAutoSpeedListener listener2 : this.mAbsBYDAutoSpeedListeners) {
                    listener2.onBrakeDeepnessChanged(brakeDeepness);
                }
                break;
            case 557867014:
                float current = packet.getCurrentSpeedR();
                for (AbsBYDAutoSpeedListener listener3 : this.mAbsBYDAutoSpeedListeners) {
                    listener3.onSpeedChanged(current);
                }
                caseFeatureIds(315, Float.valueOf(current));
                break;
            case 559942916:
                for (AbsBYDAutoSpeedListener listener4 : this.mAbsBYDAutoSpeedListeners) {
                    listener4.onAccelerateDeepnessSignificantBitChanged(packet.getAccelerateDeepnessSignificantBitR());
                }
                break;
            case 559957505:
                for (AbsBYDAutoSpeedListener listener5 : this.mAbsBYDAutoSpeedListeners) {
                    listener5.onSpeedValueChanged(packet.getSpeedValue());
                }
                caseFeatureIds(333, Float.valueOf(packet.getSpeedValue()));
                break;
        }
    }

    public static SpeedManagerImpl getInstance(Context con) {
        if (mSpeedManagerImpl == null) {
            synchronized (SpeedManagerImpl.class) {
                if (mSpeedManagerImpl == null) {
                    mSpeedManagerImpl = new SpeedManagerImpl(con);
                }
            }
        }
        return mSpeedManagerImpl;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public int getAccelerateDeepness() {
        Log.i(TAG, "getAccelerateDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAccelerateDeepness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAccelerateDeepness();
    }

    public int getBrakeDeepness() {
        Log.i(TAG, "getBrakeDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getBrakeDeepness mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getBrakeDeepness();
    }

    public float getCurrentSpeed() {
        Log.i(TAG, "getCurrentSpeed is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getCurrentSpeed mForTestValue is 0 execute return");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getCurrentSpeed();
    }

    public float getSpeedValue() {
        Log.i(TAG, "getSpeedValue is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSpeedValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedValue();
    }

    public double getIpbVehicleSpeedStatusR() {
        Log.i(TAG, "getIpbVehicleSpeedStatusR is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "IpbVehicleSpeedStatusR mForTestValue is 0 execute return");
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0d;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getIpbVehicleSpeedStatusR();
    }

    public double getSpeedAliveCounterR() {
        Log.i(TAG, "getSpeedAliveCounterR is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "SpeedAliveCounterR mForTestValue is 0 execute return");
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0d;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedAliveCounterR();
    }

    public void registerListener(AbsBYDAutoSpeedListener listener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe speed listener " + listener);
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        if (this.mForTest == 0) {
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "subscribeSpeedCallback: " + listener);
        if (listener == null) {
            return;
        }
        if (!this.mAbsBYDAutoSpeedListeners.contains(listener)) {
            this.mAbsBYDAutoSpeedListeners.add(listener);
        }
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoSpeedListener listener) {
        Log.i(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        if (this.mForTest == 0) {
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSpeedCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoSpeedListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getDevicetype() {
        Log.i(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getDevicetype();
    }

    public float getAverrageSpeed() {
        Log.i(TAG, "getAverrageSpeed is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAverrageSpeed mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAverrageSpeed();
    }

    public float getAccelerateValue() {
        Log.v(TAG, "getAccelerateValue is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getAccelerateValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAccelerateValue();
    }

    public float getSpeedFromGateway() {
        Log.v(TAG, "getSpeedFromGateway is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getSpeedFromGateway mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedFromGateway();
    }

    public int getDataFlag(int item) {
        Log.v(TAG, "getDataFlag is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getDataFlag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getDataFlag(item);
    }

    public float getFuelAccelerateDeepness() {
        Log.v(TAG, "getFuelAccelerateDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "getFuelAccelerateDeepness mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getFuelAccelerateDeepness();
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (float eventType : eventTypes) {
                if (0.0f < eventType) {
                    int caseId = (int) eventType;
                    if (caseId == 315) {
                        bydAutoEventValue.floatValue = getCurrentSpeed();
                        sparseArray.put(315, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (caseId == 331) {
                        bydAutoEventValue.floatValue = getAccelerateDeepness();
                        sparseArray.put(331, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (caseId == 333) {
                        bydAutoEventValue.floatValue = getSpeedValue();
                        sparseArray.put(333, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (caseId != 336) {
                        Log.v(TAG, "case fail ! please check out");
                    } else {
                        bydAutoEventValue.floatValue = getBrakeDeepness();
                        sparseArray.put(336, Float.valueOf(bydAutoEventValue.floatValue));
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

    public int getAccelerateDeepnessSignificantBit() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAccelerateDeepnessSignificantBit mForTestValue is 0 ");
            return -1;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAccelerateDeepnessSignificantBit();
    }
}
