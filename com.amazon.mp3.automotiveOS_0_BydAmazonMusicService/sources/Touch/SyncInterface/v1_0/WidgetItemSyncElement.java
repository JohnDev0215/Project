package Touch.SyncInterface.v1_0;

import ExternalActionInterface.v1_0.PlayMode;
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
@JsonDeserialize(as = ImmutableWidgetItemSyncElement.class)
@JsonSerialize(as = ImmutableWidgetItemSyncElement.class)
@Value.Immutable
public abstract class WidgetItemSyncElement extends SyncElement {
    @Nullable
    public abstract List<String> contentEncoding();

    @Nullable
    public abstract String contentInfoInstId();

    @Nullable
    public abstract String contentSrc();

    @Nullable
    public abstract String entityId();

    @Nullable
    public abstract EntityIdType entityIdType();

    @Nullable
    public abstract String image();

    @Nullable
    public abstract String label();

    @Nullable
    public abstract String metricsToken();

    @Nullable
    public abstract PlayMode playMode();

    @Nullable
    public abstract String primaryText();

    @Nullable
    public abstract String secondaryText();

    @Nullable
    public abstract String tertiaryText();

    public abstract WidgetItemContext widgetItemContext();
}
