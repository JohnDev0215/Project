package Touch.WidgetsInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.Date;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableEventHeaderElement.class)
@JsonSerialize(as = ImmutableEventHeaderElement.class)
@Value.Immutable
public abstract class EventHeaderElement extends HeaderElement {
    @Nullable
    public abstract String daysText();

    @Nullable
    public abstract Date endTime();

    @Nullable
    public abstract String hoursText();

    @Nullable
    public abstract String minutesText();

    @Nullable
    public abstract ButtonElement primaryButton();

    @Nullable
    public abstract ButtonElement secondaryButton();

    @Nullable
    public abstract String secondsText();

    @Nullable
    public abstract Date startTime();

    @Nullable
    @Deprecated
    public abstract String timerText();
}
