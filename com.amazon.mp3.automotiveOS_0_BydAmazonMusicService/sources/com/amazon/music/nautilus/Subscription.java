package com.amazon.music.nautilus;

import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class Subscription {
    private final Date endDate;
    private final boolean hasOpenProblems;
    private final boolean isAutoRenew;
    private final String planId;
    private final String rolloverPlanId;

    public Subscription(String str, String str2, boolean z, Date date, boolean z2) {
        this.planId = str;
        this.rolloverPlanId = str2;
        this.hasOpenProblems = z;
        this.isAutoRenew = z2;
        this.endDate = date;
    }

    public String getPlanId() {
        return this.planId;
    }

    public boolean hasOpenProblems() {
        return this.hasOpenProblems;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public boolean isAutoRenew() {
        return this.isAutoRenew;
    }

    public String getRolloverPlanId() {
        return this.rolloverPlanId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return equals(this.planId, subscription.planId) && equals(this.rolloverPlanId, subscription.rolloverPlanId) && equals(Boolean.valueOf(this.hasOpenProblems), Boolean.valueOf(subscription.hasOpenProblems)) && equals(this.endDate, subscription.endDate) && equals(Boolean.valueOf(this.isAutoRenew), Boolean.valueOf(subscription.isAutoRenew));
    }

    public int hashCode() {
        return hash(this.planId, this.rolloverPlanId, Boolean.valueOf(this.hasOpenProblems), this.endDate, Boolean.valueOf(this.isAutoRenew));
    }

    private int hash(Object... objArr) {
        int length = objArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            iHashCode = (iHashCode * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
