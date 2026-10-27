package Touch.DialogTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableDialogTemplate.class)
@JsonSerialize(as = ImmutableDialogTemplate.class)
@Value.Immutable
public abstract class DialogTemplate extends Template {
    public abstract String body();

    public abstract PlainIconButtonElement closeButton();

    public abstract String header();

    @Value.Default
    public List<DialogButtonElement> buttons() {
        return Collections.EMPTY_LIST;
    }
}
