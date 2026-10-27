package SOAAppSyncInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConditionElement implements Comparable<ConditionElement> {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.ConditionElement");

    public static abstract class Builder {
        protected void populate(ConditionElement conditionElement) {
        }
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ConditionElement conditionElement) {
        return conditionElement == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof ConditionElement;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
