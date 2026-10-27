package com.ts.appservice.power;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.car.hardware.power.CarPowerManager;
import android.car.media.CarAudioControl;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.TetheringManager;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.ConcurrentUtils;
import com.ts.appservice.power.audio.ExtAudioManager;
import com.ts.appservice.power.bcall.ExtBcallManager;
import com.ts.appservice.power.car.BYDCarServiceBase;
import com.ts.appservice.power.carplay.CarPlayRemoteManager;
import com.ts.appservice.power.constant.CarPowerServiceConst;
import com.ts.appservice.power.ecall.ExtEcallManager;
import com.ts.appservice.power.ext.ExtCarPowerManager;
import com.ts.appservice.power.hardkey.ExtHardKeyManager;
import com.ts.appservice.power.light.ExtLightsManager;
import com.ts.appservice.power.state.CarPowerOnDegradedState;
import com.ts.appservice.power.state.CarPowerOnNormalState;
import com.ts.appservice.power.state.CarPowerOnOffState;
import com.ts.appservice.power.state.CarPowerOnPreStartUpState;
import com.ts.appservice.power.state.CarPowerOnSleepState;
import com.ts.appservice.power.state.CarPowerOnStandbyState;
import com.ts.appservice.power.state.CarPowerOnStartUpState;
import com.ts.appservice.power.state.CarPowerOnStrState;
import com.ts.appservice.power.state.WorkModeState;
import com.ts.appservice.power.usb.UsbManager;
import com.ts.appservice.power.view.PowerBlackViewControler;
import com.ts.appservice.power.view.PowerViewControler;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import com.ts.lib.car.hardware.carbody.CarbodyEventChangeValue;
import com.ts.lib.car.hardware.power.CarVendorPowerManager;
import com.ts.lib.car.hardware.power.PowerEventChangeValue;
import com.ts.lib.power.ICarPowerBindCarServiceListener;
import com.ts.lib.power.ICarPowerBinder;
import com.ts.lib.power.ICarPowerEventListener;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import vendor.bosch.display.V1_0.IDisplay;
import vendor.bosch.display.V1_0.IDisplayCallback;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerBinder extends ICarPowerBinder.Stub implements CarPowerManager.CarPowerStateListenerWithCompletion {
    private static final int ABNORMAL_VALUE = 0;
    private static final int AWAKE_TIME = 6000;
    private static final int AWAKE_TIME_NORMAL = 180;
    private static final int AWAKE_TIME_OMS = 3600;
    private static final int AWAKE_TIME_UNKNOW = -1;
    private static final int AWAKE_TIME_WITH_BGSAVER = 500;
    private static final int AWAKE_TIME_ZERO = 0;
    private static final int AWAKE_WHEN_BGSAVER_ON = 30;
    private static final String BOOT_COMPLETED = "1";
    private static final int BRIGHTNESS_ERROR = 256;
    private static final int BRIGHTNESS_LEVEL_MAX = 100;
    private static final int BRIGHTNESS_MAX = 255;
    private static final int BRIGHTNESS_MINI = 10;
    private static final String BRIGHTNESS_PERMISSION = "com.ts.power.permission.BRIGHTNESS";
    private static final int CAR_AUDIO_CONTROL_TYPE = 15;
    private static final String CAR_POWER_STATE_THREAD = "CarPowerStateThread";
    private static final int COMPLETED = 1;
    private static final int DELAYED_OFF_DISPLAY_BT = 1;
    private static final int DELAYED_OFF_DISPLAY_BT_MS = 3000;
    private static final int DELAY_MS = 250;
    private static final int DYNA_ALL_INDEX = 0;
    private static final String FACTORY_RESET_REBOOT_VALUE = "factory_reset_reboot";
    private static final int FAIL_RECONNECT_MESSAGE_DELAY = 2000;
    private static final int FAIL_RECONNECT_MESSAGE_WHAT = 1;
    private static final int HIDE_BLACK_VIEW = 6;
    private static final int INDEX_DYNA_SOUND_FIELD_FOCUS = 3;
    public static final String KEY_DISCLAIMER_DISPLAY = "KEY_DISCLAIMER_DISPLAY";
    private static final int MAX_NUM = 10;
    private static final int MCU_RESET_PARAM = 1;
    private static final int MSG_CONNECT_DISPLAY_HAL = 10;
    private static final int NO_AREA = 0;
    private static final String OTA_PERMISSION = "com.ts.power.permission.OTA_ACTION";
    private static final String REASON_SHUTDOWN = "shutdown";
    private static final String REBOOT_PERMISSION = "com.ts.power.permission.REBOOT";
    private static final int RECONNECT_DISPLAY_HAL_DELAY = 6000;
    private static final int RECONNECT_MESSAGE_WHAT = 0;
    private static final int REPORT_DELAY_MS = 6000;
    private static final int SET_DISPLAY_POWER_STATE_VHAL_OFF = 1;
    private static final int SET_DISPLAY_POWER_STATE_VHAL_ON = 0;
    private static final int SET_DISPLAY_POWER_TYPE_ACC_ALL = 2;
    private static final int SET_DISPLAY_POWER_TYPE_ACC_OFF = 0;
    private static final int SET_DISPLAY_POWER_TYPE_ACC_ON = 1;
    private static final int SHOW_BLACK_VIEW = 5;
    private static final int SHOW_WARNING_MSG_TEM = 3;
    private static final int SHOW_WARNING_MSG_VOL = 4;
    private static final String SYSTEM_PROPERTY_POWER = "sys.powerctl";
    private static final String SYS_BOOT_COMPLETED = "sys.boot_completed";
    private static final String SYS_BOOT_COMPLETED_NO_READY = "0";
    private static final String TAG = CarPowerBinder.class.getSimpleName();
    private static final int TIMER_DELAY_MS = 60000;
    private static final int TIME_INIT_UPDATE_AUDIO = 1000;
    private static final int UNCOMPLETED = 0;
    private static final int UPDATE_WORKMODE_STATE = 2;
    private static final int VALUE_AUDIO_FOCUS = -200;
    public static final int VALUE_DISCLAIMER_DISMISS = 0;
    public static final int VALUE_DISCLAIMER_SHOWING = 1;
    private static volatile CarPowerBinder sInstance;
    private AndroidCarManager mAndroidCarManager;
    private Handler mAudioHandler;
    private HandlerThread mAudioHandlerThread;
    private ExtAudioManager mAudioManager;
    private ExtBcallManager mBcallManager;
    private PowerBlackViewControler mBlackViewManager;
    private Car mCar;
    private CarAudioControl mCarAudioControl;
    private CarAudioManager mCarAudioManager;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private CarPowerManager mCarPowerManager;
    private CarVendorCarbodyManager mCarVendorCarbodyManager;
    private CarVendorPowerManager mCarVendorPowerManager;
    private Context mContext;
    private CarPowerWorkModeStatus mCurWorkModeStatus;
    private final DeviceOperator mDeviceOperator;
    private DisplayHalDeathRecipient mDisplayHalDeathRecipient;
    private DisplayInfo mDisplayInfo;
    private ExtEcallManager mEcallManager;
    private ExtCarPowerManager mExtPowerManager;
    private ExtHardKeyManager mHardKeyManager;
    private IDisplay mIDisplay;
    private IDisplayCallback mIDisplayCallback;
    private ExtLightsManager mLightsManager;
    private MainHandler mMainHandler;
    private PowerManager mPowerManager;
    private CarPowerSignalStatus mSignalStatus;
    private Handler mStateHandler;
    private HandlerThread mStateHandlerThread;
    private Timer mTimer;
    private Handler mTimerHandler;
    private HandlerThread mTimerHandlerThread;
    private TimerTaskShutdownPrepare mTimerTaskShutdownPrepare;
    private UsbManager mUsbManager;
    private WorkChangeHandler mWorkModeChangeHandler;
    private WorkModeState mWorkModeState;
    private int mTimerNum = 0;
    private Map<Integer, WorkModeState> mWorkModeStateList = new HashMap();
    private RemoteCallbackList<ICarPowerEventListener> mICarPowerEventListener = new RemoteCallbackList<>();
    private RemoteCallbackExecutor mRemoteCallbackExecutor = new RemoteCallbackExecutor(this.mICarPowerEventListener);
    private RemoteCallbackList<ICarPowerBindCarServiceListener> mICarPowerBindCarServiceListener = new RemoteCallbackList<>();
    private RemoteBindCarServiceCallbackExecutor mRemoteBindCarServiceCallbackExecutor = new RemoteBindCarServiceCallbackExecutor(this.mICarPowerBindCarServiceListener);
    private boolean mCarServiceConnected = false;
    private final Object mCarServiceConnectLock = new Object();
    private boolean mStartWithOnDisplayOff = false;
    private boolean mStartWithOnFull = false;
    private boolean mOtaState = false;
    private boolean mUpdateFlag = false;
    private final Object mOtaStateLock = new Object();
    public boolean mShutdownState = false;
    private int mHalIviBrightness = 0;
    private int mOrientation = -1;
    private int mAudioFocusState = VALUE_AUDIO_FOCUS;
    private BroadcastReceiver mDebugReceiver = new BroadcastReceiver() { // from class: com.ts.appservice.power.CarPowerBinder.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                int type = intent.getIntExtra("type", -1);
                int state = intent.getIntExtra("state", -1);
                CarPowerBinder.this.setDisplayPowerState(type, state);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };
    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() { // from class: com.ts.appservice.power.CarPowerBinder.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                LogUtil.warning(CarPowerBinder.TAG, "BroadcastReceiver ACTION_SCREEN_OFF");
                if (CarPowerBinder.this.mTimer != null && CarPowerBinder.this.mTimerTaskShutdownPrepare != null && CarPowerBinder.this.mTimerTaskShutdownPrepare.completableFuture != null) {
                    LogUtil.warning(CarPowerBinder.TAG, "mTimer.schedule ACTION_SCREEN_OFF");
                    CarPowerBinder.this.mTimer.schedule(CarPowerBinder.this.mTimerTaskShutdownPrepare, 2000L);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };
    Handler mReconnectCarServiceHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.appservice.power.CarPowerBinder.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                LogUtil.info(CarPowerBinder.TAG, "mReconnectCarServiceHandler reconnect");
                try {
                    if (CarPowerBinder.this.mCar == null) {
                        CarPowerBinder.this.initCarManagers();
                        return;
                    }
                    if (CarPowerBinder.this.mCar.isConnected()) {
                        CarPowerBinder.this.mCar.disconnect();
                    }
                    CarPowerBinder.this.mCar.connect();
                } catch (Exception exception) {
                    LogUtil.error(CarPowerBinder.TAG, exception);
                }
            }
        }
    };
    private CarConnectionCallback mServicesConn = new CarConnectionCallback() { // from class: com.ts.appservice.power.CarPowerBinder.4
        public void onConnected(Car car) {
            LogUtil.info(CarPowerBinder.TAG, "mCar onConnected !");
            try {
                if (car == null) {
                    LogUtil.error(CarPowerBinder.TAG, "car == null");
                    CarPowerBinder.this.reConnectCar();
                    return;
                }
                try {
                    CarPowerBinder.this.mCarVendorPowerManager = (CarVendorPowerManager) CarPowerBinder.this.mCar.getCarManager("power");
                    CarPowerBinder.this.mCarVendorCarbodyManager = (CarVendorCarbodyManager) CarPowerBinder.this.mCar.getCarManager("carbody");
                    if (CarPowerBinder.this.mCarVendorPowerManager != null && CarPowerBinder.this.mCarVendorCarbodyManager != null) {
                        CarPowerBinder.this.mCarVendorPowerManager.addCarPowerEventListener(CarPowerBinder.this.mCarPowerCallback);
                        CarPowerBinder.this.mCarVendorCarbodyManager.addCarCarbodyEventListener(CarPowerBinder.this.mCarbodyCallback);
                        CarPowerBinder.this.mRemoteBindCarServiceCallbackExecutor.onCarPowerBindCarServiceState(true);
                        LogUtil.info(CarPowerBinder.TAG, "bind mCar onServiceConnected synchronized start mCarServiceConnected : " + CarPowerBinder.this.mCarServiceConnected);
                        synchronized (CarPowerBinder.this.mCarServiceConnectLock) {
                            CarPowerBinder.this.mCarServiceConnected = true;
                        }
                        LogUtil.info(CarPowerBinder.TAG, "bind mCar onServiceConnected synchronized end mCarServiceConnected : " + CarPowerBinder.this.mCarServiceConnected);
                        CarPowerBinder.this.mEcallManager.initEcallstate();
                        CarPowerBinder.this.mBcallManager.initBcallstate();
                        CarPowerBinder.this.initWorkModeState();
                        CarPowerBinder.this.initSignalState();
                        return;
                    }
                    CarPowerBinder.this.reConnectCar();
                    LogUtil.error(CarPowerBinder.TAG, "Manager null return");
                } catch (Exception exception) {
                    LogUtil.error(CarPowerBinder.TAG, exception.getMessage());
                    CarPowerBinder.this.reConnectCar();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void onDisconnected(Car car) {
            LogUtil.info(CarPowerBinder.TAG, "mCar onDisconnected !");
            CarPowerBinder.this.mCar = null;
            LogUtil.info(CarPowerBinder.TAG, "onServiceDisconnected synchronized start mCar : " + CarPowerBinder.this.mCarServiceConnected);
            synchronized (CarPowerBinder.this.mCarServiceConnectLock) {
                CarPowerBinder.this.mCarServiceConnected = false;
            }
            CarPowerBinder.this.mCarVendorPowerManager = null;
            CarPowerBinder.this.mCarVendorCarbodyManager = null;
            if (CarPowerBinder.this.mRemoteBindCarServiceCallbackExecutor.isConnect()) {
                CarPowerBinder.this.mRemoteBindCarServiceCallbackExecutor.onCarPowerBindCarServiceState(false);
            }
            LogUtil.info(CarPowerBinder.TAG, "onServiceDisconnected synchronized end mCar : " + CarPowerBinder.this.mCarServiceConnected);
            CarPowerBinder.this.reConnectCar();
        }
    };
    private final CarVendorCarbodyManager.OnCarCarbodyEventListener mCarbodyCallback = new CarVendorCarbodyManager.OnCarCarbodyEventListener() { // from class: com.ts.appservice.power.CarPowerBinder.5
        public void onCarbodyEventChange(CarbodyEventChangeValue carbodyEventChangeValue) {
            if (carbodyEventChangeValue == null) {
                LogUtil.error(CarPowerBinder.TAG, "onCarbodyEventChange carbodyEventChangeValue is null !");
                return;
            }
            int propId = carbodyEventChangeValue.getPropertyId();
            if (propId == 557862927) {
                CarPowerBinder.this.powerSignalChange(propId, carbodyEventChangeValue.getPowerLevel());
            }
        }

        public void onCarbodyErrorEvent(int propertyId, int zone) {
        }
    };
    private final CarVendorPowerManager.OnCarPowerEventListener mCarPowerCallback = new CarVendorPowerManager.OnCarPowerEventListener() { // from class: com.ts.appservice.power.CarPowerBinder.6
        public void onPowerEventChange(PowerEventChangeValue powerEventChangeValue) {
            if (powerEventChangeValue == null) {
                LogUtil.error(CarPowerBinder.TAG, "onPowerEventChange powerEventChangeValue is null !");
                return;
            }
            int propId = powerEventChangeValue.getPropertyId();
            if (propId == 289475072) {
                int[] powerstate = powerEventChangeValue.getIviPowerStateChanged();
                CarPowerBinder.this.handlerShutdownMes(powerstate);
                return;
            }
            if (propId == 557969412) {
                int[] state = powerEventChangeValue.getIviPowerModeChanged();
                if (!CarPowerBinder.isWorkEnumContains(state[0])) {
                    LogUtil.warning(CarPowerBinder.TAG, "Illegal Car Power Workmode state :" + state[0]);
                    state[0] = CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_OFF.getVal();
                }
                CarPowerBinder.this.workModeChange(propId, Integer.valueOf(state[0]));
            }
        }

        public void onPowerErrorEvent(int propertyId, int zone) {
        }
    };

    static /* synthetic */ int access$4012(CarPowerBinder x0, int x1) {
        int i = x0.mTimerNum + x1;
        x0.mTimerNum = i;
        return i;
    }

    public void forceStopThirdApp() {
        PackageManager packageManager = this.mContext.getPackageManager();
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfoList) {
            if ((packageInfo.applicationInfo.flags & 1) == 0 && (packageInfo.applicationInfo.flags & 128) == 0 && (2097152 & packageInfo.applicationInfo.flags) == 0) {
                LogUtil.info(TAG, "ThirdApp running packagename : " + packageInfo.packageName);
                try {
                    activityManager.forceStopPackage(packageInfo.packageName);
                    LogUtil.info(TAG, "forceStopPackage ThirdApp packagename : " + packageInfo.packageName);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private WorkModeState createWorkMode(Integer workMode) {
        LogUtil.info(TAG, "createWorkMode workMode : " + workMode);
        WorkModeState workModeState = null;
        if (this.mWorkModeStateList.containsKey(workMode)) {
            WorkModeState workModeState2 = this.mWorkModeStateList.get(workMode);
            return workModeState2;
        }
        int iIntValue = workMode.intValue();
        if (iIntValue == 0) {
            workModeState = new CarPowerOnOffState(this);
        } else if (iIntValue == 1) {
            workModeState = new CarPowerOnPreStartUpState(this);
        } else if (iIntValue == 2) {
            workModeState = new CarPowerOnStartUpState(this);
        } else if (iIntValue == 3) {
            workModeState = new CarPowerOnDegradedState(this);
        } else if (iIntValue == 4) {
            workModeState = new CarPowerOnStandbyState(this);
        } else if (iIntValue == 5) {
            workModeState = new CarPowerOnStrState(this);
        } else if (iIntValue == 8) {
            workModeState = new CarPowerOnSleepState(this);
        } else if (iIntValue == 10) {
            workModeState = new CarPowerOnNormalState(this);
        }
        if (workModeState != null) {
            this.mWorkModeStateList.put(workMode, workModeState);
        }
        return workModeState;
    }

    public static CarPowerBinder getInstance(Context context) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (CarPowerBinder.class) {
                if (sInstance == null) {
                    sInstance = new CarPowerBinder(context);
                }
            }
        }
        return sInstance;
    }

    public CarPowerBinder(Context context) {
        LogUtil.info(TAG, "CarPowerBinder");
        this.mContext = context;
        this.mDisplayInfo = new DisplayInfo();
        CarPowerWorkModeStatus carPowerWorkModeStatus = new CarPowerWorkModeStatus();
        this.mCurWorkModeStatus = carPowerWorkModeStatus;
        carPowerWorkModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_OFF);
        CarPowerSignalStatus carPowerSignalStatus = new CarPowerSignalStatus();
        this.mSignalStatus = carPowerSignalStatus;
        carPowerSignalStatus.setCarPowerSignalStatusEnum(CarPowerSignalStatus.CarPowerSignalStatusEnum.BODYWORK_POWER_LEVEL_INVALID);
        initStateHandler();
        initAudioHandler();
        initManager();
        initWorkModeChangeHandler();
        initCarManagers();
        initDisplayhal();
        LogUtil.info(TAG, "POWER_MESSAGES_CAN_ScPwDomainStateChange_MSG0");
        LogUtil.info(TAG, "POWER_MESSAGES_CAN_ScPwKeySystemAwake_MSG0");
        this.mDeviceOperator = new DeviceOperator();
        registerDisplayReceiver();
        initTest();
    }

    private void initWorkModeChangeHandler() {
        HandlerThread change = new HandlerThread("workModeChange");
        change.start();
        this.mWorkModeChangeHandler = new WorkChangeHandler(this, change.getLooper());
    }

    private void initTest() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ts.power.debug_display");
        this.mContext.registerReceiver(this.mDebugReceiver, intentFilter);
    }

    private void registerDisplayReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.mContext.registerReceiver(this.mScreenOffReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDisplayhal() {
        IDisplay displayHal = getDisplayHal();
        this.mIDisplay = displayHal;
        if (displayHal == null) {
            LogUtil.warning(TAG, "Display HAL is not available and try to reconnect every one min!");
            if (this.mStateHandler.hasMessages(10)) {
                this.mStateHandler.removeMessages(10);
            }
            this.mStateHandler.sendEmptyMessageDelayed(10, 6000L);
            return;
        }
        this.mDisplayHalDeathRecipient = new DisplayHalDeathRecipient();
        DisplayHALCallback displayHALCallback = new DisplayHALCallback();
        this.mIDisplayCallback = displayHALCallback;
        try {
            this.mIDisplay.setCallback(displayHALCallback);
            this.mIDisplay.linkToDeath(this.mDisplayHalDeathRecipient, 0L);
            this.mIDisplay.getDisplayInfo(1);
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
    }

    private class DisplayHALCallback extends IDisplayCallback.Stub {
        private DisplayHALCallback() {
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayBrightnessChange(int displayPanelId, int brightnessLevel) {
            CarPowerBinder.this.handlerDisplayEvent(1, displayPanelId, brightnessLevel);
            if (displayPanelId == 1) {
                CarPowerBinder.this.mHalIviBrightness = brightnessLevel;
                if (CarPowerBinder.this.mMainHandler.hasMessages(1)) {
                    CarPowerBinder.this.mMainHandler.removeMessages(1);
                    CarPowerBinder.this.mMainHandler.removeMessages(5);
                    Message message = CarPowerBinder.this.mMainHandler.obtainMessage(5);
                    message.arg1 = brightnessLevel;
                    CarPowerBinder.this.mMainHandler.sendMessage(message);
                    return;
                }
                if (!CarPowerBinder.this.mLightsManager.getIsSyncDefaultBrightness() && !CarPowerBinder.this.mMainHandler.hasMessages(8)) {
                    Message message2 = CarPowerBinder.this.mMainHandler.obtainMessage(8);
                    message2.arg1 = brightnessLevel;
                    CarPowerBinder.this.mMainHandler.sendMessage(message2);
                }
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayPowerStateChange(int displayPanelId, int powerState) {
            if (displayPanelId != 1) {
                CarPowerBinder.this.handlerDisplayEvent(2, displayPanelId, powerState);
                return;
            }
            if (powerState == 0) {
                powerState = 2;
            } else if (powerState == 1) {
                powerState = 1;
            }
            CarPowerBinder.this.mDisplayInfo.iviBackLightState = Integer.valueOf(powerState);
            LogUtil.warning(CarPowerBinder.TAG, "IVI displayPowerStateChange:" + CarPowerBinder.this.mDisplayInfo.iviBackLightState);
            CarPowerBinder.this.mMainHandler.updateUnTouchView();
            CarPowerBinder.this.handlerDisplayEvent(2, displayPanelId, powerState);
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayHeightChange(int displayPanelId, int heightLevel) {
            CarPowerBinder.this.handlerDisplayEvent(3, displayPanelId, heightLevel);
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayRotationChange(int displayPanelId, int rotateLevel) {
            CarPowerBinder.this.handlerDisplayEvent(4, displayPanelId, rotateLevel);
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayContentChange(int displayPanelId, int content) {
            CarPowerBinder.this.handlerDisplayEvent(5, displayPanelId, content);
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayDayNightModeChange(int mode) {
            LogUtil.info(CarPowerBinder.TAG, "displayDayNightModeChange mode : " + mode);
            CarPowerBinder.this.mMainHandler.removeMessages(1);
            CarPowerBinder.this.mMainHandler.removeMessages(5);
            CarPowerBinder.this.mMainHandler.sendEmptyMessageDelayed(1, 2000L);
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayForwardLinkMarginChange(int displayPanelId, int value) {
        }

        @Override // vendor.bosch.display.V1_0.IDisplayCallback
        public void displayReverseLinkMarginChange(int displayPanelId, int value) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerDisplayEvent(int type, int displayPanelId, int value) {
        LogUtil.info(TAG, "handlerDisplayEvent type : " + type + " displayPanelId : " + displayPanelId + " value : " + value);
        this.mRemoteCallbackExecutor.onCarPowerDisplayEvent(type, displayPanelId, value);
    }

    private class DisplayHalDeathRecipient implements IHwBinder.DeathRecipient {
        private DisplayHalDeathRecipient() {
        }

        public void serviceDied(long cookie) {
            try {
                LogUtil.warning(CarPowerBinder.TAG, "Display hal died !");
                CarPowerBinder.this.mIDisplay.unlinkToDeath(CarPowerBinder.this.mDisplayHalDeathRecipient);
                CarPowerBinder.this.mIDisplay = null;
                if (CarPowerBinder.this.mStateHandler.hasMessages(10)) {
                    CarPowerBinder.this.mStateHandler.removeMessages(10);
                }
                CarPowerBinder.this.mStateHandler.sendEmptyMessageDelayed(10, 6000L);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    private IDisplay getDisplayHal() {
        try {
            return IDisplay.getService();
        } catch (RemoteException exception) {
            LogUtil.warning(TAG, "Failed to get Display Hal !");
            exception.printStackTrace();
            return null;
        }
    }

    private void initManager() {
        LogUtil.info(TAG, "initManager");
        this.mExtPowerManager = ExtCarPowerManager.getInstance(this.mContext, this);
        this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        this.mUsbManager = UsbManager.getInstance(this.mContext);
        this.mBlackViewManager = PowerBlackViewControler.getInstance(this.mContext, this);
        this.mLightsManager = ExtLightsManager.getInstance(this.mContext, this);
        this.mHardKeyManager = ExtHardKeyManager.getInstance(this.mContext, this);
        this.mEcallManager = ExtEcallManager.getInstance(this.mContext, this);
        this.mBcallManager = ExtBcallManager.getInstance(this.mContext, this);
        this.mAudioManager = ExtAudioManager.getInstance(this.mContext, this.mAudioHandler);
        this.mAndroidCarManager = new AndroidCarManager(this.mContext, this);
        this.mCarPlayRemoteManager = CarPlayRemoteManager.getInstance(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCarManagers() {
        LogUtil.info(TAG, "init mCar");
        Car carCreateCar = Car.createCar(this.mContext, this.mServicesConn);
        this.mCar = carCreateCar;
        if (carCreateCar == null) {
            LogUtil.error(TAG, "mCar is null ! initCarManagers failed !");
        } else {
            carCreateCar.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reConnectCar() {
        boolean isSuccess = false;
        if (!this.mReconnectCarServiceHandler.hasMessages(1)) {
            this.mReconnectCarServiceHandler.sendEmptyMessageDelayed(1, 2000L);
            isSuccess = true;
        }
        LogUtil.info(TAG, "reConnectCar isSuccess ： " + isSuccess);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerShutdownMes(int[] state) {
        LogUtil.info(TAG, "handlerShutdownMes power_state : " + state[0] + "ShutdownParam : " + state[1]);
        int i = state[1];
    }

    private void initStateHandler() {
        LogUtil.info(TAG, "initStateHandler");
        MainHandler mainHandler = new MainHandler(this, Looper.getMainLooper());
        this.mMainHandler = mainHandler;
        mainHandler.sendEmptyMessageDelayed(3, 7000L);
        HandlerThread handlerThread = new HandlerThread(CAR_POWER_STATE_THREAD);
        this.mStateHandlerThread = handlerThread;
        handlerThread.start();
        this.mStateHandler = new Handler(this.mStateHandlerThread.getLooper()) { // from class: com.ts.appservice.power.CarPowerBinder.7
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtil.info(CarPowerBinder.TAG, "handleMessage what : " + msg.what);
                int i = msg.what;
                if (i == 2) {
                    if (CarPowerBinder.this.mWorkModeState != null) {
                        CarPowerBinder.this.mWorkModeState.changeState();
                    }
                } else {
                    if (i == 3 || i == 4) {
                        PowerViewControler.getInstance(CarPowerBinder.this.mContext).showPowerAbnormalView(CarPowerBinder.this.mContext, msg.what);
                        return;
                    }
                    if (i == 10) {
                        CarPowerBinder.this.initDisplayhal();
                    } else if (i == 30) {
                        try {
                            CarPowerBinder.this.setBrightnessState(msg.arg1, msg.arg2);
                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }
                    }
                }
            }
        };
        LogUtil.info(TAG, "initTimerHandlerThread");
        HandlerThread handlerThread2 = new HandlerThread("workmode test thread");
        this.mTimerHandlerThread = handlerThread2;
        handlerThread2.start();
        Handler handler = new Handler(this.mTimerHandlerThread.getLooper()) { // from class: com.ts.appservice.power.CarPowerBinder.8
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtil.info(CarPowerBinder.TAG, "mTimerHandler handleMessage what : " + msg.what);
                if (msg.what == 0) {
                    LogUtil.info(CarPowerBinder.TAG, "TimerHandlerThread loop sendEmptyMessageDelayed 1min later");
                    sendEmptyMessageDelayed(0, 60000L);
                }
            }
        };
        this.mTimerHandler = handler;
        handler.sendEmptyMessageDelayed(0, 60000L);
    }

    private void initAudioHandler() {
        LogUtil.info(TAG, "initAudioHandler");
        HandlerThread handlerThread = new HandlerThread("CarPowerAudioHandler");
        this.mAudioHandlerThread = handlerThread;
        handlerThread.start();
        this.mAudioHandler = new Handler(this.mAudioHandlerThread.getLooper());
    }

    private void quit() {
        LogUtil.info(TAG, "quit");
        if (this.mStateHandlerThread != null) {
            this.mStateHandler.removeMessages(1);
            this.mStateHandlerThread.quitSafely();
            this.mStateHandlerThread = null;
        }
        HandlerThread handlerThread = this.mAudioHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mAudioHandlerThread = null;
        }
    }

    public void initWorkModeState() {
        LogUtil.info(TAG, "initWorkModeState");
        try {
            int state = getCurrentWorkMode().getCarPowerWorkModeStatusEnum().getVal();
            LogUtil.info(TAG, "initWorkModeState getGlobalProperty CAR_POWER_WORKMODE_STATE : " + state);
            workModeChange(0, Integer.valueOf(state));
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
    }

    public void initSignalState() {
        LogUtil.info(TAG, "initSignalState");
        try {
            int state = getCurrentSignalState().getCarPowerSignalStatusEnum().getVal();
            LogUtil.info(TAG, "initSignalState getGlobalProperty CAR_POWER_SINGNAL_STATE : " + state);
            powerSignalChange(CarPowerServiceConst.POWER_LEVEL, state);
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
    }

    public void workModeChangeToShutdown() {
        LogUtil.info(TAG, "workModeChangeToShutdown");
        if (CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON == getCurrentWorkMode().getCarPowerWorkModeStatusEnum()) {
            LogUtil.info(TAG, "workModeChangeToShutdown workModeChange to sleep");
            this.mShutdownState = true;
            workModeChange(0, 4);
        }
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$VppbUOoeeC9NUwjvVSFbtAG4cw4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$workModeChangeToShutdown$0$CarPowerBinder();
            }
        });
        sendShutdownPendingMessage();
    }

    public /* synthetic */ void lambda$workModeChangeToShutdown$0$CarPowerBinder() {
        LogUtil.info(TAG, "workModeChangeToShutdown set USB state DISABLE");
        setUsbState(1, "0");
        setUsbState(2, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void workModeChange(int propId, Integer state) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = propId;
        obtain.arg2 = state.intValue();
        WorkChangeHandler workChangeHandler = this.mWorkModeChangeHandler;
        if (workChangeHandler != null) {
            workChangeHandler.sendMessage(obtain);
        } else {
            LogUtil.error(TAG, "mWorkModeChangeHandler == NULL");
        }
    }

    public void onStateChanged(int state, CompletableFuture<Void> completableFuture) {
        Log.i(TAG, " onStateChanged：" + state);
        CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum statusEnum = CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.getStrStatusEnum(state);
        if (statusEnum != null) {
            CarPowerWorkModeStatus mode = new CarPowerWorkModeStatus();
            mode.setCarPowerWorkModeStatusEnum(statusEnum);
            this.mRemoteCallbackExecutor.onCarPowerWorkModeChangeEvent(mode);
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
        }
        TimerTaskShutdownPrepare timerTaskShutdownPrepare = this.mTimerTaskShutdownPrepare;
        if (timerTaskShutdownPrepare != null) {
            timerTaskShutdownPrepare.completableFuture = null;
            this.mTimerTaskShutdownPrepare.weakReference = null;
        }
        if (state == 3) {
            Log.i(TAG, " SUSPEND_EXIT");
            this.mWorkModeChangeHandler.obtainMessage(3).sendToTarget();
        } else if (state == 7 && completableFuture != null) {
            Log.i(TAG, " SHUTDOWN_PREPARE");
            this.mWorkModeChangeHandler.obtainMessage(2).sendToTarget();
            this.mTimer = new Timer();
            TimerTaskShutdownPrepare timerTaskShutdownPrepare2 = new TimerTaskShutdownPrepare(this);
            this.mTimerTaskShutdownPrepare = timerTaskShutdownPrepare2;
            timerTaskShutdownPrepare2.completableFuture = completableFuture;
        }
    }

    private static class TimerTaskShutdownPrepare extends TimerTask {
        public static final int TIME_DELAY = 2000;
        private CompletableFuture<Void> completableFuture;
        private WeakReference<CarPowerBinder> weakReference;

        public TimerTaskShutdownPrepare(CarPowerBinder binder) {
            this.weakReference = new WeakReference<>(binder);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CarPowerBinder binder = this.weakReference.get();
            if (binder != null && this.completableFuture != null) {
                try {
                    LogUtil.warning(CarPowerBinder.TAG, "completableFuture.complete");
                    this.completableFuture.complete(null);
                    this.completableFuture = null;
                } catch (Exception exc) {
                    LogUtil.error(CarPowerBinder.TAG, Log.getStackTraceString(exc));
                }
            }
        }
    }

    private void checkAndAbandonAudio() {
        if (this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() == 10) {
            LogUtil.warning(TAG, "checkAndAbandonAudio");
            this.mWorkModeChangeHandler.removeAccOffRequestAudioFocus();
            this.mAudioManager.abandonAudioFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void workChange(int propId, Integer state) {
        LogUtil.warning(TAG, "workChange propId : " + tenToHex(propId) + " state : " + state);
        if (getCurrentWorkMode().getCarPowerWorkModeStatusEnum().getVal() == state.intValue()) {
            LogUtil.info(TAG, "workChange : state has not change !");
            return;
        }
        int oldState = getCurrentWorkMode().getCarPowerWorkModeStatusEnum().getVal();
        CarPowerWorkModeStatus workModeStatus = new CarPowerWorkModeStatus();
        if (propId == 557969412) {
            switch (state.intValue()) {
                case 0:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_OFF);
                    break;
                case 1:
                    if (!this.mStartWithOnFull) {
                        this.mStartWithOnDisplayOff = true;
                        LogUtil.info(TAG, "workChange display off set mStartWithOnDisplayOff = true");
                    } else {
                        this.mStartWithOnDisplayOff = false;
                        LogUtil.info(TAG, "workChange display off set mStartWithOnDisplayOff = false");
                    }
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_PRE_STARTUP);
                    break;
                case 2:
                    LogUtil.info(TAG, "workChange display on full set mStartWithOnFull = true mStartWithOnDisplayOff = false");
                    this.mStartWithOnFull = true;
                    this.mStartWithOnDisplayOff = false;
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STARTUP);
                    break;
                case 3:
                    LogUtil.info(TAG, "workChange shutdown prepare");
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DEGRADED);
                    break;
                case 4:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STANDBY);
                    break;
                case 5:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STR);
                    break;
                case 6:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_REFLASH);
                    break;
                case 7:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_REMOTE_FOTA);
                    break;
                case 8:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_SLEEP);
                    break;
                case 9:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STR_SUSPENDING);
                    break;
                case 10:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON);
                    break;
                case 11:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_HALF_HOUR_MODE);
                    break;
                case 12:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_TOD);
                    break;
                default:
                    workModeStatus.setCarPowerWorkModeStatusEnum(CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_TOD);
                    break;
            }
            if (workModeStatus.getCarPowerWorkModeStatusEnum() != getCurrentWorkMode().getCarPowerWorkModeStatusEnum()) {
                LogUtil.info(TAG, "workChange current workmode is changed");
                this.mCurWorkModeStatus = workModeStatus;
                this.mRemoteCallbackExecutor.onCarPowerWorkModeChangeEvent(workModeStatus);
                this.mWorkModeState = createWorkMode(state);
                this.mStateHandler.sendEmptyMessage(2);
            }
            try {
                this.mDeviceOperator.stateChange(oldState, state.intValue());
                updateAudioPowerState();
            } catch (Exception exception) {
                LogUtil.error(TAG, "mDeviceOperator stateChange " + exception.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void powerSignalChange(int propId, int state) {
        LogUtil.info(TAG, "powerSignalChange propId : " + tenToHex(propId) + " state : " + state);
        CarPowerSignalStatus.CarPowerSignalStatusEnum lastCarPowerSignalStatusEnum = this.mSignalStatus.getCarPowerSignalStatusEnum();
        if (propId == 557862927) {
            if (state == 1) {
                LogUtil.info(TAG, "powerSignalChange propId : " + tenToHex(propId) + " Signal OFF : " + state);
                this.mSignalStatus.setCarPowerSignalStatusEnum(CarPowerSignalStatus.CarPowerSignalStatusEnum.BODYWORK_POWER_LEVEL_OFF);
                this.mWorkModeChangeHandler.sendAccOffRequestAudioFocus();
            } else if (state == 2) {
                LogUtil.info(TAG, "powerSignalChange propId : " + tenToHex(propId) + " Signal ACC : " + state);
                this.mWorkModeChangeHandler.removeAccOffRequestAudioFocus();
                this.mSignalStatus.setCarPowerSignalStatusEnum(CarPowerSignalStatus.CarPowerSignalStatusEnum.BODYWORK_POWER_LEVEL_ACC);
            } else if (state == 3) {
                LogUtil.info(TAG, "powerSignalChange propId : " + tenToHex(propId) + " Signal ON : " + state);
                this.mWorkModeChangeHandler.removeAccOffRequestAudioFocus();
                this.mSignalStatus.setCarPowerSignalStatusEnum(CarPowerSignalStatus.CarPowerSignalStatusEnum.BODYWORK_POWER_LEVEL_ON);
                checkAndAbandonAudio();
            } else {
                LogUtil.info(TAG, "powerSignalChange propId : " + tenToHex(propId) + " Signal UNKNOW : " + state);
                this.mSignalStatus.setCarPowerSignalStatusEnum(CarPowerSignalStatus.CarPowerSignalStatusEnum.BODYWORK_POWER_LEVEL_INVALID);
            }
        }
        if (lastCarPowerSignalStatusEnum != getCurrentSignalState().getCarPowerSignalStatusEnum()) {
            LogUtil.info(TAG, "powerSignalChange current PowerSignal is changed");
            this.mRemoteCallbackExecutor.onCarPowerSignalChangeEvent(this.mSignalStatus);
        }
    }

    private void abnormalStateChange(final int propId, final int value) {
        LogUtil.info(TAG, "abnormalStateChange propId : " + propId + " value : " + value);
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$pwuXkwmDk2Tj1mHHu1k09W9D8G0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$abnormalStateChange$1$CarPowerBinder(propId, value);
            }
        });
    }

    public /* synthetic */ void lambda$abnormalStateChange$1$CarPowerBinder(int propId, int value) {
        this.mRemoteCallbackExecutor.onCarPowerEvent(propId, value, new CarPowerData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWorkEnumContains(int state) {
        for (CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum n : CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.values()) {
            if (n.getVal() == state) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSingleEnumContains(int state) {
        for (CarPowerSignalStatus.CarPowerSignalStatusEnum n : CarPowerSignalStatus.CarPowerSignalStatusEnum.values()) {
            if (n.getVal() == state) {
                return true;
            }
        }
        return false;
    }

    private void warningMsgChange(int propId, int value) {
        LogUtil.info(TAG, "warningMsgChange propId : " + tenToHex(propId) + " value : " + value);
        switch (propId) {
            case CarPowerServiceConst.CAR_TEMPERATURE_WARING_MSG /* 557843601 */:
                if (value == 0) {
                    this.mStateHandler.removeMessages(3);
                    this.mStateHandler.sendEmptyMessage(3);
                }
                break;
            case CarPowerServiceConst.CAR_VOLTAGE_WARING_MSG /* 557843602 */:
                if (value == 0) {
                    this.mStateHandler.removeMessages(4);
                    this.mStateHandler.sendEmptyMessage(4);
                }
                break;
        }
    }

    public int getAbnormalState(int type) {
        LogUtil.info(TAG, "getAbnormalState type : " + type);
        try {
            LogUtil.info(TAG, "getAbnormalState abnormalState : -1");
            return -1;
        } catch (Exception exc) {
            LogUtil.error(TAG, Log.getStackTraceString(exc));
            return -1;
        }
    }

    public int getDarkNightDayModeState() {
        logInfo("getDarkNightDayModeState");
        try {
            LogUtil.info(TAG, "getDarkNightDayModeState darkNightDayModeState : -1");
            return -1;
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
            logInfo("getDarkNightDayModeState :-1");
            return -1;
        }
    }

    public int getModuleAbnormalState(int type) {
        logInfo("getModuleAbnormalState type : " + type);
        int abnormalState = -1;
        switch (type) {
            case -2147483646:
                abnormalState = getAbnormalState(0);
                break;
            case -2147483645:
                abnormalState = getAbnormalState(1);
                break;
            case -2147483644:
                abnormalState = getAbnormalState(2);
                break;
            case -2147483643:
                abnormalState = getAbnormalState(3);
                break;
            case -2147483642:
                abnormalState = getAbnormalState(4);
                break;
            default:
                LogUtil.error(TAG, "getModuleAbnormalState error type : " + type);
                break;
        }
        logInfo("getModuleAbnormalState abnormalState : " + abnormalState);
        return abnormalState;
    }

    public void registerCarPowerEventListener(ICarPowerEventListener iCarPowerEventListener) {
        logInfo("registerCarPowerEventListener :" + iCarPowerEventListener);
        if (iCarPowerEventListener != null) {
            this.mICarPowerEventListener.register(iCarPowerEventListener);
        }
    }

    public void unregisterCarPowerEventListener(ICarPowerEventListener iCarPowerEventListener) {
        logInfo("unregisterCarPowerEventListener :" + iCarPowerEventListener);
        if (iCarPowerEventListener != null) {
            this.mICarPowerEventListener.unregister(iCarPowerEventListener);
        }
    }

    public CarPowerWorkModeStatus getCurrentWorkMode() {
        logInfo("getCurrentWorkMode :" + this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum());
        return this.mCurWorkModeStatus;
    }

    public CarPowerSignalStatus getCurrentSignalState() {
        logInfo("getCurrentSignalState :" + this.mSignalStatus.getCarPowerSignalStatusEnum());
        return this.mSignalStatus;
    }

    public int getBrightness(int type) throws RemoteException {
        logInfo("getBrightness  type" + type);
        int brightness = this.mLightsManager.getBrightness(type);
        logInfo("getBrightness  type" + type + " brightness:" + brightness);
        return brightness;
    }

    public boolean setBrightness(int type, int brightness) throws RemoteException {
        logInfo("setBrightness  type" + type + " brightness:" + brightness);
        this.mLightsManager.setBrightness(type, brightness);
        return true;
    }

    public int getBrightnessState(int type) throws RemoteException {
        logInfo("getBrightnessState  type" + type);
        int brightnessState = this.mLightsManager.getBrightnessState(type);
        logInfo("getBrightnessState  type" + type + " brightnessState:" + brightnessState);
        return brightnessState;
    }

    public int setBrightnessState(int type, int state) throws RemoteException {
        logInfo("setBrightnessState  type" + type + " state:" + state);
        if (getCurrentWorkMode().getCarPowerWorkModeStatusEnum() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STR_SUSPENDING || getCurrentWorkMode().getCarPowerWorkModeStatusEnum() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STANDBY) {
            int perm = this.mContext.checkCallingOrSelfPermission("com.ts.power.permission.BRIGHTNESS");
            LogUtil.info(TAG, "setBrightnessState standby state perm : " + perm);
            if (perm == 0) {
                LogUtil.info(TAG, "setBrightnessState standby state perm granted");
            } else {
                LogUtil.info(TAG, "Denied permission Brightness");
                throw new SecurityException("Denied permission Brightness");
            }
        }
        boolean bool = this.mLightsManager.setBrightnessState(type, state);
        LogUtil.info(TAG, "setBrightnessState bool : " + bool);
        if (bool) {
            handlerBlackView(type, state);
            return this.mLightsManager.getCurDisplayState();
        }
        return -1;
    }

    public void setBrightnessStateDelay(int type, int state) {
        Message msg = new Message();
        msg.what = 30;
        msg.arg1 = type;
        msg.arg2 = state;
        this.mStateHandler.sendMessageDelayed(msg, 500L);
    }

    private void handlerBlackView(int type, int state) {
        CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum carPowerWorkModeStatusEnum = getCurrentWorkMode().getCarPowerWorkModeStatusEnum();
        if (state == 0 && type == 1) {
            if (carPowerWorkModeStatusEnum == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON) {
                this.mStateHandler.removeMessages(6);
                this.mStateHandler.sendEmptyMessage(6);
                return;
            }
            return;
        }
        if (state == 1 && type == 1 && carPowerWorkModeStatusEnum == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON) {
            this.mStateHandler.removeMessages(5);
            this.mStateHandler.sendEmptyMessage(5);
        }
    }

    public void setDisplayState(int state) {
        this.mDisplayInfo.powerDisplayState = state;
        this.mMainHandler.updateUnTouchView();
        LogUtil.warning(TAG, "setDisplayState state : " + state + "mOtaState:" + this.mOtaState + "mUpdateFlag:" + this.mUpdateFlag);
        if (getCurrentWorkMode().getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STANDBY.getVal() && (this.mOtaState || this.mUpdateFlag)) {
            sendViewMessage(6);
            this.mLightsManager.setCurDisplayState(0);
            LogUtil.info(TAG, "IVI_PM_STANDBY state : " + state + "mOtaState:" + this.mOtaState + "mUpdateFlag:" + this.mUpdateFlag);
            return;
        }
        if (state == 0) {
            sendViewMessage(6);
            this.mLightsManager.setCurDisplayState(0);
        } else if (state == 1) {
            if (!isOtaWorking() && !this.mBcallManager.getIsBcallWorking() && !this.mEcallManager.getIsEcallWorking()) {
                sendViewMessage(5);
                this.mLightsManager.setCurDisplayState(1);
            } else {
                LogUtil.info(TAG, "setDisplayState OFF OTA or EBCall working return");
                return;
            }
        }
        boolean bool = this.mLightsManager.setBrightnessState(0, state);
        LogUtil.info(TAG, "setDisplayState bool : " + bool);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mOrientation != newConfig.orientation) {
            LogUtil.info(TAG, "orientation changed: " + newConfig.orientation);
            this.mOrientation = newConfig.orientation;
            try {
                this.mBlackViewManager.updateParams();
            } catch (Exception exception) {
                LogUtil.error(TAG, "updateParams: " + exception.getMessage());
            }
        }
    }

    private void sendViewMessage(int message) {
        this.mStateHandler.removeMessages(message);
        this.mStateHandler.sendEmptyMessage(message);
    }

    public void setAudioState(int state) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioPowerState() {
        if (this.mCarAudioControl == null) {
            LogUtil.error(TAG, "mCarAudioControl is null !");
            return;
        }
        try {
            LogUtil.warning(TAG, "updateAudioPowerState : " + this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() + " acc " + this.mSignalStatus.getCarPowerSignalStatusEnum().getVal());
            this.mCarAudioControl.setIntegerValue(15, this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
            if (CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON.getVal() == this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal()) {
                int result = setAllMute(0);
                LogUtil.warning(TAG, "setAllMute  result: " + result);
            }
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
    }

    public void setHardKeyState(int state) {
        LogUtil.info(TAG, "setHardKeyState state : " + state);
        int curHardKeyState = this.mHardKeyManager.getCurHardKeyState();
        LogUtil.info(TAG, "setHardKeyState curHardKeyState : " + curHardKeyState);
        if (state == 1) {
            if (curHardKeyState != 1) {
                this.mHardKeyManager.enableHardKeyEvent();
            }
        } else if (state == 0) {
            if (curHardKeyState != 0) {
                this.mHardKeyManager.disableHardKeyEvent();
            }
        } else {
            LogUtil.info(TAG, "setHardKeyState warning state : " + state);
        }
    }

    public void setUsbState(int type, String state) {
        LogUtil.info(TAG, "setUsbState type : " + type + " state : " + state);
        this.mUsbManager.setUsbState(type, state);
        this.mUsbManager.getUsbState(type);
    }

    public void sendShutdownPendingMessage() {
        LogUtil.info(TAG, "sendShutdownPendingMessage");
        this.mTimerNum = 0;
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
        }
        this.mTimer = new Timer();
        TimerTask timerTask = new TimerTask() { // from class: com.ts.appservice.power.CarPowerBinder.9
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogUtil.info(CarPowerBinder.TAG, "mTimerTask send shutdown pending mTimerNum : " + CarPowerBinder.this.mTimerNum);
                try {
                    if (CarPowerBinder.this.mTimerNum < 10) {
                        LogUtil.info(CarPowerBinder.TAG, "mTimerTask send shutdown pending SHUTDOWNCONFIRM_SHUTDOWN_PENDING_STATE : 1");
                        CarPowerBinder.this.mExtPowerManager.sendShutdownConfirmMessage(1);
                        CarPowerBinder.access$4012(CarPowerBinder.this, 1);
                    } else {
                        CarPowerBinder.this.sendShutdownConfirmMessage();
                    }
                } catch (Exception exc) {
                    LogUtil.info(CarPowerBinder.TAG, Log.getStackTraceString(exc));
                }
            }
        };
        this.mTimer.schedule(timerTask, 0L, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendShutdownConfirmMessage() {
        LogUtil.info(TAG, "sendShutdownConfirmMessage");
        this.mTimer.cancel();
        this.mTimer.purge();
        this.mTimer = null;
        try {
            this.mExtPowerManager.sendShutdownConfirmMessage(2);
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
        shutdown();
    }

    public void sendCarPowerReadyMessage() {
        LogUtil.info(TAG, "sendCarPowerReadyMessage");
        try {
            this.mExtPowerManager.sendCarPowerReadyMessage(1);
        } catch (Exception exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
    }

    public void reboot(String str) {
        logInfo("reboot  reason:" + str);
        int perm = this.mContext.checkCallingOrSelfPermission("com.ts.power.permission.REBOOT");
        LogUtil.info(TAG, "reboot perm : " + perm);
        if (perm == 0) {
            LogUtil.info(TAG, "Start reboot");
            workModeChange(0, 4);
            if (str != null && str.length() > 0) {
                str.equals(FACTORY_RESET_REBOOT_VALUE);
                return;
            }
            return;
        }
        LogUtil.info(TAG, "Denied permission reboot");
        throw new SecurityException("Denied permission reboot");
    }

    public void shutdown() {
        this.mPowerManager.shutdown(false, "CarPower", true);
        LogUtil.info(TAG, REASON_SHUTDOWN);
    }

    private void checkPermissionAndType(int type) {
        LogUtil.info(TAG, "checkPermissionAndType type : " + type);
        if (!this.mLightsManager.isBrightnessSupported(type)) {
            LogUtil.info(TAG, "notifyAppStateToCarPower type : error");
            throw new IllegalArgumentException("Screen brightness type error");
        }
        int perm = this.mContext.checkCallingOrSelfPermission("com.ts.power.permission.BRIGHTNESS");
        LogUtil.info(TAG, "notifyAppStateToCarPower perm : " + perm);
        if (perm == 0) {
            LogUtil.info(TAG, "notifyAppStateToCarPower perm granted");
        } else {
            LogUtil.info(TAG, "Denied permission Brightness");
            throw new SecurityException("Denied permission Brightness");
        }
    }

    private void sendScreenAndAwakeMessage() {
        this.mExtPowerManager.sendReleaseScreenMessage(0);
    }

    private void ecallAppStateToCarPower(final int state) {
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$6p835P__YjVZYMfrHz0engjsMcI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$ecallAppStateToCarPower$2$CarPowerBinder(state);
            }
        });
    }

    public /* synthetic */ void lambda$ecallAppStateToCarPower$2$CarPowerBinder(int state) {
        this.mEcallManager.setEcallMode(4);
        this.mEcallManager.setEcallState(state);
        if (1 == state) {
            this.mExtPowerManager.sendGetScreenMessage(0);
            return;
        }
        if (state == 0) {
            if (this.mBcallManager.getIsBcallWorking()) {
                LogUtil.info(TAG, "notifyAppStateToCarPower ECALL_ID BCALL working return");
                return;
            } else {
                sendScreenAndAwakeMessage();
                return;
            }
        }
        LogUtil.error(TAG, "ECall state : error");
    }

    private void bcallAppStateToCarPower(final int state) {
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$ppWBp8fdw3YcH9sGBC5FByBup2I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$bcallAppStateToCarPower$3$CarPowerBinder(state);
            }
        });
    }

    public /* synthetic */ void lambda$bcallAppStateToCarPower$3$CarPowerBinder(int state) {
        this.mBcallManager.setBcallMode(6);
        this.mBcallManager.setBcallState(state);
        if (1 == state) {
            this.mExtPowerManager.sendGetScreenMessage(0);
            return;
        }
        if (state == 0) {
            if (this.mEcallManager.getIsEcallWorking()) {
                LogUtil.info(TAG, "notifyAppStateToCarPower BCALL_ID ECall working return");
                return;
            } else {
                sendScreenAndAwakeMessage();
                return;
            }
        }
        LogUtil.error(TAG, "BCall state : error");
    }

    private void otaAppStateToCarPower(final int state) {
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$HAzgXK5wQX3yxNS16kO_n9Wm5ro
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$otaAppStateToCarPower$4$CarPowerBinder(state);
            }
        });
    }

    public /* synthetic */ void lambda$otaAppStateToCarPower$4$CarPowerBinder(int state) {
        if (state == 1) {
            setOtaState(true);
        } else if (state == 0) {
            setOtaState(false);
            initWorkModeState();
        } else {
            LogUtil.error(TAG, "OTA_STATE error");
        }
    }

    private void omsAppStateToCarPower(final int state) {
        this.mStateHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$3bb-mei3esfQ3d3jGDwep35cTjw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$omsAppStateToCarPower$5$CarPowerBinder(state);
            }
        });
    }

    public /* synthetic */ void lambda$omsAppStateToCarPower$5$CarPowerBinder(int state) {
        if (1 == state) {
            if (getCurrentWorkMode().getCarPowerWorkModeStatusEnum() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STR_SUSPENDING) {
                sendAwakeMessage(AWAKE_TIME_OMS);
            }
        } else if (state != 0) {
            LogUtil.error(TAG, "OMS_STATE error");
        }
    }

    public boolean notifyAppStateToCarPower(int id, int state, int type, int event) throws RemoteException {
        logInfo("notifyAppStateToCarPower id : " + id + " state : " + state + " type : " + type + " event : " + event);
        boolean bool = true;
        checkPermissionAndType(type);
        if (id == 4) {
            LogUtil.info(TAG, "notifyAppStateToCarPower ECALL_ID");
            ecallAppStateToCarPower(state);
        } else if (id == 5) {
            LogUtil.info(TAG, "OTA_ID");
            otaAppStateToCarPower(state);
        } else if (id == 6) {
            LogUtil.info(TAG, "notifyAppStateToCarPower BCALL_ID");
            bcallAppStateToCarPower(state);
        } else if (id == 7) {
            LogUtil.info(TAG, "OMS_ID");
            omsAppStateToCarPower(state);
        } else {
            LogUtil.error(TAG, "notifyAppStateToCarPower id not find");
            bool = false;
        }
        LogUtil.info(TAG, "notifyAppStateToCarPower bool : " + bool);
        return bool;
    }

    private String tenToHex(int valueTen) {
        String formatterStrHex = Integer.toHexString(valueTen);
        return formatterStrHex;
    }

    private boolean isOtaWorking() {
        boolean z;
        synchronized (this.mOtaStateLock) {
            LogUtil.info(TAG, "isOtaWorking otaState : " + this.mOtaState);
            z = this.mOtaState;
        }
        return z;
    }

    private void setOtaState(boolean state) {
        synchronized (this.mOtaStateLock) {
            LogUtil.info(TAG, "setOtaState state : " + state);
            this.mOtaState = state;
        }
    }

    public void registerCarPowerBindCarServiceListener(ICarPowerBindCarServiceListener iCarPowerBindCarServiceListener) {
        logInfo("registerCarPowerBindCarServiceListener" + iCarPowerBindCarServiceListener);
        if (iCarPowerBindCarServiceListener != null) {
            this.mICarPowerBindCarServiceListener.register(iCarPowerBindCarServiceListener);
            synchronized (this.mCarServiceConnectLock) {
                LogUtil.info(TAG, "registerCarPowerBindCarServiceListener mCarServiceConnected : " + this.mCarServiceConnected);
                if (this.mCarServiceConnected) {
                    try {
                        LogUtil.info(TAG, "registerCarPowerBindCarServiceListener onCarPowerBindCarServiceState start");
                        iCarPowerBindCarServiceListener.onCarPowerBindCarServiceState(true);
                    } catch (RemoteException excp) {
                        excp.printStackTrace();
                    }
                }
            }
            LogUtil.info(TAG, "registerCarPowerBindCarServiceListener synchronized end");
        }
    }

    public void unregisterCarPowerBindCarServiceListener(ICarPowerBindCarServiceListener iCarPowerBindCarServiceListener) {
        logInfo("unregisterCarPowerBindCarServiceListener :" + iCarPowerBindCarServiceListener);
        if (iCarPowerBindCarServiceListener != null) {
            this.mICarPowerBindCarServiceListener.unregister(iCarPowerBindCarServiceListener);
        }
    }

    private boolean checkIDisplayNotNull() {
        if (this.mIDisplay == null) {
            LogUtil.info(TAG, "mIDisplay is null !");
            return false;
        }
        return true;
    }

    public boolean setDisplayBrightness(int displayPanelId, int brightnessLevel) {
        logInfo("setDisplayBrightness displayPanelId:" + displayPanelId + "brightnessLevel:" + brightnessLevel);
        if (!checkIDisplayNotNull()) {
            return false;
        }
        try {
            return this.mIDisplay.setDisplayBrightness(displayPanelId, brightnessLevel);
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean setDisplayLvdsLinkState(int displayPanelId, int state) {
        logInfo("setDisplayLvdsLinkState displayPanelId:" + displayPanelId + "state:" + state);
        if (!checkIDisplayNotNull()) {
            return false;
        }
        try {
            return this.mIDisplay.setDisplayLvdsLinkState(displayPanelId, state);
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean setDisplayPowerState(int displayPanelId, int powerState) {
        int vhalPowerState;
        try {
            LogUtil.warning(TAG, "Pid:" + Binder.getCallingPid() + " displayPanelId:" + displayPanelId + "powerState:" + powerState);
            if (1 == powerState) {
                vhalPowerState = 0;
            } else if (2 == powerState) {
                vhalPowerState = 1;
            } else {
                LogUtil.error(TAG, "error powerState " + powerState);
                return false;
            }
            this.mCarVendorPowerManager.setDisplayState(displayPanelId, vhalPowerState, 2);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getDisplayInfo(int displayPanelId) {
        logInfo("getDisplayInfo displayPanelId:" + displayPanelId);
        if (!checkIDisplayNotNull()) {
            return -1;
        }
        return 1;
    }

    public boolean setDisplayHeight(int displayPanelId, int heightLevel) {
        logInfo("setDisplayHeight displayPanelId:" + displayPanelId + "heightLevel:" + heightLevel);
        if (!checkIDisplayNotNull()) {
            return false;
        }
        try {
            return this.mIDisplay.setDisplayHeight(displayPanelId, heightLevel);
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getDisplayHeight(int displayPanelId) {
        logInfo("getDisplayHeight displayPanelId:" + displayPanelId);
        if (!checkIDisplayNotNull()) {
            return -1;
        }
        return 1;
    }

    public boolean setDisplayRotation(int displayPanelId, int rotateLevel) {
        logInfo("setDisplayRotation displayPanelId:" + displayPanelId + "rotateLevel:" + rotateLevel);
        if (!checkIDisplayNotNull()) {
            return false;
        }
        try {
            return this.mIDisplay.setDisplayRotation(displayPanelId, rotateLevel);
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getDisplayRotation(int displayPanelId) {
        logInfo("getDisplayRotation displayPanelId:" + displayPanelId);
        if (!checkIDisplayNotNull()) {
            return -1;
        }
        return 1;
    }

    public boolean setDisplayContent(int displayPanelId, int content) {
        logInfo("setDisplayContent displayPanelId:" + displayPanelId + "content" + content);
        if (!checkIDisplayNotNull()) {
            return false;
        }
        try {
            return this.mIDisplay.setDisplayContent(displayPanelId, content);
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getDisplayContent(int displayPanelId) {
        logInfo("getDisplayContent displayPanelId:" + displayPanelId);
        if (!checkIDisplayNotNull()) {
            return -1;
        }
        return 1;
    }

    public int getDisplayDayNightMode() {
        logInfo("getDisplayDayNightMode");
        if (!checkIDisplayNotNull()) {
            return -1;
        }
        return 1;
    }

    public boolean enterOta() {
        logInfo("enterOta");
        setOtaState(true);
        handleOtaDispaly();
        if (this.mHardKeyManager == null) {
            return false;
        }
        this.mMainHandler.post(new Runnable() { // from class: com.ts.appservice.power.CarPowerBinder.10
            @Override // java.lang.Runnable
            public void run() {
                CarPowerBinder.this.setHardKeyState(0);
            }
        });
        return true;
    }

    public boolean exitOta() {
        logInfo("exitOta");
        setOtaState(false);
        handleOtaDispaly();
        if (this.mHardKeyManager == null) {
            return false;
        }
        this.mMainHandler.post(new Runnable() { // from class: com.ts.appservice.power.CarPowerBinder.11
            @Override // java.lang.Runnable
            public void run() {
                CarPowerBinder.this.setHardKeyState(1);
            }
        });
        return true;
    }

    public boolean mcuReset() {
        warning("mcuReset");
        CarVendorPowerManager carVendorPowerManager = this.mCarVendorPowerManager;
        if (carVendorPowerManager == null) {
            LogUtil.error(TAG, "mcuReset mCarVendorPowerManager null return");
            return false;
        }
        try {
            carVendorPowerManager.reset(1);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getBackLightState() {
        int iIntValue;
        logInfo("getBackLightState " + this.mDisplayInfo.iviBackLightState);
        synchronized (this.mDisplayInfo.iviBackLightState) {
            try {
                try {
                    if (this.mDisplayInfo.iviBackLightState.intValue() == 0) {
                        this.mIDisplay.getDisplayInfo(1);
                    }
                    iIntValue = this.mDisplayInfo.iviBackLightState.intValue();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    public boolean setBackLightState(int powerState, int type) {
        int vhalPowerState;
        warning("setBackLightState powerState ：" + powerState + " type:" + type);
        synchronized (this.mDisplayInfo.iviBackLightState) {
            try {
                try {
                    if (1 == powerState) {
                        vhalPowerState = 0;
                    } else if (2 == powerState) {
                        vhalPowerState = 1;
                    } else {
                        LogUtil.error(TAG, "error powerState " + powerState);
                        return false;
                    }
                    int vhalType = type;
                    if (type == 1) {
                        vhalType = 1;
                    } else if (type == 2) {
                        vhalType = 0;
                    } else if (type == 0) {
                        vhalType = 2;
                    }
                    if (this.mDisplayInfo.iviBackLightState.intValue() != powerState) {
                        this.mDisplayInfo.iviBackLightState = 0;
                    }
                    this.mCarVendorPowerManager.setDisplayState(1, vhalPowerState, vhalType);
                    this.mIDisplay.getDisplayInfo(1);
                    return true;
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setMultimediaBrightnessLinkage(int type) {
        LogUtil.info(TAG, "setMultimediaBrightnessLinkage : " + type);
        if (!checkIDisplayNotNull()) {
            return;
        }
        try {
            if (type == 1) {
                this.mIDisplay.setAutoBrightnessInfo(true);
            } else if (type == 0) {
                this.mIDisplay.setAutoBrightnessInfo(false);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setUpdateFlag(int flag) {
        logInfo(" setUpdateFlag.in-> flag:" + flag);
        if (flag == 0) {
            this.mUpdateFlag = false;
        } else if (1 == flag) {
            this.mUpdateFlag = true;
        }
        handleOtaDispaly();
    }

    private void handleOtaDispaly() {
        if (getCurrentWorkMode().getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STANDBY.getVal()) {
            if (this.mOtaState || this.mUpdateFlag) {
                setDisplayState(0);
            } else {
                setDisplayState(1);
            }
        }
    }

    public void sendReportMessage(Integer state) {
        LogUtil.info(TAG, "sendReportMessage state : " + state);
        if (this.mStartWithOnDisplayOff) {
            LogUtil.info(TAG, "sendReportMessage mStartWithOnDisplayOff : true return");
        } else if (state.intValue() != 6 && state.intValue() != 7) {
            state.intValue();
        }
    }

    public void sendAwakeMessage(int awakeTime) {
        LogUtil.info(TAG, "sendAwakeMessage awakeTime : " + awakeTime);
    }

    public void setScreenBrightnessOnOff(int type, int state) {
        LogUtil.info(TAG, "setDispSettingScdisplaycontrolMsg type :  state : " + state);
    }

    public void setScreenBrightness(int type, int brightness) {
        LogUtil.info(TAG, "setScreenBrightness type : " + type + " brightness : " + brightness);
        if (type == 0) {
            LogUtil.info(TAG, "setClusterBrightness brightness : " + brightness);
            return;
        }
        if (type == 1) {
            LogUtil.info(TAG, "setHomeScreenBrightness brightness : " + brightness);
            return;
        }
        if (type == 2) {
            LogUtil.info(TAG, "setSecondaryScreenBrightness brightness : " + brightness);
        }
    }

    public int getScreenBrightness(int type, int brightness) {
        LogUtil.info(TAG, "getScreenBrightness type : " + type + " brightness : " + brightness);
        return brightness;
    }

    public boolean isDisclaimerShowing() {
        try {
            int anInt = Settings.Global.getInt(this.mContext.getContentResolver(), KEY_DISCLAIMER_DISPLAY, 0);
            LogUtil.info(TAG, "isDisclaimerShowing***" + anInt);
            if (1 != anInt) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.error(TAG, "Settings.Global.getInt***Exception");
            return false;
        }
    }

    public void removeAllCallbackAndMessage() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this.mDebugReceiver);
            this.mContext = null;
        }
        IDisplay iDisplay = this.mIDisplay;
        if (iDisplay != null) {
            try {
                iDisplay.unlinkToDeath(this.mDisplayHalDeathRecipient);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        Handler handler = this.mStateHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mStateHandler = null;
        }
        HandlerThread handlerThread = this.mStateHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mStateHandlerThread = null;
        }
        Handler handler2 = this.mAudioHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.mAudioHandler = null;
        }
        HandlerThread handlerThread2 = this.mAudioHandlerThread;
        if (handlerThread2 != null) {
            handlerThread2.quitSafely();
            this.mAudioHandlerThread = null;
        }
        Handler handler3 = this.mTimerHandler;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
            this.mTimerHandler = null;
        }
        HandlerThread handlerThread3 = this.mTimerHandlerThread;
        if (handlerThread3 != null) {
            handlerThread3.quitSafely();
            this.mTimerHandlerThread = null;
        }
        Handler handler4 = this.mReconnectCarServiceHandler;
        if (handler4 != null) {
            handler4.removeCallbacksAndMessages(null);
            this.mReconnectCarServiceHandler = null;
        }
        WorkChangeHandler workChangeHandler = this.mWorkModeChangeHandler;
        if (workChangeHandler != null) {
            workChangeHandler.removeCallbacksAndMessages(null);
            this.mWorkModeChangeHandler = null;
        }
        MainHandler mainHandler = this.mMainHandler;
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
            this.mMainHandler = null;
        }
        DeviceOperator deviceOperator = this.mDeviceOperator;
        if (deviceOperator != null) {
            deviceOperator.quitSafely();
        }
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.quitSafely();
        }
    }

    private void logInfo(String info) {
        LogUtil.info(TAG, "Pid:" + Binder.getCallingPid() + "[ " + info + " ]");
    }

    private void warning(String info) {
        LogUtil.warning(TAG, "Pid:" + Binder.getCallingPid() + "[ " + info + " ]");
    }

    private static final class WorkChangeHandler extends Handler {
        private static final int TIME_ACC_OFF_REQUEST_AUDIO_FOCUS_DELAY = 3000;
        private static final int TIME_REPEAT_ON_DELAY = 6000;
        private static final int TIME_REPEAT_ON_INTERVAL = 10000;
        private static final int WHAT_ACC_OFF_REQUEST_AUDIO_FOCUS = 5;
        private static final int WHAT_REPEAT_ON = 4;
        private static final int WHAT_SHUTDOWN_PREPARE = 2;
        private static final int WHAT_SUSPEND_EXIT = 3;
        private static final int WORK_CHANGE = 1;
        private CarPowerBinder mCarPowerBinder;
        private long standbyTime;
        private boolean viaStandby;

        /* JADX INFO: Access modifiers changed from: private */
        public void sendAccOffRequestAudioFocus() {
            LogUtil.warning(CarPowerBinder.TAG, "sendAccOffRequestAudioFocus");
            removeMessages(5);
            sendEmptyMessageDelayed(5, 3000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAccOffRequestAudioFocus() {
            LogUtil.warning(CarPowerBinder.TAG, "removeAccOffRequestAudioFocus");
            removeMessages(5);
        }

        public WorkChangeHandler(CarPowerBinder binder, Looper looper) {
            super(looper);
            this.viaStandby = true;
            this.standbyTime = 0L;
            this.mCarPowerBinder = binder;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CarPowerBinder binder = this.mCarPowerBinder;
            if (binder == null) {
                return;
            }
            if (msg.what == 1) {
                LogUtil.info(CarPowerBinder.TAG, "start carPowerBinder workChange");
                if (msg.arg2 == 10) {
                    binder.mAudioManager.abandonAudioFocus();
                }
                binder.workChange(msg.arg1, Integer.valueOf(msg.arg2));
                if (msg.arg2 == 4) {
                    removeMessages(4);
                    this.standbyTime = SystemClock.elapsedRealtime();
                    this.viaStandby = true;
                    return;
                } else {
                    if (msg.arg2 == 10) {
                        this.viaStandby = false;
                        removeMessages(4);
                        if (SystemClock.elapsedRealtime() - this.standbyTime < 10000) {
                            sendEmptyMessageDelayed(4, 6000L);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            if (msg.what == 2) {
                Log.w(CarPowerBinder.TAG, " WHAT_SHUTDOWN_PREPARE");
                try {
                    if (!this.viaStandby) {
                        Log.w(CarPowerBinder.TAG, " WHAT_SHUTDOWN_PREPARE !viaStandby");
                        binder.mDeviceOperator.stateChange(10, 4);
                    } else {
                        Log.w(CarPowerBinder.TAG, " WHAT_SHUTDOWN_PREPARE turn off device");
                        binder.mDeviceOperator.turnOffBt();
                        binder.mDeviceOperator.turnOffWifi();
                        binder.mDeviceOperator.turnOffWifiAp();
                    }
                    if (binder.mCarAudioControl != null) {
                        Log.w(CarPowerBinder.TAG, " SHUTDOWN_PREPARE AudioControl IVI_PM_DEGRADED");
                        binder.mCarAudioControl.setIntegerValue(15, 3);
                        binder.mAudioFocusState = binder.mCarAudioControl.getIntegerValue(3, 0);
                        Log.w(CarPowerBinder.TAG, " SHUTDOWN_PREPARE AudioControl INDEX_DYNA_SOUND_FIELD_FOCUS : " + binder.mAudioFocusState);
                        return;
                    }
                    return;
                } catch (Exception exc) {
                    LogUtil.error(CarPowerBinder.TAG, Log.getStackTraceString(exc));
                    return;
                }
            }
            if (msg.what == 3) {
                Log.w(CarPowerBinder.TAG, " WHAT_SUSPEND_EXIT updateAudioPowerState");
                binder.updateAudioPowerState();
                Log.w(CarPowerBinder.TAG, " WHAT_SUSPEND_EXIT mAudioFocusState : " + binder.mAudioFocusState);
                if (binder.mAudioFocusState != CarPowerBinder.VALUE_AUDIO_FOCUS && binder.mCarAudioControl != null) {
                    binder.mCarAudioControl.setIntegerValue(3, 0);
                    Log.w(CarPowerBinder.TAG, " SHUTDOWN_PREPARE INDEX_DYNA_SOUND_FIELD_FOCUS DYNA_ALL_INDEX");
                    binder.mCarAudioControl.setIntegerValue(3, binder.mAudioFocusState);
                    Log.w(CarPowerBinder.TAG, " WHAT_SUSPEND_EXIT INDEX_DYNA_SOUND_FIELD_FOCUS : " + binder.mAudioFocusState);
                    binder.mAudioFocusState = CarPowerBinder.VALUE_AUDIO_FOCUS;
                    return;
                }
                return;
            }
            if (msg.what == 4) {
                try {
                    if (binder.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON.getVal()) {
                        binder.mRemoteCallbackExecutor.onCarPowerWorkModeChangeEvent(binder.mCurWorkModeStatus);
                        return;
                    }
                    return;
                } catch (Exception exception) {
                    Log.e(CarPowerBinder.TAG, "WHAT_REPEAT_ON", exception);
                    return;
                }
            }
            if (msg.what == 5) {
                LogUtil.warning(CarPowerBinder.TAG, "WorkChangeHandler WHAT_ACC_OFF_REQUEST_AUDIO_FOCUS");
                binder.mAudioManager.requestAudioFocus();
            }
        }
    }

    private static final class MainHandler extends Handler {
        private static final int DAY_NIGHT_MODE_CHANGE_BRIGHTNESS_INTERVAL = 2000;
        private static final int DEVICE_DEAL_TIME = 7000;
        private static final int HAL_BRIGHTNESS_AND_SETTING_BRIGHTNESS_DIFF = 4;
        private static final int SHAKE_TIME = 2000;
        private static final int SHAKE_TIME_TOUCH_VIEW = 500;
        private static final int TURN_OFF_BT_DEAL_TIME = 1200;
        public static final int WHAT_DAY_NIGHT_MODE_CHANGE_BRIGHTNESS = 5;
        private static final int WHAT_DEVICE_DEAL = 4;
        private static final int WHAT_INIT_DELAY = 3;
        public static final int WHAT_MAIN_HANDLER_DAY_NIGHT_MODE_CHANGE = 1;
        private static final int WHAT_SHAKE = 2;
        public static final int WHAT_STANDBY_TURN_OFF_BT = 7;
        public static final int WHAT_SYNC_DEFAULT_BRIGHTNESS = 8;
        public static final int WHAT_UN_TOUCH_VIEW_STATE = 6;
        private CarPowerBinder mCarPowerBinder;

        public MainHandler(CarPowerBinder binder, Looper looper) {
            super(looper);
            this.mCarPowerBinder = binder;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateUnTouchView() {
            if (hasMessages(6)) {
                removeMessages(6);
                sendEmptyMessageDelayed(6, 500L);
            } else {
                sendEmptyMessage(6);
                Message message = obtainMessage(6);
                message.arg1 = 6;
                sendMessageDelayed(message, 500L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void turnOffBtDelay() {
            removeMessages(7);
            sendEmptyMessageDelayed(7, 1200L);
        }

        private void setBrightnessToSetting(CarPowerBinder binder, int brightness) throws Settings.SettingNotFoundException {
            if (!binder.mLightsManager.getIsSyncDefaultBrightness()) {
                binder.mLightsManager.setIsSyncDefaultBrightness(true);
            }
            int brightnessLevel = (int) Math.ceil((brightness * 255.0f) / 100.0f);
            int settingBrightness = 0;
            try {
                settingBrightness = Settings.System.getInt(binder.mContext.getContentResolver(), "screen_brightness");
            } catch (Exception exception) {
                LogUtil.error(CarPowerBinder.TAG, "setBrightnessToSetting= " + exception);
            }
            LogUtil.info(CarPowerBinder.TAG, "setBrightnessToSetting brightnessLevel : " + brightnessLevel + ", settingBrightness : " + settingBrightness);
            if (Math.abs(brightnessLevel - settingBrightness) > 4) {
                LogUtil.info(CarPowerBinder.TAG, "setBrightnessToSetting putString : " + brightnessLevel);
                Settings.System.putString(binder.mContext.getContentResolver(), "screen_brightness", String.valueOf(brightnessLevel));
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) throws Settings.SettingNotFoundException {
            super.handleMessage(msg);
            CarPowerBinder binder = this.mCarPowerBinder;
            if (binder == null) {
                return;
            }
            if (msg.what == 5) {
                int brightness = msg.arg1;
                if (brightness >= 0) {
                    LogUtil.info(CarPowerBinder.TAG, "WHAT_DAY_NIGHT_MODE_CHANGE_BRIGHTNESS= " + brightness);
                    setBrightnessToSetting(binder, brightness);
                    return;
                }
                return;
            }
            if (msg.what == 8) {
                int brightness2 = msg.arg1;
                if (brightness2 >= 0) {
                    LogUtil.info(CarPowerBinder.TAG, "WHAT_SYNC_DEFAULT_BRIGHTNESS= " + brightness2);
                    setBrightnessToSetting(binder, brightness2);
                    return;
                }
                return;
            }
            if (msg.what == 2) {
                if (msg.arg1 != 0) {
                    return;
                }
                binder.mDeviceOperator.changeDeviceState(binder.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
                return;
            }
            if (msg.what == 6) {
                if (binder.mDisplayInfo == null) {
                    LogUtil.error(CarPowerBinder.TAG, "mDisplayInfo null");
                    return;
                }
                if (msg.arg1 != 6) {
                    LogUtil.warning(CarPowerBinder.TAG, "WHAT_UN_TOUCH_VIEW_STATE iviBackLightState : " + binder.mDisplayInfo.iviBackLightState + "powerDisplayState :" + binder.mDisplayInfo.powerDisplayState);
                    if (binder.mDisplayInfo.iviBackLightState.intValue() != 2 || binder.mDisplayInfo.powerDisplayState != 1) {
                        binder.mBlackViewManager.hideBlackView(binder.mContext);
                        return;
                    } else {
                        binder.mBlackViewManager.showBlackView(binder.mContext, false);
                        return;
                    }
                }
                return;
            }
            if (msg.what != 7) {
                return;
            }
            binder.mDeviceOperator.turnOffBt();
        }
    }

    private class DeviceOperator {
        private static final String PREFS_FILE = "CarPowerBinder_state";
        private static final String PREFS_KEY_BLUETOOTH = "bluetooth";
        private static final String PREFS_KEY_DEFAULT_BRIGHTNESS = "defaultBrightness";
        private static final String PREFS_KEY_WIFI = "wifi";
        private static final String PREFS_KEY_WIFIAP = "wifiAp";
        private static final int PREFS_VALUE_NO = 0;
        private static final int PREFS_VALUE_UNKNOWN = -1;
        private static final int PREFS_VALUE_YES = 1;
        private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        private BluetoothStateBroadcastReceive mBluetoothReceiver;
        private final SharedPreferences mSharedPreferences;
        private TetheringManager mTetheringManager;
        private WifiManager mWifiManager;
        private WifiStateBroadcastReceive mWifiReceiver;

        public DeviceOperator() {
            this.mSharedPreferences = CarPowerBinder.this.mContext.getSharedPreferences(PREFS_FILE, 0);
            this.mWifiManager = (WifiManager) CarPowerBinder.this.mContext.getSystemService(PREFS_KEY_WIFI);
            this.mTetheringManager = (TetheringManager) CarPowerBinder.this.mContext.getSystemService(TetheringManager.class);
            this.mWifiReceiver = new WifiStateBroadcastReceive();
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            filter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
            CarPowerBinder.this.mContext.registerReceiver(this.mWifiReceiver, filter);
            this.mBluetoothReceiver = new BluetoothStateBroadcastReceive();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            CarPowerBinder.this.mContext.registerReceiver(this.mBluetoothReceiver, intentFilter);
        }

        public void stateChange(int oldState, int state) {
            LogUtil.warning(CarPowerBinder.TAG, "oldState :" + oldState + "state:" + state);
            if (state != 4 && state != 10) {
                return;
            }
            saveState(oldState);
            if (CarPowerBinder.this.mMainHandler.hasMessages(2)) {
                CarPowerBinder.this.mMainHandler.removeMessages(2);
                Message message = CarPowerBinder.this.mMainHandler.obtainMessage(2);
                message.arg1 = 0;
                CarPowerBinder.this.mMainHandler.sendMessageDelayed(message, 2000L);
                return;
            }
            Message message2 = CarPowerBinder.this.mMainHandler.obtainMessage(2);
            message2.arg1 = 1;
            CarPowerBinder.this.mMainHandler.sendMessageDelayed(message2, 2000L);
            changeDeviceState(state);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void changeDeviceState(int state) {
            if (state == 4 || state == 10) {
                CarPowerBinder.this.mMainHandler.removeMessages(4);
                CarPowerBinder.this.mMainHandler.sendEmptyMessageDelayed(4, 7000L);
                if (4 == state) {
                    turnOffWifi();
                    CarPowerBinder.this.mMainHandler.turnOffBtDelay();
                    turnOffWifiAp();
                } else if (10 == state) {
                    CarPowerBinder.this.mMainHandler.removeMessages(7);
                    if (getBluetooth() == 1) {
                        turnOnBt();
                    }
                    if (getWifi() == 1) {
                        turnOnWifi();
                    }
                    if (getWifiAp() == 1) {
                        turnOnWifiAp();
                    }
                }
            }
        }

        private void saveState(int oldState) {
            if (oldState == 10 && !CarPowerBinder.this.mMainHandler.hasMessages(3) && !CarPowerBinder.this.mMainHandler.hasMessages(2) && !CarPowerBinder.this.mMainHandler.hasMessages(4)) {
                LogUtil.info(CarPowerBinder.TAG, "saveState :" + oldState);
                int wifi = 0;
                int bluetooth = 0;
                int wifiAp = 0;
                if (this.mWifiManager.isWifiEnabled()) {
                    wifi = 1;
                }
                if (this.mBluetoothAdapter.isEnabled()) {
                    bluetooth = 1;
                }
                if (this.mWifiManager.isWifiApEnabled()) {
                    wifiAp = 1;
                }
                setWifi(wifi);
                setBluetooth(bluetooth);
                setWifiAp(wifiAp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void callBackSaveState() {
            int state = CarPowerBinder.this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal();
            if (state != 10 || CarPowerBinder.this.mMainHandler.hasMessages(3) || CarPowerBinder.this.mMainHandler.hasMessages(4)) {
                return;
            }
            int wifi = 0;
            int bluetooth = 0;
            int wifiAp = 0;
            if (this.mWifiManager.isWifiEnabled()) {
                wifi = 1;
            }
            if (this.mWifiManager.isWifiApEnabled()) {
                wifiAp = 1;
            }
            if (this.mBluetoothAdapter.isEnabled()) {
                bluetooth = 1;
            }
            LogUtil.info(CarPowerBinder.TAG, "callBackSaveState wifi :" + wifi + "bluetooth:" + bluetooth + "wifiAp:" + wifiAp);
            setWifi(wifi);
            setBluetooth(bluetooth);
            setWifiAp(wifiAp);
        }

        private int getWifi() {
            int wifi = this.mSharedPreferences.getInt(PREFS_KEY_WIFI, -1);
            LogUtil.info(CarPowerBinder.TAG, "getWifi: " + wifi);
            return wifi;
        }

        private void setWifi(int wifi) {
            LogUtil.info(CarPowerBinder.TAG, "setWifi: " + wifi);
            this.mSharedPreferences.edit().putInt(PREFS_KEY_WIFI, wifi).apply();
        }

        private int getBluetooth() {
            int bluetooth = this.mSharedPreferences.getInt(PREFS_KEY_BLUETOOTH, -1);
            LogUtil.info(CarPowerBinder.TAG, "getBluetooth: " + bluetooth);
            return bluetooth;
        }

        private void setBluetooth(int bluetooth) {
            LogUtil.info(CarPowerBinder.TAG, "setBluetooth: " + bluetooth);
            this.mSharedPreferences.edit().putInt(PREFS_KEY_BLUETOOTH, bluetooth).apply();
        }

        private int getWifiAp() {
            int wifiAp = this.mSharedPreferences.getInt(PREFS_KEY_WIFIAP, -1);
            LogUtil.info(CarPowerBinder.TAG, "getWifiAp: " + wifiAp);
            return wifiAp;
        }

        private void setWifiAp(int wifiAp) {
            LogUtil.info(CarPowerBinder.TAG, "setWifiAp: " + wifiAp);
            if (CarPowerBinder.this.mCarPlayRemoteManager.isCarPlayLink()) {
                wifiAp = 0;
                LogUtil.warning(CarPowerBinder.TAG, "setWifiAp: isCarPlayLink wifiAp:0");
            }
            this.mSharedPreferences.edit().putInt(PREFS_KEY_WIFIAP, wifiAp).apply();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void turnOffWifi() {
            try {
                int wifiStatus = this.mWifiManager.getWifiState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOffWifi wifiStatus " + wifiStatus);
                if (wifiStatus == 3) {
                    this.mWifiManager.setWifiEnabled(false);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        private void turnOnWifi() {
            try {
                int wifiStatus = this.mWifiManager.getWifiState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOnWifi wifiStatus " + wifiStatus);
                this.mWifiManager.setWifiEnabled(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void turnOffBt() {
            try {
                int btStatus = this.mBluetoothAdapter.getState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOffBt btStatus " + btStatus);
                if (btStatus == 12) {
                    this.mBluetoothAdapter.disable();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        private void turnOnBt() {
            try {
                int btStatus = this.mBluetoothAdapter.getState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOnBt btStatus " + btStatus);
                this.mBluetoothAdapter.enable();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void turnOffWifiAp() {
            try {
                int apState = this.mWifiManager.getWifiApState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOffWifiAp apState " + apState);
                if (CarPowerBinder.this.mCarPlayRemoteManager.isCarPlayLink()) {
                    LogUtil.info(CarPowerBinder.TAG, "turnOffWifiAp CarPlay isCarPlayLink ");
                } else if (apState == 13) {
                    this.mTetheringManager.stopTethering(0);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        private void turnOnWifiAp() {
            try {
                int apState = this.mWifiManager.getWifiApState();
                LogUtil.warning(CarPowerBinder.TAG, "turnOnWifiAp apState " + apState);
                this.mTetheringManager.startTethering(0, ConcurrentUtils.DIRECT_EXECUTOR, new TetheringManager.StartTetheringCallback() { // from class: com.ts.appservice.power.CarPowerBinder.DeviceOperator.1
                    public void onTetheringFailed(int result) {
                    }
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public void quitSafely() {
            if (this.mBluetoothReceiver != null && CarPowerBinder.this.mContext != null) {
                CarPowerBinder.this.mContext.unregisterReceiver(this.mBluetoothReceiver);
                this.mBluetoothReceiver = null;
            }
            if (this.mWifiReceiver != null && CarPowerBinder.this.mContext != null) {
                CarPowerBinder.this.mContext.unregisterReceiver(this.mWifiReceiver);
                this.mWifiReceiver = null;
            }
            this.mWifiManager = null;
            this.mBluetoothAdapter = null;
        }
    }

    private class WifiStateBroadcastReceive extends BroadcastReceiver {
        private WifiStateBroadcastReceive() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int wifistate = intent.getIntExtra("wifi_state", 1);
            LogUtil.info(CarPowerBinder.TAG, "wifistate  " + wifistate);
            if (wifistate == 1) {
                LogUtil.info(CarPowerBinder.TAG, "系统关闭wifi  onStateChange");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
                return;
            }
            if (wifistate == 3) {
                LogUtil.info(CarPowerBinder.TAG, "系统关开启wifi  onStateChange");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
            } else if (wifistate == 11) {
                LogUtil.info(CarPowerBinder.TAG, "热点  已关闭");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
            } else if (wifistate == 13) {
                LogUtil.info(CarPowerBinder.TAG, "热点  已开启");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
            }
        }
    }

    private class BluetoothStateBroadcastReceive extends BroadcastReceiver {
        private BluetoothStateBroadcastReceive() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int blueState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            LogUtil.info(CarPowerBinder.TAG, "blueState :" + blueState);
            if (blueState == 10) {
                LogUtil.info(CarPowerBinder.TAG, "BluetoothStateBroadcastReceive action:STATE_OFF");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
            } else if (blueState == 12) {
                LogUtil.info(CarPowerBinder.TAG, "BluetoothStateBroadcastReceive action:STATE_ON");
                CarPowerBinder.this.mDeviceOperator.callBackSaveState();
            }
        }
    }

    private static class AndroidCarManager extends BYDCarServiceBase {
        private WeakReference<CarPowerBinder> weakReference;

        public AndroidCarManager(Context context, CarPowerBinder binder) {
            super(context);
            this.weakReference = new WeakReference<>(binder);
        }

        @Override // com.ts.appservice.power.car.BYDCarServiceBase
        protected void onServiceConnected(android.car.Car car) {
            LogUtil.warning(CarPowerBinder.TAG, "onServiceConnected " + car);
            final CarPowerBinder binder = this.weakReference.get();
            if (binder != null) {
                binder.mCarEnhancedAudioManager = (CarEnhancedAudioManager) car.getCarManager("enhanced_audio");
                binder.mCarAudioManager = (CarAudioManager) car.getCarManager("audio");
                if (binder.mCarEnhancedAudioManager != null) {
                    binder.mCarAudioControl = new CarAudioControl(car, binder.mCarEnhancedAudioManager);
                    LogUtil.info(CarPowerBinder.TAG, "mCarAudioControl is init");
                    if (binder.mAudioHandler == null) {
                        LogUtil.warning(CarPowerBinder.TAG, "onServiceConnected binder.mAudioHandler == null");
                        return;
                    } else {
                        binder.mAudioHandler.removeCallbacksAndMessages(null);
                        binder.mAudioHandler.postDelayed(new Runnable() { // from class: com.ts.appservice.power.CarPowerBinder.AndroidCarManager.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LogUtil.warning(CarPowerBinder.TAG, "updateAudioPowerState delay 1000");
                                binder.updateAudioPowerState();
                            }
                        }, 1000L);
                    }
                }
                binder.mCarPowerManager = (CarPowerManager) car.getCarManager("power");
                if (binder.mCarPowerManager != null) {
                    binder.mCarPowerManager.clearListener();
                    binder.mCarPowerManager.setListenerWithCompletion(binder);
                }
            }
        }

        @Override // com.ts.appservice.power.car.BYDCarServiceBase
        protected void onServiceDied() {
            LogUtil.error(CarPowerBinder.TAG, "onServiceDied ");
            CarPowerBinder binder = this.weakReference.get();
            if (binder != null) {
                binder.mCarEnhancedAudioManager = null;
                binder.mCarAudioControl = null;
                binder.mCarPowerManager = null;
            }
        }

        public String serviceName() {
            return "powerAndroidCarServiceManager";
        }
    }

    private static class DisplayInfo {
        private static final int BACKLIGHT_STATE_OFF = 2;
        private static final int BACKLIGHT_STATE_ON = 1;
        private static final int BACKLIGHT_STATE_UNKNOWN = 0;
        private Integer iviBackLightState;
        private int powerDisplayState;

        private DisplayInfo() {
            this.iviBackLightState = 0;
            this.powerDisplayState = -1;
        }
    }

    public int setAllMute(int state) {
        CarAudioManager carAudioManager;
        Log.w(TAG, "power setAllMute " + state);
        if (this.mCarEnhancedAudioManager != null && (carAudioManager = this.mCarAudioManager) != null) {
            int groupCount = carAudioManager.getVolumeGroupCount();
            int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
            if (state == 0) {
                for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                    if (groupIndex != callGroupId) {
                        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, false, 0);
                        Log.w(TAG, "power setAllMute muteId = " + groupIndex);
                    }
                }
                Log.d(TAG, "power setAllMute delayCheckAllMute success");
                this.mAudioHandler.postDelayed(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$CarPowerBinder$xHvFrchwJtIjAZxbsBQ3iCiXP68
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.delayCheckAllMute();
                    }
                }, 100L);
            } else if (state == 1) {
                for (int groupIndex2 = 0; groupIndex2 < groupCount; groupIndex2++) {
                    if (groupIndex2 != callGroupId) {
                        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex2, true, 0);
                        Log.w(TAG, "power setAllMute muteId = " + groupIndex2);
                    }
                }
            }
            return 1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayCheckAllMute() {
        CarAudioManager carAudioManager;
        LogUtil.warning(TAG, "delayCheckAllMute mCurWorkModeStatus : " + this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
        if (CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_DISPLAY_ON.getVal() != this.mCurWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal()) {
            LogUtil.error(TAG, "delayCheckAllMute CurWorkModeStatus is not IVI_PM_DISPLAY_ON");
            return;
        }
        if (this.mCarEnhancedAudioManager != null && (carAudioManager = this.mCarAudioManager) != null) {
            try {
                int groupCount = carAudioManager.getVolumeGroupCount();
                int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                    if (groupIndex != callGroupId) {
                        boolean isMuted = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, groupIndex);
                        LogUtil.warning(TAG, "delayCheckAllMute groupIndex : " + groupIndex + ", isMuted : " + isMuted);
                        if (isMuted) {
                            this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, false, 0);
                        }
                    }
                }
                return;
            } catch (Exception exception) {
                LogUtil.error(TAG, "delayCheckAllMute exception : " + exception);
                return;
            }
        }
        LogUtil.error(TAG, "delayCheckAllMute mCarEnhancedAudioManager and mCarAudioManager == null");
    }
}
