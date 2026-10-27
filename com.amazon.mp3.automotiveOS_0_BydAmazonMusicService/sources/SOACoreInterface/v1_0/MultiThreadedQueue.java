package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.Queue;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MultiThreadedQueue extends Queue {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.MultiThreadedQueue");

    public static class Builder extends Queue.Builder {
        public MultiThreadedQueue build() {
            MultiThreadedQueue multiThreadedQueue = new MultiThreadedQueue();
            populate(multiThreadedQueue);
            return multiThreadedQueue;
        }

        protected void populate(MultiThreadedQueue multiThreadedQueue) {
            super.populate((Queue) multiThreadedQueue);
        }

        @Override // SOACoreInterface.v1_0.Queue.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.Queue, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (sOAObject instanceof MultiThreadedQueue) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACoreInterface.v1_0.Queue, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof MultiThreadedQueue) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.Queue, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
