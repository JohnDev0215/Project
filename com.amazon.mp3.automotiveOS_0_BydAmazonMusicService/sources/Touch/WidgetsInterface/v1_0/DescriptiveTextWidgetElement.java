package Touch.WidgetsInterface.v1_0;

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
@JsonDeserialize(as = ImmutableDescriptiveTextWidgetElement.class)
@JsonSerialize(as = ImmutableDescriptiveTextWidgetElement.class)
@Value.Immutable
public abstract class DescriptiveTextWidgetElement extends WidgetElement {
    @Nullable
    public abstract String footer();

    @Nullable
    public abstract String interpunct();

    public abstract List<DescriptiveTextWidgetItemElement> textWidgetItems();
}
