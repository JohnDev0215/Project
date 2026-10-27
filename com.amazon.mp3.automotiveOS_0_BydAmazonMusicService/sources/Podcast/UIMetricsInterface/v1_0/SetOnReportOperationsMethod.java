package Podcast.UIMetricsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SetOnReportOperationsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethod");
    private List<Method> onReportOperations;

    public static class Builder extends Method.Builder {
        protected List<Method> onReportOperations;

        public SetOnReportOperationsMethod build() {
            SetOnReportOperationsMethod setOnReportOperationsMethod = new SetOnReportOperationsMethod();
            populate(setOnReportOperationsMethod);
            return setOnReportOperationsMethod;
        }

        protected void populate(SetOnReportOperationsMethod setOnReportOperationsMethod) {
            super.populate((Method) setOnReportOperationsMethod);
            setOnReportOperationsMethod.setOnReportOperations(this.onReportOperations);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withOnReportOperations(List<Method> list) {
            this.onReportOperations = list;
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
        if (!(sOAObject instanceof SetOnReportOperationsMethod)) {
            return 1;
        }
        List<Method> onReportOperations = getOnReportOperations();
        List<Method> onReportOperations2 = ((SetOnReportOperationsMethod) sOAObject).getOnReportOperations();
        if (onReportOperations != onReportOperations2) {
            if (onReportOperations == null) {
                return -1;
            }
            if (onReportOperations2 == null) {
                return 1;
            }
            if (onReportOperations instanceof Comparable) {
                int iCompareTo = ((Comparable) onReportOperations).compareTo(onReportOperations2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onReportOperations.equals(onReportOperations2)) {
                int iHashCode = onReportOperations.hashCode();
                int iHashCode2 = onReportOperations2.hashCode();
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
        if (obj instanceof SetOnReportOperationsMethod) {
            return super.equals(obj) && internalEqualityCheck(getOnReportOperations(), ((SetOnReportOperationsMethod) obj).getOnReportOperations());
        }
        return false;
    }

    public List<Method> getOnReportOperations() {
        return this.onReportOperations;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnReportOperations());
    }

    public void setOnReportOperations(List<Method> list) {
        this.onReportOperations = list;
    }
}
