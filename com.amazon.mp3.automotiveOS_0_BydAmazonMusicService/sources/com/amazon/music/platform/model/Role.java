package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Role implements Comparable<Role> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Role");
    private String roleType;
    private Long sequenceNumber;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Role role) {
        if (role == null) {
            return -1;
        }
        if (role == this) {
            return 0;
        }
        Long sequenceNumber = getSequenceNumber();
        Long sequenceNumber2 = role.getSequenceNumber();
        if (sequenceNumber != sequenceNumber2) {
            if (sequenceNumber == null) {
                return -1;
            }
            if (sequenceNumber2 == null) {
                return 1;
            }
            int iCompareTo = sequenceNumber.compareTo(sequenceNumber2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String roleType = getRoleType();
        String roleType2 = role.getRoleType();
        if (roleType != roleType2) {
            if (roleType == null) {
                return -1;
            }
            if (roleType2 == null) {
                return 1;
            }
            int iCompareTo2 = roleType.compareTo(roleType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        Role role = (Role) obj;
        return internalEqualityCheck(getSequenceNumber(), role.getSequenceNumber()) && internalEqualityCheck(getRoleType(), role.getRoleType());
    }

    public String getRoleType() {
        return this.roleType;
    }

    public Long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSequenceNumber(), getRoleType());
    }

    public void setRoleType(String str) {
        this.roleType = str;
    }

    public void setSequenceNumber(Long l) {
        this.sequenceNumber = l;
    }
}
