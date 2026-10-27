package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseRecommendations implements Comparable<BaseRecommendations> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.BaseRecommendations");
    private Integer nextResultsToken;
    private String recommendationType;
    private String subTitle;
    private String title;
    private String widgetId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseRecommendations baseRecommendations) {
        if (baseRecommendations == null) {
            return -1;
        }
        if (baseRecommendations == this) {
            return 0;
        }
        String widgetId = getWidgetId();
        String widgetId2 = baseRecommendations.getWidgetId();
        if (widgetId != widgetId2) {
            if (widgetId == null) {
                return -1;
            }
            if (widgetId2 == null) {
                return 1;
            }
            int iCompareTo = widgetId.compareTo(widgetId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = baseRecommendations.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String recommendationType = getRecommendationType();
        String recommendationType2 = baseRecommendations.getRecommendationType();
        if (recommendationType != recommendationType2) {
            if (recommendationType == null) {
                return -1;
            }
            if (recommendationType2 == null) {
                return 1;
            }
            int iCompareTo3 = recommendationType.compareTo(recommendationType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer nextResultsToken = getNextResultsToken();
        Integer nextResultsToken2 = baseRecommendations.getNextResultsToken();
        if (nextResultsToken != nextResultsToken2) {
            if (nextResultsToken == null) {
                return -1;
            }
            if (nextResultsToken2 == null) {
                return 1;
            }
            int iCompareTo4 = nextResultsToken.compareTo(nextResultsToken2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String subTitle = getSubTitle();
        String subTitle2 = baseRecommendations.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo5 = subTitle.compareTo(subTitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseRecommendations)) {
            return false;
        }
        BaseRecommendations baseRecommendations = (BaseRecommendations) obj;
        return internalEqualityCheck(getWidgetId(), baseRecommendations.getWidgetId()) && internalEqualityCheck(getTitle(), baseRecommendations.getTitle()) && internalEqualityCheck(getRecommendationType(), baseRecommendations.getRecommendationType()) && internalEqualityCheck(getNextResultsToken(), baseRecommendations.getNextResultsToken()) && internalEqualityCheck(getSubTitle(), baseRecommendations.getSubTitle());
    }

    public Integer getNextResultsToken() {
        return this.nextResultsToken;
    }

    public String getRecommendationType() {
        return this.recommendationType;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getWidgetId(), getTitle(), getRecommendationType(), getNextResultsToken(), getSubTitle());
    }

    public void setNextResultsToken(Integer num) {
        this.nextResultsToken = num;
    }

    public void setRecommendationType(String str) {
        this.recommendationType = str;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setWidgetId(String str) {
        this.widgetId = str;
    }
}
