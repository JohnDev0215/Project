package Touch.PageInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
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
@JsonDeserialize(as = ImmutableAddHorizontalItemsToLastWidgetMethod.class)
@JsonSerialize(as = ImmutableAddHorizontalItemsToLastWidgetMethod.class)
@Value.Immutable
public abstract class AddHorizontalItemsToLastWidgetMethod extends Method {
    public abstract List<HorizontalItemElement> items();

    @Value.Default
    public List<Method> onEndOfWidget() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onViewed() {
        return Collections.emptyList();
    }
}
