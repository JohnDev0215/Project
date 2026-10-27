package SOAAppSyncInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class OperationElement implements Comparable<OperationElement> {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.OperationElement");
    private ConditionElement condition;
    private WriteElement element;
    private String id;

    public static class Builder {
        protected ConditionElement condition;
        protected WriteElement element;
        protected String id;

        public OperationElement build() {
            OperationElement operationElement = new OperationElement();
            populate(operationElement);
            return operationElement;
        }

        protected void populate(OperationElement operationElement) {
            operationElement.setCondition(this.condition);
            operationElement.setId(this.id);
            operationElement.setElement(this.element);
        }

        public Builder withCondition(ConditionElement conditionElement) {
            this.condition = conditionElement;
            return this;
        }

        public Builder withElement(WriteElement writeElement) {
            this.element = writeElement;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated OperationElement operationElement) {
        if (operationElement == null) {
            return -1;
        }
        if (operationElement == this) {
            return 0;
        }
        ConditionElement condition = getCondition();
        ConditionElement condition2 = operationElement.getCondition();
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
        String id = getId();
        String id2 = operationElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        WriteElement element = getElement();
        WriteElement element2 = operationElement.getElement();
        if (element != element2) {
            if (element == null) {
                return -1;
            }
            if (element2 == null) {
                return 1;
            }
            int iCompareTo3 = element.compareTo(element2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OperationElement)) {
            return false;
        }
        OperationElement operationElement = (OperationElement) obj;
        return internalEqualityCheck(getCondition(), operationElement.getCondition()) && internalEqualityCheck(getId(), operationElement.getId()) && internalEqualityCheck(getElement(), operationElement.getElement());
    }

    public ConditionElement getCondition() {
        return this.condition;
    }

    public WriteElement getElement() {
        return this.element;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCondition(), getId(), getElement());
    }

    public void setCondition(ConditionElement conditionElement) {
        this.condition = conditionElement;
    }

    public void setElement(WriteElement writeElement) {
        this.element = writeElement;
    }

    public void setId(String str) {
        this.id = str;
    }
}
