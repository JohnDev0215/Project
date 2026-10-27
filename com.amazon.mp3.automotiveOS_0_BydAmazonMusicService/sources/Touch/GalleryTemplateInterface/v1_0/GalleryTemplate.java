package Touch.GalleryTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
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
@JsonDeserialize(as = ImmutableGalleryTemplate.class)
@JsonSerialize(as = ImmutableGalleryTemplate.class)
@Value.Immutable
public abstract class GalleryTemplate extends Template {
    @Nullable
    public abstract String backgroundColor();

    @Nullable
    public abstract String backgroundImage();

    @Nullable
    public abstract TextButtonElement button();

    @Value.Default
    public boolean canSwipeToRefresh() {
        return false;
    }

    @Nullable
    public abstract String mainText();

    @Nullable
    public abstract MessageElement messageElement();

    @Nullable
    public abstract NavigationBarElement navigationBarElement();

    @Value.Default
    public List<WidgetElement> widgetElements() {
        return Collections.emptyList();
    }
}
