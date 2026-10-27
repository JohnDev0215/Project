package ExternalActionInterface.v1_0;

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
@Value.Style(deepImmutablesDetection = true, depluralize = true)
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableStation.class)
@JsonSerialize(as = ImmutableStation.class)
@Value.Immutable
public abstract class Station extends PlayableContent {
    @Nullable
    public abstract String image();

    @Nullable
    public abstract String key();

    @Nullable
    public abstract PlaymodeEligibility playmodeEligibility();

    @Value.Default
    public List<PlayableContent> seed() {
        return Collections.emptyList();
    }
}
