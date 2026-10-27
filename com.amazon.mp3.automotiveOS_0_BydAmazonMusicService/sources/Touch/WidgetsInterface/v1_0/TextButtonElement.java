package Touch.WidgetsInterface.v1_0;

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
@JsonDeserialize(as = ImmutableTextButtonElement.class)
@JsonSerialize(as = ImmutableTextButtonElement.class)
@Value.Immutable
public abstract class TextButtonElement extends ButtonElement {

    public enum Style {
        GLASS,
        SOLID,
        OUTLINE,
        OVERRIDE
    }

    @Nullable
    public abstract String activeText();

    @Nullable
    public abstract String icon();

    @Nullable
    public abstract ImageResources iconResources();

    @Value.Default
    public boolean isActive() {
        return false;
    }

    @Value.Default
    public boolean shouldToggle() {
        return false;
    }

    @Nullable
    public abstract Style style();

    public abstract String text();
}
