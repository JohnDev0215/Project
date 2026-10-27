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
@JsonDeserialize(as = ImmutableArtist.class)
@JsonSerialize(as = ImmutableArtist.class)
@Value.Immutable
public abstract class Artist extends PlayableContent {

    public enum PlaybackSource {
        LIBRARY,
        CATALOG
    }

    @Nullable
    public abstract String asin();

    @Nullable
    public abstract PlaymodeEligibility playmodeEligibility();

    @Value.Default
    public boolean shuffle() {
        return false;
    }

    @Nullable
    public abstract PlaybackSource source();

    @Value.Default
    public boolean startStation() {
        return false;
    }
}
