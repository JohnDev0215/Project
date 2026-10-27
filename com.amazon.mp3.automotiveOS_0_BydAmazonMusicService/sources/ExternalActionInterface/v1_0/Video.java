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
@JsonDeserialize(as = ImmutableVideo.class)
@JsonSerialize(as = ImmutableVideo.class)
@Value.Immutable
public abstract class Video extends PlayableContent {
    @Nullable
    public abstract String asin();

    @Nullable
    public abstract Integer duration();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract String pid();

    @Nullable
    public abstract String videoType();
}
