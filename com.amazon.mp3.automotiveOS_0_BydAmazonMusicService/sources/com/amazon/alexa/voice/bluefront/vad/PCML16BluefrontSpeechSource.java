package com.amazon.alexa.voice.bluefront.vad;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.util.CircularBuffer;
import com.amazon.alexa.voice.core.internal.util.Functions;
import com.amazon.alexa.voice.core.internal.util.IOUtils;
import com.amazon.blueshift.bluefront.android.vad.AbstractVAD;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class PCML16BluefrontSpeechSource implements AudioSource {
    private final long audioTimeoutMillis;
    private final Action onSpeechEnd;
    private final Action onSpeechStart;
    private final Action onSpeechTimeout;
    private final AudioSource source;
    private boolean speechBegan;
    private final CircularBuffer speechBuffer;
    private boolean speechEnded;
    private long startAt;
    private final VAD vad;

    private PCML16BluefrontSpeechSource(Builder builder) {
        this.source = builder.source;
        this.audioTimeoutMillis = builder.audioTimeoutMillis;
        this.onSpeechStart = builder.onSpeechStart;
        this.onSpeechEnd = builder.onSpeechEnd;
        this.onSpeechTimeout = builder.onSpeechTimeout;
        this.vad = new VAD(builder.sampleRate);
        this.speechBuffer = new CircularBuffer(builder.sampleRate);
    }

    private void notifySpeechStarted() {
        try {
            this.onSpeechStart.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void notifySpeechEnd() {
        try {
            this.onSpeechEnd.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void notifySpeechTimeout() {
        try {
            this.onSpeechTimeout.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.speechEnded) {
            return -1;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.startAt == 0) {
            this.startAt = jCurrentTimeMillis;
            Logger.verbose("Waiting for beginning of a speech");
        }
        if (this.speechBegan && !this.speechBuffer.empty()) {
            int i3 = this.speechBuffer.read(bArr, i, i2);
            Logger.verbose("Reading from a pre-recorded buffer %d bytes", Integer.valueOf(i3));
            return i3;
        }
        int i4 = this.source.read(bArr, i, i2);
        if (i4 < 0) {
            return i4;
        }
        int iIsSpeech = this.vad.isSpeech(bArr, i, i4);
        if (this.speechBegan) {
            if (iIsSpeech == 2) {
                Logger.verbose("Detected end of a speech at %dms", Long.valueOf(jCurrentTimeMillis - this.startAt));
                this.speechEnded = true;
                notifySpeechEnd();
            }
            return i4;
        }
        this.speechBuffer.write(bArr, i, i4);
        if (iIsSpeech == 1) {
            Logger.verbose("Detected beginning of a speech at %dms", Long.valueOf(jCurrentTimeMillis - this.startAt));
            this.speechBegan = true;
            notifySpeechStarted();
            return 0;
        }
        if (iIsSpeech == 2) {
            throw new IOException("Unexpected voice detection sate. Found end of speech, expected a beginning");
        }
        if (jCurrentTimeMillis - this.startAt <= this.audioTimeoutMillis) {
            return 0;
        }
        this.speechBegan = true;
        this.speechEnded = true;
        notifySpeechTimeout();
        return -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.closeQuietly(this.vad);
        this.source.close();
    }

    public static final class Builder {
        private int sampleRate;
        private AudioSource source;
        private long audioTimeoutMillis = 10000;
        private Action onSpeechStart = Functions.emptyAction();
        private Action onSpeechEnd = Functions.emptyAction();
        private Action onSpeechTimeout = Functions.emptyAction();

        public Builder(AudioSource audioSource) {
            this.source = audioSource;
        }

        public Builder sampleRate(int i) {
            this.sampleRate = i;
            return this;
        }

        public Builder audioTimeoutMillis(long j) {
            this.audioTimeoutMillis = j;
            return this;
        }

        public Builder onSpeechEnd(Action action) {
            this.onSpeechEnd = action;
            return this;
        }

        public Builder onSpeechTimeout(Action action) {
            this.onSpeechTimeout = action;
            return this;
        }

        public PCML16BluefrontSpeechSource build() {
            if (this.source == null) {
                throw new IllegalArgumentException("source == null");
            }
            if (this.onSpeechStart == null) {
                throw new IllegalArgumentException("onSpeechStart == null");
            }
            if (this.onSpeechEnd == null) {
                throw new IllegalArgumentException("onSpeechEnd == null");
            }
            if (this.onSpeechTimeout == null) {
                throw new IllegalArgumentException("onSpeechTimeout == null");
            }
            if (this.sampleRate <= 0) {
                throw new IllegalArgumentException("sampleRate <= 0");
            }
            return new PCML16BluefrontSpeechSource(this);
        }
    }

    static final class VAD extends AbstractVAD {
        private int consecutiveNonSpeechFrames;
        private int consecutiveSpeechFrames;
        private int remaining;
        private final int sampleRate;
        private final short[] samples;
        private int state = 0;

        public VAD(int i) {
            this.sampleRate = i;
            short[] sArr = new short[i / 100];
            this.samples = sArr;
            this.remaining = sArr.length;
        }

        public int isSpeech(byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            int i5 = i2 / 2;
            int i6 = 0;
            while (true) {
                i3 = this.remaining;
                i4 = i5 - i6;
                if (i3 > i4) {
                    break;
                }
                short[] sArr = this.samples;
                writeSamples(sArr, sArr.length - i3, bArr, i + (i6 * 2), i3);
                i6 += this.remaining;
                short[] sArr2 = this.samples;
                this.remaining = sArr2.length;
                if (isSpeech(sArr2, this.sampleRate)) {
                    this.consecutiveSpeechFrames++;
                    this.consecutiveNonSpeechFrames = 0;
                } else {
                    this.consecutiveSpeechFrames = 0;
                    this.consecutiveNonSpeechFrames++;
                }
                int i7 = this.state;
                if (i7 == 0 && this.consecutiveSpeechFrames >= 15) {
                    this.state = 1;
                } else if (i7 == 1 && this.consecutiveNonSpeechFrames >= 60) {
                    this.state = 2;
                }
            }
            if (i4 > 0) {
                short[] sArr3 = this.samples;
                writeSamples(sArr3, sArr3.length - i3, bArr, i + (i6 * 2), i4);
                this.remaining -= i4;
            }
            return this.state;
        }

        private void writeSamples(short[] sArr, int i, byte[] bArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                sArr[i + i4] = (short) ((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8));
                i4++;
                i2 += 2;
            }
        }
    }
}
