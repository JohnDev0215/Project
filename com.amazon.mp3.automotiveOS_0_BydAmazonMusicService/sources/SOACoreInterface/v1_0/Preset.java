package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class Preset extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Preset");

    public static abstract class Builder extends SOAObject.Builder {
        protected void populate(Preset preset) {
            super.populate((SOAObject) preset);
        }
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
        if (sOAObject instanceof Preset) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof Preset) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
