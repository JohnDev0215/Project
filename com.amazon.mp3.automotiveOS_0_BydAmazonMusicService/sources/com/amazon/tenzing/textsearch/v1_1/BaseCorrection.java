package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseCorrection implements Comparable<BaseCorrection> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.BaseCorrection");
    private String id;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseCorrection baseCorrection) {
        String id;
        String id2;
        if (baseCorrection == null) {
            return -1;
        }
        if (baseCorrection != this && (id = getId()) != (id2 = baseCorrection.getId())) {
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BaseCorrection) {
            return internalEqualityCheck(getId(), ((BaseCorrection) obj).getId());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId());
    }

    public void setId(String str) {
        this.id = str;
    }
}
