package com.amazon.alexa.voice.core.audio;

import android.media.AudioRecord;
import android.media.audiofx.NoiseSuppressor;
import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.audio.AudioBase;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioRecorder extends AudioBase implements AudioSource {
    private AudioRecord audioRecord;
    private volatile boolean shouldStop;

    public abstract AudioFormat getAudioFormat();

    public abstract int getBitsPerSample();

    public abstract int getEncodingFormat();

    private void ensureInitialized() throws IOException {
        NoiseSuppressor noiseSuppressorCreate;
        if (this.audioRecord != null) {
            return;
        }
        Logger.debug("AudioRecorder initializing");
        AudioRecord audioRecord = new AudioRecord(1, getAudioFormat().getSampleRate(), getChannelConfig(), getEncodingFormat(), getBufferSize() * 4);
        this.audioRecord = audioRecord;
        if (audioRecord.getState() != 1) {
            throw new IOException("Audio record is not initialized");
        }
        Logger.debug("AudioRecorder initialized");
        if (NoiseSuppressor.isAvailable() && (noiseSuppressorCreate = NoiseSuppressor.create(this.audioRecord.getAudioSessionId())) != null) {
            noiseSuppressorCreate.setEnabled(true);
        }
        Logger.debug("AudioRecorder starting recording");
        this.audioRecord.startRecording();
    }

    @Override // com.amazon.alexa.voice.core.internal.audio.AudioBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.shouldStop = false;
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                Logger.debug("AudioRecorder stopping");
                this.audioRecord.stop();
            }
            Logger.debug("AudioRecorder releasing");
            this.audioRecord.release();
            this.audioRecord = null;
            Logger.debug("AudioRecorder released");
        }
    }

    private int getChannelConfig() {
        int channelCount = getAudioFormat().getChannelCount();
        if (channelCount == 2) {
            return 12;
        }
        if (channelCount == 1) {
            return 16;
        }
        throw new IllegalArgumentException("Unsupported channel count " + channelCount);
    }

    public final void signalEndOfStream() {
        this.shouldStop = true;
    }

    protected final int readInternal(short[] sArr, int i, int i2) throws IOException {
        checkClosed();
        if (this.shouldStop) {
            return -1;
        }
        ensureInitialized();
        return checkResult(this.audioRecord.read(sArr, i, i2));
    }

    public final int getBufferSize() {
        AudioFormat audioFormat = getAudioFormat();
        int minBufferSize = AudioRecord.getMinBufferSize(audioFormat.getSampleRate(), getChannelConfig(), getEncodingFormat());
        return (minBufferSize == -1 || minBufferSize == -2) ? audioFormat.getSampleRate() * getBitsPerSample() * 8 : minBufferSize;
    }
}
