package Watch.AlertTemplateInterface.v1_0;

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
@JsonDeserialize(as = ImmutableAlertTemplate.class)
@JsonSerialize(as = ImmutableAlertTemplate.class)
@Value.Immutable
public abstract class AlertTemplate extends Template {
    @Nullable
    public abstract String description();

    public abstract String message();

    @Nullable
    public abstract AlertButtonElement negativeButton();

    @Nullable
    public abstract AlertButtonElement positiveButton();

    @Nullable
    public abstract String title();
}
