package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetPlaybackCapabilitiesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethod");
    private Boolean fastForward;
    private Boolean lockScreen;
    private Boolean miniplayer;
    private Boolean playbackSpeed;
    private Boolean rewind;
    private Boolean scrubber;
    private Boolean sleepTimer;
    private Boolean transcript;

    public static class Builder extends Method.Builder {
        protected Boolean fastForward;
        protected Boolean lockScreen;
        protected Boolean miniplayer;
        protected Boolean playbackSpeed;
        protected Boolean rewind;
        protected Boolean scrubber;
        protected Boolean sleepTimer;
        protected Boolean transcript;

        public SetPlaybackCapabilitiesMethod build() {
            SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod = new SetPlaybackCapabilitiesMethod();
            populate(setPlaybackCapabilitiesMethod);
            return setPlaybackCapabilitiesMethod;
        }

        protected void populate(SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod) {
            super.populate((Method) setPlaybackCapabilitiesMethod);
            setPlaybackCapabilitiesMethod.setMiniplayer(this.miniplayer);
            setPlaybackCapabilitiesMethod.setRewind(this.rewind);
            setPlaybackCapabilitiesMethod.setScrubber(this.scrubber);
            setPlaybackCapabilitiesMethod.setPlaybackSpeed(this.playbackSpeed);
            setPlaybackCapabilitiesMethod.setLockScreen(this.lockScreen);
            setPlaybackCapabilitiesMethod.setSleepTimer(this.sleepTimer);
            setPlaybackCapabilitiesMethod.setFastForward(this.fastForward);
            setPlaybackCapabilitiesMethod.setTranscript(this.transcript);
        }

        public Builder withFastForward(Boolean bool) {
            this.fastForward = bool;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withLockScreen(Boolean bool) {
            this.lockScreen = bool;
            return this;
        }

        public Builder withMiniplayer(Boolean bool) {
            this.miniplayer = bool;
            return this;
        }

        public Builder withPlaybackSpeed(Boolean bool) {
            this.playbackSpeed = bool;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withRewind(Boolean bool) {
            this.rewind = bool;
            return this;
        }

        public Builder withScrubber(Boolean bool) {
            this.scrubber = bool;
            return this;
        }

        public Builder withSleepTimer(Boolean bool) {
            this.sleepTimer = bool;
            return this;
        }

        public Builder withTranscript(Boolean bool) {
            this.transcript = bool;
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof SetPlaybackCapabilitiesMethod)) {
            return 1;
        }
        SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod = (SetPlaybackCapabilitiesMethod) sOAObject;
        Boolean boolIsMiniplayer = isMiniplayer();
        Boolean boolIsMiniplayer2 = setPlaybackCapabilitiesMethod.isMiniplayer();
        if (boolIsMiniplayer != boolIsMiniplayer2) {
            if (boolIsMiniplayer == null) {
                return -1;
            }
            if (boolIsMiniplayer2 == null) {
                return 1;
            }
            int iCompareTo = boolIsMiniplayer.compareTo(boolIsMiniplayer2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsRewind = isRewind();
        Boolean boolIsRewind2 = setPlaybackCapabilitiesMethod.isRewind();
        if (boolIsRewind != boolIsRewind2) {
            if (boolIsRewind == null) {
                return -1;
            }
            if (boolIsRewind2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsRewind.compareTo(boolIsRewind2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsScrubber = isScrubber();
        Boolean boolIsScrubber2 = setPlaybackCapabilitiesMethod.isScrubber();
        if (boolIsScrubber != boolIsScrubber2) {
            if (boolIsScrubber == null) {
                return -1;
            }
            if (boolIsScrubber2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsScrubber.compareTo(boolIsScrubber2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsPlaybackSpeed = isPlaybackSpeed();
        Boolean boolIsPlaybackSpeed2 = setPlaybackCapabilitiesMethod.isPlaybackSpeed();
        if (boolIsPlaybackSpeed != boolIsPlaybackSpeed2) {
            if (boolIsPlaybackSpeed == null) {
                return -1;
            }
            if (boolIsPlaybackSpeed2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsPlaybackSpeed.compareTo(boolIsPlaybackSpeed2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsLockScreen = isLockScreen();
        Boolean boolIsLockScreen2 = setPlaybackCapabilitiesMethod.isLockScreen();
        if (boolIsLockScreen != boolIsLockScreen2) {
            if (boolIsLockScreen == null) {
                return -1;
            }
            if (boolIsLockScreen2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsLockScreen.compareTo(boolIsLockScreen2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsSleepTimer = isSleepTimer();
        Boolean boolIsSleepTimer2 = setPlaybackCapabilitiesMethod.isSleepTimer();
        if (boolIsSleepTimer != boolIsSleepTimer2) {
            if (boolIsSleepTimer == null) {
                return -1;
            }
            if (boolIsSleepTimer2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsSleepTimer.compareTo(boolIsSleepTimer2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsFastForward = isFastForward();
        Boolean boolIsFastForward2 = setPlaybackCapabilitiesMethod.isFastForward();
        if (boolIsFastForward != boolIsFastForward2) {
            if (boolIsFastForward == null) {
                return -1;
            }
            if (boolIsFastForward2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsFastForward.compareTo(boolIsFastForward2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsTranscript = isTranscript();
        Boolean boolIsTranscript2 = setPlaybackCapabilitiesMethod.isTranscript();
        if (boolIsTranscript != boolIsTranscript2) {
            if (boolIsTranscript == null) {
                return -1;
            }
            if (boolIsTranscript2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsTranscript.compareTo(boolIsTranscript2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetPlaybackCapabilitiesMethod)) {
            return false;
        }
        SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod = (SetPlaybackCapabilitiesMethod) obj;
        return super.equals(obj) && internalEqualityCheck(isMiniplayer(), setPlaybackCapabilitiesMethod.isMiniplayer()) && internalEqualityCheck(isRewind(), setPlaybackCapabilitiesMethod.isRewind()) && internalEqualityCheck(isScrubber(), setPlaybackCapabilitiesMethod.isScrubber()) && internalEqualityCheck(isPlaybackSpeed(), setPlaybackCapabilitiesMethod.isPlaybackSpeed()) && internalEqualityCheck(isLockScreen(), setPlaybackCapabilitiesMethod.isLockScreen()) && internalEqualityCheck(isSleepTimer(), setPlaybackCapabilitiesMethod.isSleepTimer()) && internalEqualityCheck(isFastForward(), setPlaybackCapabilitiesMethod.isFastForward()) && internalEqualityCheck(isTranscript(), setPlaybackCapabilitiesMethod.isTranscript());
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isMiniplayer(), isRewind(), isScrubber(), isPlaybackSpeed(), isLockScreen(), isSleepTimer(), isFastForward(), isTranscript());
    }

    public Boolean isFastForward() {
        return this.fastForward;
    }

    public Boolean isLockScreen() {
        return this.lockScreen;
    }

    public Boolean isMiniplayer() {
        return this.miniplayer;
    }

    public Boolean isPlaybackSpeed() {
        return this.playbackSpeed;
    }

    public Boolean isRewind() {
        return this.rewind;
    }

    public Boolean isScrubber() {
        return this.scrubber;
    }

    public Boolean isSleepTimer() {
        return this.sleepTimer;
    }

    public Boolean isTranscript() {
        return this.transcript;
    }

    public void setFastForward(Boolean bool) {
        this.fastForward = bool;
    }

    public void setLockScreen(Boolean bool) {
        this.lockScreen = bool;
    }

    public void setMiniplayer(Boolean bool) {
        this.miniplayer = bool;
    }

    public void setPlaybackSpeed(Boolean bool) {
        this.playbackSpeed = bool;
    }

    public void setRewind(Boolean bool) {
        this.rewind = bool;
    }

    public void setScrubber(Boolean bool) {
        this.scrubber = bool;
    }

    public void setSleepTimer(Boolean bool) {
        this.sleepTimer = bool;
    }

    public void setTranscript(Boolean bool) {
        this.transcript = bool;
    }
}
