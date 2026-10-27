package PlaybackInterface.v1_0;

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
@JsonDeserialize(as = ImmutableMoveQueueEntitiesClientInformation.class)
@JsonSerialize(as = ImmutableMoveQueueEntitiesClientInformation.class)
@Value.Immutable
public abstract class MoveQueueEntitiesClientInformation extends ClientInformation {
    public abstract String dropId();

    public abstract String dropIndex();

    public abstract String entryId();

    public abstract String entryIndex();
}
