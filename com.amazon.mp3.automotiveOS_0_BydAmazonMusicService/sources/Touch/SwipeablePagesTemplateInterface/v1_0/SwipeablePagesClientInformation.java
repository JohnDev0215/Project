package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.ClientInformation;
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
@JsonDeserialize(as = ImmutableSwipeablePagesClientInformation.class)
@JsonSerialize(as = ImmutableSwipeablePagesClientInformation.class)
@Value.Immutable
public abstract class SwipeablePagesClientInformation extends ClientInformation {
    @Value.Default
    public boolean isChartsV3Enabled() {
        return false;
    }

    @Value.Default
    public boolean isStageEnabled() {
        return false;
    }
}
