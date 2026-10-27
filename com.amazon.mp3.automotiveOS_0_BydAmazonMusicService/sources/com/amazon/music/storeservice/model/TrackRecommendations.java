package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TrackRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.TrackRecommendations");
    private ContentInfo contentInfo;
    private List<RecommendedTrackItem> tracks;

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
        if (!(baseRecommendations instanceof TrackRecommendations)) {
            return 1;
        }
        TrackRecommendations trackRecommendations = (TrackRecommendations) baseRecommendations;
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = trackRecommendations.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo = contentInfo.compareTo(contentInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<RecommendedTrackItem> tracks = getTracks();
        List<RecommendedTrackItem> tracks2 = trackRecommendations.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo2 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        if (!(obj instanceof TrackRecommendations)) {
            return false;
        }
        TrackRecommendations trackRecommendations = (TrackRecommendations) obj;
        return super.equals(obj) && internalEqualityCheck(getContentInfo(), trackRecommendations.getContentInfo()) && internalEqualityCheck(getTracks(), trackRecommendations.getTracks());
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public List<RecommendedTrackItem> getTracks() {
        return this.tracks;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentInfo(), getTracks());
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setTracks(List<RecommendedTrackItem> list) {
        this.tracks = list;
    }
}
