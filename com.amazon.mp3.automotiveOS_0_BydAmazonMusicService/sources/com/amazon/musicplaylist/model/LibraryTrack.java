package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.TrackId;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryTrack extends TrackId {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.LibraryTrack");
    private String objectId;

    public static class Builder extends TrackId.Builder {
        protected String objectId;

        public LibraryTrack build() {
            LibraryTrack libraryTrack = new LibraryTrack();
            populate(libraryTrack);
            return libraryTrack;
        }

        protected void populate(LibraryTrack libraryTrack) {
            super.populate((TrackId) libraryTrack);
            libraryTrack.setObjectId(this.objectId);
        }

        public Builder withObjectId(String str) {
            this.objectId = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.TrackId, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackId trackId) {
        if (trackId == null) {
            return -1;
        }
        if (trackId == this) {
            return 0;
        }
        if (!(trackId instanceof LibraryTrack)) {
            return 1;
        }
        String objectId = getObjectId();
        String objectId2 = ((LibraryTrack) trackId).getObjectId();
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
        return super.compareTo(trackId);
    }

    @Override // com.amazon.musicplaylist.model.TrackId
    public boolean equals(Object obj) {
        if (obj instanceof LibraryTrack) {
            return super.equals(obj) && internalEqualityCheck(getObjectId(), ((LibraryTrack) obj).getObjectId());
        }
        return false;
    }

    public String getObjectId() {
        return this.objectId;
    }

    @Override // com.amazon.musicplaylist.model.TrackId
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getObjectId());
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }
}
