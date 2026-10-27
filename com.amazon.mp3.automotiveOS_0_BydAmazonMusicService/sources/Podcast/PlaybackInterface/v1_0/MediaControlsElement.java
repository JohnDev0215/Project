package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MediaControlsElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.MediaControlsElement");
    private FastForwardElement fastForward;
    private PlayPauseElement playPause;
    private PlaybackSpeedElement playbackSpeed;
    private RewindElement rewind;
    private SleepTimerElement sleepTimer;

    public static class Builder extends PlaybackElement.Builder {
        protected FastForwardElement fastForward;
        protected PlayPauseElement playPause;
        protected PlaybackSpeedElement playbackSpeed;
        protected RewindElement rewind;
        protected SleepTimerElement sleepTimer;

        public MediaControlsElement build() {
            MediaControlsElement mediaControlsElement = new MediaControlsElement();
            populate(mediaControlsElement);
            return mediaControlsElement;
        }

        protected void populate(MediaControlsElement mediaControlsElement) {
            super.populate((PlaybackElement) mediaControlsElement);
            mediaControlsElement.setRewind(this.rewind);
            mediaControlsElement.setSleepTimer(this.sleepTimer);
            mediaControlsElement.setPlayPause(this.playPause);
            mediaControlsElement.setFastForward(this.fastForward);
            mediaControlsElement.setPlaybackSpeed(this.playbackSpeed);
        }

        public Builder withFastForward(FastForwardElement fastForwardElement) {
            this.fastForward = fastForwardElement;
            return this;
        }

        public Builder withPlayPause(PlayPauseElement playPauseElement) {
            this.playPause = playPauseElement;
            return this;
        }

        public Builder withPlaybackSpeed(PlaybackSpeedElement playbackSpeedElement) {
            this.playbackSpeed = playbackSpeedElement;
            return this;
        }

        public Builder withRewind(RewindElement rewindElement) {
            this.rewind = rewindElement;
            return this;
        }

        public Builder withSleepTimer(SleepTimerElement sleepTimerElement) {
            this.sleepTimer = sleepTimerElement;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof MediaControlsElement)) {
            return 1;
        }
        MediaControlsElement mediaControlsElement = (MediaControlsElement) sOAObject;
        RewindElement rewind = getRewind();
        RewindElement rewind2 = mediaControlsElement.getRewind();
        if (rewind != rewind2) {
            if (rewind == null) {
                return -1;
            }
            if (rewind2 == null) {
                return 1;
            }
            int iCompareTo = rewind.compareTo(rewind2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        SleepTimerElement sleepTimer = getSleepTimer();
        SleepTimerElement sleepTimer2 = mediaControlsElement.getSleepTimer();
        if (sleepTimer != sleepTimer2) {
            if (sleepTimer == null) {
                return -1;
            }
            if (sleepTimer2 == null) {
                return 1;
            }
            int iCompareTo2 = sleepTimer.compareTo(sleepTimer2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        PlayPauseElement playPause = getPlayPause();
        PlayPauseElement playPause2 = mediaControlsElement.getPlayPause();
        if (playPause != playPause2) {
            if (playPause == null) {
                return -1;
            }
            if (playPause2 == null) {
                return 1;
            }
            int iCompareTo3 = playPause.compareTo(playPause2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        FastForwardElement fastForward = getFastForward();
        FastForwardElement fastForward2 = mediaControlsElement.getFastForward();
        if (fastForward != fastForward2) {
            if (fastForward == null) {
                return -1;
            }
            if (fastForward2 == null) {
                return 1;
            }
            int iCompareTo4 = fastForward.compareTo(fastForward2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PlaybackSpeedElement playbackSpeed = getPlaybackSpeed();
        PlaybackSpeedElement playbackSpeed2 = mediaControlsElement.getPlaybackSpeed();
        if (playbackSpeed != playbackSpeed2) {
            if (playbackSpeed == null) {
                return -1;
            }
            if (playbackSpeed2 == null) {
                return 1;
            }
            int iCompareTo5 = playbackSpeed.compareTo(playbackSpeed2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof MediaControlsElement)) {
            return false;
        }
        MediaControlsElement mediaControlsElement = (MediaControlsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getRewind(), mediaControlsElement.getRewind()) && internalEqualityCheck(getSleepTimer(), mediaControlsElement.getSleepTimer()) && internalEqualityCheck(getPlayPause(), mediaControlsElement.getPlayPause()) && internalEqualityCheck(getFastForward(), mediaControlsElement.getFastForward()) && internalEqualityCheck(getPlaybackSpeed(), mediaControlsElement.getPlaybackSpeed());
    }

    public FastForwardElement getFastForward() {
        return this.fastForward;
    }

    public PlayPauseElement getPlayPause() {
        return this.playPause;
    }

    public PlaybackSpeedElement getPlaybackSpeed() {
        return this.playbackSpeed;
    }

    public RewindElement getRewind() {
        return this.rewind;
    }

    public SleepTimerElement getSleepTimer() {
        return this.sleepTimer;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getRewind(), getSleepTimer(), getPlayPause(), getFastForward(), getPlaybackSpeed());
    }

    public void setFastForward(FastForwardElement fastForwardElement) {
        this.fastForward = fastForwardElement;
    }

    public void setPlayPause(PlayPauseElement playPauseElement) {
        this.playPause = playPauseElement;
    }

    public void setPlaybackSpeed(PlaybackSpeedElement playbackSpeedElement) {
        this.playbackSpeed = playbackSpeedElement;
    }

    public void setRewind(RewindElement rewindElement) {
        this.rewind = rewindElement;
    }

    public void setSleepTimer(SleepTimerElement sleepTimerElement) {
        this.sleepTimer = sleepTimerElement;
    }
}
