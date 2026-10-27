package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioStreamAndQualityPair {
    private final AudioQualityLevel mAudioQualityLevel;
    private final StreamIndex mStreamIndex;

    public AudioStreamAndQualityPair(@Nonnull StreamIndex streamIndex, @Nonnull AudioQualityLevel audioQualityLevel) {
        this.mStreamIndex = (StreamIndex) Preconditions.checkNotNull(streamIndex, "streamIndex");
        this.mAudioQualityLevel = (AudioQualityLevel) Preconditions.checkNotNull(audioQualityLevel, "audioQualityLevel");
    }

    @Nonnull
    public StreamIndex getAudioStream() {
        return this.mStreamIndex;
    }

    @Nonnull
    public AudioQualityLevel getAudioQualityLevel() {
        return this.mAudioQualityLevel;
    }
}
