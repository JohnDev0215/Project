package com.amazon.avod.playback;

import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackRestartEvent extends PlaybackEvent {
    private static final ImmutableSet<String> RESTART_TYPES_WHITELIST = ImmutableSet.of("AudioTrackChange", "LiveRestartOnOriginFailover", "LiveKeyRotation", "AudioAdaptationSetChange-Seek", "AudioAdaptationSetChange-Buffer", "RendererDecoderStalledRestart", "PlayerCorruptFragmentRestart", "AudioFormatChange", "PlaybackAudioTrackDiedRestart", "LiveManifestDownloadFailedRestart", "PlayerPerfEvaluatorSFRFallback", "PlayerPerfEvaluatorHFRDynamicCapping", "AudioTrackAndFormatChange");
    private final AudioVideoUrls mAudioVideoUrls;
    private final String mContentSessionUUID;
    private final String mContentUrlSetId;
    private final String mLanguageCode;
    private final String mMessage;
    private final boolean mOverridePreviousRestart;
    private final long mRestartTimeMillis;
    private final long mTimeBeforeRestartMillis;
    private final int mType;

    public static PlaybackRestartEvent newLiveOriginFailoverEvent(@Nullable String str, @Nullable AudioVideoUrls audioVideoUrls, long j, boolean z, @Nullable String str2) {
        return new PlaybackRestartEvent(1, -1L, j, null, str, audioVideoUrls, z, str2, null);
    }

    public static PlaybackRestartEvent newLiveKeyRotationEvent(long j) {
        Preconditions.checkArgument(j >= 0, "restartTimeMillis must be non-negative.");
        return new PlaybackRestartEvent(3, -1L, j, null, null, null, false, null, null);
    }

    public static PlaybackRestartEvent newRendererDecoderStalledRestartEvent(long j) {
        return new PlaybackRestartEvent(6, j, -1L, null, null, null, false, null, null);
    }

    public static PlaybackRestartEvent newPlayerCorruptedFragmentRestartEvent(String str) {
        return new PlaybackRestartEvent(7, -1L, -1L, null, null, null, false, null, str);
    }

    public static PlaybackRestartEvent newAudioAdaptationSetSeekRestartEvent(long j) {
        return new PlaybackRestartEvent(5, j, -1L, null, null, null, false, null, null);
    }

    public static PlaybackRestartEvent newAudioAdaptationSetBufferRestartEvent() {
        return new PlaybackRestartEvent(4, -1L, -1L, null, null, null, false, null, null);
    }

    public static PlaybackRestartEvent newAudioTrackDiedRestartPlayerEvent(long j, String str) {
        return new PlaybackRestartEvent(9, j, j, null, null, null, false, null, str);
    }

    public static PlaybackRestartEvent newManifestDownloadFailedRestartPlayerEvent(String str) {
        return new PlaybackRestartEvent(10, -1L, -1L, null, null, null, false, null, str);
    }

    public static PlaybackRestartEvent newStandardFrameRateFallbackRestartEvent(String str) {
        return new PlaybackRestartEvent(12, -1L, -1L, null, null, null, false, null, str);
    }

    public static PlaybackRestartEvent newHighFrameRateDynamicCappingRestartEvent(String str) {
        return new PlaybackRestartEvent(13, -1L, -1L, null, null, null, false, null, str);
    }

    @Nonnull
    public static PlaybackRestartEvent newAutoEvalCanaryFailedRestartEvent(long j, @Nonnull String str) {
        return new PlaybackRestartEvent(14, j, j, null, null, null, false, null, str);
    }

    @Nonnull
    public static PlaybackRestartEvent newAutoEvalPoorPlayerPerformanceRestartEvent(long j, @Nonnull String str) {
        return new PlaybackRestartEvent(15, j, j, null, null, null, false, null, str);
    }

    private PlaybackRestartEvent(int i, long j, long j2, @Nullable String str, @Nullable String str2, @Nullable AudioVideoUrls audioVideoUrls, boolean z, @Nullable String str3, @Nullable String str4) {
        super(TimeSpan.fromMilliseconds(0L));
        this.mType = i;
        this.mTimeBeforeRestartMillis = j;
        this.mRestartTimeMillis = j2;
        this.mLanguageCode = str;
        this.mContentUrlSetId = str2;
        this.mAudioVideoUrls = audioVideoUrls;
        this.mOverridePreviousRestart = z;
        this.mContentSessionUUID = str3;
        this.mMessage = str4;
    }

    public int getType() {
        return this.mType;
    }

    public String getTypeString() {
        switch (this.mType) {
            case 0:
                return "AudioTrackChange";
            case 1:
                return "LiveRestartOnOriginFailover";
            case 2:
            default:
                throw new IllegalStateException("PlaybackRestartEvent mType must be one of the defined types!");
            case 3:
                return "LiveKeyRotation";
            case 4:
                return "AudioAdaptationSetChange-Buffer";
            case 5:
                return "AudioAdaptationSetChange-Seek";
            case 6:
                return "RendererDecoderStalledRestart";
            case 7:
                return "PlayerCorruptFragmentRestart";
            case 8:
                return "AudioFormatChange";
            case 9:
                return "PlaybackAudioTrackDiedRestart";
            case 10:
                return "LiveManifestDownloadFailedRestart";
            case 11:
                return "AudioTrackAndFormatChange";
            case 12:
                return "PlayerPerfEvaluatorSFRFallback";
            case 13:
                return "PlayerPerfEvaluatorHFRDynamicCapping";
            case 14:
                return "PlayerAutoEvalCanaryFailed";
            case 15:
                return "PlayerAutoEvalPoorPlayerPerformance";
        }
    }

    @Nonnull
    public ReportableString toReportableString() {
        try {
            return new ReportableString(getTypeString(), RESTART_TYPES_WHITELIST, "Unknown");
        } catch (Exception unused) {
            return new ReportableString("", RESTART_TYPES_WHITELIST, "Unknown");
        }
    }

    public long getRestartTimeMillis() {
        return this.mRestartTimeMillis;
    }

    public long getTimeBeforeRestartMillis() {
        return this.mTimeBeforeRestartMillis;
    }

    @Nullable
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nullable
    public String getContentUrlSetId() {
        return this.mContentUrlSetId;
    }

    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    public boolean shouldOverridePreviousRestart() {
        return this.mOverridePreviousRestart;
    }

    @Nullable
    public String getContentSessionUUID() {
        return this.mContentSessionUUID;
    }

    @Nullable
    public String getMessage() {
        return this.mMessage;
    }
}
