package PlaybackInterface.v1_0;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "MediaStateClientInformation", generator = "Immutables")
public final class ImmutableMediaStateClientInformation extends MediaStateClientInformation {

    @Nullable
    private final String bitrate;

    @Nullable
    private final String cacheHitStatus;

    @Nullable
    private final String cdnCacheHitStatus;

    @Nullable
    private final String cdnType;

    @Nullable
    private final Boolean isNPVShowing;
    private final String mediaPlayerName;

    @Nullable
    private final Long playbackInitiationDelayMilliSeconds;

    @Nullable
    private final Long playbackInterruptionMilliSeconds;

    @Nullable
    private final Long playbackRate;

    @Nullable
    private final Long playbackResumedAtTimeStampMilliSeconds;

    @Nullable
    private final Long playbackStartedAtTimeStampMilliSeconds;

    @Nullable
    private final PlaybackState playbackState;

    @Nullable
    private final PlaybackState previousPlaybackState;

    @Nullable
    private final Long progressInMilliSeconds;

    @Nullable
    private final Integer rebufferCount;

    @Nullable
    private final Long rebufferDurationMilliSeconds;

    @Nullable
    private final String streamFormat;

    @Nullable
    private final String substituteMediaId;

    @Nullable
    private final Long terminationDurationSeconds;

    @Nullable
    private final Long transferBytesPerSecond;

    private ImmutableMediaStateClientInformation(String str, @Nullable PlaybackState playbackState, @Nullable PlaybackState playbackState2, @Nullable Long l, @Nullable String str2, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable String str3, @Nullable String str4, @Nullable Long l5, @Nullable String str5, @Nullable String str6, @Nullable Integer num, @Nullable Long l6, @Nullable Long l7, @Nullable String str7, @Nullable Long l8, @Nullable Boolean bool, @Nullable Long l9) {
        this.mediaPlayerName = str;
        this.playbackState = playbackState;
        this.previousPlaybackState = playbackState2;
        this.progressInMilliSeconds = l;
        this.streamFormat = str2;
        this.playbackStartedAtTimeStampMilliSeconds = l2;
        this.playbackResumedAtTimeStampMilliSeconds = l3;
        this.playbackInitiationDelayMilliSeconds = l4;
        this.bitrate = str3;
        this.cacheHitStatus = str4;
        this.transferBytesPerSecond = l5;
        this.cdnType = str5;
        this.cdnCacheHitStatus = str6;
        this.rebufferCount = num;
        this.rebufferDurationMilliSeconds = l6;
        this.playbackInterruptionMilliSeconds = l7;
        this.substituteMediaId = str7;
        this.terminationDurationSeconds = l8;
        this.isNPVShowing = bool;
        this.playbackRate = l9;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("mediaPlayerName")
    public String mediaPlayerName() {
        return this.mediaPlayerName;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty(TetheredMessageKey.playbackState)
    @Nullable
    public PlaybackState playbackState() {
        return this.playbackState;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("previousPlaybackState")
    @Nullable
    public PlaybackState previousPlaybackState() {
        return this.previousPlaybackState;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("progressInMilliSeconds")
    @Nullable
    public Long progressInMilliSeconds() {
        return this.progressInMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("streamFormat")
    @Nullable
    public String streamFormat() {
        return this.streamFormat;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("playbackStartedAtTimeStampMilliSeconds")
    @Nullable
    public Long playbackStartedAtTimeStampMilliSeconds() {
        return this.playbackStartedAtTimeStampMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("playbackResumedAtTimeStampMilliSeconds")
    @Nullable
    public Long playbackResumedAtTimeStampMilliSeconds() {
        return this.playbackResumedAtTimeStampMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("playbackInitiationDelayMilliSeconds")
    @Nullable
    public Long playbackInitiationDelayMilliSeconds() {
        return this.playbackInitiationDelayMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("bitrate")
    @Nullable
    public String bitrate() {
        return this.bitrate;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("cacheHitStatus")
    @Nullable
    public String cacheHitStatus() {
        return this.cacheHitStatus;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("transferBytesPerSecond")
    @Nullable
    public Long transferBytesPerSecond() {
        return this.transferBytesPerSecond;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("cdnType")
    @Nullable
    public String cdnType() {
        return this.cdnType;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("cdnCacheHitStatus")
    @Nullable
    public String cdnCacheHitStatus() {
        return this.cdnCacheHitStatus;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("rebufferCount")
    @Nullable
    public Integer rebufferCount() {
        return this.rebufferCount;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("rebufferDurationMilliSeconds")
    @Nullable
    public Long rebufferDurationMilliSeconds() {
        return this.rebufferDurationMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("playbackInterruptionMilliSeconds")
    @Nullable
    public Long playbackInterruptionMilliSeconds() {
        return this.playbackInterruptionMilliSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("substituteMediaId")
    @Nullable
    public String substituteMediaId() {
        return this.substituteMediaId;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("terminationDurationSeconds")
    @Nullable
    public Long terminationDurationSeconds() {
        return this.terminationDurationSeconds;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("isNPVShowing")
    @Nullable
    public Boolean isNPVShowing() {
        return this.isNPVShowing;
    }

    @Override // PlaybackInterface.v1_0.MediaStateClientInformation
    @JsonProperty("playbackRate")
    @Nullable
    public Long playbackRate() {
        return this.playbackRate;
    }

    public final ImmutableMediaStateClientInformation withMediaPlayerName(String str) {
        String str2 = (String) requireNonNull(str, "mediaPlayerName");
        return this.mediaPlayerName.equals(str2) ? this : new ImmutableMediaStateClientInformation(str2, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackState(@Nullable PlaybackState playbackState) {
        return this.playbackState == playbackState ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPreviousPlaybackState(@Nullable PlaybackState playbackState) {
        return this.previousPlaybackState == playbackState ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, playbackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withProgressInMilliSeconds(@Nullable Long l) {
        return equals(this.progressInMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, l, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withStreamFormat(@Nullable String str) {
        return equals(this.streamFormat, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, str, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackStartedAtTimeStampMilliSeconds(@Nullable Long l) {
        return equals(this.playbackStartedAtTimeStampMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, l, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackResumedAtTimeStampMilliSeconds(@Nullable Long l) {
        return equals(this.playbackResumedAtTimeStampMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, l, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackInitiationDelayMilliSeconds(@Nullable Long l) {
        return equals(this.playbackInitiationDelayMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, l, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withBitrate(@Nullable String str) {
        return equals(this.bitrate, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, str, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withCacheHitStatus(@Nullable String str) {
        return equals(this.cacheHitStatus, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, str, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withTransferBytesPerSecond(@Nullable Long l) {
        return equals(this.transferBytesPerSecond, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, l, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withCdnType(@Nullable String str) {
        return equals(this.cdnType, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, str, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withCdnCacheHitStatus(@Nullable String str) {
        return equals(this.cdnCacheHitStatus, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, str, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withRebufferCount(@Nullable Integer num) {
        return equals(this.rebufferCount, num) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, num, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withRebufferDurationMilliSeconds(@Nullable Long l) {
        return equals(this.rebufferDurationMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, l, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackInterruptionMilliSeconds(@Nullable Long l) {
        return equals(this.playbackInterruptionMilliSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, l, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withSubstituteMediaId(@Nullable String str) {
        return equals(this.substituteMediaId, str) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, str, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withTerminationDurationSeconds(@Nullable Long l) {
        return equals(this.terminationDurationSeconds, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, l, this.isNPVShowing, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withIsNPVShowing(@Nullable Boolean bool) {
        return equals(this.isNPVShowing, bool) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, bool, this.playbackRate);
    }

    public final ImmutableMediaStateClientInformation withPlaybackRate(@Nullable Long l) {
        return equals(this.playbackRate, l) ? this : new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaStateClientInformation) && equalTo(0, (ImmutableMediaStateClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableMediaStateClientInformation immutableMediaStateClientInformation) {
        return this.mediaPlayerName.equals(immutableMediaStateClientInformation.mediaPlayerName) && equals(this.playbackState, immutableMediaStateClientInformation.playbackState) && equals(this.previousPlaybackState, immutableMediaStateClientInformation.previousPlaybackState) && equals(this.progressInMilliSeconds, immutableMediaStateClientInformation.progressInMilliSeconds) && equals(this.streamFormat, immutableMediaStateClientInformation.streamFormat) && equals(this.playbackStartedAtTimeStampMilliSeconds, immutableMediaStateClientInformation.playbackStartedAtTimeStampMilliSeconds) && equals(this.playbackResumedAtTimeStampMilliSeconds, immutableMediaStateClientInformation.playbackResumedAtTimeStampMilliSeconds) && equals(this.playbackInitiationDelayMilliSeconds, immutableMediaStateClientInformation.playbackInitiationDelayMilliSeconds) && equals(this.bitrate, immutableMediaStateClientInformation.bitrate) && equals(this.cacheHitStatus, immutableMediaStateClientInformation.cacheHitStatus) && equals(this.transferBytesPerSecond, immutableMediaStateClientInformation.transferBytesPerSecond) && equals(this.cdnType, immutableMediaStateClientInformation.cdnType) && equals(this.cdnCacheHitStatus, immutableMediaStateClientInformation.cdnCacheHitStatus) && equals(this.rebufferCount, immutableMediaStateClientInformation.rebufferCount) && equals(this.rebufferDurationMilliSeconds, immutableMediaStateClientInformation.rebufferDurationMilliSeconds) && equals(this.playbackInterruptionMilliSeconds, immutableMediaStateClientInformation.playbackInterruptionMilliSeconds) && equals(this.substituteMediaId, immutableMediaStateClientInformation.substituteMediaId) && equals(this.terminationDurationSeconds, immutableMediaStateClientInformation.terminationDurationSeconds) && equals(this.isNPVShowing, immutableMediaStateClientInformation.isNPVShowing) && equals(this.playbackRate, immutableMediaStateClientInformation.playbackRate);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.mediaPlayerName.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.playbackState);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.previousPlaybackState);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.progressInMilliSeconds);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.streamFormat);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.playbackStartedAtTimeStampMilliSeconds);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.playbackResumedAtTimeStampMilliSeconds);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.playbackInitiationDelayMilliSeconds);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.bitrate);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.cacheHitStatus);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.transferBytesPerSecond);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.cdnType);
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + hashCode(this.cdnCacheHitStatus);
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + hashCode(this.rebufferCount);
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + hashCode(this.rebufferDurationMilliSeconds);
        int iHashCode16 = iHashCode15 + (iHashCode15 << 5) + hashCode(this.playbackInterruptionMilliSeconds);
        int iHashCode17 = iHashCode16 + (iHashCode16 << 5) + hashCode(this.substituteMediaId);
        int iHashCode18 = iHashCode17 + (iHashCode17 << 5) + hashCode(this.terminationDurationSeconds);
        int iHashCode19 = iHashCode18 + (iHashCode18 << 5) + hashCode(this.isNPVShowing);
        return iHashCode19 + (iHashCode19 << 5) + hashCode(this.playbackRate);
    }

    public String toString() {
        return "MediaStateClientInformation{mediaPlayerName=" + this.mediaPlayerName + ", playbackState=" + this.playbackState + ", previousPlaybackState=" + this.previousPlaybackState + ", progressInMilliSeconds=" + this.progressInMilliSeconds + ", streamFormat=" + this.streamFormat + ", playbackStartedAtTimeStampMilliSeconds=" + this.playbackStartedAtTimeStampMilliSeconds + ", playbackResumedAtTimeStampMilliSeconds=" + this.playbackResumedAtTimeStampMilliSeconds + ", playbackInitiationDelayMilliSeconds=" + this.playbackInitiationDelayMilliSeconds + ", bitrate=" + this.bitrate + ", cacheHitStatus=" + this.cacheHitStatus + ", transferBytesPerSecond=" + this.transferBytesPerSecond + ", cdnType=" + this.cdnType + ", cdnCacheHitStatus=" + this.cdnCacheHitStatus + ", rebufferCount=" + this.rebufferCount + ", rebufferDurationMilliSeconds=" + this.rebufferDurationMilliSeconds + ", playbackInterruptionMilliSeconds=" + this.playbackInterruptionMilliSeconds + ", substituteMediaId=" + this.substituteMediaId + ", terminationDurationSeconds=" + this.terminationDurationSeconds + ", isNPVShowing=" + this.isNPVShowing + ", playbackRate=" + this.playbackRate + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaStateClientInformation", generator = "Immutables")
    static final class Json extends MediaStateClientInformation {

        @Nullable
        String bitrate;

        @Nullable
        String cacheHitStatus;

        @Nullable
        String cdnCacheHitStatus;

        @Nullable
        String cdnType;

        @Nullable
        Boolean isNPVShowing;

        @Nullable
        String mediaPlayerName;

        @Nullable
        Long playbackInitiationDelayMilliSeconds;

        @Nullable
        Long playbackInterruptionMilliSeconds;

        @Nullable
        Long playbackRate;

        @Nullable
        Long playbackResumedAtTimeStampMilliSeconds;

        @Nullable
        Long playbackStartedAtTimeStampMilliSeconds;

        @Nullable
        PlaybackState playbackState;

        @Nullable
        PlaybackState previousPlaybackState;

        @Nullable
        Long progressInMilliSeconds;

        @Nullable
        Integer rebufferCount;

        @Nullable
        Long rebufferDurationMilliSeconds;

        @Nullable
        String streamFormat;

        @Nullable
        String substituteMediaId;

        @Nullable
        Long terminationDurationSeconds;

        @Nullable
        Long transferBytesPerSecond;

        Json() {
        }

        @JsonProperty("mediaPlayerName")
        public void setMediaPlayerName(String str) {
            this.mediaPlayerName = str;
        }

        @JsonProperty(TetheredMessageKey.playbackState)
        public void setPlaybackState(@Nullable PlaybackState playbackState) {
            this.playbackState = playbackState;
        }

        @JsonProperty("previousPlaybackState")
        public void setPreviousPlaybackState(@Nullable PlaybackState playbackState) {
            this.previousPlaybackState = playbackState;
        }

        @JsonProperty("progressInMilliSeconds")
        public void setProgressInMilliSeconds(@Nullable Long l) {
            this.progressInMilliSeconds = l;
        }

        @JsonProperty("streamFormat")
        public void setStreamFormat(@Nullable String str) {
            this.streamFormat = str;
        }

        @JsonProperty("playbackStartedAtTimeStampMilliSeconds")
        public void setPlaybackStartedAtTimeStampMilliSeconds(@Nullable Long l) {
            this.playbackStartedAtTimeStampMilliSeconds = l;
        }

        @JsonProperty("playbackResumedAtTimeStampMilliSeconds")
        public void setPlaybackResumedAtTimeStampMilliSeconds(@Nullable Long l) {
            this.playbackResumedAtTimeStampMilliSeconds = l;
        }

        @JsonProperty("playbackInitiationDelayMilliSeconds")
        public void setPlaybackInitiationDelayMilliSeconds(@Nullable Long l) {
            this.playbackInitiationDelayMilliSeconds = l;
        }

        @JsonProperty("bitrate")
        public void setBitrate(@Nullable String str) {
            this.bitrate = str;
        }

        @JsonProperty("cacheHitStatus")
        public void setCacheHitStatus(@Nullable String str) {
            this.cacheHitStatus = str;
        }

        @JsonProperty("transferBytesPerSecond")
        public void setTransferBytesPerSecond(@Nullable Long l) {
            this.transferBytesPerSecond = l;
        }

        @JsonProperty("cdnType")
        public void setCdnType(@Nullable String str) {
            this.cdnType = str;
        }

        @JsonProperty("cdnCacheHitStatus")
        public void setCdnCacheHitStatus(@Nullable String str) {
            this.cdnCacheHitStatus = str;
        }

        @JsonProperty("rebufferCount")
        public void setRebufferCount(@Nullable Integer num) {
            this.rebufferCount = num;
        }

        @JsonProperty("rebufferDurationMilliSeconds")
        public void setRebufferDurationMilliSeconds(@Nullable Long l) {
            this.rebufferDurationMilliSeconds = l;
        }

        @JsonProperty("playbackInterruptionMilliSeconds")
        public void setPlaybackInterruptionMilliSeconds(@Nullable Long l) {
            this.playbackInterruptionMilliSeconds = l;
        }

        @JsonProperty("substituteMediaId")
        public void setSubstituteMediaId(@Nullable String str) {
            this.substituteMediaId = str;
        }

        @JsonProperty("terminationDurationSeconds")
        public void setTerminationDurationSeconds(@Nullable Long l) {
            this.terminationDurationSeconds = l;
        }

        @JsonProperty("isNPVShowing")
        public void setIsNPVShowing(@Nullable Boolean bool) {
            this.isNPVShowing = bool;
        }

        @JsonProperty("playbackRate")
        public void setPlaybackRate(@Nullable Long l) {
            this.playbackRate = l;
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String mediaPlayerName() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public PlaybackState playbackState() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public PlaybackState previousPlaybackState() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long progressInMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String streamFormat() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long playbackStartedAtTimeStampMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long playbackResumedAtTimeStampMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long playbackInitiationDelayMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String bitrate() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String cacheHitStatus() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long transferBytesPerSecond() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String cdnType() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String cdnCacheHitStatus() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Integer rebufferCount() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long rebufferDurationMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long playbackInterruptionMilliSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public String substituteMediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long terminationDurationSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Boolean isNPVShowing() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaStateClientInformation
        public Long playbackRate() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaStateClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.mediaPlayerName;
        if (str != null) {
            builder.mediaPlayerName(str);
        }
        PlaybackState playbackState = json.playbackState;
        if (playbackState != null) {
            builder.playbackState(playbackState);
        }
        PlaybackState playbackState2 = json.previousPlaybackState;
        if (playbackState2 != null) {
            builder.previousPlaybackState(playbackState2);
        }
        Long l = json.progressInMilliSeconds;
        if (l != null) {
            builder.progressInMilliSeconds(l);
        }
        String str2 = json.streamFormat;
        if (str2 != null) {
            builder.streamFormat(str2);
        }
        Long l2 = json.playbackStartedAtTimeStampMilliSeconds;
        if (l2 != null) {
            builder.playbackStartedAtTimeStampMilliSeconds(l2);
        }
        Long l3 = json.playbackResumedAtTimeStampMilliSeconds;
        if (l3 != null) {
            builder.playbackResumedAtTimeStampMilliSeconds(l3);
        }
        Long l4 = json.playbackInitiationDelayMilliSeconds;
        if (l4 != null) {
            builder.playbackInitiationDelayMilliSeconds(l4);
        }
        String str3 = json.bitrate;
        if (str3 != null) {
            builder.bitrate(str3);
        }
        String str4 = json.cacheHitStatus;
        if (str4 != null) {
            builder.cacheHitStatus(str4);
        }
        Long l5 = json.transferBytesPerSecond;
        if (l5 != null) {
            builder.transferBytesPerSecond(l5);
        }
        String str5 = json.cdnType;
        if (str5 != null) {
            builder.cdnType(str5);
        }
        String str6 = json.cdnCacheHitStatus;
        if (str6 != null) {
            builder.cdnCacheHitStatus(str6);
        }
        Integer num = json.rebufferCount;
        if (num != null) {
            builder.rebufferCount(num);
        }
        Long l6 = json.rebufferDurationMilliSeconds;
        if (l6 != null) {
            builder.rebufferDurationMilliSeconds(l6);
        }
        Long l7 = json.playbackInterruptionMilliSeconds;
        if (l7 != null) {
            builder.playbackInterruptionMilliSeconds(l7);
        }
        String str7 = json.substituteMediaId;
        if (str7 != null) {
            builder.substituteMediaId(str7);
        }
        Long l8 = json.terminationDurationSeconds;
        if (l8 != null) {
            builder.terminationDurationSeconds(l8);
        }
        Boolean bool = json.isNPVShowing;
        if (bool != null) {
            builder.isNPVShowing(bool);
        }
        Long l9 = json.playbackRate;
        if (l9 != null) {
            builder.playbackRate(l9);
        }
        return builder.build();
    }

    public static ImmutableMediaStateClientInformation copyOf(MediaStateClientInformation mediaStateClientInformation) {
        if (mediaStateClientInformation instanceof ImmutableMediaStateClientInformation) {
            return (ImmutableMediaStateClientInformation) mediaStateClientInformation;
        }
        return builder().from(mediaStateClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaStateClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MEDIA_PLAYER_NAME = 1;

        @Nullable
        private String bitrate;

        @Nullable
        private String cacheHitStatus;

        @Nullable
        private String cdnCacheHitStatus;

        @Nullable
        private String cdnType;
        private long initBits;

        @Nullable
        private Boolean isNPVShowing;

        @Nullable
        private String mediaPlayerName;

        @Nullable
        private Long playbackInitiationDelayMilliSeconds;

        @Nullable
        private Long playbackInterruptionMilliSeconds;

        @Nullable
        private Long playbackRate;

        @Nullable
        private Long playbackResumedAtTimeStampMilliSeconds;

        @Nullable
        private Long playbackStartedAtTimeStampMilliSeconds;

        @Nullable
        private PlaybackState playbackState;

        @Nullable
        private PlaybackState previousPlaybackState;

        @Nullable
        private Long progressInMilliSeconds;

        @Nullable
        private Integer rebufferCount;

        @Nullable
        private Long rebufferDurationMilliSeconds;

        @Nullable
        private String streamFormat;

        @Nullable
        private String substituteMediaId;

        @Nullable
        private Long terminationDurationSeconds;

        @Nullable
        private Long transferBytesPerSecond;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(MediaStateClientInformation mediaStateClientInformation) {
            ImmutableMediaStateClientInformation.requireNonNull(mediaStateClientInformation, "instance");
            mediaPlayerName(mediaStateClientInformation.mediaPlayerName());
            PlaybackState playbackState = mediaStateClientInformation.playbackState();
            if (playbackState != null) {
                playbackState(playbackState);
            }
            PlaybackState playbackStatePreviousPlaybackState = mediaStateClientInformation.previousPlaybackState();
            if (playbackStatePreviousPlaybackState != null) {
                previousPlaybackState(playbackStatePreviousPlaybackState);
            }
            Long lProgressInMilliSeconds = mediaStateClientInformation.progressInMilliSeconds();
            if (lProgressInMilliSeconds != null) {
                progressInMilliSeconds(lProgressInMilliSeconds);
            }
            String strStreamFormat = mediaStateClientInformation.streamFormat();
            if (strStreamFormat != null) {
                streamFormat(strStreamFormat);
            }
            Long lPlaybackStartedAtTimeStampMilliSeconds = mediaStateClientInformation.playbackStartedAtTimeStampMilliSeconds();
            if (lPlaybackStartedAtTimeStampMilliSeconds != null) {
                playbackStartedAtTimeStampMilliSeconds(lPlaybackStartedAtTimeStampMilliSeconds);
            }
            Long lPlaybackResumedAtTimeStampMilliSeconds = mediaStateClientInformation.playbackResumedAtTimeStampMilliSeconds();
            if (lPlaybackResumedAtTimeStampMilliSeconds != null) {
                playbackResumedAtTimeStampMilliSeconds(lPlaybackResumedAtTimeStampMilliSeconds);
            }
            Long lPlaybackInitiationDelayMilliSeconds = mediaStateClientInformation.playbackInitiationDelayMilliSeconds();
            if (lPlaybackInitiationDelayMilliSeconds != null) {
                playbackInitiationDelayMilliSeconds(lPlaybackInitiationDelayMilliSeconds);
            }
            String strBitrate = mediaStateClientInformation.bitrate();
            if (strBitrate != null) {
                bitrate(strBitrate);
            }
            String strCacheHitStatus = mediaStateClientInformation.cacheHitStatus();
            if (strCacheHitStatus != null) {
                cacheHitStatus(strCacheHitStatus);
            }
            Long lTransferBytesPerSecond = mediaStateClientInformation.transferBytesPerSecond();
            if (lTransferBytesPerSecond != null) {
                transferBytesPerSecond(lTransferBytesPerSecond);
            }
            String strCdnType = mediaStateClientInformation.cdnType();
            if (strCdnType != null) {
                cdnType(strCdnType);
            }
            String strCdnCacheHitStatus = mediaStateClientInformation.cdnCacheHitStatus();
            if (strCdnCacheHitStatus != null) {
                cdnCacheHitStatus(strCdnCacheHitStatus);
            }
            Integer numRebufferCount = mediaStateClientInformation.rebufferCount();
            if (numRebufferCount != null) {
                rebufferCount(numRebufferCount);
            }
            Long lRebufferDurationMilliSeconds = mediaStateClientInformation.rebufferDurationMilliSeconds();
            if (lRebufferDurationMilliSeconds != null) {
                rebufferDurationMilliSeconds(lRebufferDurationMilliSeconds);
            }
            Long lPlaybackInterruptionMilliSeconds = mediaStateClientInformation.playbackInterruptionMilliSeconds();
            if (lPlaybackInterruptionMilliSeconds != null) {
                playbackInterruptionMilliSeconds(lPlaybackInterruptionMilliSeconds);
            }
            String strSubstituteMediaId = mediaStateClientInformation.substituteMediaId();
            if (strSubstituteMediaId != null) {
                substituteMediaId(strSubstituteMediaId);
            }
            Long lTerminationDurationSeconds = mediaStateClientInformation.terminationDurationSeconds();
            if (lTerminationDurationSeconds != null) {
                terminationDurationSeconds(lTerminationDurationSeconds);
            }
            Boolean boolIsNPVShowing = mediaStateClientInformation.isNPVShowing();
            if (boolIsNPVShowing != null) {
                isNPVShowing(boolIsNPVShowing);
            }
            Long lPlaybackRate = mediaStateClientInformation.playbackRate();
            if (lPlaybackRate != null) {
                playbackRate(lPlaybackRate);
            }
            return this;
        }

        @JsonProperty("mediaPlayerName")
        public final Builder mediaPlayerName(String str) {
            this.mediaPlayerName = (String) ImmutableMediaStateClientInformation.requireNonNull(str, "mediaPlayerName");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(TetheredMessageKey.playbackState)
        public final Builder playbackState(@Nullable PlaybackState playbackState) {
            this.playbackState = playbackState;
            return this;
        }

        @JsonProperty("previousPlaybackState")
        public final Builder previousPlaybackState(@Nullable PlaybackState playbackState) {
            this.previousPlaybackState = playbackState;
            return this;
        }

        @JsonProperty("progressInMilliSeconds")
        public final Builder progressInMilliSeconds(@Nullable Long l) {
            this.progressInMilliSeconds = l;
            return this;
        }

        @JsonProperty("streamFormat")
        public final Builder streamFormat(@Nullable String str) {
            this.streamFormat = str;
            return this;
        }

        @JsonProperty("playbackStartedAtTimeStampMilliSeconds")
        public final Builder playbackStartedAtTimeStampMilliSeconds(@Nullable Long l) {
            this.playbackStartedAtTimeStampMilliSeconds = l;
            return this;
        }

        @JsonProperty("playbackResumedAtTimeStampMilliSeconds")
        public final Builder playbackResumedAtTimeStampMilliSeconds(@Nullable Long l) {
            this.playbackResumedAtTimeStampMilliSeconds = l;
            return this;
        }

        @JsonProperty("playbackInitiationDelayMilliSeconds")
        public final Builder playbackInitiationDelayMilliSeconds(@Nullable Long l) {
            this.playbackInitiationDelayMilliSeconds = l;
            return this;
        }

        @JsonProperty("bitrate")
        public final Builder bitrate(@Nullable String str) {
            this.bitrate = str;
            return this;
        }

        @JsonProperty("cacheHitStatus")
        public final Builder cacheHitStatus(@Nullable String str) {
            this.cacheHitStatus = str;
            return this;
        }

        @JsonProperty("transferBytesPerSecond")
        public final Builder transferBytesPerSecond(@Nullable Long l) {
            this.transferBytesPerSecond = l;
            return this;
        }

        @JsonProperty("cdnType")
        public final Builder cdnType(@Nullable String str) {
            this.cdnType = str;
            return this;
        }

        @JsonProperty("cdnCacheHitStatus")
        public final Builder cdnCacheHitStatus(@Nullable String str) {
            this.cdnCacheHitStatus = str;
            return this;
        }

        @JsonProperty("rebufferCount")
        public final Builder rebufferCount(@Nullable Integer num) {
            this.rebufferCount = num;
            return this;
        }

        @JsonProperty("rebufferDurationMilliSeconds")
        public final Builder rebufferDurationMilliSeconds(@Nullable Long l) {
            this.rebufferDurationMilliSeconds = l;
            return this;
        }

        @JsonProperty("playbackInterruptionMilliSeconds")
        public final Builder playbackInterruptionMilliSeconds(@Nullable Long l) {
            this.playbackInterruptionMilliSeconds = l;
            return this;
        }

        @JsonProperty("substituteMediaId")
        public final Builder substituteMediaId(@Nullable String str) {
            this.substituteMediaId = str;
            return this;
        }

        @JsonProperty("terminationDurationSeconds")
        public final Builder terminationDurationSeconds(@Nullable Long l) {
            this.terminationDurationSeconds = l;
            return this;
        }

        @JsonProperty("isNPVShowing")
        public final Builder isNPVShowing(@Nullable Boolean bool) {
            this.isNPVShowing = bool;
            return this;
        }

        @JsonProperty("playbackRate")
        public final Builder playbackRate(@Nullable Long l) {
            this.playbackRate = l;
            return this;
        }

        public ImmutableMediaStateClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMediaStateClientInformation(this.mediaPlayerName, this.playbackState, this.previousPlaybackState, this.progressInMilliSeconds, this.streamFormat, this.playbackStartedAtTimeStampMilliSeconds, this.playbackResumedAtTimeStampMilliSeconds, this.playbackInitiationDelayMilliSeconds, this.bitrate, this.cacheHitStatus, this.transferBytesPerSecond, this.cdnType, this.cdnCacheHitStatus, this.rebufferCount, this.rebufferDurationMilliSeconds, this.playbackInterruptionMilliSeconds, this.substituteMediaId, this.terminationDurationSeconds, this.isNPVShowing, this.playbackRate);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("mediaPlayerName");
            }
            return "Cannot build MediaStateClientInformation, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
