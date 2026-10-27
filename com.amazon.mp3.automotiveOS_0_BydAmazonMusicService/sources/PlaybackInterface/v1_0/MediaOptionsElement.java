package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Element;
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
@JsonDeserialize(as = ImmutableMediaOptionsElement.class)
@JsonSerialize(as = ImmutableMediaOptionsElement.class)
@Value.Immutable
public abstract class MediaOptionsElement extends Element {
    @Nullable
    public abstract MediaOptionElement album();

    public abstract String altText();

    @Nullable
    public abstract MediaOptionElement artist();

    public abstract String description();

    @Nullable
    public abstract MediaOptionElement station();
}
