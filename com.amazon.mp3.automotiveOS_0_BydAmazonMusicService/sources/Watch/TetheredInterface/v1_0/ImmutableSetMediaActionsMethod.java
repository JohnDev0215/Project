package Watch.TetheredInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SetMediaActionsMethod", generator = "Immutables")
public final class ImmutableSetMediaActionsMethod extends SetMediaActionsMethod {
    private final Boolean forced;
    private final List<Method> onMoveTo;
    private final List<Method> onNext;
    private final List<Method> onPause;
    private final List<Method> onPlaybackSpeedChange;
    private final List<Method> onPrevious;

    @Nullable
    private final List<Method> onRemotePlaybackActivated;

    @Nullable
    private final List<Method> onRemotePlaybackDeactivated;
    private final List<Method> onRepeatAll;
    private final List<Method> onRepeatOff;
    private final List<Method> onRepeatOne;
    private final List<Method> onResume;
    private final List<Method> onShuffleOff;
    private final List<Method> onShuffleOn;
    private final List<Method> onSkipBackward;
    private final List<Method> onSkipForward;
    private final List<Method> onThumbsDown;
    private final List<Method> onThumbsUp;
    private final List<Method> onUndoThumbsDown;
    private final List<Method> onUndoThumbsUp;
    private final List<Method> onVolumeChange;
    private final Queue queue;

    private ImmutableSetMediaActionsMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.onPause = createUnmodifiableList(true, builder.onPause);
        this.onResume = createUnmodifiableList(true, builder.onResume);
        this.onNext = createUnmodifiableList(true, builder.onNext);
        this.onPrevious = createUnmodifiableList(true, builder.onPrevious);
        this.onRepeatOff = createUnmodifiableList(true, builder.onRepeatOff);
        this.onRepeatOne = createUnmodifiableList(true, builder.onRepeatOne);
        this.onRepeatAll = createUnmodifiableList(true, builder.onRepeatAll);
        this.onShuffleOn = createUnmodifiableList(true, builder.onShuffleOn);
        this.onShuffleOff = createUnmodifiableList(true, builder.onShuffleOff);
        this.onThumbsUp = createUnmodifiableList(true, builder.onThumbsUp);
        this.onUndoThumbsUp = createUnmodifiableList(true, builder.onUndoThumbsUp);
        this.onThumbsDown = createUnmodifiableList(true, builder.onThumbsDown);
        this.onUndoThumbsDown = createUnmodifiableList(true, builder.onUndoThumbsDown);
        this.onVolumeChange = createUnmodifiableList(true, builder.onVolumeChange);
        this.onMoveTo = createUnmodifiableList(true, builder.onMoveTo);
        this.onSkipForward = createUnmodifiableList(true, builder.onSkipForward);
        this.onSkipBackward = createUnmodifiableList(true, builder.onSkipBackward);
        this.onPlaybackSpeedChange = createUnmodifiableList(true, builder.onPlaybackSpeedChange);
        this.onRemotePlaybackActivated = builder.onRemotePlaybackActivated == null ? null : createUnmodifiableList(true, builder.onRemotePlaybackActivated);
        this.onRemotePlaybackDeactivated = builder.onRemotePlaybackDeactivated != null ? createUnmodifiableList(true, builder.onRemotePlaybackDeactivated) : null;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetMediaActionsMethod(Queue queue, Boolean bool, List<Method> list, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5, List<Method> list6, List<Method> list7, List<Method> list8, List<Method> list9, List<Method> list10, List<Method> list11, List<Method> list12, List<Method> list13, List<Method> list14, List<Method> list15, List<Method> list16, List<Method> list17, List<Method> list18, @Nullable List<Method> list19, @Nullable List<Method> list20) {
        this.queue = queue;
        this.forced = bool;
        this.onPause = list;
        this.onResume = list2;
        this.onNext = list3;
        this.onPrevious = list4;
        this.onRepeatOff = list5;
        this.onRepeatOne = list6;
        this.onRepeatAll = list7;
        this.onShuffleOn = list8;
        this.onShuffleOff = list9;
        this.onThumbsUp = list10;
        this.onUndoThumbsUp = list11;
        this.onThumbsDown = list12;
        this.onUndoThumbsDown = list13;
        this.onVolumeChange = list14;
        this.onMoveTo = list15;
        this.onSkipForward = list16;
        this.onSkipBackward = list17;
        this.onPlaybackSpeedChange = list18;
        this.onRemotePlaybackActivated = list19;
        this.onRemotePlaybackDeactivated = list20;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        return this.forced;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onPause")
    public List<Method> onPause() {
        return this.onPause;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onResume")
    public List<Method> onResume() {
        return this.onResume;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onNext")
    public List<Method> onNext() {
        return this.onNext;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onPrevious")
    public List<Method> onPrevious() {
        return this.onPrevious;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onRepeatOff")
    public List<Method> onRepeatOff() {
        return this.onRepeatOff;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onRepeatOne")
    public List<Method> onRepeatOne() {
        return this.onRepeatOne;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onRepeatAll")
    public List<Method> onRepeatAll() {
        return this.onRepeatAll;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onShuffleOn")
    public List<Method> onShuffleOn() {
        return this.onShuffleOn;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onShuffleOff")
    public List<Method> onShuffleOff() {
        return this.onShuffleOff;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onThumbsUp")
    public List<Method> onThumbsUp() {
        return this.onThumbsUp;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onUndoThumbsUp")
    public List<Method> onUndoThumbsUp() {
        return this.onUndoThumbsUp;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onThumbsDown")
    public List<Method> onThumbsDown() {
        return this.onThumbsDown;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onUndoThumbsDown")
    public List<Method> onUndoThumbsDown() {
        return this.onUndoThumbsDown;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onVolumeChange")
    public List<Method> onVolumeChange() {
        return this.onVolumeChange;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onMoveTo")
    public List<Method> onMoveTo() {
        return this.onMoveTo;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onSkipForward")
    public List<Method> onSkipForward() {
        return this.onSkipForward;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onSkipBackward")
    public List<Method> onSkipBackward() {
        return this.onSkipBackward;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onPlaybackSpeedChange")
    public List<Method> onPlaybackSpeedChange() {
        return this.onPlaybackSpeedChange;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onRemotePlaybackActivated")
    @Nullable
    public List<Method> onRemotePlaybackActivated() {
        return this.onRemotePlaybackActivated;
    }

    @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
    @JsonProperty("onRemotePlaybackDeactivated")
    @Nullable
    public List<Method> onRemotePlaybackDeactivated() {
        return this.onRemotePlaybackDeactivated;
    }

    public final ImmutableSetMediaActionsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetMediaActionsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetMediaActionsMethod(this.queue, bool2, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPause(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPause(Iterable<? extends Method> iterable) {
        if (this.onPause == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnResume(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnResume(Iterable<? extends Method> iterable) {
        if (this.onResume == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnNext(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnNext(Iterable<? extends Method> iterable) {
        if (this.onNext == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPrevious(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPrevious(Iterable<? extends Method> iterable) {
        if (this.onPrevious == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatOff(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatOff(Iterable<? extends Method> iterable) {
        if (this.onRepeatOff == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatOne(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatOne(Iterable<? extends Method> iterable) {
        if (this.onRepeatOne == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatAll(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRepeatAll(Iterable<? extends Method> iterable) {
        if (this.onRepeatAll == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnShuffleOn(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnShuffleOn(Iterable<? extends Method> iterable) {
        if (this.onShuffleOn == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnShuffleOff(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnShuffleOff(Iterable<? extends Method> iterable) {
        if (this.onShuffleOff == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnThumbsUp(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnThumbsUp(Iterable<? extends Method> iterable) {
        if (this.onThumbsUp == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnUndoThumbsUp(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnUndoThumbsUp(Iterable<? extends Method> iterable) {
        if (this.onUndoThumbsUp == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnThumbsDown(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnThumbsDown(Iterable<? extends Method> iterable) {
        if (this.onThumbsDown == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnUndoThumbsDown(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnUndoThumbsDown(Iterable<? extends Method> iterable) {
        if (this.onUndoThumbsDown == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnVolumeChange(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnVolumeChange(Iterable<? extends Method> iterable) {
        if (this.onVolumeChange == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnMoveTo(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnMoveTo(Iterable<? extends Method> iterable) {
        if (this.onMoveTo == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnSkipForward(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnSkipForward(Iterable<? extends Method> iterable) {
        if (this.onSkipForward == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnSkipBackward(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnSkipBackward(Iterable<? extends Method> iterable) {
        if (this.onSkipBackward == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPlaybackSpeedChange(Method... methodArr) {
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnPlaybackSpeedChange(Iterable<? extends Method> iterable) {
        if (this.onPlaybackSpeedChange == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRemotePlaybackActivated, this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRemotePlaybackActivated(@Nullable Method... methodArr) {
        if (methodArr == null) {
            return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, null, this.onRemotePlaybackDeactivated);
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, Arrays.asList(methodArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRemotePlaybackActivated(@Nullable Iterable<? extends Method> iterable) {
        if (this.onRemotePlaybackActivated == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRemotePlaybackDeactivated);
    }

    public final ImmutableSetMediaActionsMethod withOnRemotePlaybackDeactivated(@Nullable Method... methodArr) {
        if (methodArr == null) {
            return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, null);
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, Arrays.asList(methodArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSetMediaActionsMethod withOnRemotePlaybackDeactivated(@Nullable Iterable<? extends Method> iterable) {
        if (this.onRemotePlaybackDeactivated == iterable) {
            return this;
        }
        return new ImmutableSetMediaActionsMethod(this.queue, this.forced, this.onPause, this.onResume, this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo, this.onSkipForward, this.onSkipBackward, this.onPlaybackSpeedChange, this.onRemotePlaybackActivated, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetMediaActionsMethod) && equalTo(0, (ImmutableSetMediaActionsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetMediaActionsMethod immutableSetMediaActionsMethod) {
        return this.queue.equals(immutableSetMediaActionsMethod.queue) && this.forced.equals(immutableSetMediaActionsMethod.forced) && this.onPause.equals(immutableSetMediaActionsMethod.onPause) && this.onResume.equals(immutableSetMediaActionsMethod.onResume) && this.onNext.equals(immutableSetMediaActionsMethod.onNext) && this.onPrevious.equals(immutableSetMediaActionsMethod.onPrevious) && this.onRepeatOff.equals(immutableSetMediaActionsMethod.onRepeatOff) && this.onRepeatOne.equals(immutableSetMediaActionsMethod.onRepeatOne) && this.onRepeatAll.equals(immutableSetMediaActionsMethod.onRepeatAll) && this.onShuffleOn.equals(immutableSetMediaActionsMethod.onShuffleOn) && this.onShuffleOff.equals(immutableSetMediaActionsMethod.onShuffleOff) && this.onThumbsUp.equals(immutableSetMediaActionsMethod.onThumbsUp) && this.onUndoThumbsUp.equals(immutableSetMediaActionsMethod.onUndoThumbsUp) && this.onThumbsDown.equals(immutableSetMediaActionsMethod.onThumbsDown) && this.onUndoThumbsDown.equals(immutableSetMediaActionsMethod.onUndoThumbsDown) && this.onVolumeChange.equals(immutableSetMediaActionsMethod.onVolumeChange) && this.onMoveTo.equals(immutableSetMediaActionsMethod.onMoveTo) && this.onSkipForward.equals(immutableSetMediaActionsMethod.onSkipForward) && this.onSkipBackward.equals(immutableSetMediaActionsMethod.onSkipBackward) && this.onPlaybackSpeedChange.equals(immutableSetMediaActionsMethod.onPlaybackSpeedChange) && equals(this.onRemotePlaybackActivated, immutableSetMediaActionsMethod.onRemotePlaybackActivated) && equals(this.onRemotePlaybackDeactivated, immutableSetMediaActionsMethod.onRemotePlaybackDeactivated);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onPause.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onResume.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onNext.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onPrevious.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onRepeatOff.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.onRepeatOne.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.onRepeatAll.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.onShuffleOn.hashCode();
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + this.onShuffleOff.hashCode();
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + this.onThumbsUp.hashCode();
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + this.onUndoThumbsUp.hashCode();
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + this.onThumbsDown.hashCode();
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + this.onUndoThumbsDown.hashCode();
        int iHashCode16 = iHashCode15 + (iHashCode15 << 5) + this.onVolumeChange.hashCode();
        int iHashCode17 = iHashCode16 + (iHashCode16 << 5) + this.onMoveTo.hashCode();
        int iHashCode18 = iHashCode17 + (iHashCode17 << 5) + this.onSkipForward.hashCode();
        int iHashCode19 = iHashCode18 + (iHashCode18 << 5) + this.onSkipBackward.hashCode();
        int iHashCode20 = iHashCode19 + (iHashCode19 << 5) + this.onPlaybackSpeedChange.hashCode();
        int iHashCode21 = iHashCode20 + (iHashCode20 << 5) + hashCode(this.onRemotePlaybackActivated);
        return iHashCode21 + (iHashCode21 << 5) + hashCode(this.onRemotePlaybackDeactivated);
    }

    public String toString() {
        return "SetMediaActionsMethod{queue=" + this.queue + ", forced=" + this.forced + ", onPause=" + this.onPause + ", onResume=" + this.onResume + ", onNext=" + this.onNext + ", onPrevious=" + this.onPrevious + ", onRepeatOff=" + this.onRepeatOff + ", onRepeatOne=" + this.onRepeatOne + ", onRepeatAll=" + this.onRepeatAll + ", onShuffleOn=" + this.onShuffleOn + ", onShuffleOff=" + this.onShuffleOff + ", onThumbsUp=" + this.onThumbsUp + ", onUndoThumbsUp=" + this.onUndoThumbsUp + ", onThumbsDown=" + this.onThumbsDown + ", onUndoThumbsDown=" + this.onUndoThumbsDown + ", onVolumeChange=" + this.onVolumeChange + ", onMoveTo=" + this.onMoveTo + ", onSkipForward=" + this.onSkipForward + ", onSkipBackward=" + this.onSkipBackward + ", onPlaybackSpeedChange=" + this.onPlaybackSpeedChange + ", onRemotePlaybackActivated=" + this.onRemotePlaybackActivated + ", onRemotePlaybackDeactivated=" + this.onRemotePlaybackDeactivated + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetMediaActionsMethod", generator = "Immutables")
    static final class Json extends SetMediaActionsMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        List<Method> onPause = Collections.emptyList();

        @Nullable
        List<Method> onResume = Collections.emptyList();

        @Nullable
        List<Method> onNext = Collections.emptyList();

        @Nullable
        List<Method> onPrevious = Collections.emptyList();

        @Nullable
        List<Method> onRepeatOff = Collections.emptyList();

        @Nullable
        List<Method> onRepeatOne = Collections.emptyList();

        @Nullable
        List<Method> onRepeatAll = Collections.emptyList();

        @Nullable
        List<Method> onShuffleOn = Collections.emptyList();

        @Nullable
        List<Method> onShuffleOff = Collections.emptyList();

        @Nullable
        List<Method> onThumbsUp = Collections.emptyList();

        @Nullable
        List<Method> onUndoThumbsUp = Collections.emptyList();

        @Nullable
        List<Method> onThumbsDown = Collections.emptyList();

        @Nullable
        List<Method> onUndoThumbsDown = Collections.emptyList();

        @Nullable
        List<Method> onVolumeChange = Collections.emptyList();

        @Nullable
        List<Method> onMoveTo = Collections.emptyList();

        @Nullable
        List<Method> onSkipForward = Collections.emptyList();

        @Nullable
        List<Method> onSkipBackward = Collections.emptyList();

        @Nullable
        List<Method> onPlaybackSpeedChange = Collections.emptyList();

        @Nullable
        List<Method> onRemotePlaybackActivated = null;

        @Nullable
        List<Method> onRemotePlaybackDeactivated = null;

        Json() {
        }

        @JsonProperty("queue")
        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        @JsonProperty("forced")
        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        @JsonProperty("onPause")
        public void setOnPause(List<Method> list) {
            this.onPause = list;
        }

        @JsonProperty("onResume")
        public void setOnResume(List<Method> list) {
            this.onResume = list;
        }

        @JsonProperty("onNext")
        public void setOnNext(List<Method> list) {
            this.onNext = list;
        }

        @JsonProperty("onPrevious")
        public void setOnPrevious(List<Method> list) {
            this.onPrevious = list;
        }

        @JsonProperty("onRepeatOff")
        public void setOnRepeatOff(List<Method> list) {
            this.onRepeatOff = list;
        }

        @JsonProperty("onRepeatOne")
        public void setOnRepeatOne(List<Method> list) {
            this.onRepeatOne = list;
        }

        @JsonProperty("onRepeatAll")
        public void setOnRepeatAll(List<Method> list) {
            this.onRepeatAll = list;
        }

        @JsonProperty("onShuffleOn")
        public void setOnShuffleOn(List<Method> list) {
            this.onShuffleOn = list;
        }

        @JsonProperty("onShuffleOff")
        public void setOnShuffleOff(List<Method> list) {
            this.onShuffleOff = list;
        }

        @JsonProperty("onThumbsUp")
        public void setOnThumbsUp(List<Method> list) {
            this.onThumbsUp = list;
        }

        @JsonProperty("onUndoThumbsUp")
        public void setOnUndoThumbsUp(List<Method> list) {
            this.onUndoThumbsUp = list;
        }

        @JsonProperty("onThumbsDown")
        public void setOnThumbsDown(List<Method> list) {
            this.onThumbsDown = list;
        }

        @JsonProperty("onUndoThumbsDown")
        public void setOnUndoThumbsDown(List<Method> list) {
            this.onUndoThumbsDown = list;
        }

        @JsonProperty("onVolumeChange")
        public void setOnVolumeChange(List<Method> list) {
            this.onVolumeChange = list;
        }

        @JsonProperty("onMoveTo")
        public void setOnMoveTo(List<Method> list) {
            this.onMoveTo = list;
        }

        @JsonProperty("onSkipForward")
        public void setOnSkipForward(List<Method> list) {
            this.onSkipForward = list;
        }

        @JsonProperty("onSkipBackward")
        public void setOnSkipBackward(List<Method> list) {
            this.onSkipBackward = list;
        }

        @JsonProperty("onPlaybackSpeedChange")
        public void setOnPlaybackSpeedChange(List<Method> list) {
            this.onPlaybackSpeedChange = list;
        }

        @JsonProperty("onRemotePlaybackActivated")
        public void setOnRemotePlaybackActivated(@Nullable List<Method> list) {
            this.onRemotePlaybackActivated = list;
        }

        @JsonProperty("onRemotePlaybackDeactivated")
        public void setOnRemotePlaybackDeactivated(@Nullable List<Method> list) {
            this.onRemotePlaybackDeactivated = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onPause() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onResume() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onNext() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onRepeatOff() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onRepeatOne() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onRepeatAll() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onShuffleOn() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onShuffleOff() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onThumbsUp() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onUndoThumbsUp() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onThumbsDown() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onUndoThumbsDown() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onVolumeChange() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onMoveTo() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onSkipForward() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onSkipBackward() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onPlaybackSpeedChange() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onRemotePlaybackActivated() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetMediaActionsMethod
        public List<Method> onRemotePlaybackDeactivated() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetMediaActionsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        List<Method> list = json.onPause;
        if (list != null) {
            builder.addAllOnPause(list);
        }
        List<Method> list2 = json.onResume;
        if (list2 != null) {
            builder.addAllOnResume(list2);
        }
        List<Method> list3 = json.onNext;
        if (list3 != null) {
            builder.addAllOnNext(list3);
        }
        List<Method> list4 = json.onPrevious;
        if (list4 != null) {
            builder.addAllOnPrevious(list4);
        }
        List<Method> list5 = json.onRepeatOff;
        if (list5 != null) {
            builder.addAllOnRepeatOff(list5);
        }
        List<Method> list6 = json.onRepeatOne;
        if (list6 != null) {
            builder.addAllOnRepeatOne(list6);
        }
        List<Method> list7 = json.onRepeatAll;
        if (list7 != null) {
            builder.addAllOnRepeatAll(list7);
        }
        List<Method> list8 = json.onShuffleOn;
        if (list8 != null) {
            builder.addAllOnShuffleOn(list8);
        }
        List<Method> list9 = json.onShuffleOff;
        if (list9 != null) {
            builder.addAllOnShuffleOff(list9);
        }
        List<Method> list10 = json.onThumbsUp;
        if (list10 != null) {
            builder.addAllOnThumbsUp(list10);
        }
        List<Method> list11 = json.onUndoThumbsUp;
        if (list11 != null) {
            builder.addAllOnUndoThumbsUp(list11);
        }
        List<Method> list12 = json.onThumbsDown;
        if (list12 != null) {
            builder.addAllOnThumbsDown(list12);
        }
        List<Method> list13 = json.onUndoThumbsDown;
        if (list13 != null) {
            builder.addAllOnUndoThumbsDown(list13);
        }
        List<Method> list14 = json.onVolumeChange;
        if (list14 != null) {
            builder.addAllOnVolumeChange(list14);
        }
        List<Method> list15 = json.onMoveTo;
        if (list15 != null) {
            builder.addAllOnMoveTo(list15);
        }
        List<Method> list16 = json.onSkipForward;
        if (list16 != null) {
            builder.addAllOnSkipForward(list16);
        }
        List<Method> list17 = json.onSkipBackward;
        if (list17 != null) {
            builder.addAllOnSkipBackward(list17);
        }
        List<Method> list18 = json.onPlaybackSpeedChange;
        if (list18 != null) {
            builder.addAllOnPlaybackSpeedChange(list18);
        }
        List<Method> list19 = json.onRemotePlaybackActivated;
        if (list19 != null) {
            builder.addAllOnRemotePlaybackActivated(list19);
        }
        List<Method> list20 = json.onRemotePlaybackDeactivated;
        if (list20 != null) {
            builder.addAllOnRemotePlaybackDeactivated(list20);
        }
        return builder.build();
    }

    public static ImmutableSetMediaActionsMethod copyOf(SetMediaActionsMethod setMediaActionsMethod) {
        if (setMediaActionsMethod instanceof ImmutableSetMediaActionsMethod) {
            return (ImmutableSetMediaActionsMethod) setMediaActionsMethod;
        }
        return builder().from(setMediaActionsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetMediaActionsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onMoveTo;
        private List<Method> onNext;
        private List<Method> onPause;
        private List<Method> onPlaybackSpeedChange;
        private List<Method> onPrevious;
        private List<Method> onRemotePlaybackActivated;
        private List<Method> onRemotePlaybackDeactivated;
        private List<Method> onRepeatAll;
        private List<Method> onRepeatOff;
        private List<Method> onRepeatOne;
        private List<Method> onResume;
        private List<Method> onShuffleOff;
        private List<Method> onShuffleOn;
        private List<Method> onSkipBackward;
        private List<Method> onSkipForward;
        private List<Method> onThumbsDown;
        private List<Method> onThumbsUp;
        private List<Method> onUndoThumbsDown;
        private List<Method> onUndoThumbsUp;
        private List<Method> onVolumeChange;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.onPause = new ArrayList();
            this.onResume = new ArrayList();
            this.onNext = new ArrayList();
            this.onPrevious = new ArrayList();
            this.onRepeatOff = new ArrayList();
            this.onRepeatOne = new ArrayList();
            this.onRepeatAll = new ArrayList();
            this.onShuffleOn = new ArrayList();
            this.onShuffleOff = new ArrayList();
            this.onThumbsUp = new ArrayList();
            this.onUndoThumbsUp = new ArrayList();
            this.onThumbsDown = new ArrayList();
            this.onUndoThumbsDown = new ArrayList();
            this.onVolumeChange = new ArrayList();
            this.onMoveTo = new ArrayList();
            this.onSkipForward = new ArrayList();
            this.onSkipBackward = new ArrayList();
            this.onPlaybackSpeedChange = new ArrayList();
            this.onRemotePlaybackActivated = null;
            this.onRemotePlaybackDeactivated = null;
        }

        public final Builder from(Method method) {
            ImmutableSetMediaActionsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetMediaActionsMethod setMediaActionsMethod) {
            ImmutableSetMediaActionsMethod.requireNonNull(setMediaActionsMethod, "instance");
            from((Object) setMediaActionsMethod);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Method) {
                Method method = (Method) obj;
                forced(method.forced());
                queue(method.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof SetMediaActionsMethod) {
                SetMediaActionsMethod setMediaActionsMethod = (SetMediaActionsMethod) obj;
                addAllOnShuffleOn(setMediaActionsMethod.onShuffleOn());
                addAllOnMoveTo(setMediaActionsMethod.onMoveTo());
                List<Method> listOnRemotePlaybackDeactivated = setMediaActionsMethod.onRemotePlaybackDeactivated();
                if (listOnRemotePlaybackDeactivated != null) {
                    addAllOnRemotePlaybackDeactivated(listOnRemotePlaybackDeactivated);
                }
                if ((j & 1) == 0) {
                    forced(setMediaActionsMethod.forced());
                    j |= 1;
                }
                addAllOnThumbsUp(setMediaActionsMethod.onThumbsUp());
                addAllOnShuffleOff(setMediaActionsMethod.onShuffleOff());
                addAllOnSkipForward(setMediaActionsMethod.onSkipForward());
                addAllOnPause(setMediaActionsMethod.onPause());
                addAllOnSkipBackward(setMediaActionsMethod.onSkipBackward());
                addAllOnVolumeChange(setMediaActionsMethod.onVolumeChange());
                addAllOnThumbsDown(setMediaActionsMethod.onThumbsDown());
                addAllOnResume(setMediaActionsMethod.onResume());
                addAllOnUndoThumbsDown(setMediaActionsMethod.onUndoThumbsDown());
                addAllOnNext(setMediaActionsMethod.onNext());
                addAllOnRepeatOff(setMediaActionsMethod.onRepeatOff());
                addAllOnUndoThumbsUp(setMediaActionsMethod.onUndoThumbsUp());
                addAllOnPlaybackSpeedChange(setMediaActionsMethod.onPlaybackSpeedChange());
                List<Method> listOnRemotePlaybackActivated = setMediaActionsMethod.onRemotePlaybackActivated();
                if (listOnRemotePlaybackActivated != null) {
                    addAllOnRemotePlaybackActivated(listOnRemotePlaybackActivated);
                }
                addAllOnRepeatOne(setMediaActionsMethod.onRepeatOne());
                addAllOnPrevious(setMediaActionsMethod.onPrevious());
                addAllOnRepeatAll(setMediaActionsMethod.onRepeatAll());
                if ((j & 2) == 0) {
                    queue(setMediaActionsMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetMediaActionsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetMediaActionsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addOnPause(Method method) {
            this.onPause.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPause element"));
            return this;
        }

        public final Builder addOnPause(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPause.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPause element"));
            }
            return this;
        }

        @JsonProperty("onPause")
        public final Builder onPause(Iterable<? extends Method> iterable) {
            this.onPause.clear();
            return addAllOnPause(iterable);
        }

        public final Builder addAllOnPause(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onPause.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onPause element"));
            }
            return this;
        }

        public final Builder addOnResume(Method method) {
            this.onResume.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onResume element"));
            return this;
        }

        public final Builder addOnResume(Method... methodArr) {
            for (Method method : methodArr) {
                this.onResume.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onResume element"));
            }
            return this;
        }

        @JsonProperty("onResume")
        public final Builder onResume(Iterable<? extends Method> iterable) {
            this.onResume.clear();
            return addAllOnResume(iterable);
        }

        public final Builder addAllOnResume(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onResume.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onResume element"));
            }
            return this;
        }

        public final Builder addOnNext(Method method) {
            this.onNext.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onNext element"));
            return this;
        }

        public final Builder addOnNext(Method... methodArr) {
            for (Method method : methodArr) {
                this.onNext.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onNext element"));
            }
            return this;
        }

        @JsonProperty("onNext")
        public final Builder onNext(Iterable<? extends Method> iterable) {
            this.onNext.clear();
            return addAllOnNext(iterable);
        }

        public final Builder addAllOnNext(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onNext.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onNext element"));
            }
            return this;
        }

        public final Builder addOnPrevious(Method method) {
            this.onPrevious.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPrevious element"));
            return this;
        }

        public final Builder addOnPrevious(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPrevious.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPrevious element"));
            }
            return this;
        }

        @JsonProperty("onPrevious")
        public final Builder onPrevious(Iterable<? extends Method> iterable) {
            this.onPrevious.clear();
            return addAllOnPrevious(iterable);
        }

        public final Builder addAllOnPrevious(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onPrevious.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onPrevious element"));
            }
            return this;
        }

        public final Builder addOnRepeatOff(Method method) {
            this.onRepeatOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatOff element"));
            return this;
        }

        public final Builder addOnRepeatOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatOff element"));
            }
            return this;
        }

        @JsonProperty("onRepeatOff")
        public final Builder onRepeatOff(Iterable<? extends Method> iterable) {
            this.onRepeatOff.clear();
            return addAllOnRepeatOff(iterable);
        }

        public final Builder addAllOnRepeatOff(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRepeatOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onRepeatOff element"));
            }
            return this;
        }

        public final Builder addOnRepeatOne(Method method) {
            this.onRepeatOne.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatOne element"));
            return this;
        }

        public final Builder addOnRepeatOne(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatOne.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatOne element"));
            }
            return this;
        }

        @JsonProperty("onRepeatOne")
        public final Builder onRepeatOne(Iterable<? extends Method> iterable) {
            this.onRepeatOne.clear();
            return addAllOnRepeatOne(iterable);
        }

        public final Builder addAllOnRepeatOne(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRepeatOne.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onRepeatOne element"));
            }
            return this;
        }

        public final Builder addOnRepeatAll(Method method) {
            this.onRepeatAll.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatAll element"));
            return this;
        }

        public final Builder addOnRepeatAll(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatAll.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRepeatAll element"));
            }
            return this;
        }

        @JsonProperty("onRepeatAll")
        public final Builder onRepeatAll(Iterable<? extends Method> iterable) {
            this.onRepeatAll.clear();
            return addAllOnRepeatAll(iterable);
        }

        public final Builder addAllOnRepeatAll(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRepeatAll.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onRepeatAll element"));
            }
            return this;
        }

        public final Builder addOnShuffleOn(Method method) {
            this.onShuffleOn.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onShuffleOn element"));
            return this;
        }

        public final Builder addOnShuffleOn(Method... methodArr) {
            for (Method method : methodArr) {
                this.onShuffleOn.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onShuffleOn element"));
            }
            return this;
        }

        @JsonProperty("onShuffleOn")
        public final Builder onShuffleOn(Iterable<? extends Method> iterable) {
            this.onShuffleOn.clear();
            return addAllOnShuffleOn(iterable);
        }

        public final Builder addAllOnShuffleOn(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onShuffleOn.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onShuffleOn element"));
            }
            return this;
        }

        public final Builder addOnShuffleOff(Method method) {
            this.onShuffleOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onShuffleOff element"));
            return this;
        }

        public final Builder addOnShuffleOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onShuffleOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onShuffleOff element"));
            }
            return this;
        }

        @JsonProperty("onShuffleOff")
        public final Builder onShuffleOff(Iterable<? extends Method> iterable) {
            this.onShuffleOff.clear();
            return addAllOnShuffleOff(iterable);
        }

        public final Builder addAllOnShuffleOff(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onShuffleOff.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onShuffleOff element"));
            }
            return this;
        }

        public final Builder addOnThumbsUp(Method method) {
            this.onThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onThumbsUp element"));
            return this;
        }

        public final Builder addOnThumbsUp(Method... methodArr) {
            for (Method method : methodArr) {
                this.onThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onThumbsUp element"));
            }
            return this;
        }

        @JsonProperty("onThumbsUp")
        public final Builder onThumbsUp(Iterable<? extends Method> iterable) {
            this.onThumbsUp.clear();
            return addAllOnThumbsUp(iterable);
        }

        public final Builder addAllOnThumbsUp(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onThumbsUp element"));
            }
            return this;
        }

        public final Builder addOnUndoThumbsUp(Method method) {
            this.onUndoThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onUndoThumbsUp element"));
            return this;
        }

        public final Builder addOnUndoThumbsUp(Method... methodArr) {
            for (Method method : methodArr) {
                this.onUndoThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onUndoThumbsUp element"));
            }
            return this;
        }

        @JsonProperty("onUndoThumbsUp")
        public final Builder onUndoThumbsUp(Iterable<? extends Method> iterable) {
            this.onUndoThumbsUp.clear();
            return addAllOnUndoThumbsUp(iterable);
        }

        public final Builder addAllOnUndoThumbsUp(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onUndoThumbsUp.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onUndoThumbsUp element"));
            }
            return this;
        }

        public final Builder addOnThumbsDown(Method method) {
            this.onThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onThumbsDown element"));
            return this;
        }

        public final Builder addOnThumbsDown(Method... methodArr) {
            for (Method method : methodArr) {
                this.onThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onThumbsDown element"));
            }
            return this;
        }

        @JsonProperty("onThumbsDown")
        public final Builder onThumbsDown(Iterable<? extends Method> iterable) {
            this.onThumbsDown.clear();
            return addAllOnThumbsDown(iterable);
        }

        public final Builder addAllOnThumbsDown(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onThumbsDown element"));
            }
            return this;
        }

        public final Builder addOnUndoThumbsDown(Method method) {
            this.onUndoThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onUndoThumbsDown element"));
            return this;
        }

        public final Builder addOnUndoThumbsDown(Method... methodArr) {
            for (Method method : methodArr) {
                this.onUndoThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onUndoThumbsDown element"));
            }
            return this;
        }

        @JsonProperty("onUndoThumbsDown")
        public final Builder onUndoThumbsDown(Iterable<? extends Method> iterable) {
            this.onUndoThumbsDown.clear();
            return addAllOnUndoThumbsDown(iterable);
        }

        public final Builder addAllOnUndoThumbsDown(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onUndoThumbsDown.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onUndoThumbsDown element"));
            }
            return this;
        }

        public final Builder addOnVolumeChange(Method method) {
            this.onVolumeChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onVolumeChange element"));
            return this;
        }

        public final Builder addOnVolumeChange(Method... methodArr) {
            for (Method method : methodArr) {
                this.onVolumeChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onVolumeChange element"));
            }
            return this;
        }

        @JsonProperty("onVolumeChange")
        public final Builder onVolumeChange(Iterable<? extends Method> iterable) {
            this.onVolumeChange.clear();
            return addAllOnVolumeChange(iterable);
        }

        public final Builder addAllOnVolumeChange(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onVolumeChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onVolumeChange element"));
            }
            return this;
        }

        public final Builder addOnMoveTo(Method method) {
            this.onMoveTo.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onMoveTo element"));
            return this;
        }

        public final Builder addOnMoveTo(Method... methodArr) {
            for (Method method : methodArr) {
                this.onMoveTo.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onMoveTo element"));
            }
            return this;
        }

        @JsonProperty("onMoveTo")
        public final Builder onMoveTo(Iterable<? extends Method> iterable) {
            this.onMoveTo.clear();
            return addAllOnMoveTo(iterable);
        }

        public final Builder addAllOnMoveTo(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onMoveTo.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onMoveTo element"));
            }
            return this;
        }

        public final Builder addOnSkipForward(Method method) {
            this.onSkipForward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onSkipForward element"));
            return this;
        }

        public final Builder addOnSkipForward(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSkipForward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onSkipForward element"));
            }
            return this;
        }

        @JsonProperty("onSkipForward")
        public final Builder onSkipForward(Iterable<? extends Method> iterable) {
            this.onSkipForward.clear();
            return addAllOnSkipForward(iterable);
        }

        public final Builder addAllOnSkipForward(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSkipForward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onSkipForward element"));
            }
            return this;
        }

        public final Builder addOnSkipBackward(Method method) {
            this.onSkipBackward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onSkipBackward element"));
            return this;
        }

        public final Builder addOnSkipBackward(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSkipBackward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onSkipBackward element"));
            }
            return this;
        }

        @JsonProperty("onSkipBackward")
        public final Builder onSkipBackward(Iterable<? extends Method> iterable) {
            this.onSkipBackward.clear();
            return addAllOnSkipBackward(iterable);
        }

        public final Builder addAllOnSkipBackward(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSkipBackward.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onSkipBackward element"));
            }
            return this;
        }

        public final Builder addOnPlaybackSpeedChange(Method method) {
            this.onPlaybackSpeedChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPlaybackSpeedChange element"));
            return this;
        }

        public final Builder addOnPlaybackSpeedChange(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPlaybackSpeedChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onPlaybackSpeedChange element"));
            }
            return this;
        }

        @JsonProperty("onPlaybackSpeedChange")
        public final Builder onPlaybackSpeedChange(Iterable<? extends Method> iterable) {
            this.onPlaybackSpeedChange.clear();
            return addAllOnPlaybackSpeedChange(iterable);
        }

        public final Builder addAllOnPlaybackSpeedChange(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onPlaybackSpeedChange.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onPlaybackSpeedChange element"));
            }
            return this;
        }

        public final Builder addOnRemotePlaybackActivated(Method method) {
            if (this.onRemotePlaybackActivated == null) {
                this.onRemotePlaybackActivated = new ArrayList();
            }
            this.onRemotePlaybackActivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRemotePlaybackActivated element"));
            return this;
        }

        public final Builder addOnRemotePlaybackActivated(Method... methodArr) {
            if (this.onRemotePlaybackActivated == null) {
                this.onRemotePlaybackActivated = new ArrayList();
            }
            for (Method method : methodArr) {
                this.onRemotePlaybackActivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRemotePlaybackActivated element"));
            }
            return this;
        }

        @JsonProperty("onRemotePlaybackActivated")
        public final Builder onRemotePlaybackActivated(@Nullable Iterable<? extends Method> iterable) {
            if (iterable == null) {
                this.onRemotePlaybackActivated = null;
                return this;
            }
            this.onRemotePlaybackActivated = new ArrayList();
            return addAllOnRemotePlaybackActivated(iterable);
        }

        public final Builder addAllOnRemotePlaybackActivated(Iterable<? extends Method> iterable) {
            ImmutableSetMediaActionsMethod.requireNonNull(iterable, "onRemotePlaybackActivated element");
            if (this.onRemotePlaybackActivated == null) {
                this.onRemotePlaybackActivated = new ArrayList();
            }
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRemotePlaybackActivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onRemotePlaybackActivated element"));
            }
            return this;
        }

        public final Builder addOnRemotePlaybackDeactivated(Method method) {
            if (this.onRemotePlaybackDeactivated == null) {
                this.onRemotePlaybackDeactivated = new ArrayList();
            }
            this.onRemotePlaybackDeactivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRemotePlaybackDeactivated element"));
            return this;
        }

        public final Builder addOnRemotePlaybackDeactivated(Method... methodArr) {
            if (this.onRemotePlaybackDeactivated == null) {
                this.onRemotePlaybackDeactivated = new ArrayList();
            }
            for (Method method : methodArr) {
                this.onRemotePlaybackDeactivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(method, "onRemotePlaybackDeactivated element"));
            }
            return this;
        }

        @JsonProperty("onRemotePlaybackDeactivated")
        public final Builder onRemotePlaybackDeactivated(@Nullable Iterable<? extends Method> iterable) {
            if (iterable == null) {
                this.onRemotePlaybackDeactivated = null;
                return this;
            }
            this.onRemotePlaybackDeactivated = new ArrayList();
            return addAllOnRemotePlaybackDeactivated(iterable);
        }

        public final Builder addAllOnRemotePlaybackDeactivated(Iterable<? extends Method> iterable) {
            ImmutableSetMediaActionsMethod.requireNonNull(iterable, "onRemotePlaybackDeactivated element");
            if (this.onRemotePlaybackDeactivated == null) {
                this.onRemotePlaybackDeactivated = new ArrayList();
            }
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRemotePlaybackDeactivated.add((Method) ImmutableSetMediaActionsMethod.requireNonNull(it.next(), "onRemotePlaybackDeactivated element"));
            }
            return this;
        }

        public ImmutableSetMediaActionsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetMediaActionsMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetMediaActionsMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
