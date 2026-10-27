package com.ts.androidauto.aap.audiofocus;

import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class LossTransientFocusState implements FocusState {
    private static final String TAG = "AAP" + LossTransientFocusState.class.getSimpleName();
    private static LossTransientFocusState mThis;

    public static synchronized LossTransientFocusState getInstance() {
        if (mThis == null) {
            mThis = new LossTransientFocusState();
        }
        return mThis;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doMdReqActionAndGetNextState(FocusState lastState, int event) {
        LogUtil.info(TAG, "LossTransientFocusState doMdReqActionAndGetNextState event is  " + event);
        switch (event) {
            case 1:
                boolean ret = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_GAIN);
                if (ret) {
                    LogUtil.info(TAG, "LossTransientFocusState GAIN_REQ success. ");
                    FocusState nextState = GainFocusState.getInstance();
                    return nextState;
                }
                LogUtil.info(TAG, "LossTransientFocusState GAIN_REQ failed. ");
                FocusState nextState2 = getInstance();
                return nextState2;
            case 2:
                boolean ret2 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
                if (ret2) {
                    LogUtil.info(TAG, "LossTransientFocusState GAIN_TRANSIENT_REQ success. ");
                    FocusState nextState3 = GainTransientFocusState.getInstance();
                    return nextState3;
                }
                LogUtil.info(TAG, "LossTransientFocusState GAIN_TRANSIENT_REQ failed. ");
                FocusState nextState4 = getInstance();
                return nextState4;
            case 3:
                boolean ret3 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
                if (ret3) {
                    LogUtil.info(TAG, "LossTransientFocusState GAIN_TRANSIENT_MAY_DUCK_REQ success. ");
                    FocusState nextState5 = GainTransientFocusState.getInstance();
                    return nextState5;
                }
                LogUtil.info(TAG, "LossTransientFocusState GAIN_TRANSIENT_MAY_DUCK_REQ failed. ");
                FocusState nextState6 = getInstance();
                return nextState6;
            case 4:
            default:
                LogUtil.info(TAG, "Unknown audio focus request type   FocusReqType is " + event);
                return null;
            case 5:
                boolean ret4 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
                if (ret4) {
                    LogUtil.info(TAG, "LossTransientFocusState PLAY_VR_REQ success. ");
                } else {
                    LogUtil.info(TAG, "LossTransientFocusState PLAY_VR_REQ failed. ");
                }
                return null;
            case 6:
                boolean ret5 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
                if (ret5) {
                    LogUtil.info(TAG, "LossTransientFocusState STOP_VR_REQ success. ");
                } else {
                    LogUtil.info(TAG, "LossTransientFocusState STOP_VR_REQ failed. ");
                }
                return null;
            case 7:
                boolean ret6 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
                if (ret6) {
                    LogUtil.info(TAG, "LossTransientFocusState PLAY_NAVI_REQ success. ");
                } else {
                    LogUtil.info(TAG, "LossTransientFocusState PLAY_NAVI_REQ failed. ");
                }
                return null;
            case 8:
                boolean ret7 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
                if (ret7) {
                    LogUtil.info(TAG, "LossTransientFocusState STOP_NAVI_REQ success. ");
                } else {
                    LogUtil.info(TAG, "LossTransientFocusState STOP_NAVI_REQ failed. ");
                }
                return null;
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction) {
        if (huReqAction == null) {
            return null;
        }
        LogUtil.info(TAG, "LossTransientFocusState doHuReqActionAndGetNextState event is  " + huReqAction.getEvent());
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[huReqAction.getEvent().ordinal()];
        if (i == 1) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                return null;
            }
            StateManager.getInstance().setMediaFocusState(true);
            if (huReqAction.getLastState() == GainFocusState.getInstance() || huReqAction.getLastState() == getInstance() || huReqAction.getLastState() == LossDuckFocusState.getInstance() || huReqAction.getLastState() == GainTransientFocusState.getInstance()) {
                FocusState nextState = GainFocusState.getInstance();
                return nextState;
            }
            FocusState nextState2 = LossFocusState.getInstance();
            return nextState2;
        }
        if (i == 2) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                return null;
            }
            LogUtil.info(TAG, "LossTransientFocusState media changed");
            FocusState nextState3 = LossFocusState.getInstance();
            StateManager.getInstance().setMediaFocusState(false);
            StateManager.getInstance().setVrFocusState(false);
            return nextState3;
        }
        if (i == 3) {
            FocusState nextState4 = LossDuckFocusState.getInstance();
            return nextState4;
        }
        if (i == 4) {
            if (huReqAction.getLastState() == GainFocusState.getInstance() || huReqAction.getLastState() == LossDuckFocusState.getInstance()) {
                FocusState nextState5 = GainFocusState.getInstance();
                StateManager.getInstance().setMediaFocusState(true);
                return nextState5;
            }
            FocusState nextState6 = LossFocusState.getInstance();
            return nextState6;
        }
        LogUtil.info(TAG, "Unknown audio focus request type FocusReqType is " + huReqAction.getEvent());
        return null;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.LossTransientFocusState$1, reason: invalid class name */
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
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void enterState(boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "LossTransientFocusState enterState isMDReq is " + isMdReq + " reqIndex:" + reqIndex);
        int currentMdRequestIndex = AapAudioFocusManager.getInstance().getCurrentMdRequestIndex();
        int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
        if ((audioFocusState != 5 && !isMdReq) || (audioFocusState != 5 && isMdReq && reqIndex == currentMdRequestIndex)) {
            if (AapAudioFocusManager.getInstance().getAudioFocus() != 5) {
                AapAudioFocusManager.getInstance().setAudioFocus(5, true);
            }
            StateManager.getInstance().setMdCurrentFocusState(5);
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void exitState() {
        LogUtil.info(TAG, "LossTransientFocusState exitState.");
    }
}
