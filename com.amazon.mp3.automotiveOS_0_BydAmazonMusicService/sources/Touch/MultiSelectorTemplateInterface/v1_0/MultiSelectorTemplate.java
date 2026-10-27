package Touch.MultiSelectorTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@JsonDeserialize(as = ImmutableMultiSelectorTemplate.class)
@JsonSerialize(as = ImmutableMultiSelectorTemplate.class)
@Value.Immutable
public abstract class MultiSelectorTemplate extends Template {
    @Nullable
    public abstract String backgroundImage();

    @Nullable
    public abstract ButtonElement callToActionButton();

    @Nullable
    public abstract ButtonElement closeButton();

    @Nullable
    public abstract String subtitle();

    @Nullable
    public abstract TabNavigatorWidgetElement tabNavigatorWidgetElement();

    @Nullable
    public abstract String title();

    @Value.Default
    public List<WidgetElement> widgetElements() {
        return Collections.emptyList();
    }
}
