package SOACoreInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class SOAObject implements Comparable<SOAObject> {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.SOAObject");

    public static abstract class Builder {
        protected void populate(SOAObject sOAObject) {
        }
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        return sOAObject == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof SOAObject;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
