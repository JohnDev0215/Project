package ExternalActionInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class PlayableContent {
    @Value.Default
    public boolean isExplicit() {
        return false;
    }

    @Value.Default
    public boolean isFree() {
        return false;
    }

    @Nullable
    public abstract Boolean isPrime();

    @Nullable
    public abstract Boolean isSonicRush();

    @Nullable
    public abstract Boolean isUnlimited();

    @Nullable
    public abstract String title();
}
