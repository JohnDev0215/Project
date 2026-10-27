package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.AllowedParentalControls;
import com.amazon.musicensembleservice.RequestIdentity;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class GetBrowseRecommendationsRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.GetBrowseRecommendationsRequest");
    private AllowedParentalControls allowedParentalControls;
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
        if (!(requestIdentity instanceof GetBrowseRecommendationsRequest)) {
            return 1;
        }
        GetBrowseRecommendationsRequest getBrowseRecommendationsRequest = (GetBrowseRecommendationsRequest) requestIdentity;
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = getBrowseRecommendationsRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer minResultsPerWidget = getMinResultsPerWidget();
        Integer minResultsPerWidget2 = getBrowseRecommendationsRequest.getMinResultsPerWidget();
        if (minResultsPerWidget != minResultsPerWidget2) {
            if (minResultsPerWidget == null) {
                return -1;
            }
            if (minResultsPerWidget2 == null) {
                return 1;
            }
            int iCompareTo2 = minResultsPerWidget.compareTo(minResultsPerWidget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer maxResultsPerWidget = getMaxResultsPerWidget();
        Integer maxResultsPerWidget2 = getBrowseRecommendationsRequest.getMaxResultsPerWidget();
        if (maxResultsPerWidget != maxResultsPerWidget2) {
            if (maxResultsPerWidget == null) {
                return -1;
            }
            if (maxResultsPerWidget2 == null) {
                return 1;
            }
            int iCompareTo3 = maxResultsPerWidget.compareTo(maxResultsPerWidget2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, Integer> widgetIdTokenMap = getWidgetIdTokenMap();
        Map<String, Integer> widgetIdTokenMap2 = getBrowseRecommendationsRequest.getWidgetIdTokenMap();
        if (widgetIdTokenMap != widgetIdTokenMap2) {
            if (widgetIdTokenMap == null) {
                return -1;
            }
            if (widgetIdTokenMap2 == null) {
                return 1;
            }
            if (widgetIdTokenMap instanceof Comparable) {
                int iCompareTo4 = ((Comparable) widgetIdTokenMap).compareTo(widgetIdTokenMap2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getBrowseRecommendationsRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo5 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String lang = getLang();
        String lang2 = getBrowseRecommendationsRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo6 = lang.compareTo(lang2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof GetBrowseRecommendationsRequest)) {
            return false;
        }
        GetBrowseRecommendationsRequest getBrowseRecommendationsRequest = (GetBrowseRecommendationsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(isStub(), getBrowseRecommendationsRequest.isStub()) && internalEqualityCheck(getMinResultsPerWidget(), getBrowseRecommendationsRequest.getMinResultsPerWidget()) && internalEqualityCheck(getMaxResultsPerWidget(), getBrowseRecommendationsRequest.getMaxResultsPerWidget()) && internalEqualityCheck(getWidgetIdTokenMap(), getBrowseRecommendationsRequest.getWidgetIdTokenMap()) && internalEqualityCheck(getAllowedParentalControls(), getBrowseRecommendationsRequest.getAllowedParentalControls()) && internalEqualityCheck(getLang(), getBrowseRecommendationsRequest.getLang());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isStub(), getMinResultsPerWidget(), getMaxResultsPerWidget(), getWidgetIdTokenMap(), getAllowedParentalControls(), getLang());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
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
