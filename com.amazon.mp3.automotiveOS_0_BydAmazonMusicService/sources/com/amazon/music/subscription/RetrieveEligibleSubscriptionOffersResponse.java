package com.amazon.music.subscription;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveEligibleSubscriptionOffersResponse implements Comparable<RetrieveEligibleSubscriptionOffersResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponse");
    private List<PlanOffer> paid;
    private List<PlanOffer> trial;

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
        List<PlanOffer> paid = getPaid();
        List<PlanOffer> paid2 = retrieveEligibleSubscriptionOffersResponse.getPaid();
        if (paid != paid2) {
            if (paid == null) {
                return -1;
            }
            if (paid2 == null) {
                return 1;
            }
            if (paid instanceof Comparable) {
                int iCompareTo = ((Comparable) paid).compareTo(paid2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!paid.equals(paid2)) {
                int iHashCode = paid.hashCode();
                int iHashCode2 = paid2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<PlanOffer> trial = getTrial();
        List<PlanOffer> trial2 = retrieveEligibleSubscriptionOffersResponse.getTrial();
        if (trial != trial2) {
            if (trial == null) {
                return -1;
            }
            if (trial2 == null) {
                return 1;
            }
            if (trial instanceof Comparable) {
                int iCompareTo2 = ((Comparable) trial).compareTo(trial2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!trial.equals(trial2)) {
                int iHashCode3 = trial.hashCode();
                int iHashCode4 = trial2.hashCode();
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
        return internalEqualityCheck(getPaid(), retrieveEligibleSubscriptionOffersResponse.getPaid()) && internalEqualityCheck(getTrial(), retrieveEligibleSubscriptionOffersResponse.getTrial());
    }

    public List<PlanOffer> getPaid() {
        return this.paid;
    }

    public List<PlanOffer> getTrial() {
        return this.trial;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPaid(), getTrial());
    }

    public void setPaid(List<PlanOffer> list) {
        this.paid = list;
    }

    public void setTrial(List<PlanOffer> list) {
        this.trial = list;
    }
}
