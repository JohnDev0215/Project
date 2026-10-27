package Touch.WidgetsTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.ArrayList;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableWidgetsTemplate.class)
@JsonSerialize(as = ImmutableWidgetsTemplate.class)
@Value.Immutable
public abstract class WidgetsTemplate extends Template {
    @Value.Default
    public List<WidgetElement> widgets() {
        return new ArrayList();
    }
}
