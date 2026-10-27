package com.ts.androidauto.aap.audiofocus;

import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes.dex */
public class LossFocusState implements FocusState {
    private static final String TAG = "AAP" + LossFocusState.class.getSimpleName();
    private static LossFocusState mThis;

    public static synchronized LossFocusState getInstance() {
        if (mThis == null) {
            mThis = new LossFocusState();
        }
        return mThis;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doMdReqActionAndGetNextState(FocusState lastState, int event) {
        LogUtil.info(TAG, "LossFocusState doMdReqActionAndGetNextState event is " + event);
        if (event == 1) {
            boolean nativeNaviState = StateManager.getInstance().getNativeTtsState();
            boolean ret = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_MEDIA_GAIN);
            if (ret) {
                LogUtil.info(TAG, "LossFocusState GAIN_REQ success.");
                if (nativeNaviState) {
                    FocusState nextState = LossDuckFocusState.getInstance();
                    return nextState;
                }
                FocusState nextState2 = GainFocusState.getInstance();
                return nextState2;
            }
            LogUtil.info(TAG, "LossFocusState GAIN_REQ failed.");
            FocusState nextState3 = getInstance();
            return nextState3;
        }
        if (event == 2) {
            boolean ret2 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_VR_GAIN);
            if (ret2) {
                LogUtil.info(TAG, "LossFocusState GAIN_TRANSIENT_REQ success.");
                FocusState nextState4 = GainTransientFocusState.getInstance();
                return nextState4;
            }
            LogUtil.info(TAG, "LossFocusState GAIN_TRANSIENT_REQ failed.");
            FocusState nextState5 = getInstance();
            return nextState5;
        }
        if (event == 3) {
            boolean ret3 = AapAudioFocusManager.getInstance().reqHuAudioFocus(ResourceConst.AudioFocusRequest.AUDIOFOCUS_NAVI_GAIN);
            if (ret3) {
                LogUtil.info(TAG, "LossFocusState GAIN_TRANSIENT_MAY_DUCK_REQ   success.");
                FocusState nextState6 = GainTransientFocusState.getInstance();
                return nextState6;
            }
            LogUtil.info(TAG, "LossFocusState GAIN_TRANSIENT_MAY_DUCK_REQ   failed.");
            FocusState nextState7 = getInstance();
            return nextState7;
        }
        if (event == 4) {
            FocusState nextState8 = getInstance();
            return nextState8;
        }
        if (event == 5) {
            LogUtil.info(TAG, "LossFocusState PLAY_VR_REQ skip it.");
            return null;
        }
        LogUtil.info(TAG, "Unknown audio focus request type :FocusReqType is " + event);
        return null;
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public FocusState doHuReqActionAndGetNextState(HuReqAction huReqAction) {
        if (huReqAction == null) {
            return null;
        }
        LogUtil.info(TAG, "LossFocusState doHuReqActionAndGetNextState event is " + huReqAction.getEvent());
        LogUtil.info(TAG, "LossFocusState doHuReq self id: " + huReqAction.getSelfAudioId() + " cause id :" + huReqAction.getCauseAudioId());
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[huReqAction.getEvent().ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
                    return null;
                }
                StateManager.getInstance().setMediaFocusState(false);
                return null;
            }
            if (i == 3) {
                if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_VR) {
                    return null;
                }
                FocusState nextState = LossTransientFocusState.getInstance();
                return nextState;
            }
            LogUtil.info(TAG, "Unknown audio focus request type :FocusReqType is " + huReqAction.getEvent());
            return null;
        }
        if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_MEDIA) {
            if (huReqAction.getSelfAudioId() != ResourceConst.AudioId.PROJECTION_AAP_VR) {
                return null;
            }
            FocusState nextState2 = GainTransientFocusState.getInstance();
            return nextState2;
        }
        int mdFocusReq = StateManager.getInstance().getCurrentMdFocusReq();
        LogUtil.info(TAG, "LossFocusState mdFocusReq is " + mdFocusReq);
        if (mdFocusReq == GalReceiver.AUDIO_FOCUS_GAIN) {
            StateManager.getInstance().setMediaFocusState(true);
            FocusState nextState3 = GainFocusState.getInstance();
            return nextState3;
        }
        LogUtil.info(TAG, "LossFocusState md do not has gain focus do noting");
        return null;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.audiofocus.LossFocusState$1, reason: invalid class name */
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
                $SwitchMap$com$ts$androidauto$fsdk$common$ResourceConst$AudioFocusNotify[ResourceConst.AudioFocusNotify.AUDIOFOCUS_LOSS_TRANSIENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void enterState(boolean isMdReq, int reqIndex) {
        LogUtil.info(TAG, "LossFocusState enterState isMDReq is " + isMdReq + " reqIndex:" + reqIndex);
        int currentMdRequestIndex = AapAudioFocusManager.getInstance().getCurrentMdRequestIndex();
        int audioFocusState = StateManager.getInstance().getMdCurrentFocusState();
        if ((audioFocusState != 3 && !isMdReq) || (audioFocusState != 3 && isMdReq && reqIndex == currentMdRequestIndex)) {
            if (AapAudioFocusManager.getInstance().getAudioFocus() != 3) {
                AapAudioFocusManager.getInstance().setAudioFocus(3, true);
            }
            StateManager.getInstance().setMdCurrentFocusState(3);
        }
        LogUtil.info(TAG, "LossFocusState enter with focus:" + StateManager.getInstance().getMdCurrentFocusState());
        LogUtil.info(TAG, "LossFocusState enter with req:" + StateManager.getInstance().getCurrentMdFocusReq());
        StateManager.getInstance().setMdCurrentFocusState(3);
        StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_RELEASE);
    }

    @Override // com.ts.androidauto.aap.audiofocus.FocusState
    public void exitState() {
        LogUtil.info(TAG, "LossFocusState exitState. ");
    }
}
