package SOACacheInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WriteCacheElement extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACacheInterface.v1_0.WriteCacheElement");
    private List<Method> onInvalidated;

    public static class Builder extends SOAObject.Builder {
        protected List<Method> onInvalidated;

        public WriteCacheElement build() {
            WriteCacheElement writeCacheElement = new WriteCacheElement();
            populate(writeCacheElement);
            return writeCacheElement;
        }

        protected void populate(WriteCacheElement writeCacheElement) {
            super.populate((SOAObject) writeCacheElement);
            writeCacheElement.setOnInvalidated(this.onInvalidated);
        }

        public Builder withOnInvalidated(List<Method> list) {
            this.onInvalidated = list;
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
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof WriteCacheElement)) {
            return 1;
        }
        List<Method> onInvalidated = getOnInvalidated();
        List<Method> onInvalidated2 = ((WriteCacheElement) sOAObject).getOnInvalidated();
        if (onInvalidated != onInvalidated2) {
            if (onInvalidated == null) {
                return -1;
            }
            if (onInvalidated2 == null) {
                return 1;
            }
            if (onInvalidated instanceof Comparable) {
                int iCompareTo = ((Comparable) onInvalidated).compareTo(onInvalidated2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onInvalidated.equals(onInvalidated2)) {
                int iHashCode = onInvalidated.hashCode();
                int iHashCode2 = onInvalidated2.hashCode();
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

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof WriteCacheElement) {
            return super.equals(obj) && internalEqualityCheck(getOnInvalidated(), ((WriteCacheElement) obj).getOnInvalidated());
        }
        return false;
    }

    public List<Method> getOnInvalidated() {
        return this.onInvalidated;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnInvalidated());
    }

    public void setOnInvalidated(List<Method> list) {
        this.onInvalidated = list;
    }
}
