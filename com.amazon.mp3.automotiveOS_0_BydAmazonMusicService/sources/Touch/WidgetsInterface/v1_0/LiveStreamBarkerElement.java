package Touch.WidgetsInterface.v1_0;

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
@JsonDeserialize(as = ImmutableLiveStreamBarkerElement.class)
@JsonSerialize(as = ImmutableLiveStreamBarkerElement.class)
@Value.Immutable
public abstract class LiveStreamBarkerElement extends WidgetElement {
    public abstract String avatar();

    public abstract String primaryText();

    public abstract String secondaryText();

    public abstract String state();

    @Nullable
    public abstract String tertiaryText();

    public abstract String thumbnailImage();

    public abstract String viewers();

    public abstract String wordmark();

    @Value.Default
    public List<Method> onItemSelected() {
        return Collections.emptyList();
    }
}
