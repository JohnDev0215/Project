package org.joda.time;

import org.joda.time.base.BasePeriod;

/* JADX INFO: loaded from: classes6.dex */
public class MutablePeriod extends BasePeriod implements ReadWritablePeriod, Cloneable {
    public MutablePeriod() {
        super(0L, (PeriodType) null, (Chronology) null);
    }

    public MutablePeriod(long j, PeriodType periodType) {
        super(j, periodType, (Chronology) null);
    }

    public MutablePeriod(Object obj, PeriodType periodType, Chronology chronology) {
        super(obj, periodType, chronology);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void clear() {
        super.setValues(new int[size()]);
    }

    @Override // org.joda.time.base.BasePeriod, org.joda.time.ReadWritablePeriod
    public void setValue(int i, int i2) {
        super.setValue(i, i2);
    }

    @Override // org.joda.time.base.BasePeriod, org.joda.time.ReadWritablePeriod
    public void setPeriod(ReadablePeriod readablePeriod) {
        super.setPeriod(readablePeriod);
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

    @Override // org.joda.time.ReadWritablePeriod
    public void setYears(int i) {
        super.setField(DurationFieldType.years(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setMonths(int i) {
        super.setField(DurationFieldType.months(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setWeeks(int i) {
        super.setField(DurationFieldType.weeks(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setDays(int i) {
        super.setField(DurationFieldType.days(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setHours(int i) {
        super.setField(DurationFieldType.hours(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setMinutes(int i) {
        super.setField(DurationFieldType.minutes(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setSeconds(int i) {
        super.setField(DurationFieldType.seconds(), i);
    }

    @Override // org.joda.time.ReadWritablePeriod
    public void setMillis(int i) {
        super.setField(DurationFieldType.millis(), i);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError("Clone error");
        }
    }
}
