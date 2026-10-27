package Watch.MessageTemplateInterface.v1_0;

import CoreInterface.v1_0.Template;
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
@JsonDeserialize(as = ImmutableMessageTemplate.class)
@JsonSerialize(as = ImmutableMessageTemplate.class)
@Value.Immutable
public abstract class MessageTemplate extends Template {
    public abstract String message();

    @Nullable
    public abstract String subMessage();

    @Nullable
    public abstract String title();
}
