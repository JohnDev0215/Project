package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryTrackId implements Comparable<LibraryTrackId> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.LibraryTrackId");
    private String objectId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LibraryTrackId libraryTrackId) {
        String objectId;
        String objectId2;
        if (libraryTrackId == null) {
            return -1;
        }
        if (libraryTrackId != this && (objectId = getObjectId()) != (objectId2 = libraryTrackId.getObjectId())) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo = objectId.compareTo(objectId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LibraryTrackId) {
            return internalEqualityCheck(getObjectId(), ((LibraryTrackId) obj).getObjectId());
        }
        return false;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getObjectId());
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }
}
