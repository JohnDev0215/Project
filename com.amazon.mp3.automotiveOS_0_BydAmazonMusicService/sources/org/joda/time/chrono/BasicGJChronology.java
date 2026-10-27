package org.joda.time.chrono;

import org.joda.time.Chronology;

/* JADX INFO: loaded from: classes6.dex */
abstract class BasicGJChronology extends BasicChronology {
    private static final int[] MIN_DAYS_PER_MONTH_ARRAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] MAX_DAYS_PER_MONTH_ARRAY = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
    private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];

    static {
        long j = 0;
        int i = 0;
        long j2 = 0;
        while (i < 11) {
            j += ((long) MIN_DAYS_PER_MONTH_ARRAY[i]) * 86400000;
            int i2 = i + 1;
            MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i2] = j;
            j2 += ((long) MAX_DAYS_PER_MONTH_ARRAY[i]) * 86400000;
            MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i2] = j2;
            i = i2;
        }
    }

    BasicGJChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    @Override // org.joda.time.chrono.BasicChronology
    boolean isLeapDay(long j) {
        return dayOfMonth().get(j) == 29 && monthOfYear().isLeap(j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        if (r13 < 5062500) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
    
        if (r13 < 12825000) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004e, code lost:
    
        if (r13 < 20587500) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005b, code lost:
    
        if (r13 < 28265625) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006f, code lost:
    
        if (r13 < 4978125) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007f, code lost:
    
        if (r13 < 12740625) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0094, code lost:
    
        if (r13 < 20503125) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a3, code lost:
    
        if (r13 < 28181250) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00a8, code lost:
    
        return 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:?, code lost:
    
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:?, code lost:
    
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:?, code lost:
    
        return 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:?, code lost:
    
        return 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:?, code lost:
    
        return 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:?, code lost:
    
        return 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:?, code lost:
    
        return 11;
     */
    @Override // org.joda.time.chrono.BasicChronology
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int getMonthOfYear(long r13, int r15) {
        /*
            r12 = this;
            long r0 = r12.getYearMillis(r15)
            long r13 = r13 - r0
            r0 = 10
            long r13 = r13 >> r0
            int r13 = (int) r13
            boolean r14 = r12.isLeapYear(r15)
            r15 = 2
            r1 = 3
            r2 = 5
            r3 = 6
            r4 = 8
            r5 = 9
            r6 = 11
            r7 = 12
            r8 = 1
            r9 = 4
            r10 = 7
            r11 = 2615625(0x27e949, float:3.665271E-39)
            if (r14 == 0) goto L5e
            r14 = 15356250(0xea515a, float:2.151869E-38)
            if (r13 >= r14) goto L40
            r14 = 7678125(0x7528ad, float:1.0759345E-38)
            if (r13 >= r14) goto L34
            if (r13 >= r11) goto L2e
            goto L6a
        L2e:
            r14 = 5062500(0x4d3f64, float:7.094073E-39)
            if (r13 >= r14) goto L73
            goto L71
        L34:
            r14 = 10209375(0x9bc85f, float:1.4306382E-38)
            if (r13 >= r14) goto L3a
            goto L7a
        L3a:
            r14 = 12825000(0xc3b1a8, float:1.7971653E-38)
            if (r13 >= r14) goto L83
            goto L81
        L40:
            r14 = 23118750(0x160c39e, float:4.128265E-38)
            if (r13 >= r14) goto L51
            r14 = 17971875(0x1123aa3, float:2.6858035E-38)
            if (r13 >= r14) goto L4b
            goto L8f
        L4b:
            r14 = 20587500(0x13a23ec, float:3.4188577E-38)
            if (r13 >= r14) goto L98
            goto L96
        L51:
            r14 = 25734375(0x188ace7, float:5.020661E-38)
            if (r13 >= r14) goto L58
            goto La8
        L58:
            r14 = 28265625(0x1af4c99, float:6.439476E-38)
            if (r13 >= r14) goto La7
            goto La5
        L5e:
            r14 = 15271875(0xe907c3, float:2.1400455E-38)
            if (r13 >= r14) goto L85
            r14 = 7593750(0x73df16, float:1.064111E-38)
            if (r13 >= r14) goto L75
            if (r13 >= r11) goto L6c
        L6a:
            r0 = r8
            goto La8
        L6c:
            r14 = 4978125(0x4bf5cd, float:6.975839E-39)
            if (r13 >= r14) goto L73
        L71:
            r0 = r15
            goto La8
        L73:
            r0 = r1
            goto La8
        L75:
            r14 = 10125000(0x9a7ec8, float:1.4188147E-38)
            if (r13 >= r14) goto L7c
        L7a:
            r0 = r9
            goto La8
        L7c:
            r14 = 12740625(0xc26811, float:1.7853418E-38)
            if (r13 >= r14) goto L83
        L81:
            r0 = r2
            goto La8
        L83:
            r0 = r3
            goto La8
        L85:
            r14 = 23034375(0x15f7a07, float:4.1046182E-38)
            if (r13 >= r14) goto L9a
            r14 = 17887500(0x110f10c, float:2.6621566E-38)
            if (r13 >= r14) goto L91
        L8f:
            r0 = r10
            goto La8
        L91:
            r14 = 20503125(0x138da55, float:3.3952108E-38)
            if (r13 >= r14) goto L98
        L96:
            r0 = r4
            goto La8
        L98:
            r0 = r5
            goto La8
        L9a:
            r14 = 25650000(0x1876350, float:4.9733674E-38)
            if (r13 >= r14) goto La0
            goto La8
        La0:
            r14 = 28181250(0x1ae0302, float:6.392182E-38)
            if (r13 >= r14) goto La7
        La5:
            r0 = r6
            goto La8
        La7:
            r0 = r7
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.chrono.BasicGJChronology.getMonthOfYear(long, int):int");
    }

    @Override // org.joda.time.chrono.BasicChronology
    int getDaysInYearMonth(int i, int i2) {
        if (isLeapYear(i)) {
            return MAX_DAYS_PER_MONTH_ARRAY[i2 - 1];
        }
        return MIN_DAYS_PER_MONTH_ARRAY[i2 - 1];
    }

    @Override // org.joda.time.chrono.BasicChronology
    int getDaysInMonthMax(int i) {
        return MAX_DAYS_PER_MONTH_ARRAY[i - 1];
    }

    @Override // org.joda.time.chrono.BasicChronology
    int getDaysInMonthMaxForSet(long j, int i) {
        if (i > 28 || i < 1) {
            return getDaysInMonthMax(j);
        }
        return 28;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getTotalMillisByYearMonth(int i, int i2) {
        if (isLeapYear(i)) {
            return MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1];
        }
        return MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1];
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getYearDifference(long j, long j2) {
        int year = getYear(j);
        int year2 = getYear(j2);
        long yearMillis = j - getYearMillis(year);
        long yearMillis2 = j2 - getYearMillis(year2);
        if (yearMillis2 >= 5097600000L) {
            if (isLeapYear(year2)) {
                if (!isLeapYear(year)) {
                    yearMillis2 -= 86400000;
                }
            } else if (yearMillis >= 5097600000L && isLeapYear(year)) {
                yearMillis -= 86400000;
            }
        }
        int i = year - year2;
        if (yearMillis < yearMillis2) {
            i--;
        }
        return i;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long setYear(long j, int i) {
        int year = getYear(j);
        int dayOfYear = getDayOfYear(j, year);
        int millisOfDay = getMillisOfDay(j);
        if (dayOfYear > 59) {
            if (isLeapYear(year)) {
                if (!isLeapYear(i)) {
                    dayOfYear--;
                }
            } else if (isLeapYear(i)) {
                dayOfYear++;
            }
        }
        return getYearMonthDayMillis(i, 1, dayOfYear) + ((long) millisOfDay);
    }
}
