package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@JsonDeserialize(as = ImmutableNavigationBarElement.class)
@JsonSerialize(as = ImmutableNavigationBarElement.class)
@Value.Immutable
public abstract class NavigationBarElement extends Element {
    @Nullable
    public abstract ButtonElement callToActionButton();

    public abstract String primaryHeader();

    @Nullable
    public abstract RefinementCollectionElement refinementCollection();

    @Nullable
    public abstract SearchBarElement searchBarElement();

    @Nullable
    public abstract String secondaryHeader();

    @Value.Default
    public List<ButtonElement> buttons() {
        return Collections.emptyList();
    }
}
