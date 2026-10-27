package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@JsonDeserialize(as = ImmutableSelectionElement.class)
@JsonSerialize(as = ImmutableSelectionElement.class)
@Value.Immutable
public abstract class SelectionElement extends Element {
    public abstract boolean isSelected();

    public abstract String primaryText();

    @Nullable
    public abstract String secondaryText();

    public abstract String selectedIcon();

    public abstract String unSelectedIcon();

    @Value.Default
    public List<Method> onItemSelected() {
        return Collections.emptyList();
    }
}
