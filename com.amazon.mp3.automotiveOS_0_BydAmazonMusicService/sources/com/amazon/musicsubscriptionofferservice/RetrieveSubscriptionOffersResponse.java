package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveSubscriptionOffersResponse implements Comparable<RetrieveSubscriptionOffersResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersResponse");
    private List<PlanSchedule> planSchedules;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveSubscriptionOffersResponse retrieveSubscriptionOffersResponse) {
        List<PlanSchedule> planSchedules;
        List<PlanSchedule> planSchedules2;
        if (retrieveSubscriptionOffersResponse == null) {
            return -1;
        }
        if (retrieveSubscriptionOffersResponse != this && (planSchedules = getPlanSchedules()) != (planSchedules2 = retrieveSubscriptionOffersResponse.getPlanSchedules())) {
            if (planSchedules == null) {
                return -1;
            }
            if (planSchedules2 == null) {
                return 1;
            }
            if (planSchedules instanceof Comparable) {
                int iCompareTo = ((Comparable) planSchedules).compareTo(planSchedules2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!planSchedules.equals(planSchedules2)) {
                int iHashCode = planSchedules.hashCode();
                int iHashCode2 = planSchedules2.hashCode();
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
        if (obj instanceof RetrieveSubscriptionOffersResponse) {
            return internalEqualityCheck(getPlanSchedules(), ((RetrieveSubscriptionOffersResponse) obj).getPlanSchedules());
        }
        return false;
    }

    public List<PlanSchedule> getPlanSchedules() {
        return this.planSchedules;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlanSchedules());
    }

    public void setPlanSchedules(List<PlanSchedule> list) {
        this.planSchedules = list;
    }
}
