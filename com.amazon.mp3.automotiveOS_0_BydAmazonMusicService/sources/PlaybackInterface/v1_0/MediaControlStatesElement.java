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
@JsonDeserialize(as = ImmutableMediaControlStatesElement.class)
@JsonSerialize(as = ImmutableMediaControlStatesElement.class)
@Value.Immutable
public abstract class MediaControlStatesElement extends Element {
    @Nullable
    public abstract ActiveQueuesElement activeQueues();

    @Nullable
    public abstract ActiveQueuesControlStateElement activeQueuesTV();

    @Nullable
    public abstract FastForwardControlStateElement fastForward();

    @Nullable
    public abstract NextControlStateElement next();

    @Nullable
    public abstract PlayControlStateElement play();

    @Nullable
    public abstract PlaybackSpeedControlStateElement playbackSpeed();

    @Nullable
    public abstract PreviousControlStateElement previous();

    @Nullable
    public abstract RepeatControlStateElement repeat();

    @Nullable
    public abstract RewindControlStateElement rewind();

    @Nullable
    public abstract ScrubControlStateElement scrub();

    @Nullable
    public abstract ShuffleControlStateElement shuffle();

    @Nullable
    public abstract SkipBackwardControlStateElement skipBackward();

    @Nullable
    public abstract SkipForwardControlStateElement skipForward();

    @Nullable
    public abstract SleepTimerControlStateElement sleepTimer();

    @Nullable
    public abstract ThumbsDownControlStateElement thumbsDown();

    @Nullable
    public abstract ThumbsUpControlStateElement thumbsUp();

    @Nullable
    public abstract VisualPlayQueueControlStateElement visualPlayQueue();

    @Nullable
    public abstract VolumeControlStateElement volume();
}
