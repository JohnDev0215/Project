package Touch.VideoQueueTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
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
@JsonDeserialize(as = ImmutableVideoQueueElement.class)
@JsonSerialize(as = ImmutableVideoQueueElement.class)
@Value.Immutable
public abstract class VideoQueueElement extends Element {
    @Nullable
    public abstract Integer startIndex();

    public abstract List<PlaybackTemplate> videoItems();
}
