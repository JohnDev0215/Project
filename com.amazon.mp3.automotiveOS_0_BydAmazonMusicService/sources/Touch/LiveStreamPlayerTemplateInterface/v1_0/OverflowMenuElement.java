package Touch.LiveStreamPlayerTemplateInterface.v1_0;

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
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableOverflowMenuElement.class)
@JsonSerialize(as = ImmutableOverflowMenuElement.class)
@Value.Immutable
public abstract class OverflowMenuElement extends Element {
    public abstract List<ActionButtonElement> actions();

    @Value.Default
    public List<Method> onItemSelected() {
        return Collections.EMPTY_LIST;
    }
}
