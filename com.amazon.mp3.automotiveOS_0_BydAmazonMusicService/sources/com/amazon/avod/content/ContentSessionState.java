package com.amazon.avod.content;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.LiveDynamicFrontMargin;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentSessionState {
    private final boolean mIsUtcTranslationEnabled;
    private final LiveDynamicFrontMargin mLiveDynamicFrontMargin;
    private final long mLiveFrontMarginForRefreshNanos;
    private final int mLiveStreamingFutureBufferSeconds;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    private final LiveWindowDuration mLiveWindowDuration;
    private final Manifest mManifest;
    private volatile PlayerBindState mPlayerBindState;
    private QualityLevel mQualityLevel;
    private final Stopwatch mTimeWindowStopwatch;
    private final VideoSpecification mVideoSpecification;
    private final AtomicReference<ProtectionHeader> mProtectionHeader = new AtomicReference<>(null);
    private final KeyRotationState mKeyRotationState = new KeyRotationState();
    private MediaQuality mMediaQuality = MediaQuality.HIGHEST;
    private VideoResolution.ResolutionBand mMaxResolution = VideoResolution.ResolutionBand.UNKNOWN;
    private long mPlayPositionInNanoseconds = 0;
    private boolean mIsPlaybackRestrictedToBufferedContent = false;
    private boolean mIsWANStreamingEnabled = false;
    private QualityLevel mSustainableQualityLevel = null;
    private final AtomicInteger mConcurrentFragmentRepairCount = new AtomicInteger();
    private final Map<Integer, Integer> mConsumptionHeads = Maps.newConcurrentMap();

    public ContentSessionState(@Nonnull VideoSpecification videoSpecification, @Nonnull Manifest manifest, @Nonnull Stopwatch stopwatch, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, int i, @Nullable LiveWindowDuration liveWindowDuration, @Nonnull PlayerBindState playerBindState) {
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mManifest = (Manifest) Preconditions.checkNotNull(manifest, "manifest");
        this.mTimeWindowStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, "timeWindowStopWatch");
        LiveStreamingPlaybackConfig liveStreamingPlaybackConfig2 = (LiveStreamingPlaybackConfig) Preconditions.checkNotNull(liveStreamingPlaybackConfig, "liveStreamingPlaybackConfig");
        this.mLiveStreamingPlaybackConfig = liveStreamingPlaybackConfig2;
        this.mLiveStreamingFutureBufferSeconds = i;
        this.mLiveWindowDuration = liveWindowDuration;
        this.mLiveDynamicFrontMargin = new LiveDynamicFrontMargin(liveStreamingPlaybackConfig2.getMaxDynamicFrontMargin().getTotalNanoSeconds(), liveStreamingPlaybackConfig2.getDynamicFrontMarginStep().getTotalNanoSeconds());
        this.mLiveFrontMarginForRefreshNanos = TimeUnit.MILLISECONDS.toNanos(liveStreamingPlaybackConfig2.getLiveFrontMarginForRefreshDurationMillis());
        this.mPlayerBindState = (PlayerBindState) Preconditions.checkNotNull(playerBindState, "playerBindState");
        this.mIsUtcTranslationEnabled = liveStreamingPlaybackConfig2.isUtcTranslationEnabled();
    }

    @Nonnull
    public MediaQuality getMediaQuality() {
        return this.mMediaQuality;
    }

    public void setMediaQuality(@Nonnull MediaQuality mediaQuality) {
        this.mMediaQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "mediaQuality");
    }

    public void setVideoQualityOverride(@Nullable QualityLevel qualityLevel) {
        this.mQualityLevel = qualityLevel;
    }

    @Nonnull
    public VideoResolution.ResolutionBand getMaxResolution() {
        return this.mMaxResolution;
    }

    public boolean isPlaybackRestrictedToBufferedContent() {
        return this.mIsPlaybackRestrictedToBufferedContent;
    }

    public void setRestrictPlaybackToBufferedContent(boolean z) {
        this.mIsPlaybackRestrictedToBufferedContent = z;
    }

    public boolean isWANStreamingEnabled() {
        return this.mIsWANStreamingEnabled;
    }

    @Nonnull
    public long getPlayPositionInNanos() {
        return this.mPlayPositionInNanoseconds;
    }

    public void setPlayPositionInNanos(long j) {
        this.mPlayPositionInNanoseconds = j;
    }

    @Nullable
    public QualityLevel getSustainableQualityLevel() {
        return this.mSustainableQualityLevel;
    }

    public void setSustainableQualityLevel(@Nullable QualityLevel qualityLevel) {
        this.mSustainableQualityLevel = qualityLevel;
    }

    public boolean isBoundToPlayer() {
        return this.mPlayerBindState != PlayerBindState.UNATTACHED;
    }

    public boolean shouldUseQualityCap() {
        return this.mPlayerBindState != PlayerBindState.ATTACHED_LOADED;
    }

    public void notifyPlayerBindStateChanged(@Nonnull PlayerBindState playerBindState) {
        this.mPlayerBindState = (PlayerBindState) Preconditions.checkNotNull(playerBindState, "playerBindState");
    }

    @Nullable
    public ProtectionHeader getVideoProtectionHeader() {
        return this.mProtectionHeader.get();
    }

    public void setProtectionHeaderIfUnset(@Nullable ProtectionHeader protectionHeader, @Nullable ProtectionHeader protectionHeader2) throws IOException {
        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mProtectionHeader, null, protectionHeader)) {
            this.mKeyRotationState.initialize(protectionHeader, protectionHeader2);
        }
    }

    public AtomicInteger getConcurrentFragmentRepairCount() {
        return this.mConcurrentFragmentRepairCount;
    }

    @Nonnull
    public KeyRotationState getKeyRotationState() {
        return this.mKeyRotationState;
    }

    public long getEpochUTCTimeWindowStartMillis() throws ContentException {
        Preconditions.checkState(this.mVideoSpecification.isLiveStream(), "May not call getEpochUTCTimeWindowStartMillis on non-live stream");
        return getManifestAvailabilityStartTimeMillis() + TimeUnit.NANOSECONDS.toMillis(getMediaTimeWindowStartNanos());
    }

    public long getMediaTimeWindowStartNanos() {
        if (!this.mVideoSpecification.isLiveStream() || !this.mManifest.isDynamic()) {
            return this.mManifest.getManifestStartTime().getTotalNanoSeconds();
        }
        Preconditions.checkState(this.mLiveWindowDuration != null, "Live streaming content session should have a live window controller");
        long mediaTimeWindowStartNanosBasedOnManifest = getMediaTimeWindowStartNanosBasedOnManifest();
        long mediaTimeWindowEndNanos = getMediaTimeWindowEndNanos();
        long jMax = Math.max(mediaTimeWindowStartNanosBasedOnManifest, Math.min(mediaTimeWindowEndNanos - TimeUnit.MILLISECONDS.toNanos(this.mLiveWindowDuration.getRestrictedDurationMillis()), mediaTimeWindowEndNanos - TimeUnit.SECONDS.toNanos(this.mLiveStreamingPlaybackConfig.getLiveStreamingMinTimeWindowDurationSeconds())));
        DLog.devf("mediaTimeWindowStartNanosBasedOnManifest start: %d  end : %d  computedstart: %d", Long.valueOf(mediaTimeWindowStartNanosBasedOnManifest), Long.valueOf(mediaTimeWindowEndNanos), Long.valueOf(jMax));
        return jMax;
    }

    private long getMediaTimeWindowStartNanosBasedOnManifest() {
        long totalNanoSeconds = this.mManifest.getManifestStartTime().getTotalNanoSeconds();
        if (!this.mManifest.isDynamic()) {
            return totalNanoSeconds;
        }
        long totalNanoSeconds2 = this.mManifest.getManifestEndTime().getTotalNanoSeconds();
        long totalNanoSeconds3 = this.mManifest.getTimeshiftBufferDuration().getTotalNanoSeconds();
        long jElapsed = this.mTimeWindowStopwatch.elapsed(TimeUnit.NANOSECONDS);
        long jMax = Math.max(totalNanoSeconds2 - totalNanoSeconds3, totalNanoSeconds);
        long j = this.mLiveFrontMarginForRefreshNanos;
        long j2 = jMax + j < totalNanoSeconds2 ? jMax + jElapsed + j : jMax + jElapsed;
        DLog.devf("manifestEndNanos: " + totalNanoSeconds2 + " timeShiftBufferNanos: " + totalNanoSeconds3 + " elapsedNanos: " + jElapsed + " manifestStartTimeNanos: " + totalNanoSeconds + " max(manifestend-timeShiftBuffer,manifeststart): " + jMax + " computedStartNanos: " + j2);
        return j2;
    }

    public long getEpochUTCTimeWindowEndMillis() throws ContentException {
        Preconditions.checkState(this.mVideoSpecification.isLiveStream(), "May not call getEpochUTCTimeWindowEndMillis on non-live stream");
        return getManifestAvailabilityStartTimeMillis() + TimeUnit.NANOSECONDS.toMillis(getMediaTimeWindowEndNanos());
    }

    public long getLiveStreamingFutureBufferSeconds() {
        return this.mLiveStreamingFutureBufferSeconds;
    }

    public long getMediaTimeWindowEndNanos() {
        if (!this.mVideoSpecification.isLiveStream()) {
            return this.mManifest.getManifestEndTime().getTotalNanoSeconds();
        }
        long totalNanoSeconds = this.mManifest.getManifestEndTime().getTotalNanoSeconds();
        if (this.mManifest.isDynamic()) {
            long totalNanoSeconds2 = this.mManifest.getSuggestedFrontMargin().getTotalNanoSeconds();
            return Math.max(0L, Math.min(totalNanoSeconds - totalNanoSeconds2, totalNanoSeconds + ((this.mTimeWindowStopwatch.elapsed(TimeUnit.NANOSECONDS) - totalNanoSeconds2) - TimeUnit.SECONDS.toNanos(this.mLiveStreamingFutureBufferSeconds))));
        }
        return Math.max(0L, totalNanoSeconds - 1);
    }

    public long resolveToMediaTimeWindowBoundary(long j, boolean z) throws ContentException {
        if (this.mIsUtcTranslationEnabled && z) {
            j -= TimeUnit.MILLISECONDS.toNanos(getManifestAvailabilityStartTimeMillis());
        }
        long mediaTimeWindowStartNanos = getMediaTimeWindowStartNanos();
        if (j < mediaTimeWindowStartNanos) {
            DLog.logf("Resolving %d to beginning of time window: %d", Long.valueOf(j), Long.valueOf(mediaTimeWindowStartNanos));
            return mediaTimeWindowStartNanos;
        }
        long mediaTimeWindowEndNanos = getMediaTimeWindowEndNanos();
        if (j <= mediaTimeWindowEndNanos) {
            return j;
        }
        DLog.logf("Resolving %d to live: %d", Long.valueOf(j), Long.valueOf(mediaTimeWindowEndNanos));
        return mediaTimeWindowEndNanos;
    }

    public long getManifestAvailabilityStartTimeMillis() throws ContentException {
        long availabilityStartTimeMillis = this.mManifest.getAvailabilityStartTimeMillis();
        if (availabilityStartTimeMillis != -1) {
            return availabilityStartTimeMillis;
        }
        throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, "Manifest does not have an availability start time");
    }

    public int getConsumptionHead(int i) {
        if (this.mConsumptionHeads.containsKey(Integer.valueOf(i))) {
            return this.mConsumptionHeads.get(Integer.valueOf(i)).intValue();
        }
        return 0;
    }

    public void updateConsumptionHead(int i, int i2) {
        this.mConsumptionHeads.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public LiveDynamicFrontMargin getLiveDynamicFrontMargin() {
        return this.mLiveDynamicFrontMargin;
    }

    @Nonnull
    public VideoSpecification getVideoSpecification() {
        return this.mVideoSpecification;
    }
}
