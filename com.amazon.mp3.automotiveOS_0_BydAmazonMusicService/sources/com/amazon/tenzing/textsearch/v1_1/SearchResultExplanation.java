package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class SearchResultExplanation implements Comparable<SearchResultExplanation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SearchResultExplanation");
    private Map<String, String> searchProviderDetails;
    private Map<String, String> serviceDetails;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SearchResultExplanation searchResultExplanation) {
        if (searchResultExplanation == null) {
            return -1;
        }
        if (searchResultExplanation == this) {
            return 0;
        }
        Map<String, String> searchProviderDetails = getSearchProviderDetails();
        Map<String, String> searchProviderDetails2 = searchResultExplanation.getSearchProviderDetails();
        if (searchProviderDetails != searchProviderDetails2) {
            if (searchProviderDetails == null) {
                return -1;
            }
            if (searchProviderDetails2 == null) {
                return 1;
            }
            if (searchProviderDetails instanceof Comparable) {
                int iCompareTo = ((Comparable) searchProviderDetails).compareTo(searchProviderDetails2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!searchProviderDetails.equals(searchProviderDetails2)) {
                int iHashCode = searchProviderDetails.hashCode();
                int iHashCode2 = searchProviderDetails2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Map<String, String> serviceDetails = getServiceDetails();
        Map<String, String> serviceDetails2 = searchResultExplanation.getServiceDetails();
        if (serviceDetails != serviceDetails2) {
            if (serviceDetails == null) {
                return -1;
            }
            if (serviceDetails2 == null) {
                return 1;
            }
            if (serviceDetails instanceof Comparable) {
                int iCompareTo2 = ((Comparable) serviceDetails).compareTo(serviceDetails2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!serviceDetails.equals(serviceDetails2)) {
                int iHashCode3 = serviceDetails.hashCode();
                int iHashCode4 = serviceDetails2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SearchResultExplanation)) {
            return false;
        }
        SearchResultExplanation searchResultExplanation = (SearchResultExplanation) obj;
        return internalEqualityCheck(getSearchProviderDetails(), searchResultExplanation.getSearchProviderDetails()) && internalEqualityCheck(getServiceDetails(), searchResultExplanation.getServiceDetails());
    }

    public Map<String, String> getSearchProviderDetails() {
        return this.searchProviderDetails;
    }

    public Map<String, String> getServiceDetails() {
        return this.serviceDetails;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSearchProviderDetails(), getServiceDetails());
    }

    public void setSearchProviderDetails(Map<String, String> map) {
        this.searchProviderDetails = map;
    }

    public void setServiceDetails(Map<String, String> map) {
        this.serviceDetails = map;
    }
}
