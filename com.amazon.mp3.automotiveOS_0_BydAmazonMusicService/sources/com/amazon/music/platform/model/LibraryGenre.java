package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryGenre extends Genre {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LibraryGenre");
    private String objectId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof LibraryGenre)) {
            return 1;
        }
        String objectId = getObjectId();
        String objectId2 = ((LibraryGenre) document).getObjectId();
        if (objectId != objectId2) {
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
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (obj instanceof LibraryGenre) {
            return super.equals(obj) && internalEqualityCheck(getObjectId(), ((LibraryGenre) obj).getObjectId());
        }
        return false;
    }

    public String getObjectId() {
        return this.objectId;
    }

    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getObjectId());
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }
}
