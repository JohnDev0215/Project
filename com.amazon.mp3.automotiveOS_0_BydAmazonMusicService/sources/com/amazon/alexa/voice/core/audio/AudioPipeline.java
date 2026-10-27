package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.audio.AudioTranscoderBase;
import com.amazon.alexa.voice.core.internal.audio.AudioUtils;
import com.amazon.alexa.voice.core.internal.util.IOUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPipeline extends AudioTranscoderBase {
    private final AudioFormat format;
    private final AudioSink sink;
    private final AudioSource source;

    public AudioPipeline(AudioSource audioSource, AudioSink audioSink, AudioFormat audioFormat) {
        this.source = audioSource;
        this.sink = audioSink;
        this.format = audioFormat;
    }

    public AudioPipeline(AudioSource audioSource, AudioSink audioSink) {
        this(audioSource, audioSink, null);
    }

    @Override // com.amazon.alexa.voice.core.AudioTranscoder
    public AudioFormat getAudioFormat() throws IOException {
        AudioFormat audioFormat = this.format;
        if (audioFormat != null) {
            return audioFormat;
        }
        throw new IOException("Audio format is not supported");
    }

    @Override // com.amazon.alexa.voice.core.AudioTranscoder
    public void transcode() throws IOException {
        try {
            AudioUtils.transfer(this.source, this.sink, this);
        } finally {
            Logger.debug("Closing audio source & sink");
            IOUtils.closeQuietly(this.source);
            IOUtils.closeQuietly(this.sink);
            Logger.debug("Audio source & sink closed");
        }
    }
}
