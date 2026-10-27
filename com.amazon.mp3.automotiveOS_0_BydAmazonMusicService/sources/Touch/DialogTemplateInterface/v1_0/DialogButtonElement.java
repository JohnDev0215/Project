package Touch.DialogTemplateInterface.v1_0;

import Touch.WidgetsInterface.v1_0.ButtonElement;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableDialogButtonElement.class)
@JsonSerialize(as = ImmutableDialogButtonElement.class)
@Value.Immutable
public abstract class DialogButtonElement extends ButtonElement {

    public enum Style {
        PRIMARY,
        SECONDARY
    }

    public abstract Style style();

    public abstract String text();
}
