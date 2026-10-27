package android.hardware.bydauto.speed.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
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

    private SpeedManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.speed.tsimpl.SpeedManagerImpl.1
            public void onCarSensorBinderChanged(boolean z) {
                Log.v(SpeedManagerImpl.TAG, "onCarSensorBinderChanged is " + z);
            }

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
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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

    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
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
            if (r1 >= r3) goto L1a
            r13.intValue = r2
            goto L88
        L1a:
            int r1 = r12.length
            r3 = 0
            r4 = r3
        L1d:
            if (r4 >= r1) goto L88
            r5 = r12[r4]
            float r5 = (float) r5
            r6 = 0
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 >= 0) goto L83
            int r5 = (int) r5
            java.lang.String r6 = "SpeedManagerImpl"
            r7 = 315(0x13b, float:4.41E-43)
            if (r5 == r7) goto L56
            r7 = 331(0x14b, float:4.64E-43)
            if (r5 == r7) goto L50
            r7 = 333(0x14d, float:4.67E-43)
            if (r5 == r7) goto L45
            r7 = 336(0x150, float:4.71E-43)
            if (r5 == r7) goto L40
            java.lang.String r5 = "case fail ! please check out"
            android.hardware.bydauto.Log.v(r6, r5)
            goto L63
        L40:
            int r5 = r11.getBrakeDeepness()
            goto L54
        L45:
            android.hardware.bydauto.TsManagerImpl r5 = r11.mTsManagerImpl
            com.ts.lib.caradapter.sensor.CarSensorAdapterManager r5 = r5.getCarSensorAdapterManager()
            float r5 = r5.getCurrentSpeed()
            goto L5a
        L50:
            int r5 = r11.getAccelerateDeepness()
        L54:
            float r5 = (float) r5
            goto L5a
        L56:
            float r5 = r11.getCurrentSpeed()
        L5a:
            r13.floatValue = r5
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            r0.put(r7, r5)
        L63:
            int r5 = r12.length
            r7 = r3
            r8 = r7
        L66:
            if (r7 >= r5) goto L85
            r9 = r12[r7]
            java.lang.Object r9 = r0.get(r9)     // Catch: java.lang.Exception -> L79
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.Exception -> L79
            int r9 = r9.intValue()     // Catch: java.lang.Exception -> L79
            int[] r10 = r13.intArrayValue     // Catch: java.lang.Exception -> L79
            r10[r8] = r9     // Catch: java.lang.Exception -> L79
            goto L7e
        L79:
            java.lang.String r9 = "Feature id doesn't exist"
            android.hardware.bydauto.Log.e(r6, r9)
        L7e:
            int r8 = r8 + 1
            int r7 = r7 + 1
            goto L66
        L83:
            r13.intValue = r2
        L85:
            int r4 = r4 + 1
            goto L1d
        L88:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.speed.tsimpl.SpeedManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getAccelerateDeepness() {
        String str;
        Log.i(TAG, "getAccelerateDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            str = "getAccelerateDeepness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getAccelerateDeepness();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getAccelerateValue() {
        Log.v(TAG, "getAccelerateValue is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getAccelerateValue();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.v(TAG, "getAccelerateValue mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public float getAverrageSpeed() {
        Log.i(TAG, "getAverrageSpeed is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getAverrageSpeed();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.v(TAG, "getAverrageSpeed mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public int getBrakeDeepness() {
        String str;
        Log.i(TAG, "getBrakeDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            str = "getBrakeDeepness mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getBrakeDeepness();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getCurrentSpeed() {
        String str;
        Log.i(TAG, "getCurrentSpeed is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            str = "getCurrentSpeed mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getCurrentSpeed();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public int getDataFlag(int i) {
        Log.v(TAG, "getDataFlag is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getDataFlag(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getDataFlag mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getDevicetype() {
        Log.i(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getDevicetype();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public float getFuelAccelerateDeepness() {
        Log.v(TAG, "getFuelAccelerateDeepness is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getFuelAccelerateDeepness();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.v(TAG, "getFuelAccelerateDeepness mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public double getIpbVehicleSpeedStatusR() {
        Log.i(TAG, "getIpbVehicleSpeedStatusR is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "IpbVehicleSpeedStatusR mForTestValue is 0 execute return");
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
            return this.mTsManagerImpl.getCarSensorAdapterManager().getIpbVehicleSpeedStatusR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0d;
    }

    public double getSpeedAliveCounterR() {
        Log.i(TAG, "getSpeedAliveCounterR is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            Log.v(TAG, "SpeedAliveCounterR mForTestValue is 0 execute return");
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
            return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedAliveCounterR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0d;
    }

    public float getSpeedFromGateway() {
        Log.v(TAG, "getSpeedFromGateway is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedFromGateway();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.v(TAG, "getSpeedFromGateway mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public float getSpeedValue() {
        String str;
        Log.i(TAG, "getSpeedValue is execute");
        enforcePermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_GET);
        if (this.mForTest == 0) {
            str = "getSpeedValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getSpeedValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
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
}
