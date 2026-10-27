package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableFlexibleTextContainerElement.class)
@JsonSerialize(as = ImmutableFlexibleTextContainerElement.class)
@Value.Immutable
public abstract class FlexibleTextContainerElement extends Element {
    public abstract String collapseLabelText();

    @Nullable
    public abstract String content();

    public abstract String expandLabelText();

    @Value.Default
    public int lineCountWhenCollapsed() {
        return 6;
    }

    @Nullable
    public abstract List<TextElement> richContent();
}
