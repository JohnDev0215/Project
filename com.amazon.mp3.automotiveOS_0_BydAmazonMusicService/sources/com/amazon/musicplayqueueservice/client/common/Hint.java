package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Hint implements Comparable<Hint> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.Hint");
    private String hint;
    private String id;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        String id = getId();
        String id2 = hint.getId();
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
        String hint2 = getHint();
        String hint3 = hint.getHint();
        if (hint2 != hint3) {
            if (hint2 == null) {
                return -1;
            }
            if (hint3 == null) {
                return 1;
            }
            int iCompareTo2 = hint2.compareTo(hint3);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Hint)) {
            return false;
        }
        Hint hint = (Hint) obj;
        return internalEqualityCheck(getId(), hint.getId()) && internalEqualityCheck(getHint(), hint.getHint());
    }

    public String getHint() {
        return this.hint;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), getHint());
    }

    public void setHint(String str) {
        this.hint = str;
    }

    public void setId(String str) {
        this.id = str;
    }
}
