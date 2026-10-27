package ExternalActionInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableAlbum.class)
@JsonSerialize(as = ImmutableAlbum.class)
@Value.Immutable
public abstract class Album extends PlayableContent {

    public enum PlaybackSource {
        LIBRARY,
        CATALOG
    }

    @Nullable
    public abstract String albumArtworkUrl();

    @Nullable
    public abstract List<Track> albumTracks();

    @Nullable
    public abstract String artistAsin();

    @Nullable
    public abstract String artistName();

    @Nullable
    public abstract String asin();

    @Nullable
    public abstract Date originalReleaseDate();

    @Nullable
    public abstract PlaymodeEligibility playmodeEligibility();

    @Nullable
    public abstract PlaybackSource source();
}
