package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class Method extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Method");
    private Boolean forced;
    private Queue queue;

    public static abstract class Builder extends SOAObject.Builder {
        protected Boolean forced;
        protected Queue queue;

        protected void populate(Method method) {
            super.populate((SOAObject) method);
            method.setQueue(this.queue);
            method.setForced(this.forced);
        }

        public Builder withForced(Boolean bool) {
            this.forced = bool;
            return this;
        }

        public Builder withQueue(Queue queue) {
            this.queue = queue;
            return this;
        }
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof Method)) {
            return 1;
        }
        Method method = (Method) sOAObject;
        Queue queue = getQueue();
        Queue queue2 = method.getQueue();
        if (queue != queue2) {
            if (queue == null) {
                return -1;
            }
            if (queue2 == null) {
                return 1;
            }
            int iCompareTo = queue.compareTo(queue2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsForced = isForced();
        Boolean boolIsForced2 = method.isForced();
        if (boolIsForced != boolIsForced2) {
            if (boolIsForced == null) {
                return -1;
            }
            if (boolIsForced2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsForced.compareTo(boolIsForced2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof Method)) {
            return false;
        }
        Method method = (Method) obj;
        return super.equals(obj) && internalEqualityCheck(getQueue(), method.getQueue()) && internalEqualityCheck(isForced(), method.isForced());
    }

    public Queue getQueue() {
        return this.queue;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQueue(), isForced());
    }

    public Boolean isForced() {
        return this.forced;
    }

    public void setForced(Boolean bool) {
        this.forced = bool;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
