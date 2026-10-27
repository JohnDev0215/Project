package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableInitializeTransferPlaybackMethod.class)
@JsonSerialize(as = ImmutableInitializeTransferPlaybackMethod.class)
@Value.Immutable
public abstract class InitializeTransferPlaybackMethod extends Method {
    public abstract String entityId();

    public abstract String entityReferenceId();

    public abstract String entityType();

    public abstract String queueId();

    public abstract String sessionId();

    @Nullable
    public abstract Double startAt();
}
