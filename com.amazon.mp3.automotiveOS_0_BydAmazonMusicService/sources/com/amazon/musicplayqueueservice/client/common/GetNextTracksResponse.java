package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetNextTracksResponse implements Comparable<GetNextTracksResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetNextTracksResponse");
    private String nextPageToken;
    private List<TrackMetadata> trackMetadataList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetNextTracksResponse getNextTracksResponse) {
        if (getNextTracksResponse == null) {
            return -1;
        }
        if (getNextTracksResponse == this) {
            return 0;
        }
        String nextPageToken = getNextPageToken();
        String nextPageToken2 = getNextTracksResponse.getNextPageToken();
        if (nextPageToken != nextPageToken2) {
            if (nextPageToken == null) {
                return -1;
            }
            if (nextPageToken2 == null) {
                return 1;
            }
            int iCompareTo = nextPageToken.compareTo(nextPageToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<TrackMetadata> trackMetadataList = getTrackMetadataList();
        List<TrackMetadata> trackMetadataList2 = getNextTracksResponse.getTrackMetadataList();
        if (trackMetadataList != trackMetadataList2) {
            if (trackMetadataList == null) {
                return -1;
            }
            if (trackMetadataList2 == null) {
                return 1;
            }
            if (trackMetadataList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) trackMetadataList).compareTo(trackMetadataList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!trackMetadataList.equals(trackMetadataList2)) {
                int iHashCode = trackMetadataList.hashCode();
                int iHashCode2 = trackMetadataList2.hashCode();
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
        if (!(obj instanceof GetNextTracksResponse)) {
            return false;
        }
        GetNextTracksResponse getNextTracksResponse = (GetNextTracksResponse) obj;
        return internalEqualityCheck(getNextPageToken(), getNextTracksResponse.getNextPageToken()) && internalEqualityCheck(getTrackMetadataList(), getNextTracksResponse.getTrackMetadataList());
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public List<TrackMetadata> getTrackMetadataList() {
        return this.trackMetadataList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getNextPageToken(), getTrackMetadataList());
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public void setTrackMetadataList(List<TrackMetadata> list) {
        this.trackMetadataList = list;
    }
}
