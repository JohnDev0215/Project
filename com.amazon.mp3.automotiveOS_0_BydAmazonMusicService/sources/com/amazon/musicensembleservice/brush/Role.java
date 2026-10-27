package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Role implements Comparable<Role> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Role");
    private String roleType;
    private Long sequence;

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
        Long sequence = getSequence();
        Long sequence2 = role.getSequence();
        if (sequence != sequence2) {
            if (sequence == null) {
                return -1;
            }
            if (sequence2 == null) {
                return 1;
            }
            int iCompareTo = sequence.compareTo(sequence2);
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
        return internalEqualityCheck(getSequence(), role.getSequence()) && internalEqualityCheck(getRoleType(), role.getRoleType());
    }

    public String getRoleType() {
        return this.roleType;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSequence(), getRoleType());
    }

    public void setRoleType(String str) {
        this.roleType = str;
    }

    public void setSequence(Long l) {
        this.sequence = l;
    }
}
