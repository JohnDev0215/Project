package com.amazon.alexa.voice.core;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioTranscoder extends Cancellable {
    AudioFormat getAudioFormat() throws IOException;

    void transcode() throws IOException;
}
