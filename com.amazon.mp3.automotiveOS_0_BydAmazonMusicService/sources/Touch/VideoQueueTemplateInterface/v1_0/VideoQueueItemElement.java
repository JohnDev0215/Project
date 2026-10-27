package Touch.VideoQueueTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.LabeledIconButtonElement;
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
@JsonDeserialize(as = ImmutableVideoQueueItemElement.class)
@JsonSerialize(as = ImmutableVideoQueueItemElement.class)
@Value.Immutable
public abstract class VideoQueueItemElement extends Element {
    @Nullable
    public abstract Integer durationSeconds();

    @Nullable
    public abstract String introUri();

    public abstract String mediaId();

    @Nullable
    public abstract String subtitle();

    @Nullable
    public abstract String title();

    public abstract String uri();

    @Value.Default
    public List<Method> onItemViewed() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<LabeledIconButtonElement> actions() {
        return Collections.emptyList();
    }
}
