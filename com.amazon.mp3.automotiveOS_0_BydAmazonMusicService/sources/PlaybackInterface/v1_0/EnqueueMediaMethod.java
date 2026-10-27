package PlaybackInterface.v1_0;

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
@JsonDeserialize(as = ImmutableEnqueueMediaMethod.class)
@JsonSerialize(as = ImmutableEnqueueMediaMethod.class)
@Value.Immutable
public abstract class EnqueueMediaMethod extends Method {
    @Nullable
    public abstract String audioStreamFormat();

    public abstract String audioUri();

    public abstract String mediaId();

    @Value.Default
    public List<Method> onEnqueuedStart() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onSkippedToNextTrack() {
        return Collections.EMPTY_LIST;
    }
}
