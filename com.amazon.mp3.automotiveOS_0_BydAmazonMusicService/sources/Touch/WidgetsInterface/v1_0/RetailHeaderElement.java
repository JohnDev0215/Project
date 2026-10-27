package Touch.WidgetsInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableRetailHeaderElement.class)
@JsonSerialize(as = ImmutableRetailHeaderElement.class)
@Value.Immutable
public abstract class RetailHeaderElement extends HeaderElement {
    public abstract List<String> carouselImages();

    @Nullable
    public abstract ImageResources placeholder();

    @Nullable
    public abstract String primaryBadge();

    @Nullable
    public abstract String primaryBadgeDescription();
}
