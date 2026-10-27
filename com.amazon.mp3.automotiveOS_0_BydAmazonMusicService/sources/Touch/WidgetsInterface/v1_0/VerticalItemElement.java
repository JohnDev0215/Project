package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class VerticalItemElement extends Element {
    @Nullable
    public abstract String backgroundImage();

    @Nullable
    public abstract List<BadgeItemElement> bottomBadges();

    @Nullable
    public abstract List<ButtonElement> buttons();

    @Nullable
    public abstract String icon();

    @Nullable
    public abstract ImageResources iconResources();

    public abstract String image();

    @Nullable
    public abstract String imageDescription();

    @Value.Default
    public boolean isDisabled() {
        return false;
    }

    @Nullable
    public abstract String label();

    public abstract List<Method> onItemContextSelected();

    public abstract List<Method> onItemSelected();

    @Nullable
    public abstract ImageResources placeholder();

    public abstract String primaryText();

    @Nullable
    public abstract Double rating();

    @Nullable
    public abstract Integer reviewCount();

    @Nullable
    public abstract String secondaryText();

    @Value.Default
    public boolean shouldAddImagePadding() {
        return false;
    }

    @Value.Default
    public boolean shouldBlurBackgroundImage() {
        return false;
    }

    @Nullable
    public abstract String tertiaryText();

    @Nullable
    public abstract List<BadgeItemElement> topBadges();
}
