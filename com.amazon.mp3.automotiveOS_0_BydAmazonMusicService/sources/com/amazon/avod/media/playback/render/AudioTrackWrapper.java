package com.amazon.avod.media.playback.render;

import android.media.AudioTrack;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackWrapper {
    private final AudioTrack mAudioTrack;
    private final long mAudioTrackBufferSizeBytes;
    private final long mAudioTrackBufferSizeUs;

    public AudioTrackWrapper(@Nonnull AudioTrack audioTrack, long j, long j2) {
        this.mAudioTrack = (AudioTrack) Preconditions.checkNotNull(audioTrack, "audioTrack");
        Preconditions.checkState(j > 0, "audioTrackBufferSizeUs");
        this.mAudioTrackBufferSizeUs = j;
        Preconditions.checkState(j2 > 0, "audioTrackBufferSizeBytes");
        this.mAudioTrackBufferSizeBytes = j2;
    }

    @Nonnull
    public AudioTrack getAudioTrack() {
        return this.mAudioTrack;
    }

    public long getAudioTrackBufferSizeUs() {
        return this.mAudioTrackBufferSizeUs;
    }
}
