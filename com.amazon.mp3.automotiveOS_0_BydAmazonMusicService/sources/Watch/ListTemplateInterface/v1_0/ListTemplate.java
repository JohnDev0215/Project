package Watch.ListTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
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
@JsonDeserialize(as = ImmutableListTemplate.class)
@JsonSerialize(as = ImmutableListTemplate.class)
@Value.Immutable
public abstract class ListTemplate extends Template {
    @Nullable
    public abstract String title();

    @Value.Default
    public List<ListItemElement> overflowItems() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<WidgetElement> widgets() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onEndOfWidgetsReached() {
        return Collections.emptyList();
    }
}
