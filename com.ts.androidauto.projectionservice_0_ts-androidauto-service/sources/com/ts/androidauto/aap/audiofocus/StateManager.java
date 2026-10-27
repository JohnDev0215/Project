package com.ts.androidauto.aap.audiofocus;

import com.google.android.projection.protocol.GalReceiver;

/* JADX INFO: loaded from: classes.dex */
public class StateManager {
    private static StateManager mStateManager;
    private volatile int mMdCurrFocusState = 3;
    private volatile int mCurrentMdFocusReq = GalReceiver.AUDIO_FOCUS_STATE_LOSS;
    private volatile int mMdVrState = GalReceiver.VOICE_RECOGNITION_STATE_ENDED;
    private int mHasSendToMdFocus = GalReceiver.AUDIO_FOCUS_STATE_LOSS;
    private int mVrPlayState = -1;
    private boolean mMdTtsState = false;
    private boolean mHuNaviTtsState = false;
    private boolean mVrFocusState = false;
    private boolean mNaviFocusState = false;
    private boolean mMediaFocusState = false;
    private boolean mSimulateGuidanceStatus = false;
    private boolean mDumpAudioState = false;
    private boolean mMicFocusState = false;
    private boolean mDuckAudioState = false;
    private boolean mVrReleaseFocusDelayState = false;
    private boolean mNaviReleaseFocusDelayState = false;
    private boolean mIsVrRunningInPowerOff = false;
    private boolean mIsPhoneActiveByRunningVr = false;
    private boolean mIsPhoneRunningInPowerOff = false;
    private boolean mIsPowerOffDelayState = false;
    private boolean mIsVideoShowing = false;
    private boolean mIsPhoneRunningInScreenOff = false;
    private boolean mIsMediaStart = false;
    private boolean mIsDuringReqNavi = false;

    public static synchronized StateManager getInstance() {
        if (mStateManager == null) {
            mStateManager = new StateManager();
        }
        return mStateManager;
    }

    public void setCurrentMdFocusReq(int focusState) {
        this.mCurrentMdFocusReq = focusState;
    }

    public int getCurrentMdFocusReq() {
        return this.mCurrentMdFocusReq;
    }

    public void setMdCurrentFocusState(int audioFocusState) {
        this.mMdCurrFocusState = audioFocusState;
    }

    public int getMdCurrentFocusState() {
        return this.mMdCurrFocusState;
    }

    public int getVoiceSessionState() {
        return this.mMdVrState;
    }

    public void setVoiceSessionState(int state) {
        this.mMdVrState = state;
    }

    public void setMdTtsState(boolean state) {
        this.mMdTtsState = state;
    }

    public boolean getMdTtsState() {
        return this.mMdTtsState;
    }

    public void setNativeTtsState(boolean state) {
        this.mHuNaviTtsState = state;
    }

    public boolean getNativeTtsState() {
        return this.mHuNaviTtsState;
    }

    public void setVrFocusState(boolean state) {
        this.mVrFocusState = state;
    }

    public boolean getVrFocusState() {
        return this.mVrFocusState;
    }

    public void setNaviFocusState(boolean state) {
        this.mNaviFocusState = state;
    }

    public boolean getNaviFocusState() {
        return this.mNaviFocusState;
    }

    public void setMediaFocusState(boolean state) {
        this.mMediaFocusState = state;
    }

    public boolean getMediaFocusState() {
        return this.mMediaFocusState;
    }

    public void setVrPlayState(int vrPlayState) {
        this.mVrPlayState = vrPlayState;
    }

    public int getVrPlayState() {
        return this.mVrPlayState;
    }

    public void setMicFocusState(boolean state) {
        this.mMicFocusState = state;
    }

    public boolean getMicFocusState() {
        return this.mMicFocusState;
    }

    public void setDuckAudioState(boolean state) {
        this.mDuckAudioState = state;
    }

    public boolean getDuckAudioState() {
        return this.mDuckAudioState;
    }

    public void setVrReleaseFocusDelayState(boolean state) {
        this.mVrReleaseFocusDelayState = state;
    }

    public boolean getVrReleaseFocusDelayState() {
        return this.mVrReleaseFocusDelayState;
    }

    public void setNaviReleaseFocusDelayState(boolean state) {
        this.mNaviReleaseFocusDelayState = state;
    }

    public boolean getNaviReleaseFocusDelayState() {
        return this.mNaviReleaseFocusDelayState;
    }

    public void setVrRunningInPowerOffState(boolean state) {
        this.mIsVrRunningInPowerOff = state;
    }

    public boolean getVrRunningInPowerOffState() {
        return this.mIsVrRunningInPowerOff;
    }

    public void setPhoneStateByRunningVr(boolean state) {
        this.mIsPhoneActiveByRunningVr = state;
    }

    public boolean getPhoneStateByRunningVr() {
        return this.mIsPhoneActiveByRunningVr;
    }

    public void setPhoneRunningInPowerOffState(boolean state) {
        this.mIsPhoneRunningInPowerOff = state;
    }

    public boolean getPhoneRunningInPowerOffState() {
        return this.mIsPhoneRunningInPowerOff;
    }

    public void setPhoneRunningInScreenOffState(boolean state) {
        this.mIsPhoneRunningInScreenOff = state;
    }

    public boolean getPhoneRunningInScreenOffState() {
        return this.mIsPhoneRunningInScreenOff;
    }

    public void setPowerOffDelayState(boolean state) {
        this.mIsPowerOffDelayState = state;
    }

    public boolean getPowerOffDelayState() {
        return this.mIsPowerOffDelayState;
    }

    public void setVideoShowing(boolean isShowing) {
        this.mIsVideoShowing = isShowing;
    }

    public boolean isVideoShowing() {
        return this.mIsVideoShowing;
    }

    public void setMediaStart(boolean isStart) {
        this.mIsMediaStart = isStart;
    }

    public boolean isMediaStart() {
        return this.mIsMediaStart;
    }

    public void setDuringReqNavi(boolean isDuringReqNavi) {
        this.mIsDuringReqNavi = isDuringReqNavi;
    }

    public boolean isDuringReqNavi() {
        return this.mIsDuringReqNavi;
    }

    public void resetAllState() {
        this.mCurrentMdFocusReq = GalReceiver.AUDIO_FOCUS_STATE_LOSS;
        this.mMdVrState = GalReceiver.VOICE_RECOGNITION_STATE_ENDED;
        this.mHasSendToMdFocus = GalReceiver.AUDIO_FOCUS_STATE_LOSS;
        this.mVrPlayState = -1;
        this.mMdTtsState = false;
        this.mHuNaviTtsState = false;
        this.mVrFocusState = false;
        this.mNaviFocusState = false;
        this.mMediaFocusState = false;
        this.mSimulateGuidanceStatus = false;
        this.mDumpAudioState = false;
        this.mMicFocusState = false;
        this.mDuckAudioState = false;
        this.mVrReleaseFocusDelayState = false;
        this.mNaviReleaseFocusDelayState = false;
        this.mIsVrRunningInPowerOff = false;
        this.mIsPowerOffDelayState = false;
        this.mIsPhoneRunningInPowerOff = false;
        this.mIsVideoShowing = false;
        this.mIsPhoneRunningInScreenOff = false;
        this.mIsMediaStart = false;
        this.mIsDuringReqNavi = false;
    }
}
