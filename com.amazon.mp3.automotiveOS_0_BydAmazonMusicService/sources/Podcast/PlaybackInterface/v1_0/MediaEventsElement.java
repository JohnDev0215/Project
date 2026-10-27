package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaEventsElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.MediaEventsElement");
    private List<Method> onCompletedThresholdElapsed;
    private List<Method> onError;
    private List<Method> onFastForward;
    private List<Method> onFinished;
    private List<Method> onPaused;
    private List<Method> onPlaybackSpeedModified;
    private List<Method> onPostScrubbed;
    private List<Method> onPreScrubbed;
    private List<Method> onRebuffered;
    private List<Method> onResumed;
    private List<Method> onRewind;
    private List<Method> onScrubbed;
    private List<Method> onSleepTimerElapsed;
    private List<Method> onSleepTimerSet;
    private List<Method> onStarted;
    private List<Method> onStopped;
    private List<Method> onWriteAndReportFrequencyReached;
    private List<Method> onWriteFrequencyReached;

    public static class Builder extends PlaybackElement.Builder {
        protected List<Method> onCompletedThresholdElapsed;
        protected List<Method> onError;
        protected List<Method> onFastForward;
        protected List<Method> onFinished;
        protected List<Method> onPaused;
        protected List<Method> onPlaybackSpeedModified;
        protected List<Method> onPostScrubbed;
        protected List<Method> onPreScrubbed;
        protected List<Method> onRebuffered;
        protected List<Method> onResumed;
        protected List<Method> onRewind;
        protected List<Method> onScrubbed;
        protected List<Method> onSleepTimerElapsed;
        protected List<Method> onSleepTimerSet;
        protected List<Method> onStarted;
        protected List<Method> onStopped;
        protected List<Method> onWriteAndReportFrequencyReached;
        protected List<Method> onWriteFrequencyReached;

        public MediaEventsElement build() {
            MediaEventsElement mediaEventsElement = new MediaEventsElement();
            populate(mediaEventsElement);
            return mediaEventsElement;
        }

        protected void populate(MediaEventsElement mediaEventsElement) {
            super.populate((PlaybackElement) mediaEventsElement);
            mediaEventsElement.setOnFinished(this.onFinished);
            mediaEventsElement.setOnSleepTimerSet(this.onSleepTimerSet);
            mediaEventsElement.setOnFastForward(this.onFastForward);
            mediaEventsElement.setOnStopped(this.onStopped);
            mediaEventsElement.setOnError(this.onError);
            mediaEventsElement.setOnCompletedThresholdElapsed(this.onCompletedThresholdElapsed);
            mediaEventsElement.setOnPlaybackSpeedModified(this.onPlaybackSpeedModified);
            mediaEventsElement.setOnSleepTimerElapsed(this.onSleepTimerElapsed);
            mediaEventsElement.setOnWriteFrequencyReached(this.onWriteFrequencyReached);
            mediaEventsElement.setOnPostScrubbed(this.onPostScrubbed);
            mediaEventsElement.setOnPaused(this.onPaused);
            mediaEventsElement.setOnWriteAndReportFrequencyReached(this.onWriteAndReportFrequencyReached);
            mediaEventsElement.setOnRewind(this.onRewind);
            mediaEventsElement.setOnResumed(this.onResumed);
            mediaEventsElement.setOnRebuffered(this.onRebuffered);
            mediaEventsElement.setOnPreScrubbed(this.onPreScrubbed);
            mediaEventsElement.setOnScrubbed(this.onScrubbed);
            mediaEventsElement.setOnStarted(this.onStarted);
        }

        public Builder withOnCompletedThresholdElapsed(List<Method> list) {
            this.onCompletedThresholdElapsed = list;
            return this;
        }

        public Builder withOnError(List<Method> list) {
            this.onError = list;
            return this;
        }

        public Builder withOnFastForward(List<Method> list) {
            this.onFastForward = list;
            return this;
        }

        public Builder withOnFinished(List<Method> list) {
            this.onFinished = list;
            return this;
        }

        public Builder withOnPaused(List<Method> list) {
            this.onPaused = list;
            return this;
        }

        public Builder withOnPlaybackSpeedModified(List<Method> list) {
            this.onPlaybackSpeedModified = list;
            return this;
        }

        public Builder withOnPostScrubbed(List<Method> list) {
            this.onPostScrubbed = list;
            return this;
        }

        public Builder withOnPreScrubbed(List<Method> list) {
            this.onPreScrubbed = list;
            return this;
        }

        public Builder withOnRebuffered(List<Method> list) {
            this.onRebuffered = list;
            return this;
        }

        public Builder withOnResumed(List<Method> list) {
            this.onResumed = list;
            return this;
        }

        public Builder withOnRewind(List<Method> list) {
            this.onRewind = list;
            return this;
        }

        public Builder withOnScrubbed(List<Method> list) {
            this.onScrubbed = list;
            return this;
        }

        public Builder withOnSleepTimerElapsed(List<Method> list) {
            this.onSleepTimerElapsed = list;
            return this;
        }

        public Builder withOnSleepTimerSet(List<Method> list) {
            this.onSleepTimerSet = list;
            return this;
        }

        public Builder withOnStarted(List<Method> list) {
            this.onStarted = list;
            return this;
        }

        public Builder withOnStopped(List<Method> list) {
            this.onStopped = list;
            return this;
        }

        public Builder withOnWriteAndReportFrequencyReached(List<Method> list) {
            this.onWriteAndReportFrequencyReached = list;
            return this;
        }

        public Builder withOnWriteFrequencyReached(List<Method> list) {
            this.onWriteFrequencyReached = list;
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
        if (!(sOAObject instanceof MediaEventsElement)) {
            return 1;
        }
        MediaEventsElement mediaEventsElement = (MediaEventsElement) sOAObject;
        List<Method> onFinished = getOnFinished();
        List<Method> onFinished2 = mediaEventsElement.getOnFinished();
        if (onFinished != onFinished2) {
            if (onFinished == null) {
                return -1;
            }
            if (onFinished2 == null) {
                return 1;
            }
            if (onFinished instanceof Comparable) {
                int iCompareTo = ((Comparable) onFinished).compareTo(onFinished2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onFinished.equals(onFinished2)) {
                int iHashCode = onFinished.hashCode();
                int iHashCode2 = onFinished2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onSleepTimerSet = getOnSleepTimerSet();
        List<Method> onSleepTimerSet2 = mediaEventsElement.getOnSleepTimerSet();
        if (onSleepTimerSet != onSleepTimerSet2) {
            if (onSleepTimerSet == null) {
                return -1;
            }
            if (onSleepTimerSet2 == null) {
                return 1;
            }
            if (onSleepTimerSet instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onSleepTimerSet).compareTo(onSleepTimerSet2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onSleepTimerSet.equals(onSleepTimerSet2)) {
                int iHashCode3 = onSleepTimerSet.hashCode();
                int iHashCode4 = onSleepTimerSet2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onFastForward = getOnFastForward();
        List<Method> onFastForward2 = mediaEventsElement.getOnFastForward();
        if (onFastForward != onFastForward2) {
            if (onFastForward == null) {
                return -1;
            }
            if (onFastForward2 == null) {
                return 1;
            }
            if (onFastForward instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onFastForward).compareTo(onFastForward2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onFastForward.equals(onFastForward2)) {
                int iHashCode5 = onFastForward.hashCode();
                int iHashCode6 = onFastForward2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Method> onStopped = getOnStopped();
        List<Method> onStopped2 = mediaEventsElement.getOnStopped();
        if (onStopped != onStopped2) {
            if (onStopped == null) {
                return -1;
            }
            if (onStopped2 == null) {
                return 1;
            }
            if (onStopped instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onStopped).compareTo(onStopped2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onStopped.equals(onStopped2)) {
                int iHashCode7 = onStopped.hashCode();
                int iHashCode8 = onStopped2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<Method> onError = getOnError();
        List<Method> onError2 = mediaEventsElement.getOnError();
        if (onError != onError2) {
            if (onError == null) {
                return -1;
            }
            if (onError2 == null) {
                return 1;
            }
            if (onError instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onError).compareTo(onError2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onError.equals(onError2)) {
                int iHashCode9 = onError.hashCode();
                int iHashCode10 = onError2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        List<Method> onCompletedThresholdElapsed = getOnCompletedThresholdElapsed();
        List<Method> onCompletedThresholdElapsed2 = mediaEventsElement.getOnCompletedThresholdElapsed();
        if (onCompletedThresholdElapsed != onCompletedThresholdElapsed2) {
            if (onCompletedThresholdElapsed == null) {
                return -1;
            }
            if (onCompletedThresholdElapsed2 == null) {
                return 1;
            }
            if (onCompletedThresholdElapsed instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onCompletedThresholdElapsed).compareTo(onCompletedThresholdElapsed2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onCompletedThresholdElapsed.equals(onCompletedThresholdElapsed2)) {
                int iHashCode11 = onCompletedThresholdElapsed.hashCode();
                int iHashCode12 = onCompletedThresholdElapsed2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        List<Method> onPlaybackSpeedModified = getOnPlaybackSpeedModified();
        List<Method> onPlaybackSpeedModified2 = mediaEventsElement.getOnPlaybackSpeedModified();
        if (onPlaybackSpeedModified != onPlaybackSpeedModified2) {
            if (onPlaybackSpeedModified == null) {
                return -1;
            }
            if (onPlaybackSpeedModified2 == null) {
                return 1;
            }
            if (onPlaybackSpeedModified instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onPlaybackSpeedModified).compareTo(onPlaybackSpeedModified2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onPlaybackSpeedModified.equals(onPlaybackSpeedModified2)) {
                int iHashCode13 = onPlaybackSpeedModified.hashCode();
                int iHashCode14 = onPlaybackSpeedModified2.hashCode();
                if (iHashCode13 < iHashCode14) {
                    return -1;
                }
                if (iHashCode13 > iHashCode14) {
                    return 1;
                }
            }
        }
        List<Method> onSleepTimerElapsed = getOnSleepTimerElapsed();
        List<Method> onSleepTimerElapsed2 = mediaEventsElement.getOnSleepTimerElapsed();
        if (onSleepTimerElapsed != onSleepTimerElapsed2) {
            if (onSleepTimerElapsed == null) {
                return -1;
            }
            if (onSleepTimerElapsed2 == null) {
                return 1;
            }
            if (onSleepTimerElapsed instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onSleepTimerElapsed).compareTo(onSleepTimerElapsed2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onSleepTimerElapsed.equals(onSleepTimerElapsed2)) {
                int iHashCode15 = onSleepTimerElapsed.hashCode();
                int iHashCode16 = onSleepTimerElapsed2.hashCode();
                if (iHashCode15 < iHashCode16) {
                    return -1;
                }
                if (iHashCode15 > iHashCode16) {
                    return 1;
                }
            }
        }
        List<Method> onWriteFrequencyReached = getOnWriteFrequencyReached();
        List<Method> onWriteFrequencyReached2 = mediaEventsElement.getOnWriteFrequencyReached();
        if (onWriteFrequencyReached != onWriteFrequencyReached2) {
            if (onWriteFrequencyReached == null) {
                return -1;
            }
            if (onWriteFrequencyReached2 == null) {
                return 1;
            }
            if (onWriteFrequencyReached instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onWriteFrequencyReached).compareTo(onWriteFrequencyReached2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!onWriteFrequencyReached.equals(onWriteFrequencyReached2)) {
                int iHashCode17 = onWriteFrequencyReached.hashCode();
                int iHashCode18 = onWriteFrequencyReached2.hashCode();
                if (iHashCode17 < iHashCode18) {
                    return -1;
                }
                if (iHashCode17 > iHashCode18) {
                    return 1;
                }
            }
        }
        List<Method> onPostScrubbed = getOnPostScrubbed();
        List<Method> onPostScrubbed2 = mediaEventsElement.getOnPostScrubbed();
        if (onPostScrubbed != onPostScrubbed2) {
            if (onPostScrubbed == null) {
                return -1;
            }
            if (onPostScrubbed2 == null) {
                return 1;
            }
            if (onPostScrubbed instanceof Comparable) {
                int iCompareTo10 = ((Comparable) onPostScrubbed).compareTo(onPostScrubbed2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!onPostScrubbed.equals(onPostScrubbed2)) {
                int iHashCode19 = onPostScrubbed.hashCode();
                int iHashCode20 = onPostScrubbed2.hashCode();
                if (iHashCode19 < iHashCode20) {
                    return -1;
                }
                if (iHashCode19 > iHashCode20) {
                    return 1;
                }
            }
        }
        List<Method> onPaused = getOnPaused();
        List<Method> onPaused2 = mediaEventsElement.getOnPaused();
        if (onPaused != onPaused2) {
            if (onPaused == null) {
                return -1;
            }
            if (onPaused2 == null) {
                return 1;
            }
            if (onPaused instanceof Comparable) {
                int iCompareTo11 = ((Comparable) onPaused).compareTo(onPaused2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!onPaused.equals(onPaused2)) {
                int iHashCode21 = onPaused.hashCode();
                int iHashCode22 = onPaused2.hashCode();
                if (iHashCode21 < iHashCode22) {
                    return -1;
                }
                if (iHashCode21 > iHashCode22) {
                    return 1;
                }
            }
        }
        List<Method> onWriteAndReportFrequencyReached = getOnWriteAndReportFrequencyReached();
        List<Method> onWriteAndReportFrequencyReached2 = mediaEventsElement.getOnWriteAndReportFrequencyReached();
        if (onWriteAndReportFrequencyReached != onWriteAndReportFrequencyReached2) {
            if (onWriteAndReportFrequencyReached == null) {
                return -1;
            }
            if (onWriteAndReportFrequencyReached2 == null) {
                return 1;
            }
            if (onWriteAndReportFrequencyReached instanceof Comparable) {
                int iCompareTo12 = ((Comparable) onWriteAndReportFrequencyReached).compareTo(onWriteAndReportFrequencyReached2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!onWriteAndReportFrequencyReached.equals(onWriteAndReportFrequencyReached2)) {
                int iHashCode23 = onWriteAndReportFrequencyReached.hashCode();
                int iHashCode24 = onWriteAndReportFrequencyReached2.hashCode();
                if (iHashCode23 < iHashCode24) {
                    return -1;
                }
                if (iHashCode23 > iHashCode24) {
                    return 1;
                }
            }
        }
        List<Method> onRewind = getOnRewind();
        List<Method> onRewind2 = mediaEventsElement.getOnRewind();
        if (onRewind != onRewind2) {
            if (onRewind == null) {
                return -1;
            }
            if (onRewind2 == null) {
                return 1;
            }
            if (onRewind instanceof Comparable) {
                int iCompareTo13 = ((Comparable) onRewind).compareTo(onRewind2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!onRewind.equals(onRewind2)) {
                int iHashCode25 = onRewind.hashCode();
                int iHashCode26 = onRewind2.hashCode();
                if (iHashCode25 < iHashCode26) {
                    return -1;
                }
                if (iHashCode25 > iHashCode26) {
                    return 1;
                }
            }
        }
        List<Method> onResumed = getOnResumed();
        List<Method> onResumed2 = mediaEventsElement.getOnResumed();
        if (onResumed != onResumed2) {
            if (onResumed == null) {
                return -1;
            }
            if (onResumed2 == null) {
                return 1;
            }
            if (onResumed instanceof Comparable) {
                int iCompareTo14 = ((Comparable) onResumed).compareTo(onResumed2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!onResumed.equals(onResumed2)) {
                int iHashCode27 = onResumed.hashCode();
                int iHashCode28 = onResumed2.hashCode();
                if (iHashCode27 < iHashCode28) {
                    return -1;
                }
                if (iHashCode27 > iHashCode28) {
                    return 1;
                }
            }
        }
        List<Method> onRebuffered = getOnRebuffered();
        List<Method> onRebuffered2 = mediaEventsElement.getOnRebuffered();
        if (onRebuffered != onRebuffered2) {
            if (onRebuffered == null) {
                return -1;
            }
            if (onRebuffered2 == null) {
                return 1;
            }
            if (onRebuffered instanceof Comparable) {
                int iCompareTo15 = ((Comparable) onRebuffered).compareTo(onRebuffered2);
                if (iCompareTo15 != 0) {
                    return iCompareTo15;
                }
            } else if (!onRebuffered.equals(onRebuffered2)) {
                int iHashCode29 = onRebuffered.hashCode();
                int iHashCode30 = onRebuffered2.hashCode();
                if (iHashCode29 < iHashCode30) {
                    return -1;
                }
                if (iHashCode29 > iHashCode30) {
                    return 1;
                }
            }
        }
        List<Method> onPreScrubbed = getOnPreScrubbed();
        List<Method> onPreScrubbed2 = mediaEventsElement.getOnPreScrubbed();
        if (onPreScrubbed != onPreScrubbed2) {
            if (onPreScrubbed == null) {
                return -1;
            }
            if (onPreScrubbed2 == null) {
                return 1;
            }
            if (onPreScrubbed instanceof Comparable) {
                int iCompareTo16 = ((Comparable) onPreScrubbed).compareTo(onPreScrubbed2);
                if (iCompareTo16 != 0) {
                    return iCompareTo16;
                }
            } else if (!onPreScrubbed.equals(onPreScrubbed2)) {
                int iHashCode31 = onPreScrubbed.hashCode();
                int iHashCode32 = onPreScrubbed2.hashCode();
                if (iHashCode31 < iHashCode32) {
                    return -1;
                }
                if (iHashCode31 > iHashCode32) {
                    return 1;
                }
            }
        }
        List<Method> onScrubbed = getOnScrubbed();
        List<Method> onScrubbed2 = mediaEventsElement.getOnScrubbed();
        if (onScrubbed != onScrubbed2) {
            if (onScrubbed == null) {
                return -1;
            }
            if (onScrubbed2 == null) {
                return 1;
            }
            if (onScrubbed instanceof Comparable) {
                int iCompareTo17 = ((Comparable) onScrubbed).compareTo(onScrubbed2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!onScrubbed.equals(onScrubbed2)) {
                int iHashCode33 = onScrubbed.hashCode();
                int iHashCode34 = onScrubbed2.hashCode();
                if (iHashCode33 < iHashCode34) {
                    return -1;
                }
                if (iHashCode33 > iHashCode34) {
                    return 1;
                }
            }
        }
        List<Method> onStarted = getOnStarted();
        List<Method> onStarted2 = mediaEventsElement.getOnStarted();
        if (onStarted != onStarted2) {
            if (onStarted == null) {
                return -1;
            }
            if (onStarted2 == null) {
                return 1;
            }
            if (onStarted instanceof Comparable) {
                int iCompareTo18 = ((Comparable) onStarted).compareTo(onStarted2);
                if (iCompareTo18 != 0) {
                    return iCompareTo18;
                }
            } else if (!onStarted.equals(onStarted2)) {
                int iHashCode35 = onStarted.hashCode();
                int iHashCode36 = onStarted2.hashCode();
                if (iHashCode35 < iHashCode36) {
                    return -1;
                }
                if (iHashCode35 > iHashCode36) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof MediaEventsElement)) {
            return false;
        }
        MediaEventsElement mediaEventsElement = (MediaEventsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnFinished(), mediaEventsElement.getOnFinished()) && internalEqualityCheck(getOnSleepTimerSet(), mediaEventsElement.getOnSleepTimerSet()) && internalEqualityCheck(getOnFastForward(), mediaEventsElement.getOnFastForward()) && internalEqualityCheck(getOnStopped(), mediaEventsElement.getOnStopped()) && internalEqualityCheck(getOnError(), mediaEventsElement.getOnError()) && internalEqualityCheck(getOnCompletedThresholdElapsed(), mediaEventsElement.getOnCompletedThresholdElapsed()) && internalEqualityCheck(getOnPlaybackSpeedModified(), mediaEventsElement.getOnPlaybackSpeedModified()) && internalEqualityCheck(getOnSleepTimerElapsed(), mediaEventsElement.getOnSleepTimerElapsed()) && internalEqualityCheck(getOnWriteFrequencyReached(), mediaEventsElement.getOnWriteFrequencyReached()) && internalEqualityCheck(getOnPostScrubbed(), mediaEventsElement.getOnPostScrubbed()) && internalEqualityCheck(getOnPaused(), mediaEventsElement.getOnPaused()) && internalEqualityCheck(getOnWriteAndReportFrequencyReached(), mediaEventsElement.getOnWriteAndReportFrequencyReached()) && internalEqualityCheck(getOnRewind(), mediaEventsElement.getOnRewind()) && internalEqualityCheck(getOnResumed(), mediaEventsElement.getOnResumed()) && internalEqualityCheck(getOnRebuffered(), mediaEventsElement.getOnRebuffered()) && internalEqualityCheck(getOnPreScrubbed(), mediaEventsElement.getOnPreScrubbed()) && internalEqualityCheck(getOnScrubbed(), mediaEventsElement.getOnScrubbed()) && internalEqualityCheck(getOnStarted(), mediaEventsElement.getOnStarted());
    }

    public List<Method> getOnCompletedThresholdElapsed() {
        return this.onCompletedThresholdElapsed;
    }

    public List<Method> getOnError() {
        return this.onError;
    }

    public List<Method> getOnFastForward() {
        return this.onFastForward;
    }

    public List<Method> getOnFinished() {
        return this.onFinished;
    }

    public List<Method> getOnPaused() {
        return this.onPaused;
    }

    public List<Method> getOnPlaybackSpeedModified() {
        return this.onPlaybackSpeedModified;
    }

    public List<Method> getOnPostScrubbed() {
        return this.onPostScrubbed;
    }

    public List<Method> getOnPreScrubbed() {
        return this.onPreScrubbed;
    }

    public List<Method> getOnRebuffered() {
        return this.onRebuffered;
    }

    public List<Method> getOnResumed() {
        return this.onResumed;
    }

    public List<Method> getOnRewind() {
        return this.onRewind;
    }

    public List<Method> getOnScrubbed() {
        return this.onScrubbed;
    }

    public List<Method> getOnSleepTimerElapsed() {
        return this.onSleepTimerElapsed;
    }

    public List<Method> getOnSleepTimerSet() {
        return this.onSleepTimerSet;
    }

    public List<Method> getOnStarted() {
        return this.onStarted;
    }

    public List<Method> getOnStopped() {
        return this.onStopped;
    }

    public List<Method> getOnWriteAndReportFrequencyReached() {
        return this.onWriteAndReportFrequencyReached;
    }

    public List<Method> getOnWriteFrequencyReached() {
        return this.onWriteFrequencyReached;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnFinished(), getOnSleepTimerSet(), getOnFastForward(), getOnStopped(), getOnError(), getOnCompletedThresholdElapsed(), getOnPlaybackSpeedModified(), getOnSleepTimerElapsed(), getOnWriteFrequencyReached(), getOnPostScrubbed(), getOnPaused(), getOnWriteAndReportFrequencyReached(), getOnRewind(), getOnResumed(), getOnRebuffered(), getOnPreScrubbed(), getOnScrubbed(), getOnStarted());
    }

    public void setOnCompletedThresholdElapsed(List<Method> list) {
        this.onCompletedThresholdElapsed = list;
    }

    public void setOnError(List<Method> list) {
        this.onError = list;
    }

    public void setOnFastForward(List<Method> list) {
        this.onFastForward = list;
    }

    public void setOnFinished(List<Method> list) {
        this.onFinished = list;
    }

    public void setOnPaused(List<Method> list) {
        this.onPaused = list;
    }

    public void setOnPlaybackSpeedModified(List<Method> list) {
        this.onPlaybackSpeedModified = list;
    }

    public void setOnPostScrubbed(List<Method> list) {
        this.onPostScrubbed = list;
    }

    public void setOnPreScrubbed(List<Method> list) {
        this.onPreScrubbed = list;
    }

    public void setOnRebuffered(List<Method> list) {
        this.onRebuffered = list;
    }

    public void setOnResumed(List<Method> list) {
        this.onResumed = list;
    }

    public void setOnRewind(List<Method> list) {
        this.onRewind = list;
    }

    public void setOnScrubbed(List<Method> list) {
        this.onScrubbed = list;
    }

    public void setOnSleepTimerElapsed(List<Method> list) {
        this.onSleepTimerElapsed = list;
    }

    public void setOnSleepTimerSet(List<Method> list) {
        this.onSleepTimerSet = list;
    }

    public void setOnStarted(List<Method> list) {
        this.onStarted = list;
    }

    public void setOnStopped(List<Method> list) {
        this.onStopped = list;
    }

    public void setOnWriteAndReportFrequencyReached(List<Method> list) {
        this.onWriteAndReportFrequencyReached = list;
    }

    public void setOnWriteFrequencyReached(List<Method> list) {
        this.onWriteFrequencyReached = list;
    }
}
