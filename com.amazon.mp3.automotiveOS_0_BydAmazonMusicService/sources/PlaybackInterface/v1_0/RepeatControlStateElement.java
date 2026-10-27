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
import java.util.Map;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableRepeatControlStateElement.class)
@JsonSerialize(as = ImmutableRepeatControlStateElement.class)
@Value.Immutable
public abstract class RepeatControlStateElement extends Element {

    public enum State {
        ALL,
        ONE,
        OFF
    }

    public abstract Map<State, String> altTexts();

    public abstract Map<State, String> descriptions();

    public abstract State state();

    @Value.Default
    public Boolean isDisabled() {
        return Boolean.FALSE;
    }

    @Value.Default
    public Boolean isRepeatAllDisabled() {
        return Boolean.FALSE;
    }

    @Value.Default
    public List<Method> onEnabledRepeatControlSelected() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onDisabledRepeatControlSelected() {
        return Collections.EMPTY_LIST;
    }
}
