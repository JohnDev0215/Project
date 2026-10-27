package org.joda.time;

import org.joda.time.base.BaseInterval;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/* JADX INFO: loaded from: classes6.dex */
public final class Interval extends BaseInterval {
    public static Interval parseWithOffset(String str) {
        DateTime dateTime;
        int iIndexOf = str.indexOf(47);
        if (iIndexOf < 0) {
            throw new IllegalArgumentException("Format requires a '/' separator: " + str);
        }
        String strSubstring = str.substring(0, iIndexOf);
        if (strSubstring.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + str);
        }
        String strSubstring2 = str.substring(iIndexOf + 1);
        if (strSubstring2.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + str);
        }
        DateTimeFormatter dateTimeFormatterWithOffsetParsed = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
        PeriodFormatter periodFormatterStandard = ISOPeriodFormat.standard();
        char cCharAt = strSubstring.charAt(0);
        Period period = null;
        if (cCharAt == 'P' || cCharAt == 'p') {
            period = periodFormatterStandard.withParseType(PeriodType.standard()).parsePeriod(strSubstring);
            dateTime = null;
        } else {
            dateTime = dateTimeFormatterWithOffsetParsed.parseDateTime(strSubstring);
        }
        char cCharAt2 = strSubstring2.charAt(0);
        if (cCharAt2 != 'P' && cCharAt2 != 'p') {
            DateTime dateTime2 = dateTimeFormatterWithOffsetParsed.parseDateTime(strSubstring2);
            if (period != null) {
                return new Interval(period, dateTime2);
            }
            return new Interval(dateTime, dateTime2);
        }
        if (period != null) {
            throw new IllegalArgumentException("Interval composed of two durations: " + str);
        }
        return new Interval(dateTime, periodFormatterStandard.withParseType(PeriodType.standard()).parsePeriod(strSubstring2));
    }

    public Interval(long j, long j2) {
        super(j, j2, null);
    }

    public Interval(long j, long j2, DateTimeZone dateTimeZone) {
        super(j, j2, ISOChronology.getInstance(dateTimeZone));
    }

    public Interval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        super(readableInstant, readableInstant2);
    }

    public Interval(ReadableInstant readableInstant, ReadablePeriod readablePeriod) {
        super(readableInstant, readablePeriod);
    }

    public Interval(ReadablePeriod readablePeriod, ReadableInstant readableInstant) {
        super(readablePeriod, readableInstant);
    }
}
