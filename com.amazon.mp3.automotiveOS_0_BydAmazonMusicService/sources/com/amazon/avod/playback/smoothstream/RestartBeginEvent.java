package com.amazon.avod.playback.smoothstream;

import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RestartBeginEvent {
    private final int mAudioAdaptationSetBitrate;
    private final String mAudioAdaptationSetName;
    private final PlaybackRestartEvent mCause;
    public final VideoSpecification mNewSpec;
    public final String mOldLanguage;
    public final VideoSpecification mOldSpec;
    private final String mStreamSelectionParams;

    public RestartBeginEvent(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoSpecification videoSpecification2, @Nonnull String str, @Nonnull String str2, int i, @Nonnull String str3, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
        this.mOldSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "oldSpec");
        this.mNewSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification2, "newSpec");
        this.mOldLanguage = (String) Preconditions.checkNotNull(str, "language");
        this.mAudioAdaptationSetName = (String) Preconditions.checkNotNull(str2, "audioAdaptationSetName");
        this.mAudioAdaptationSetBitrate = i;
        this.mStreamSelectionParams = (String) Preconditions.checkNotNull(str3, "streamSelectionParams");
        this.mCause = (PlaybackRestartEvent) Preconditions.checkNotNull(playbackRestartEvent, "cause");
    }

    @Nonnull
    public final VideoSpecification getOldSpec() {
        return this.mOldSpec;
    }

    @Nonnull
    public final VideoSpecification getNewSpec() {
        return this.mNewSpec;
    }

    @Nonnull
    public final String getOldLanguage() {
        return this.mOldLanguage;
    }

    @Nonnull
    public final String getNewLanguage() {
        if (this.mNewSpec.isLiveStream()) {
            return (String) MoreObjects.firstNonNull(this.mNewSpec.getAudioLanguage(), EnvironmentCompat.MEDIA_UNKNOWN);
        }
        return (String) MoreObjects.firstNonNull(this.mCause.getLanguageCode(), EnvironmentCompat.MEDIA_UNKNOWN);
    }

    @Nonnull
    public PlaybackRestartEvent getCause() {
        return this.mCause;
    }

    @Nonnull
    public final String getAudioAdaptationSetName() {
        return this.mAudioAdaptationSetName;
    }

    public final int getAudioAdaptationSetBitrate() {
        return this.mAudioAdaptationSetBitrate;
    }

    @Nonnull
    public String getStreamSelectionParams() {
        return this.mStreamSelectionParams;
    }
}
