package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.SkipDateTimeField;

/* JADX INFO: loaded from: classes6.dex */
public final class JulianChronology extends BasicGJChronology {
    private static final ConcurrentHashMap<DateTimeZone, JulianChronology[]> cCache = new ConcurrentHashMap<>();
    private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);

    @Override // org.joda.time.chrono.BasicChronology
    long getApproxMillisAtEpochDividedByTwo() {
        return 31083663600000L;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerMonth() {
        return 2629800000L;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerYear() {
        return 31557600000L;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }

    @Override // org.joda.time.chrono.BasicChronology
    int getMaxYear() {
        return 292272992;
    }

    @Override // org.joda.time.chrono.BasicChronology
    int getMinYear() {
        return -292269054;
    }

    @Override // org.joda.time.chrono.BasicChronology
    boolean isLeapYear(int i) {
        return (i & 3) == 0;
    }

    @Override // org.joda.time.chrono.BasicChronology
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology, org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
    public /* bridge */ /* synthetic */ long getDateTimeMillis(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        return super.getDateTimeMillis(i, i2, i3, i4);
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology, org.joda.time.chrono.BaseChronology, org.joda.time.Chronology
    public /* bridge */ /* synthetic */ long getDateTimeMillis(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        return super.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
    }

    @Override // org.joda.time.chrono.BasicChronology
    public /* bridge */ /* synthetic */ int getMinimumDaysInFirstWeek() {
        return super.getMinimumDaysInFirstWeek();
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology, org.joda.time.Chronology
    public /* bridge */ /* synthetic */ DateTimeZone getZone() {
        return super.getZone();
    }

    @Override // org.joda.time.chrono.BasicChronology
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.Chronology
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    static int adjustYearForSet(int i) {
        if (i > 0) {
            return i;
        }
        if (i != 0) {
            return i + 1;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(i), null, null);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        JulianChronology julianChronology;
        JulianChronology[] julianChronologyArrPutIfAbsent;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        ConcurrentHashMap<DateTimeZone, JulianChronology[]> concurrentHashMap = cCache;
        JulianChronology[] julianChronologyArr = concurrentHashMap.get(dateTimeZone);
        if (julianChronologyArr == null && (julianChronologyArrPutIfAbsent = concurrentHashMap.putIfAbsent(dateTimeZone, (julianChronologyArr = new JulianChronology[7]))) != null) {
            julianChronologyArr = julianChronologyArrPutIfAbsent;
        }
        int i2 = i - 1;
        try {
            JulianChronology julianChronology2 = julianChronologyArr[i2];
            if (julianChronology2 == null) {
                synchronized (julianChronologyArr) {
                    julianChronology2 = julianChronologyArr[i2];
                    if (julianChronology2 == null) {
                        DateTimeZone dateTimeZone2 = DateTimeZone.UTC;
                        if (dateTimeZone == dateTimeZone2) {
                            julianChronology = new JulianChronology(null, null, i);
                        } else {
                            julianChronology = new JulianChronology(ZonedChronology.getInstance(getInstance(dateTimeZone2, i), dateTimeZone), null, i);
                        }
                        julianChronologyArr[i2] = julianChronology;
                        julianChronology2 = julianChronology;
                    }
                }
            }
            return julianChronology2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    JulianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    @Override // org.joda.time.Chronology
    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    @Override // org.joda.time.Chronology
    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getDateMidnightMillis(int i, int i2, int i3) throws IllegalArgumentException {
        return super.getDateMidnightMillis(adjustYearForSet(i), i2, i3);
    }

    @Override // org.joda.time.chrono.BasicChronology
    long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1968;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            int i4 = i3 >> 2;
            i2 = !isLeapYear(i) ? i4 + 1 : i4;
        }
        return (((((long) i3) * 365) + ((long) i2)) * 86400000) - 62035200000L;
    }

    @Override // org.joda.time.chrono.BasicChronology, org.joda.time.chrono.AssembledChronology
    protected void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
        }
    }
}
