package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@JsonDeserialize(as = ImmutableRefinementButtonElement.class)
@JsonSerialize(as = ImmutableRefinementButtonElement.class)
@Value.Immutable
public abstract class RefinementButtonElement extends RefinementElement {
    @Nullable
    public abstract ImageResources leftIconResources();

    @Nullable
    public abstract List<Method> onItemDeselected();

    public abstract List<Method> onItemSelected();

    @Nullable
    public abstract ImageResources rightIconResources();

    public abstract String text();

    @Value.Default
    public Boolean isActive() {
        return Boolean.FALSE;
    }
}
