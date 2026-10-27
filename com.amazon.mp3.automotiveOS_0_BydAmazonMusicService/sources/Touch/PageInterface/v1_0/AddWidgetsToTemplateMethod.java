package Touch.PageInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@JsonDeserialize(as = ImmutableAddWidgetsToTemplateMethod.class)
@JsonSerialize(as = ImmutableAddWidgetsToTemplateMethod.class)
@Value.Immutable
public abstract class AddWidgetsToTemplateMethod extends Method {
    @Value.Default
    public boolean shouldPerformWithAnimation() {
        return false;
    }

    @Value.Default
    public boolean shouldReplaceExistingWidgets() {
        return false;
    }

    @Value.Default
    public boolean shouldScroll() {
        return false;
    }

    @Nullable
    public abstract String widgetId();

    public abstract List<WidgetElement> widgets();

    @Value.Default
    public List<Method> onEndOfWidget() {
        return Collections.emptyList();
    }
}
