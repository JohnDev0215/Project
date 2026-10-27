package com.ts.carplay.lib.resourcemanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayStartInfo;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayResourceManager {
    private static final int BACKCAMERA_CONFIRM = 3;
    private static final int EVENT_BACKCAMERA_OFF = 2;
    private static final int EVENT_BACKCAMERA_ON = 1;
    private static final int EVENT_CONFIRM_TIMEROUT = 500;
    private static final int EVENT_SCREEN_OFF = 8;
    private static final int EVENT_SCREEN_ON = 7;
    private static final int SCREEN_CONFIRM = 9;
    private static final String TAG = "CarPlayResourceManager";
    private static final int UNUSED_INITIAL_VALUE = -2;
    static CarPlayResourceManager sCarPlayResourceManager = null;
    private AudioResourceManager mAudioResourceManager;
    private CarPlayManager mCarPlayManager;
    private CpScreenOffManager mCpScreenOffManager;
    private ExternalEventHandler mExternalEventHandler;
    private CarPlayLastUserMode mLastUserMode;
    private ScreenResourceManager mScreenResourceManager;
    protected Object mLock = new Object();
    private int mCurrentScene = -1;
    private int mCurVrSpeechEntity = -2;
    private int mCurVrSpeechMode = -2;
    private int mCurPhoneEntity = -2;
    private int mCurPhoneSpeech = -2;
    private int mCurNaviEntity = -2;
    private boolean mPreDisplayStatus = false;

    public CarPlayResourceManager(CarPlayManager manager) {
        if (sCarPlayResourceManager != null) {
            throw new IllegalStateException("sCarPlayResourceManager was already created, use getInstance instead.");
        }
        sCarPlayResourceManager = this;
        this.mCarPlayManager = manager;
        this.mLastUserMode = new CarPlayLastUserMode(manager);
        this.mScreenResourceManager = new ScreenResourceManager(manager);
        this.mAudioResourceManager = new AudioResourceManager(manager);
        this.mCpScreenOffManager = new CpScreenOffManager(this.mScreenResourceManager);
        createExternalEventHandler();
    }

    public static CarPlayResourceManager getInstance() {
        CarPlayResourceManager carPlayResourceManager = sCarPlayResourceManager;
        if (carPlayResourceManager == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayResourceManager before construct was called.");
        }
        return carPlayResourceManager;
    }

    public void onConnected(LinkDevice device) {
    }

    public void onDisconnected(LinkDevice device) {
    }

    public void onSessionEstablished() {
        this.mScreenResourceManager.onSessionEstablished();
        this.mAudioResourceManager.onSessionEstablished();
        this.mCpScreenOffManager.onSessionEstablished();
    }

    public void onSessionDestoryed(LinkDevice device) {
        this.mScreenResourceManager.onSessionDestoryed(device);
        this.mAudioResourceManager.onSessionDestoryed(device);
        this.mCpScreenOffManager.onSessionDestoryed();
        this.mPreDisplayStatus = false;
        resetAllAppStates();
    }

    public void wiredCpScreenDestoryed(LinkDevice device) {
        this.mScreenResourceManager.onSessionDestoryed(device);
    }

    public void sessionDisconnected(LinkDevice device) {
        this.mScreenResourceManager.onSessionDestoryed(device);
        this.mAudioResourceManager.onSessionDestoryed(device);
    }

    public void screenChangedToCarPlay(boolean isQuested) {
        LogUtil.info(TAG, "screenChangedToCarPlay isQuested = " + isQuested);
        this.mScreenResourceManager.screenChangedToCarPlay(isQuested);
    }

    public void deviceListTouched() {
        LogUtil.info(TAG, "deviceListTouched");
        this.mScreenResourceManager.deviceListTouched();
    }

    public void vehiclePowerModeChangedTo(boolean ignoff) {
        LogUtil.info(TAG, "vehiclePowerModeChangedTo, ignoff = " + ignoff);
        this.mScreenResourceManager.vehiclePowerModeChangedTo(ignoff);
    }

    public void accessoryAudioChange(int priority, String borrowId) {
        LogUtil.info(TAG, "accessoryAudioChange, priority = " + priority + "borrowId" + borrowId);
        this.mAudioResourceManager.accessoryAudioChange(priority, borrowId);
    }

    public void accessoryScreenChange(int priority, boolean playbk, String borrowId, int action) {
        this.mScreenResourceManager.accessoryScreenChange(priority, playbk, borrowId, action);
    }

    public void nativeVrChangedNeedBorrow() {
        this.mAudioResourceManager.carplayAudioChangedBorrow();
        this.mScreenResourceManager.carplayScreenChangedBorrow();
    }

    public void nativeVrChangedNeedUnBorrow() {
        this.mAudioResourceManager.carplayAudioChangedUnBorrow();
        this.mScreenResourceManager.carplayScreenChangedUnBorrow();
    }

    public void backCameraStatusChangedTo(CpConst.AppState state) {
        synchronized (this.mLock) {
            if (state == CpConst.AppState.APP_ON) {
                this.mExternalEventHandler.sendEmptyMessage(1);
            } else {
                this.mExternalEventHandler.sendEmptyMessage(2);
            }
        }
    }

    public void backCameraStatusChangedToInner(CpConst.AppState state) {
        LogUtil.info(TAG, "backCameraStatusChangedToInner, state=" + state);
        this.mScreenResourceManager.backCameraStatusChangedTo(state);
        this.mAudioResourceManager.backCameraStatusChangedTo(state);
    }

    public void screenStatusChangedTo(CpConst.ScreenStatus screenStatus) {
        synchronized (this.mLock) {
            if (screenStatus == CpConst.ScreenStatus.STATUS_ON) {
                this.mExternalEventHandler.sendEmptyMessage(7);
            } else {
                this.mExternalEventHandler.sendEmptyMessage(8);
            }
        }
    }

    public void forceChangeToTake() {
        this.mScreenResourceManager.forceChangeToTakeState();
    }

    public void screenStatusChangedToInner(CpConst.ScreenStatus screenStatus) {
        LogUtil.info(TAG, "screenStatusChangedToInner screenStatus = " + screenStatus);
        this.mCpScreenOffManager.screenStatusChangedTo(screenStatus);
    }

    private void createExternalEventHandler() {
        Looper looper = Looper.myLooper();
        if (looper != null) {
            this.mExternalEventHandler = new ExternalEventHandler(this, looper);
            return;
        }
        Looper looper2 = Looper.getMainLooper();
        if (looper2 != null) {
            this.mExternalEventHandler = new ExternalEventHandler(this, looper2);
        } else {
            this.mExternalEventHandler = null;
        }
    }

    public class ExternalEventHandler extends Handler {
        private CpConst.AppState backCameraStatus;
        private CarPlayResourceManager mResourceManager;
        private CpConst.ScreenStatus screenOffStatus;

        public ExternalEventHandler(CarPlayResourceManager rm, Looper looper) {
            super(looper);
            this.backCameraStatus = CpConst.AppState.APP_OFF;
            this.screenOffStatus = CpConst.ScreenStatus.STATUS_OFF;
            this.mResourceManager = rm;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            synchronized (CarPlayResourceManager.this.mLock) {
                LogUtil.info(CarPlayResourceManager.TAG, "handleMessage Start: " + msg.what);
                int i = msg.what;
                if (i == 1) {
                    this.backCameraStatus = CpConst.AppState.APP_ON;
                    if (hasMessages(3)) {
                        LogUtil.info(CarPlayResourceManager.TAG, "EVENT_BACKCAMERA_ON wait final event");
                    } else {
                        sendEmptyMessageDelayed(3, 500L);
                    }
                } else if (i == 2) {
                    this.backCameraStatus = CpConst.AppState.APP_OFF;
                    if (hasMessages(3)) {
                        LogUtil.info(CarPlayResourceManager.TAG, "EVENT_BACKCAMERA_OFF wait final event");
                    } else {
                        sendEmptyMessageDelayed(3, 500L);
                    }
                } else if (i == 3) {
                    LogUtil.info(CarPlayResourceManager.TAG, "BACKCAMERA_CONFIRM backCameraStatus: " + this.backCameraStatus);
                    this.mResourceManager.backCameraStatusChangedToInner(this.backCameraStatus);
                } else if (i == 7) {
                    this.screenOffStatus = CpConst.ScreenStatus.STATUS_ON;
                    if (hasMessages(9)) {
                        LogUtil.info(CarPlayResourceManager.TAG, "EVENT_SCREEN_ON wait final event");
                    } else {
                        sendEmptyMessageDelayed(9, 500L);
                    }
                } else if (i == 8) {
                    this.screenOffStatus = CpConst.ScreenStatus.STATUS_OFF;
                    if (hasMessages(9)) {
                        LogUtil.info(CarPlayResourceManager.TAG, "EVENT_SCREEN_OFF wait final event");
                    } else {
                        sendEmptyMessageDelayed(9, 500L);
                    }
                } else if (i == 9) {
                    LogUtil.info(CarPlayResourceManager.TAG, "SCREEN_CONFIRM screenOffStatus: " + this.screenOffStatus);
                    this.mResourceManager.screenStatusChangedToInner(this.screenOffStatus);
                } else {
                    LogUtil.error(CarPlayResourceManager.TAG, "handleMessage() Unknown event type: " + msg.what);
                }
            }
            LogUtil.info(CarPlayResourceManager.TAG, "handleMessage end: " + msg.what);
        }
    }

    public void appStateChanged(int phoneEntity, int tbtEntity, int speechEntity, int speechMode) {
        int vrSpeechEntity;
        int vrSpeechMode;
        int phoneSpeechMode = -1;
        if (phoneEntity != 0) {
            phoneSpeechMode = speechMode;
        }
        if (this.mCurPhoneEntity != phoneEntity || this.mCurPhoneSpeech != phoneSpeechMode) {
            this.mCurPhoneEntity = phoneEntity;
            this.mCurPhoneSpeech = phoneSpeechMode;
            CarPlayManager carPlayManager = this.mCarPlayManager;
            if (carPlayManager != null) {
                carPlayManager.onCarPlayAppStateChanged(0, phoneEntity == 1, phoneSpeechMode);
                this.mCarPlayManager.onCarPlayAppStateChanged(0, phoneEntity, phoneSpeechMode);
            }
        }
        if (this.mCurNaviEntity != tbtEntity) {
            this.mCurNaviEntity = tbtEntity;
            CarPlayManager carPlayManager2 = this.mCarPlayManager;
            if (carPlayManager2 != null) {
                carPlayManager2.onCarPlayAppStateChanged(1, tbtEntity == 1, speechMode);
                this.mCarPlayManager.onCarPlayAppStateChanged(1, tbtEntity, speechMode);
            }
        }
        if (this.mCurPhoneEntity == 0) {
            vrSpeechEntity = speechEntity;
            vrSpeechMode = speechMode;
        } else {
            vrSpeechEntity = 0;
            vrSpeechMode = -1;
        }
        if (this.mCurVrSpeechEntity != vrSpeechEntity || this.mCurVrSpeechMode != vrSpeechMode) {
            this.mCurVrSpeechEntity = vrSpeechEntity;
            this.mCurVrSpeechMode = vrSpeechMode;
            CarPlayManager carPlayManager3 = this.mCarPlayManager;
            if (carPlayManager3 != null) {
                carPlayManager3.onCarPlayAppStateChanged(2, vrSpeechEntity == 1, vrSpeechMode);
                this.mCarPlayManager.onCarPlayAppStateChanged(2, vrSpeechEntity, vrSpeechMode);
            }
        }
    }

    private void resetAllAppStates() {
        this.mCurNaviEntity = resetAppState(1, this.mCurNaviEntity);
        this.mCurPhoneEntity = resetAppState(0, this.mCurPhoneEntity);
        this.mCurVrSpeechEntity = resetAppState(2, this.mCurVrSpeechEntity);
        this.mCurPhoneSpeech = -1;
    }

    private int resetAppState(int appState, int entity) {
        CarPlayManager carPlayManager = this.mCarPlayManager;
        if (carPlayManager != null && entity == 1) {
            carPlayManager.onCarPlayAppStateChanged(appState, false, -1);
            this.mCarPlayManager.onCarPlayAppStateChanged(appState, 0, -1);
            return -2;
        }
        return -2;
    }

    public void resourceChanged(int screenEntity, int audioEntity, int perScreenEntity, int perAudioEntity) {
        this.mScreenResourceManager.screenResourceChanged(screenEntity, perScreenEntity);
        if (screenEntity == 1) {
            CarPlayManager carPlayManager = this.mCarPlayManager;
            if (carPlayManager != null && !this.mPreDisplayStatus) {
                carPlayManager.onCarPlayDisplayStatusChanged(true);
                this.mPreDisplayStatus = true;
            }
        } else {
            CarPlayManager carPlayManager2 = this.mCarPlayManager;
            if (carPlayManager2 != null && this.mPreDisplayStatus) {
                carPlayManager2.onCarPlayDisplayStatusChanged(false);
                this.mPreDisplayStatus = false;
            }
        }
        this.mAudioResourceManager.audioEntityChanged(audioEntity, perAudioEntity);
    }

    public void duckAudio(int durationMs, int volume) {
        AudioResourceManager audioResourceManager = this.mAudioResourceManager;
        if (audioResourceManager != null) {
            audioResourceManager.duckAudio(durationMs, volume);
        }
    }

    public void unduckAudio(int durationMs) {
        AudioResourceManager audioResourceManager = this.mAudioResourceManager;
        if (audioResourceManager != null) {
            audioResourceManager.unduckAudio(durationMs);
        }
    }

    public int videoResourceRequest() {
        LogUtil.info(TAG, "videoResourceRequest");
        return this.mScreenResourceManager.screenResourceRequest();
    }

    public int audioResourceRequest(int requestType, int sampleRate) {
        LogUtil.info(TAG, "audioResourceRequest requestType:" + requestType + ", sampleRate: " + sampleRate);
        return this.mAudioResourceManager.audioResourceRequest(requestType, sampleRate);
    }

    public void notification(int notify, int param1, int param2, int param3) {
        synchronized (this.mLock) {
            try {
                if (notify == 1) {
                    this.mCarPlayManager.projectionStateChange(true);
                } else if (notify == 2) {
                    this.mCarPlayManager.projectionStateChange(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mScreenResourceManager.notification(notify, param1, param2, param3);
        if (notify == 5 || notify == 6 || notify == 11) {
            this.mAudioResourceManager.audioResourceAbandon(param1, param2, param3);
        }
    }

    public CarPlayStartInfo getLastResourceState(CpConst.CarPlayResource resourceType) {
        if (resourceType == CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN) {
            return this.mScreenResourceManager.getLastResourceState();
        }
        return this.mAudioResourceManager.getLastResourceState();
    }

    public boolean isResourceBorrowed(CpConst.CarPlayResource resourceType) {
        if (resourceType == CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN) {
            return this.mScreenResourceManager.isBorrowed();
        }
        return this.mAudioResourceManager.isBorrowed();
    }

    public AudioResourceManager getAudioResourceManager() {
        return this.mAudioResourceManager;
    }

    public void setDeviceMirroringRunning(boolean on, boolean isRunning) {
        this.mScreenResourceManager.setDeviceMirroringRunning(on, isRunning);
    }

    public void releaseCurrentAudio() {
        this.mAudioResourceManager.releaseCurrentAudio();
    }

    public ScreenResourceManager getscreenResourceManager() {
        ScreenResourceManager screenResourceManager = this.mScreenResourceManager;
        if (screenResourceManager != null) {
            return screenResourceManager;
        }
        return null;
    }

    public boolean isInScreenOffState() {
        return this.mCpScreenOffManager.isScreenOff();
    }

    public boolean isCarPlayShown() {
        return this.mScreenResourceManager.isInCarPlayState();
    }

    public boolean isScreenShowAllow() {
        return this.mScreenResourceManager.isScreenShowAllow();
    }

    public void setUserRequest(boolean userRequest) {
        ScreenResourceManager screenResourceManager = this.mScreenResourceManager;
        if (screenResourceManager != null) {
            screenResourceManager.setUserRequest(userRequest);
        }
    }

    public void clearLocalStatus() {
        this.mAudioResourceManager.clearLocalStatus();
        this.mScreenResourceManager.clearLocalStatus();
    }

    public int getPhoneEntity() {
        return this.mCurPhoneEntity;
    }

    public int getPhoneSpeechMode() {
        return this.mCurPhoneSpeech;
    }

    public int getNaviEntity() {
        return this.mCurNaviEntity;
    }

    public int getVrSpeechEntity() {
        return this.mCurVrSpeechEntity;
    }

    public int getVrSpeechMode() {
        return this.mCurVrSpeechMode;
    }
}
