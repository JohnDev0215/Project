package com.amazon.video.sdk.avod.playbackclient.utils;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.service.cache.PlayerResourcesCache;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheRequest;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playbackclient.live.LiveLanguageTransformer;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitlesLanguageHelper {
    private ConsumptionType mConsumptionType;
    private boolean mInitialized;
    private final LiveLanguageTransformer mLiveLanguageTransformer;
    private PlaybackExperienceController mPlaybackExperienceController;
    private VideoOptions mVideoOptions;
    private VideoSpecification mVideoSpec;

    public SubtitlesLanguageHelper() {
        this(new LiveLanguageTransformer());
    }

    SubtitlesLanguageHelper(@Nonnull LiveLanguageTransformer liveLanguageTransformer) {
        this.mLiveLanguageTransformer = (LiveLanguageTransformer) Preconditions.checkNotNull(liveLanguageTransformer, "liveLanguageTransformer");
    }

    public void initialize(@Nonnull VideoSpecification videoSpecification, @Nonnull ConsumptionType consumptionType, @Nonnull VideoOptions videoOptions, @Nonnull PlaybackExperienceController playbackExperienceController) {
        Preconditions.checkState(!this.mInitialized, "Already initialized");
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpec");
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions");
        this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "PlaybackExperienceController");
        this.mInitialized = true;
    }

    public void reset() {
        this.mInitialized = false;
        this.mVideoSpec = null;
        this.mVideoOptions = null;
        this.mPlaybackExperienceController = null;
        this.mConsumptionType = null;
    }

    @Nonnull
    public ImmutableSet<SubtitleLanguage> transformToSubtitleLanguage(@Nullable List<Subtitle> list) {
        if (list == null) {
            return ImmutableSet.of();
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        Iterator<Subtitle> it = list.iterator();
        while (it.hasNext()) {
            builder.add(new SubtitleLanguage(it.next()));
        }
        return builder.build();
    }

    @Nonnull
    public Optional<ImmutableList<Subtitle>> getAvailableSubtitles() {
        if (!this.mInitialized) {
            DLog.warnf("SubtitlesLanguageHelper has not yet been initialized. Returning no available subtitles");
            return Optional.absent();
        }
        boolean zIsStreamingSubtitlesSupported = isStreamingSubtitlesSupported(this.mPlaybackExperienceController);
        if (this.mVideoSpec.isLiveStream() || zIsStreamingSubtitlesSupported) {
            return Optional.of(this.mLiveLanguageTransformer.transformToSubtitles(this.mPlaybackExperienceController.getAvailableSubtitleLanguageCodes()));
        }
        PlaybackResources playbackResources = getPlaybackResources();
        return playbackResources != null ? Optional.of(playbackResources.getSubtitles()) : Optional.absent();
    }

    public static boolean isStreamingSubtitlesSupported(@Nonnull PlaybackExperienceController playbackExperienceController) {
        Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
        try {
            return playbackExperienceController.isStreamingSubtitlesSupported();
        } catch (IllegalStateException unused) {
            DLog.warnf("swallowing ISE calling isStreamingSubtitlesSupported before main player is active");
            return false;
        }
    }

    @Nullable
    private PlaybackResources getPlaybackResources() {
        return PlayerResourcesCache.getInstance().getResourcesIfPresent(new PlayerResourcesCacheRequest(this.mVideoSpec, this.mConsumptionType, this.mVideoOptions.getSessionContext())).orNull();
    }
}
