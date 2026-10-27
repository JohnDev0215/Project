package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetTopMusicEntitiesRequest");
    private AllowedParentalControls allowedParentalControls;
    private String browseId;
    private List<String> features;
    private String lang;
    private List<String> languagesOfPerformance;
    private String languagesOfPerformanceFilter;
    private Integer maxCount;
    private Map<String, String> nextTokenMap;
    private String rankType;
    private Boolean stub;
    private List<String> types;

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
        if (!(requestIdentity instanceof GetTopMusicEntitiesRequest)) {
            return 1;
        }
        GetTopMusicEntitiesRequest getTopMusicEntitiesRequest = (GetTopMusicEntitiesRequest) requestIdentity;
        List<String> types = getTypes();
        List<String> types2 = getTopMusicEntitiesRequest.getTypes();
        if (types != types2) {
            if (types == null) {
                return -1;
            }
            if (types2 == null) {
                return 1;
            }
            if (types instanceof Comparable) {
                int iCompareTo = ((Comparable) types).compareTo(types2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!types.equals(types2)) {
                int iHashCode = types.hashCode();
                int iHashCode2 = types2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String languagesOfPerformanceFilter = getLanguagesOfPerformanceFilter();
        String languagesOfPerformanceFilter2 = getTopMusicEntitiesRequest.getLanguagesOfPerformanceFilter();
        if (languagesOfPerformanceFilter != languagesOfPerformanceFilter2) {
            if (languagesOfPerformanceFilter == null) {
                return -1;
            }
            if (languagesOfPerformanceFilter2 == null) {
                return 1;
            }
            int iCompareTo2 = languagesOfPerformanceFilter.compareTo(languagesOfPerformanceFilter2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = getTopMusicEntitiesRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getTopMusicEntitiesRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo4 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String browseId = getBrowseId();
        String browseId2 = getTopMusicEntitiesRequest.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo5 = browseId.compareTo(browseId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String rankType = getRankType();
        String rankType2 = getTopMusicEntitiesRequest.getRankType();
        if (rankType != rankType2) {
            if (rankType == null) {
                return -1;
            }
            if (rankType2 == null) {
                return 1;
            }
            int iCompareTo6 = rankType.compareTo(rankType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<String> features = getFeatures();
        List<String> features2 = getTopMusicEntitiesRequest.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo7 = ((Comparable) features).compareTo(features2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!features.equals(features2)) {
                int iHashCode3 = features.hashCode();
                int iHashCode4 = features2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Map<String, String> nextTokenMap = getNextTokenMap();
        Map<String, String> nextTokenMap2 = getTopMusicEntitiesRequest.getNextTokenMap();
        if (nextTokenMap != nextTokenMap2) {
            if (nextTokenMap == null) {
                return -1;
            }
            if (nextTokenMap2 == null) {
                return 1;
            }
            if (nextTokenMap instanceof Comparable) {
                int iCompareTo8 = ((Comparable) nextTokenMap).compareTo(nextTokenMap2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!nextTokenMap.equals(nextTokenMap2)) {
                int iHashCode5 = nextTokenMap.hashCode();
                int iHashCode6 = nextTokenMap2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String lang = getLang();
        String lang2 = getTopMusicEntitiesRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo9 = lang.compareTo(lang2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Integer maxCount = getMaxCount();
        Integer maxCount2 = getTopMusicEntitiesRequest.getMaxCount();
        if (maxCount != maxCount2) {
            if (maxCount == null) {
                return -1;
            }
            if (maxCount2 == null) {
                return 1;
            }
            int iCompareTo10 = maxCount.compareTo(maxCount2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = getTopMusicEntitiesRequest.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo11 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!languagesOfPerformance.equals(languagesOfPerformance2)) {
                int iHashCode7 = languagesOfPerformance.hashCode();
                int iHashCode8 = languagesOfPerformance2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof GetTopMusicEntitiesRequest)) {
            return false;
        }
        GetTopMusicEntitiesRequest getTopMusicEntitiesRequest = (GetTopMusicEntitiesRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getTypes(), getTopMusicEntitiesRequest.getTypes()) && internalEqualityCheck(getLanguagesOfPerformanceFilter(), getTopMusicEntitiesRequest.getLanguagesOfPerformanceFilter()) && internalEqualityCheck(isStub(), getTopMusicEntitiesRequest.isStub()) && internalEqualityCheck(getAllowedParentalControls(), getTopMusicEntitiesRequest.getAllowedParentalControls()) && internalEqualityCheck(getBrowseId(), getTopMusicEntitiesRequest.getBrowseId()) && internalEqualityCheck(getRankType(), getTopMusicEntitiesRequest.getRankType()) && internalEqualityCheck(getFeatures(), getTopMusicEntitiesRequest.getFeatures()) && internalEqualityCheck(getNextTokenMap(), getTopMusicEntitiesRequest.getNextTokenMap()) && internalEqualityCheck(getLang(), getTopMusicEntitiesRequest.getLang()) && internalEqualityCheck(getMaxCount(), getTopMusicEntitiesRequest.getMaxCount()) && internalEqualityCheck(getLanguagesOfPerformance(), getTopMusicEntitiesRequest.getLanguagesOfPerformance());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public String getLanguagesOfPerformanceFilter() {
        return this.languagesOfPerformanceFilter;
    }

    public Integer getMaxCount() {
        return this.maxCount;
    }

    public Map<String, String> getNextTokenMap() {
        return this.nextTokenMap;
    }

    public String getRankType() {
        return this.rankType;
    }

    public List<String> getTypes() {
        return this.types;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTypes(), getLanguagesOfPerformanceFilter(), isStub(), getAllowedParentalControls(), getBrowseId(), getRankType(), getFeatures(), getNextTokenMap(), getLang(), getMaxCount(), getLanguagesOfPerformance());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setFeatures(List<String> list) {
        this.features = list;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setMaxCount(Integer num) {
        this.maxCount = num;
    }

    public void setNextTokenMap(Map<String, String> map) {
        this.nextTokenMap = map;
    }

    public void setRankType(String str) {
        this.rankType = str;
    }

    public void setTypes(List<String> list) {
        this.types = list;
    }
}
