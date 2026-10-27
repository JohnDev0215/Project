package com.ts.appservice.power.ext;

import android.content.Context;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtCarPowerManager {
    public static final int ABANDON_AUDIO_FOCUS = 2;
    public static final int ANDROID_START_READY = 1;
    public static final int BACKLIGHT_OFF = 0;
    public static final int BACKLIGHT_ON = 1;
    public static final int CMD_ANDROID_POWER_READY = 3;
    public static final int CMD_AUDIO_FOCUS = 2;
    public static final int CMD_BACKLIGHT_CONTROL = 6;
    public static final int CMD_BACKLIGHT_SWITCH = 5;
    public static final int CMD_GET_SCREEN = 10;
    public static final int CMD_INIT = 0;
    public static final int CMD_RELEASE_SCREEN = 11;
    public static final int CMD_REQUEST_RESTART_ANDROID = 8;
    public static final int CMD_REQUEST_RESTART_SYSTEM = 9;
    public static final int CMD_SHUTDOWN = 4;
    public static final int CMD_SHUTDOWN_CONFIRM = 1;
    public static final int CMD_SWITCH_WORKMODE = 7;
    public static final int EBCALL_TYPE = 0;
    public static final int NORMAL_SHUTDOWN = 1;
    public static final int OTA_TYPE = 1;
    public static final int POWER_READY = 1;
    public static final int RECOVERY_SHUTDOWN = 2;
    public static final int REQUEST_AUDIO_FOCUS = 1;
    public static final int RESTART_ANDROID = 1;
    public static final int RESTART_ANDROID_TO_RECOVERY = 2;
    public static final int SHUTDOWNCONFIRM_SHUTDOWN_CONFIRM_STATE = 2;
    public static final int SHUTDOWNCONFIRM_SHUTDOWN_PENDING_STATE = 1;
    private static final String TAG = ExtCarPowerManager.class.getSimpleName();
    private static volatile ExtCarPowerManager sInstance;
    private CarPowerBinder mCarPowerBinder;
    private Context mContext;

    public static ExtCarPowerManager getInstance(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtCarPowerManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtCarPowerManager(context, tsCarPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private ExtCarPowerManager(Context context, CarPowerBinder carPowerBinder) {
        LogUtil.info(TAG, "ExtCarPowerManager");
        this.mContext = context;
        this.mCarPowerBinder = carPowerBinder;
        initManager();
    }

    private void initManager() {
        LogUtil.info(TAG, "initManager");
    }

    public boolean sendShutdownConfirmMessage(int state) {
        LogUtil.info(TAG, "sendShutdownConfirmMessage state : " + state);
        LogUtil.info(TAG, "sendShutdownConfirmMessage res : false");
        return false;
    }

    public boolean sendAndroidStartReadyMessage(int state) {
        LogUtil.info(TAG, "sendAndroidStartReadyMessage state : " + state);
        LogUtil.info(TAG, "sendAndroidStartReadyMessage res : false");
        return false;
    }

    public boolean setAppNotifyWorkModeChange(int mode) {
        LogUtil.info(TAG, "carPowerChangeWorkMode mode : " + mode);
        LogUtil.info(TAG, "carPowerChangeWorkMode res : false");
        return false;
    }

    public boolean setAppNotifyShutdownAction(int action) {
        LogUtil.info(TAG, "carPowerShutdownAction action : " + action);
        LogUtil.info(TAG, "carPowerShutdownAction res : false");
        return false;
    }

    public void sendChangeBacklightStateMessage(int state) {
        LogUtil.info(TAG, "sendChangeBacklightStateMessage state : " + state);
    }

    public void sendCarPowerReadyMessage(int state) {
        LogUtil.info(TAG, "sendTsCarPowerReadyMessage state : " + state);
    }

    public void sendRequestRestartAndroid(int mode) {
        LogUtil.info(TAG, "sendRequestRestartAndroid mode : " + mode);
    }

    public void sendRequestAudioFocus() {
        LogUtil.info(TAG, "sendRequestAudioFocus");
    }

    public void sendAbandonAudioFocus() {
        LogUtil.info(TAG, "sendAbandonAudioFocus");
    }

    public void sendGetScreenMessage(int type) {
        LogUtil.info(TAG, "sendGetScreenMessage");
        this.mCarPowerBinder.setScreenBrightnessOnOff(1, 0);
    }

    public void sendReleaseScreenMessage(int type) {
        LogUtil.info(TAG, "sendReleaseScreenMessage");
        this.mCarPowerBinder.setScreenBrightnessOnOff(1, 1);
    }
}
