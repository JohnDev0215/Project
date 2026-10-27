package com.amazon.alexa.voice.core.internal.audio;

import com.amazon.alexa.voice.core.AudioTranscoder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioTranscoderBase implements AudioTranscoder {
    private volatile boolean cancelled;

    @Override // com.amazon.alexa.voice.core.Cancellable
    public final boolean isCancelled() {
        return this.cancelled;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public final void cancel() {
        this.cancelled = true;
    }
}
