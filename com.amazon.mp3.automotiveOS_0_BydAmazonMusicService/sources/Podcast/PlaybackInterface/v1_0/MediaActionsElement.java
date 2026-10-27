package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaActionsElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.MediaActionsElement");
    private List<Method> onNext;
    private List<Method> onPrevious;

    public static class Builder extends PlaybackElement.Builder {
        protected List<Method> onNext;
        protected List<Method> onPrevious;

        public MediaActionsElement build() {
            MediaActionsElement mediaActionsElement = new MediaActionsElement();
            populate(mediaActionsElement);
            return mediaActionsElement;
        }

        protected void populate(MediaActionsElement mediaActionsElement) {
            super.populate((PlaybackElement) mediaActionsElement);
            mediaActionsElement.setOnPrevious(this.onPrevious);
            mediaActionsElement.setOnNext(this.onNext);
        }

        public Builder withOnNext(List<Method> list) {
            this.onNext = list;
            return this;
        }

        public Builder withOnPrevious(List<Method> list) {
            this.onPrevious = list;
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
        if (!(sOAObject instanceof MediaActionsElement)) {
            return 1;
        }
        MediaActionsElement mediaActionsElement = (MediaActionsElement) sOAObject;
        List<Method> onPrevious = getOnPrevious();
        List<Method> onPrevious2 = mediaActionsElement.getOnPrevious();
        if (onPrevious != onPrevious2) {
            if (onPrevious == null) {
                return -1;
            }
            if (onPrevious2 == null) {
                return 1;
            }
            if (onPrevious instanceof Comparable) {
                int iCompareTo = ((Comparable) onPrevious).compareTo(onPrevious2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onPrevious.equals(onPrevious2)) {
                int iHashCode = onPrevious.hashCode();
                int iHashCode2 = onPrevious2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onNext = getOnNext();
        List<Method> onNext2 = mediaActionsElement.getOnNext();
        if (onNext != onNext2) {
            if (onNext == null) {
                return -1;
            }
            if (onNext2 == null) {
                return 1;
            }
            if (onNext instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onNext).compareTo(onNext2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onNext.equals(onNext2)) {
                int iHashCode3 = onNext.hashCode();
                int iHashCode4 = onNext2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof MediaActionsElement)) {
            return false;
        }
        MediaActionsElement mediaActionsElement = (MediaActionsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnPrevious(), mediaActionsElement.getOnPrevious()) && internalEqualityCheck(getOnNext(), mediaActionsElement.getOnNext());
    }

    public List<Method> getOnNext() {
        return this.onNext;
    }

    public List<Method> getOnPrevious() {
        return this.onPrevious;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnPrevious(), getOnNext());
    }

    public void setOnNext(List<Method> list) {
        this.onNext = list;
    }

    public void setOnPrevious(List<Method> list) {
        this.onPrevious = list;
    }
}
