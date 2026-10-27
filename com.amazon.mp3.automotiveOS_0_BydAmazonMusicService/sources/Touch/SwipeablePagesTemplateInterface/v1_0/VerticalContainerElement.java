package Touch.SwipeablePagesTemplateInterface.v1_0;

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
@JsonDeserialize(as = ImmutableVerticalContainerElement.class)
@JsonSerialize(as = ImmutableVerticalContainerElement.class)
@Value.Immutable
public abstract class VerticalContainerElement extends Element {
    @Nullable
    public abstract String backgroundImage();

    public abstract List<Element> elements();

    @Value.Default
    public Boolean shouldBlurBackground() {
        return Boolean.FALSE;
    }

    @Value.Default
    public Boolean canSpillOverToNextSection() {
        return Boolean.FALSE;
    }

    @Value.Default
    public List<Method> onViewed() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onViewedPreview() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onPreviewClicked() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onLongPressed() {
        return Collections.EMPTY_LIST;
    }
}
