package CoreInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Collections;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
public abstract class Template extends SkyFireObject {
    @Value.Default
    public List<Method> onCreated() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onBound() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public List<Method> onViewed() {
        return Collections.EMPTY_LIST;
    }

    @Value.Default
    public TemplateLaunchMode launchMode() {
        return TemplateLaunchMode.standard;
    }

    @Value.Default
    public Long cacheSeconds() {
        return Long.MAX_VALUE;
    }
}
