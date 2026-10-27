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
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutablePillNavigatorWidgetSeeMoreItemElement.class)
@JsonSerialize(as = ImmutablePillNavigatorWidgetSeeMoreItemElement.class)
@Value.Immutable
public abstract class PillNavigatorWidgetSeeMoreItemElement extends WidgetElement {
    @Nullable
    public abstract String icon();

    public abstract String text();

    @Value.Default
    public List<Method> onItemSelected() {
        return Collections.emptyList();
    }
}
