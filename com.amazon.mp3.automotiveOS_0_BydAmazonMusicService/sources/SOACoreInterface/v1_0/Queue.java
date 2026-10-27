package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class Queue extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Queue");
    private String id;

    public static abstract class Builder extends SOAObject.Builder {
        protected String id;

        protected void populate(Queue queue) {
            super.populate((SOAObject) queue);
            queue.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
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
        if (!(sOAObject instanceof Queue)) {
            return 1;
        }
        String id = getId();
        String id2 = ((Queue) sOAObject).getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof Queue) {
            return super.equals(obj) && internalEqualityCheck(getId(), ((Queue) obj).getId());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId());
    }

    public void setId(String str) {
        this.id = str;
    }
}
