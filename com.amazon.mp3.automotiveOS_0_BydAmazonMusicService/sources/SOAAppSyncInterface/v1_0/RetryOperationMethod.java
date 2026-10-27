package SOAAppSyncInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RetryOperationMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.RetryOperationMethod");
    private List<String> operations;

    public static class Builder extends Method.Builder {
        protected List<String> operations;

        public RetryOperationMethod build() {
            RetryOperationMethod retryOperationMethod = new RetryOperationMethod();
            populate(retryOperationMethod);
            return retryOperationMethod;
        }

        protected void populate(RetryOperationMethod retryOperationMethod) {
            super.populate((Method) retryOperationMethod);
            retryOperationMethod.setOperations(this.operations);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withOperations(List<String> list) {
            this.operations = list;
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
        if (!(sOAObject instanceof RetryOperationMethod)) {
            return 1;
        }
        List<String> operations = getOperations();
        List<String> operations2 = ((RetryOperationMethod) sOAObject).getOperations();
        if (operations != operations2) {
            if (operations == null) {
                return -1;
            }
            if (operations2 == null) {
                return 1;
            }
            if (operations instanceof Comparable) {
                int iCompareTo = ((Comparable) operations).compareTo(operations2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!operations.equals(operations2)) {
                int iHashCode = operations.hashCode();
                int iHashCode2 = operations2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof RetryOperationMethod) {
            return super.equals(obj) && internalEqualityCheck(getOperations(), ((RetryOperationMethod) obj).getOperations());
        }
        return false;
    }

    public List<String> getOperations() {
        return this.operations;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOperations());
    }

    public void setOperations(List<String> list) {
        this.operations = list;
    }
}
