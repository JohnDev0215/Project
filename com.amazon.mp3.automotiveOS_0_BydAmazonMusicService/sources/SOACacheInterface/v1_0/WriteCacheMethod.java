package SOACacheInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class WriteCacheMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOACacheInterface.v1_0.WriteCacheMethod");
    private WriteCacheElement element;

    public static class Builder extends Method.Builder {
        protected WriteCacheElement element;

        public WriteCacheMethod build() {
            WriteCacheMethod writeCacheMethod = new WriteCacheMethod();
            populate(writeCacheMethod);
            return writeCacheMethod;
        }

        protected void populate(WriteCacheMethod writeCacheMethod) {
            super.populate((Method) writeCacheMethod);
            writeCacheMethod.setElement(this.element);
        }

        public Builder withElement(WriteCacheElement writeCacheElement) {
            this.element = writeCacheElement;
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
        if (!(sOAObject instanceof WriteCacheMethod)) {
            return 1;
        }
        WriteCacheElement element = getElement();
        WriteCacheElement element2 = ((WriteCacheMethod) sOAObject).getElement();
        if (element != element2) {
            if (element == null) {
                return -1;
            }
            if (element2 == null) {
                return 1;
            }
            int iCompareTo = element.compareTo(element2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof WriteCacheMethod) {
            return super.equals(obj) && internalEqualityCheck(getElement(), ((WriteCacheMethod) obj).getElement());
        }
        return false;
    }

    public WriteCacheElement getElement() {
        return this.element;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getElement());
    }

    public void setElement(WriteCacheElement writeCacheElement) {
        this.element = writeCacheElement;
    }
}
