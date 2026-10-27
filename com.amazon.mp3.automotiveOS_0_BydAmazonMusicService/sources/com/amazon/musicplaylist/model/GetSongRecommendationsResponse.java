package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetSongRecommendationsResponse implements Comparable<GetSongRecommendationsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetSongRecommendationsResponse");
    private List<CatalogPlaylistTrackEntry> tracks;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetSongRecommendationsResponse getSongRecommendationsResponse) {
        List<CatalogPlaylistTrackEntry> tracks;
        List<CatalogPlaylistTrackEntry> tracks2;
        if (getSongRecommendationsResponse == null) {
            return -1;
        }
        if (getSongRecommendationsResponse != this && (tracks = getTracks()) != (tracks2 = getSongRecommendationsResponse.getTracks())) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode = tracks.hashCode();
                int iHashCode2 = tracks2.hashCode();
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
        if (obj instanceof GetSongRecommendationsResponse) {
            return internalEqualityCheck(getTracks(), ((GetSongRecommendationsResponse) obj).getTracks());
        }
        return false;
    }

    public List<CatalogPlaylistTrackEntry> getTracks() {
        return this.tracks;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTracks());
    }

    public void setTracks(List<CatalogPlaylistTrackEntry> list) {
        this.tracks = list;
    }
}
