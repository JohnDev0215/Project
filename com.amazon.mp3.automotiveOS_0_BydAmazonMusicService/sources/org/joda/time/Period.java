package org.joda.time;

import org.joda.convert.FromString;
import org.joda.time.base.BasePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/* JADX INFO: loaded from: classes6.dex */
public final class Period extends BasePeriod {
    public static final Period ZERO = new Period();

    @Override // org.joda.time.base.AbstractPeriod, org.joda.time.ReadablePeriod
    public Period toPeriod() {
        return this;
    }

    @FromString
    public static Period parse(String str) {
        return parse(str, ISOPeriodFormat.standard());
    }

    public static Period parse(String str, PeriodFormatter periodFormatter) {
        return periodFormatter.parsePeriod(str);
    }

    public Period() {
        super(0L, (PeriodType) null, (Chronology) null);
    }

    public Period(long j) {
        super(j);
    }

    public Period(Object obj) {
        super(obj, (PeriodType) null, (Chronology) null);
    }

    private Period(int[] iArr, PeriodType periodType) {
        super(iArr, periodType);
    }

    public int getYears() {
        return getPeriodType().getIndexedField(this, PeriodType.YEAR_INDEX);
    }

    public int getMonths() {
        return getPeriodType().getIndexedField(this, PeriodType.MONTH_INDEX);
    }

    public int getWeeks() {
        return getPeriodType().getIndexedField(this, PeriodType.WEEK_INDEX);
    }

    public int getDays() {
        return getPeriodType().getIndexedField(this, PeriodType.DAY_INDEX);
    }

    public int getHours() {
        return getPeriodType().getIndexedField(this, PeriodType.HOUR_INDEX);
    }

    public int getMinutes() {
        return getPeriodType().getIndexedField(this, PeriodType.MINUTE_INDEX);
    }

    public int getSeconds() {
        return getPeriodType().getIndexedField(this, PeriodType.SECOND_INDEX);
    }

    public int getMillis() {
        return getPeriodType().getIndexedField(this, PeriodType.MILLI_INDEX);
    }

    public Period withFields(ReadablePeriod readablePeriod) {
        return readablePeriod == null ? this : new Period(super.mergePeriodInto(getValues(), readablePeriod), getPeriodType());
    }

    public Seconds toStandardSeconds() {
        checkYearsAndMonths("Seconds");
        return Seconds.seconds(FieldUtils.safeToInt(FieldUtils.safeAdd(FieldUtils.safeAdd(FieldUtils.safeAdd(FieldUtils.safeAdd(FieldUtils.safeAdd(getMillis() / 1000, getSeconds()), ((long) getMinutes()) * 60), ((long) getHours()) * 3600), ((long) getDays()) * 86400), ((long) getWeeks()) * 604800)));
    }

    public Duration toStandardDuration() {
        checkYearsAndMonths("Duration");
        return new Duration(((long) getMillis()) + (((long) getSeconds()) * 1000) + (((long) getMinutes()) * 60000) + (((long) getHours()) * 3600000) + (((long) getDays()) * 86400000) + (((long) getWeeks()) * 604800000));
    }

    private void checkYearsAndMonths(String str) {
        if (getMonths() != 0) {
            throw new UnsupportedOperationException("Cannot convert to " + str + " as this period contains months and months vary in length");
        }
        if (getYears() == 0) {
            return;
        }
        throw new UnsupportedOperationException("Cannot convert to " + str + " as this period contains years and years vary in length");
    }
}
