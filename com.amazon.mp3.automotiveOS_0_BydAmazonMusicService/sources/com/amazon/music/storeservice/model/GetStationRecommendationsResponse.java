package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class GetStationRecommendationsResponse implements Comparable<GetStationRecommendationsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.GetStationRecommendationsResponse");
    private List<StationRecommendations> recommendations;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetStationRecommendationsResponse getStationRecommendationsResponse) {
        List<StationRecommendations> recommendations;
        List<StationRecommendations> recommendations2;
        if (getStationRecommendationsResponse == null) {
            return -1;
        }
        if (getStationRecommendationsResponse != this && (recommendations = getRecommendations()) != (recommendations2 = getStationRecommendationsResponse.getRecommendations())) {
            if (recommendations == null) {
                return -1;
            }
            if (recommendations2 == null) {
                return 1;
            }
            if (recommendations instanceof Comparable) {
                int iCompareTo = ((Comparable) recommendations).compareTo(recommendations2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!recommendations.equals(recommendations2)) {
                int iHashCode = recommendations.hashCode();
                int iHashCode2 = recommendations2.hashCode();
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
        if (obj instanceof GetStationRecommendationsResponse) {
            return internalEqualityCheck(getRecommendations(), ((GetStationRecommendationsResponse) obj).getRecommendations());
        }
        return false;
    }

    public List<StationRecommendations> getRecommendations() {
        return this.recommendations;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRecommendations());
    }

    public void setRecommendations(List<StationRecommendations> list) {
        this.recommendations = list;
    }
}
