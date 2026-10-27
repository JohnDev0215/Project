package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.PlayableContent;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class HorizontalItemElement extends Element {
    @Nullable
    public abstract String backgroundImage();

    @Nullable
    public abstract List<BadgeItemElement> badges();

    @Nullable
    public abstract String icon();

    @Nullable
    public abstract ImageResources iconResources();

    public abstract String image();

    @Value.Default
    public boolean isDisabled() {
        return false;
    }

    @Value.Default
    public boolean isFeatured() {
        return false;
    }

    @Nullable
    public abstract String label();

    public abstract List<Method> onItemContextSelected();

    public abstract List<Method> onItemSelected();

    @Nullable
    public abstract ImageResources placeholder();

    @Nullable
    public abstract PlayableContent playableContent();

    public abstract String primaryText();

    @Nullable
    public abstract ButtonElement secondaryButton();

    @Nullable
    public abstract String secondaryText();

    @Value.Default
    public boolean shouldBlurBackgroundImage() {
        return false;
    }

    @Value.Default
    public boolean showNavigationIcon() {
        return false;
    }

    @Nullable
    public abstract String tertiaryText();

    @Value.Default
    public List<Method> onAddSelected() {
        return Collections.emptyList();
    }
}
