package com.byd.vrassistant.viewInf;

import android.content.Context;
import cerence.ark.assistant.api.notification.AsrResultUpdated;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.api.notification.DialogStateChanged;
import cerence.ark.assistant.controller.domain.hmi.param.HmiFontColorParam;
import com.byd.vrassistant.eventbus.VoiceSateEvent;
import com.byd.vrassistant.viewInf.common.IViewCommon;

/* JADX INFO: loaded from: classes2.dex */
public interface IViewStateManager extends IViewCommon {
    void inputHandleResult(HmiFontColorParam hmiFontColorParam);

    boolean isInConversationSession();

    boolean isInUiSession();

    boolean isVrStarted();

    void setAsrResultUpdated(AsrResultUpdated asrResultUpdated);

    void setContext(Context context);

    void setDialogEvent(DialogEvent dialogEvent);

    void setDialogState(DialogStateChanged.State state, long j);

    void setPanoStart(boolean z);

    void setPttEventZone(String str);

    void setResponseContent(String str, long j);

    void setVoiceStateEvent(VoiceSateEvent voiceSateEvent);

    void showHint();
}
