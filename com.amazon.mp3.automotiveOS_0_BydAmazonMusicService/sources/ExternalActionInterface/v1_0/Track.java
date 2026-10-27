package ExternalActionInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@Value.Style(deepImmutablesDetection = true, depluralize = true)
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableTrack.class)
@JsonSerialize(as = ImmutableTrack.class)
@Value.Immutable
public abstract class Track extends PlayableContent {
    @Nullable
    public abstract String albumArtworkUrl();

    @Nullable
    public abstract String albumAsin();

    @Nullable
    public abstract String albumName();

    @Nullable
    public abstract String artistAsin();

    @Nullable
    public abstract String artistName();

    @Nullable
    public abstract String asin();

    @Nullable
    public abstract Integer duration();

    @Value.Default
    public boolean isVideo() {
        return false;
    }

    @Nullable
    public abstract PlaymodeEligibility playmodeEligibility();

    @Nullable
    public abstract String videoPid();
}
