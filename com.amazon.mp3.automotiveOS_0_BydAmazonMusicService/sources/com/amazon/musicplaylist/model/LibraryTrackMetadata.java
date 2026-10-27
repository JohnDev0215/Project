package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryTrackMetadata implements Comparable<LibraryTrackMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.LibraryTrackMetadata");
    private Map<String, String> requestedMetadata;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LibraryTrackMetadata libraryTrackMetadata) {
        Map<String, String> requestedMetadata;
        Map<String, String> requestedMetadata2;
        if (libraryTrackMetadata == null) {
            return -1;
        }
        if (libraryTrackMetadata != this && (requestedMetadata = getRequestedMetadata()) != (requestedMetadata2 = libraryTrackMetadata.getRequestedMetadata())) {
            if (requestedMetadata == null) {
                return -1;
            }
            if (requestedMetadata2 == null) {
                return 1;
            }
            if (requestedMetadata instanceof Comparable) {
                int iCompareTo = ((Comparable) requestedMetadata).compareTo(requestedMetadata2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!requestedMetadata.equals(requestedMetadata2)) {
                int iHashCode = requestedMetadata.hashCode();
                int iHashCode2 = requestedMetadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LibraryTrackMetadata) {
            return internalEqualityCheck(getRequestedMetadata(), ((LibraryTrackMetadata) obj).getRequestedMetadata());
        }
        return false;
    }

    public Map<String, String> getRequestedMetadata() {
        return this.requestedMetadata;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRequestedMetadata());
    }

    public void setRequestedMetadata(Map<String, String> map) {
        this.requestedMetadata = map;
    }
}
