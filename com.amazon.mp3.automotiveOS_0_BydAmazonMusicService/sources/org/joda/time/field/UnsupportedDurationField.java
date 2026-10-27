package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/* JADX INFO: loaded from: classes6.dex */
public final class UnsupportedDurationField extends DurationField implements Serializable {
    private static HashMap<DurationFieldType, UnsupportedDurationField> cCache;
    private final DurationFieldType iType;

    @Override // java.lang.Comparable
    public int compareTo(DurationField durationField) {
        return 0;
    }

    @Override // org.joda.time.DurationField
    public long getUnitMillis() {
        return 0L;
    }

    @Override // org.joda.time.DurationField
    public boolean isPrecise() {
        return true;
    }

    @Override // org.joda.time.DurationField
    public boolean isSupported() {
        return false;
    }

    public static synchronized UnsupportedDurationField getInstance(DurationFieldType durationFieldType) {
        UnsupportedDurationField unsupportedDurationField;
        HashMap<DurationFieldType, UnsupportedDurationField> map = cCache;
        if (map == null) {
            cCache = new HashMap<>(7);
            unsupportedDurationField = null;
        } else {
            unsupportedDurationField = map.get(durationFieldType);
        }
        if (unsupportedDurationField == null) {
            unsupportedDurationField = new UnsupportedDurationField(durationFieldType);
            cCache.put(durationFieldType, unsupportedDurationField);
        }
        return unsupportedDurationField;
    }

    private UnsupportedDurationField(DurationFieldType durationFieldType) {
        this.iType = durationFieldType;
    }

    @Override // org.joda.time.DurationField
    public final DurationFieldType getType() {
        return this.iType;
    }

    public String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DurationField
    public long add(long j, int i) {
        throw unsupported();
    }

    @Override // org.joda.time.DurationField
    public long add(long j, long j2) {
        throw unsupported();
    }

    @Override // org.joda.time.DurationField
    public int getDifference(long j, long j2) {
        throw unsupported();
    }

    @Override // org.joda.time.DurationField
    public long getDifferenceAsLong(long j, long j2) {
        throw unsupported();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnsupportedDurationField)) {
            return false;
        }
        UnsupportedDurationField unsupportedDurationField = (UnsupportedDurationField) obj;
        if (unsupportedDurationField.getName() == null) {
            return getName() == null;
        }
        return unsupportedDurationField.getName().equals(getName());
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public String toString() {
        return "UnsupportedDurationField[" + getName() + ']';
    }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(this.iType + " field is unsupported");
    }
}
