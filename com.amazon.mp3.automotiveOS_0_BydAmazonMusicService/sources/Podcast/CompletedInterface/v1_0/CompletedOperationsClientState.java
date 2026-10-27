package Podcast.CompletedInterface.v1_0;

import SOAAppSyncInterface.v1_0.OperationElement;
import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompletedOperationsClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.CompletedInterface.v1_0.CompletedOperationsClientState");
    private List<OperationElement> operations;

    public static class Builder extends ClientState.Builder {
        protected List<OperationElement> operations;

        public CompletedOperationsClientState build() {
            CompletedOperationsClientState completedOperationsClientState = new CompletedOperationsClientState();
            populate(completedOperationsClientState);
            return completedOperationsClientState;
        }

        protected void populate(CompletedOperationsClientState completedOperationsClientState) {
            super.populate((ClientState) completedOperationsClientState);
            completedOperationsClientState.setOperations(this.operations);
        }

        public Builder withOperations(List<OperationElement> list) {
            this.operations = list;
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
    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof CompletedOperationsClientState)) {
            return 1;
        }
        List<OperationElement> operations = getOperations();
        List<OperationElement> operations2 = ((CompletedOperationsClientState) sOAObject).getOperations();
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

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CompletedOperationsClientState) {
            return super.equals(obj) && internalEqualityCheck(getOperations(), ((CompletedOperationsClientState) obj).getOperations());
        }
        return false;
    }

    public List<OperationElement> getOperations() {
        return this.operations;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOperations());
    }

    public void setOperations(List<OperationElement> list) {
        this.operations = list;
    }
}
