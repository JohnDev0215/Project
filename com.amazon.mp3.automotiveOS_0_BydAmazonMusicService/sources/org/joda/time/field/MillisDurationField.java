package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/* JADX INFO: loaded from: classes6.dex */
public final class MillisDurationField extends DurationField implements Serializable {
    public static final DurationField INSTANCE = new MillisDurationField();

    @Override // org.joda.time.DurationField
    public final long getUnitMillis() {
        return 1L;
    }

    @Override // org.joda.time.DurationField
    public final boolean isPrecise() {
        return true;
    }

    @Override // org.joda.time.DurationField
    public boolean isSupported() {
        return true;
    }

    public String toString() {
        return "DurationField[millis]";
    }

    private MillisDurationField() {
    }

    @Override // org.joda.time.DurationField
    public DurationFieldType getType() {
        return DurationFieldType.millis();
    }

    @Override // org.joda.time.DurationField
    public long add(long j, int i) {
        return FieldUtils.safeAdd(j, i);
    }

    @Override // org.joda.time.DurationField
    public long add(long j, long j2) {
        return FieldUtils.safeAdd(j, j2);
    }

    @Override // org.joda.time.DurationField
    public int getDifference(long j, long j2) {
        return FieldUtils.safeToInt(FieldUtils.safeSubtract(j, j2));
    }

    @Override // org.joda.time.DurationField
    public long getDifferenceAsLong(long j, long j2) {
        return FieldUtils.safeSubtract(j, j2);
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

    public boolean equals(Object obj) {
        return (obj instanceof MillisDurationField) && getUnitMillis() == ((MillisDurationField) obj).getUnitMillis();
    }

    public int hashCode() {
        return (int) getUnitMillis();
    }
}
