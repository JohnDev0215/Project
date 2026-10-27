package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Period implements Comparable<Period> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.Period");
    private Integer numberOfIntervals;
    private String timeInterval;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Period period) {
        if (period == null) {
            return -1;
        }
        if (period == this) {
            return 0;
        }
        String timeInterval = getTimeInterval();
        String timeInterval2 = period.getTimeInterval();
        if (timeInterval != timeInterval2) {
            if (timeInterval == null) {
                return -1;
            }
            if (timeInterval2 == null) {
                return 1;
            }
            int iCompareTo = timeInterval.compareTo(timeInterval2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer numberOfIntervals = getNumberOfIntervals();
        Integer numberOfIntervals2 = period.getNumberOfIntervals();
        if (numberOfIntervals != numberOfIntervals2) {
            if (numberOfIntervals == null) {
                return -1;
            }
            if (numberOfIntervals2 == null) {
                return 1;
            }
            int iCompareTo2 = numberOfIntervals.compareTo(numberOfIntervals2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        return internalEqualityCheck(getTimeInterval(), period.getTimeInterval()) && internalEqualityCheck(getNumberOfIntervals(), period.getNumberOfIntervals());
    }

    public Integer getNumberOfIntervals() {
        return this.numberOfIntervals;
    }

    public String getTimeInterval() {
        return this.timeInterval;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTimeInterval(), getNumberOfIntervals());
    }

    public void setNumberOfIntervals(Integer num) {
        this.numberOfIntervals = num;
    }

    public void setTimeInterval(String str) {
        this.timeInterval = str;
    }
}
