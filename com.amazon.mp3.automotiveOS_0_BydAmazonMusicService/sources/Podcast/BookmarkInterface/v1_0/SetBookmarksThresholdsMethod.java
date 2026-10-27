package Podcast.BookmarkInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetBookmarksThresholdsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethod");
    private Long inProgressLowerBoundMilliseconds;
    private Integer inProgressUpperBoundPercentage;

    public static class Builder extends Method.Builder {
        protected Long inProgressLowerBoundMilliseconds;
        protected Integer inProgressUpperBoundPercentage;

        public SetBookmarksThresholdsMethod build() {
            SetBookmarksThresholdsMethod setBookmarksThresholdsMethod = new SetBookmarksThresholdsMethod();
            populate(setBookmarksThresholdsMethod);
            return setBookmarksThresholdsMethod;
        }

        protected void populate(SetBookmarksThresholdsMethod setBookmarksThresholdsMethod) {
            super.populate((Method) setBookmarksThresholdsMethod);
            setBookmarksThresholdsMethod.setInProgressUpperBoundPercentage(this.inProgressUpperBoundPercentage);
            setBookmarksThresholdsMethod.setInProgressLowerBoundMilliseconds(this.inProgressLowerBoundMilliseconds);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withInProgressLowerBoundMilliseconds(Long l) {
            this.inProgressLowerBoundMilliseconds = l;
            return this;
        }

        public Builder withInProgressUpperBoundPercentage(Integer num) {
            this.inProgressUpperBoundPercentage = num;
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
        if (!(sOAObject instanceof SetBookmarksThresholdsMethod)) {
            return 1;
        }
        SetBookmarksThresholdsMethod setBookmarksThresholdsMethod = (SetBookmarksThresholdsMethod) sOAObject;
        Integer inProgressUpperBoundPercentage = getInProgressUpperBoundPercentage();
        Integer inProgressUpperBoundPercentage2 = setBookmarksThresholdsMethod.getInProgressUpperBoundPercentage();
        if (inProgressUpperBoundPercentage != inProgressUpperBoundPercentage2) {
            if (inProgressUpperBoundPercentage == null) {
                return -1;
            }
            if (inProgressUpperBoundPercentage2 == null) {
                return 1;
            }
            int iCompareTo = inProgressUpperBoundPercentage.compareTo(inProgressUpperBoundPercentage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long inProgressLowerBoundMilliseconds = getInProgressLowerBoundMilliseconds();
        Long inProgressLowerBoundMilliseconds2 = setBookmarksThresholdsMethod.getInProgressLowerBoundMilliseconds();
        if (inProgressLowerBoundMilliseconds != inProgressLowerBoundMilliseconds2) {
            if (inProgressLowerBoundMilliseconds == null) {
                return -1;
            }
            if (inProgressLowerBoundMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo2 = inProgressLowerBoundMilliseconds.compareTo(inProgressLowerBoundMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetBookmarksThresholdsMethod)) {
            return false;
        }
        SetBookmarksThresholdsMethod setBookmarksThresholdsMethod = (SetBookmarksThresholdsMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getInProgressUpperBoundPercentage(), setBookmarksThresholdsMethod.getInProgressUpperBoundPercentage()) && internalEqualityCheck(getInProgressLowerBoundMilliseconds(), setBookmarksThresholdsMethod.getInProgressLowerBoundMilliseconds());
    }

    public Long getInProgressLowerBoundMilliseconds() {
        return this.inProgressLowerBoundMilliseconds;
    }

    public Integer getInProgressUpperBoundPercentage() {
        return this.inProgressUpperBoundPercentage;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getInProgressUpperBoundPercentage(), getInProgressLowerBoundMilliseconds());
    }

    public void setInProgressLowerBoundMilliseconds(Long l) {
        this.inProgressLowerBoundMilliseconds = l;
    }

    public void setInProgressUpperBoundPercentage(Integer num) {
        this.inProgressUpperBoundPercentage = num;
    }
}
