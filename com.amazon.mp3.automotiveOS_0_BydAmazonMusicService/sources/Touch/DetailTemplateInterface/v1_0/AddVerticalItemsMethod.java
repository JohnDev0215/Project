package Touch.DetailTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
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
@JsonDeserialize(as = ImmutableAddVerticalItemsMethod.class)
@JsonSerialize(as = ImmutableAddVerticalItemsMethod.class)
@Value.Immutable
public abstract class AddVerticalItemsMethod extends Method {
    @Value.Default
    public List<VerticalItemElement> items() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onEndOfList() {
        return Collections.EMPTY_LIST;
    }
}
