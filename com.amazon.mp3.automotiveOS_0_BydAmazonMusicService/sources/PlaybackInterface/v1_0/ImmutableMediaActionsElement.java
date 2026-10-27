package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "MediaActionsElement", generator = "Immutables")
public final class ImmutableMediaActionsElement extends MediaActionsElement {
    private final List<Method> onMoveTo;
    private final List<Method> onNext;
    private final List<Method> onPrevious;
    private final List<Method> onRepeatAll;
    private final List<Method> onRepeatOff;
    private final List<Method> onRepeatOne;
    private final List<Method> onShuffleOff;
    private final List<Method> onShuffleOn;
    private final List<Method> onThumbsDown;
    private final List<Method> onThumbsUp;
    private final List<Method> onUndoThumbsDown;
    private final List<Method> onUndoThumbsUp;
    private final List<Method> onVolumeChange;

    private ImmutableMediaActionsElement(List<Method> list, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5, List<Method> list6, List<Method> list7, List<Method> list8, List<Method> list9, List<Method> list10, List<Method> list11, List<Method> list12, List<Method> list13) {
        this.onNext = list;
        this.onPrevious = list2;
        this.onRepeatOff = list3;
        this.onRepeatOne = list4;
        this.onRepeatAll = list5;
        this.onShuffleOn = list6;
        this.onShuffleOff = list7;
        this.onThumbsUp = list8;
        this.onUndoThumbsUp = list9;
        this.onThumbsDown = list10;
        this.onUndoThumbsDown = list11;
        this.onVolumeChange = list12;
        this.onMoveTo = list13;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onNext")
    public List<Method> onNext() {
        return this.onNext;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onPrevious")
    public List<Method> onPrevious() {
        return this.onPrevious;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onRepeatOff")
    public List<Method> onRepeatOff() {
        return this.onRepeatOff;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onRepeatOne")
    public List<Method> onRepeatOne() {
        return this.onRepeatOne;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onRepeatAll")
    public List<Method> onRepeatAll() {
        return this.onRepeatAll;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onShuffleOn")
    public List<Method> onShuffleOn() {
        return this.onShuffleOn;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onShuffleOff")
    public List<Method> onShuffleOff() {
        return this.onShuffleOff;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onThumbsUp")
    public List<Method> onThumbsUp() {
        return this.onThumbsUp;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onUndoThumbsUp")
    public List<Method> onUndoThumbsUp() {
        return this.onUndoThumbsUp;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onThumbsDown")
    public List<Method> onThumbsDown() {
        return this.onThumbsDown;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onUndoThumbsDown")
    public List<Method> onUndoThumbsDown() {
        return this.onUndoThumbsDown;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onVolumeChange")
    public List<Method> onVolumeChange() {
        return this.onVolumeChange;
    }

    @Override // PlaybackInterface.v1_0.MediaActionsElement
    @JsonProperty("onMoveTo")
    public List<Method> onMoveTo() {
        return this.onMoveTo;
    }

    public final ImmutableMediaActionsElement withOnNext(Method... methodArr) {
        return new ImmutableMediaActionsElement(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnNext(Iterable<? extends Method> iterable) {
        return this.onNext == iterable ? this : new ImmutableMediaActionsElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnPrevious(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnPrevious(Iterable<? extends Method> iterable) {
        if (this.onPrevious == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatOff(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatOff(Iterable<? extends Method> iterable) {
        if (this.onRepeatOff == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatOne(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatOne(Iterable<? extends Method> iterable) {
        if (this.onRepeatOne == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatAll(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnRepeatAll(Iterable<? extends Method> iterable) {
        if (this.onRepeatAll == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnShuffleOn(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnShuffleOn(Iterable<? extends Method> iterable) {
        if (this.onShuffleOn == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnShuffleOff(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnShuffleOff(Iterable<? extends Method> iterable) {
        if (this.onShuffleOff == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnThumbsUp(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnThumbsUp(Iterable<? extends Method> iterable) {
        if (this.onThumbsUp == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnUndoThumbsUp(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnUndoThumbsUp(Iterable<? extends Method> iterable) {
        if (this.onUndoThumbsUp == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnThumbsDown(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnThumbsDown(Iterable<? extends Method> iterable) {
        if (this.onThumbsDown == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onUndoThumbsDown, this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnUndoThumbsDown(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnUndoThumbsDown(Iterable<? extends Method> iterable) {
        if (this.onUndoThumbsDown == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onVolumeChange, this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnVolumeChange(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnVolumeChange(Iterable<? extends Method> iterable) {
        if (this.onVolumeChange == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onMoveTo);
    }

    public final ImmutableMediaActionsElement withOnMoveTo(Method... methodArr) {
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableMediaActionsElement withOnMoveTo(Iterable<? extends Method> iterable) {
        if (this.onMoveTo == iterable) {
            return this;
        }
        return new ImmutableMediaActionsElement(this.onNext, this.onPrevious, this.onRepeatOff, this.onRepeatOne, this.onRepeatAll, this.onShuffleOn, this.onShuffleOff, this.onThumbsUp, this.onUndoThumbsUp, this.onThumbsDown, this.onUndoThumbsDown, this.onVolumeChange, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaActionsElement) && equalTo(0, (ImmutableMediaActionsElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaActionsElement immutableMediaActionsElement) {
        return this.onNext.equals(immutableMediaActionsElement.onNext) && this.onPrevious.equals(immutableMediaActionsElement.onPrevious) && this.onRepeatOff.equals(immutableMediaActionsElement.onRepeatOff) && this.onRepeatOne.equals(immutableMediaActionsElement.onRepeatOne) && this.onRepeatAll.equals(immutableMediaActionsElement.onRepeatAll) && this.onShuffleOn.equals(immutableMediaActionsElement.onShuffleOn) && this.onShuffleOff.equals(immutableMediaActionsElement.onShuffleOff) && this.onThumbsUp.equals(immutableMediaActionsElement.onThumbsUp) && this.onUndoThumbsUp.equals(immutableMediaActionsElement.onUndoThumbsUp) && this.onThumbsDown.equals(immutableMediaActionsElement.onThumbsDown) && this.onUndoThumbsDown.equals(immutableMediaActionsElement.onUndoThumbsDown) && this.onVolumeChange.equals(immutableMediaActionsElement.onVolumeChange) && this.onMoveTo.equals(immutableMediaActionsElement.onMoveTo);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onNext.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onPrevious.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onRepeatOff.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onRepeatOne.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onRepeatAll.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onShuffleOn.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onShuffleOff.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.onThumbsUp.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.onUndoThumbsUp.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.onThumbsDown.hashCode();
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + this.onUndoThumbsDown.hashCode();
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + this.onVolumeChange.hashCode();
        return iHashCode12 + (iHashCode12 << 5) + this.onMoveTo.hashCode();
    }

    public String toString() {
        return "MediaActionsElement{onNext=" + this.onNext + ", onPrevious=" + this.onPrevious + ", onRepeatOff=" + this.onRepeatOff + ", onRepeatOne=" + this.onRepeatOne + ", onRepeatAll=" + this.onRepeatAll + ", onShuffleOn=" + this.onShuffleOn + ", onShuffleOff=" + this.onShuffleOff + ", onThumbsUp=" + this.onThumbsUp + ", onUndoThumbsUp=" + this.onUndoThumbsUp + ", onThumbsDown=" + this.onThumbsDown + ", onUndoThumbsDown=" + this.onUndoThumbsDown + ", onVolumeChange=" + this.onVolumeChange + ", onMoveTo=" + this.onMoveTo + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaActionsElement", generator = "Immutables")
    static final class Json extends MediaActionsElement {

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

        Json() {
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

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onNext() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onRepeatOff() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onRepeatOne() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onRepeatAll() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onShuffleOn() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onShuffleOff() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onThumbsUp() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onUndoThumbsUp() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onThumbsDown() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onUndoThumbsDown() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onVolumeChange() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaActionsElement
        public List<Method> onMoveTo() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaActionsElement fromJson(Json json) {
        Builder builder = builder();
        List<Method> list = json.onNext;
        if (list != null) {
            builder.addAllOnNext(list);
        }
        List<Method> list2 = json.onPrevious;
        if (list2 != null) {
            builder.addAllOnPrevious(list2);
        }
        List<Method> list3 = json.onRepeatOff;
        if (list3 != null) {
            builder.addAllOnRepeatOff(list3);
        }
        List<Method> list4 = json.onRepeatOne;
        if (list4 != null) {
            builder.addAllOnRepeatOne(list4);
        }
        List<Method> list5 = json.onRepeatAll;
        if (list5 != null) {
            builder.addAllOnRepeatAll(list5);
        }
        List<Method> list6 = json.onShuffleOn;
        if (list6 != null) {
            builder.addAllOnShuffleOn(list6);
        }
        List<Method> list7 = json.onShuffleOff;
        if (list7 != null) {
            builder.addAllOnShuffleOff(list7);
        }
        List<Method> list8 = json.onThumbsUp;
        if (list8 != null) {
            builder.addAllOnThumbsUp(list8);
        }
        List<Method> list9 = json.onUndoThumbsUp;
        if (list9 != null) {
            builder.addAllOnUndoThumbsUp(list9);
        }
        List<Method> list10 = json.onThumbsDown;
        if (list10 != null) {
            builder.addAllOnThumbsDown(list10);
        }
        List<Method> list11 = json.onUndoThumbsDown;
        if (list11 != null) {
            builder.addAllOnUndoThumbsDown(list11);
        }
        List<Method> list12 = json.onVolumeChange;
        if (list12 != null) {
            builder.addAllOnVolumeChange(list12);
        }
        List<Method> list13 = json.onMoveTo;
        if (list13 != null) {
            builder.addAllOnMoveTo(list13);
        }
        return builder.build();
    }

    public static ImmutableMediaActionsElement copyOf(MediaActionsElement mediaActionsElement) {
        if (mediaActionsElement instanceof ImmutableMediaActionsElement) {
            return (ImmutableMediaActionsElement) mediaActionsElement;
        }
        return builder().from(mediaActionsElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaActionsElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<Method> onMoveTo;
        private List<Method> onNext;
        private List<Method> onPrevious;
        private List<Method> onRepeatAll;
        private List<Method> onRepeatOff;
        private List<Method> onRepeatOne;
        private List<Method> onShuffleOff;
        private List<Method> onShuffleOn;
        private List<Method> onThumbsDown;
        private List<Method> onThumbsUp;
        private List<Method> onUndoThumbsDown;
        private List<Method> onUndoThumbsUp;
        private List<Method> onVolumeChange;

        private Builder() {
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
        }

        public final Builder from(MediaActionsElement mediaActionsElement) {
            ImmutableMediaActionsElement.requireNonNull(mediaActionsElement, "instance");
            addAllOnNext(mediaActionsElement.onNext());
            addAllOnPrevious(mediaActionsElement.onPrevious());
            addAllOnRepeatOff(mediaActionsElement.onRepeatOff());
            addAllOnRepeatOne(mediaActionsElement.onRepeatOne());
            addAllOnRepeatAll(mediaActionsElement.onRepeatAll());
            addAllOnShuffleOn(mediaActionsElement.onShuffleOn());
            addAllOnShuffleOff(mediaActionsElement.onShuffleOff());
            addAllOnThumbsUp(mediaActionsElement.onThumbsUp());
            addAllOnUndoThumbsUp(mediaActionsElement.onUndoThumbsUp());
            addAllOnThumbsDown(mediaActionsElement.onThumbsDown());
            addAllOnUndoThumbsDown(mediaActionsElement.onUndoThumbsDown());
            addAllOnVolumeChange(mediaActionsElement.onVolumeChange());
            addAllOnMoveTo(mediaActionsElement.onMoveTo());
            return this;
        }

        public final Builder addOnNext(Method method) {
            this.onNext.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onNext element"));
            return this;
        }

        public final Builder addOnNext(Method... methodArr) {
            for (Method method : methodArr) {
                this.onNext.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onNext element"));
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
                this.onNext.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onNext element"));
            }
            return this;
        }

        public final Builder addOnPrevious(Method method) {
            this.onPrevious.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onPrevious element"));
            return this;
        }

        public final Builder addOnPrevious(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPrevious.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onPrevious element"));
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
                this.onPrevious.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onPrevious element"));
            }
            return this;
        }

        public final Builder addOnRepeatOff(Method method) {
            this.onRepeatOff.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatOff element"));
            return this;
        }

        public final Builder addOnRepeatOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatOff.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatOff element"));
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
                this.onRepeatOff.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onRepeatOff element"));
            }
            return this;
        }

        public final Builder addOnRepeatOne(Method method) {
            this.onRepeatOne.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatOne element"));
            return this;
        }

        public final Builder addOnRepeatOne(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatOne.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatOne element"));
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
                this.onRepeatOne.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onRepeatOne element"));
            }
            return this;
        }

        public final Builder addOnRepeatAll(Method method) {
            this.onRepeatAll.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatAll element"));
            return this;
        }

        public final Builder addOnRepeatAll(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRepeatAll.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onRepeatAll element"));
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
                this.onRepeatAll.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onRepeatAll element"));
            }
            return this;
        }

        public final Builder addOnShuffleOn(Method method) {
            this.onShuffleOn.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onShuffleOn element"));
            return this;
        }

        public final Builder addOnShuffleOn(Method... methodArr) {
            for (Method method : methodArr) {
                this.onShuffleOn.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onShuffleOn element"));
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
                this.onShuffleOn.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onShuffleOn element"));
            }
            return this;
        }

        public final Builder addOnShuffleOff(Method method) {
            this.onShuffleOff.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onShuffleOff element"));
            return this;
        }

        public final Builder addOnShuffleOff(Method... methodArr) {
            for (Method method : methodArr) {
                this.onShuffleOff.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onShuffleOff element"));
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
                this.onShuffleOff.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onShuffleOff element"));
            }
            return this;
        }

        public final Builder addOnThumbsUp(Method method) {
            this.onThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onThumbsUp element"));
            return this;
        }

        public final Builder addOnThumbsUp(Method... methodArr) {
            for (Method method : methodArr) {
                this.onThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onThumbsUp element"));
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
                this.onThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onThumbsUp element"));
            }
            return this;
        }

        public final Builder addOnUndoThumbsUp(Method method) {
            this.onUndoThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onUndoThumbsUp element"));
            return this;
        }

        public final Builder addOnUndoThumbsUp(Method... methodArr) {
            for (Method method : methodArr) {
                this.onUndoThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onUndoThumbsUp element"));
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
                this.onUndoThumbsUp.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onUndoThumbsUp element"));
            }
            return this;
        }

        public final Builder addOnThumbsDown(Method method) {
            this.onThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onThumbsDown element"));
            return this;
        }

        public final Builder addOnThumbsDown(Method... methodArr) {
            for (Method method : methodArr) {
                this.onThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onThumbsDown element"));
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
                this.onThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onThumbsDown element"));
            }
            return this;
        }

        public final Builder addOnUndoThumbsDown(Method method) {
            this.onUndoThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onUndoThumbsDown element"));
            return this;
        }

        public final Builder addOnUndoThumbsDown(Method... methodArr) {
            for (Method method : methodArr) {
                this.onUndoThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onUndoThumbsDown element"));
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
                this.onUndoThumbsDown.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onUndoThumbsDown element"));
            }
            return this;
        }

        public final Builder addOnVolumeChange(Method method) {
            this.onVolumeChange.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onVolumeChange element"));
            return this;
        }

        public final Builder addOnVolumeChange(Method... methodArr) {
            for (Method method : methodArr) {
                this.onVolumeChange.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onVolumeChange element"));
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
                this.onVolumeChange.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onVolumeChange element"));
            }
            return this;
        }

        public final Builder addOnMoveTo(Method method) {
            this.onMoveTo.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onMoveTo element"));
            return this;
        }

        public final Builder addOnMoveTo(Method... methodArr) {
            for (Method method : methodArr) {
                this.onMoveTo.add((Method) ImmutableMediaActionsElement.requireNonNull(method, "onMoveTo element"));
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
                this.onMoveTo.add((Method) ImmutableMediaActionsElement.requireNonNull(it.next(), "onMoveTo element"));
            }
            return this;
        }

        public ImmutableMediaActionsElement build() {
            return new ImmutableMediaActionsElement(ImmutableMediaActionsElement.createUnmodifiableList(true, this.onNext), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onPrevious), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onRepeatOff), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onRepeatOne), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onRepeatAll), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onShuffleOn), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onShuffleOff), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onThumbsUp), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onUndoThumbsUp), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onThumbsDown), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onUndoThumbsDown), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onVolumeChange), ImmutableMediaActionsElement.createUnmodifiableList(true, this.onMoveTo));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
