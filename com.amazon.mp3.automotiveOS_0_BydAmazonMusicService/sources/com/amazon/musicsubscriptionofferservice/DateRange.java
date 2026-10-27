package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DateRange implements Comparable<DateRange> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.DateRange");
    private Date from;
    private Date to;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DateRange dateRange) {
        if (dateRange == null) {
            return -1;
        }
        if (dateRange == this) {
            return 0;
        }
        Date to = getTo();
        Date to2 = dateRange.getTo();
        if (to != to2) {
            if (to == null) {
                return -1;
            }
            if (to2 == null) {
                return 1;
            }
            int iCompareTo = to.compareTo(to2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date from = getFrom();
        Date from2 = dateRange.getFrom();
        if (from != from2) {
            if (from == null) {
                return -1;
            }
            if (from2 == null) {
                return 1;
            }
            int iCompareTo2 = from.compareTo(from2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DateRange)) {
            return false;
        }
        DateRange dateRange = (DateRange) obj;
        return internalEqualityCheck(getTo(), dateRange.getTo()) && internalEqualityCheck(getFrom(), dateRange.getFrom());
    }

    public Date getFrom() {
        return this.from;
    }

    public Date getTo() {
        return this.to;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTo(), getFrom());
    }

    public void setFrom(Date date) {
        this.from = date;
    }

    public void setTo(Date date) {
        this.to = date;
    }
}
