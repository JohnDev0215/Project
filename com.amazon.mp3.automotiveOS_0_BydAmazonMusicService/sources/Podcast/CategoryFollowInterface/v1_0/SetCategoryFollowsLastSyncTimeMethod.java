package Podcast.CategoryFollowInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetCategoryFollowsLastSyncTimeMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethod");
    private Long lastSyncTime;

    public static class Builder extends Method.Builder {
        protected Long lastSyncTime;

        public SetCategoryFollowsLastSyncTimeMethod build() {
            SetCategoryFollowsLastSyncTimeMethod setCategoryFollowsLastSyncTimeMethod = new SetCategoryFollowsLastSyncTimeMethod();
            populate(setCategoryFollowsLastSyncTimeMethod);
            return setCategoryFollowsLastSyncTimeMethod;
        }

        protected void populate(SetCategoryFollowsLastSyncTimeMethod setCategoryFollowsLastSyncTimeMethod) {
            super.populate((Method) setCategoryFollowsLastSyncTimeMethod);
            setCategoryFollowsLastSyncTimeMethod.setLastSyncTime(this.lastSyncTime);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withLastSyncTime(Long l) {
            this.lastSyncTime = l;
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
        if (!(sOAObject instanceof SetCategoryFollowsLastSyncTimeMethod)) {
            return 1;
        }
        Long lastSyncTime = getLastSyncTime();
        Long lastSyncTime2 = ((SetCategoryFollowsLastSyncTimeMethod) sOAObject).getLastSyncTime();
        if (lastSyncTime != lastSyncTime2) {
            if (lastSyncTime == null) {
                return -1;
            }
            if (lastSyncTime2 == null) {
                return 1;
            }
            int iCompareTo = lastSyncTime.compareTo(lastSyncTime2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SetCategoryFollowsLastSyncTimeMethod) {
            return super.equals(obj) && internalEqualityCheck(getLastSyncTime(), ((SetCategoryFollowsLastSyncTimeMethod) obj).getLastSyncTime());
        }
        return false;
    }

    public Long getLastSyncTime() {
        return this.lastSyncTime;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLastSyncTime());
    }

    public void setLastSyncTime(Long l) {
        this.lastSyncTime = l;
    }
}
