package com.ts.androidauto.aap.audiofocus;

import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class GainTransientFocusState implements FocusState {
    private static final String TAG = "AAP" + GainTransientFocusState.class.getSimpleName();
    private static GainTransientFocusState mThis;

    public static synchronized GainTransientFocusState getInstance() {
        if (mThis == null) {
            mThis = new GainTransientFocusState();
        }
        return mThis;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doMdReqActionAndGetNextState(FocusState lastState, int event) {
        LogUtil.info(TAG, "GainTransientFocusState doMdReqActionAndGetNextState event is  " + event);
        switch (event) {
            case 1:
                boolean vrFocusState = StateManager.getInstance().getVrFocusState();
                if (vrFocusState) {
                    boolean ret = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                    if (ret) {
                        LogUtil.info(TAG, "gain request release VR success");
                    }
                }
                boolean ret2 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_GAIN);
                if (ret2) {
                    LogUtil.info(TAG, "GainTransientFocusState GAIN_REQ success.  ");
                    FocusState nextState = GainFocusState.getInstance();
                    return nextState;
                }
                LogUtil.info(TAG, "GainTransientFocusState GAIN_REQ should  not failed. ");
                FocusState nextState2 = LossTransientFocusState.getInstance();
                return nextState2;
            case 2:
            default:
                LogUtil.info(TAG, "Unknown audio focus request type  FocusReqType is " + event);
                return null;
            case 3:
                boolean ret3 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
                if (ret3) {
                    LogUtil.info(TAG, "GainTransientFocusStateGAIN_TRANSIENT_MAY_DUCK_REQ success.");
                    FocusState nextState3 = getInstance();
                    return nextState3;
                }
                LogUtil.info(TAG, "GainTransientFocusStateGAIN_TRANSIENT_MAY_DUCK_REQ failed.");
                FocusState nextState4 = LossFocusState.getInstance();
                return nextState4;
            case 4:
                boolean ret4 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                if (ret4) {
                    LogUtil.info(TAG, "GainTransientFocusState STOP_VR_REQ success");
                }
                boolean ret5 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
                if (ret5) {
                    LogUtil.info(TAG, "GainTransientFocusState STOP_NAVI_REQ success");
                }
                if (lastState == LossTransientFocusState.getInstance()) {
                    FocusState nextState5 = LossTransientFocusState.getInstance();
                    return nextState5;
                }
                FocusState nextState6 = LossFocusState.getInstance();
                return nextState6;
            case 5:
                boolean ret6 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
                if (ret6) {
                    LogUtil.info(TAG, "GainTransientFocusState PLAY_VR_REQ success. ");
                    return null;
                }
                LogUtil.info(TAG, "GainTransientFocusState PLAY_VR_REQ should not failed.");
                return null;
            case 6:
                boolean ret7 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                if (!ret7) {
                    return null;
                }
                LogUtil.info(TAG, "GainTransientFocusState STOP_VR_REQ success");
                return null;
            case 7:
                boolean ret8 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
                if (ret8) {
                    LogUtil.info(TAG, "GainTransientFocusState PLAY_NAVI_REQ success.");
                    return null;
                }
                LogUtil.info(TAG, "GainTransientFocusState PLAY_NAVI_REQ should not failed.");
                FocusState nextState7 = LossFocusState.getInstance();
                return nextState7;
            case 8:
                boolean ret9 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
                if (!ret9) {
                    return null;
                }
                LogUtil.info(TAG, "GainTransientFocusState STOP_NAVI_REQ success");
                return null;
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction) {
        if (huReqAction == null) {
            return null;
        }
        LogUtil.info(TAG, "GainTransientFocusState doHuReqActionAndGetNextState  event is " + huReqAction.getEvent());
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[huReqAction.getEvent().ordinal()];
        if (i == 1) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                return null;
            }
            StateManager.getInstance().setMediaFocusState(true);
            FocusState nextState = GainFocusState.getInstance();
            return nextState;
        }
        if (i != 2 && i != 3) {
            if (i == 4) {
                if (!StateManager.getInstance().getNaviFocusState()) {
                    StateManager.getInstance().setMediaFocusState(false);
                    StateManager.getInstance().setVrFocusState(false);
                    FocusState nextState2 = LossTransientFocusState.getInstance();
                    return nextState2;
                }
                LogUtil.info(TAG, "GainTransientFocusState phone interruptNavi status do nothing");
                return null;
            }
            if (i == 5) {
                FocusState nextState3 = LossDuckFocusState.getInstance();
                return nextState3;
            }
            LogUtil.info(TAG, "Unknown audio focus request type  event is FocusReqType is " + huReqAction.getEvent());
            return null;
        }
        if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_VR && huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_NAVI) {
            return null;
        }
        LogUtil.info(TAG, "GainTransientFocusState   lastState:" + huReqAction.getLastState());
        StateManager.getInstance().setMediaFocusState(false);
        StateManager.getInstance().setVrFocusState(false);
        StateManager.getInstance().setNaviFocusState(false);
        if (StateManager.getInstance().getVoiceSessionState() == GalReceiver.VOICE_RECOGNITION_STATE_STARTED) {
            AapController.getInstance().sendVrKey(true);
            AapController.getInstance().sendVrKey(false);
        }
        if (huReqAction.getLastState() == LossTransientFocusState.getInstance()) {
            FocusState nextState4 = LossTransientFocusState.getInstance();
            return nextState4;
        }
        FocusState nextState5 = LossFocusState.getInstance();
        return nextState5;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.GainTransientFocusState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify;

        static {
            int[] iArr = new int[ResourceConst.AudioFocusNotify.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify = iArr;
            try {
                iArr[ResourceConst.AudioFocusNotify.AUDIOFOCUS_GAIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_START.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void enterState(boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "GainTransientFocusState enterState isMDReq is " + isMdReq + " reqIndex:" + reqIndex);
        int currentMdRequestIndex = AapAudioFocusManager.getInstance().getCurrentMdRequestIndex();
        int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
        LogUtil.info(TAG, "GainTransientFocusState currentMdRequestIndex:" + currentMdRequestIndex + " audioFocusState:" + audioFocusState);
        if ((audioFocusState != 2 && !isMdReq) || (audioFocusState != 2 && isMdReq && reqIndex == currentMdRequestIndex)) {
            if (AapAudioFocusManager.getInstance().getAudioFocus() != 2) {
                AapAudioFocusManager.getInstance().setAudioFocus(2, true);
            }
            StateManager.getInstance().setMdCurrentFocusState(2);
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void exitState() {
        LogUtil.info(TAG, "GainTransientFocusState exitState.");
    }
}
