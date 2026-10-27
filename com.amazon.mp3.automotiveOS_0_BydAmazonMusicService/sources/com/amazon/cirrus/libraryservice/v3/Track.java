package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Track implements Comparable<Track> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.Track");
    private Map<String, String> metadata;
    private String trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Track track) {
        if (track == null) {
            return -1;
        }
        if (track == this) {
            return 0;
        }
        Map<String, String> metadata = getMetadata();
        Map<String, String> metadata2 = track.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            if (metadata instanceof Comparable) {
                int iCompareTo = ((Comparable) metadata).compareTo(metadata2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!metadata.equals(metadata2)) {
                int iHashCode = metadata.hashCode();
                int iHashCode2 = metadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String trackId = getTrackId();
        String trackId2 = track.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo2 = trackId.compareTo(trackId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return internalEqualityCheck(getMetadata(), track.getMetadata()) && internalEqualityCheck(getTrackId(), track.getTrackId());
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMetadata(), getTrackId());
    }

    public void setMetadata(Map<String, String> map) {
        this.metadata = map;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }
}
