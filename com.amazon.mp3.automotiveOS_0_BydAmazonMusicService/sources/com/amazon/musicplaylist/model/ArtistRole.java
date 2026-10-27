package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ArtistRole implements Comparable<ArtistRole> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.ArtistRole");
    private String role;
    private int sequence;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistRole artistRole) {
        if (artistRole == null) {
            return -1;
        }
        if (artistRole == this) {
            return 0;
        }
        if (getSequence() < artistRole.getSequence()) {
            return -1;
        }
        if (getSequence() > artistRole.getSequence()) {
            return 1;
        }
        String role = getRole();
        String role2 = artistRole.getRole();
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistRole)) {
            return false;
        }
        ArtistRole artistRole = (ArtistRole) obj;
        return internalEqualityCheck(Integer.valueOf(getSequence()), Integer.valueOf(artistRole.getSequence())) && internalEqualityCheck(getRole(), artistRole.getRole());
    }

    public String getRole() {
        return this.role;
    }

    public int getSequence() {
        return this.sequence;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getSequence()), getRole());
    }

    public void setRole(String str) {
        this.role = str;
    }

    public void setSequence(int i) {
        this.sequence = i;
    }
}
