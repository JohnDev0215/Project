package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class EntityRelation implements Comparable<EntityRelation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.EntityRelation");
    private String role;
    private Long sequenceNumber;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EntityRelation entityRelation) {
        if (entityRelation == null) {
            return -1;
        }
        if (entityRelation == this) {
            return 0;
        }
        String role = getRole();
        String role2 = entityRelation.getRole();
        if (role != role2) {
            if (role == null) {
                return -1;
            }
            if (role2 == null) {
                return 1;
            }
            int iCompareTo = role.compareTo(role2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long sequenceNumber = getSequenceNumber();
        Long sequenceNumber2 = entityRelation.getSequenceNumber();
        if (sequenceNumber != sequenceNumber2) {
            if (sequenceNumber == null) {
                return -1;
            }
            if (sequenceNumber2 == null) {
                return 1;
            }
            int iCompareTo2 = sequenceNumber.compareTo(sequenceNumber2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EntityRelation)) {
            return false;
        }
        EntityRelation entityRelation = (EntityRelation) obj;
        return internalEqualityCheck(getRole(), entityRelation.getRole()) && internalEqualityCheck(getSequenceNumber(), entityRelation.getSequenceNumber());
    }

    public String getRole() {
        return this.role;
    }

    public Long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRole(), getSequenceNumber());
    }

    public void setRole(String str) {
        this.role = str;
    }

    public void setSequenceNumber(Long l) {
        this.sequenceNumber = l;
    }
}
