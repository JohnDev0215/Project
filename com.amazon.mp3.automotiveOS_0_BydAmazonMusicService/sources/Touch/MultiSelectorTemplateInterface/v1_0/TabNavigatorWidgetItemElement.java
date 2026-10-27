package Touch.MultiSelectorTemplateInterface.v1_0;

import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableTabNavigatorWidgetItemElement.class)
@JsonSerialize(as = ImmutableTabNavigatorWidgetItemElement.class)
@Value.Immutable
public abstract class TabNavigatorWidgetItemElement extends WidgetElement {
    @Nullable
    public abstract SelectableTextButtonListingElement items();

    public abstract String label();
}
