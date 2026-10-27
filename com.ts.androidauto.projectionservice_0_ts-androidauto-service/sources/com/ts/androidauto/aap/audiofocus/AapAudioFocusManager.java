package com.ts.androidauto.aap.audiofocus;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.audio.AapAudioManager;
import com.ts.androidauto.aap.bluetooth.AapBluetoothManager;
import com.ts.androidauto.aap.common.AapConfig;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes.dex */
public class AapAudioFocusManager implements IAudioControllerListener {
    private static final int AAP_FADE_TIME = 100;
    private static final int AAP_FADE_VOLUME_DUCK = -10;
    private static final int AAP_FADE_VOLUME_UNDUCK = 0;
    private static final int MUTE_STATE_OFF = 0;
    private static final int MUTE_STATE_ON = 1;
    private static final int SLEEP_200 = 200;
    private static final int SLEEP_500 = 500;
    private static AapAudioFocusManager mAapAudioFocusManager;
    private AapController mAapController;
    private IAudioControllerAdapter mAudioControllerAdapter;
    private FocusState mCurrentState;
    private GalIntegration mGal;
    private HandlerThread mHandlerThread;
    private FocusState mLastState;
    private static final String TAG = "AAP" + AapAudioFocusManager.class.getSimpleName();
    private static BluetoothConst.BtPhoneStatus mPhoneStatus = BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE;
    private static BluetoothConst.BtPhoneStatus mBtPhoneStatus = BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE;
    private AapBluetoothManager mAapBluetoothManager = null;
    private final ConcurrentLinkedQueue<FocusRequest> mFocusQue = new ConcurrentLinkedQueue<>();
    private HandleFocusThread mHandleFocusThread = null;
    private int mMdRequestFocusIndex = 0;
    private boolean mIsVrInterruptMedia = false;
    private Handler mOnstopReleaseFocusHandler = null;
    private Object mLock = new Object();
    private boolean mIsPhoneStarted = false;
    private boolean mIsHandleVrFocus = false;
    private boolean mIsMuteBySelf = false;
    private int mCurrentAudioFocus = 0;
    private AapBluetoothManager.PhoneCallStatusListener mPhoneCallStatusListener = new AapBluetoothManager.PhoneCallStatusListener() { // from class: com.ts.androidauto.aap.audiofocus.AapAudioFocusManager.1
        @Override // com.ts.androidauto.aap.bluetooth.AapBluetoothManager.PhoneCallStatusListener
        public void notifyImsPhoneStatus(BluetoothConst.BtPhoneStatus phoneStatus) {
            LogUtil.info(AapAudioFocusManager.TAG, "notifyImsPhoneStatus phoneStatus:" + phoneStatus);
            LogUtil.info(AapAudioFocusManager.TAG, "Ims phone:" + phoneStatus + " bt phone:" + AapAudioFocusManager.mBtPhoneStatus + " handle vr:" + AapAudioFocusManager.this.mIsHandleVrFocus);
            BluetoothConst.BtPhoneStatus unused = AapAudioFocusManager.mPhoneStatus = phoneStatus;
            if (AapAudioFocusManager.mPhoneStatus != BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE || AapAudioFocusManager.mBtPhoneStatus != BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE) {
                AapAudioFocusManager.this.sendPhoneInterruptEnd();
                AapAudioFocusManager.this.mIsHandleVrFocus = false;
            } else if (!AapAudioFocusManager.this.mIsHandleVrFocus) {
                AapAudioFocusManager.this.mIsHandleVrFocus = true;
                AapAudioFocusManager.this.sendPhoneInterruptStart();
            }
        }

        @Override // com.ts.androidauto.aap.bluetooth.AapBluetoothManager.PhoneCallStatusListener
        public void notifyBtPhoneStatus(BluetoothConst.BtPhoneStatus phoneStatus) {
            LogUtil.info(AapAudioFocusManager.TAG, "Bt phone:" + phoneStatus + " ims phone:" + AapAudioFocusManager.mPhoneStatus + " handle vr:" + AapAudioFocusManager.this.mIsHandleVrFocus);
            BluetoothConst.BtPhoneStatus unused = AapAudioFocusManager.mBtPhoneStatus = phoneStatus;
            if (AapAudioFocusManager.mPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE && AapAudioFocusManager.mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE && !AapAudioFocusManager.this.mIsHandleVrFocus) {
                AapAudioFocusManager.this.mIsHandleVrFocus = true;
                AapAudioFocusManager.this.sendPhoneInterruptStart();
            }
            if (AapAudioFocusManager.mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE && AapAudioFocusManager.this.mCurrentAudioFocus == 5) {
                AapAudioFocusManager.this.mIsPhoneStarted = true;
                AapAudioFocusManager.this.sendPhoneInterruptEnd();
            }
        }
    };

    public static synchronized void updateImsPhoneStatus(BluetoothConst.BtPhoneStatus phoneStatus) {
        LogUtil.info(TAG, "updateImsPhoneStatus phoneStatus:" + phoneStatus);
        mPhoneStatus = phoneStatus;
    }

    public static synchronized void updateBtPhoneStatus(BluetoothConst.BtPhoneStatus phoneStatus) {
        LogUtil.info(TAG, "updateBtPhoneStatus phoneStatus:" + phoneStatus);
        mBtPhoneStatus = phoneStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPhoneInterruptStart() {
        this.mIsPhoneStarted = true;
        synchronized (this.mLock) {
            if (StateManager.getInstance().getVrFocusState()) {
                removeDelayFocusMessage(0);
                SystemClock.sleep(200L);
                int result = this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                LogUtil.info(TAG, "Request Hu release success result:" + result);
            } else {
                LogUtil.info(TAG, "notifyPhoneStatus already release VR focus");
            }
        }
        FocusRequest focusRequest = new FocusRequest();
        StateManager.getInstance().setVrFocusState(false);
        focusRequest.huRequest = ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_START;
        focusRequest.isMdRequest = false;
        focusRequest.reqIndex = 0;
        focusRequest.selfAudioId = ResourceConst.AudioId.PROJECTION_AAP_MEDIA;
        focusRequest.causeAudioId = ResourceConst.AudioId.NATIVE_HIGHPRIORITY;
        this.mFocusQue.offer(focusRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPhoneInterruptEnd() {
        AapController aapController;
        FocusRequest focusRequest = new FocusRequest();
        if (this.mIsPhoneStarted && (aapController = this.mAapController) != null && !aapController.isNativePhoneInCall()) {
            focusRequest.huRequest = ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_END;
            focusRequest.isMdRequest = false;
            focusRequest.reqIndex = 0;
            focusRequest.selfAudioId = ResourceConst.AudioId.PROJECTION_AAP_MEDIA;
            focusRequest.causeAudioId = ResourceConst.AudioId.NATIVE_HIGHPRIORITY;
            this.mFocusQue.offer(focusRequest);
        }
        this.mIsPhoneStarted = false;
    }

    public void startHandleFocusThread() {
        LogUtil.verbose(TAG, "startHandleFocusThread is invoke!");
        if (this.mHandleFocusThread != null) {
            stopHandleFocusThread();
        }
        HandleFocusThread handleFocusThread = new HandleFocusThread();
        this.mHandleFocusThread = handleFocusThread;
        handleFocusThread.start();
    }

    public void stopHandleFocusThread() {
        LogUtil.verbose(TAG, "stopHandleFocusThread is invoke!");
        HandleFocusThread handleFocusThread = this.mHandleFocusThread;
        if (handleFocusThread != null) {
            try {
                handleFocusThread.finishFocusThread();
                this.mHandleFocusThread.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            this.mHandleFocusThread = null;
        }
    }

    public class HandleFocusThread extends Thread {
        private boolean handleFocusFlag = true;

        public HandleFocusThread() {
        }

        public void finishFocusThread() {
            this.handleFocusFlag = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FocusState next;
            LogUtil.verbose(AapAudioFocusManager.TAG, "HandleFocusThread run");
            Process.setThreadPriority(-16);
            while (this.handleFocusFlag && !Thread.currentThread().isInterrupted()) {
                while (!AapAudioFocusManager.this.mFocusQue.isEmpty()) {
                    FocusRequest focusRequest = (FocusRequest) AapAudioFocusManager.this.mFocusQue.poll();
                    if (focusRequest != null) {
                        boolean isMdRequest = focusRequest.isMdRequest;
                        int reqIndex = focusRequest.reqIndex;
                        LogUtil.info(AapAudioFocusManager.TAG, "Handler isMdRequest:" + isMdRequest + " reqIndex:" + reqIndex);
                        if (isMdRequest) {
                            next = AapAudioFocusManager.this.mCurrentState.doMdReqActionAndGetNextState(AapAudioFocusManager.this.mLastState, focusRequest.mdRequest);
                        } else {
                            HuReqAction huReqAction = new HuReqAction();
                            huReqAction.setLastState(AapAudioFocusManager.this.mLastState);
                            huReqAction.setEvent(focusRequest.huRequest);
                            huReqAction.setSelfAudioId(focusRequest.selfAudioId);
                            huReqAction.setCauseAudioId(focusRequest.causeAudioId);
                            next = AapAudioFocusManager.this.mCurrentState.doHuReqActionAndGetNextState(huReqAction);
                        }
                        if (next != null) {
                            AapAudioFocusManager.this.transitToState(next, isMdRequest, reqIndex);
                        }
                    }
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    LogUtil.verbose(AapAudioFocusManager.TAG, "HandleFocusThread InterruptedException:" + exception.toString());
                }
            }
        }
    }

    public static synchronized AapAudioFocusManager getInstance() {
        if (mAapAudioFocusManager == null) {
            mAapAudioFocusManager = new AapAudioFocusManager();
        }
        return mAapAudioFocusManager;
    }

    private AapAudioFocusManager() {
    }

    public void releaseResource() {
        this.mMdRequestFocusIndex = 0;
        stopHandleFocusThread();
        IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
        if (iAudioControllerAdapter != null) {
            iAudioControllerAdapter.unregisterAudioListener(this);
        }
        boolean vrFocusState = StateManager.getInstance().getVrFocusState();
        boolean naviFocusState = StateManager.getInstance().getNaviFocusState();
        boolean micFocusState = StateManager.getInstance().getMicFocusState();
        if (vrFocusState) {
            LogUtil.info(TAG, "releaseResource vr.");
            requestHuReleaseVrDelay();
        }
        if (naviFocusState) {
            LogUtil.info(TAG, "releaseResource navi.");
            requestHuReleaseNaviDelay();
        }
        if (micFocusState) {
            LogUtil.info(TAG, "releaseResource mic.");
            requestHuReleaseMicFocus();
        }
        if (StateManager.getInstance().getDuckAudioState()) {
            StateManager.getInstance().setDuckAudioState(false);
            unduckAudio();
        }
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.unRegisterPhoneCallStatusListener();
        }
        if (this.mHandlerThread != null) {
            LogUtil.info(TAG, "releaseResource onstop release handler looper.");
            this.mHandlerThread.quit();
            this.mHandlerThread = null;
            this.mOnstopReleaseFocusHandler = null;
        }
        boolean isPowerOffDelay = StateManager.getInstance().getPowerOffDelayState();
        boolean isVrRunningInPowerOff = StateManager.getInstance().getVrRunningInPowerOffState();
        LogUtil.info(TAG, "Disconnect release isVrRunningInPowerOff:" + isVrRunningInPowerOff + " isPowerOffDelay:" + isPowerOffDelay);
        if (isPowerOffDelay) {
            LogUtil.info(TAG, "Disconnect  release in delay power off, remove first");
            removePowerOffDelayMessage(2);
        }
        if (isVrRunningInPowerOff) {
            LogUtil.info(TAG, "releaseResource set phone status off in power off.");
            StateManager.getInstance().setVrRunningInPowerOffState(false);
        }
        StateManager.getInstance().resetAllState();
        if (this.mIsVrInterruptMedia) {
            LogUtil.info(TAG, "releaseResource reset mIsVrInterruptMedia flag");
            this.mIsVrInterruptMedia = false;
        }
        this.mIsMuteBySelf = false;
    }

    public void transitToState(FocusState next, boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "transitToState next:" + next + " isMdReq:" + isMdReq);
        this.mCurrentState.exitState();
        this.mLastState = this.mCurrentState;
        this.mCurrentState = next;
        next.enterState(isMdReq, reqIndex);
    }

    public void init(GalIntegration gal, IAudioControllerAdapter audioControllerAdapter) {
        LogUtil.info(TAG, "Init  aap audio focus manager start.");
        this.mGal = gal;
        this.mAudioControllerAdapter = audioControllerAdapter;
        this.mAapController = AapController.getInstance();
        AapBluetoothManager aapBluetoothManager = AapBluetoothManager.getInstance();
        this.mAapBluetoothManager = aapBluetoothManager;
        aapBluetoothManager.registerPhoneCallStatusListener(this.mPhoneCallStatusListener);
        startHandleFocusThread();
        this.mCurrentState = LossFocusState.getInstance();
        this.mLastState = LossFocusState.getInstance();
        startOnstopReleaseFocusThread();
        IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
        if (iAudioControllerAdapter != null) {
            iAudioControllerAdapter.registerAudioListener(this);
        }
        LogUtil.info(TAG, "Init  aap audio focus manager end.");
    }

    private class FocusRequest {
        public ResourceConst.AudioId causeAudioId;
        public ResourceConst.AudioFocusNotify huRequest;
        public boolean isMdRequest;
        public int mdRequest;
        public int reqIndex;
        public ResourceConst.AudioId selfAudioId;

        private FocusRequest() {
        }
    }

    private synchronized void handleAudioFocusRequest(int request, boolean isMdRequest, int requestIndex) {
        LogUtil.info(TAG, "Handle audio focus request form MD.   Request is " + request);
        FocusRequest focusRequest = new FocusRequest();
        focusRequest.mdRequest = request;
        focusRequest.isMdRequest = isMdRequest;
        focusRequest.reqIndex = requestIndex;
        this.mFocusQue.offer(focusRequest);
    }

    @Override // com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener
    public void audioFocusChangedTo(ResourceConst.AudioId selfAudioId, ResourceConst.AudioId causeAudioId, ResourceConst.AudioFocusNotify focusChange, boolean isHfpCall) {
        AapController aapController;
        LogUtil.info(TAG, "audioFocusChangedTo Hu audio focus change selfAudioId is " + selfAudioId + " causeAudioId is " + causeAudioId);
        LogUtil.info(TAG, " focusChange is " + focusChange + " isHfpCall:" + isHfpCall + " mPhoneStatus:" + mPhoneStatus);
        FocusRequest focusRequest = new FocusRequest();
        focusRequest.huRequest = focusChange;
        focusRequest.isMdRequest = false;
        focusRequest.reqIndex = 0;
        focusRequest.causeAudioId = causeAudioId;
        focusRequest.selfAudioId = selfAudioId;
        LogUtil.info(TAG, "audioFocusChangedTo mIsVrInterruptMedia:" + this.mIsVrInterruptMedia);
        if (selfAudioId == ResourceConst.AudioId.PROJECTION_AAP_VR && isHfpCall) {
            this.mFocusQue.offer(focusRequest);
            return;
        }
        ResourceConst.AudioId audioId = ResourceConst.AudioId.NATIVE_HIGHPRIORITY;
        if (focusChange == ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS) {
            LogUtil.info(TAG, "audioFocusChangedTo loss focus stop audiotrack  mBtPhoneStatus:" + mBtPhoneStatus);
            stopAudioTrack();
            if (this.mIsVrInterruptMedia) {
                LogUtil.info(TAG, "Hu change source interrupt Vr delay interrupt media");
                this.mIsVrInterruptMedia = false;
            }
            if (mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE && selfAudioId == ResourceConst.AudioId.PROJECTION_AAP_VR) {
                LogUtil.info(TAG, "BT phone interrupt AA VR");
                focusRequest.huRequest = ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_START;
                focusRequest.isMdRequest = false;
                focusRequest.reqIndex = 0;
                focusRequest.selfAudioId = ResourceConst.AudioId.PROJECTION_AAP_MEDIA;
                focusRequest.causeAudioId = ResourceConst.AudioId.NATIVE_HIGHPRIORITY;
            }
        }
        if (focusChange == ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS && (selfAudioId == ResourceConst.AudioId.PROJECTION_CARPLAY_MEDIA || selfAudioId == ResourceConst.AudioId.PROJECTION_CARLIFE_MEDIA)) {
            LogUtil.info(TAG, "audioFocusChangedTo " + selfAudioId + " interrupt AA, media focus loss return");
            return;
        }
        this.mFocusQue.offer(focusRequest);
        if (focusChange == ResourceConst.AudioFocusNotify.AUDIOFOCUS_GAIN && (aapController = this.mAapController) != null) {
            aapController.updateAapMediaMetaData();
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener
    public void audioSourceStateChanged(ResourceConst.AudioId sourceId, int audioSourceState) {
        if (sourceId == ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
            LogUtil.info(TAG, "Update media info when getting media focus");
            AapController aapController = this.mAapController;
            if (aapController != null) {
                aapController.updateAapMediaMetaData();
            }
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener
    public void micFocusChangedTo(ResourceConst.MicChangeEvent event) {
        LogUtil.info(TAG, "micFocusChangedTo is " + event + " micStatus:" + StateManager.getInstance().getMicFocusState());
        if (event == ResourceConst.MicChangeEvent.EVENT_RELEASE_NOTIFICATION && StateManager.getInstance().getMicFocusState()) {
            requestHuReleaseMicFocus();
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener
    public void entertainmentMuteStateChangedTo(int state) {
        LogUtil.info(TAG, "entertainmentMuteStateChangedTo state = " + state);
        AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
        AapController aapController = this.mAapController;
        if (aapController != null) {
            int playState = aapController.getMusicStatus();
            boolean hasFocus = this.mAudioControllerAdapter.getLastAudioFocusState(56);
            LogUtil.info(TAG, "entertainmentMuteStateChangedTo playState: " + playState + " hasFocus: " + hasFocus + " mIsMuteBySelf: " + this.mIsMuteBySelf);
            if (playState == AndroidAutoConst.MusicStatus.MUSIC_STATUS_PLAYING.ordinal() && state == 1 && hasFocus) {
                this.mIsMuteBySelf = true;
                this.mAapController.sendMediaPauseKey(true);
                this.mAapController.sendMediaPauseKey(false);
            } else if (playState == AndroidAutoConst.MusicStatus.MUSIC_STATUS_PAUSED.ordinal() && state == 0 && hasFocus && this.mIsMuteBySelf) {
                this.mIsMuteBySelf = false;
                this.mAapController.sendMediaPlayKey(true);
                this.mAapController.sendMediaPlayKey(false);
            }
        }
    }

    @Override // com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerListener
    public void setNativeTtsState(boolean state) {
        LogUtil.info(TAG, "setNativeTtsState state = " + state);
        StateManager.getInstance().setNativeTtsState(state);
    }

    private boolean handleMediaGain() {
        ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal();
        boolean mediaFocusState = StateManager.getInstance().getMediaFocusState();
        boolean isVrFocusReleaseDelay = StateManager.getInstance().getVrReleaseFocusDelayState();
        if (this.mIsVrInterruptMedia) {
            this.mIsVrInterruptMedia = false;
            StateManager.getInstance().setMediaFocusState(true);
            LogUtil.info(TAG, "Request Media in VR interrupt Meida, reset media interrupt flag.");
            return true;
        }
        if (!mediaFocusState) {
            if (mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE) {
                int result = this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_GAIN);
                if (result == ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                    LogUtil.info(TAG, "Request Hu media audio focus success.");
                    if (isVrFocusReleaseDelay) {
                        removeDelayFocusMessage(0);
                        SystemClock.sleep(500L);
                        this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                        if (result == ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                            StateManager.getInstance().setVrFocusState(false);
                            LogUtil.info(TAG, "Request Media in delay vr, release vr success.");
                        }
                        handleVrRunningInPoweroff(true);
                    }
                    StateManager.getInstance().setMediaFocusState(true);
                    LogUtil.info(TAG, "Request Hu media audio focus success update metaData.");
                    AapController aapController = this.mAapController;
                    if (aapController != null) {
                        aapController.updateAapMediaMetaData();
                    }
                    return true;
                }
                StateManager.getInstance().setMediaFocusState(false);
                return false;
            }
            LogUtil.info(TAG, "phone status is active req media focus failed return.");
            StateManager.getInstance().setMediaFocusState(false);
            return false;
        }
        LogUtil.info(TAG, "already has AUDIOFOCUS_MEDIA_GAIN.");
        return true;
    }

    private boolean handleVrGain() {
        ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal();
        boolean mediaFocusState = StateManager.getInstance().getMediaFocusState();
        boolean vrFocusState = StateManager.getInstance().getVrFocusState();
        boolean isVrFocusReleaseDelay = StateManager.getInstance().getVrReleaseFocusDelayState();
        boolean isNaviFocusReleaseDelay = StateManager.getInstance().getNaviReleaseFocusDelayState();
        boolean isPowerpffDelay = StateManager.getInstance().getPowerOffDelayState();
        if (isVrFocusReleaseDelay) {
            removeDelayFocusMessage(0);
        }
        if (isNaviFocusReleaseDelay) {
            removeDelayFocusMessage(1);
            int result = this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
            if (result == ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                StateManager.getInstance().setNaviFocusState(false);
                if (StateManager.getInstance().getDuckAudioState()) {
                    LogUtil.info(TAG, "request vr in delay navi, Set unduck audio");
                    StateManager.getInstance().setDuckAudioState(false);
                    unduckAudio();
                }
                LogUtil.info(TAG, "Request VR in delay navi, release navi success.");
            }
        }
        if (!vrFocusState) {
            if (isPowerpffDelay) {
                LogUtil.info(TAG, "VR remove power off delay handle");
                removePowerOffDelayMessage(2);
            }
            if (mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE) {
                int result2 = this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
                if (result2 != ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                    StateManager.getInstance().setVrFocusState(false);
                    return false;
                }
                LogUtil.info(TAG, "Request Hu VR audio focus success.");
                StateManager.getInstance().setVrFocusState(true);
                if (mediaFocusState) {
                    LogUtil.info(TAG, "Request VR audio focus set media focus false.");
                    this.mIsVrInterruptMedia = true;
                    StateManager.getInstance().setMediaFocusState(false);
                }
                return true;
            }
            LogUtil.info(TAG, "phone status is active req vr focus failed return.");
            StateManager.getInstance().setVrFocusState(false);
            return false;
        }
        LogUtil.info(TAG, "already has AUDIOFOCUS_VR_GAIN.");
        return true;
    }

    private boolean handleNaviGain() {
        int result = ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal();
        boolean naviFocusState = StateManager.getInstance().getNaviFocusState();
        boolean isVrFocusReleaseDelay = StateManager.getInstance().getVrReleaseFocusDelayState();
        boolean isNaviFocusReleaseDelay = StateManager.getInstance().getNaviReleaseFocusDelayState();
        boolean isPowerpffDelay = StateManager.getInstance().getPowerOffDelayState();
        if (isNaviFocusReleaseDelay) {
            removeDelayFocusMessage(1);
        } else if (isVrFocusReleaseDelay) {
            removeDelayFocusMessage(0);
            this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
            if (result == ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                if (this.mIsVrInterruptMedia) {
                    LogUtil.info(TAG, "Request Navi in delay vr interrup media resume.");
                    this.mIsVrInterruptMedia = false;
                    StateManager.getInstance().setMediaFocusState(true);
                }
                StateManager.getInstance().setVrFocusState(false);
                LogUtil.info(TAG, "Request Navi in delay vr, release vr success.");
            }
        }
        if (!naviFocusState) {
            StateManager.getInstance().setDuringReqNavi(true);
            if (isPowerpffDelay) {
                LogUtil.info(TAG, "Navi remove power off delay handle");
                removePowerOffDelayMessage(2);
            }
            int result2 = this.mAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
            if (result2 != ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal()) {
                StateManager.getInstance().setNaviFocusState(false);
                StateManager.getInstance().setDuringReqNavi(false);
                return false;
            }
            LogUtil.info(TAG, "Request Hu Navi audio focus success.");
            StateManager.getInstance().setNaviFocusState(true);
            int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
            LogUtil.info(TAG, "reqHuAudioFocus audioFocusState:" + audioFocusState);
            if (audioFocusState == 1) {
                if (!StateManager.getInstance().getDuckAudioState()) {
                    LogUtil.info(TAG, "Set duck audio AAP_FADE_VOLUME_UNDUCK");
                    duckAudio(0);
                    StateManager.getInstance().setDuckAudioState(true);
                }
            } else if (!StateManager.getInstance().getDuckAudioState()) {
                LogUtil.info(TAG, "Set duck audio AAP_FADE_VOLUME_DUCK");
                duckAudio(-10);
                StateManager.getInstance().setDuckAudioState(true);
            }
            StateManager.getInstance().setDuringReqNavi(false);
            return true;
        }
        LogUtil.info(TAG, "already has AUDIOFOCUS_NAVI_GAIN.");
        return true;
    }

    public boolean reqHuAudioFocus(ResourceConst.AudioFocusRequest requestHuFocus) {
        boolean isSuccess;
        LogUtil.info(TAG, "requestHuFocus is " + requestHuFocus);
        synchronized (this.mLock) {
            ResourceConst.AudioFocusReqResult.AUDIOFOCUS_REQUEST_GRANTED.ordinal();
            isSuccess = false;
            boolean mediaFocusState = StateManager.getInstance().getMediaFocusState();
            boolean vrFocusState = StateManager.getInstance().getVrFocusState();
            boolean naviFocusState = StateManager.getInstance().getNaviFocusState();
            boolean isVrFocusReleaseDelay = StateManager.getInstance().getVrReleaseFocusDelayState();
            boolean isNaviFocusReleaseDelay = StateManager.getInstance().getNaviReleaseFocusDelayState();
            boolean isPowerpffDelay = StateManager.getInstance().getPowerOffDelayState();
            LogUtil.info(TAG, "requestHuFocus is " + requestHuFocus + " isVrFocusReleaseDelay:" + isVrFocusReleaseDelay);
            LogUtil.info(TAG, "requestHuFocus isNaviFocusReleaseDelay:" + isNaviFocusReleaseDelay + " mIsVrInterruptMedia:" + this.mIsVrInterruptMedia);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("requestHuFocus isPowerpffDelay:");
            sb.append(isPowerpffDelay);
            LogUtil.info(str, sb.toString());
            if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_GAIN) {
                isSuccess = handleMediaGain();
            } else if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN) {
                isSuccess = handleVrGain();
            } else if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN) {
                isSuccess = handleNaviGain();
            } else if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_RELEASE) {
                if (mediaFocusState) {
                    StateManager.getInstance().setMediaFocusState(false);
                    this.mAudioControllerAdapter.requestAudioFocus(requestHuFocus);
                } else {
                    LogUtil.info(TAG, "already release AUDIOFOCUS_MEDIA_RELEASE.");
                }
                isSuccess = true;
            } else if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE) {
                if (vrFocusState) {
                    if (isVrFocusReleaseDelay) {
                        LogUtil.info(TAG, "Vr release is in delay wait.");
                    } else {
                        LogUtil.info(TAG, "Vr release delay handle mIsVrInterruptMedia:" + this.mIsVrInterruptMedia);
                        sendReleaseFocusDelayMessage(0, AapConfig.FOCUS_RELEASE_DELAY_TIME);
                    }
                } else {
                    LogUtil.info(TAG, "already release AUDIOFOCUS_VR_RELEASE.");
                }
                isSuccess = true;
            } else if (requestHuFocus == ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE) {
                if (naviFocusState) {
                    if (isNaviFocusReleaseDelay) {
                        LogUtil.info(TAG, "Navi release is in delay wait.");
                    } else {
                        LogUtil.info(TAG, "Navi release delay handle.");
                        sendReleaseFocusDelayMessage(1, AapConfig.FOCUS_RELEASE_DELAY_TIME);
                    }
                } else {
                    LogUtil.info(TAG, "already release AUDIOFOCUS_NAVI_RELEASE.");
                }
                isSuccess = true;
            } else {
                LogUtil.info(TAG, "Unknown audio focus request type. Ignoring.");
            }
        }
        return isSuccess;
    }

    private void requestHuReleaseMicFocus() {
        int result = -1;
        IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
        if (iAudioControllerAdapter != null) {
            result = iAudioControllerAdapter.requestMicFocus(ResourceConst.MicFocusRequest.MICFOCUS_RELEASE_ANDROIDAUTO_VR);
        }
        LogUtil.debug(TAG, "requestMicFocus request : " + result);
        if (result == ResourceConst.MicFocusReqResult.MICFOCUS_REQUEST_GRANTED.ordinal()) {
            StateManager.getInstance().setMicFocusState(false);
        }
    }

    public void handleVrRunningInPoweroff(boolean keepOn) {
        boolean isVrRunningInPowerOff = StateManager.getInstance().getVrRunningInPowerOffState();
        int vrSessionState = StateManager.getInstance().getVoiceSessionState();
        LogUtil.info(TAG, "handle Poweroff isVrRunningInPowerOff:" + isVrRunningInPowerOff + " vrSessionState:" + vrSessionState);
        if (isVrRunningInPowerOff && vrSessionState == GalReceiver.VOICE_RECOGNITION_STATE_ENDED) {
            LogUtil.info(TAG, "handleVrRunningInPoweroff set phone status off");
            StateManager.getInstance().setVrRunningInPowerOffState(false);
        }
    }

    public void requestHuReleaseVrDelay() {
        LogUtil.info(TAG, "Request Hu release VR delay.");
        boolean vrFocusState = StateManager.getInstance().getVrFocusState();
        if (vrFocusState) {
            int result = -1;
            IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
            if (iAudioControllerAdapter != null) {
                result = iAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
            }
            LogUtil.info(TAG, "Request Hu VR release delay success result:" + result + " mIsVrInterruptMedia:" + this.mIsVrInterruptMedia);
            SystemClock.sleep(100L);
            StateManager.getInstance().setVrFocusState(false);
            if (this.mIsVrInterruptMedia) {
                this.mIsVrInterruptMedia = false;
                StateManager.getInstance().setMediaFocusState(true);
            }
            boolean isPowerOffDelay = StateManager.getInstance().getPowerOffDelayState();
            boolean isVrRunningInPowerOff = StateManager.getInstance().getVrRunningInPowerOffState();
            LogUtil.info(TAG, "Request Hu VR release isVrRunningInPowerOff:" + isVrRunningInPowerOff + " isPowerOffDelay:" + isPowerOffDelay);
            if (isPowerOffDelay) {
                LogUtil.info(TAG, "Request VR release in delay power off, remove first");
                removePowerOffDelayMessage(2);
            }
            if (isVrRunningInPowerOff) {
                sendPowerOffDelayMessage(2, 1000);
                return;
            }
            return;
        }
        LogUtil.info(TAG, "already release AUDIOFOCUS_VR_RELEASE.");
    }

    public void requestHuReleaseNaviDelay() {
        LogUtil.info(TAG, "Request Hu release Navi delay.");
        boolean naviFocusState = StateManager.getInstance().getNaviFocusState();
        if (naviFocusState) {
            int result = -1;
            IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
            if (iAudioControllerAdapter != null) {
                result = iAudioControllerAdapter.requestAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
            }
            LogUtil.info(TAG, "Request Hu Navi release delay success result:" + result);
            StateManager.getInstance().setNaviFocusState(false);
            if (StateManager.getInstance().getDuckAudioState()) {
                LogUtil.info(TAG, "Set unduck audio");
                StateManager.getInstance().setDuckAudioState(false);
                unduckAudio();
            }
            boolean isPowerOffDelay = StateManager.getInstance().getPowerOffDelayState();
            boolean isVrRunningInPowerOff = StateManager.getInstance().getVrRunningInPowerOffState();
            LogUtil.info(TAG, "Request Hu Navi release isVrRunningInPowerOff:" + isVrRunningInPowerOff + " isPowerOffDelay:" + isPowerOffDelay);
            if (isPowerOffDelay) {
                LogUtil.info(TAG, "Request Navi release in delay power off, remove first");
                removePowerOffDelayMessage(2);
            }
            if (isVrRunningInPowerOff) {
                sendPowerOffDelayMessage(2, 1000);
                return;
            }
            return;
        }
        LogUtil.info(TAG, "already release AUDIOFOCUS_NAVI_RELEASE.");
    }

    public void sendPowerOffDelayMessage(int delayPowerOff, int delay) {
        LogUtil.info(TAG, "sendPowerOffDelayMessage delayPowerOff:" + delayPowerOff + " delay time:" + delay);
        StateManager.getInstance().setPowerOffDelayState(true);
        Handler handler = this.mOnstopReleaseFocusHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(delayPowerOff, delay);
        }
    }

    public void removePowerOffDelayMessage(int delayPowerOff) {
        boolean isPowerpffDelay = StateManager.getInstance().getPowerOffDelayState();
        LogUtil.info(TAG, "removePowerOffDelayMessage  isPowerpffDelay:" + isPowerpffDelay);
        if (this.mOnstopReleaseFocusHandler != null && isPowerpffDelay) {
            StateManager.getInstance().setPowerOffDelayState(false);
            this.mOnstopReleaseFocusHandler.removeMessages(2);
        }
    }

    public void sendReleaseFocusDelayMessage(int delayFocus, int delay) {
        LogUtil.info(TAG, "sendReleaseFocusDelayMessage delayFocus:" + delayFocus + " delay time:" + delay);
        if (delayFocus == 0) {
            StateManager.getInstance().setVrReleaseFocusDelayState(true);
        } else if (delayFocus == 1) {
            StateManager.getInstance().setNaviReleaseFocusDelayState(true);
        }
        Handler handler = this.mOnstopReleaseFocusHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(delayFocus, delay);
        }
    }

    public void removeDelayFocusMessage(int delayFocus) {
        boolean isVrFocusReleaseDelay = StateManager.getInstance().getVrReleaseFocusDelayState();
        boolean isNaviFocusReleaseDelay = StateManager.getInstance().getNaviReleaseFocusDelayState();
        LogUtil.info(TAG, "removeDelayFocusMessage  delayFocus:" + delayFocus + " isVrFocusReleaseDelay:" + isVrFocusReleaseDelay + " isNaviFocusReleaseDelay:" + isNaviFocusReleaseDelay);
        if (this.mOnstopReleaseFocusHandler != null) {
            if (isVrFocusReleaseDelay) {
                StateManager.getInstance().setVrReleaseFocusDelayState(false);
                this.mOnstopReleaseFocusHandler.removeMessages(0);
            }
            if (isNaviFocusReleaseDelay) {
                StateManager.getInstance().setNaviReleaseFocusDelayState(false);
                this.mOnstopReleaseFocusHandler.removeMessages(1);
            }
        }
    }

    private void startOnstopReleaseFocusThread() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mOnstopReleaseFocusHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.androidauto.aap.audiofocus.AapAudioFocusManager.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                synchronized (AapAudioFocusManager.this.mLock) {
                    int i = msg.arg1;
                    LogUtil.info(AapAudioFocusManager.TAG, "startOnstopReleaseFocusThread action:" + msg.what);
                    int i2 = msg.what;
                    if (i2 == 0) {
                        StateManager.getInstance().setVrReleaseFocusDelayState(false);
                        AapAudioFocusManager.this.requestHuReleaseVrDelay();
                    } else if (i2 == 1) {
                        StateManager.getInstance().setNaviReleaseFocusDelayState(false);
                        AapAudioFocusManager.this.requestHuReleaseNaviDelay();
                    } else if (i2 == 2) {
                        StateManager.getInstance().setPowerOffDelayState(false);
                        AapAudioFocusManager.this.handleVrRunningInPoweroff(false);
                    }
                }
            }
        };
    }

    public void setAudioFocus(int focusState, boolean unsolicited) {
        if (this.mAapController != null) {
            LogUtil.info(TAG, "Set audio focus to MD: " + focusState + " AA connected:" + this.mAapController.isAapConnected());
            if (this.mGal != null && this.mAapController.isAapConnected()) {
                this.mCurrentAudioFocus = focusState;
                this.mGal.galReceiver.setAudioFocus(focusState, unsolicited);
            }
        }
    }

    public int getAudioFocus() {
        return this.mCurrentAudioFocus;
    }

    public void audioFocusRequestCallback(int request) {
        LogUtil.info(TAG, "audioFocusRequestCallback mdAudioFocusReq:" + request);
        LogUtil.info(TAG, "audioFocusRequestCallback mPhoneStatus:" + mPhoneStatus);
        if (request == GalReceiver.AUDIO_FOCUS_GAIN) {
            LogUtil.info(TAG, "audioFocusRequestCallback send md focus gain!");
            StateManager.getInstance().setMdCurrentFocusState(1);
            setAudioFocus(1, false);
            this.mMdRequestFocusIndex++;
            mdAudioFocusReq(1);
            return;
        }
        if (request == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT) {
            StateManager.getInstance().setMdCurrentFocusState(2);
            this.mMdRequestFocusIndex++;
            setAudioFocus(2, false);
            mdAudioFocusReq(2);
            return;
        }
        if (request == GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK) {
            LogUtil.info(TAG, "audioFocusRequestCallback send  md focus GAIN_TRANSIENT_MAY_DUCK!");
            StateManager.getInstance().setMdCurrentFocusState(2);
            setAudioFocus(2, false);
            this.mMdRequestFocusIndex++;
            mdAudioFocusReq(3);
            return;
        }
        if (request == GalReceiver.AUDIO_FOCUS_RELEASE) {
            StateManager.getInstance().setMdCurrentFocusState(3);
            setAudioFocus(3, false);
            this.mMdRequestFocusIndex++;
            mdAudioFocusReq(4);
            return;
        }
        LogUtil.info(TAG, "Unknown audio focus request type. Ignoring.");
    }

    public void mdAudioFocusReq(int event) {
        LogUtil.info(TAG, "mdAudioFocusReq event:" + event + " mMdRequestFocusIndex:" + this.mMdRequestFocusIndex);
        boolean isDuringReqNavi = false;
        if (event == 1) {
            StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN);
        } else if (event == 2) {
            StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT);
        } else if (event == 3) {
            StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK);
        } else if (event == 4) {
            StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_RELEASE);
        } else if (event == 7) {
            if (StateManager.getInstance().isDuringReqNavi()) {
                LogUtil.info(TAG, "During request navi focus. Skip it.");
                isDuringReqNavi = true;
            }
        } else {
            LogUtil.info(TAG, "Not MD request focus. Ignoring.");
        }
        if (!isDuringReqNavi) {
            handleAudioFocusRequest(event, true, this.mMdRequestFocusIndex);
        }
    }

    public void duckAudio(int volume) {
        LogUtil.info(TAG, "duckAudio is invoke.");
        IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
        if (iAudioControllerAdapter != null) {
            iAudioControllerAdapter.duckAudio(2, 100, volume);
        }
    }

    public void unduckAudio() {
        LogUtil.info(TAG, "unDuckAudio is invoke.");
        IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
        if (iAudioControllerAdapter != null) {
            iAudioControllerAdapter.unDuckAudio(2);
        }
    }

    public void stopAudioTrack() {
        LogUtil.info(TAG, "stopAudioTrack is invoke");
        AapAudioManager.getInstance().stopAudioTrack();
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.AapAudioFocusManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$aap$common$AapConfig$ValidAudioFocusType;

        static {
            int[] iArr = new int[AapConfig.ValidAudioFocusType.values().length];
            $SwitchMap$com$ts$androidauto$aap$common$AapConfig$ValidAudioFocusType = iArr;
            try {
                iArr[AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$aap$common$AapConfig$ValidAudioFocusType[AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_VR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$aap$common$AapConfig$ValidAudioFocusType[AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_NAVI.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public boolean hasValidAudioFocus(AapConfig.ValidAudioFocusType type) {
        int i = AnonymousClass3.$SwitchMap$com$ts$androidauto$aap$common$AapConfig$ValidAudioFocusType[type.ordinal()];
        if (i == 1) {
            boolean result = StateManager.getInstance().getMediaFocusState();
            return result;
        }
        if (i == 2) {
            boolean result2 = StateManager.getInstance().getVrFocusState();
            return result2;
        }
        if (i != 3) {
            return false;
        }
        boolean result3 = StateManager.getInstance().getNaviFocusState();
        return result3;
    }

    public int getCurrentMdRequestIndex() {
        return this.mMdRequestFocusIndex;
    }
}
