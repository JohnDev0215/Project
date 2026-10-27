package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
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
@JsonDeserialize(as = ImmutableSwipeablePagesTemplate.class)
@JsonSerialize(as = ImmutableSwipeablePagesTemplate.class)
@Value.Immutable
public abstract class SwipeablePagesTemplate extends Template {
    @Nullable
    public abstract SwipeablePageLabelElement terminatingLabel();

    @Value.Default
    public List<SwipeablePageElement> pages() {
        return Collections.EMPTY_LIST;
    }
}
