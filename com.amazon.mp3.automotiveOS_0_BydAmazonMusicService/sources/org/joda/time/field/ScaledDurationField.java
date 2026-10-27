package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/* JADX INFO: loaded from: classes6.dex */
public class ScaledDurationField extends DecoratedDurationField {
    private final int iScalar;

    public ScaledDurationField(DurationField durationField, DurationFieldType durationFieldType, int i) {
        super(durationField, durationFieldType);
        if (i == 0 || i == 1) {
            throw new IllegalArgumentException("The scalar must not be 0 or 1");
        }
        this.iScalar = i;
    }

    @Override // org.joda.time.field.DecoratedDurationField, org.joda.time.DurationField
    public long add(long j, int i) {
        return getWrappedField().add(j, ((long) i) * ((long) this.iScalar));
    }

    @Override // org.joda.time.field.DecoratedDurationField, org.joda.time.DurationField
    public long add(long j, long j2) {
        return getWrappedField().add(j, FieldUtils.safeMultiply(j2, this.iScalar));
    }

    @Override // org.joda.time.field.BaseDurationField, org.joda.time.DurationField
    public int getDifference(long j, long j2) {
        return getWrappedField().getDifference(j, j2) / this.iScalar;
    }

    @Override // org.joda.time.field.DecoratedDurationField, org.joda.time.DurationField
    public long getDifferenceAsLong(long j, long j2) {
        return getWrappedField().getDifferenceAsLong(j, j2) / ((long) this.iScalar);
    }

    @Override // org.joda.time.field.DecoratedDurationField, org.joda.time.DurationField
    public long getUnitMillis() {
        return getWrappedField().getUnitMillis() * ((long) this.iScalar);
    }

    public int getScalar() {
        return this.iScalar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScaledDurationField)) {
            return false;
        }
        ScaledDurationField scaledDurationField = (ScaledDurationField) obj;
        return getWrappedField().equals(scaledDurationField.getWrappedField()) && getType() == scaledDurationField.getType() && this.iScalar == scaledDurationField.iScalar;
    }

    public int hashCode() {
        long j = this.iScalar;
        return ((int) (j ^ (j >>> 32))) + getType().hashCode() + getWrappedField().hashCode();
    }
}
