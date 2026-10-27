package com.ts.appservice.power.hardkey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.power.util.LogUtil;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.input.HardkeyConstrants;
import ts.car.service.input.IHardKeyManager;

/* JADX INFO: loaded from: classes.dex */
public class ExtHardKeyManager {
    private static final String FALSE = "false";
    private static final String KEY_SOUND_ENABLE = "sys.hardkeysound.enable";
    private static final String TAG = ExtHardKeyManager.class.getSimpleName();
    private static final String TRUE = "true";
    private static volatile ExtHardKeyManager sInstance;
    private IHardKeyManager mHardKeyManager;
    private HardKeyEventCenter mKeyInterceptCenter;
    private CarPowerBinder mTsCarPowerBinder;
    private volatile int mCurHardKeyState = -1;
    private boolean mIsDisableHardKey = false;
    private boolean mDisclaimerSceneFlag = false;
    private boolean mBlackModeSceneFlag = false;
    private Object mLock = new Object();
    private BroadcastReceiver mDebugReceiver = new BroadcastReceiver() { // from class: com.ts.appservice.power.hardkey.ExtHardKeyManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean initHardKeyManager = intent.getBooleanExtra("init_hard_key_manager", true);
            if (initHardKeyManager) {
                ExtHardKeyManager.this.initManager(context);
            }
            boolean hardKeyStatus = intent.getBooleanExtra("hard_key_status", true);
            if (hardKeyStatus) {
                ExtHardKeyManager.this.enableHardKeyEvent();
            } else {
                ExtHardKeyManager.this.disableHardKeyEvent();
            }
        }
    };
    private IServiceConnectionListener mConnectionListener = new IServiceConnectionListener() { // from class: com.ts.appservice.power.hardkey.ExtHardKeyManager.2
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            LogUtil.info(ExtHardKeyManager.TAG, "onServiceConnected");
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            LogUtil.warning(ExtHardKeyManager.TAG, "onServiceDisconnected");
        }
    };

    public static ExtHardKeyManager getInstance(Context context, CarPowerBinder carPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtHardKeyManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtHardKeyManager(context, carPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private ExtHardKeyManager(Context context, CarPowerBinder carPowerBinder) {
        LogUtil.info(TAG, "TsHardKeyManager");
        this.mTsCarPowerBinder = carPowerBinder;
        this.mKeyInterceptCenter = new HardKeyEventCenter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ts.power.debug_bc");
        context.registerReceiver(this.mDebugReceiver, intentFilter);
        initManager(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initManager(Context context) {
        LogUtil.info(TAG, "initManager");
        try {
            this.mHardKeyManager = (IHardKeyManager) ServiceProvider.get(IHardKeyManager.class, context.getApplicationContext());
            LogUtil.info(TAG, " ==>-- get HardKeyManager = " + this.mHardKeyManager);
            this.mHardKeyManager.registerConnectionListener(this.mConnectionListener);
        } catch (RuntimeException exc) {
            LogUtil.info(TAG, Log.getStackTraceString(exc));
        }
    }

    public int disableHardKeyEvent() {
        LogUtil.info(TAG, "disableHardKeyEvent");
        if (this.mHardKeyManager == null) {
            LogUtil.info(TAG, "disableHardKeyEvent mHardKeyManager : null");
            return -1;
        }
        LogUtil.info(TAG, "disableHardKeyEvent mIsDisableHardKey : " + this.mIsDisableHardKey);
        if (!this.mIsDisableHardKey) {
            try {
                this.mHardKeyManager.addKeyEventListener(this.mKeyInterceptCenter, HardkeyConstrants.SCENE_DISABLE_ALL_KEYS);
                this.mHardKeyManager.addLongPressEventListener(this.mKeyInterceptCenter, HardkeyConstrants.SCENE_DISABLE_ALL_KEYS);
                setCurHardKeyState(0);
                this.mIsDisableHardKey = true;
                setKeySoundEnable(false);
            } catch (Exception exc) {
                LogUtil.info(TAG, Log.getStackTraceString(exc));
            }
        }
        return this.mCurHardKeyState;
    }

    public int enableHardKeyEvent() {
        LogUtil.info(TAG, "enableHardKeyEvent");
        if (this.mHardKeyManager == null) {
            LogUtil.info(TAG, "enableHardKeyEvent mHardKeyManager : null");
            return -1;
        }
        LogUtil.info(TAG, "enableHardKeyEvent mIsDisableHardKey : " + this.mIsDisableHardKey);
        if (this.mIsDisableHardKey) {
            try {
                this.mHardKeyManager.removeKeyEventListener(this.mKeyInterceptCenter, HardkeyConstrants.SCENE_DISABLE_ALL_KEYS);
                this.mHardKeyManager.removeLongPressEventListener(this.mKeyInterceptCenter, HardkeyConstrants.SCENE_DISABLE_ALL_KEYS);
                setCurHardKeyState(1);
                this.mIsDisableHardKey = false;
                setKeySoundEnable(true);
            } catch (Exception exc) {
                LogUtil.info(TAG, Log.getStackTraceString(exc));
            }
        } else {
            setCurHardKeyState(1);
        }
        return this.mCurHardKeyState;
    }

    public void handleBlackModeKeyEvent() {
        LogUtil.info(TAG, "handleBlackModeKeyEvent");
        CarPowerBinder carPowerBinder = this.mTsCarPowerBinder;
        if (carPowerBinder == null) {
            LogUtil.info(TAG, "handleBlackModeKeyEvent mTsCarPowerBinder : null");
            return;
        }
        if (carPowerBinder.isDisclaimerShowing() && !this.mDisclaimerSceneFlag) {
            this.mDisclaimerSceneFlag = true;
        } else {
            LogUtil.debug(TAG, "mDisclaimerSceneFlag = " + this.mDisclaimerSceneFlag + "do not addKeyEvent again");
        }
        if (!this.mBlackModeSceneFlag) {
            this.mBlackModeSceneFlag = true;
            return;
        }
        LogUtil.debug(TAG, "mBlackModeSceneFlag = " + this.mBlackModeSceneFlag + "do not addKeyEvent again");
    }

    public void unhandleBlackModeKeyEvent() {
        LogUtil.info(TAG, "unhandleBlackModeKeyEvent");
        CarPowerBinder carPowerBinder = this.mTsCarPowerBinder;
        if (carPowerBinder == null) {
            LogUtil.info(TAG, "unhandleBlackModeKeyEvent mTsCarPowerBinder : null");
            return;
        }
        if (carPowerBinder.isDisclaimerShowing() && this.mDisclaimerSceneFlag) {
            this.mDisclaimerSceneFlag = false;
        }
        if (this.mBlackModeSceneFlag) {
            this.mBlackModeSceneFlag = false;
        }
    }

    private void setCurHardKeyState(int state) {
        LogUtil.info(TAG, "setCurHardKeyState state : " + state);
        synchronized (this.mLock) {
            this.mCurHardKeyState = state;
        }
        LogUtil.info(TAG, "setCurHardKeyState mCurHardKeyState : " + this.mCurHardKeyState);
    }

    public int getCurHardKeyState() {
        int i;
        LogUtil.info(TAG, "getCurHardKeyState mCurHardKeyState : " + this.mCurHardKeyState);
        synchronized (this.mLock) {
            i = this.mCurHardKeyState;
        }
        return i;
    }

    private void setKeySoundEnable(boolean state) {
        LogUtil.info(TAG, "setKeySoundEnable state : " + state);
        if (state) {
            LogUtil.info(TAG, "setKeySoundEnable enable");
        } else {
            LogUtil.info(TAG, "setKeySoundEnable disable");
        }
    }
}
