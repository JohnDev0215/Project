package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePeriod;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseSingleFieldPeriod implements ReadablePeriod, Comparable<BaseSingleFieldPeriod>, Serializable {
    private volatile int iPeriod;

    public abstract DurationFieldType getFieldType();

    @Override // org.joda.time.ReadablePeriod
    public abstract PeriodType getPeriodType();

    @Override // org.joda.time.ReadablePeriod
    public int size() {
        return 1;
    }

    protected BaseSingleFieldPeriod(int i) {
        this.iPeriod = i;
    }

    protected int getValue() {
        return this.iPeriod;
    }

    protected void setValue(int i) {
        this.iPeriod = i;
    }

    @Override // org.joda.time.ReadablePeriod
    public DurationFieldType getFieldType(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        return getFieldType();
    }

    @Override // org.joda.time.ReadablePeriod
    public int getValue(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        return getValue();
    }

    @Override // org.joda.time.ReadablePeriod
    public int get(DurationFieldType durationFieldType) {
        if (durationFieldType == getFieldType()) {
            return getValue();
        }
        return 0;
    }

    @Override // org.joda.time.ReadablePeriod
    public Period toPeriod() {
        return Period.ZERO.withFields(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePeriod)) {
            return false;
        }
        ReadablePeriod readablePeriod = (ReadablePeriod) obj;
        return readablePeriod.getPeriodType() == getPeriodType() && readablePeriod.getValue(0) == getValue();
    }

    public int hashCode() {
        return ((459 + getValue()) * 27) + getFieldType().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(BaseSingleFieldPeriod baseSingleFieldPeriod) {
        if (baseSingleFieldPeriod.getClass() != getClass()) {
            throw new ClassCastException(getClass() + " cannot be compared to " + baseSingleFieldPeriod.getClass());
        }
        int value = baseSingleFieldPeriod.getValue();
        int value2 = getValue();
        if (value2 > value) {
            return 1;
        }
        return value2 < value ? -1 : 0;
    }
}
