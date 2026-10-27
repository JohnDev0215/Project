package com.amazon.alexa.voice.core;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface VoiceProcessor {

    public interface ErrorListener {
        void onError(VoiceProcessorException voiceProcessorException);
    }

    Directive[] execute(Event event) throws IOException;
}
