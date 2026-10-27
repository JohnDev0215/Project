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
@JsonDeserialize(as = ImmutablePillNavigatorWidgetElement.class)
@JsonSerialize(as = ImmutablePillNavigatorWidgetElement.class)
@Value.Immutable
public abstract class PillNavigatorWidgetElement extends WidgetElement {
    @Nullable
    public abstract String header();

    public abstract List<PillNavigatorWidgetItemElement> items();

    @Nullable
    public abstract PillNavigatorWidgetSeeMoreItemElement seeMore();
}
