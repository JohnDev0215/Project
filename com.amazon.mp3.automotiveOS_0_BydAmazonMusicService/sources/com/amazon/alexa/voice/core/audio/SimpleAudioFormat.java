package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class SimpleAudioFormat implements AudioFormat {
    private final int bitRate;
    private final int channelCount;
    private final String mime;
    private final int sampleRate;

    public SimpleAudioFormat(String str, int i, int i2, int i3) {
        Preconditions.notNull(str, "mime == null");
        this.mime = str;
        this.sampleRate = i;
        this.bitRate = i2;
        this.channelCount = i3;
    }

    @Override // com.amazon.alexa.voice.core.AudioFormat
    public String getMime() {
        return this.mime;
    }

    @Override // com.amazon.alexa.voice.core.AudioFormat
    public int getSampleRate() {
        return this.sampleRate;
    }

    @Override // com.amazon.alexa.voice.core.AudioFormat
    public int getBitRate() {
        return this.bitRate;
    }

    @Override // com.amazon.alexa.voice.core.AudioFormat
    public int getChannelCount() {
        return this.channelCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SimpleAudioFormat.class != obj.getClass()) {
            return false;
        }
        SimpleAudioFormat simpleAudioFormat = (SimpleAudioFormat) obj;
        if (this.sampleRate == simpleAudioFormat.sampleRate && this.bitRate == simpleAudioFormat.bitRate && this.channelCount == simpleAudioFormat.channelCount) {
            return this.mime.equals(simpleAudioFormat.mime);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.mime.hashCode() * 31) + this.sampleRate) * 31) + this.bitRate) * 31) + this.channelCount;
    }

    public String toString() {
        return "SimpleAudioFormat{mime='" + this.mime + "', sampleRate=" + this.sampleRate + ", bitRate=" + this.bitRate + ", channelCount=" + this.channelCount + '}';
    }
}
