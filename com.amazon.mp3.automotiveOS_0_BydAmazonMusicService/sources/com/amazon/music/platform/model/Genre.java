package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Genre extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Genre");
    private String name;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof Genre)) {
            return 1;
        }
        String name = getName();
        String name2 = ((Genre) document).getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo = name.compareTo(name2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (obj instanceof Genre) {
            return super.equals(obj) && internalEqualityCheck(getName(), ((Genre) obj).getName());
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getName());
    }

    public void setName(String str) {
        this.name = str;
    }
}
