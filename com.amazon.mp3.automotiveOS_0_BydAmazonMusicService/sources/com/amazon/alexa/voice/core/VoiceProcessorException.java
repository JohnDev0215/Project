package com.amazon.alexa.voice.core;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceProcessorException extends IOException {
    public VoiceProcessorException(IOException iOException) {
        super(iOException);
    }

    public VoiceProcessorException(String str) {
        super(str);
    }
}
