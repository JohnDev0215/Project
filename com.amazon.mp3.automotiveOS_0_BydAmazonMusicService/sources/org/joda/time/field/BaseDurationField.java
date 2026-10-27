package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseDurationField extends DurationField implements Serializable {
    private final DurationFieldType iType;

    @Override // org.joda.time.DurationField
    public final boolean isSupported() {
        return true;
    }

    protected BaseDurationField(DurationFieldType durationFieldType) {
        if (durationFieldType == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.iType = durationFieldType;
    }

    @Override // org.joda.time.DurationField
    public final DurationFieldType getType() {
        return this.iType;
    }

    public final String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DurationField
    public int getDifference(long j, long j2) {
        return FieldUtils.safeToInt(getDifferenceAsLong(j, j2));
    }

    @Override // java.lang.Comparable
    public int compareTo(DurationField durationField) {
        long unitMillis = durationField.getUnitMillis();
        long unitMillis2 = getUnitMillis();
        if (unitMillis2 == unitMillis) {
            return 0;
        }
        return unitMillis2 < unitMillis ? -1 : 1;
    }

    public String toString() {
        return "DurationField[" + getName() + ']';
    }
}
