package PlaybackInterface.v1_0;

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
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableRewindControlStateElement.class)
@JsonSerialize(as = ImmutableRewindControlStateElement.class)
@Value.Immutable
public abstract class RewindControlStateElement extends Element {
    public abstract String altText();

    public abstract String description();

    @Value.Default
    public Boolean isDisabled() {
        return Boolean.FALSE;
    }

    @Value.Default
    public List<Method> onEnabledRewindControlStateElementSelected() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onDisabledRewindControlStateElementSelected() {
        return Collections.EMPTY_LIST;
    }
}
