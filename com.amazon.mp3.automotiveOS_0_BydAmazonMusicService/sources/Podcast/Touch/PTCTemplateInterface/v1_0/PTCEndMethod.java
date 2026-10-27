package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCEndMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethod");
    private List<Method> onTrailerNotPlayed;
    private List<Method> onTrailerPlayed;

    public static class Builder extends Method.Builder {
        protected List<Method> onTrailerNotPlayed;
        protected List<Method> onTrailerPlayed;

        public PTCEndMethod build() {
            PTCEndMethod pTCEndMethod = new PTCEndMethod();
            populate(pTCEndMethod);
            return pTCEndMethod;
        }

        protected void populate(PTCEndMethod pTCEndMethod) {
            super.populate((Method) pTCEndMethod);
            pTCEndMethod.setOnTrailerNotPlayed(this.onTrailerNotPlayed);
            pTCEndMethod.setOnTrailerPlayed(this.onTrailerPlayed);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withOnTrailerNotPlayed(List<Method> list) {
            this.onTrailerNotPlayed = list;
            return this;
        }

        public Builder withOnTrailerPlayed(List<Method> list) {
            this.onTrailerPlayed = list;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
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
        if (!(sOAObject instanceof PTCEndMethod)) {
            return 1;
        }
        PTCEndMethod pTCEndMethod = (PTCEndMethod) sOAObject;
        List<Method> onTrailerNotPlayed = getOnTrailerNotPlayed();
        List<Method> onTrailerNotPlayed2 = pTCEndMethod.getOnTrailerNotPlayed();
        if (onTrailerNotPlayed != onTrailerNotPlayed2) {
            if (onTrailerNotPlayed == null) {
                return -1;
            }
            if (onTrailerNotPlayed2 == null) {
                return 1;
            }
            if (onTrailerNotPlayed instanceof Comparable) {
                int iCompareTo = ((Comparable) onTrailerNotPlayed).compareTo(onTrailerNotPlayed2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onTrailerNotPlayed.equals(onTrailerNotPlayed2)) {
                int iHashCode = onTrailerNotPlayed.hashCode();
                int iHashCode2 = onTrailerNotPlayed2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onTrailerPlayed = getOnTrailerPlayed();
        List<Method> onTrailerPlayed2 = pTCEndMethod.getOnTrailerPlayed();
        if (onTrailerPlayed != onTrailerPlayed2) {
            if (onTrailerPlayed == null) {
                return -1;
            }
            if (onTrailerPlayed2 == null) {
                return 1;
            }
            if (onTrailerPlayed instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onTrailerPlayed).compareTo(onTrailerPlayed2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onTrailerPlayed.equals(onTrailerPlayed2)) {
                int iHashCode3 = onTrailerPlayed.hashCode();
                int iHashCode4 = onTrailerPlayed2.hashCode();
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

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCEndMethod)) {
            return false;
        }
        PTCEndMethod pTCEndMethod = (PTCEndMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getOnTrailerNotPlayed(), pTCEndMethod.getOnTrailerNotPlayed()) && internalEqualityCheck(getOnTrailerPlayed(), pTCEndMethod.getOnTrailerPlayed());
    }

    public List<Method> getOnTrailerNotPlayed() {
        return this.onTrailerNotPlayed;
    }

    public List<Method> getOnTrailerPlayed() {
        return this.onTrailerPlayed;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnTrailerNotPlayed(), getOnTrailerPlayed());
    }

    public void setOnTrailerNotPlayed(List<Method> list) {
        this.onTrailerNotPlayed = list;
    }

    public void setOnTrailerPlayed(List<Method> list) {
        this.onTrailerPlayed = list;
    }
}
