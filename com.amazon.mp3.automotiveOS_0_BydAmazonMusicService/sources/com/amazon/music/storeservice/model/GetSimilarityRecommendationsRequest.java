package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.AllowedParentalControls;
import com.amazon.musicensembleservice.RequestIdentity;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class GetSimilarityRecommendationsRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequest");
    private AllowedParentalControls allowedParentalControls;
    private String asin;
    private String lang;
    private Integer maxResultsPerWidget;
    private Integer minResultsPerWidget;
    private Boolean stub;
    private Map<String, Integer> widgetIdTokenMap;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.RequestIdentity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestIdentity requestIdentity) {
        if (requestIdentity == null) {
            return -1;
        }
        if (requestIdentity == this) {
            return 0;
        }
        if (!(requestIdentity instanceof GetSimilarityRecommendationsRequest)) {
            return 1;
        }
        GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest = (GetSimilarityRecommendationsRequest) requestIdentity;
        Map<String, Integer> widgetIdTokenMap = getWidgetIdTokenMap();
        Map<String, Integer> widgetIdTokenMap2 = getSimilarityRecommendationsRequest.getWidgetIdTokenMap();
        if (widgetIdTokenMap != widgetIdTokenMap2) {
            if (widgetIdTokenMap == null) {
                return -1;
            }
            if (widgetIdTokenMap2 == null) {
                return 1;
            }
            if (widgetIdTokenMap instanceof Comparable) {
                int iCompareTo = ((Comparable) widgetIdTokenMap).compareTo(widgetIdTokenMap2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!widgetIdTokenMap.equals(widgetIdTokenMap2)) {
                int iHashCode = widgetIdTokenMap.hashCode();
                int iHashCode2 = widgetIdTokenMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String lang = getLang();
        String lang2 = getSimilarityRecommendationsRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo2 = lang.compareTo(lang2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer minResultsPerWidget = getMinResultsPerWidget();
        Integer minResultsPerWidget2 = getSimilarityRecommendationsRequest.getMinResultsPerWidget();
        if (minResultsPerWidget != minResultsPerWidget2) {
            if (minResultsPerWidget == null) {
                return -1;
            }
            if (minResultsPerWidget2 == null) {
                return 1;
            }
            int iCompareTo3 = minResultsPerWidget.compareTo(minResultsPerWidget2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer maxResultsPerWidget = getMaxResultsPerWidget();
        Integer maxResultsPerWidget2 = getSimilarityRecommendationsRequest.getMaxResultsPerWidget();
        if (maxResultsPerWidget != maxResultsPerWidget2) {
            if (maxResultsPerWidget == null) {
                return -1;
            }
            if (maxResultsPerWidget2 == null) {
                return 1;
            }
            int iCompareTo4 = maxResultsPerWidget.compareTo(maxResultsPerWidget2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = getSimilarityRecommendationsRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = getSimilarityRecommendationsRequest.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo6 = asin.compareTo(asin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getSimilarityRecommendationsRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo7 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof GetSimilarityRecommendationsRequest)) {
            return false;
        }
        GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest = (GetSimilarityRecommendationsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getWidgetIdTokenMap(), getSimilarityRecommendationsRequest.getWidgetIdTokenMap()) && internalEqualityCheck(getLang(), getSimilarityRecommendationsRequest.getLang()) && internalEqualityCheck(getMinResultsPerWidget(), getSimilarityRecommendationsRequest.getMinResultsPerWidget()) && internalEqualityCheck(getMaxResultsPerWidget(), getSimilarityRecommendationsRequest.getMaxResultsPerWidget()) && internalEqualityCheck(isStub(), getSimilarityRecommendationsRequest.isStub()) && internalEqualityCheck(getAsin(), getSimilarityRecommendationsRequest.getAsin()) && internalEqualityCheck(getAllowedParentalControls(), getSimilarityRecommendationsRequest.getAllowedParentalControls());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getLang() {
        return this.lang;
    }

    public Integer getMaxResultsPerWidget() {
        return this.maxResultsPerWidget;
    }

    public Integer getMinResultsPerWidget() {
        return this.minResultsPerWidget;
    }

    public Map<String, Integer> getWidgetIdTokenMap() {
        return this.widgetIdTokenMap;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getWidgetIdTokenMap(), getLang(), getMinResultsPerWidget(), getMaxResultsPerWidget(), isStub(), getAsin(), getAllowedParentalControls());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setMaxResultsPerWidget(Integer num) {
        this.maxResultsPerWidget = num;
    }

    public void setMinResultsPerWidget(Integer num) {
        this.minResultsPerWidget = num;
    }

    public void setWidgetIdTokenMap(Map<String, Integer> map) {
        this.widgetIdTokenMap = map;
    }
}
