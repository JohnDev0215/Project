package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Explanation implements Comparable<Explanation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.Explanation");
    private String clusterId;
    private String description;
    private List<Explanation> detail;
    private String indexId;
    private Boolean match;
    private String query;
    private Float value;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Explanation explanation) {
        if (explanation == null) {
            return -1;
        }
        if (explanation == this) {
            return 0;
        }
        String indexId = getIndexId();
        String indexId2 = explanation.getIndexId();
        if (indexId != indexId2) {
            if (indexId == null) {
                return -1;
            }
            if (indexId2 == null) {
                return 1;
            }
            int iCompareTo = indexId.compareTo(indexId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Explanation> detail = getDetail();
        List<Explanation> detail2 = explanation.getDetail();
        if (detail != detail2) {
            if (detail == null) {
                return -1;
            }
            if (detail2 == null) {
                return 1;
            }
            if (detail instanceof Comparable) {
                int iCompareTo2 = ((Comparable) detail).compareTo(detail2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!detail.equals(detail2)) {
                int iHashCode = detail.hashCode();
                int iHashCode2 = detail2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsMatch = isMatch();
        Boolean boolIsMatch2 = explanation.isMatch();
        if (boolIsMatch != boolIsMatch2) {
            if (boolIsMatch == null) {
                return -1;
            }
            if (boolIsMatch2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsMatch.compareTo(boolIsMatch2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Float value = getValue();
        Float value2 = explanation.getValue();
        if (value != value2) {
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            int iCompareTo4 = value.compareTo(value2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String description = getDescription();
        String description2 = explanation.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo5 = description.compareTo(description2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String clusterId = getClusterId();
        String clusterId2 = explanation.getClusterId();
        if (clusterId != clusterId2) {
            if (clusterId == null) {
                return -1;
            }
            if (clusterId2 == null) {
                return 1;
            }
            int iCompareTo6 = clusterId.compareTo(clusterId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String query = getQuery();
        String query2 = explanation.getQuery();
        if (query != query2) {
            if (query == null) {
                return -1;
            }
            if (query2 == null) {
                return 1;
            }
            int iCompareTo7 = query.compareTo(query2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Explanation)) {
            return false;
        }
        Explanation explanation = (Explanation) obj;
        return internalEqualityCheck(getIndexId(), explanation.getIndexId()) && internalEqualityCheck(getDetail(), explanation.getDetail()) && internalEqualityCheck(isMatch(), explanation.isMatch()) && internalEqualityCheck(getValue(), explanation.getValue()) && internalEqualityCheck(getDescription(), explanation.getDescription()) && internalEqualityCheck(getClusterId(), explanation.getClusterId()) && internalEqualityCheck(getQuery(), explanation.getQuery());
    }

    public String getClusterId() {
        return this.clusterId;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Explanation> getDetail() {
        return this.detail;
    }

    public String getIndexId() {
        return this.indexId;
    }

    public String getQuery() {
        return this.query;
    }

    public Float getValue() {
        return this.value;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getIndexId(), getDetail(), isMatch(), getValue(), getDescription(), getClusterId(), getQuery());
    }

    public Boolean isMatch() {
        return this.match;
    }

    public void setClusterId(String str) {
        this.clusterId = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDetail(List<Explanation> list) {
        this.detail = list;
    }

    public void setIndexId(String str) {
        this.indexId = str;
    }

    public void setMatch(Boolean bool) {
        this.match = bool;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setValue(Float f) {
        this.value = f;
    }
}
