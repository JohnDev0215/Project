package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractPlaylistTrackEntry implements Comparable<AbstractPlaylistTrackEntry> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry");
    private String entryId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistTrackEntry abstractPlaylistTrackEntry) {
        String entryId;
        String entryId2;
        if (abstractPlaylistTrackEntry == null) {
            return -1;
        }
        if (abstractPlaylistTrackEntry != this && (entryId = getEntryId()) != (entryId2 = abstractPlaylistTrackEntry.getEntryId())) {
            if (entryId == null) {
                return -1;
            }
            if (entryId2 == null) {
                return 1;
            }
            int iCompareTo = entryId.compareTo(entryId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AbstractPlaylistTrackEntry) {
            return internalEqualityCheck(getEntryId(), ((AbstractPlaylistTrackEntry) obj).getEntryId());
        }
        return false;
    }

    public String getEntryId() {
        return this.entryId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEntryId());
    }

    public void setEntryId(String str) {
        this.entryId = str;
    }
}
