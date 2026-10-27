package Touch.GroupDetailTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
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
@JsonDeserialize(as = ImmutableGroupDetailTemplate.class)
@JsonSerialize(as = ImmutableGroupDetailTemplate.class)
@Value.Immutable
public abstract class GroupDetailTemplate extends Template {
    @Nullable
    public abstract String backgroundImage();

    @Nullable
    public abstract String primaryHeader();

    @Nullable
    public abstract String secondaryHeader();

    @Nullable
    public abstract String tertiaryHeader();

    @Value.Default
    public List<WidgetElement> elements() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onEndOfList() {
        return Collections.EMPTY_LIST;
    }
}
