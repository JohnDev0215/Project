package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class PCML16VolumeSource implements AudioSource {
    private float currentVolume;
    private float lastReportedVolume;
    private final OnVolumeChangedListener listener;
    private final long reportIntervalMillis;
    private final float reportThreshold;
    private float rmsWindowStart;
    private final AudioSource source;
    private long startAt;

    public interface OnVolumeChangedListener {
        void onVolumeChanged(float f);
    }

    PCML16VolumeSource(Builder builder) {
        this.source = builder.source;
        this.listener = builder.onVolumeChangedListener;
        this.reportThreshold = builder.reportThreshold;
        this.reportIntervalMillis = builder.reportIntervalMillis;
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.source.read(bArr, i, i2);
        if (i3 == -1) {
            return i3;
        }
        float fCalculateRms = calculateRms(bArr, i, i3);
        float f = this.rmsWindowStart;
        if (fCalculateRms < f) {
            this.rmsWindowStart = fCalculateRms;
        } else if (fCalculateRms > f + 150.0f) {
            this.rmsWindowStart = fCalculateRms - 150.0f;
        }
        float f2 = this.rmsWindowStart;
        float fMin = Math.min(1.0f, Math.max(0.0f, (fCalculateRms - f2) / ((150.0f + f2) - f2)));
        float f3 = this.currentVolume;
        if (f3 == 0.0f) {
            this.currentVolume = fMin;
        } else {
            this.currentVolume = (f3 + fMin) / 2.0f;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.startAt >= this.reportIntervalMillis && Math.abs(this.lastReportedVolume - this.currentVolume) >= this.reportThreshold) {
            this.listener.onVolumeChanged(this.currentVolume);
            this.lastReportedVolume = this.currentVolume;
            this.currentVolume = 0.0f;
            this.startAt = jCurrentTimeMillis;
        }
        return i3;
    }

    float calculateRms(byte[] bArr, int i, int i2) {
        int i3 = i2 / 2;
        float f = 0.0f;
        for (int i4 = 0; i4 < i2; i4 += 2) {
            int i5 = i + i4;
            float f2 = (short) ((bArr[i5] & 255) + ((bArr[i5 + 1] & 255) << 8));
            f += (f2 * f2) / i3;
        }
        return (float) Math.sqrt(f);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }

    public static final class Builder {
        OnVolumeChangedListener onVolumeChangedListener;
        long reportIntervalMillis = 120;
        float reportThreshold = 0.2f;
        AudioSource source;

        public Builder(AudioSource audioSource) {
            this.source = audioSource;
        }

        public Builder onVolumeChanged(OnVolumeChangedListener onVolumeChangedListener) {
            this.onVolumeChangedListener = onVolumeChangedListener;
            return this;
        }

        public PCML16VolumeSource build() {
            Preconditions.notNull(this.source, "source == null");
            Preconditions.notNull(this.onVolumeChangedListener, "onVolumeChangedListener == null");
            Preconditions.min(this.reportIntervalMillis, 0L, "reportIntervalMillis cannot be negative");
            Preconditions.range(this.reportThreshold, 0.0f, 1.0f, "reportThreshold has to be in range from 0 to 1");
            return new PCML16VolumeSource(this);
        }
    }
}
