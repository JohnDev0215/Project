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
@JsonDeserialize(as = ImmutablePodcastEpisode.class)
@JsonSerialize(as = ImmutablePodcastEpisode.class)
@Value.Immutable
public abstract class PodcastEpisode extends PlayableContent {

    public enum PlaybackSource {
        CATALOG,
        PLAYLIST,
        LATEST
    }

    public abstract String episodeId();

    @Value.Default
    public String episodeTitle() {
        return "";
    }

    @Nullable
    public abstract String image();

    @Value.Default
    public String podcastId() {
        return "";
    }

    @Value.Default
    public String podcastTitle() {
        return "";
    }

    @Value.Default
    public PlaybackSource source() {
        return PlaybackSource.CATALOG;
    }
}
