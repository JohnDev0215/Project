package PlaybackInterface.v1_0;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "MediaControlStatesElement", generator = "Immutables")
public final class ImmutableMediaControlStatesElement extends MediaControlStatesElement {

    @Nullable
    private final ActiveQueuesElement activeQueues;

    @Nullable
    private final ActiveQueuesControlStateElement activeQueuesTV;

    @Nullable
    private final FastForwardControlStateElement fastForward;

    @Nullable
    private final NextControlStateElement next;

    @Nullable
    private final PlayControlStateElement play;

    @Nullable
    private final PlaybackSpeedControlStateElement playbackSpeed;

    @Nullable
    private final PreviousControlStateElement previous;

    @Nullable
    private final RepeatControlStateElement repeat;

    @Nullable
    private final RewindControlStateElement rewind;

    @Nullable
    private final ScrubControlStateElement scrub;

    @Nullable
    private final ShuffleControlStateElement shuffle;

    @Nullable
    private final SkipBackwardControlStateElement skipBackward;

    @Nullable
    private final SkipForwardControlStateElement skipForward;

    @Nullable
    private final SleepTimerControlStateElement sleepTimer;

    @Nullable
    private final ThumbsDownControlStateElement thumbsDown;

    @Nullable
    private final ThumbsUpControlStateElement thumbsUp;

    @Nullable
    private final VisualPlayQueueControlStateElement visualPlayQueue;

    @Nullable
    private final VolumeControlStateElement volume;

    private ImmutableMediaControlStatesElement(@Nullable PlayControlStateElement playControlStateElement, @Nullable NextControlStateElement nextControlStateElement, @Nullable SkipForwardControlStateElement skipForwardControlStateElement, @Nullable SkipBackwardControlStateElement skipBackwardControlStateElement, @Nullable RewindControlStateElement rewindControlStateElement, @Nullable FastForwardControlStateElement fastForwardControlStateElement, @Nullable PlaybackSpeedControlStateElement playbackSpeedControlStateElement, @Nullable SleepTimerControlStateElement sleepTimerControlStateElement, @Nullable PreviousControlStateElement previousControlStateElement, @Nullable ThumbsUpControlStateElement thumbsUpControlStateElement, @Nullable ThumbsDownControlStateElement thumbsDownControlStateElement, @Nullable RepeatControlStateElement repeatControlStateElement, @Nullable ShuffleControlStateElement shuffleControlStateElement, @Nullable ScrubControlStateElement scrubControlStateElement, @Nullable ActiveQueuesControlStateElement activeQueuesControlStateElement, @Nullable VisualPlayQueueControlStateElement visualPlayQueueControlStateElement, @Nullable ActiveQueuesElement activeQueuesElement, @Nullable VolumeControlStateElement volumeControlStateElement) {
        this.play = playControlStateElement;
        this.next = nextControlStateElement;
        this.skipForward = skipForwardControlStateElement;
        this.skipBackward = skipBackwardControlStateElement;
        this.rewind = rewindControlStateElement;
        this.fastForward = fastForwardControlStateElement;
        this.playbackSpeed = playbackSpeedControlStateElement;
        this.sleepTimer = sleepTimerControlStateElement;
        this.previous = previousControlStateElement;
        this.thumbsUp = thumbsUpControlStateElement;
        this.thumbsDown = thumbsDownControlStateElement;
        this.repeat = repeatControlStateElement;
        this.shuffle = shuffleControlStateElement;
        this.scrub = scrubControlStateElement;
        this.activeQueuesTV = activeQueuesControlStateElement;
        this.visualPlayQueue = visualPlayQueueControlStateElement;
        this.activeQueues = activeQueuesElement;
        this.volume = volumeControlStateElement;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty(PageUriUtils.DO_PLAY_VALUE)
    @Nullable
    public PlayControlStateElement play() {
        return this.play;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("next")
    @Nullable
    public NextControlStateElement next() {
        return this.next;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("skipForward")
    @Nullable
    public SkipForwardControlStateElement skipForward() {
        return this.skipForward;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("skipBackward")
    @Nullable
    public SkipBackwardControlStateElement skipBackward() {
        return this.skipBackward;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("rewind")
    @Nullable
    public RewindControlStateElement rewind() {
        return this.rewind;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("fastForward")
    @Nullable
    public FastForwardControlStateElement fastForward() {
        return this.fastForward;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("playbackSpeed")
    @Nullable
    public PlaybackSpeedControlStateElement playbackSpeed() {
        return this.playbackSpeed;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("sleepTimer")
    @Nullable
    public SleepTimerControlStateElement sleepTimer() {
        return this.sleepTimer;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("previous")
    @Nullable
    public PreviousControlStateElement previous() {
        return this.previous;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("thumbsUp")
    @Nullable
    public ThumbsUpControlStateElement thumbsUp() {
        return this.thumbsUp;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("thumbsDown")
    @Nullable
    public ThumbsDownControlStateElement thumbsDown() {
        return this.thumbsDown;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("repeat")
    @Nullable
    public RepeatControlStateElement repeat() {
        return this.repeat;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("shuffle")
    @Nullable
    public ShuffleControlStateElement shuffle() {
        return this.shuffle;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("scrub")
    @Nullable
    public ScrubControlStateElement scrub() {
        return this.scrub;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("activeQueuesTV")
    @Nullable
    public ActiveQueuesControlStateElement activeQueuesTV() {
        return this.activeQueuesTV;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("visualPlayQueue")
    @Nullable
    public VisualPlayQueueControlStateElement visualPlayQueue() {
        return this.visualPlayQueue;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty("activeQueues")
    @Nullable
    public ActiveQueuesElement activeQueues() {
        return this.activeQueues;
    }

    @Override // PlaybackInterface.v1_0.MediaControlStatesElement
    @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
    @Nullable
    public VolumeControlStateElement volume() {
        return this.volume;
    }

    public final ImmutableMediaControlStatesElement withPlay(@Nullable PlayControlStateElement playControlStateElement) {
        return this.play == playControlStateElement ? this : new ImmutableMediaControlStatesElement(playControlStateElement, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withNext(@Nullable NextControlStateElement nextControlStateElement) {
        return this.next == nextControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, nextControlStateElement, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withSkipForward(@Nullable SkipForwardControlStateElement skipForwardControlStateElement) {
        return this.skipForward == skipForwardControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, skipForwardControlStateElement, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withSkipBackward(@Nullable SkipBackwardControlStateElement skipBackwardControlStateElement) {
        return this.skipBackward == skipBackwardControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, skipBackwardControlStateElement, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withRewind(@Nullable RewindControlStateElement rewindControlStateElement) {
        return this.rewind == rewindControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, rewindControlStateElement, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withFastForward(@Nullable FastForwardControlStateElement fastForwardControlStateElement) {
        return this.fastForward == fastForwardControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, fastForwardControlStateElement, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withPlaybackSpeed(@Nullable PlaybackSpeedControlStateElement playbackSpeedControlStateElement) {
        return this.playbackSpeed == playbackSpeedControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, playbackSpeedControlStateElement, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withSleepTimer(@Nullable SleepTimerControlStateElement sleepTimerControlStateElement) {
        return this.sleepTimer == sleepTimerControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, sleepTimerControlStateElement, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withPrevious(@Nullable PreviousControlStateElement previousControlStateElement) {
        return this.previous == previousControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, previousControlStateElement, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withThumbsUp(@Nullable ThumbsUpControlStateElement thumbsUpControlStateElement) {
        return this.thumbsUp == thumbsUpControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, thumbsUpControlStateElement, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withThumbsDown(@Nullable ThumbsDownControlStateElement thumbsDownControlStateElement) {
        return this.thumbsDown == thumbsDownControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, thumbsDownControlStateElement, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withRepeat(@Nullable RepeatControlStateElement repeatControlStateElement) {
        return this.repeat == repeatControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, repeatControlStateElement, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withShuffle(@Nullable ShuffleControlStateElement shuffleControlStateElement) {
        return this.shuffle == shuffleControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, shuffleControlStateElement, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withScrub(@Nullable ScrubControlStateElement scrubControlStateElement) {
        return this.scrub == scrubControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, scrubControlStateElement, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withActiveQueuesTV(@Nullable ActiveQueuesControlStateElement activeQueuesControlStateElement) {
        return this.activeQueuesTV == activeQueuesControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, activeQueuesControlStateElement, this.visualPlayQueue, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withVisualPlayQueue(@Nullable VisualPlayQueueControlStateElement visualPlayQueueControlStateElement) {
        return this.visualPlayQueue == visualPlayQueueControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, visualPlayQueueControlStateElement, this.activeQueues, this.volume);
    }

    public final ImmutableMediaControlStatesElement withActiveQueues(@Nullable ActiveQueuesElement activeQueuesElement) {
        return this.activeQueues == activeQueuesElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, activeQueuesElement, this.volume);
    }

    public final ImmutableMediaControlStatesElement withVolume(@Nullable VolumeControlStateElement volumeControlStateElement) {
        return this.volume == volumeControlStateElement ? this : new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, volumeControlStateElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaControlStatesElement) && equalTo(0, (ImmutableMediaControlStatesElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaControlStatesElement immutableMediaControlStatesElement) {
        return equals(this.play, immutableMediaControlStatesElement.play) && equals(this.next, immutableMediaControlStatesElement.next) && equals(this.skipForward, immutableMediaControlStatesElement.skipForward) && equals(this.skipBackward, immutableMediaControlStatesElement.skipBackward) && equals(this.rewind, immutableMediaControlStatesElement.rewind) && equals(this.fastForward, immutableMediaControlStatesElement.fastForward) && equals(this.playbackSpeed, immutableMediaControlStatesElement.playbackSpeed) && equals(this.sleepTimer, immutableMediaControlStatesElement.sleepTimer) && equals(this.previous, immutableMediaControlStatesElement.previous) && equals(this.thumbsUp, immutableMediaControlStatesElement.thumbsUp) && equals(this.thumbsDown, immutableMediaControlStatesElement.thumbsDown) && equals(this.repeat, immutableMediaControlStatesElement.repeat) && equals(this.shuffle, immutableMediaControlStatesElement.shuffle) && equals(this.scrub, immutableMediaControlStatesElement.scrub) && equals(this.activeQueuesTV, immutableMediaControlStatesElement.activeQueuesTV) && equals(this.visualPlayQueue, immutableMediaControlStatesElement.visualPlayQueue) && equals(this.activeQueues, immutableMediaControlStatesElement.activeQueues) && equals(this.volume, immutableMediaControlStatesElement.volume);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.play) + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.next);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.skipForward);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.skipBackward);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.rewind);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.fastForward);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.playbackSpeed);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.sleepTimer);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.previous);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.thumbsUp);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.thumbsDown);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.repeat);
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + hashCode(this.shuffle);
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + hashCode(this.scrub);
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + hashCode(this.activeQueuesTV);
        int iHashCode16 = iHashCode15 + (iHashCode15 << 5) + hashCode(this.visualPlayQueue);
        int iHashCode17 = iHashCode16 + (iHashCode16 << 5) + hashCode(this.activeQueues);
        return iHashCode17 + (iHashCode17 << 5) + hashCode(this.volume);
    }

    public String toString() {
        return "MediaControlStatesElement{play=" + this.play + ", next=" + this.next + ", skipForward=" + this.skipForward + ", skipBackward=" + this.skipBackward + ", rewind=" + this.rewind + ", fastForward=" + this.fastForward + ", playbackSpeed=" + this.playbackSpeed + ", sleepTimer=" + this.sleepTimer + ", previous=" + this.previous + ", thumbsUp=" + this.thumbsUp + ", thumbsDown=" + this.thumbsDown + ", repeat=" + this.repeat + ", shuffle=" + this.shuffle + ", scrub=" + this.scrub + ", activeQueuesTV=" + this.activeQueuesTV + ", visualPlayQueue=" + this.visualPlayQueue + ", activeQueues=" + this.activeQueues + ", volume=" + this.volume + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaControlStatesElement", generator = "Immutables")
    static final class Json extends MediaControlStatesElement {

        @Nullable
        ActiveQueuesElement activeQueues;

        @Nullable
        ActiveQueuesControlStateElement activeQueuesTV;

        @Nullable
        FastForwardControlStateElement fastForward;

        @Nullable
        NextControlStateElement next;

        @Nullable
        PlayControlStateElement play;

        @Nullable
        PlaybackSpeedControlStateElement playbackSpeed;

        @Nullable
        PreviousControlStateElement previous;

        @Nullable
        RepeatControlStateElement repeat;

        @Nullable
        RewindControlStateElement rewind;

        @Nullable
        ScrubControlStateElement scrub;

        @Nullable
        ShuffleControlStateElement shuffle;

        @Nullable
        SkipBackwardControlStateElement skipBackward;

        @Nullable
        SkipForwardControlStateElement skipForward;

        @Nullable
        SleepTimerControlStateElement sleepTimer;

        @Nullable
        ThumbsDownControlStateElement thumbsDown;

        @Nullable
        ThumbsUpControlStateElement thumbsUp;

        @Nullable
        VisualPlayQueueControlStateElement visualPlayQueue;

        @Nullable
        VolumeControlStateElement volume;

        Json() {
        }

        @JsonProperty(PageUriUtils.DO_PLAY_VALUE)
        public void setPlay(@Nullable PlayControlStateElement playControlStateElement) {
            this.play = playControlStateElement;
        }

        @JsonProperty("next")
        public void setNext(@Nullable NextControlStateElement nextControlStateElement) {
            this.next = nextControlStateElement;
        }

        @JsonProperty("skipForward")
        public void setSkipForward(@Nullable SkipForwardControlStateElement skipForwardControlStateElement) {
            this.skipForward = skipForwardControlStateElement;
        }

        @JsonProperty("skipBackward")
        public void setSkipBackward(@Nullable SkipBackwardControlStateElement skipBackwardControlStateElement) {
            this.skipBackward = skipBackwardControlStateElement;
        }

        @JsonProperty("rewind")
        public void setRewind(@Nullable RewindControlStateElement rewindControlStateElement) {
            this.rewind = rewindControlStateElement;
        }

        @JsonProperty("fastForward")
        public void setFastForward(@Nullable FastForwardControlStateElement fastForwardControlStateElement) {
            this.fastForward = fastForwardControlStateElement;
        }

        @JsonProperty("playbackSpeed")
        public void setPlaybackSpeed(@Nullable PlaybackSpeedControlStateElement playbackSpeedControlStateElement) {
            this.playbackSpeed = playbackSpeedControlStateElement;
        }

        @JsonProperty("sleepTimer")
        public void setSleepTimer(@Nullable SleepTimerControlStateElement sleepTimerControlStateElement) {
            this.sleepTimer = sleepTimerControlStateElement;
        }

        @JsonProperty("previous")
        public void setPrevious(@Nullable PreviousControlStateElement previousControlStateElement) {
            this.previous = previousControlStateElement;
        }

        @JsonProperty("thumbsUp")
        public void setThumbsUp(@Nullable ThumbsUpControlStateElement thumbsUpControlStateElement) {
            this.thumbsUp = thumbsUpControlStateElement;
        }

        @JsonProperty("thumbsDown")
        public void setThumbsDown(@Nullable ThumbsDownControlStateElement thumbsDownControlStateElement) {
            this.thumbsDown = thumbsDownControlStateElement;
        }

        @JsonProperty("repeat")
        public void setRepeat(@Nullable RepeatControlStateElement repeatControlStateElement) {
            this.repeat = repeatControlStateElement;
        }

        @JsonProperty("shuffle")
        public void setShuffle(@Nullable ShuffleControlStateElement shuffleControlStateElement) {
            this.shuffle = shuffleControlStateElement;
        }

        @JsonProperty("scrub")
        public void setScrub(@Nullable ScrubControlStateElement scrubControlStateElement) {
            this.scrub = scrubControlStateElement;
        }

        @JsonProperty("activeQueuesTV")
        public void setActiveQueuesTV(@Nullable ActiveQueuesControlStateElement activeQueuesControlStateElement) {
            this.activeQueuesTV = activeQueuesControlStateElement;
        }

        @JsonProperty("visualPlayQueue")
        public void setVisualPlayQueue(@Nullable VisualPlayQueueControlStateElement visualPlayQueueControlStateElement) {
            this.visualPlayQueue = visualPlayQueueControlStateElement;
        }

        @JsonProperty("activeQueues")
        public void setActiveQueues(@Nullable ActiveQueuesElement activeQueuesElement) {
            this.activeQueues = activeQueuesElement;
        }

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public void setVolume(@Nullable VolumeControlStateElement volumeControlStateElement) {
            this.volume = volumeControlStateElement;
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public PlayControlStateElement play() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public NextControlStateElement next() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public SkipForwardControlStateElement skipForward() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public SkipBackwardControlStateElement skipBackward() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public RewindControlStateElement rewind() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public FastForwardControlStateElement fastForward() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public PlaybackSpeedControlStateElement playbackSpeed() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public SleepTimerControlStateElement sleepTimer() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public PreviousControlStateElement previous() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ThumbsUpControlStateElement thumbsUp() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ThumbsDownControlStateElement thumbsDown() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public RepeatControlStateElement repeat() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ShuffleControlStateElement shuffle() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ScrubControlStateElement scrub() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ActiveQueuesControlStateElement activeQueuesTV() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public VisualPlayQueueControlStateElement visualPlayQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public ActiveQueuesElement activeQueues() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaControlStatesElement
        public VolumeControlStateElement volume() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaControlStatesElement fromJson(Json json) {
        Builder builder = builder();
        PlayControlStateElement playControlStateElement = json.play;
        if (playControlStateElement != null) {
            builder.play(playControlStateElement);
        }
        NextControlStateElement nextControlStateElement = json.next;
        if (nextControlStateElement != null) {
            builder.next(nextControlStateElement);
        }
        SkipForwardControlStateElement skipForwardControlStateElement = json.skipForward;
        if (skipForwardControlStateElement != null) {
            builder.skipForward(skipForwardControlStateElement);
        }
        SkipBackwardControlStateElement skipBackwardControlStateElement = json.skipBackward;
        if (skipBackwardControlStateElement != null) {
            builder.skipBackward(skipBackwardControlStateElement);
        }
        RewindControlStateElement rewindControlStateElement = json.rewind;
        if (rewindControlStateElement != null) {
            builder.rewind(rewindControlStateElement);
        }
        FastForwardControlStateElement fastForwardControlStateElement = json.fastForward;
        if (fastForwardControlStateElement != null) {
            builder.fastForward(fastForwardControlStateElement);
        }
        PlaybackSpeedControlStateElement playbackSpeedControlStateElement = json.playbackSpeed;
        if (playbackSpeedControlStateElement != null) {
            builder.playbackSpeed(playbackSpeedControlStateElement);
        }
        SleepTimerControlStateElement sleepTimerControlStateElement = json.sleepTimer;
        if (sleepTimerControlStateElement != null) {
            builder.sleepTimer(sleepTimerControlStateElement);
        }
        PreviousControlStateElement previousControlStateElement = json.previous;
        if (previousControlStateElement != null) {
            builder.previous(previousControlStateElement);
        }
        ThumbsUpControlStateElement thumbsUpControlStateElement = json.thumbsUp;
        if (thumbsUpControlStateElement != null) {
            builder.thumbsUp(thumbsUpControlStateElement);
        }
        ThumbsDownControlStateElement thumbsDownControlStateElement = json.thumbsDown;
        if (thumbsDownControlStateElement != null) {
            builder.thumbsDown(thumbsDownControlStateElement);
        }
        RepeatControlStateElement repeatControlStateElement = json.repeat;
        if (repeatControlStateElement != null) {
            builder.repeat(repeatControlStateElement);
        }
        ShuffleControlStateElement shuffleControlStateElement = json.shuffle;
        if (shuffleControlStateElement != null) {
            builder.shuffle(shuffleControlStateElement);
        }
        ScrubControlStateElement scrubControlStateElement = json.scrub;
        if (scrubControlStateElement != null) {
            builder.scrub(scrubControlStateElement);
        }
        ActiveQueuesControlStateElement activeQueuesControlStateElement = json.activeQueuesTV;
        if (activeQueuesControlStateElement != null) {
            builder.activeQueuesTV(activeQueuesControlStateElement);
        }
        VisualPlayQueueControlStateElement visualPlayQueueControlStateElement = json.visualPlayQueue;
        if (visualPlayQueueControlStateElement != null) {
            builder.visualPlayQueue(visualPlayQueueControlStateElement);
        }
        ActiveQueuesElement activeQueuesElement = json.activeQueues;
        if (activeQueuesElement != null) {
            builder.activeQueues(activeQueuesElement);
        }
        VolumeControlStateElement volumeControlStateElement = json.volume;
        if (volumeControlStateElement != null) {
            builder.volume(volumeControlStateElement);
        }
        return builder.build();
    }

    public static ImmutableMediaControlStatesElement copyOf(MediaControlStatesElement mediaControlStatesElement) {
        if (mediaControlStatesElement instanceof ImmutableMediaControlStatesElement) {
            return (ImmutableMediaControlStatesElement) mediaControlStatesElement;
        }
        return builder().from(mediaControlStatesElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaControlStatesElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private ActiveQueuesElement activeQueues;

        @Nullable
        private ActiveQueuesControlStateElement activeQueuesTV;

        @Nullable
        private FastForwardControlStateElement fastForward;

        @Nullable
        private NextControlStateElement next;

        @Nullable
        private PlayControlStateElement play;

        @Nullable
        private PlaybackSpeedControlStateElement playbackSpeed;

        @Nullable
        private PreviousControlStateElement previous;

        @Nullable
        private RepeatControlStateElement repeat;

        @Nullable
        private RewindControlStateElement rewind;

        @Nullable
        private ScrubControlStateElement scrub;

        @Nullable
        private ShuffleControlStateElement shuffle;

        @Nullable
        private SkipBackwardControlStateElement skipBackward;

        @Nullable
        private SkipForwardControlStateElement skipForward;

        @Nullable
        private SleepTimerControlStateElement sleepTimer;

        @Nullable
        private ThumbsDownControlStateElement thumbsDown;

        @Nullable
        private ThumbsUpControlStateElement thumbsUp;

        @Nullable
        private VisualPlayQueueControlStateElement visualPlayQueue;

        @Nullable
        private VolumeControlStateElement volume;

        private Builder() {
        }

        public final Builder from(MediaControlStatesElement mediaControlStatesElement) {
            ImmutableMediaControlStatesElement.requireNonNull(mediaControlStatesElement, "instance");
            PlayControlStateElement playControlStateElementPlay = mediaControlStatesElement.play();
            if (playControlStateElementPlay != null) {
                play(playControlStateElementPlay);
            }
            NextControlStateElement next = mediaControlStatesElement.next();
            if (next != null) {
                next(next);
            }
            SkipForwardControlStateElement skipForwardControlStateElementSkipForward = mediaControlStatesElement.skipForward();
            if (skipForwardControlStateElementSkipForward != null) {
                skipForward(skipForwardControlStateElementSkipForward);
            }
            SkipBackwardControlStateElement skipBackwardControlStateElementSkipBackward = mediaControlStatesElement.skipBackward();
            if (skipBackwardControlStateElementSkipBackward != null) {
                skipBackward(skipBackwardControlStateElementSkipBackward);
            }
            RewindControlStateElement rewindControlStateElementRewind = mediaControlStatesElement.rewind();
            if (rewindControlStateElementRewind != null) {
                rewind(rewindControlStateElementRewind);
            }
            FastForwardControlStateElement fastForwardControlStateElementFastForward = mediaControlStatesElement.fastForward();
            if (fastForwardControlStateElementFastForward != null) {
                fastForward(fastForwardControlStateElementFastForward);
            }
            PlaybackSpeedControlStateElement playbackSpeedControlStateElementPlaybackSpeed = mediaControlStatesElement.playbackSpeed();
            if (playbackSpeedControlStateElementPlaybackSpeed != null) {
                playbackSpeed(playbackSpeedControlStateElementPlaybackSpeed);
            }
            SleepTimerControlStateElement sleepTimerControlStateElementSleepTimer = mediaControlStatesElement.sleepTimer();
            if (sleepTimerControlStateElementSleepTimer != null) {
                sleepTimer(sleepTimerControlStateElementSleepTimer);
            }
            PreviousControlStateElement previousControlStateElementPrevious = mediaControlStatesElement.previous();
            if (previousControlStateElementPrevious != null) {
                previous(previousControlStateElementPrevious);
            }
            ThumbsUpControlStateElement thumbsUpControlStateElementThumbsUp = mediaControlStatesElement.thumbsUp();
            if (thumbsUpControlStateElementThumbsUp != null) {
                thumbsUp(thumbsUpControlStateElementThumbsUp);
            }
            ThumbsDownControlStateElement thumbsDownControlStateElementThumbsDown = mediaControlStatesElement.thumbsDown();
            if (thumbsDownControlStateElementThumbsDown != null) {
                thumbsDown(thumbsDownControlStateElementThumbsDown);
            }
            RepeatControlStateElement repeatControlStateElementRepeat = mediaControlStatesElement.repeat();
            if (repeatControlStateElementRepeat != null) {
                repeat(repeatControlStateElementRepeat);
            }
            ShuffleControlStateElement shuffleControlStateElementShuffle = mediaControlStatesElement.shuffle();
            if (shuffleControlStateElementShuffle != null) {
                shuffle(shuffleControlStateElementShuffle);
            }
            ScrubControlStateElement scrubControlStateElementScrub = mediaControlStatesElement.scrub();
            if (scrubControlStateElementScrub != null) {
                scrub(scrubControlStateElementScrub);
            }
            ActiveQueuesControlStateElement activeQueuesControlStateElementActiveQueuesTV = mediaControlStatesElement.activeQueuesTV();
            if (activeQueuesControlStateElementActiveQueuesTV != null) {
                activeQueuesTV(activeQueuesControlStateElementActiveQueuesTV);
            }
            VisualPlayQueueControlStateElement visualPlayQueueControlStateElementVisualPlayQueue = mediaControlStatesElement.visualPlayQueue();
            if (visualPlayQueueControlStateElementVisualPlayQueue != null) {
                visualPlayQueue(visualPlayQueueControlStateElementVisualPlayQueue);
            }
            ActiveQueuesElement activeQueuesElementActiveQueues = mediaControlStatesElement.activeQueues();
            if (activeQueuesElementActiveQueues != null) {
                activeQueues(activeQueuesElementActiveQueues);
            }
            VolumeControlStateElement volumeControlStateElementVolume = mediaControlStatesElement.volume();
            if (volumeControlStateElementVolume != null) {
                volume(volumeControlStateElementVolume);
            }
            return this;
        }

        @JsonProperty(PageUriUtils.DO_PLAY_VALUE)
        public final Builder play(@Nullable PlayControlStateElement playControlStateElement) {
            this.play = playControlStateElement;
            return this;
        }

        @JsonProperty("next")
        public final Builder next(@Nullable NextControlStateElement nextControlStateElement) {
            this.next = nextControlStateElement;
            return this;
        }

        @JsonProperty("skipForward")
        public final Builder skipForward(@Nullable SkipForwardControlStateElement skipForwardControlStateElement) {
            this.skipForward = skipForwardControlStateElement;
            return this;
        }

        @JsonProperty("skipBackward")
        public final Builder skipBackward(@Nullable SkipBackwardControlStateElement skipBackwardControlStateElement) {
            this.skipBackward = skipBackwardControlStateElement;
            return this;
        }

        @JsonProperty("rewind")
        public final Builder rewind(@Nullable RewindControlStateElement rewindControlStateElement) {
            this.rewind = rewindControlStateElement;
            return this;
        }

        @JsonProperty("fastForward")
        public final Builder fastForward(@Nullable FastForwardControlStateElement fastForwardControlStateElement) {
            this.fastForward = fastForwardControlStateElement;
            return this;
        }

        @JsonProperty("playbackSpeed")
        public final Builder playbackSpeed(@Nullable PlaybackSpeedControlStateElement playbackSpeedControlStateElement) {
            this.playbackSpeed = playbackSpeedControlStateElement;
            return this;
        }

        @JsonProperty("sleepTimer")
        public final Builder sleepTimer(@Nullable SleepTimerControlStateElement sleepTimerControlStateElement) {
            this.sleepTimer = sleepTimerControlStateElement;
            return this;
        }

        @JsonProperty("previous")
        public final Builder previous(@Nullable PreviousControlStateElement previousControlStateElement) {
            this.previous = previousControlStateElement;
            return this;
        }

        @JsonProperty("thumbsUp")
        public final Builder thumbsUp(@Nullable ThumbsUpControlStateElement thumbsUpControlStateElement) {
            this.thumbsUp = thumbsUpControlStateElement;
            return this;
        }

        @JsonProperty("thumbsDown")
        public final Builder thumbsDown(@Nullable ThumbsDownControlStateElement thumbsDownControlStateElement) {
            this.thumbsDown = thumbsDownControlStateElement;
            return this;
        }

        @JsonProperty("repeat")
        public final Builder repeat(@Nullable RepeatControlStateElement repeatControlStateElement) {
            this.repeat = repeatControlStateElement;
            return this;
        }

        @JsonProperty("shuffle")
        public final Builder shuffle(@Nullable ShuffleControlStateElement shuffleControlStateElement) {
            this.shuffle = shuffleControlStateElement;
            return this;
        }

        @JsonProperty("scrub")
        public final Builder scrub(@Nullable ScrubControlStateElement scrubControlStateElement) {
            this.scrub = scrubControlStateElement;
            return this;
        }

        @JsonProperty("activeQueuesTV")
        public final Builder activeQueuesTV(@Nullable ActiveQueuesControlStateElement activeQueuesControlStateElement) {
            this.activeQueuesTV = activeQueuesControlStateElement;
            return this;
        }

        @JsonProperty("visualPlayQueue")
        public final Builder visualPlayQueue(@Nullable VisualPlayQueueControlStateElement visualPlayQueueControlStateElement) {
            this.visualPlayQueue = visualPlayQueueControlStateElement;
            return this;
        }

        @JsonProperty("activeQueues")
        public final Builder activeQueues(@Nullable ActiveQueuesElement activeQueuesElement) {
            this.activeQueues = activeQueuesElement;
            return this;
        }

        @JsonProperty(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME)
        public final Builder volume(@Nullable VolumeControlStateElement volumeControlStateElement) {
            this.volume = volumeControlStateElement;
            return this;
        }

        public ImmutableMediaControlStatesElement build() {
            return new ImmutableMediaControlStatesElement(this.play, this.next, this.skipForward, this.skipBackward, this.rewind, this.fastForward, this.playbackSpeed, this.sleepTimer, this.previous, this.thumbsUp, this.thumbsDown, this.repeat, this.shuffle, this.scrub, this.activeQueuesTV, this.visualPlayQueue, this.activeQueues, this.volume);
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
}
