package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class GetBrowseRecommendationsBySourceAsinsResponse implements Comparable<GetBrowseRecommendationsBySourceAsinsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsResponse");
    private List<BaseRecommendations> recommendations;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetBrowseRecommendationsBySourceAsinsResponse getBrowseRecommendationsBySourceAsinsResponse) {
        List<BaseRecommendations> recommendations;
        List<BaseRecommendations> recommendations2;
        if (getBrowseRecommendationsBySourceAsinsResponse == null) {
            return -1;
        }
        if (getBrowseRecommendationsBySourceAsinsResponse != this && (recommendations = getRecommendations()) != (recommendations2 = getBrowseRecommendationsBySourceAsinsResponse.getRecommendations())) {
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
        if (obj instanceof GetBrowseRecommendationsBySourceAsinsResponse) {
            return internalEqualityCheck(getRecommendations(), ((GetBrowseRecommendationsBySourceAsinsResponse) obj).getRecommendations());
        }
        return false;
    }

    public List<BaseRecommendations> getRecommendations() {
        return this.recommendations;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRecommendations());
    }

    public void setRecommendations(List<BaseRecommendations> list) {
        this.recommendations = list;
    }
}
