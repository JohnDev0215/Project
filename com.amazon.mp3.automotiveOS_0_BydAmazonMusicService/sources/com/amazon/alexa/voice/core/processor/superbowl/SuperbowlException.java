package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.VoiceProcessorException;

/* JADX INFO: loaded from: classes2.dex */
public final class SuperbowlException extends VoiceProcessorException {
    private final int code;

    public SuperbowlException(String str, int i) {
        super(str);
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
