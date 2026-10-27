package Touch.PlaybackTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.PlayableContent;
import Touch.WidgetsInterface.v1_0.ButtonElement;
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
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutablePlaybackTemplate.class)
@JsonSerialize(as = ImmutablePlaybackTemplate.class)
@Value.Immutable
public abstract class PlaybackTemplate extends Template {
    @Nullable
    public abstract String backgroundImage();

    public abstract PlayableContent playableContent();

    public abstract String primaryText();

    @Nullable
    public abstract String secondaryText();

    @Nullable
    public abstract String tertiaryText();

    @Value.Default
    public List<ButtonElement> actionButtons() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Element> secondaryButtons() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onAudioEnded() {
        return Collections.emptyList();
    }

    @Value.Default
    public List<Method> onSwipedUp() {
        return Collections.emptyList();
    }
}
