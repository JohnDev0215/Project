package com.amazon.musicrelationshipservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ObjectIdentifier implements Comparable<ObjectIdentifier> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicrelationshipservice.model.ObjectIdentifier");
    private String id;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ObjectIdentifier objectIdentifier) {
        if (objectIdentifier == null) {
            return -1;
        }
        if (objectIdentifier == this) {
            return 0;
        }
        String id = getId();
        String id2 = objectIdentifier.getId();
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
        String type = getType();
        String type2 = objectIdentifier.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectIdentifier)) {
            return false;
        }
        ObjectIdentifier objectIdentifier = (ObjectIdentifier) obj;
        return internalEqualityCheck(getId(), objectIdentifier.getId()) && internalEqualityCheck(getType(), objectIdentifier.getType());
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), getType());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
