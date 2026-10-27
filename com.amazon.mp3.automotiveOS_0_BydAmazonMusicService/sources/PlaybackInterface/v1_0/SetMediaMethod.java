package PlaybackInterface.v1_0;

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
@JsonDeserialize(as = ImmutableSetMediaMethod.class)
@JsonSerialize(as = ImmutableSetMediaMethod.class)
@Value.Immutable
public abstract class SetMediaMethod extends Method {
    public abstract MediaActionsElement actions();

    public abstract MediaEventsElement events();

    public abstract MediaMetadataElement metadata();

    @Nullable
    public abstract MediaOptionsElement options();

    @Nullable
    public abstract MediaResourcesElement resources();

    public abstract MediaControlStatesElement states();
}
