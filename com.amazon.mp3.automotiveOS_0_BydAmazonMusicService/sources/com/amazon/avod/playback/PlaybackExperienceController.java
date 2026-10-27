package com.amazon.avod.playback;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackExperienceController {
    default void changeOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
    }

    void deregisterSubtitleListener(@Nonnull SubtitlesListener subtitlesListener);

    @Nullable
    PlaybackMediaEventReporters getAloysiusReporter();

    @Nonnull
    Optional<String> getAudioTrackId();

    @Nonnull
    ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() throws IllegalPlayerStateException;

    @Nonnull
    Set<String> getAvailableAudioLanguages();

    @Nonnull
    Set<String> getAvailableSubtitleLanguageCodes();

    @Nonnull
    Optional<String> getCurrentAudioLanguage();

    long getLiveTimeWindowEndMillis();

    long getLiveTimeWindowStartMillis();

    @Nonnull
    VideoConfig getVideoConfig() throws IllegalPlayerStateException;

    @Nonnull
    PlaybackZoomLevel getZoomLevel();

    default boolean isPlaybackSpeedAdjustmentSupported() {
        return false;
    }

    boolean isStreamingSubtitlesSupported();

    default boolean isVideoTrackRecreationSupported() {
        return false;
    }

    void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode);

    void onSubtitlesCallbackComplete();

    default void overrideVideoQuality(@Nullable QualityLevel qualityLevel) {
    }

    void registerSubtitleListener(@Nonnull SubtitlesListener subtitlesListener);

    void scaleVolume(float f);

    void setFailoverZigZagSpeed(int i);

    default void setPlaybackSpeed(float f) {
    }

    void setRestrictPlaybackToBufferedContent(boolean z);

    default void setTimelineManager(@Nonnull TimelineManager timelineManager) {
    }

    void setVideoPlayerInBackground(boolean z);

    void setZoomLevel(@Nonnull PlaybackZoomLevel playbackZoomLevel);

    void startSubtitleDownload(String str);

    void stopSubtitleDownload();
}
