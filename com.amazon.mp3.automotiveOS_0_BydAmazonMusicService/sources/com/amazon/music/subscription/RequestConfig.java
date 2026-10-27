package com.amazon.music.subscription;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class RequestConfig implements Comparable<RequestConfig> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.RequestConfig");
    private String bountyContext;
    private String contextType;
    private Boolean isFamilySitewide2020HolidayPromo;
    private Boolean keepFreeTrialEligAfterCancellation;
    private Boolean payBounty;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestConfig requestConfig) {
        if (requestConfig == null) {
            return -1;
        }
        if (requestConfig == this) {
            return 0;
        }
        Boolean boolIsPayBounty = isPayBounty();
        Boolean boolIsPayBounty2 = requestConfig.isPayBounty();
        if (boolIsPayBounty != boolIsPayBounty2) {
            if (boolIsPayBounty == null) {
                return -1;
            }
            if (boolIsPayBounty2 == null) {
                return 1;
            }
            int iCompareTo = boolIsPayBounty.compareTo(boolIsPayBounty2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String bountyContext = getBountyContext();
        String bountyContext2 = requestConfig.getBountyContext();
        if (bountyContext != bountyContext2) {
            if (bountyContext == null) {
                return -1;
            }
            if (bountyContext2 == null) {
                return 1;
            }
            int iCompareTo2 = bountyContext.compareTo(bountyContext2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String contextType = getContextType();
        String contextType2 = requestConfig.getContextType();
        if (contextType != contextType2) {
            if (contextType == null) {
                return -1;
            }
            if (contextType2 == null) {
                return 1;
            }
            int iCompareTo3 = contextType.compareTo(contextType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIsFamilySitewide2020HolidayPromo = isIsFamilySitewide2020HolidayPromo();
        Boolean boolIsIsFamilySitewide2020HolidayPromo2 = requestConfig.isIsFamilySitewide2020HolidayPromo();
        if (boolIsIsFamilySitewide2020HolidayPromo != boolIsIsFamilySitewide2020HolidayPromo2) {
            if (boolIsIsFamilySitewide2020HolidayPromo == null) {
                return -1;
            }
            if (boolIsIsFamilySitewide2020HolidayPromo2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsFamilySitewide2020HolidayPromo.compareTo(boolIsIsFamilySitewide2020HolidayPromo2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsKeepFreeTrialEligAfterCancellation = isKeepFreeTrialEligAfterCancellation();
        Boolean boolIsKeepFreeTrialEligAfterCancellation2 = requestConfig.isKeepFreeTrialEligAfterCancellation();
        if (boolIsKeepFreeTrialEligAfterCancellation != boolIsKeepFreeTrialEligAfterCancellation2) {
            if (boolIsKeepFreeTrialEligAfterCancellation == null) {
                return -1;
            }
            if (boolIsKeepFreeTrialEligAfterCancellation2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsKeepFreeTrialEligAfterCancellation.compareTo(boolIsKeepFreeTrialEligAfterCancellation2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RequestConfig)) {
            return false;
        }
        RequestConfig requestConfig = (RequestConfig) obj;
        return internalEqualityCheck(isPayBounty(), requestConfig.isPayBounty()) && internalEqualityCheck(getBountyContext(), requestConfig.getBountyContext()) && internalEqualityCheck(getContextType(), requestConfig.getContextType()) && internalEqualityCheck(isIsFamilySitewide2020HolidayPromo(), requestConfig.isIsFamilySitewide2020HolidayPromo()) && internalEqualityCheck(isKeepFreeTrialEligAfterCancellation(), requestConfig.isKeepFreeTrialEligAfterCancellation());
    }

    public String getBountyContext() {
        return this.bountyContext;
    }

    public String getContextType() {
        return this.contextType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isPayBounty(), getBountyContext(), getContextType(), isIsFamilySitewide2020HolidayPromo(), isKeepFreeTrialEligAfterCancellation());
    }

    public Boolean isIsFamilySitewide2020HolidayPromo() {
        return this.isFamilySitewide2020HolidayPromo;
    }

    public Boolean isKeepFreeTrialEligAfterCancellation() {
        return this.keepFreeTrialEligAfterCancellation;
    }

    public Boolean isPayBounty() {
        return this.payBounty;
    }
}
