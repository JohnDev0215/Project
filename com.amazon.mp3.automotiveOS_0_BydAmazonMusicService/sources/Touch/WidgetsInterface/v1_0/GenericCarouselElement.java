package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class GenericCarouselElement extends WidgetElement {
    @Nullable
    public abstract Integer durationSeconds();

    @Nullable
    public abstract CarouselNavigationElement leftNavigation();

    @Nullable
    public abstract CarouselNavigationElement rightNavigation();

    @Value.Default
    public List<Method> onSwipeLeft() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onSwipeRight() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onAutoAdvance() {
        return Collections.emptyList();
    }
}
