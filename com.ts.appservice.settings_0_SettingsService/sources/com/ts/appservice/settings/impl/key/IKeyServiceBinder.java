package com.ts.appservice.settings.impl.key;

import android.car.Car;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFocusInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.KeyEvent;
import com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.key.IKeySettingsCallback;
import com.ts.lib.settings.key.IKeySettingsEventCallback;
import com.ts.lib.settings.key.IKeySettingsLongPressEventCallback;
import com.ts.lib.settings.key.IKeySettingsService;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.net.bsd.RCommandClient;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.input.IHardKeyManager;
import ts.car.service.input.IKeyEventListener;
import ts.car.service.input.IKeyLongPressEventListener;

/* JADX INFO: loaded from: classes.dex */
public class IKeyServiceBinder extends IKeySettingsService.Stub {
    public static final int ACCELERATE_TIMER_INTERFACE = 1;
    private static final String ANDROID_AUTO = "androidauto";
    public static final int CLICK_LONG = 2;
    public static final int CLICK_SHORT = 1;
    public static final int GEAR_R = 2;
    public static final int IDLERWHEEL_DOWN = 2;
    public static final int IDLERWHEEL_UP = 1;
    public static final int INSTRUMENT_MENU_INTERFACE = 2;
    private static final int MAX_CREATE_CAR_MS = 200000;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CAR_ADAPTER = 6;
    private static final int MESSAGE_SERVICE_CREATE_CAR = 4;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    public static final int SCENE_ALARM_MESSAGE_BG = 201;
    public static final int SCENE_BT_HMI_BG = 101;
    private static final String VOICE_REQUEST_RELEASE = "android.broadcast.voice.request.release";
    private static final String VOICE_REQUEST_START = "android.broadcast.voice.request.start";
    private static final int WHEEL_LONG_PRESS_TIME = 800;
    private static final String WHO = "who";
    private Car mCar;
    private boolean mCarAdapterBinderState;
    private CarAdapterManager mCarAdapterManager;
    private CarAdapterServiceManager mCarAdapterServiceManager;
    private CarAudioManager mCarAudioManager;
    private CarCabinAdapterManager mCarCabinAdapterManager;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private Context mContext;
    protected HelperHandler mHandler;
    private IHardKeyManager mHardKeyManager;
    protected boolean mIsBinding;
    private KeyCallback mKeyCallback;
    private LongSceneKeyCallback mLongSceneKeyCallback;
    protected boolean mManualDisconnected;
    private boolean mMenuDelayRemoveStatus;
    private SceneKeyCallback mSceneKeyCallback;
    protected HandlerThread mThread;
    private VrReceiver mVrReceiver;
    private static final String TAG = IKeyServiceBinder.class.getSimpleName();
    private static final HashMap<Integer, Integer> BYD_TO_TS_SCENE_MAP = new HashMap<>();
    private static final HashMap<Integer, Integer> TS_TO_BYD_SCENE_MAP = new HashMap<>();
    private boolean mMenuAddStatus = false;
    private boolean mGearAddStatus = false;
    private boolean mAccelerateTimerStatus = false;
    private boolean mIsAndroidAuto = false;
    public int mSpeedUpCountStateR = 0;
    public int mAccCounterUiR = 0;
    private boolean mWheelDown = false;
    private boolean mBlockWheelEventStatus = false;
    private long mWheelDownTime = 0;
    private OnCabinChangedListener mCabinChangedListener = new OnCabinChangedListener() { // from class: com.ts.appservice.settings.impl.key.IKeyServiceBinder.1
        public void onCabinChanged(int id, int areaId, CabinPacket packet) {
            if (id == 557867009) {
                IKeyServiceBinder.this.mSpeedUpCountStateR = packet.getSpeedUpCountStateR();
                IKeyServiceBinder.this.addClusterMenuKeyEventListener();
                return;
            }
            if (id == 557867011) {
                IKeyServiceBinder.this.mAccCounterUiR = packet.getAccCounterUiR();
                IKeyServiceBinder.this.addAccTimeKeyEventListener();
                return;
            }
            if (id == 557860357) {
                int gearBoxAutoModeType = packet.getGearboxAutoModeType();
                LogUtil.error(IKeyServiceBinder.TAG, "gearBoxAutoModeType is " + gearBoxAutoModeType);
                if (gearBoxAutoModeType == 2) {
                    try {
                        if (!IKeyServiceBinder.this.mGearAddStatus) {
                            LogUtil.info(IKeyServiceBinder.TAG, "gearBoxAutoModeType = 2 and mGearAddStatus is false, add ClusterMenuKeyEventListener");
                            IKeyServiceBinder.this.mHardKeyManager.addKeyEventListener(IKeyServiceBinder.this.mClusterMenuKeyEventListener, RCommandClient.DEFAULT_PORT);
                            IKeyServiceBinder.this.mGearAddStatus = true;
                            return;
                        }
                        return;
                    } catch (Exception ex) {
                        LogUtil.error(IKeyServiceBinder.TAG, "onCabinChanged error ", ex);
                        return;
                    }
                }
                try {
                    if (IKeyServiceBinder.this.mGearAddStatus) {
                        LogUtil.info(IKeyServiceBinder.TAG, "gearBoxAutoModeType != 2 and mGearAddStatus is true, remove ClusterMenuKeyEventListener");
                        IKeyServiceBinder.this.mHardKeyManager.removeKeyEventListener(IKeyServiceBinder.this.mClusterMenuKeyEventListener, RCommandClient.DEFAULT_PORT);
                        IKeyServiceBinder.this.mGearAddStatus = false;
                    }
                } catch (Exception ex2) {
                    LogUtil.error(IKeyServiceBinder.TAG, "onCabinChanged error ", ex2);
                }
            }
        }

        public void onCabinBinderChanged(boolean binderState) {
        }
    };
    private Car.CarServiceLifecycleListener mCarServiceLifecycleListener = new Car.CarServiceLifecycleListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$9aZLIp2mn8yLM-Vj9ijo8YRJ6Ro
        @Override // android.car.Car.CarServiceLifecycleListener
        public final void onLifecycleChanged(Car car, boolean z) {
            this.f$0.lambda$new$0$IKeyServiceBinder(car, z);
        }
    };
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.appservice.settings.impl.key.IKeyServiceBinder.2
        public void onServiceConnected(IPlatformService iPlatformService) {
            try {
                IKeyServiceBinder.this.mHardKeyManager.addKeyEventListener(IKeyServiceBinder.this.mKeyEventListener, 502);
                IKeyServiceBinder.this.mHardKeyManager.addLongPressEventListener(IKeyServiceBinder.this.mWheelKeyLongPressEventListener, 502);
                IKeyServiceBinder.this.mHardKeyManager.addLongPressEventListener(IKeyServiceBinder.this.mKeyLongPressEventListener, 503);
            } catch (Exception ex) {
                LogUtil.error(IKeyServiceBinder.TAG, "onServiceConnected error ", ex);
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
        }
    };
    private final IKeyEventListener mKeyEventListener = new IKeyEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$M1P11ZtOTUNAleWoUcUZ5ODEvu4
        public final void onKeyEvent(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$1$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyEventListener mClusterMenuKeyEventListener = new IKeyEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$t-924U8Iu_YZ8FmvAElieLzqXMA
        public final void onKeyEvent(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$2$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyLongPressEventListener mClusterMenuKeyLongEventListener = new IKeyLongPressEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$d-BXuyIi8GrhteC7_xws9Na5INc
        public final void onKeyLongPressed(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$3$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyEventListener mAccelerationTimerKeyEventListener = new IKeyEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$QcpbJXYVpAu3coLi_PVYZuhrNbs
        public final void onKeyEvent(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$4$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyLongPressEventListener mKeyLongPressEventListener = new IKeyLongPressEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$lALA8MeNCZALhrYZHUOEekj_IbU
        public final void onKeyLongPressed(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$5$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyLongPressEventListener mWheelKeyLongPressEventListener = new IKeyLongPressEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$m8VkHPYrj2tlvt8Jra6lDGlMAzw
        public final void onKeyLongPressed(KeyEvent keyEvent, int i, int i2) {
            IKeyServiceBinder.lambda$new$6(keyEvent, i, i2);
        }
    };
    private final IKeyEventListener mSceneKeyEventListener = new IKeyEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$dTwmgxYcESeGUQs6wnI-a5iDCnM
        public final void onKeyEvent(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$7$IKeyServiceBinder(keyEvent, i, i2);
        }
    };
    private final IKeyLongPressEventListener mSceneKeyLongPressEventListener = new IKeyLongPressEventListener() { // from class: com.ts.appservice.settings.impl.key.-$$Lambda$IKeyServiceBinder$b0OXU_rD42FMTfvaKlrXHNleOIw
        public final void onKeyLongPressed(KeyEvent keyEvent, int i, int i2) {
            this.f$0.lambda$new$8$IKeyServiceBinder(keyEvent, i, i2);
        }
    };

    static {
        HashMap<Integer, Integer> map = BYD_TO_TS_SCENE_MAP;
        Integer numValueOf = Integer.valueOf(SCENE_BT_HMI_BG);
        map.put(numValueOf, 506);
        BYD_TO_TS_SCENE_MAP.put(201, 504);
        TS_TO_BYD_SCENE_MAP.put(506, numValueOf);
        TS_TO_BYD_SCENE_MAP.put(504, 201);
    }

    public IKeyServiceBinder(Context context) {
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mHandler = new HelperHandler(this.mThread.getLooper());
        IHardKeyManager iHardKeyManager = (IHardKeyManager) ServiceProvider.get(IHardKeyManager.class, context);
        this.mHardKeyManager = iHardKeyManager;
        iHardKeyManager.registerConnectionListener(this.mServiceConnectionListener);
        this.mHandler.sendEmptyMessage(6);
        this.mHandler.sendEmptyMessage(5);
        this.mKeyCallback = new KeyCallback(this.mContext);
        this.mLongSceneKeyCallback = new LongSceneKeyCallback(this.mContext);
        this.mSceneKeyCallback = new SceneKeyCallback(this.mContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VOICE_REQUEST_START);
        intentFilter.addAction(VOICE_REQUEST_RELEASE);
        VrReceiver vrReceiver = new VrReceiver();
        this.mVrReceiver = vrReceiver;
        this.mContext.registerReceiver(vrReceiver, intentFilter);
    }

    public void addInstumentStatusListener() {
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext.getApplicationContext());
        this.mCarAdapterManager = carAdapterManager;
        CarCabinAdapterManager carCabinAdapterManager = (CarCabinAdapterManager) carAdapterManager.getCarAdapterManager("cabin");
        this.mCarCabinAdapterManager = carCabinAdapterManager;
        carCabinAdapterManager.subscribeCabinCallback(this.mCabinChangedListener);
        this.mSpeedUpCountStateR = this.mCarCabinAdapterManager.getSpeedUpCountState();
        this.mAccCounterUiR = this.mCarCabinAdapterManager.getAccCounterUi();
        addClusterMenuKeyEventListener();
        addAccTimeKeyEventListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addClusterMenuKeyEventListener() {
        LogUtil.info(TAG, "speedUpCountStateR is " + this.mSpeedUpCountStateR);
        if (this.mSpeedUpCountStateR == 2) {
            try {
                if (!this.mMenuAddStatus) {
                    LogUtil.info(TAG, "is MENU_INTERFACE and mMenuAddStatus is false, add ClusterMenuKeyEventListener");
                    this.mHardKeyManager.addKeyEventListener(this.mClusterMenuKeyEventListener, 512);
                    this.mHardKeyManager.addLongPressEventListener(this.mClusterMenuKeyLongEventListener, 512);
                    this.mMenuAddStatus = true;
                    return;
                }
                return;
            } catch (Exception ex) {
                LogUtil.error(TAG, "onCabinChanged error ", ex);
                return;
            }
        }
        try {
            if (this.mMenuAddStatus) {
                LogUtil.info(TAG, "speedUpCountStateR != 2 and mMenuAddStatus is true, remove ClusterMenuKeyEventListener");
                this.mHardKeyManager.removeKeyEventListener(this.mClusterMenuKeyEventListener, 512);
                this.mHardKeyManager.removeLongPressEventListener(this.mClusterMenuKeyLongEventListener, 512);
                this.mMenuAddStatus = false;
                if (this.mWheelDown) {
                    this.mMenuDelayRemoveStatus = true;
                    LogUtil.info(TAG, "because of mWheelDown is true to set mMenuDelayRemoveStatus true");
                }
            }
        } catch (Exception ex2) {
            LogUtil.error(TAG, "onCabinChanged error ", ex2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAccTimeKeyEventListener() {
        LogUtil.info(TAG, "mAccCounterUiR is " + this.mAccCounterUiR);
        if (this.mAccCounterUiR == 1) {
            try {
                if (!this.mAccelerateTimerStatus) {
                    LogUtil.info(TAG, "is ACCELERATE_TIMER and mAccelerateTimerStatus is false, add mAccelerationTimerKeyEventListener");
                    this.mHardKeyManager.addKeyEventListener(this.mAccelerationTimerKeyEventListener, 501);
                    this.mAccelerateTimerStatus = true;
                    return;
                }
                return;
            } catch (Exception ex) {
                LogUtil.error(TAG, "onCabinChanged error ", ex);
                return;
            }
        }
        try {
            if (this.mAccelerateTimerStatus) {
                LogUtil.info(TAG, "mAccCounterUiR != 1 and mAccelerateTimerStatus is true, remove mAccelerationTimerKeyEventListener");
                this.mHardKeyManager.removeKeyEventListener(this.mAccelerationTimerKeyEventListener, 501);
                this.mAccelerateTimerStatus = false;
                if (this.mWheelDown) {
                    this.mBlockWheelEventStatus = true;
                    LogUtil.info(TAG, "because of mWheelDown is true to set mBlockWheelEventStatus true");
                }
            }
        } catch (Exception ex2) {
            LogUtil.error(TAG, "onCabinChanged error ", ex2);
        }
    }

    public /* synthetic */ void lambda$new$0$IKeyServiceBinder(Car car, boolean ready) {
        LogUtil.info(TAG, "CarServiceLifecycleListener ready is = " + ready);
        if (ready) {
            synchronized (this) {
                this.mCar = car;
                this.mIsBinding = false;
            }
            this.mCarEnhancedAudioManager = (CarEnhancedAudioManager) car.getCarManager("enhanced_audio");
            this.mCarAudioManager = (CarAudioManager) this.mCar.getCarManager("audio");
            return;
        }
        synchronized (this) {
            this.mCar = null;
            this.mIsBinding = false;
        }
        this.mCarEnhancedAudioManager = null;
        this.mHandler.sendEmptyMessage(2);
    }

    private class HelperHandler extends Handler {
        public HelperHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 2) {
                if (!IKeyServiceBinder.this.mManualDisconnected) {
                    int randTime = (int) (Math.random() * 500.0d);
                    IKeyServiceBinder.this.mHandler.sendEmptyMessageDelayed(5, randTime);
                    return;
                }
                return;
            }
            if (i == 4) {
                synchronized (IKeyServiceBinder.this) {
                    if (IKeyServiceBinder.this.mIsBinding) {
                        Car car = Car.createCar(IKeyServiceBinder.this.mContext, IKeyServiceBinder.this.mHandler, 200000L, IKeyServiceBinder.this.mCarServiceLifecycleListener);
                        if (car != null) {
                            synchronized (IKeyServiceBinder.this) {
                                IKeyServiceBinder.this.mCar = car;
                                IKeyServiceBinder.this.mIsBinding = false;
                            }
                            return;
                        }
                        int randTime2 = (int) (Math.random() * 500.0d);
                        IKeyServiceBinder.this.mHandler.sendEmptyMessageDelayed(5, randTime2);
                        return;
                    }
                    return;
                }
            }
            if (i == 5) {
                if (IKeyServiceBinder.this.mIsBinding || IKeyServiceBinder.this.mManualDisconnected) {
                    return;
                }
                IKeyServiceBinder.this.bindService();
                return;
            }
            if (i != 6) {
                LogUtil.debug(IKeyServiceBinder.TAG, "unrecognized handler what:" + msg.what);
                return;
            }
            if (IKeyServiceBinder.this.mHandler.hasMessages(6)) {
                IKeyServiceBinder.this.mHandler.removeMessages(6);
            }
            IKeyServiceBinder iKeyServiceBinder = IKeyServiceBinder.this;
            iKeyServiceBinder.mCarAdapterServiceManager = CarAdapterServiceManager.getInstance(iKeyServiceBinder.mContext.getApplicationContext());
            IKeyServiceBinder iKeyServiceBinder2 = IKeyServiceBinder.this;
            iKeyServiceBinder2.mCarAdapterBinderState = iKeyServiceBinder2.mCarAdapterServiceManager.getCarAdapterBinderState();
            LogUtil.info(IKeyServiceBinder.TAG, "mCarAdapterBinderState is " + IKeyServiceBinder.this.mCarAdapterBinderState);
            if (IKeyServiceBinder.this.mCarAdapterBinderState) {
                IKeyServiceBinder.this.addInstumentStatusListener();
            } else {
                IKeyServiceBinder.this.mHandler.sendEmptyMessageDelayed(6, 500L);
            }
        }
    }

    public boolean bindService() {
        synchronized (this) {
            if (this.mCar != null && this.mCar.isConnected()) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mIsBinding = true;
            this.mManualDisconnected = false;
            this.mHandler.sendEmptyMessage(4);
            return true;
        }
    }

    public boolean unbindService() {
        synchronized (this) {
            if (this.mCar != null && this.mCar.isConnected()) {
                if (this.mIsBinding) {
                    return false;
                }
                this.mManualDisconnected = true;
                return true;
            }
            return true;
        }
    }

    public /* synthetic */ void lambda$new$1$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtil.error(TAG, " mIKeyEventListener keyCode :" + keyCode + ", action : " + action);
        if (action == 0) {
            if (keyCode == 17 || keyCode == 502) {
                adjustVolumeUp();
                return;
            }
            if (keyCode == 18 || keyCode == 503) {
                adjustVolumeDown();
                return;
            } else {
                if (keyCode == 9) {
                    this.mWheelDown = true;
                    this.mWheelDownTime = SystemClock.elapsedRealtime();
                    return;
                }
                return;
            }
        }
        if (action == 1) {
            if (keyCode != 9) {
                callbackOnKeyClick(keyCode, 1);
                return;
            }
            if (keyCode == 9) {
                if (!this.mWheelDown) {
                    LogUtil.error(TAG, "miss a KEYCODE_WHEEL down event, return");
                    return;
                }
                if (this.mBlockWheelEventStatus) {
                    this.mBlockWheelEventStatus = false;
                    this.mWheelDown = false;
                    LogUtil.error(TAG, "mBlockWheelEventStatus is true miss a KEYCODE_WHEEL down event, return");
                    return;
                }
                if (this.mMenuDelayRemoveStatus) {
                    this.mMenuDelayRemoveStatus = false;
                    this.mWheelDown = false;
                    LogUtil.error(TAG, "mMenuDelayRemoveStatus is true miss a KEYCODE_WHEEL down event, return");
                    return;
                }
                this.mWheelDown = false;
                if (SystemClock.elapsedRealtime() - this.mWheelDownTime >= 800) {
                    LogUtil.error(TAG, "LongPress no respond");
                    return;
                }
                if (this.mCarEnhancedAudioManager != null) {
                    CarAudioManager carAudioManager = this.mCarAudioManager;
                    if (carAudioManager != null) {
                        int groupCount = carAudioManager.getVolumeGroupCount();
                        int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                        HashMap<Integer, Boolean> muteStateMap = new HashMap<>();
                        for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                            if (groupIndex != callGroupId) {
                                boolean muteState = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, groupIndex);
                                LogUtil.info(TAG, "get group id : " + groupIndex + "muteState : " + muteState);
                                muteStateMap.put(Integer.valueOf(groupIndex), Boolean.valueOf(muteState));
                            }
                        }
                        if (muteStateMap.containsValue(true)) {
                            LogUtil.info(TAG, "state is mute");
                            volumnUnMute();
                            return;
                        } else {
                            LogUtil.info(TAG, "state is unmute");
                            volumeMute();
                            return;
                        }
                    }
                    LogUtil.error(TAG, "mCarAudioManager is null");
                    return;
                }
                LogUtil.error(TAG, "mCarEnhancedAudioManager is null");
            }
        }
    }

    public /* synthetic */ void lambda$new$2$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if (action == 1) {
            this.mWheelDown = false;
        }
        LogUtil.info(TAG, " mClusterMenuKeyEventListener keyCode :" + keyCode + ", action : " + action);
    }

    public /* synthetic */ void lambda$new$3$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if (action == 1) {
            this.mWheelDown = false;
        }
        LogUtil.info(TAG, " mClusterMenuKeyLongEventListener keyCode :" + keyCode + ", action : " + action);
    }

    public /* synthetic */ void lambda$new$4$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if (action == 1) {
            this.mWheelDown = false;
        }
        LogUtil.info(TAG, " mAccelerationTimerKeyEventListener keyCode :" + keyCode + ", action : " + action);
    }

    public /* synthetic */ void lambda$new$5$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtil.info(TAG, " mIKeyLongPressEventListener keyCode :" + keyCode + ", action : " + action);
        if (action == 0) {
            callbackOnKeyClick(keyCode, 2);
        }
    }

    static /* synthetic */ void lambda$new$6(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtil.info(TAG, " mWheelKeyLongPressEventListener keyCode :" + keyCode + ", action : " + action);
    }

    public /* synthetic */ void lambda$new$7$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtil.debug(TAG, " mSceneKeyEventListener keyCode :" + keyCode + ", action : " + action);
        if (action == 1) {
            callbackOnKeyEvent(keyEvent, TS_TO_BYD_SCENE_MAP.get(Integer.valueOf(sceneType)).intValue(), targetDisplay);
        }
    }

    public /* synthetic */ void lambda$new$8$IKeyServiceBinder(KeyEvent keyEvent, int sceneType, int targetDisplay) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtil.debug(TAG, " mSceneKeyLongPressEventListener keyCode :" + keyCode + ", action : " + action);
        if (action == 0) {
            callbackOnLongKeyEvent(keyEvent, TS_TO_BYD_SCENE_MAP.get(Integer.valueOf(sceneType)).intValue(), targetDisplay);
        }
    }

    public void subscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mKeyCallback.register(callback);
        }
    }

    public void unsubscribeKeyCallback(IKeySettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mKeyCallback.unregister(callback);
        }
    }

    public void subscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
        if (callback != null) {
            this.mSceneKeyCallback.register(callback);
        }
    }

    public void unsubscribeSceneKeyCallback(IKeySettingsEventCallback callback) throws RemoteException {
        if (callback != null) {
            this.mSceneKeyCallback.unregister(callback);
        }
    }

    public void subscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLongSceneKeyCallback.register(callback);
        }
    }

    public void unsubscribeSceneLongPressKeyCallback(IKeySettingsLongPressEventCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLongSceneKeyCallback.unregister(callback);
        }
    }

    public void addHighPriorityScene(int sceneId) {
        if (BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)) != null) {
            try {
                this.mHardKeyManager.addKeyEventListener(this.mSceneKeyEventListener, BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)).intValue());
                this.mHardKeyManager.addLongPressEventListener(this.mSceneKeyLongPressEventListener, BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)).intValue());
            } catch (Exception ex) {
                LogUtil.error(TAG, "addHighPriorityScene ex ", ex);
            }
        }
    }

    public void removeHighPriorityScene(int sceneId) {
        if (BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)) != null) {
            try {
                this.mHardKeyManager.removeKeyEventListener(this.mSceneKeyEventListener, BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)).intValue());
                this.mHardKeyManager.removeLongPressEventListener(this.mSceneKeyLongPressEventListener, BYD_TO_TS_SCENE_MAP.get(Integer.valueOf(sceneId)).intValue());
            } catch (Exception ex) {
                LogUtil.error(TAG, "removeHighPriorityScene ex ", ex);
            }
        }
    }

    public void callbackOnIdlerWheel(int value) {
        LogUtil.info(TAG, "callbackOnIdlerWheel value " + value);
        this.mKeyCallback.onIdlerWheel(value);
    }

    public void callbackOnKeyClick(int keyValue, int state) {
        LogUtil.info(TAG, "callbackOnKeyClick keyValue " + keyValue + ", state : " + state);
        this.mKeyCallback.onKeyClick(keyValue, state);
    }

    public void callbackOnKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        LogUtil.info(TAG, "callbackOnKeyEvent event " + event + " sceneType: targetDisplay: " + targetDisplay);
        this.mSceneKeyCallback.onKeyEvent(event, sceneType, targetDisplay);
    }

    public void callbackOnLongKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        LogUtil.info(TAG, "callbackOnLongKeyEvent event " + event + " sceneType: targetDisplay: " + targetDisplay);
        this.mLongSceneKeyCallback.onLongKeyEvent(event, sceneType, targetDisplay);
    }

    private void adjustVolumeUp() {
        LogUtil.info(TAG, "adjustVolumeUp");
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null) {
            LogUtil.error(TAG, "mCarEnhancedAudioManager is null");
            return;
        }
        try {
            if (this.mIsAndroidAuto) {
                int groupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 11);
                this.mCarEnhancedAudioManager.adjustSuggestedGroupVolume(0, groupId, 1, 1, 1);
            } else {
                carEnhancedAudioManager.adjustVolume(0, 1, 1, 1);
            }
            if (!judgeAudioFocusIsPhone()) {
                int groupCount = this.mCarAudioManager.getVolumeGroupCount();
                int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                HashMap<Integer, Boolean> muteStateMap = new HashMap<>();
                for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                    if (groupIndex != callGroupId) {
                        boolean muteState = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, groupIndex);
                        LogUtil.info(TAG, "adjustVolumeUp get group id : " + groupIndex + "muteState : " + muteState);
                        muteStateMap.put(Integer.valueOf(groupIndex), Boolean.valueOf(muteState));
                    }
                }
                if (muteStateMap.containsValue(true)) {
                    LogUtil.info(TAG, "adjustVolumeUp state is mute");
                    volumnUnMute();
                } else {
                    LogUtil.info(TAG, "state is unmute");
                }
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "adjustVolumeUp ex", ex);
        }
    }

    private void adjustVolumeDown() {
        LogUtil.info(TAG, "adjustVolumeDown");
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null) {
            LogUtil.error(TAG, "mCarEnhancedAudioManager is null");
            return;
        }
        try {
            if (this.mIsAndroidAuto) {
                int groupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 11);
                this.mCarEnhancedAudioManager.adjustSuggestedGroupVolume(0, groupId, -1, 1, 1);
            } else {
                carEnhancedAudioManager.adjustVolume(0, -1, 1, 1);
            }
            if (!judgeAudioFocusIsPhone()) {
                int groupCount = this.mCarAudioManager.getVolumeGroupCount();
                int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                HashMap<Integer, Boolean> muteStateMap = new HashMap<>();
                for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                    if (groupIndex != callGroupId) {
                        boolean muteState = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, groupIndex);
                        LogUtil.info(TAG, "adjustVolumeDown get group id : " + groupIndex + "muteState : " + muteState);
                        muteStateMap.put(Integer.valueOf(groupIndex), Boolean.valueOf(muteState));
                    }
                }
                if (muteStateMap.containsValue(true)) {
                    LogUtil.info(TAG, "adjustVolumeDown state is mute");
                    volumnUnMute();
                } else {
                    LogUtil.info(TAG, "adjustVolumeDown state is unmute");
                }
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "adjustVolumeDown ex", ex);
        }
    }

    private boolean judgeAudioFocusIsPhone() {
        List<AudioFocusInfo> audioFocusHoldersForZoneId = this.mCarEnhancedAudioManager.getAudioFocusHoldersForZoneId(0);
        LogUtil.info(TAG, "judgeAudioFocusIsPhone start and audioFocusHoldersForZoneId size: " + audioFocusHoldersForZoneId.size());
        for (int i = 0; i < audioFocusHoldersForZoneId.size(); i++) {
            int usage = audioFocusHoldersForZoneId.get(i).getAttributes().getUsage();
            LogUtil.info(TAG, "adjustVolumeDown get audio focus usage :" + usage);
            if (usage == 2 || usage == 32) {
                LogUtil.info(TAG, "adjustVolumeDown get audio focus usage is phone to return true");
                return true;
            }
        }
        return false;
    }

    private void volumeMute() {
        LogUtil.info(TAG, "volumeMute");
        if (this.mCarEnhancedAudioManager == null) {
            LogUtil.error(TAG, "mCarEnhancedAudioManager is null");
            return;
        }
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            LogUtil.error(TAG, "mCarAudioManager is null");
            return;
        }
        try {
            int groupCount = carAudioManager.getVolumeGroupCount();
            int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
            for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                if (groupIndex != callGroupId) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, true, 0);
                    LogUtil.debug(TAG, "volumeMute muteId = " + groupIndex);
                }
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "volumeMute ex", ex);
        }
    }

    private void volumnUnMute() {
        LogUtil.info(TAG, "volumnUnMute");
        if (this.mCarEnhancedAudioManager == null) {
            LogUtil.error(TAG, "mCarEnhancedAudioManager is null");
            return;
        }
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            LogUtil.error(TAG, "mCarAudioManager is null");
            return;
        }
        try {
            int groupCount = carAudioManager.getVolumeGroupCount();
            int callGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
            for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                if (groupIndex != callGroupId) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, false, 0);
                    LogUtil.debug(TAG, "volumnUnMute muteId = " + groupIndex);
                }
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "volumnUnMute ex", ex);
        }
    }

    public class VrReceiver extends BroadcastReceiver {
        public VrReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null || intent.getStringExtra(IKeyServiceBinder.WHO) == null) {
                LogUtil.debug(IKeyServiceBinder.TAG, "intent or action or who is null");
                return;
            }
            LogUtil.info(IKeyServiceBinder.TAG, "action: " + intent.getAction() + ",who: " + intent.getStringExtra(IKeyServiceBinder.WHO));
            String action = intent.getAction();
            byte b = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != -1988150117) {
                if (iHashCode == -658999808 && action.equals(IKeyServiceBinder.VOICE_REQUEST_RELEASE)) {
                    b = 1;
                }
            } else if (action.equals(IKeyServiceBinder.VOICE_REQUEST_START)) {
                b = 0;
            }
            if (b == 0) {
                if (IKeyServiceBinder.ANDROID_AUTO.equals(intent.getStringExtra(IKeyServiceBinder.WHO))) {
                    LogUtil.info(IKeyServiceBinder.TAG, "mIsAndroidAuto is true");
                    IKeyServiceBinder.this.mIsAndroidAuto = true;
                    return;
                } else {
                    IKeyServiceBinder.this.mIsAndroidAuto = false;
                    return;
                }
            }
            if (b == 1 && IKeyServiceBinder.ANDROID_AUTO.equals(intent.getStringExtra(IKeyServiceBinder.WHO))) {
                LogUtil.info(IKeyServiceBinder.TAG, "mIsAndroidAuto is false");
                IKeyServiceBinder.this.mIsAndroidAuto = false;
            }
        }
    }
}
