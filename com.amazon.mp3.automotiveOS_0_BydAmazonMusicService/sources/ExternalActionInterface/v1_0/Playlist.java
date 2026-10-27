package ExternalActionInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutablePlaylist.class)
@JsonSerialize(as = ImmutablePlaylist.class)
@Value.Immutable
public abstract class Playlist extends PlayableContent {
    @Nullable
    public abstract String asin();

    @Nullable
    public abstract String luid();

    @Nullable
    public abstract String playlistArtworkUrl();

    @Nullable
    public abstract List<Track> playlistTracks();

    @Nullable
    public abstract PlaymodeEligibility playmodeEligibility();
}
