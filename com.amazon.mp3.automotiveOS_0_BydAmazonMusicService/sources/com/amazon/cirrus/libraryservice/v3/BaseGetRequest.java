package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseGetRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.BaseGetRequest");
    private List<String> attributeList;
    private List<Filter> filterList;
    private int maxResults;
    private String nextToken;
    private SortOrder sortOrder;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof BaseGetRequest)) {
            return 1;
        }
        BaseGetRequest baseGetRequest = (BaseGetRequest) cirrusBaseRequestV2;
        SortOrder sortOrder = getSortOrder();
        SortOrder sortOrder2 = baseGetRequest.getSortOrder();
        if (sortOrder != sortOrder2) {
            if (sortOrder == null) {
                return -1;
            }
            if (sortOrder2 == null) {
                return 1;
            }
            int iCompareTo = sortOrder.compareTo(sortOrder2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Filter> filterList = getFilterList();
        List<Filter> filterList2 = baseGetRequest.getFilterList();
        if (filterList != filterList2) {
            if (filterList == null) {
                return -1;
            }
            if (filterList2 == null) {
                return 1;
            }
            if (filterList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) filterList).compareTo(filterList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!filterList.equals(filterList2)) {
                int iHashCode = filterList.hashCode();
                int iHashCode2 = filterList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        if (getMaxResults() < baseGetRequest.getMaxResults()) {
            return -1;
        }
        if (getMaxResults() > baseGetRequest.getMaxResults()) {
            return 1;
        }
        String nextToken = getNextToken();
        String nextToken2 = baseGetRequest.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo3 = nextToken.compareTo(nextToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> attributeList = getAttributeList();
        List<String> attributeList2 = baseGetRequest.getAttributeList();
        if (attributeList != attributeList2) {
            if (attributeList == null) {
                return -1;
            }
            if (attributeList2 == null) {
                return 1;
            }
            if (attributeList instanceof Comparable) {
                int iCompareTo4 = ((Comparable) attributeList).compareTo(attributeList2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!attributeList.equals(attributeList2)) {
                int iHashCode3 = attributeList.hashCode();
                int iHashCode4 = attributeList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseGetRequest)) {
            return false;
        }
        BaseGetRequest baseGetRequest = (BaseGetRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSortOrder(), baseGetRequest.getSortOrder()) && internalEqualityCheck(getFilterList(), baseGetRequest.getFilterList()) && internalEqualityCheck(Integer.valueOf(getMaxResults()), Integer.valueOf(baseGetRequest.getMaxResults())) && internalEqualityCheck(getNextToken(), baseGetRequest.getNextToken()) && internalEqualityCheck(getAttributeList(), baseGetRequest.getAttributeList());
    }

    public List<String> getAttributeList() {
        return this.attributeList;
    }

    public List<Filter> getFilterList() {
        return this.filterList;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSortOrder(), getFilterList(), Integer.valueOf(getMaxResults()), getNextToken(), getAttributeList());
    }

    public void setMaxResults(int i) {
        this.maxResults = i;
    }
}
