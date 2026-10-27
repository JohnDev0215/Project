package Watch.TetheredInterface.v1_0;

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
@JsonDeserialize(as = ImmutableSetErrorActionsMethod.class)
@JsonSerialize(as = ImmutableSetErrorActionsMethod.class)
@Value.Immutable
public abstract class SetErrorActionsMethod extends Method {
    @Value.Default
    public List<Method> onTetheredDeviceNotReachable() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onTimeout() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onNotLoggedIn() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onInOfflineMode() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onCheckPhone() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onUnknownError() {
        return Collections.emptyList();
    }
}
