package com.ts.androidauto.aap.audiofocus;

import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class LossDuckFocusState implements FocusState {
    private static final String TAG = "AAP" + LossDuckFocusState.class.getSimpleName();
    private static LossDuckFocusState mThis;

    public static synchronized LossDuckFocusState getInstance() {
        if (mThis == null) {
            mThis = new LossDuckFocusState();
        }
        return mThis;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doMdReqActionAndGetNextState(FocusState lastState, int event) {
        LogUtil.info(TAG, "LossDuckFocusState doMdReqActionAndGetNextState event is  " + event);
        if (event == 1) {
            FocusState nextState = getInstance();
            return nextState;
        }
        if (event == 2) {
            boolean ret = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
            if (ret) {
                LogUtil.info(TAG, "LossDuckFocusState GAIN_TRANSIENT_REQ success .");
                FocusState nextState2 = GainTransientFocusState.getInstance();
                return nextState2;
            }
            LogUtil.info(TAG, "LossDuckFocusState GAIN_TRANSIENT_REQ failed");
            FocusState nextState3 = getInstance();
            return nextState3;
        }
        if (event == 5) {
            boolean ret2 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
            if (ret2) {
                LogUtil.info(TAG, "LossDuckFocusState PLAY_VR_REQ success.");
            } else {
                LogUtil.info(TAG, "LossDuckFocusState PLAY_VR_REQ failed.");
            }
            return null;
        }
        if (event == 6) {
            AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_RELEASE);
            LogUtil.info(TAG, "LossDuckFocusState STOP_VR_REQ success.");
            return null;
        }
        if (event == 7) {
            boolean ret3 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
            if (ret3) {
                LogUtil.info(TAG, "LossDuckFocusState PLAY_NAVI_REQ success.");
                return null;
            }
            LogUtil.info(TAG, "LossDuckFocusState PLAY_NAVI_REQ should not failed.");
            return null;
        }
        if (event == 8) {
            AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_RELEASE);
            LogUtil.info(TAG, "LossDuckFocusState STOP_NAVI_REQ success.");
            return null;
        }
        LogUtil.info(TAG, "Unknown audio focus request type.FocusReqType is " + event);
        return null;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction) {
        if (huReqAction == null) {
            return null;
        }
        LogUtil.info(TAG, "LossDuckFocusState doHuReqActionAndGetNextState event is  " + huReqAction.getEvent());
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[huReqAction.getEvent().ordinal()];
        if (i == 1) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                return null;
            }
            FocusState nextState = LossFocusState.getInstance();
            StateManager.getInstance().setMediaFocusState(false);
            return nextState;
        }
        if (i == 2) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                return null;
            }
            StateManager.getInstance().setMediaFocusState(true);
            FocusState nextState2 = GainFocusState.getInstance();
            return nextState2;
        }
        if (i == 3 || i == 4) {
            StateManager.getInstance().setMediaFocusState(false);
            if (huReqAction.getCauseAudioId() != ResourceConst.AudioId.NATIVE_HIGHPRIORITY) {
                return null;
            }
            FocusState nextState3 = LossTransientFocusState.getInstance();
            return nextState3;
        }
        LogUtil.info(TAG, "Unknown audio focus request type   FocusReqType is " + huReqAction.getEvent());
        return null;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.LossDuckFocusState$1, reason: invalid class name */
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
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_GAIN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_PHONE_INTERRUPT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void enterState(boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "LossDuckFocusState enterState isMDReq is " + isMdReq + " reqIndex:" + reqIndex);
        int currentMdRequestIndex = AapAudioFocusManager.getInstance().getCurrentMdRequestIndex();
        int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
        if ((audioFocusState != 4 && !isMdReq) || (audioFocusState != 4 && isMdReq && reqIndex == currentMdRequestIndex)) {
            if (AapAudioFocusManager.getInstance().getAudioFocus() != 4) {
                AapAudioFocusManager.getInstance().setAudioFocus(4, true);
            }
            StateManager.getInstance().setMdCurrentFocusState(4);
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void exitState() {
        LogUtil.info(TAG, "LossDuckFocusState exitState.");
    }
}
