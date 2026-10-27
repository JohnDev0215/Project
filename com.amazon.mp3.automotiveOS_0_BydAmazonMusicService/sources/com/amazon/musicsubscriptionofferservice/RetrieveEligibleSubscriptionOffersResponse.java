package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveEligibleSubscriptionOffersResponse implements Comparable<RetrieveEligibleSubscriptionOffersResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse");
    private List<Addon> addons;
    private List<Result> results;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse) {
        if (retrieveEligibleSubscriptionOffersResponse == null) {
            return -1;
        }
        if (retrieveEligibleSubscriptionOffersResponse == this) {
            return 0;
        }
        List<Result> results = getResults();
        List<Result> results2 = retrieveEligibleSubscriptionOffersResponse.getResults();
        if (results != results2) {
            if (results == null) {
                return -1;
            }
            if (results2 == null) {
                return 1;
            }
            if (results instanceof Comparable) {
                int iCompareTo = ((Comparable) results).compareTo(results2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!results.equals(results2)) {
                int iHashCode = results.hashCode();
                int iHashCode2 = results2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Addon> addons = getAddons();
        List<Addon> addons2 = retrieveEligibleSubscriptionOffersResponse.getAddons();
        if (addons != addons2) {
            if (addons == null) {
                return -1;
            }
            if (addons2 == null) {
                return 1;
            }
            if (addons instanceof Comparable) {
                int iCompareTo2 = ((Comparable) addons).compareTo(addons2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!addons.equals(addons2)) {
                int iHashCode3 = addons.hashCode();
                int iHashCode4 = addons2.hashCode();
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
        if (!(obj instanceof RetrieveEligibleSubscriptionOffersResponse)) {
            return false;
        }
        RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse = (RetrieveEligibleSubscriptionOffersResponse) obj;
        return internalEqualityCheck(getResults(), retrieveEligibleSubscriptionOffersResponse.getResults()) && internalEqualityCheck(getAddons(), retrieveEligibleSubscriptionOffersResponse.getAddons());
    }

    public List<Addon> getAddons() {
        return this.addons;
    }

    public List<Result> getResults() {
        return this.results;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getResults(), getAddons());
    }

    public void setAddons(List<Addon> list) {
        this.addons = list;
    }

    public void setResults(List<Result> list) {
        this.results = list;
    }
}
