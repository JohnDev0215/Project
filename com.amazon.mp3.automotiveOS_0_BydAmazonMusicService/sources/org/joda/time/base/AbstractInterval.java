package org.joda.time.base;

import com.fasterxml.jackson.core.JsonPointer;
import org.joda.time.DateTime;
import org.joda.time.ReadableInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AbstractInterval implements ReadableInterval {
    protected AbstractInterval() {
    }

    protected void checkInterval(long j, long j2) {
        if (j2 < j) {
            throw new IllegalArgumentException("The end instant must be greater than the start instant");
        }
    }

    public DateTime getStart() {
        return new DateTime(getStartMillis(), getChronology());
    }

    public DateTime getEnd() {
        return new DateTime(getEndMillis(), getChronology());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadableInterval)) {
            return false;
        }
        ReadableInterval readableInterval = (ReadableInterval) obj;
        return getStartMillis() == readableInterval.getStartMillis() && getEndMillis() == readableInterval.getEndMillis() && FieldUtils.equals(getChronology(), readableInterval.getChronology());
    }

    public int hashCode() {
        long startMillis = getStartMillis();
        long endMillis = getEndMillis();
        return ((((3007 + ((int) (startMillis ^ (startMillis >>> 32)))) * 31) + ((int) (endMillis ^ (endMillis >>> 32)))) * 31) + getChronology().hashCode();
    }

    public String toString() {
        DateTimeFormatter dateTimeFormatterWithChronology = ISODateTimeFormat.dateTime().withChronology(getChronology());
        StringBuffer stringBuffer = new StringBuffer(48);
        dateTimeFormatterWithChronology.printTo(stringBuffer, getStartMillis());
        stringBuffer.append(JsonPointer.SEPARATOR);
        dateTimeFormatterWithChronology.printTo(stringBuffer, getEndMillis());
        return stringBuffer.toString();
    }
}
