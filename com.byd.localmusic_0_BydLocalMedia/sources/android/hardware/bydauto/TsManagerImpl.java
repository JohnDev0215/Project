package android.hardware.bydauto;

import android.car.Car;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.assist.CarAssistAdapterManager;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import com.ts.lib.caradapter.body.CarBodyManager;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.ChargingAdapterManager;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import com.ts.lib.caradapter.body.OnChargingChangedListener;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import com.ts.lib.caradapter.cloud.CarCloudAdapterManager;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.OnCloudChangedListener;
import com.ts.lib.caradapter.general.CarGeneralAdapterManager;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import com.ts.lib.caradapter.hvac.HvacAdapterManager;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;
import com.ts.lib.caradapter.interfaces.OnCarAssistChangedListener;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import com.ts.lib.caradapter.selfstudy.OnSelfStudyChangedListener;
import com.ts.lib.caradapter.selfstudy.StudyAdapterManager;
import com.ts.lib.caradapter.sensor.CarSensorAdapterManager;
import com.ts.lib.caradapter.sensor.OnCarSensorChangedListener;
import com.ts.lib.caradapter.sensor.SensorPacket;
import com.ts.lib.caradapter.setting.OnSettingChangedListener;
import com.ts.lib.caradapter.setting.SettingAdapterManager;
import com.ts.lib.caradapter.setting.SettingPacket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TsManagerImpl {
    private static final int MSG_DELAY_CALLBACK_SEC = 100;
    private static final int MSG_DELAY_MILL_SEC = 500;
    private static final int MSG_IS_OPTIMIZATION_API_STATE = 405;
    private static final int MSG_REGISTER_ASSIST_CALLBACK = 1002;
    private static final int MSG_REGISTER_BODY_CALLBACK = 1003;
    private static final int MSG_REGISTER_CABIN_CALLBACK = 1001;
    private static final int MSG_REGISTER_CHARGING_CALLBACK = 1008;
    private static final int MSG_REGISTER_COLLECT_CALLBACK = 1007;
    private static final int MSG_REGISTER_GENERAL_CALLBACK = 1004;
    private static final int MSG_REGISTER_HVAC_CALLBACK = 1000;
    private static final int MSG_REGISTER_SENSOR_CALLBACK = 1005;
    private static final int MSG_REGISTER_SETTING_CALLBACK = 1006;
    private static final int MSG_SERVICE_BOUND_STATE = 500;
    public static final int S_CONSTANT_EIGHT = 8;
    public static final int S_CONSTANT_ELEVEN = 11;
    public static final int S_CONSTANT_FIVE = 5;
    public static final int S_CONSTANT_FOUR = 4;
    public static final int S_CONSTANT_NEGATIVE = -1;
    public static final int S_CONSTANT_NINE = 9;
    public static final int S_CONSTANT_ONE = 1;
    public static final int S_CONSTANT_SEVEN = 7;
    public static final int S_CONSTANT_SIX = 6;
    public static final int S_CONSTANT_TEN = 10;
    public static final int S_CONSTANT_THREE = 3;
    public static final int S_CONSTANT_TWELVE = 12;
    public static final int S_CONSTANT_TWO = 2;
    public static final int S_CONSTANT_ZERO = 0;
    private static TsManagerImpl mInstance;
    private CarAdapterManager mCarAdapterManager;
    private CarAssistAdapterManager mCarAssistAdapterManager;
    private CarBodyManager mCarBodyManager;
    private CarCabinAdapterManager mCarCabinAdapterManager;
    private CarCloudAdapterManager mCarCloudAdapterManager;
    private CarSensorAdapterManager mCarSensorAdapterManager;
    private ChargingAdapterManager mChargingManager;
    private Context mContext;
    private CarGeneralAdapterManager mGeneralAdapterManager;
    private HvacAdapterManager mHvacAdapterManager;
    private SettingAdapterManager mSettingAdapterManager;
    private StudyAdapterManager mStudyAdapterManager;
    private static final String TAG = TsManagerImpl.class.getSimpleName();
    public static Boolean bindState = false;
    public static boolean isReturnCallback = false;
    private static final List<TsConnectListener> mConnectListener = new ArrayList();
    public static boolean isConnect = false;
    private static boolean mIsConnectServiceAndAdapter = false;
    private final List<OnCarSensorChangedListener> mOnSensorChangedListeners = new ArrayList();
    private final List<OnHvacChangedListener> mOnHvacChangedListeners = new ArrayList();
    private final List<OnCabinChangedListener> mOnCabinChangedListeners = new ArrayList();
    private final List<OnBodyChangedListener> mOnBodyChangedListeners = new ArrayList();
    private final List<OnAssistChangedListener> mOnAssistChangedListeners = new ArrayList();
    private final List<OnCloudChangedListener> mOnCloudChangedListeners = new ArrayList();
    private final List<OnSettingChangedListener> mOnSettingChangedListeners = new ArrayList();
    private final List<OnCarGeneralChangedListener> onCarGeneralChangedListeners = new ArrayList();
    private final List<OnSelfStudyChangedListener> mOnSelfStudyChangedListeners = new ArrayList();
    private final List<OnChargingChangedListener> mOnChargingChangedListeners = new ArrayList();
    private boolean isDied = false;
    private final Map<String, Integer> mIsRegisterCallback = new HashMap();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: android.hardware.bydauto.TsManagerImpl.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 405) {
                android.util.Log.e(TsManagerImpl.TAG, " isOptimizationApi start! isConnect:" + TsManagerImpl.isConnect);
                TsManagerImpl.isReturnCallback = true;
                TsManagerImpl.this.isOptimizationApi();
                android.util.Log.e(TsManagerImpl.TAG, " isOptimizationApi end! isConnect:" + TsManagerImpl.isConnect);
            }
            if (i == 500) {
                TsManagerImpl.this.isServiceBound();
                return;
            }
            switch (i) {
                case 1000:
                    TsManagerImpl.this.registerHvacCallback();
                    break;
                case 1001:
                    TsManagerImpl.this.registerCabinCallback();
                    break;
                case 1002:
                    TsManagerImpl.this.registerAssistCallback();
                    break;
                case 1003:
                    TsManagerImpl.this.registerCarBodyCallback();
                    break;
                case 1004:
                    TsManagerImpl.this.registerGeneralCallback();
                    break;
                case 1005:
                    TsManagerImpl.this.registerSensorCallback();
                    break;
                case 1006:
                    TsManagerImpl.this.registerSettingCallback();
                    break;
                case 1007:
                    TsManagerImpl.this.registerCollectCallback();
                    break;
                case 1008:
                    TsManagerImpl.this.registerChargingCallback();
                    break;
                default:
                    android.util.Log.d(TsManagerImpl.TAG, "no have message " + message.what);
                    break;
            }
        }
    };
    private OnChargingChangedListener mChargingChangedListener = new OnChargingChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.3
        public void onChargingChanged(int i, int i2, CarBodyPacket carBodyPacket) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnChargingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnChargingChangedListener) it.next()).onChargingChanged(i, i2, carBodyPacket);
                }
            }
        }

        public void onBinderStateChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnChargingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnChargingChangedListener) it.next()).onBinderStateChanged(z);
                }
            }
        }
    };
    private OnHvacChangedListener mHvacChangedListener = new OnHvacChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.4
        public void onHvacChanged(int i, int i2, HvacPacket hvacPacket) {
            synchronized (TsManagerImpl.this.mOnHvacChangedListeners) {
                for (OnHvacChangedListener onHvacChangedListener : TsManagerImpl.this.mOnHvacChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "OnHvacChangedListener packet is " + hvacPacket.toString());
                    onHvacChangedListener.onHvacChanged(i, i2, hvacPacket);
                }
            }
        }
    };
    private OnCabinChangedListener mCabinChangedListener = new OnCabinChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.5
        public void onCabinChanged(int i, int i2, CabinPacket cabinPacket) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCabinChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCabinChangedListener) it.next()).onCabinChanged(i, i2, cabinPacket);
                }
            }
        }

        public void onCabinBinderChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCabinChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCabinChangedListener) it.next()).onCabinBinderChanged(z);
                }
            }
        }
    };
    private OnCarAssistChangedListener mAssistChangedListener = new OnCarAssistChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.6
        public void onCarAssistChanged(int i, int i2, CarAssistPacket carAssistPacket) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnAssistChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAssistChangedListener) it.next()).onAssistChanged(i, i2, carAssistPacket);
                }
            }
        }

        public void onBinderStateChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnAssistChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAssistChangedListener) it.next()).onBinderStateChanged(z);
                }
            }
        }
    };
    private OnBodyChangedListener mBodyChangedListener = new OnBodyChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.7
        public void onBodyChanged(int i, int i2, CarBodyPacket carBodyPacket) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnBodyChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnBodyChangedListener) it.next()).onBodyChanged(i, i2, carBodyPacket);
                }
            }
        }

        public void onBinderStateChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnBodyChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnBodyChangedListener) it.next()).onBinderStateChanged(z);
                }
            }
        }
    };
    private OnCarGeneralChangedListener mGeneralListener = new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.8
        public void onCarGeneralChanged(int i, int i2, GeneralPacket generalPacket) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarGeneralChanged(i, i2, generalPacket);
                }
            }
        }

        public void onCarGeneralBinderChanged(boolean z) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarGeneralBinderChanged(z);
                }
            }
        }

        public void onCarClusterBrightnessChanged(int i) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarClusterBrightnessChanged(i);
                }
            }
        }
    };
    private OnCarSensorChangedListener mSensorChangedListener = new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.9
        public void onCarSensorStateChanged(int i, int i2, SensorPacket sensorPacket) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSensorChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarSensorChangedListener) it.next()).onCarSensorStateChanged(i, i2, sensorPacket);
                }
            }
        }

        public void onCarSensorBinderChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSensorChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarSensorChangedListener) it.next()).onCarSensorBinderChanged(z);
                }
            }
        }
    };
    private OnCloudChangedListener mCollectChangedListener = new OnCloudChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.10
        public void onBinderStateChanged(boolean z) {
        }

        public void onCloudChanged(int i, int i2, CarCloudPacket carCloudPacket) {
        }

        public void onRemoteChanged(int i, int i2, CarRemotePacket carRemotePacket) {
        }

        public void onCollectDataChanged(int i, int i2, CarCollectDataPacket carCollectDataPacket) {
            synchronized (TsManagerImpl.this.mOnCloudChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCloudChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCloudChangedListener) it.next()).onCollectDataChanged(i, i2, carCollectDataPacket);
                }
            }
        }
    };
    private OnSettingChangedListener mSettingChangedListener = new OnSettingChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.11
        public void onSettingChanged(int i, int i2, SettingPacket settingPacket) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSettingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnSettingChangedListener) it.next()).onSettingChanged(i, i2, settingPacket);
                }
            }
        }

        public void onBinderSettingChanged(boolean z) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSettingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnSettingChangedListener) it.next()).onBinderSettingChanged(z);
                }
            }
        }
    };

    private TsManagerImpl(Context context) {
        this.mContext = context;
        android.util.Log.w(TAG, "current jar can channel version = v0.0.44 " + this.mContext.getPackageName());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.TsManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                android.util.Log.d(TsManagerImpl.TAG, "bind service time consuming=" + (jCurrentTimeMillis2 - jCurrentTimeMillis));
                TsManagerImpl.isConnect = z;
                TsManagerImpl.this.mHandler.sendEmptyMessage(405);
                TsManagerImpl.this.mHandler.sendEmptyMessage(500);
                TsManagerImpl.this.initAdapterManager(z);
            }
        });
        this.mCarAdapterManager.connect();
    }

    public static TsManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (TsManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new TsManagerImpl(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapterManager(boolean z) {
        android.util.Log.d(TAG, "bind state is " + z);
        android.util.Log.w(TAG, "bind state start ");
        if (z) {
            if (this.mHvacAdapterManager == null) {
                this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.HVAC_SERVICE);
            }
            if (this.mCarCabinAdapterManager == null) {
                this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.CABIN_SERVICE);
            }
            if (this.mCarAssistAdapterManager == null) {
                this.mCarAssistAdapterManager = (CarAssistAdapterManager) this.mCarAdapterManager.getCarAdapterManager("assist");
            }
            if (this.mCarBodyManager == null) {
                this.mCarBodyManager = (CarBodyManager) this.mCarAdapterManager.getCarAdapterManager("body");
            }
            if (this.mChargingManager == null) {
                this.mChargingManager = (ChargingAdapterManager) this.mCarAdapterManager.getCarAdapterManager("charging");
            }
            if (this.mGeneralAdapterManager == null) {
                this.mGeneralAdapterManager = (CarGeneralAdapterManager) this.mCarAdapterManager.getCarAdapterManager("general");
            }
            if (this.mCarSensorAdapterManager == null) {
                this.mCarSensorAdapterManager = (CarSensorAdapterManager) this.mCarAdapterManager.getCarAdapterManager("sensor");
            }
            if (this.mSettingAdapterManager == null) {
                this.mSettingAdapterManager = (SettingAdapterManager) this.mCarAdapterManager.getCarAdapterManager("setting");
            }
            if (this.mStudyAdapterManager == null) {
                this.mStudyAdapterManager = (StudyAdapterManager) this.mCarAdapterManager.getCarAdapterManager("selfstudy");
            }
            this.mCarCloudAdapterManager = (CarCloudAdapterManager) this.mCarAdapterManager.getCarAdapterManager("cloud");
            if (this.isDied) {
                reRegisterBinderCallback();
                this.isDied = false;
            }
            android.util.Log.w(TAG, "bind state end ");
            return;
        }
        this.mHvacAdapterManager = null;
        this.mCarCabinAdapterManager = null;
        this.mCarBodyManager = null;
        this.mGeneralAdapterManager = null;
        this.mCarSensorAdapterManager = null;
        this.mSettingAdapterManager = null;
        this.mStudyAdapterManager = null;
        this.mCarCloudAdapterManager = null;
        this.mChargingManager = null;
        mIsConnectServiceAndAdapter = false;
        this.isDied = true;
    }

    public void setOnChargingChangedListener(OnChargingChangedListener onChargingChangedListener) {
        synchronized (this.mOnChargingChangedListeners) {
            if (!this.mOnChargingChangedListeners.contains(onChargingChangedListener) && onChargingChangedListener != null) {
                this.mOnChargingChangedListeners.add(onChargingChangedListener);
                android.util.Log.i(TAG, "setOnChargingChangedListener ok! listener size: " + this.mOnChargingChangedListeners.size());
            }
        }
    }

    public void setOnHvacChangedListener(OnHvacChangedListener onHvacChangedListener) {
        synchronized (this.mOnHvacChangedListeners) {
            if (!this.mOnHvacChangedListeners.contains(onHvacChangedListener) && onHvacChangedListener != null) {
                this.mOnHvacChangedListeners.add(onHvacChangedListener);
                android.util.Log.i(TAG, "setOnHvacChangedListener ok! listener size: " + this.mOnHvacChangedListeners.size());
            }
        }
    }

    public void setOnGeneralChangedListener(OnCarGeneralChangedListener onCarGeneralChangedListener) {
        synchronized (this.onCarGeneralChangedListeners) {
            if (!this.onCarGeneralChangedListeners.contains(onCarGeneralChangedListener) && onCarGeneralChangedListener != null) {
                this.onCarGeneralChangedListeners.add(onCarGeneralChangedListener);
                android.util.Log.i(TAG, "setOnSafetyBeltChangedListener ok! listener size: " + this.onCarGeneralChangedListeners.size());
            }
        }
    }

    public void setOnCabinChangedListener(OnCabinChangedListener onCabinChangedListener) {
        synchronized (this.mOnCabinChangedListeners) {
            if (!this.mOnCabinChangedListeners.contains(onCabinChangedListener) && onCabinChangedListener != null) {
                this.mOnCabinChangedListeners.add(onCabinChangedListener);
                android.util.Log.i(TAG, "setOnCabinChangedListener ok! listener size: " + this.mOnCabinChangedListeners.size());
            }
        }
    }

    public void setOnBodyChangedListener(OnBodyChangedListener onBodyChangedListener) {
        synchronized (this.mOnBodyChangedListeners) {
            if (!this.mOnBodyChangedListeners.contains(onBodyChangedListener) && onBodyChangedListener != null) {
                this.mOnBodyChangedListeners.add(onBodyChangedListener);
                android.util.Log.i(TAG, "setOnBodyChangedListener ok! listener size: " + this.mOnBodyChangedListeners.size());
            }
        }
    }

    public void setOnSensorChangedListener(OnCarSensorChangedListener onCarSensorChangedListener) {
        synchronized (this.mOnSensorChangedListeners) {
            if (!this.mOnSensorChangedListeners.contains(onCarSensorChangedListener) && onCarSensorChangedListener != null) {
                this.mOnSensorChangedListeners.add(onCarSensorChangedListener);
                android.util.Log.i(TAG, "setOnSensorChangedListener ok! listener size: " + this.mOnSensorChangedListeners.size());
            }
        }
    }

    public void setOnSettingChangedListener(OnSettingChangedListener onSettingChangedListener) {
        synchronized (this.mOnSettingChangedListeners) {
            if (!this.mOnSettingChangedListeners.contains(onSettingChangedListener) && onSettingChangedListener != null) {
                this.mOnSettingChangedListeners.add(onSettingChangedListener);
                android.util.Log.i(TAG, "setOnSettingChangedListener ok! listener size: " + this.mOnSettingChangedListeners.size());
            }
        }
    }

    public void setOnSelfStudyChangedListener(OnSelfStudyChangedListener onSelfStudyChangedListener) {
        synchronized (this.mOnSelfStudyChangedListeners) {
            if (!this.mOnSelfStudyChangedListeners.contains(onSelfStudyChangedListener) && onSelfStudyChangedListener != null) {
                this.mOnSelfStudyChangedListeners.add(onSelfStudyChangedListener);
                android.util.Log.i(TAG, "setOnSelfStudyChangedListener ok! listener size: " + this.mOnSelfStudyChangedListeners.size());
            }
        }
    }

    public void setOnAssistChangedListener(OnAssistChangedListener onAssistChangedListener) {
        synchronized (this.mOnAssistChangedListeners) {
            if (!this.mOnAssistChangedListeners.contains(onAssistChangedListener) && onAssistChangedListener != null) {
                this.mOnAssistChangedListeners.add(onAssistChangedListener);
                android.util.Log.i(TAG, "setOnAssistChangedListener ok! listener size: " + this.mOnAssistChangedListeners.size());
            }
        }
    }

    public void setOnCloudChangedListener(OnCloudChangedListener onCloudChangedListener) {
        synchronized (this.mOnCloudChangedListeners) {
            if (!this.mOnCloudChangedListeners.contains(onCloudChangedListener) && onCloudChangedListener != null) {
                this.mOnCloudChangedListeners.add(onCloudChangedListener);
                android.util.Log.i(TAG, "setOnCloudChangedListener ok! listener size: " + this.mOnCloudChangedListeners.size());
            }
        }
    }

    public HvacAdapterManager getHvacAdapterManager() {
        if (this.mHvacAdapterManager == null) {
            this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.HVAC_SERVICE);
        }
        return this.mHvacAdapterManager;
    }

    public CarGeneralAdapterManager getGeneralAdapterManager() {
        if (this.mGeneralAdapterManager == null) {
            this.mGeneralAdapterManager = (CarGeneralAdapterManager) this.mCarAdapterManager.getCarAdapterManager("general");
        }
        return this.mGeneralAdapterManager;
    }

    public CarCabinAdapterManager getCarCabinAdapterManager() {
        if (this.mCarCabinAdapterManager == null) {
            this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.CABIN_SERVICE);
        }
        return this.mCarCabinAdapterManager;
    }

    public CarBodyManager getCarBodyManager() {
        if (this.mCarBodyManager == null) {
            this.mCarBodyManager = (CarBodyManager) this.mCarAdapterManager.getCarAdapterManager("body");
        }
        return this.mCarBodyManager;
    }

    public CarAssistAdapterManager getCarAssistAdapterManager() {
        if (this.mCarAssistAdapterManager == null) {
            this.mCarAssistAdapterManager = (CarAssistAdapterManager) this.mCarAdapterManager.getCarAdapterManager("assist");
        }
        return this.mCarAssistAdapterManager;
    }

    public CarSensorAdapterManager getCarSensorAdapterManager() {
        if (this.mCarSensorAdapterManager == null) {
            this.mCarSensorAdapterManager = (CarSensorAdapterManager) this.mCarAdapterManager.getCarAdapterManager("sensor");
        }
        return this.mCarSensorAdapterManager;
    }

    public SettingAdapterManager getSettingAdapterManager() {
        if (this.mSettingAdapterManager == null) {
            this.mSettingAdapterManager = (SettingAdapterManager) this.mCarAdapterManager.getCarAdapterManager("setting");
        }
        return this.mSettingAdapterManager;
    }

    public StudyAdapterManager getStudyAdapterManager() {
        if (this.mStudyAdapterManager == null) {
            this.mStudyAdapterManager = (StudyAdapterManager) this.mCarAdapterManager.getCarAdapterManager("selfstudy");
        }
        return this.mStudyAdapterManager;
    }

    public CarCloudAdapterManager getCarCloudAdapterManager() {
        return this.mCarCloudAdapterManager;
    }

    public ChargingAdapterManager getChargingAdapterManager() {
        if (this.mChargingManager == null) {
            this.mChargingManager = (ChargingAdapterManager) this.mCarAdapterManager.getCarAdapterManager("charging");
        }
        return this.mChargingManager;
    }

    public boolean isConnected() {
        return isConnect;
    }

    public boolean isCarServiceConnect() {
        CarAdapterManager carAdapterManager = this.mCarAdapterManager;
        if (carAdapterManager != null) {
            return carAdapterManager.isCarServiceBound();
        }
        return false;
    }

    public static void registerConnectListener(TsConnectListener tsConnectListener) {
        synchronized (mConnectListener) {
            if (!mConnectListener.contains(tsConnectListener)) {
                mConnectListener.add(tsConnectListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isServiceBound() {
        if (!isConnected()) {
            synchronized (mConnectListener) {
                Iterator<TsConnectListener> it = mConnectListener.iterator();
                while (it.hasNext()) {
                    it.next().isConnected(false);
                }
            }
            return;
        }
        boolean zIsCarServiceConnect = isCarServiceConnect();
        android.util.Log.v(TAG, this.mContext.getPackageName() + " isServiceBound:" + zIsCarServiceConnect);
        if (zIsCarServiceConnect) {
            synchronized (mConnectListener) {
                android.util.Log.i(TAG, this.mContext.getPackageName() + " bind car adapter success!");
                Iterator<TsConnectListener> it2 = mConnectListener.iterator();
                while (it2.hasNext()) {
                    it2.next().isConnected(true);
                }
                mIsConnectServiceAndAdapter = true;
            }
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(500, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isOptimizationApi() {
        synchronized (mConnectListener) {
            android.util.Log.e(TAG, this.mContext.getPackageName() + "isOptimizationApi bind success! mConnectListener is size :" + mConnectListener.size() + "isConnect:" + isConnect);
            Iterator<TsConnectListener> it = mConnectListener.iterator();
            while (it.hasNext()) {
                it.next().isOptimizationApi(isConnect);
            }
        }
    }

    public void registerChargingCallback() {
        ChargingAdapterManager chargingAdapterManager;
        android.util.Log.d(TAG, "re register Charging call back");
        if (isConnected() && (chargingAdapterManager = this.mChargingManager) != null) {
            chargingAdapterManager.subscribeCarChargingCallback(this.mChargingChangedListener);
            this.mIsRegisterCallback.put("registerChargingCallback", 1008);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1008, 500L);
        }
    }

    public void registerHvacCallback() {
        HvacAdapterManager hvacAdapterManager;
        android.util.Log.d(TAG, "re register Hvac call back");
        if (isConnected() && (hvacAdapterManager = this.mHvacAdapterManager) != null) {
            hvacAdapterManager.setOnHvacChangedListener(this.mHvacChangedListener);
            this.mIsRegisterCallback.put("registerHvacCallback", 1000);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1000, 500L);
        }
    }

    public void registerCabinCallback() {
        CarCabinAdapterManager carCabinAdapterManager;
        android.util.Log.d(TAG, "re register cabin call back");
        if (isConnected() && (carCabinAdapterManager = this.mCarCabinAdapterManager) != null) {
            carCabinAdapterManager.subscribeCabinCallback(this.mCabinChangedListener);
            this.mIsRegisterCallback.put("registerCabinCallback", 1001);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1001, 500L);
        }
    }

    public void registerAssistCallback() {
        CarAssistAdapterManager carAssistAdapterManager;
        android.util.Log.d(TAG, "re register assist call back");
        if (isConnected() && (carAssistAdapterManager = this.mCarAssistAdapterManager) != null) {
            carAssistAdapterManager.subscribeCarAssistCallback(this.mAssistChangedListener);
            this.mIsRegisterCallback.put("registerAssistCallback", 1002);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1002, 500L);
        }
    }

    public void registerCarBodyCallback() {
        android.util.Log.d(TAG, "re register car body call back");
        if (isConnected() && this.mCarBodyManager != null) {
            android.util.Log.d(TAG, "registerCarBodyCallback is connected");
            this.mCarBodyManager.subscribeCarBodyCallback(this.mBodyChangedListener);
            this.mIsRegisterCallback.put("registerCarBodyCallback", 1003);
        } else {
            android.util.Log.d(TAG, "registerCarBodyCallback is still not connected");
            this.mHandler.sendEmptyMessageDelayed(1003, 500L);
        }
    }

    public void registerGeneralCallback() {
        CarGeneralAdapterManager carGeneralAdapterManager;
        android.util.Log.d(TAG, "re register general call back");
        if (isConnected() && (carGeneralAdapterManager = this.mGeneralAdapterManager) != null) {
            carGeneralAdapterManager.subscribeCarGeneralCallback(this.mGeneralListener);
            this.mIsRegisterCallback.put("registerGeneralCallback", 1004);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1004, 500L);
        }
    }

    public void registerSensorCallback() {
        CarSensorAdapterManager carSensorAdapterManager;
        android.util.Log.d(TAG, "re register sensor call back");
        if (isConnected() && (carSensorAdapterManager = this.mCarSensorAdapterManager) != null) {
            carSensorAdapterManager.registerSensorCallback(this.mSensorChangedListener);
            this.mIsRegisterCallback.put("registerSensorCallback", 1005);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1005, 500L);
        }
    }

    public void registerCollectCallback() {
        android.util.Log.d(TAG, "re register collect call back");
        if (isConnected() && this.mCarCloudAdapterManager != null) {
            android.util.Log.d(TAG, "re register collect call back in");
            this.mCarCloudAdapterManager.subscribeCarCloudCallback(this.mCollectChangedListener);
            this.mIsRegisterCallback.put("registerCollectCallback", 1007);
            android.util.Log.d(TAG, "re register collect call back out");
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(1007, 500L);
    }

    public void registerSettingCallback() {
        SettingAdapterManager settingAdapterManager;
        android.util.Log.d(TAG, "re register setting call back");
        if (isConnected() && (settingAdapterManager = this.mSettingAdapterManager) != null) {
            settingAdapterManager.setOnSettingChangedListener(this.mSettingChangedListener);
            this.mIsRegisterCallback.put("registerSettingCallback", 1006);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1006, 500L);
        }
    }

    private void reRegisterBinderCallback() {
        android.util.Log.d("reRegisterCallback", "re register call back");
        try {
            Iterator<String> it = this.mIsRegisterCallback.keySet().iterator();
            while (it.hasNext()) {
                this.mHandler.sendEmptyMessage(this.mIsRegisterCallback.get(it.next()).intValue());
            }
        } catch (ConcurrentModificationException e) {
            android.util.Log.e(TAG, e.getMessage());
        }
    }

    public static boolean isConnectSuccess() {
        return mIsConnectServiceAndAdapter;
    }
}
