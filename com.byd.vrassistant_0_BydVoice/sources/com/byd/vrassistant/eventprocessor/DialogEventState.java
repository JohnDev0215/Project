package com.byd.vrassistant.eventprocessor;

import com.google.common.base.Enums;

/* JADX INFO: loaded from: classes.dex */
public enum DialogEventState {
    CONVERSATION_BEGIN,
    CONVERSATION_END,
    CAPTURING,
    PROCESSING,
    LISTENING_CMD,
    RECOGNITION_RESTARTED,
    BROADCAST_BEGIN,
    BROADCAST_END,
    CAPTURING_EX,
    PROCESSING_EX;

    public static DialogEventState getIfPresent(String str) {
        return (DialogEventState) Enums.getIfPresent(DialogEventState.class, str).orNull();
    }
}
