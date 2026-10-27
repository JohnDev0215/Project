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
@JsonDeserialize(as = ImmutableVideoStoryContent.class)
@JsonSerialize(as = ImmutableVideoStoryContent.class)
@Value.Immutable
public abstract class VideoStoryContent extends PlayableContent {
    @Nullable
    public abstract String artistArtworkUrl();

    @Nullable
    public abstract String artistAsin();

    @Nullable
    public abstract String artistName();

    @Nullable
    public abstract String artworkUrl();

    @Nullable
    public abstract String asin();

    @Nullable
    public abstract Integer duration();

    @Nullable
    public abstract String introUri();

    @Nullable
    public abstract String uri();

    @Nullable
    public abstract String videoPid();
}
