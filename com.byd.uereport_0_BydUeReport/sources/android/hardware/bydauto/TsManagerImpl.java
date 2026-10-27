package android.hardware.bydauto;

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
    private static final int COUNT_RETRY_SERVICE = 3;
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
    private static final String TAG = "TsManagerImpl";
    public static final String VERSION = "v0.2.50";
    public static boolean isReturnCallback = false;
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
            int i8 = message.what;
            if (i8 == 405) {
                android.util.Log.e(TsManagerImpl.TAG, " isOptimizationApi start! isConnect:" + TsManagerImpl.isConnect);
                TsManagerImpl.isReturnCallback = true;
                TsManagerImpl.this.isOptimizationApi();
                android.util.Log.e(TsManagerImpl.TAG, " isOptimizationApi end! isConnect:" + TsManagerImpl.isConnect);
            }
            if (i8 == 500) {
                TsManagerImpl.this.isServiceBound();
                return;
            }
            if (i8 == 600) {
                TsManagerImpl.this.reconnectService();
                return;
            }
            switch (i8) {
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
        public void onBinderStateChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnChargingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnChargingChangedListener) it.next()).onBinderStateChanged(z7);
                }
            }
        }

        public void onChargingChanged(int i8, int i9, CarBodyPacket carBodyPacket) {
            synchronized (TsManagerImpl.this.mOnChargingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnChargingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnChargingChangedListener) it.next()).onChargingChanged(i8, i9, carBodyPacket);
                }
            }
        }
    };
    private OnHvacChangedListener mHvacChangedListener = new OnHvacChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.4
        public void onHvacChanged(int i8, int i9, HvacPacket hvacPacket) {
            synchronized (TsManagerImpl.this.mOnHvacChangedListeners) {
                for (OnHvacChangedListener onHvacChangedListener : TsManagerImpl.this.mOnHvacChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "OnHvacChangedListener package: " + TsManagerImpl.this.mContext.getPackageName() + ", id:" + i8 + ", statusId:" + i9 + ", isConnect:" + TsManagerImpl.isConnect);
                    onHvacChangedListener.onHvacChanged(i8, i9, hvacPacket);
                }
            }
        }
    };
    private OnCabinChangedListener mCabinChangedListener = new OnCabinChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.5
        public void onCabinBinderChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCabinChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCabinChangedListener) it.next()).onCabinBinderChanged(z7);
                }
            }
        }

        public void onCabinChanged(int i8, int i9, CabinPacket cabinPacket) {
            synchronized (TsManagerImpl.this.mOnCabinChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCabinChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCabinChangedListener) it.next()).onCabinChanged(i8, i9, cabinPacket);
                }
            }
        }
    };
    private OnCarAssistChangedListener mAssistChangedListener = new OnCarAssistChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.6
        public void onBinderStateChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnAssistChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAssistChangedListener) it.next()).onBinderStateChanged(z7);
                }
            }
        }

        public void onCarAssistChanged(int i8, int i9, CarAssistPacket carAssistPacket) {
            synchronized (TsManagerImpl.this.mOnAssistChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnAssistChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAssistChangedListener) it.next()).onAssistChanged(i8, i9, carAssistPacket);
                }
            }
        }
    };
    private OnBodyChangedListener mBodyChangedListener = new OnBodyChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.7
        public void onBinderStateChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                for (OnBodyChangedListener onBodyChangedListener : TsManagerImpl.this.mOnBodyChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "onBodyBinderStateChanged package: " + TsManagerImpl.this.mContext.getPackageName() + ", binderState:" + z7 + ", isConnect:" + TsManagerImpl.isConnect);
                    onBodyChangedListener.onBinderStateChanged(z7);
                }
            }
        }

        public void onBodyChanged(int i8, int i9, CarBodyPacket carBodyPacket) {
            synchronized (TsManagerImpl.this.mOnBodyChangedListeners) {
                for (OnBodyChangedListener onBodyChangedListener : TsManagerImpl.this.mOnBodyChangedListeners) {
                    android.util.Log.d(TsManagerImpl.TAG, "onBodyChanged package: " + TsManagerImpl.this.mContext.getPackageName() + ", id:" + i8 + ", areaId:" + i9 + ", isConnect:" + TsManagerImpl.isConnect);
                    onBodyChangedListener.onBodyChanged(i8, i9, carBodyPacket);
                }
            }
        }
    };
    private OnCarGeneralChangedListener mGeneralListener = new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.8
        public void onCarClusterBrightnessChanged(int i8) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarClusterBrightnessChanged(i8);
                }
            }
        }

        public void onCarGeneralBinderChanged(boolean z7) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarGeneralBinderChanged(z7);
                }
            }
        }

        public void onCarGeneralChanged(int i8, int i9, GeneralPacket generalPacket) {
            synchronized (TsManagerImpl.this.onCarGeneralChangedListeners) {
                Iterator it = TsManagerImpl.this.onCarGeneralChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarGeneralChangedListener) it.next()).onCarGeneralChanged(i8, i9, generalPacket);
                }
            }
        }
    };
    private OnCarSensorChangedListener mSensorChangedListener = new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.9
        public void onCarSensorBinderChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSensorChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarSensorChangedListener) it.next()).onCarSensorBinderChanged(z7);
                }
            }
        }

        public void onCarSensorStateChanged(int i8, int i9, SensorPacket sensorPacket) {
            synchronized (TsManagerImpl.this.mOnSensorChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSensorChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarSensorChangedListener) it.next()).onCarSensorStateChanged(i8, i9, sensorPacket);
                }
            }
        }
    };
    private OnCloudChangedListener mCollectChangedListener = new OnCloudChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.10
        public void onBinderStateChanged(boolean z7) {
        }

        public void onCloudChanged(int i8, int i9, CarCloudPacket carCloudPacket) {
        }

        public void onCollectDataChanged(int i8, int i9, CarCollectDataPacket carCollectDataPacket) {
            synchronized (TsManagerImpl.this.mOnCloudChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnCloudChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCloudChangedListener) it.next()).onCollectDataChanged(i8, i9, carCollectDataPacket);
                }
            }
        }

        public void onRemoteChanged(int i8, int i9, CarRemotePacket carRemotePacket) {
        }
    };
    private OnSettingChangedListener mSettingChangedListener = new OnSettingChangedListener() { // from class: android.hardware.bydauto.TsManagerImpl.11
        public void onBinderSettingChanged(boolean z7) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSettingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnSettingChangedListener) it.next()).onBinderSettingChanged(z7);
                }
            }
        }

        public void onSettingChanged(int i8, int i9, SettingPacket settingPacket) {
            synchronized (TsManagerImpl.this.mOnSettingChangedListeners) {
                Iterator it = TsManagerImpl.this.mOnSettingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnSettingChangedListener) it.next()).onSettingChanged(i8, i9, settingPacket);
                }
            }
        }
    };

    private TsManagerImpl(Context context) {
        this.mContext = context;
        android.util.Log.w(TAG, "current jar version = v0.2.50 " + this.mContext.getPackageName());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.TsManagerImpl.1
            public void onBinderStateChanged(boolean z7) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                android.util.Log.e(TsManagerImpl.TAG, "bind service time consuming=" + (jCurrentTimeMillis2 - jCurrentTimeMillis) + ", isConnect:" + TsManagerImpl.isConnect + ", binderState:" + z7);
                TsManagerImpl.this.mRetryCount = 0;
                boolean z8 = TsManagerImpl.isConnect;
                if (z8 && !z7) {
                    TsManagerImpl.isConnect = z7;
                    TsManagerImpl.this.checkServiceConnection();
                } else if (!z8 && z7) {
                    TsManagerImpl.this.mHandler.removeMessages(600);
                }
                TsManagerImpl.isConnect = z7;
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
        String str = TAG;
        android.util.Log.d(str, "bind state is " + isConnect);
        android.util.Log.w(str, "bind state start ");
        if (!isConnect) {
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
            return;
        }
        if (this.mHvacAdapterManager == null) {
            this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager("hvac");
        }
        if (this.mCarCabinAdapterManager == null) {
            this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager("cabin");
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
        android.util.Log.w(str, "bind state end ");
    }

    public static boolean isConnectSuccess() {
        return mIsConnectServiceAndAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isOptimizationApi() {
        List<TsConnectListener> list = mConnectListener;
        synchronized (list) {
            android.util.Log.e(TAG, this.mContext.getPackageName() + "isOptimizationApi bind success! mConnectListener is size :" + list.size() + "isConnect:" + isConnect);
            Iterator<TsConnectListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().isOptimizationApi(isConnect);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isServiceBound() {
        if (!isConnected()) {
            List<TsConnectListener> list = mConnectListener;
            synchronized (list) {
                Iterator<TsConnectListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().isConnected(false);
                }
            }
            return;
        }
        boolean zIsCarServiceConnect = isCarServiceConnect();
        String str = TAG;
        android.util.Log.v(str, this.mContext.getPackageName() + " isServiceBound:" + zIsCarServiceConnect);
        if (!zIsCarServiceConnect) {
            this.mHandler.sendEmptyMessageDelayed(500, 100L);
            return;
        }
        List<TsConnectListener> list2 = mConnectListener;
        synchronized (list2) {
            android.util.Log.i(str, this.mContext.getPackageName() + " bind car adapter success!");
            Iterator<TsConnectListener> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().isConnected(true);
            }
            mIsConnectServiceAndAdapter = true;
        }
    }

    private void reRegisterBinderCallback() {
        android.util.Log.d("reRegisterCallback", "re register call back");
        try {
            Iterator<String> it = this.mIsRegisterCallback.keySet().iterator();
            while (it.hasNext()) {
                this.mHandler.sendEmptyMessage(this.mIsRegisterCallback.get(it.next()).intValue());
            }
        } catch (ConcurrentModificationException e8) {
            android.util.Log.e(TAG, e8.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectService() {
        android.util.Log.e(TAG, "package:" + this.mContext.getPackageName() + " reconnectService, mRetryCount:" + this.mRetryCount + ", isConnect:" + isConnect + ", mCarAdapterManager:" + this.mCarAdapterManager);
        CarAdapterManager carAdapterManager = this.mCarAdapterManager;
        if (carAdapterManager == null || isConnect) {
            return;
        }
        carAdapterManager.connect();
        int i8 = this.mRetryCount;
        if (i8 < 3) {
            this.mRetryCount = i8 + 1;
            this.mHandler.sendEmptyMessageDelayed(600, 10000L);
        }
    }

    public static void registerConnectListener(TsConnectListener tsConnectListener) {
        List<TsConnectListener> list = mConnectListener;
        synchronized (list) {
            if (!list.contains(tsConnectListener)) {
                list.add(tsConnectListener);
            }
        }
    }

    public void checkServiceConnection() {
        String str = TAG;
        android.util.Log.e(str, "checkServiceConnection, package:" + this.mContext.getPackageName() + " isConnect:" + isConnect);
        if (isConnect) {
            return;
        }
        if (PACKAGE_SYSTEMUI.equals(this.mContext.getPackageName()) || PACKAGE_UEREPORT.equals(this.mContext.getPackageName())) {
            this.mRetryCount = 0;
            android.util.Log.e(str, "checkServiceConnection, package:" + this.mContext.getPackageName() + " reconnect Carservice");
            this.mHandler.removeMessages(600);
            this.mHandler.sendEmptyMessageDelayed(600, 10000L);
        }
    }

    public CarAssistAdapterManager getCarAssistAdapterManager() {
        if (this.mCarAssistAdapterManager == null) {
            this.mCarAssistAdapterManager = (CarAssistAdapterManager) this.mCarAdapterManager.getCarAdapterManager("assist");
        }
        return this.mCarAssistAdapterManager;
    }

    public CarBodyManager getCarBodyManager() {
        if (this.mCarBodyManager == null) {
            this.mCarBodyManager = (CarBodyManager) this.mCarAdapterManager.getCarAdapterManager("body");
        }
        return this.mCarBodyManager;
    }

    public CarCabinAdapterManager getCarCabinAdapterManager() {
        if (this.mCarCabinAdapterManager == null) {
            this.mCarCabinAdapterManager = (CarCabinAdapterManager) this.mCarAdapterManager.getCarAdapterManager("cabin");
        }
        return this.mCarCabinAdapterManager;
    }

    public CarCloudAdapterManager getCarCloudAdapterManager() {
        return this.mCarCloudAdapterManager;
    }

    public CarSensorAdapterManager getCarSensorAdapterManager() {
        if (this.mCarSensorAdapterManager == null) {
            this.mCarSensorAdapterManager = (CarSensorAdapterManager) this.mCarAdapterManager.getCarAdapterManager("sensor");
        }
        return this.mCarSensorAdapterManager;
    }

    public ChargingAdapterManager getChargingAdapterManager() {
        if (this.mChargingManager == null) {
            this.mChargingManager = (ChargingAdapterManager) this.mCarAdapterManager.getCarAdapterManager("charging");
        }
        return this.mChargingManager;
    }

    public CarGeneralAdapterManager getGeneralAdapterManager() {
        if (this.mGeneralAdapterManager == null) {
            this.mGeneralAdapterManager = (CarGeneralAdapterManager) this.mCarAdapterManager.getCarAdapterManager("general");
        }
        return this.mGeneralAdapterManager;
    }

    public HvacAdapterManager getHvacAdapterManager() {
        if (this.mHvacAdapterManager == null) {
            this.mHvacAdapterManager = (HvacAdapterManager) this.mCarAdapterManager.getCarAdapterManager("hvac");
        }
        return this.mHvacAdapterManager;
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

    public boolean isCarServiceConnect() {
        CarAdapterManager carAdapterManager = this.mCarAdapterManager;
        if (carAdapterManager != null) {
            return carAdapterManager.isCarServiceBound();
        }
        return false;
    }

    public boolean isConnected() {
        return isConnect;
    }

    public void registerAssistCallback() {
        CarAssistAdapterManager carAssistAdapterManager;
        android.util.Log.d(TAG, "re register assist call back");
        if (!isConnected() || (carAssistAdapterManager = this.mCarAssistAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1002, 500L);
        } else {
            carAssistAdapterManager.subscribeCarAssistCallback(this.mAssistChangedListener);
            this.mIsRegisterCallback.put("registerAssistCallback", 1002);
        }
    }

    public void registerCabinCallback() {
        CarCabinAdapterManager carCabinAdapterManager;
        android.util.Log.d(TAG, "re register cabin call back");
        if (!isConnected() || (carCabinAdapterManager = this.mCarCabinAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1001, 500L);
        } else {
            carCabinAdapterManager.subscribeCabinCallback(this.mCabinChangedListener);
            this.mIsRegisterCallback.put("registerCabinCallback", 1001);
        }
    }

    public void registerCarBodyCallback() {
        String str = TAG;
        android.util.Log.d(str, "re register car body call back");
        if (!isConnected() || this.mCarBodyManager == null) {
            android.util.Log.d(str, "registerCarBodyCallback is still not connected");
            this.mHandler.sendEmptyMessageDelayed(1003, 500L);
        } else {
            android.util.Log.d(str, "registerCarBodyCallback is connected");
            this.mCarBodyManager.subscribeCarBodyCallback(this.mBodyChangedListener);
            this.mIsRegisterCallback.put("registerCarBodyCallback", 1003);
        }
    }

    public void registerChargingCallback() {
        ChargingAdapterManager chargingAdapterManager;
        android.util.Log.d(TAG, "re register Charging call back");
        if (!isConnected() || (chargingAdapterManager = this.mChargingManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1008, 500L);
        } else {
            chargingAdapterManager.subscribeCarChargingCallback(this.mChargingChangedListener);
            this.mIsRegisterCallback.put("registerChargingCallback", 1008);
        }
    }

    public void registerCollectCallback() {
        String str = TAG;
        android.util.Log.d(str, "re register collect call back");
        if (!isConnected() || this.mCarCloudAdapterManager == null) {
            this.mHandler.sendEmptyMessageDelayed(1007, 500L);
            return;
        }
        android.util.Log.d(str, "re register collect call back in");
        this.mCarCloudAdapterManager.subscribeCarCloudCallback(this.mCollectChangedListener);
        this.mIsRegisterCallback.put("registerCollectCallback", 1007);
        android.util.Log.d(str, "re register collect call back out");
    }

    public void registerGeneralCallback() {
        CarGeneralAdapterManager carGeneralAdapterManager;
        android.util.Log.d(TAG, "re register general call back");
        if (!isConnected() || (carGeneralAdapterManager = this.mGeneralAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1004, 500L);
        } else {
            carGeneralAdapterManager.subscribeCarGeneralCallback(this.mGeneralListener);
            this.mIsRegisterCallback.put("registerGeneralCallback", 1004);
        }
    }

    public void registerHvacCallback() {
        HvacAdapterManager hvacAdapterManager;
        android.util.Log.d(TAG, "re register Hvac call back");
        if (!isConnected() || (hvacAdapterManager = this.mHvacAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1000, 500L);
        } else {
            hvacAdapterManager.setOnHvacChangedListener(this.mHvacChangedListener);
            this.mIsRegisterCallback.put("registerHvacCallback", 1000);
        }
    }

    public void registerSensorCallback() {
        CarSensorAdapterManager carSensorAdapterManager;
        android.util.Log.d(TAG, "re register sensor call back");
        if (!isConnected() || (carSensorAdapterManager = this.mCarSensorAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1005, 500L);
        } else {
            carSensorAdapterManager.registerSensorCallback(this.mSensorChangedListener);
            this.mIsRegisterCallback.put("registerSensorCallback", 1005);
        }
    }

    public void registerSettingCallback() {
        SettingAdapterManager settingAdapterManager;
        android.util.Log.d(TAG, "re register setting call back");
        if (!isConnected() || (settingAdapterManager = this.mSettingAdapterManager) == null) {
            this.mHandler.sendEmptyMessageDelayed(1006, 500L);
        } else {
            settingAdapterManager.setOnSettingChangedListener(this.mSettingChangedListener);
            this.mIsRegisterCallback.put("registerSettingCallback", 1006);
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

    public void setOnBodyChangedListener(OnBodyChangedListener onBodyChangedListener) {
        synchronized (this.mOnBodyChangedListeners) {
            if (!this.mOnBodyChangedListeners.contains(onBodyChangedListener) && onBodyChangedListener != null) {
                this.mOnBodyChangedListeners.add(onBodyChangedListener);
                android.util.Log.i(TAG, "setOnBodyChangedListener ok! listener size: " + this.mOnBodyChangedListeners.size());
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

    public void setOnChargingChangedListener(OnChargingChangedListener onChargingChangedListener) {
        synchronized (this.mOnChargingChangedListeners) {
            if (!this.mOnChargingChangedListeners.contains(onChargingChangedListener) && onChargingChangedListener != null) {
                this.mOnChargingChangedListeners.add(onChargingChangedListener);
                android.util.Log.i(TAG, "setOnChargingChangedListener ok! listener size: " + this.mOnChargingChangedListeners.size());
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

    public void setOnGeneralChangedListener(OnCarGeneralChangedListener onCarGeneralChangedListener) {
        synchronized (this.onCarGeneralChangedListeners) {
            if (!this.onCarGeneralChangedListeners.contains(onCarGeneralChangedListener) && onCarGeneralChangedListener != null) {
                this.onCarGeneralChangedListeners.add(onCarGeneralChangedListener);
                android.util.Log.i(TAG, "setOnSafetyBeltChangedListener ok! listener size: " + this.onCarGeneralChangedListeners.size());
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

    public void setOnSelfStudyChangedListener(OnSelfStudyChangedListener onSelfStudyChangedListener) {
        synchronized (this.mOnSelfStudyChangedListeners) {
            if (!this.mOnSelfStudyChangedListeners.contains(onSelfStudyChangedListener) && onSelfStudyChangedListener != null) {
                this.mOnSelfStudyChangedListeners.add(onSelfStudyChangedListener);
                android.util.Log.i(TAG, "setOnSelfStudyChangedListener ok! listener size: " + this.mOnSelfStudyChangedListeners.size());
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
}
