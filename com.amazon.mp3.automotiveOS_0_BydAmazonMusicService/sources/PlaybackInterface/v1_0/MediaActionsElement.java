package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableMediaActionsElement.class)
@JsonSerialize(as = ImmutableMediaActionsElement.class)
@Value.Immutable
public abstract class MediaActionsElement extends Element {
    public abstract List<Method> onMoveTo();

    public abstract List<Method> onNext();

    public abstract List<Method> onPrevious();

    public abstract List<Method> onRepeatAll();

    public abstract List<Method> onRepeatOff();

    public abstract List<Method> onRepeatOne();

    public abstract List<Method> onShuffleOff();

    public abstract List<Method> onShuffleOn();

    public abstract List<Method> onThumbsDown();

    public abstract List<Method> onThumbsUp();

    public abstract List<Method> onUndoThumbsDown();

    public abstract List<Method> onUndoThumbsUp();

    public abstract List<Method> onVolumeChange();
}
