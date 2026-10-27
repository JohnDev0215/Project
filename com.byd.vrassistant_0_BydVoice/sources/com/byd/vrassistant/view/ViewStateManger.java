package com.byd.vrassistant.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.notification.AsrResultUpdated;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.api.notification.DialogStateChanged;
import cerence.ark.assistant.controller.domain.hmi.param.HmiFontColorParam;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.bean.AnimState;
import com.byd.vrassistant.bean.ContentState;
import com.byd.vrassistant.bean.DialogStateExtension;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.bean.VrContent;
import com.byd.vrassistant.bean.jsonparse.InteractionBean;
import com.byd.vrassistant.bean.jsonparse.VrUiState;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.eventbus.VoiceSateEvent;
import com.byd.vrassistant.eventprocessor.DialogEventProcessor;
import com.byd.vrassistant.eventprocessor.DialogEventState;
import com.byd.vrassistant.eventprocessor.EventStateMixProcessor;
import com.byd.vrassistant.eventprocessor.WaveState;
import com.byd.vrassistant.hint.GSON;
import com.byd.vrassistant.hint.HintMgr;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.InterrupterTimer;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewInf.IViewStateManager;
import com.byd.vrassistant.viewmodel.AnimViewModel;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.byd.vrassistant.viewmodel.VoiceBarViewModel;

/* JADX INFO: loaded from: classes2.dex */
public class ViewStateManger implements IViewStateManager {
    VrContent currentVrContent;
    boolean inUiSession;
    boolean isInSession;
    boolean isUiInit;
    boolean vrStarted;
    boolean vrWaitToShow;
    String zone;
    String TAG = "NEU_ViewStateManger";
    HintMgr hintMgr = new HintMgr();
    Context context = ContextHolder.getApplicationContext();
    CommonViewModel commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
    ViewManagerViewModel viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
    VoiceBarViewModel voiceBarViewModel = (VoiceBarViewModel) ViewModelHelper.getAppViewModel(VoiceBarViewModel.class);
    AnimViewModel animViewModel = (AnimViewModel) ViewModelHelper.getAppViewModel(AnimViewModel.class);
    EventStateMixProcessor eventStateMixProcessor = new EventStateMixProcessor(new EventStateMixProcessor.OnDialogEventStateUpdateListener() { // from class: com.byd.vrassistant.view.ViewStateManger$$ExternalSyntheticLambda0
        @Override // com.byd.vrassistant.eventprocessor.EventStateMixProcessor.OnDialogEventStateUpdateListener
        public final void onEventStateUpdate(DialogEventState dialogEventState) {
            this.f$0.m203lambda$new$0$combydvrassistantviewViewStateManger(dialogEventState);
        }
    });
    InterrupterTimer initMsgTimer = new InterrupterTimer(new Runnable() { // from class: com.byd.vrassistant.view.ViewStateManger$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m204lambda$new$1$combydvrassistantviewViewStateManger();
        }
    });
    Runnable broadcastEndTask = new Runnable() { // from class: com.byd.vrassistant.view.ViewStateManger$$ExternalSyntheticLambda2
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m205lambda$new$2$combydvrassistantviewViewStateManger();
        }
    };
    InterrupterTimer broadcastTimer = new InterrupterTimer(this.broadcastEndTask);
    InterrupterTimer hintTimer = new InterrupterTimer(new Runnable() { // from class: com.byd.vrassistant.view.ViewStateManger$$ExternalSyntheticLambda3
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.showHint();
        }
    });
    int validTimes = 0;
    int responseTimes = 0;

    static class InstanceHolder {
        static ViewStateManger INSTANCE = new ViewStateManger();

        InstanceHolder() {
        }
    }

    public static ViewStateManger get() {
        return InstanceHolder.INSTANCE;
    }

    public VrContent getCurrentVrContent() {
        if (this.currentVrContent == null) {
            this.currentVrContent = VrContent.emptyInit();
        }
        return this.currentVrContent;
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-view-ViewStateManger, reason: not valid java name */
    /* synthetic */ void m203lambda$new$0$combydvrassistantviewViewStateManger(DialogEventState dialogEventState) {
        if (isVrStarted() && isInUiSession()) {
            this.animViewModel.setDialogEventState(dialogEventState);
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-byd-vrassistant-view-ViewStateManger, reason: not valid java name */
    /* synthetic */ void m204lambda$new$1$combydvrassistantviewViewStateManger() {
        setVrWaitToShow(false);
        this.viewManagerViewModel.updateVoiceBarType(VoiceBarType.NONE);
    }

    /* JADX INFO: renamed from: lambda$new$2$com-byd-vrassistant-view-ViewStateManger, reason: not valid java name */
    /* synthetic */ void m205lambda$new$2$combydvrassistantviewViewStateManger() {
        this.animViewModel.setDialogEventState(DialogEventState.BROADCAST_END);
        Log.i(this.TAG, "broadcastEndTask ");
        if (getCurrentVrContent().getContentState() == ContentState.HINT || getCurrentVrContent().getContentState() == ContentState.HINT_CONTINUE || getCurrentVrContent().getContentState() == ContentState.ISSUING) {
            return;
        }
        if (getCurrentVrContent().isInputInvalid() || getCurrentVrContent().isRejection() || this.isInSession) {
            showHint();
        } else {
            this.voiceBarViewModel.setVrContent(getCurrentVrContent().setCurrentState(ContentState.NONE));
        }
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setPanoStart(boolean z) {
        this.commonViewModel.setIsPanoramicStart(z);
    }

    public void setVrWaitToShow(boolean z) {
        this.vrWaitToShow = z;
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setVoiceStateEvent(VoiceSateEvent voiceSateEvent) {
        Log.i(this.TAG, "setVoiceStateEvent: isVoiceStarted=" + voiceSateEvent.getVoiceStarted() + ",vrWaitToShow=" + this.vrWaitToShow);
        this.vrStarted = voiceSateEvent.getVoiceStarted();
        if (BridgeHelper.getVrStatus().isVoiceOnly()) {
            return;
        }
        if (voiceSateEvent.getVoiceStarted()) {
            if (this.vrWaitToShow) {
                BridgeHelper.getVrOperation().sendPTTEvent(this.zone);
            }
            setVrWaitToShow(false);
            this.initMsgTimer.interruptTimer();
            return;
        }
        if (voiceSateEvent.isShowGuid()) {
            return;
        }
        setVrWaitToShow(true);
        getCurrentVrContent().setCurrentState(ContentState.INIT_MSG).setInput(ContextHolder.getSysContext().getString(R.string.vr_not_available));
        this.voiceBarViewModel.setVrContent(getCurrentVrContent());
        Log.i(this.TAG, "setVoiceStateEvent: " + this.zone);
        if (this.zone.equals(VehicleAreaEnum.SEAT_ROW_FRONT) && BridgeHelper.getVrAreaStatus().isDriverOnly()) {
            this.zone = BridgeHelper.getVrAreaStatus().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT;
        }
        this.voiceBarViewModel.setVehicleAreaEnum(this.zone);
        this.viewManagerViewModel.setStartupParam(VrUiState.initStarting(this.zone));
        this.viewManagerViewModel.removeViewBlew();
        this.animViewModel.setAnimState(AnimState.NORMAL);
        this.animViewModel.updateWaveState(WaveState.NONE);
        this.initMsgTimer.startTimer(5000L);
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setPttEventZone(String str) {
        this.zone = str;
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setDialogState(DialogStateChanged.State state, long j) {
        Log.i(this.TAG, "dialogState update: state= " + state + ",listenCmdTime=" + j);
        this.animViewModel.setDialogEventState(DialogEventState.getIfPresent(state.name()));
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void showHint() {
        RLog.caller(this.TAG, "showHint");
        if (getCurrentVrContent().getContentState() == ContentState.ISSUING || this.hintTimer.isTimerRunning()) {
            return;
        }
        Log.i(this.TAG, "showHint: isInSession=" + this.isInSession);
        if (this.isInSession) {
            this.voiceBarViewModel.setVrContent(getCurrentVrContent().setCurrentState(ContentState.HINT_CONTINUE));
            return;
        }
        this.voiceBarViewModel.setVrContent(getCurrentVrContent().setCurrentState(ContentState.HINT).setHints(this.hintMgr.getHintShow()));
        this.hintTimer.startTimer(5000L);
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setAsrResultUpdated(AsrResultUpdated asrResultUpdated) {
        if (asrResultUpdated.getSubtype() == AsrResultUpdated.Subtype.WUW_SDS) {
            Log.i(this.TAG, "setAsrResultUpdated: don't show hi,byd");
            this.validTimes = 0;
            this.responseTimes = 0;
            return;
        }
        if (!isInConversationSession() || TextUtils.isEmpty(asrResultUpdated.getUtterance())) {
            Log.i(this.TAG, "setAsrResultUpdated: not in conversation or asr is empty,asr= " + asrResultUpdated.getUtterance());
            return;
        }
        this.hintTimer.interruptTimer();
        Log.i(this.TAG, "setAsrResultUpdated: " + asrResultUpdated.getUtterance() + ",isFinal=" + asrResultUpdated.isFinal());
        getCurrentVrContent().setCurrentState(ContentState.ISSUING).setInput(asrResultUpdated.getUtterance());
        if (asrResultUpdated.isFinal()) {
            this.eventStateMixProcessor.setDialogStateExtension(DialogStateExtension.PROCESSING_EX);
            this.voiceBarViewModel.setVrContent(getCurrentVrContent());
        } else if (BridgeHelper.getVrStatus().getEnableWordStreaming()) {
            this.eventStateMixProcessor.setDialogStateExtension(DialogStateExtension.CAPTURING_EX);
            this.voiceBarViewModel.setVrContent(getCurrentVrContent());
        }
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void inputHandleResult(HmiFontColorParam hmiFontColorParam) {
        ContentState contentState = hmiFontColorParam.getFontColor().trim().equalsIgnoreCase("GREY") ? ContentState.INVALID : ContentState.VALID;
        this.viewManagerViewModel.cmdHandle(hmiFontColorParam);
        if (contentState == ContentState.INVALID) {
            this.eventStateMixProcessor.setDialogEventState(DialogEventState.RECOGNITION_RESTARTED);
            this.hintTimer.interruptTimer();
            this.hintTimer.startTimer(2000L);
        }
        if (ContentState.ISSUING == getCurrentVrContent().getContentState() || ContentState.RESPONSE == getCurrentVrContent().getContentState()) {
            this.voiceBarViewModel.setVrContent(getCurrentVrContent().setCurrentState(contentState));
        }
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setResponseContent(String str, long j) {
        Log.i(this.TAG, "setResponseContent: " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.hintTimer.interruptTimer();
        this.voiceBarViewModel.setVrContent(getCurrentVrContent().addResponse(str).setCurrentState(ContentState.RESPONSE));
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setDialogEvent(DialogEvent dialogEvent) {
        RLog.s_log(this.TAG, "dialogState update: event= " + dialogEvent.getEvent(), ",extra=" + dialogEvent.getExtra());
        boolean zContains = dialogEvent.getEvent().name().contains("END");
        if (!BridgeHelper.getVrStatus().isVoiceOnly() || zContains) {
            switch (AnonymousClass1.$SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[dialogEvent.getEvent().ordinal()]) {
                case 1:
                    this.inUiSession = true;
                    this.validTimes = 0;
                    this.responseTimes = 0;
                    this.viewManagerViewModel.removeViewBlew();
                    this.viewManagerViewModel.setShowGoogleIngo(false);
                    DialogEventProcessor.getInstance().cancelEndConversation();
                    VrUiState vrUiState = (VrUiState) GSON.fromJson(dialogEvent.getExtra(), VrUiState.class);
                    if (vrUiState.getZone().equals(VehicleAreaEnum.SEAT_ROW_FRONT) && BridgeHelper.getVrAreaStatus().isDriverOnly()) {
                        vrUiState.setZone(BridgeHelper.getVrAreaStatus().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT);
                    }
                    if (vrUiState.getReason().equals("SHORTCUT")) {
                        return;
                    }
                    vrUiState.setVrStart(true);
                    vrUiState.setVoiceBarType(VoiceBarType.NORMAL);
                    this.voiceBarViewModel.setVehicleAreaEnum(vrUiState.getZone());
                    this.animViewModel.updateWakeArea(vrUiState.getZone());
                    this.animViewModel.setAnimState(AnimState.CYCLE);
                    this.viewManagerViewModel.setStartupParam(vrUiState);
                    showHint();
                    this.viewManagerViewModel.setConversationState(true);
                    break;
                case 2:
                    this.viewManagerViewModel.setConversationState(false);
                    this.voiceBarViewModel.setVrContent(getCurrentVrContent().setCurrentState(ContentState.NONE));
                    ViewManager.get().removeAllView(true);
                    this.hintTimer.interruptTimer();
                    DialogEventProcessor.getInstance().setHideViewInitiative(false);
                    break;
                case 3:
                    this.responseTimes = this.validTimes;
                    InteractionBean interactionBean = (InteractionBean) GSON.fromJson(dialogEvent.getExtra(), InteractionBean.class);
                    if (interactionBean.getInteraction_type().isEmpty()) {
                        getCurrentVrContent().setInteractionType("");
                        setResponseContent(interactionBean.getText(), System.currentTimeMillis());
                        return;
                    }
                    getCurrentVrContent().setInteractionType(interactionBean.getInteraction_type().get(0));
                    if (getCurrentVrContent().isCancel()) {
                        DialogEventProcessor.getInstance().setHideViewInitiative(true);
                        this.animViewModel.setDialogEventState(DialogEventState.RECOGNITION_RESTARTED);
                        return;
                    }
                    if (!getCurrentVrContent().isTimeout()) {
                        setResponseContent(interactionBean.getText(), System.currentTimeMillis());
                    }
                    if (getCurrentVrContent().isStillCycle()) {
                        return;
                    }
                    this.broadcastTimer.interruptTimer();
                    Log.i(this.TAG, "startBroadCast: " + getCurrentVrContent());
                    this.animViewModel.setDialogEventState(DialogEventState.BROADCAST_BEGIN);
                    if (TextUtils.isEmpty(getCurrentVrContent().getResponse())) {
                        this.broadcastTimer.startTimer(2000L);
                    }
                    break;
                    break;
                case 4:
                    if (getCurrentVrContent().isStillCycle()) {
                        return;
                    }
                    if (this.broadcastTimer.isTimerRunning()) {
                        Log.i(this.TAG, "broadcastTimer: return ");
                        return;
                    }
                    this.broadcastTimer.startTimer(1300L);
                    break;
                case 5:
                case 6:
                    this.isInSession = false;
                    this.voiceBarViewModel.resetArea();
                    this.viewManagerViewModel.sessionEnd();
                    this.viewManagerViewModel.removeViewBlew();
                    break;
                case 7:
                    this.responseTimes = this.validTimes;
                    this.isInSession = true;
                    break;
            }
            this.animViewModel.setDialogEventState(DialogEventState.getIfPresent(dialogEvent.getEvent().name()));
            if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_END) {
                this.inUiSession = false;
                BridgeHelper.getSystemSetting().setSendingHomeKey(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.view.ViewStateManger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event;

        static {
            int[] iArr = new int[DialogEvent.Event.values().length];
            $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event = iArr;
            try {
                iArr[DialogEvent.Event.CONVERSATION_BEGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.CONVERSATION_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.PROMPT_BEGIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.PROMPT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.SNOOZING_BEGIN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.SESSION_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.SESSION_BEGIN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.RECOGNITION_RESTARTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$api$notification$DialogEvent$Event[DialogEvent.Event.SNOOZING_END.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public boolean isInUiSession() {
        return this.inUiSession;
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public boolean isInConversationSession() {
        return DialogEventProcessor.getInstance().isInConversationSession();
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public boolean isVrStarted() {
        return this.vrStarted;
    }

    @Override // com.byd.vrassistant.viewInf.IViewStateManager
    public void setContext(Context context) {
        this.context = context;
        Log.i(this.TAG, "setContext: " + context.getResources().getConfiguration().getLocales().get(0));
        this.hintMgr.onDestroy();
        this.hintMgr = new HintMgr();
    }

    public void executeReShowOnDriverSide() {
        if (!isInConversationSession() || BridgeHelper.getVrStatus().isVoiceOnly()) {
            return;
        }
        this.viewManagerViewModel.updateVoiceBarType(VoiceBarType.NORMAL);
        this.viewManagerViewModel.removeViewBlew();
        this.animViewModel.setAnimState(AnimState.CYCLE);
        this.isInSession = false;
        showHint();
        DialogEventProcessor.getInstance().cancelEndConversation();
        VrUiState vrUiStateInitPTT = VrUiState.initPTT();
        this.voiceBarViewModel.setVehicleAreaEnum(vrUiStateInitPTT.getZone());
        this.viewManagerViewModel.setStartupParam(vrUiStateInitPTT);
        this.animViewModel.updateWakeArea(vrUiStateInitPTT.getZone());
    }

    public void executeCancelPtt() {
        DialogEventProcessor.getInstance().setHideViewInitiative(true);
        if (isInConversationSession()) {
            BridgeHelper.getVrOperation().sendCancelPTTEvent();
            return;
        }
        if (this.viewManagerViewModel.isViewShow() && !isInUiSession()) {
            ViewManager.get().removeAllView(true);
        }
        BridgeHelper.getVrUiStatus().onBackPress();
    }

    public void removeViewWhenNotInConversation() {
        if (isInConversationSession() || !this.isUiInit) {
            return;
        }
        ViewManager.get().removeAllView(true);
        setVrWaitToShow(false);
    }
}
