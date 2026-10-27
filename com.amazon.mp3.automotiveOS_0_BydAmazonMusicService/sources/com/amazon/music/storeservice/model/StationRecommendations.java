package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class StationRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.StationRecommendations");
    private ContentInfo contentInfo;
    private List<RecommendedStationItem> stations;

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
        if (!(baseRecommendations instanceof StationRecommendations)) {
            return 1;
        }
        StationRecommendations stationRecommendations = (StationRecommendations) baseRecommendations;
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = stationRecommendations.getContentInfo();
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
        List<RecommendedStationItem> stations = getStations();
        List<RecommendedStationItem> stations2 = stationRecommendations.getStations();
        if (stations != stations2) {
            if (stations == null) {
                return -1;
            }
            if (stations2 == null) {
                return 1;
            }
            if (stations instanceof Comparable) {
                int iCompareTo2 = ((Comparable) stations).compareTo(stations2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!stations.equals(stations2)) {
                int iHashCode = stations.hashCode();
                int iHashCode2 = stations2.hashCode();
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
        if (!(obj instanceof StationRecommendations)) {
            return false;
        }
        StationRecommendations stationRecommendations = (StationRecommendations) obj;
        return super.equals(obj) && internalEqualityCheck(getContentInfo(), stationRecommendations.getContentInfo()) && internalEqualityCheck(getStations(), stationRecommendations.getStations());
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public List<RecommendedStationItem> getStations() {
        return this.stations;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentInfo(), getStations());
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setStations(List<RecommendedStationItem> list) {
        this.stations = list;
    }
}
