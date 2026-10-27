package com.ts.androidauto.aap.audiofocus;

import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class GainFocusState implements FocusState {
    private static final String TAG = "AAP" + GainFocusState.class.getSimpleName();
    private static GainFocusState mThis;

    public static synchronized GainFocusState getInstance() {
        if (mThis == null) {
            mThis = new GainFocusState();
        }
        return mThis;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doMdReqActionAndGetNextState(FocusState lastState, int event) {
        switch (event) {
            case 4:
                AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
                FocusState nextState = LossFocusState.getInstance();
                return nextState;
            case 5:
                boolean ret = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
                if (ret) {
                    LogUtil.info(TAG, "GainFocusState PLAY_VR_REQ success.");
                    return null;
                }
                LogUtil.info(TAG, "GainFocusState PLAY_VR_REQ should not failed.");
                return null;
            case 6:
                AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                LogUtil.info(TAG, "GainFocusState STOP_VR_REQ success.");
                return null;
            case 7:
                boolean ret2 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
                if (ret2) {
                    LogUtil.info(TAG, "GainFocusState PLAY_NAVI_REQ success.");
                    return null;
                }
                LogUtil.info(TAG, "GainFocusState PLAY_NAVI_REQ should not failed.");
                return null;
            case 8:
                boolean ret3 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
                if (!ret3) {
                    return null;
                }
                LogUtil.info(TAG, "GainFocusState STOP_NAVI_REQ success.");
                return null;
            default:
                LogUtil.info(TAG, "Unknown audio focus request type : FocusReqType is " + event);
                return null;
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction) {
        if (huReqAction == null) {
            return null;
        }
        LogUtil.info(TAG, "GainFocusState doHuReqActionAndGetNextState event is " + huReqAction.getEvent());
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[huReqAction.getEvent().ordinal()];
        if (i == 1) {
            LogUtil.info(TAG, "AUDIOFOCUS_LOSS set media focus false.");
            StateManager.getInstance().setMediaFocusState(false);
            if (StateManager.getInstance().getVoiceSessionState() == GalReceiver.VOICE_RECOGNITION_STATE_STARTED) {
                AapController.getInstance().sendVrKey(true);
                AapController.getInstance().sendVrKey(false);
            }
            AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
            AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
            FocusState nextState = LossFocusState.getInstance();
            return nextState;
        }
        if (i == 2 || i == 3) {
            FocusState nextState2 = LossTransientFocusState.getInstance();
            if (StateManager.getInstance().getVoiceSessionState() == GalReceiver.VOICE_RECOGNITION_STATE_STARTED) {
                LogUtil.info(TAG, "AUDIOFOCUS_LOSS_TRANSIENT cancel AA Vr");
                AapController.getInstance().sendVrKey(true);
                AapController.getInstance().sendVrKey(false);
            }
            StateManager.getInstance().setMediaFocusState(false);
            StateManager.getInstance().setVrFocusState(false);
            return nextState2;
        }
        if (i == 4) {
            FocusState nextState3 = LossDuckFocusState.getInstance();
            return nextState3;
        }
        LogUtil.info(TAG, "Unknown audio focus request type : FocusReqType is " + huReqAction.getEvent());
        return null;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.GainFocusState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify;

        static {
            int[] iArr = new int[ResourceConst.AudioFocusNotify.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify = iArr;
            try {
                iArr[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void enterState(boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "GainFocusState enterState isMDReq is " + isMdReq + " reqIndex:" + reqIndex);
        int currentMdRequestIndex = AapAudioFocusManager.getInstance().getCurrentMdRequestIndex();
        LogUtil.info(TAG, "GainFocusState enterState currentMdRequestIndex " + currentMdRequestIndex);
        int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
        LogUtil.info(TAG, "GainFocusState enterState audioFocusState " + audioFocusState);
        if ((audioFocusState != 1 && !isMdReq) || (audioFocusState != 1 && isMdReq && reqIndex == currentMdRequestIndex)) {
            if (AapAudioFocusManager.getInstance().getAudioFocus() != 1) {
                LogUtil.info(TAG, "GainFocusState enterState send gain focus.");
                AapAudioFocusManager.getInstance().setAudioFocus(1, true);
            } else {
                LogUtil.info(TAG, "GainFocusState enterState is already gain focus, don't send.");
            }
            StateManager.getInstance().setMdCurrentFocusState(1);
        }
        LogUtil.info(TAG, "GainFocusState enter with focus:" + StateManager.getInstance().getMdCurrentFocusState());
        LogUtil.info(TAG, "GainFocusState enter with req:" + StateManager.getInstance().getCurrentMdFocusReq());
        StateManager.getInstance().setMdCurrentFocusState(1);
        StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN);
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void exitState() {
        LogUtil.info(TAG, "GainFocusState exitState. ");
    }
}
