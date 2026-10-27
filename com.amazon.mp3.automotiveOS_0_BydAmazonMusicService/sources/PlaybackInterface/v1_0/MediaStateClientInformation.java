package PlaybackInterface.v1_0;

import CoreInterface.v1_0.ClientInformation;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableMediaStateClientInformation.class)
@JsonSerialize(as = ImmutableMediaStateClientInformation.class)
@Value.Immutable
public abstract class MediaStateClientInformation extends ClientInformation {
    @Nullable
    public abstract String bitrate();

    @Nullable
    public abstract String cacheHitStatus();

    @Nullable
    public abstract String cdnCacheHitStatus();

    @Nullable
    public abstract String cdnType();

    @Nullable
    public abstract Boolean isNPVShowing();

    public abstract String mediaPlayerName();

    @Nullable
    public abstract Long playbackInitiationDelayMilliSeconds();

    @Nullable
    public abstract Long playbackInterruptionMilliSeconds();

    @Nullable
    public abstract Long playbackRate();

    @Nullable
    public abstract Long playbackResumedAtTimeStampMilliSeconds();

    @Nullable
    public abstract Long playbackStartedAtTimeStampMilliSeconds();

    @Nullable
    public abstract PlaybackState playbackState();

    @Nullable
    public abstract PlaybackState previousPlaybackState();

    @Nullable
    public abstract Long progressInMilliSeconds();

    @Nullable
    public abstract Integer rebufferCount();

    @Nullable
    public abstract Long rebufferDurationMilliSeconds();

    @Nullable
    public abstract String streamFormat();

    @Nullable
    public abstract String substituteMediaId();

    @Nullable
    public abstract Long terminationDurationSeconds();

    @Nullable
    public abstract Long transferBytesPerSecond();
}
