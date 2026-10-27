package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableSpotlightCarouselElement.class)
@JsonSerialize(as = ImmutableSpotlightCarouselElement.class)
@Value.Immutable
public abstract class SpotlightCarouselElement extends WidgetElement {
    public abstract String image();

    public abstract List<SpotlightCarouselSlideElement> items();

    public abstract CarouselNavigationElement leftNavigation();

    public abstract CarouselNavigationElement rightNavigation();

    @Value.Default
    public List<Method> onSwipeLeft() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onSwipeRight() {
        return Collections.emptyList();
    }
}
