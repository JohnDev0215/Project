package org.joda.time.field;

import org.joda.time.DurationFieldType;

/* JADX INFO: loaded from: classes6.dex */
public class PreciseDurationField extends BaseDurationField {
    private final long iUnitMillis;

    @Override // org.joda.time.DurationField
    public final boolean isPrecise() {
        return true;
    }

    public PreciseDurationField(DurationFieldType durationFieldType, long j) {
        super(durationFieldType);
        this.iUnitMillis = j;
    }

    @Override // org.joda.time.DurationField
    public final long getUnitMillis() {
        return this.iUnitMillis;
    }

    @Override // org.joda.time.DurationField
    public long add(long j, int i) {
        return FieldUtils.safeAdd(j, ((long) i) * this.iUnitMillis);
    }

    @Override // org.joda.time.DurationField
    public long add(long j, long j2) {
        return FieldUtils.safeAdd(j, FieldUtils.safeMultiply(j2, this.iUnitMillis));
    }

    @Override // org.joda.time.DurationField
    public long getDifferenceAsLong(long j, long j2) {
        return FieldUtils.safeSubtract(j, j2) / this.iUnitMillis;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PreciseDurationField)) {
            return false;
        }
        PreciseDurationField preciseDurationField = (PreciseDurationField) obj;
        return getType() == preciseDurationField.getType() && this.iUnitMillis == preciseDurationField.iUnitMillis;
    }

    public int hashCode() {
        long j = this.iUnitMillis;
        return ((int) (j ^ (j >>> 32))) + getType().hashCode();
    }
}
