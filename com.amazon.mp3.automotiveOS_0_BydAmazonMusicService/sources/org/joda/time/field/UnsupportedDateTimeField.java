package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

/* JADX INFO: loaded from: classes6.dex */
public final class UnsupportedDateTimeField extends DateTimeField implements Serializable {
    private static HashMap<DateTimeFieldType, UnsupportedDateTimeField> cCache;
    private final DurationField iDurationField;
    private final DateTimeFieldType iType;

    @Override // org.joda.time.DateTimeField
    public DurationField getLeapDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isLenient() {
        return false;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isSupported() {
        return false;
    }

    public String toString() {
        return "UnsupportedDateTimeField";
    }

    public static synchronized UnsupportedDateTimeField getInstance(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        UnsupportedDateTimeField unsupportedDateTimeField;
        HashMap<DateTimeFieldType, UnsupportedDateTimeField> map = cCache;
        unsupportedDateTimeField = null;
        if (map == null) {
            cCache = new HashMap<>(7);
        } else {
            UnsupportedDateTimeField unsupportedDateTimeField2 = map.get(dateTimeFieldType);
            if (unsupportedDateTimeField2 == null || unsupportedDateTimeField2.getDurationField() == durationField) {
                unsupportedDateTimeField = unsupportedDateTimeField2;
            }
        }
        if (unsupportedDateTimeField == null) {
            unsupportedDateTimeField = new UnsupportedDateTimeField(dateTimeFieldType, durationField);
            cCache.put(dateTimeFieldType, unsupportedDateTimeField);
        }
        return unsupportedDateTimeField;
    }

    private UnsupportedDateTimeField(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        if (dateTimeFieldType == null || durationField == null) {
            throw new IllegalArgumentException();
        }
        this.iType = dateTimeFieldType;
        this.iDurationField = durationField;
    }

    @Override // org.joda.time.DateTimeField
    public DateTimeFieldType getType() {
        return this.iType;
    }

    @Override // org.joda.time.DateTimeField
    public String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DateTimeField
    public int get(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(long j, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(ReadablePartial readablePartial, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(int i, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(long j, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(ReadablePartial readablePartial, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(int i, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long add(long j, int i) {
        return getDurationField().add(j, i);
    }

    @Override // org.joda.time.DateTimeField
    public long add(long j, long j2) {
        return getDurationField().add(j, j2);
    }

    @Override // org.joda.time.DateTimeField
    public int getDifference(long j, long j2) {
        return getDurationField().getDifference(j, j2);
    }

    @Override // org.joda.time.DateTimeField
    public long getDifferenceAsLong(long j, long j2) {
        return getDurationField().getDifferenceAsLong(j, j2);
    }

    @Override // org.joda.time.DateTimeField
    public long set(long j, int i) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long set(long j, String str, Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getDurationField() {
        return this.iDurationField;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isLeap(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getLeapAmount(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue() {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(ReadablePartial readablePartial) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue() {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(ReadablePartial readablePartial) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumTextLength(Locale locale) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundFloor(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundCeiling(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfFloor(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfCeiling(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfEven(long j) {
        throw unsupported();
    }

    @Override // org.joda.time.DateTimeField
    public long remainder(long j) {
        throw unsupported();
    }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(this.iType + " field is unsupported");
    }
}
