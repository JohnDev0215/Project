package Touch.SelectionTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.SelectionElement;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableSelectionTemplate.class)
@JsonSerialize(as = ImmutableSelectionTemplate.class)
@Value.Immutable
public abstract class SelectionTemplate extends Template {
    public abstract List<SelectionElement> items();

    public abstract ButtonElement primaryButton();

    public abstract String primaryHeader();

    public abstract ButtonElement secondaryButton();
}
