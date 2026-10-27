package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.internal.audio.AudioUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class PCML16AudioRecorder extends AudioRecorder {
    private final AudioFormat audioFormat;
    private short[] buffer;
    private final PCML16AudioTransformer transformer;

    @Override // com.amazon.alexa.voice.core.audio.AudioRecorder
    public int getBitsPerSample() {
        return 16;
    }

    @Override // com.amazon.alexa.voice.core.audio.AudioRecorder
    public int getEncodingFormat() {
        return 2;
    }

    public PCML16AudioRecorder(int i, int i2) {
        int pCMBitRate = AudioUtils.getPCMBitRate(i, 16, i2);
        this.transformer = new PCML16AudioTransformer();
        this.audioFormat = new SimpleAudioFormat("audio/L16", i, pCMBitRate, i2);
    }

    public PCML16AudioRecorder(AudioFormat audioFormat) {
        this(audioFormat.getSampleRate(), audioFormat.getChannelCount());
    }

    @Override // com.amazon.alexa.voice.core.audio.AudioRecorder
    public AudioFormat getAudioFormat() {
        return this.audioFormat;
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.buffer == null) {
            this.buffer = new short[(getBufferSize() / getBitsPerSample()) / 8];
        }
        int internal = readInternal(this.buffer, 0, Math.min((i2 / getBitsPerSample()) / 8, this.buffer.length));
        return internal == -1 ? internal : this.transformer.transform(this.buffer, 0, internal, bArr, i);
    }
}
