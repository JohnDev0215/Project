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
import java.util.Iterator;
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    private SpeedManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.speed.tsimpl.SpeedManagerImpl.1
            public void onCarSensorStateChanged(int i, int i2, SensorPacket sensorPacket) {
                if (SpeedManagerImpl.this.mAbsBYDAutoSpeedListeners.isEmpty()) {
                    return;
                }
                try {
                    SpeedManagerImpl.this.sensorCallback(i, i2, sensorPacket);
                } catch (Exception unused) {
                    Log.e(SpeedManagerImpl.TAG, "sensorCallback is error");
                }
            }

            public void onCarSensorBinderChanged(boolean z) {
                Log.v(SpeedManagerImpl.TAG, "onCarSensorBinderChanged is " + z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sensorCallback(int i, int i2, SensorPacket sensorPacket) {
        Log.i(TAG, "sensorCallback propertyId:" + i);
        switch (i) {
            case SpeedHalId.ID_ACCELERATE_DEEPNESSR /* 557845760 */:
                int accelerateDeepnessR = sensorPacket.getAccelerateDeepnessR();
                Iterator<AbsBYDAutoSpeedListener> it = this.mAbsBYDAutoSpeedListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAccelerateDeepnessChanged(accelerateDeepnessR);
                }
                break;
            case SpeedHalId.ID_BRAKE_DEEPNESSR /* 557845761 */:
                int brakeDeepnessR = sensorPacket.getBrakeDeepnessR();
                Iterator<AbsBYDAutoSpeedListener> it2 = this.mAbsBYDAutoSpeedListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onBrakeDeepnessChanged(brakeDeepnessR);
                }
                break;
            case SpeedHalId.ID_CURRENT_SPEEDR /* 557867014 */:
                float currentSpeedR = sensorPacket.getCurrentSpeedR();
                Iterator<AbsBYDAutoSpeedListener> it3 = this.mAbsBYDAutoSpeedListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onSpeedChanged(currentSpeedR);
                }
                break;
            case 559957505:
                Iterator<AbsBYDAutoSpeedListener> it4 = this.mAbsBYDAutoSpeedListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onSpeedValueChanged(sensorPacket.getSpeedValue());
                }
                break;
        }
    }

    public static SpeedManagerImpl getInstance(Context context) {
        if (mSpeedManagerImpl == null) {
            synchronized (SpeedManagerImpl.class) {
                if (mSpeedManagerImpl == null) {
                    mSpeedManagerImpl = new SpeedManagerImpl(context);
                }
            }
        }
        return mSpeedManagerImpl;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
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
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
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

    public void registerListener(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe speed listener " + absBYDAutoSpeedListener);
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        if (this.mForTest == 0) {
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "subscribeSpeedCallback: " + absBYDAutoSpeedListener);
        if (absBYDAutoSpeedListener == null) {
            return;
        }
        if (!this.mAbsBYDAutoSpeedListeners.contains(absBYDAutoSpeedListener)) {
            this.mAbsBYDAutoSpeedListeners.add(absBYDAutoSpeedListener);
        }
        registerConnectListener(this.mContext, absBYDAutoSpeedListener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoSpeedListener absBYDAutoSpeedListener) {
        Log.i(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        if (this.mForTest == 0) {
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSpeedCallback: " + absBYDAutoSpeedListener);
        if (absBYDAutoSpeedListener == null) {
            return;
        }
        this.mAbsBYDAutoSpeedListeners.remove(absBYDAutoSpeedListener);
        unregisterConnectListener(absBYDAutoSpeedListener);
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

    public int getDataFlag(int i) {
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
        return this.mTsManagerImpl.getCarSensorAdapterManager().getDataFlag(i);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (float f : iArr) {
                if (0.0f < f) {
                    int i = (int) f;
                    if (i == 315) {
                        bYDAutoEventValue.floatValue = getCurrentSpeed();
                        sparseArray.put(315, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 331) {
                        bYDAutoEventValue.floatValue = getAccelerateDeepness();
                        sparseArray.put(331, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 333) {
                        bYDAutoEventValue.floatValue = this.mTsManagerImpl.getCarSensorAdapterManager().getCurrentSpeed();
                        sparseArray.put(333, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 336) {
                        bYDAutoEventValue.floatValue = getBrakeDeepness();
                        sparseArray.put(336, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else {
                        Log.v(TAG, "case fail ! please check out");
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
}
