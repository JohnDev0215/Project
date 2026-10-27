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
@JsonDeserialize(as = ImmutableMediaEventsElement.class)
@JsonSerialize(as = ImmutableMediaEventsElement.class)
@Value.Immutable
public abstract class MediaEventsElement extends Element {
    @Value.Default
    public List<Method> onLicenseRequired() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onStarted() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onResumed() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onScrubbed() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onPaused() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onFinished() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onStopped() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onCompletedThresholdElapsed() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onRebuffered() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onError() {
        return Collections.EMPTY_LIST;
    }
}
