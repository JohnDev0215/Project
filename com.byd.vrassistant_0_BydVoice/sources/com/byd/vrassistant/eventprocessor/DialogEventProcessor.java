package com.byd.vrassistant.eventprocessor;

import android.util.Log;
import cerence.ark.assistant.api.notification.DialogEvent;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.utils.InterrupterTimer;
import com.byd.vrassistant.view.ViewStateManger;

/* JADX INFO: loaded from: classes.dex */
public class DialogEventProcessor {
    private static final String TAG = "NEU_DialogEventProcessor";
    static DialogEventProcessor instance = new DialogEventProcessor();
    Runnable EndDialogTask = new Runnable() { // from class: com.byd.vrassistant.eventprocessor.DialogEventProcessor.1
        @Override // java.lang.Runnable
        public void run() {
            Log.i(DialogEventProcessor.TAG, "setDialogEvent: CONVERSATION_END delay 1s");
            if (DialogEventProcessor.this.lastDialogEvent == null) {
                return;
            }
            ViewStateManger.get().setDialogEvent(DialogEventProcessor.this.lastDialogEvent);
        }
    };
    InterrupterTimer eventTimer = new InterrupterTimer(this.EndDialogTask);
    boolean hideInitiative;
    boolean isInConversationSession;
    DialogEvent lastDialogEvent;

    public static DialogEventProcessor getInstance() {
        return instance;
    }

    public void setDialogEvent(DialogEvent dialogEvent) {
        if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_END && this.isInConversationSession) {
            this.lastDialogEvent = dialogEvent;
            this.isInConversationSession = false;
        }
        if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_BEGIN && !BridgeHelper.getVrStatus().isVoiceOnly()) {
            cancelEndConversation();
            this.isInConversationSession = true;
        }
        if (this.hideInitiative && dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_END) {
            Log.i(TAG, "setDialogEvent: CONVERSATION_END hideInitiative");
            ViewStateManger.get().setDialogEvent(dialogEvent);
            this.hideInitiative = false;
        } else if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_END && !this.hideInitiative) {
            this.eventTimer.startTimer(500L);
        } else {
            ViewStateManger.get().setDialogEvent(dialogEvent);
        }
    }

    public void cancelEndConversation() {
        this.eventTimer.interruptTimer();
    }

    public void setHideViewInitiative(boolean z) {
        if (ViewStateManger.get().isInUiSession()) {
            this.hideInitiative = z;
        }
    }

    public void setInConversationSession(boolean z) {
        this.isInConversationSession = z;
    }

    public boolean isInConversationSession() {
        return this.isInConversationSession;
    }
}
