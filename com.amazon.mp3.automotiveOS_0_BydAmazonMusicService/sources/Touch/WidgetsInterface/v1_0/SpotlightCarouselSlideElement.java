package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@JsonDeserialize(as = ImmutableSpotlightCarouselSlideElement.class)
@JsonSerialize(as = ImmutableSpotlightCarouselSlideElement.class)
@Value.Immutable
public abstract class SpotlightCarouselSlideElement extends Element {
    @Nullable
    public abstract PillNavigatorWidgetItemElement callToAction();

    @Nullable
    public abstract String image();

    public abstract String primaryText();

    @Nullable
    public abstract PillNavigatorWidgetItemElement secondaryCallToAction();

    public abstract String secondaryText();

    @Nullable
    public abstract String tertiaryText();
}
