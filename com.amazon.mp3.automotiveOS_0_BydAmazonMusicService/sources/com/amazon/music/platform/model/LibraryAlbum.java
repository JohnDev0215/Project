package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryAlbum extends Album {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LibraryAlbum");
    private String contributors;
    private String objectId;
    private Float rating;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof LibraryAlbum)) {
            return 1;
        }
        LibraryAlbum libraryAlbum = (LibraryAlbum) document;
        String contributors = getContributors();
        String contributors2 = libraryAlbum.getContributors();
        if (contributors != contributors2) {
            if (contributors == null) {
                return -1;
            }
            if (contributors2 == null) {
                return 1;
            }
            int iCompareTo = contributors.compareTo(contributors2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String objectId = getObjectId();
        String objectId2 = libraryAlbum.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo2 = objectId.compareTo(objectId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Float rating = getRating();
        Float rating2 = libraryAlbum.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo3 = rating.compareTo(rating2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryAlbum)) {
            return false;
        }
        LibraryAlbum libraryAlbum = (LibraryAlbum) obj;
        return super.equals(obj) && internalEqualityCheck(getContributors(), libraryAlbum.getContributors()) && internalEqualityCheck(getObjectId(), libraryAlbum.getObjectId()) && internalEqualityCheck(getRating(), libraryAlbum.getRating());
    }

    public String getContributors() {
        return this.contributors;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Float getRating() {
        return this.rating;
    }

    @Override // com.amazon.music.platform.model.Album, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContributors(), getObjectId(), getRating());
    }

    public void setContributors(String str) {
        this.contributors = str;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setRating(Float f) {
        this.rating = f;
    }
}
