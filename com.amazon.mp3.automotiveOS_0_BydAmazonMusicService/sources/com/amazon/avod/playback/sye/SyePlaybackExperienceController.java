package com.amazon.avod.playback.sye;

import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.content.urlvending.TimeShiftPolicy;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.config.PlaybackDebugOverride;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.playback.sye.listeners.AudioTracksHolder;
import com.amazon.avod.playback.sye.listeners.SubtitleTracksHolder;
import com.amazon.avod.playback.sye.listeners.SyeTimelineHolder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.SyeFrontend;
import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.video.SyeVideoStreamInfo;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SyePlaybackExperienceController implements PlaybackExperienceController {
    private static final VideoRegion DEFAULT_VIDEO_REGION = new VideoRegion(0, 0, 0, 0);
    private final AloysiusReporterHolder mAloysiusReporterHolder;
    private final AudioTracksHolder mAudioTracksHolder;
    private final MediaCodecDeviceCapabilityDetector mMediaCodecDeviceCapabilityDetector;
    private final PlaybackDebugOverride mOverride;
    private final ISyePlayer mPlayer;
    private final SubtitleTracksHolder mSubtitleTracksHolder;
    private final SyeConfig mSyeConfig;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;
    private final TimeShiftPolicy mTimeShiftPolicy;
    private final SyeTimelineHolder mTimelineHolder;
    private VideoRegion mVideoRegion;
    private final SyeVideoResolutionCap mVideoResolutionCap;
    private final SyeViewHolder mViewHolder;

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void deregisterSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void onSubtitlesCallbackComplete() {
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void registerSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setVideoPlayerInBackground(boolean z) {
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setZoomLevel(@Nonnull PlaybackZoomLevel playbackZoomLevel) {
    }

    SyePlaybackExperienceController(@Nonnull ISyePlayer iSyePlayer, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull AudioTracksHolder audioTracksHolder, @Nonnull SubtitleTracksHolder subtitleTracksHolder, @Nonnull SyeViewHolder syeViewHolder, @Nonnull SyeTimelineHolder syeTimelineHolder, @Nonnull TimeShiftPolicy timeShiftPolicy, @Nonnull SyeConfig syeConfig) {
        this(iSyePlayer, syeDomainVendingMachine, aloysiusReporterHolder, mediaCodecDeviceCapabilityDetector, audioTracksHolder, subtitleTracksHolder, syeViewHolder, syeTimelineHolder, timeShiftPolicy, syeConfig, PlaybackDebugOverride.getInstance(), new SyeVideoResolutionCap(syeConfig));
    }

    SyePlaybackExperienceController(@Nonnull ISyePlayer iSyePlayer, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull AudioTracksHolder audioTracksHolder, @Nonnull SubtitleTracksHolder subtitleTracksHolder, @Nonnull SyeViewHolder syeViewHolder, @Nonnull SyeTimelineHolder syeTimelineHolder, @Nonnull TimeShiftPolicy timeShiftPolicy, @Nonnull SyeConfig syeConfig, @Nonnull PlaybackDebugOverride playbackDebugOverride, @Nonnull SyeVideoResolutionCap syeVideoResolutionCap) {
        this.mVideoRegion = DEFAULT_VIDEO_REGION;
        this.mPlayer = (ISyePlayer) Preconditions.checkNotNull(iSyePlayer, "player");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
        this.mAloysiusReporterHolder = (AloysiusReporterHolder) Preconditions.checkNotNull(aloysiusReporterHolder, "aloysiusReporterHolder");
        this.mAudioTracksHolder = (AudioTracksHolder) Preconditions.checkNotNull(audioTracksHolder, "audioTracksHolder");
        this.mViewHolder = (SyeViewHolder) Preconditions.checkNotNull(syeViewHolder, "viewHolder");
        this.mSubtitleTracksHolder = (SubtitleTracksHolder) Preconditions.checkNotNull(subtitleTracksHolder, "subtitleTracksHolder");
        this.mTimelineHolder = (SyeTimelineHolder) Preconditions.checkNotNull(syeTimelineHolder, "timelineHolder");
        this.mTimeShiftPolicy = (TimeShiftPolicy) Preconditions.checkNotNull(timeShiftPolicy, "timeShiftPolicy");
        this.mSyeConfig = (SyeConfig) Preconditions.checkNotNull(syeConfig, "syeConfig");
        this.mOverride = (PlaybackDebugOverride) Preconditions.checkNotNull(playbackDebugOverride, "override");
        this.mMediaCodecDeviceCapabilityDetector = (MediaCodecDeviceCapabilityDetector) Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "mediaCodecDeviceCapabilityDetector");
        this.mVideoResolutionCap = (SyeVideoResolutionCap) Preconditions.checkNotNull(syeVideoResolutionCap, "videoResolutionCap");
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode) {
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$content$urlvending$FailoverType[failoverType.ordinal()] == 1) {
            SyeDomainHolder syeDomainHolderNextSyeDomain = this.mSyeDomainVendingMachine.nextSyeDomain();
            if (syeDomainHolderNextSyeDomain == null) {
                DLog.warnf("Manually triggered failover but no SyeDomains are available...");
                return;
            }
            this.mPlayer.updateSyeSystem(new SyeSystem(new SyeFrontend(syeDomainHolderNextSyeDomain.getSyeDomain().getBaseUrl(), syeDomainHolderNextSyeDomain.getToken())));
            if (this.mPlayer.getPlayerState() == SyePlayerState.Playing) {
                this.mPlayer.playResume();
            }
            DLog.logf("Switched to sye cdn %s", syeDomainHolderNextSyeDomain.getSyeDomain().getName());
            return;
        }
        throw new UnsupportedOperationException("Currently only cdn switching is supported");
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Optional<String> getAudioTrackId() {
        return getLanguage();
    }

    @Nonnull
    public Optional<String> getLanguage() {
        return Optional.fromNullable(this.mAudioTracksHolder.getCurrentAudioLanguage());
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setRestrictPlaybackToBufferedContent(boolean z) {
        if (z) {
            this.mPlayer.stop();
        } else {
            if (this.mPlayer.getPlayerState() == SyePlayerState.Playing || this.mPlayer.getPlayerState() == SyePlayerState.Loading) {
                return;
            }
            this.mPlayer.playResume();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public PlaybackZoomLevel getZoomLevel() {
        return PlaybackZoomLevel.NATIVE;
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setPlaybackSpeed(float f) {
        DLog.warnf("setPlaybackSpeed unimplemented for SyePlaybackExperience");
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void scaleVolume(float f) {
        this.mPlayer.setAudioVolume(f);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public boolean isStreamingSubtitlesSupported() {
        return !getAvailableSubtitleLanguageCodes().isEmpty();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Set<String> getAvailableSubtitleLanguageCodes() {
        String currentAudioLanguage = this.mAudioTracksHolder.getCurrentAudioLanguage();
        if (currentAudioLanguage == null) {
            return Collections.emptySet();
        }
        return Sets.newHashSet(currentAudioLanguage);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void startSubtitleDownload(String str) {
        this.mPlayer.setClosedCaptionsEnabled(true);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void stopSubtitleDownload() {
        this.mPlayer.setClosedCaptionsEnabled(false);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Set<String> getAvailableAudioLanguages() {
        return this.mAudioTracksHolder.getAudioLanguages();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public Optional<String> getCurrentAudioLanguage() {
        return Optional.fromNullable(this.mAudioTracksHolder.getCurrentAudioLanguage());
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public long getLiveTimeWindowStartMillis() {
        return this.mTimelineHolder.getLivePosUtcMillis() - ((long) this.mSyeDomainVendingMachine.currentSyeDomain().getDvrDurationMillis());
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public long getLiveTimeWindowEndMillis() {
        return Math.max(this.mTimelineHolder.getLivePosUtcMillis(), 1L);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
        return ImmutableList.of();
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.sye.SyePlaybackExperienceController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$urlvending$FailoverType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$AudioFormat;

        static {
            int[] iArr = new int[AudioFormat.values().length];
            $SwitchMap$com$amazon$avod$media$AudioFormat = iArr;
            try {
                iArr[AudioFormat.AC_3_5_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$AudioFormat[AudioFormat.AC_3_7_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[FailoverType.values().length];
            $SwitchMap$com$amazon$avod$content$urlvending$FailoverType = iArr2;
            try {
                iArr2[FailoverType.CDN.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nullable
    public PlaybackMediaEventReporters getAloysiusReporter() {
        return this.mAloysiusReporterHolder.getMediaEventReporters();
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    @Nonnull
    public VideoConfig getVideoConfig() {
        SyeVideoStreamInfo videoStreamInfo = this.mPlayer.getVideoStreamInfo();
        if (videoStreamInfo != null && videoStreamInfo.getHeight() != 0) {
            int height = videoStreamInfo.getHeight();
            int width = videoStreamInfo.getWidth();
            double d = width / height;
            VideoResolution videoResolution = new VideoResolution(width, height, d);
            return new VideoConfig("h264", videoResolution, videoResolution, null, false, false, d);
        }
        DLog.warnf("Calling getVideoConfig() before stream starts, returning default config.");
        int maxHFRDisplayHeight = this.mSyeConfig.getMaxHFRDisplayHeight();
        int maxHFRDisplayHeight2 = this.mSyeConfig.getMaxHFRDisplayHeight();
        double d2 = maxHFRDisplayHeight / maxHFRDisplayHeight2;
        VideoResolution videoResolution2 = new VideoResolution(maxHFRDisplayHeight, maxHFRDisplayHeight2, d2);
        return new VideoConfig("h264", videoResolution2, videoResolution2, null, false, false, d2);
    }

    @Override // com.amazon.avod.playback.PlaybackExperienceController
    public void setFailoverZigZagSpeed(int i) {
        throw new UnsupportedOperationException();
    }
}
