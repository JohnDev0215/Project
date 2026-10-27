package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class WidgetElement extends Element {
    @Value.Default
    public String widgetId() {
        return UUID.randomUUID().toString();
    }

    @Value.Default
    public List<Method> onViewed() {
        return Collections.emptyList();
    }
}
