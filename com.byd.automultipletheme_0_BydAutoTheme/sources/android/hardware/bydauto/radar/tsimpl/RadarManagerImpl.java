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
import java.util.Iterator;
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

    private RadarManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestRadarManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.radar.tsimpl.RadarManagerImpl.1
            public void onCarSensorBinderChanged(boolean z) {
            }

            public void onCarSensorStateChanged(int i2, int i3, SensorPacket sensorPacket) {
                if (RadarManagerImpl.this.mAbsBydAutoRadarListeners.isEmpty()) {
                    return;
                }
                try {
                    RadarManagerImpl.this.radarCallback(i2, i3, sensorPacket);
                } catch (Exception unused) {
                    Log.e(RadarManagerImpl.TAG, "radarCallback is error");
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

    /* JADX INFO: Access modifiers changed from: private */
    public void radarCallback(int i, int i2, SensorPacket sensorPacket) {
        if (sensorPacket == null) {
            Log.e(TAG, "radarCallback is null");
        }
        Log.i(TAG, "radarCallback propertyId:" + i);
        switch (i) {
            case RadarHalId.ID_RADAR_PROBE_STATE_FL /* 557861120 */:
                Iterator<AbsBYDAutoRadarListener> it = this.mAbsBydAutoRadarListeners.iterator();
                while (it.hasNext()) {
                    it.next().onRadarProbeStateChanged(5, sensorPacket.getRadarProbeStateFL());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_FML /* 557861121 */:
                Iterator<AbsBYDAutoRadarListener> it2 = this.mAbsBydAutoRadarListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onRadarProbeStateChanged(1, sensorPacket.getRadarProbeStateFML());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_FMR /* 557861122 */:
                Iterator<AbsBYDAutoRadarListener> it3 = this.mAbsBydAutoRadarListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRadarProbeStateChanged(2, sensorPacket.getRadarProbeStateFMR());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_FR /* 557861123 */:
                Iterator<AbsBYDAutoRadarListener> it4 = this.mAbsBydAutoRadarListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onRadarProbeStateChanged(6, sensorPacket.getRadarProbeStateFR());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_MR /* 557861124 */:
                Iterator<AbsBYDAutoRadarListener> it5 = this.mAbsBydAutoRadarListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onRadarProbeStateChanged(3, sensorPacket.getRadarProbeStateMR());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_RL /* 557861125 */:
                Iterator<AbsBYDAutoRadarListener> it6 = this.mAbsBydAutoRadarListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onRadarProbeStateChanged(7, sensorPacket.getRadarProbeStateRL());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_RML /* 557861126 */:
                Iterator<AbsBYDAutoRadarListener> it7 = this.mAbsBydAutoRadarListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onRadarProbeStateChanged(8, sensorPacket.getRadarProbeStateRML());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_RMR /* 557861127 */:
                Iterator<AbsBYDAutoRadarListener> it8 = this.mAbsBydAutoRadarListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onRadarProbeStateChanged(4, sensorPacket.getRadarProbeStateRMR());
                }
                break;
            case RadarHalId.ID_RADAR_PROBE_STATE_RR /* 557861128 */:
                Iterator<AbsBYDAutoRadarListener> it9 = this.mAbsBydAutoRadarListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onRadarProbeStateChanged(9, sensorPacket.getRadarProbeStateRR());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_FL /* 557861129 */:
                Iterator<AbsBYDAutoRadarListener> it10 = this.mAbsBydAutoRadarListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onRadarObstacleDistanceChanged(5, sensorPacket.getRadarObstacleDistanceFL());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_FML /* 557861130 */:
                Iterator<AbsBYDAutoRadarListener> it11 = this.mAbsBydAutoRadarListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onRadarObstacleDistanceChanged(1, sensorPacket.getRadarObstacleDistanceFML());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_FMR /* 557861131 */:
                Iterator<AbsBYDAutoRadarListener> it12 = this.mAbsBydAutoRadarListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onRadarObstacleDistanceChanged(2, sensorPacket.getRadarObstacleDistanceFMR());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_FR /* 557861132 */:
                Iterator<AbsBYDAutoRadarListener> it13 = this.mAbsBydAutoRadarListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onRadarObstacleDistanceChanged(6, sensorPacket.getRadarObstacleDistanceFR());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_RL /* 557861133 */:
                Iterator<AbsBYDAutoRadarListener> it14 = this.mAbsBydAutoRadarListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onRadarObstacleDistanceChanged(3, sensorPacket.getRadarObstacleDistanceRL());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_RML /* 557861134 */:
                Iterator<AbsBYDAutoRadarListener> it15 = this.mAbsBydAutoRadarListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onRadarObstacleDistanceChanged(7, sensorPacket.getRadarObstacleDistanceRML());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_RMR /* 557861135 */:
                Iterator<AbsBYDAutoRadarListener> it16 = this.mAbsBydAutoRadarListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onRadarObstacleDistanceChanged(8, sensorPacket.getRadarObstacleDistanceRMR());
                }
                break;
            case RadarHalId.ID_RADAR_OBSTACLE_DISTANCE_RR /* 557861136 */:
                Iterator<AbsBYDAutoRadarListener> it17 = this.mAbsBydAutoRadarListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onRadarObstacleDistanceChanged(4, sensorPacket.getRadarObstacleDistanceRR());
                }
                break;
            case RadarHalId.ID_REVERSE_RADAR_SWITCH_STATE_R /* 557861137 */:
                Log.w(TAG, "key state = " + sensorPacket.getReverseRadarSwitchStateR());
                Iterator<AbsBYDAutoRadarListener> it18 = this.mAbsBydAutoRadarListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onReverseRadarKeyStateChanged(sensorPacket.getReverseRadarSwitchStateR());
                }
                break;
            case RadarHalId.ID_NEW_REVERSE_RADAR_SWITCH_STATE_R /* 557861140 */:
                Log.w(TAG, "reverse radar state = " + sensorPacket.getNewReverseRadarSwitchStateR());
                Iterator<AbsBYDAutoRadarListener> it19 = this.mAbsBydAutoRadarListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onReverseRadarSwitchStateChanged(sensorPacket.getNewReverseRadarSwitchStateR());
                }
                break;
        }
    }

    public static RadarManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (RadarManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new RadarManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getRadarProbeState(int i) {
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
        switch (i) {
            case 1:
                int radarProbeStateFml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFml();
                if (radarProbeStateFml != 0) {
                    if (1 == radarProbeStateFml) {
                    }
                }
                break;
            case 2:
                int radarProbeStateFmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFmr();
                if (radarProbeStateFmr != 0) {
                    if (1 == radarProbeStateFmr) {
                    }
                }
                break;
            case 3:
                int radarProbeStateMr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateMr();
                if (radarProbeStateMr != 0) {
                    if (1 == radarProbeStateMr) {
                    }
                }
                break;
            case 4:
                int radarProbeStateRmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRmr();
                if (radarProbeStateRmr != 0) {
                    if (1 == radarProbeStateRmr) {
                    }
                }
                break;
            case 5:
                int radarProbeStateFl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFl();
                if (radarProbeStateFl != 0) {
                    if (1 == radarProbeStateFl) {
                    }
                }
                break;
            case 6:
                int radarProbeStateFr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateFr();
                if (radarProbeStateFr != 0) {
                    if (1 == radarProbeStateFr) {
                    }
                }
                break;
            case 7:
                int radarProbeStateRl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRl();
                if (radarProbeStateRl != 0) {
                    if (1 == radarProbeStateRl) {
                    }
                }
                break;
            case 8:
                int radarProbeStateRml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRml();
                if (radarProbeStateRml != 0) {
                    if (1 == radarProbeStateRml) {
                    }
                }
                break;
            case 9:
                int radarProbeStateRr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarProbeStateRr();
                if (radarProbeStateRr != 0) {
                    if (1 == radarProbeStateRr) {
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

    public int setReverseRadarSwitchState(int i) {
        Log.v(TAG, "setReverseRadarSwitchState is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.onReverseRadarSwitchStateChanged(i);
            Log.v(TAG, "setReverseRadarSwitchState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTsManagerImpl.getCarSensorAdapterManager().setReverseRadarSwitchState(i);
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
        int reverseRadarSwitchState = this.mTsManagerImpl.getCarSensorAdapterManager().getReverseRadarSwitchState();
        if (reverseRadarSwitchState == 0) {
            return 0;
        }
        if (reverseRadarSwitchState == 1) {
            return 1;
        }
        return reverseRadarSwitchState;
    }

    public int getRadarObstacleDistance(int i) {
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
        switch (i) {
            case 1:
                int radarObstacleDistanceFml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFml();
                if (radarObstacleDistanceFml > 155 || radarObstacleDistanceFml < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 2:
                int radarObstacleDistanceFmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFmr();
                if (radarObstacleDistanceFmr > 155 || radarObstacleDistanceFmr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 3:
                int radarObstacleDistanceRl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRl();
                if (radarObstacleDistanceRl > 155 || radarObstacleDistanceRl < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 4:
                int radarObstacleDistanceRr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRr();
                if (radarObstacleDistanceRr > 155 || radarObstacleDistanceRr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 5:
                int radarObstacleDistanceFl = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFl();
                if (radarObstacleDistanceFl > 155 || radarObstacleDistanceFl < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 6:
                int radarObstacleDistanceFr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceFr();
                if (radarObstacleDistanceFr > 155 || radarObstacleDistanceFr < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 7:
                int radarObstacleDistanceRml = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRml();
                if (radarObstacleDistanceRml > 155 || radarObstacleDistanceRml < 0) {
                    Log.v(TAG, "The value is not legal");
                }
                break;
            case 8:
                int radarObstacleDistanceRmr = this.mTsManagerImpl.getCarSensorAdapterManager().getRadarObstacleDistanceRmr();
                if (radarObstacleDistanceRmr > 155 || radarObstacleDistanceRmr < 0) {
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

    public void registerListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        registerListener(absBYDAutoRadarListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoRadarListener absBYDAutoRadarListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe radar listener " + absBYDAutoRadarListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoRadarListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoRadarListener(absBYDAutoRadarListener);
            return;
        }
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        if (!this.mAbsBydAutoRadarListeners.contains(absBYDAutoRadarListener)) {
            this.mAbsBydAutoRadarListeners.add(absBYDAutoRadarListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoRadarListener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoRadarDevice.PERMISSION_BYDAUTO_RADAR_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoRadarListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeHvacCallback: " + absBYDAutoRadarListener);
        this.mAbsBydAutoRadarListeners.remove(absBYDAutoRadarListener);
        unregisterConnectListener(absBYDAutoRadarListener);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoRadarListener> it = this.mAbsBydAutoRadarListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i == 2227) {
            setReverseRadarSwitchState(iIntValue);
            Log.v(TAG, "setReverseRadarSwitchState:" + iIntValue);
            return 1;
        }
        if (i == 2228) {
            setReverseRadarSwitchState(iIntValue);
            Log.v(TAG, "setReverseRadarSwitchState:" + iIntValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
