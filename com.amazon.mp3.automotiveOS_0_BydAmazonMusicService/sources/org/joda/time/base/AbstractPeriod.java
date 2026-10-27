package org.joda.time.base;

import org.joda.convert.ToString;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AbstractPeriod implements ReadablePeriod {
    protected AbstractPeriod() {
    }

    @Override // org.joda.time.ReadablePeriod
    public int size() {
        return getPeriodType().size();
    }

    @Override // org.joda.time.ReadablePeriod
    public DurationFieldType getFieldType(int i) {
        return getPeriodType().getFieldType(i);
    }

    public int[] getValues() {
        int size = size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = getValue(i);
        }
        return iArr;
    }

    @Override // org.joda.time.ReadablePeriod
    public int get(DurationFieldType durationFieldType) {
        int iIndexOf = indexOf(durationFieldType);
        if (iIndexOf == -1) {
            return 0;
        }
        return getValue(iIndexOf);
    }

    public int indexOf(DurationFieldType durationFieldType) {
        return getPeriodType().indexOf(durationFieldType);
    }

    @Override // org.joda.time.ReadablePeriod
    public Period toPeriod() {
        return new Period(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePeriod)) {
            return false;
        }
        ReadablePeriod readablePeriod = (ReadablePeriod) obj;
        if (size() != readablePeriod.size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getValue(i) != readablePeriod.getValue(i) || getFieldType(i) != readablePeriod.getFieldType(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int value = 17;
        for (int i = 0; i < size; i++) {
            value = (((value * 27) + getValue(i)) * 27) + getFieldType(i).hashCode();
        }
        return value;
    }

    @ToString
    public String toString() {
        return ISOPeriodFormat.standard().print(this);
    }
}
