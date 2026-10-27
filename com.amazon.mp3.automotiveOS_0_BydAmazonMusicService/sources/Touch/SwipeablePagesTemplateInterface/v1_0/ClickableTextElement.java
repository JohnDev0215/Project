package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
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
@JsonDeserialize(as = ImmutableClickableTextElement.class)
@JsonSerialize(as = ImmutableClickableTextElement.class)
@Value.Immutable
public abstract class ClickableTextElement extends Element {

    public enum Style {
        PRIMARY,
        SECONDARY,
        CONTENT,
        PRIMARY_TITLE,
        DESCRIPTION
    }

    @Nullable
    public abstract LinkElement link();

    @Nullable
    public abstract Integer maxLines();

    public abstract Style style();

    public abstract String text();

    @Value.Default
    public List<Method> onItemSelected() {
        return Collections.EMPTY_LIST;
    }
}
