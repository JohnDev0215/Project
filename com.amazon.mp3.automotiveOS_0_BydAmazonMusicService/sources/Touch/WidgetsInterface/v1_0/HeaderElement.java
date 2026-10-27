package Touch.WidgetsInterface.v1_0;

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
public abstract class HeaderElement extends WidgetElement {
    @Nullable
    public abstract ButtonElement callToActionButton();

    @Nullable
    public abstract String image();

    @Nullable
    public abstract String label();

    public abstract String primaryText();

    @Nullable
    public abstract String secondaryText();

    @Nullable
    public abstract String tertiaryText();

    @Value.Default
    public List<ButtonElement> buttons() {
        return Collections.emptyList();
    }
}
