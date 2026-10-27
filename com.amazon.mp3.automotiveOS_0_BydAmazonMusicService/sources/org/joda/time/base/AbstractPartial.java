package org.joda.time.base;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AbstractPartial implements ReadablePartial, Comparable<ReadablePartial> {
    protected abstract DateTimeField getField(int i, Chronology chronology);

    protected AbstractPartial() {
    }

    @Override // org.joda.time.ReadablePartial
    public DateTimeFieldType getFieldType(int i) {
        return getField(i, getChronology()).getType();
    }

    @Override // org.joda.time.ReadablePartial
    public DateTimeField getField(int i) {
        return getField(i, getChronology());
    }

    @Override // org.joda.time.ReadablePartial
    public int get(DateTimeFieldType dateTimeFieldType) {
        return getValue(indexOfSupported(dateTimeFieldType));
    }

    @Override // org.joda.time.ReadablePartial
    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        return indexOf(dateTimeFieldType) != -1;
    }

    public int indexOf(DateTimeFieldType dateTimeFieldType) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getFieldType(i) == dateTimeFieldType) {
                return i;
            }
        }
        return -1;
    }

    protected int indexOfSupported(DateTimeFieldType dateTimeFieldType) {
        int iIndexOf = indexOf(dateTimeFieldType);
        if (iIndexOf != -1) {
            return iIndexOf;
        }
        throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePartial)) {
            return false;
        }
        ReadablePartial readablePartial = (ReadablePartial) obj;
        if (size() != readablePartial.size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getValue(i) != readablePartial.getValue(i) || getFieldType(i) != readablePartial.getFieldType(i)) {
                return false;
            }
        }
        return FieldUtils.equals(getChronology(), readablePartial.getChronology());
    }

    public int hashCode() {
        int size = size();
        int value = GeneratorAdapter.GT;
        for (int i = 0; i < size; i++) {
            value = (((value * 23) + getValue(i)) * 23) + getFieldType(i).hashCode();
        }
        return value + getChronology().hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (size() != readablePartial.size()) {
            throw new ClassCastException("ReadablePartial objects must have matching field types");
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getFieldType(i) != readablePartial.getFieldType(i)) {
                throw new ClassCastException("ReadablePartial objects must have matching field types");
            }
        }
        int size2 = size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (getValue(i2) > readablePartial.getValue(i2)) {
                return 1;
            }
            if (getValue(i2) < readablePartial.getValue(i2)) {
                return -1;
            }
        }
        return 0;
    }

    public String toString(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            return toString();
        }
        return dateTimeFormatter.print(this);
    }
}
