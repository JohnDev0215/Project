package android.hardware.bydauto.radar.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.provider.Settings;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.sensor.CarSensorAdapterManager;
import com.ts.lib.caradapter.sensor.OnCarSensorChangedListener;
import com.ts.lib.caradapter.sensor.SensorPacket;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RadarManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_RadarManagerImpl";
    private static final String TAG = "RadarManagerImpl";
    private static volatile RadarManagerImpl sInstance;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TestRadarManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private CarSensorAdapterManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoRadarListener> mAbsBydAutoRadarListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private RadarManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        ContentResolver contentResolver = con.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestRadarManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.radar.tsimpl.RadarManagerImpl.1
            public void onCarSensorStateChanged(int id, int areaId, SensorPacket packet) {
                if (!RadarManagerImpl.this.mAbsBydAutoRadarListeners.isEmpty()) {
                    try {
                        RadarManagerImpl.this.radarCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(RadarManagerImpl.TAG, "radarCallback is error");
                    }
                }
            }

            public void onCarSensorBinderChanged(boolean binderState) {
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void radarCallback(int id, int areaId, SensorPacket sensorPacket) {
        if (sensorPacket == null) {
            Log.e(TAG, "radarCallback is null");
        }
        Log.i(TAG, "radarCallback propertyId:" + id);
        switch (id) {
            case 557861120:
                for (AbsBYDAutoRadarListener listener : this.mAbsBydAutoRadarListeners) {
                    listener.onRadarProbeStateChanged(5, sensorPacket.getRadarProbeStateFL());
                }
                break;
            case 557861121:
                for (AbsBYDAutoRadarListener listener2 : this.mAbsBydAutoRadarListeners) {
                    listener2.onRadarProbeStateChanged(1, sensorPacket.getRadarProbeStateFML());
                }
                break;
            case 557861122:
                for (AbsBYDAutoRadarListener listener3 : this.mAbsBydAutoRadarListeners) {
                    listener3.onRadarProbeStateChanged(2, sensorPacket.getRadarProbeStateFMR());
                }
                break;
            case 557861123:
                for (AbsBYDAutoRadarListener listener4 : this.mAbsBydAutoRadarListeners) {
                    listener4.onRadarProbeStateChanged(6, sensorPacket.getRadarProbeStateFR());
                }
                break;
            case 557861124:
                for (AbsBYDAutoRadarListener listener5 : this.mAbsBydAutoRadarListeners) {
                    listener5.onRadarProbeStateChanged(3, sensorPacket.getRadarProbeStateMR());
                }
                break;
            case 557861125:
                for (AbsBYDAutoRadarListener listener6 : this.mAbsBydAutoRadarListeners) {
                    listener6.onRadarProbeStateChanged(7, sensorPacket.getRadarProbeStateRL());
                }
                break;
            case 557861126:
                for (AbsBYDAutoRadarListener listener7 : this.mAbsBydAutoRadarListeners) {
                    listener7.onRadarProbeStateChanged(8, sensorPacket.getRadarProbeStateRML());
                }
                break;
            case 557861127:
                for (AbsBYDAutoRadarListener listener8 : this.mAbsBydAutoRadarListeners) {
                    listener8.onRadarProbeStateChanged(4, sensorPacket.getRadarProbeStateRMR());
                }
                break;
            case 557861128:
                for (AbsBYDAutoRadarListener listener9 : this.mAbsBydAutoRadarListeners) {
                    listener9.onRadarProbeStateChanged(9, sensorPacket.getRadarProbeStateRR());
                }
                break;
            case 557861129:
                for (AbsBYDAutoRadarListener listener10 : this.mAbsBydAutoRadarListeners) {
                    listener10.onRadarObstacleDistanceChanged(5, sensorPacket.getRadarObstacleDistanceFL());
                }
                break;
            case 557861130:
                for (AbsBYDAutoRadarListener listener11 : this.mAbsBydAutoRadarListeners) {
                    listener11.onRadarObstacleDistanceChanged(1, sensorPacket.getRadarObstacleDistanceFML());
                }
                break;
            case 557861131:
                for (AbsBYDAutoRadarListener listener12 : this.mAbsBydAutoRadarListeners) {
                    listener12.onRadarObstacleDistanceChanged(2, sensorPacket.getRadarObstacleDistanceFMR());
                }
                break;
            case 557861132:
                for (AbsBYDAutoRadarListener listener13 : this.mAbsBydAutoRadarListeners) {
                    listener13.onRadarObstacleDistanceChanged(6, sensorPacket.getRadarObstacleDistanceFR());
                }
                break;
            case 557861133:
                for (AbsBYDAutoRadarListener listener14 : this.mAbsBydAutoRadarListeners) {
                    listener14.onRadarObstacleDistanceChanged(3, sensorPacket.getRadarObstacleDistanceRL());
                }
                break;
            case 557861134:
                for (AbsBYDAutoRadarListener listener15 : this.mAbsBydAutoRadarListeners) {
                    listener15.onRadarObstacleDistanceChanged(7, sensorPacket.getRadarObstacleDistanceRML());
                }
                break;
            case 557861135:
                for (AbsBYDAutoRadarListener listener16 : this.mAbsBydAutoRadarListeners) {
                    listener16.onRadarObstacleDistanceChanged(8, sensorPacket.getRadarObstacleDistanceRMR());
                }
                break;
            case 557861136:
                for (AbsBYDAutoRadarListener listener17 : this.mAbsBydAutoRadarListeners) {
                    listener17.onRadarObstacleDistanceChanged(4, sensorPacket.getRadarObstacleDistanceRR());
                }
                break;
            case 557861137:
                Log.w(TAG, "key state = " + sensorPacket.getReverseRadarSwitchStateR());
                for (AbsBYDAutoRadarListener listener18 : this.mAbsBydAutoRadarListeners) {
                    listener18.onReverseRadarKeyStateChanged(sensorPacket.getReverseRadarSwitchStateR());
                }
                break;
            case 557861140:
                Log.w(TAG, "reverse radar state = " + sensorPacket.getNewReverseRadarSwitchStateR());
                for (AbsBYDAutoRadarListener listener19 : this.mAbsBydAutoRadarListeners) {
                    listener19.onReverseRadarSwitchStateChanged(sensorPacket.getNewReverseRadarSwitchStateR());
                }
                break;
        }
    }

    public static RadarManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (RadarManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new RadarManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public int getRadarProbeState(int area) {
        Log.v(TAG, "getRadarProbeState is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRadarProbeState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        switch (area) {
            case 1:
                int stateFml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFml();
                if (0 != stateFml) {
                    if (1 == stateFml) {
                    }
                }
                break;
            case 2:
                int stateFmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFmr();
                if (0 != stateFmr) {
                    if (1 == stateFmr) {
                    }
                }
                break;
            case 3:
                int stateMr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateMr();
                if (0 != stateMr) {
                    if (1 == stateMr) {
                    }
                }
                break;
            case 4:
                int stateRmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRmr();
                if (0 != stateRmr) {
                    if (1 == stateRmr) {
                    }
                }
                break;
            case 5:
                int stateFl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFl();
                if (0 != stateFl) {
                    if (1 == stateFl) {
                    }
                }
                break;
            case 6:
                int stateFr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFr();
                if (0 != stateFr) {
                    if (1 == stateFr) {
                    }
                }
                break;
            case 7:
                int stateRl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRl();
                if (0 != stateRl) {
                    if (1 == stateRl) {
                    }
                }
                break;
            case 8:
                int stateRml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRml();
                if (0 != stateRml) {
                    if (1 == stateRml) {
                    }
                }
                break;
            case 9:
                int stateRr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRr();
                if (0 != stateRr) {
                    if (1 == stateRr) {
                    }
                }
                break;
        }
        return 0;
    }

    public int[] getAllRadarProbeStates() {
        Log.v(TAG, "getAllRadarProbeStates is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAllRadarProbeStates mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAllRadarProbeStates();
    }

    public int setReverseRadarSwitchState(int state) {
        Log.v(TAG, "setReverseRadarSwitchState is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.onReverseRadarSwitchStateChanged(state);
            Log.v(TAG, "setReverseRadarSwitchState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTsManagerImpl.getCarSensorAdapterManager().setReverseRadarSwitchState(state);
        return 0;
    }

    public int getReverseRadarSwitchState() {
        Log.v(TAG, "getReverseRadarSwitchState is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getReverseRadarSwitchState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getNewReverseRadarSwitchState();
    }

    public int getReverseRadarKeyState() {
        Log.v(TAG, "getReverseRadarKeyState is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getReverseRadarKeyState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int reverseRadar = this.mTsManagerImpl.getCarSensorAdapterManager().getReverseRadarSwitchState();
        if (reverseRadar == 0) {
            return 0;
        }
        if (reverseRadar == 1) {
            return 1;
        }
        return reverseRadar;
    }

    public int getRadarObstacleDistance(int area) {
        Log.v(TAG, "getRadarObstacleDistance is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRadarObstacleDistance mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        switch (area) {
            case 1:
                int fml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFml();
                if (fml > 155 || fml < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 2:
                int fmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFmr();
                if (fmr > 155 || fmr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 3:
                int rl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRl();
                if (rl > 155 || rl < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 4:
                int rr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRr();
                if (rr > 155 || rr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 5:
                int fl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFl();
                if (fl > 155 || fl < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 6:
                int fr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFr();
                if (fr > 155 || fr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 7:
                int rml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRml();
                if (rml > 155 || rml < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 8:
                int rmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRmr();
                if (rmr > 155 || rmr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
        }
        return 0;
    }

    public int[] getAllRadarDistance() {
        Log.v(TAG, "getAllRadarDistance is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAllRadarDistance mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getAllRadarDistance();
    }

    public void registerListener(AbsBYDAutoRadarListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoRadarListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe radar listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoRadarListener(listener);
            return;
        }
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        if (!this.mAbsBydAutoRadarListeners.contains(listener)) {
            this.mAbsBydAutoRadarListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoRadarListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoRadarListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeHvacCallback: " + listener);
        this.mAbsBydAutoRadarListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoRadarListener listener : this.mAbsBydAutoRadarListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 2227) {
            setReverseRadarSwitchState(intValue);
            Log.v(TAG, "setReverseRadarSwitchState:" + intValue);
            return 1;
        }
        if (eventType == 2228) {
            setReverseRadarSwitchState(intValue);
            Log.v(TAG, "setReverseRadarSwitchState:" + intValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
