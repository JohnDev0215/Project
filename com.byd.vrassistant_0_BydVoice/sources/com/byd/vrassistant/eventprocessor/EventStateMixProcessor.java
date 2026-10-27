package com.byd.vrassistant.eventprocessor;

import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.api.notification.DialogStateChanged;
import com.byd.vrassistant.bean.DialogStateExtension;

/* JADX INFO: loaded from: classes.dex */
public class EventStateMixProcessor {
    DialogEventState lastDialogState;
    OnDialogEventStateUpdateListener onDialogEventStateUpdateListener;

    public interface OnDialogEventStateUpdateListener {
        void onEventStateUpdate(DialogEventState dialogEventState);
    }

    public EventStateMixProcessor(OnDialogEventStateUpdateListener onDialogEventStateUpdateListener) {
        this.onDialogEventStateUpdateListener = onDialogEventStateUpdateListener;
    }

    public void setDialogState(DialogStateChanged.State state) {
        setDialogState(getDialogState(state));
    }

    public void setDialogEvent(DialogEvent dialogEvent) {
        setDialogState(getDialogState(dialogEvent));
    }

    public void setDialogStateExtension(DialogStateExtension dialogStateExtension) {
        setDialogState(getDialogState(dialogStateExtension));
    }

    public void setDialogEventState(DialogEventState dialogEventState) {
        setDialogState(dialogEventState);
    }

    private DialogEventState getDialogState(DialogStateExtension dialogStateExtension) {
        return DialogEventState.getIfPresent(dialogStateExtension.name());
    }

    private DialogEventState getDialogState(DialogStateChanged.State state) {
        return DialogEventState.getIfPresent(state.name());
    }

    private DialogEventState getDialogState(DialogEvent dialogEvent) {
        return DialogEventState.getIfPresent(dialogEvent.getEvent().name());
    }

    private void setDialogState(DialogEventState dialogEventState) {
        if (dialogEventState == this.lastDialogState || dialogEventState == null) {
            return;
        }
        this.onDialogEventStateUpdateListener.onEventStateUpdate(dialogEventState);
        this.lastDialogState = dialogEventState;
    }
}
