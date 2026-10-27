package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicStoreTrackRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.MusicStoreTrackRecommendations");
    private List<MusicStoreRecommendedTrackItem> tracks;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.storeservice.model.BaseRecommendations, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseRecommendations baseRecommendations) {
        if (baseRecommendations == null) {
            return -1;
        }
        if (baseRecommendations == this) {
            return 0;
        }
        if (!(baseRecommendations instanceof MusicStoreTrackRecommendations)) {
            return 1;
        }
        List<MusicStoreRecommendedTrackItem> tracks = getTracks();
        List<MusicStoreRecommendedTrackItem> tracks2 = ((MusicStoreTrackRecommendations) baseRecommendations).getTracks();
        if (tracks != tracks2) {
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
        return super.compareTo(baseRecommendations);
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public boolean equals(Object obj) {
        if (obj instanceof MusicStoreTrackRecommendations) {
            return super.equals(obj) && internalEqualityCheck(getTracks(), ((MusicStoreTrackRecommendations) obj).getTracks());
        }
        return false;
    }

    public List<MusicStoreRecommendedTrackItem> getTracks() {
        return this.tracks;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTracks());
    }

    public void setTracks(List<MusicStoreRecommendedTrackItem> list) {
        this.tracks = list;
    }
}
