package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableMediaMetadataElement.class)
@JsonSerialize(as = ImmutableMediaMetadataElement.class)
@Value.Immutable
public abstract class MediaMetadataElement extends Element {
    @Value.Default
    public String albumName() {
        return "";
    }

    @Value.Default
    public String artistName() {
        return "";
    }

    public abstract String artwork();

    @Nullable
    public abstract Integer durationSeconds();

    public abstract String logo();

    public abstract String mediaId();

    @Nullable
    public abstract String subTitle();

    @Nullable
    public abstract String subTitleContainerType();

    public abstract String title();

    @Value.Default
    public List<Method> onBackgroundImageRequired() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onLyricsRequired() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onMediaViewed() {
        return Collections.emptyList();
    }
}
