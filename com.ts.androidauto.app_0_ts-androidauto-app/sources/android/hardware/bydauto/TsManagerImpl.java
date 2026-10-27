package android.hardware.bydauto;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ts.lib.car.Car;
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
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TsManagerImpl {
    private static final int COUNT_RETRY_SERVICE = 10;
    private static final int MSG_DELAY_CALLBACK_SEC = 100;
    private static final int MSG_DELAY_MILL_SEC = 500;
    private static final int MSG_DELAY_RECONNECT = 10000;
    private static final int MSG_IS_OPTIMIZATION_API_STATE = 405;
    private static final int MSG_RECONNECT_SERVICE = 600;
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
    private static final String PACKAGE_SYSTEMUI = "com.android.systemui";
    private static final String PACKAGE_UEREPORT = "com.byd.uereport";
    public static final int S_CONSTANT_EIGHT = 8;
    public static final int S_CONSTANT_ELEVEN = 11;
    public static final int S_CONSTANT_FIVE = 5;
    public static final int S_CONSTANT_FOUR = 4;
    public static final int S_CONSTANT_NEGATIVE = -1;
    public static final int S_CONSTANT_NINE = 9;
    public static final int S_CONSTANT_ONE = 1;
    public static final int S_CONSTANT_SEVEN = 7;
    public static final int S_CONSTANT_SIX = 6;
    public static final int S_CONSTANT_SIXTEEN = 16;
    public static final int S_CONSTANT_TEN = 10;
    public static final int S_CONSTANT_THREE = 3;
    public static final int S_CONSTANT_TWO = 2;
    public static final int S_CONSTANT_ZERO = 0;
    public static final String VERSION = "v0.2.54";
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
    private int mRetryCount;
    private SettingAdapterManager mSettingAdapterManager;
    private StudyAdapterManager mStudyAdapterManager;
    private static final String TAG = TsManagerImpl.class.getSimpleName();
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
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
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
            if (i == 600) {
                TsManagerImpl.this.reconnectService();
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
                    android.util.Log.d(TsManagerImpl.TAG, "no have message " + msg.what);
                    break;
            }
        }
    };
    private OnChargingChangedListener mChargingChangedListener = new OnChargingChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.3
        public void onChargingChanged(int id, int statusId, CarBodyPacket packet) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                for (OnChargingChangedListener listener : TsManagerImpl.this.mOnChargingChangedListeners) {
                    listener.onChargingChanged(id, statusId, packet);
                }
            }
        }

        public void onBinderStateChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                for (OnChargingChangedListener listener : TsManagerImpl.this.mOnChargingChangedListeners) {
                    listener.onBinderStateChanged(binderState);
                }
            }
        }
    };
    private OnHvacChangedListener mHvacChangedListener = new OnHvacChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.4
        public void onHvacChanged(int id, int statusId, HvacPacket packet) {
            synchronized (TsManagerImpl.this.mOnHvacChangedListeners) {
                for (OnHvacChangedListener listener : TsManagerImpl.this.mOnHvacChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "OnHvacChangedListener package: " + TsManagerImpl.this.mContext.getPackageName() + ", id:" + id + ", statusId:" + statusId + ", isConnect:" + TsManagerImpl.isConnect);
                    listener.onHvacChanged(id, statusId, packet);
                }
            }
        }
    };
    private OnCabinChangedListener mCabinChangedListener = new OnCabinChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.5
        public void onCabinChanged(int id, int areaId, CabinPacket packet) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                for (OnCabinChangedListener listener : TsManagerImpl.this.mOnCabinChangedListeners) {
                    listener.onCabinChanged(id, areaId, packet);
                }
            }
        }

        public void onCabinBinderChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                for (OnCabinChangedListener listener : TsManagerImpl.this.mOnCabinChangedListeners) {
                    listener.onCabinBinderChanged(binderState);
                }
            }
        }
    };
    private OnCarAssistChangedListener mAssistChangedListener = new OnCarAssistChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.6
        public void onCarAssistChanged(int id, int areaId, CarAssistPacket packet) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                for (OnAssistChangedListener listener : TsManagerImpl.this.mOnAssistChangedListeners) {
                    listener.onAssistChanged(id, areaId, packet);
                }
            }
        }

        public void onBinderStateChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                for (OnAssistChangedListener listener : TsManagerImpl.this.mOnAssistChangedListeners) {
                    listener.onBinderStateChanged(binderState);
                }
            }
        }
    };
    private OnBodyChangedListener mBodyChangedListener = new OnBodyChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.7
        public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                for (OnBodyChangedListener listener : TsManagerImpl.this.mOnBodyChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "onBodyChanged package: " + TsManagerImpl.this.mContext.getPackageName() + ", id:" + id + ", areaId:" + areaId + ", isConnect:" + TsManagerImpl.isConnect);
                    listener.onBodyChanged(id, areaId, packet);
                }
            }
        }

        public void onBinderStateChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                for (OnBodyChangedListener listener : TsManagerImpl.this.mOnBodyChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "onBodyBinderStateChanged package: " + TsManagerImpl.this.mContext.getPackageName() + ", binderState:" + binderState + ", isConnect:" + TsManagerImpl.isConnect);
                    listener.onBinderStateChanged(binderState);
                }
            }
        }
    };
    private OnCarGeneralChangedListener mGeneralListener = new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.8
        public void onCarGeneralChanged(int id, int areaId, GeneralPacket packet) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                for (OnCarGeneralChangedListener listener : TsManagerImpl.this.onCarGeneralChangedListeners) {
                    listener.onCarGeneralChanged(id, areaId, packet);
                }
            }
        }

        public void onCarGeneralBinderChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                for (OnCarGeneralChangedListener listener : TsManagerImpl.this.onCarGeneralChangedListeners) {
                    listener.onCarGeneralBinderChanged(binderState);
                }
            }
        }

        public void onCarClusterBrightnessChanged(int brightness) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                for (OnCarGeneralChangedListener listener : TsManagerImpl.this.onCarGeneralChangedListeners) {
                    listener.onCarClusterBrightnessChanged(brightness);
                }
            }
        }
    };
    private OnCarSensorChangedListener mSensorChangedListener = new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.9
        public void onCarSensorStateChanged(int id, int areaId, SensorPacket packet) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                for (OnCarSensorChangedListener listener : TsManagerImpl.this.mOnSensorChangedListeners) {
                    listener.onCarSensorStateChanged(id, areaId, packet);
                }
            }
        }

        public void onCarSensorBinderChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                for (OnCarSensorChangedListener listener : TsManagerImpl.this.mOnSensorChangedListeners) {
                    listener.onCarSensorBinderChanged(binderState);
                }
            }
        }
    };
    private OnCloudChangedListener mCollectChangedListener = new OnCloudChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.10
        public void onCloudChanged(int id, int areaId, CarCloudPacket packet) {
        }

        public void onRemoteChanged(int id, int areaId, CarRemotePacket packet) {
        }

        public void onCollectDataChanged(int id, int areaId, CarCollectDataPacket packet) {
            synchronized (TsManagerImpl.this.mOnCloudChangedListeners) {
                for (OnCloudChangedListener listener : TsManagerImpl.this.mOnCloudChangedListeners) {
                    listener.onCollectDataChanged(id, areaId, packet);
                }
            }
        }

        public void onBinderStateChanged(boolean binderState) {
        }
    };
    private OnSettingChangedListener mSettingChangedListener = new OnSettingChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.11
        public void onSettingChanged(int id, int areaId, SettingPacket packet) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                for (OnSettingChangedListener listener : TsManagerImpl.this.mOnSettingChangedListeners) {
                    listener.onSettingChanged(id, areaId, packet);
                }
            }
        }

        public void onBinderSettingChanged(boolean binderState) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                for (OnSettingChangedListener listener : TsManagerImpl.this.mOnSettingChangedListeners) {
                    listener.onBinderSettingChanged(binderState);
                }
            }
        }
    };

    private TsManagerImpl(Context context) {
        this.mContext = context;
        android.util.Log.w(TAG, "current jar version = v0.2.54 " + this.mContext.getPackageName());
        final long startTime = System.currentTimeMillis();
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.TsManagerImpl.1
            public void onBinderStateChanged(boolean binderState) {
                long endTime = System.currentTimeMillis();
                android.util.Log.e(TsManagerImpl.TAG, "bind service time consuming=" + (endTime - startTime) + ", isConnect:" + TsManagerImpl.isConnect + ", binderState:" + binderState);
                TsManagerImpl.this.mRetryCount = 0;
                if (TsManagerImpl.isConnect && !binderState) {
                    TsManagerImpl.isConnect = binderState;
                    TsManagerImpl.this.checkServiceConnection();
                } else if (!TsManagerImpl.isConnect && binderState) {
                    TsManagerImpl.this.mHandler.removeMessages(600);
                }
                TsManagerImpl.isConnect = binderState;
                TsManagerImpl.this.mHandler.sendEmptyMessage(405);
                TsManagerImpl.this.mHandler.sendEmptyMessage(500);
                TsManagerImpl.this.initAdapterManager();
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
    public void initAdapterManager() {
        android.util.Log.d(TAG, "bind state is " + isConnect);
        android.util.Log.w(TAG, "bind state start ");
        if (isConnect) {
            if (this.mHvacAdapterManager == null) {
                this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_HVAC_SERVICE);
            }
            if (this.mCarCabinAdapterManager == null) {
                this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager("cabin");
            }
            if (this.mCarAssistAdapterManager == null) {
                this.mCarAssistAdapterManager = (CarAssistAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_ASSIST_SERVICE);
            }
            if (this.mCarBodyManager == null) {
                this.mCarBodyManager = (CarBodyManager) this.mCarAdapterManager.getCarAdapterManager("body");
            }
            if (this.mChargingManager == null) {
                this.mChargingManager = (ChargingAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_CHARGING_SERVICE);
            }
            if (this.mGeneralAdapterManager == null) {
                this.mGeneralAdapterManager = (CarGeneralAdapterManager) this.mCarAdapterManager.getCarAdapterManager("general");
            }
            if (this.mCarSensorAdapterManager == null) {
                this.mCarSensorAdapterManager = (CarSensorAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_SENSOR_SERVICE);
            }
            if (this.mSettingAdapterManager == null) {
                this.mSettingAdapterManager = (SettingAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_SETTING_SERVICE);
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

    public void setOnSensorChangedListener(OnCarSensorChangedListener onSensorChangedListener) {
        synchronized (this.mOnSensorChangedListeners) {
            if (!this.mOnSensorChangedListeners.contains(onSensorChangedListener) && onSensorChangedListener != null) {
                this.mOnSensorChangedListeners.add(onSensorChangedListener);
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

    public void setOnSelfStudyChangedListener(OnSelfStudyChangedListener listener) {
        synchronized (this.mOnSelfStudyChangedListeners) {
            if (!this.mOnSelfStudyChangedListeners.contains(listener) && listener != null) {
                this.mOnSelfStudyChangedListeners.add(listener);
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
            this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_HVAC_SERVICE);
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
            this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager("cabin");
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
            this.mCarAssistAdapterManager = (CarAssistAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_ASSIST_SERVICE);
        }
        return this.mCarAssistAdapterManager;
    }

    public CarSensorAdapterManager getCarSensorAdapterManager() {
        if (this.mCarSensorAdapterManager == null) {
            this.mCarSensorAdapterManager = (CarSensorAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_SENSOR_SERVICE);
        }
        return this.mCarSensorAdapterManager;
    }

    public SettingAdapterManager getSettingAdapterManager() {
        if (this.mSettingAdapterManager == null) {
            this.mSettingAdapterManager = (SettingAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_SETTING_SERVICE);
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
            this.mChargingManager = (ChargingAdapterManager) this.mCarAdapterManager.getCarAdapterManager(Car.VENDOR_CHARGING_SERVICE);
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

    public static void registerConnectListener(TsConnectListener connectListener) {
        synchronized (mConnectListener) {
            if (!mConnectListener.contains(connectListener)) {
                mConnectListener.add(connectListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isServiceBound() {
        if (!isConnected()) {
            synchronized (mConnectListener) {
                for (TsConnectListener tsConnectListener : mConnectListener) {
                    tsConnectListener.isConnected(false);
                }
            }
            return;
        }
        boolean serviceBound = isCarServiceConnect();
        android.util.Log.v(TAG, this.mContext.getPackageName() + " isServiceBound:" + serviceBound);
        if (serviceBound) {
            synchronized (mConnectListener) {
                android.util.Log.i(TAG, this.mContext.getPackageName() + " bind car adapter success!");
                for (TsConnectListener tsConnectListener2 : mConnectListener) {
                    tsConnectListener2.isConnected(true);
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
            for (TsConnectListener tsConnectListener : mConnectListener) {
                tsConnectListener.isOptimizationApi(isConnect);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectService() {
        android.util.Log.e(TAG, "package:" + this.mContext.getPackageName() + " reconnectService, mRetryCount:" + this.mRetryCount + ", isConnect:" + isConnect + ", mCarAdapterManager:" + this.mCarAdapterManager);
        CarAdapterManager carAdapterManager = this.mCarAdapterManager;
        if (carAdapterManager != null && !isConnect) {
            carAdapterManager.connect();
            int i = this.mRetryCount;
            if (i < 10) {
                this.mRetryCount = i + 1;
                this.mHandler.sendEmptyMessageDelayed(600, 10000L);
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
            for (String key : this.mIsRegisterCallback.keySet()) {
                this.mHandler.sendEmptyMessage(this.mIsRegisterCallback.get(key).intValue());
            }
        } catch (ConcurrentModificationException exception) {
            android.util.Log.e(TAG, exception.getMessage());
        }
    }

    public static boolean isConnectSuccess() {
        return mIsConnectServiceAndAdapter;
    }

    public void checkServiceConnection() {
        android.util.Log.e(TAG, "checkServiceConnection, package:" + this.mContext.getPackageName() + " isConnect:" + isConnect);
        if (!isConnect) {
            if (PACKAGE_SYSTEMUI.equals(this.mContext.getPackageName()) || PACKAGE_UEREPORT.equals(this.mContext.getPackageName())) {
                this.mRetryCount = 0;
                android.util.Log.e(TAG, "checkServiceConnection, package:" + this.mContext.getPackageName() + " reconnect Carservice");
                this.mHandler.removeMessages(600);
                this.mHandler.sendEmptyMessageDelayed(600, 10000L);
            }
        }
    }
}
