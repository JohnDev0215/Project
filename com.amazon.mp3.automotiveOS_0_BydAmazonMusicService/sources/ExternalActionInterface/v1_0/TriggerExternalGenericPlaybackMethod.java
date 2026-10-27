package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableTriggerExternalGenericPlaybackMethod.class)
@JsonSerialize(as = ImmutableTriggerExternalGenericPlaybackMethod.class)
@Value.Immutable
public abstract class TriggerExternalGenericPlaybackMethod extends Method {
    @Nullable
    public abstract List<PlayableContent> content();

    @Nullable
    public abstract String nextToken();

    @Value.Default
    public int startingIndex() {
        return 0;
    }
}
