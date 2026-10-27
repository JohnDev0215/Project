package SOAAppSyncInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WriteMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.WriteMethod");
    private ConditionElement condition;
    private List<WriteElement> element;

    public static class Builder extends Method.Builder {
        protected ConditionElement condition;
        protected List<WriteElement> element;

        public WriteMethod build() {
            WriteMethod writeMethod = new WriteMethod();
            populate(writeMethod);
            return writeMethod;
        }

        protected void populate(WriteMethod writeMethod) {
            super.populate((Method) writeMethod);
            writeMethod.setCondition(this.condition);
            writeMethod.setElement(this.element);
        }

        public Builder withCondition(ConditionElement conditionElement) {
            this.condition = conditionElement;
            return this;
        }

        public Builder withElement(List<WriteElement> list) {
            this.element = list;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
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
        if (!(sOAObject instanceof WriteMethod)) {
            return 1;
        }
        WriteMethod writeMethod = (WriteMethod) sOAObject;
        ConditionElement condition = getCondition();
        ConditionElement condition2 = writeMethod.getCondition();
        if (condition != condition2) {
            if (condition == null) {
                return -1;
            }
            if (condition2 == null) {
                return 1;
            }
            int iCompareTo = condition.compareTo(condition2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<WriteElement> element = getElement();
        List<WriteElement> element2 = writeMethod.getElement();
        if (element != element2) {
            if (element == null) {
                return -1;
            }
            if (element2 == null) {
                return 1;
            }
            if (element instanceof Comparable) {
                int iCompareTo2 = ((Comparable) element).compareTo(element2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!element.equals(element2)) {
                int iHashCode = element.hashCode();
                int iHashCode2 = element2.hashCode();
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
        if (!(obj instanceof WriteMethod)) {
            return false;
        }
        WriteMethod writeMethod = (WriteMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getCondition(), writeMethod.getCondition()) && internalEqualityCheck(getElement(), writeMethod.getElement());
    }

    public ConditionElement getCondition() {
        return this.condition;
    }

    public List<WriteElement> getElement() {
        return this.element;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCondition(), getElement());
    }

    public void setCondition(ConditionElement conditionElement) {
        this.condition = conditionElement;
    }

    public void setElement(List<WriteElement> list) {
        this.element = list;
    }
}
