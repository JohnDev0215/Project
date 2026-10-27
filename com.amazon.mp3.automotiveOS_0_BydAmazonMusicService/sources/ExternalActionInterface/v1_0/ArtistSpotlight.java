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
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableArtistSpotlight.class)
@JsonSerialize(as = ImmutableArtistSpotlight.class)
@Deprecated
@Value.Immutable
public abstract class ArtistSpotlight extends PlayableContent {
    @Nullable
    public abstract String artistAsin();

    @Nullable
    public abstract String artistName();

    @Nullable
    public abstract PlayableContent featuredContent();

    @Nullable
    public abstract String spotlightArtwork();

    @Nullable
    public abstract String spotlightPlaybackContent();
}
