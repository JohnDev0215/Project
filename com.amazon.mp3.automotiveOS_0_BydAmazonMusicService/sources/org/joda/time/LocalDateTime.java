package org.joda.time;

import java.io.Serializable;
import org.joda.convert.ToString;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public final class LocalDateTime extends BaseLocal implements Serializable {
    private final Chronology iChronology;
    private final long iLocalMillis;

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return 4;
    }

    public LocalDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalDateTime(long j, DateTimeZone dateTimeZone) {
        this(j, ISOChronology.getInstance(dateTimeZone));
    }

    public LocalDateTime(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iLocalMillis = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        this.iChronology = chronology2.withUTC();
    }

    public LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(i, i2, i3, i4, i5, i6, i7, ISOChronology.getInstanceUTC());
    }

    public LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, Chronology chronology) {
        Chronology chronologyWithUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = chronologyWithUTC.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        this.iChronology = chronologyWithUTC;
        this.iLocalMillis = dateTimeMillis;
    }

    @Override // org.joda.time.base.AbstractPartial
    protected DateTimeField getField(int i, Chronology chronology) {
        if (i == 0) {
            return chronology.year();
        }
        if (i == 1) {
            return chronology.monthOfYear();
        }
        if (i == 2) {
            return chronology.dayOfMonth();
        }
        if (i == 3) {
            return chronology.millisOfDay();
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.ReadablePartial
    public int getValue(int i) {
        if (i == 0) {
            return getChronology().year().get(getLocalMillis());
        }
        if (i == 1) {
            return getChronology().monthOfYear().get(getLocalMillis());
        }
        if (i == 2) {
            return getChronology().dayOfMonth().get(getLocalMillis());
        }
        if (i == 3) {
            return getChronology().millisOfDay().get(getLocalMillis());
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public int get(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        return dateTimeFieldType.getField(getChronology()).get(getLocalMillis());
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            return false;
        }
        return dateTimeFieldType.getField(getChronology()).isSupported();
    }

    protected long getLocalMillis() {
        return this.iLocalMillis;
    }

    @Override // org.joda.time.ReadablePartial
    public Chronology getChronology() {
        return this.iChronology;
    }

    @Override // org.joda.time.base.AbstractPartial
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) obj;
            if (this.iChronology.equals(localDateTime.iChronology)) {
                return this.iLocalMillis == localDateTime.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    @Override // org.joda.time.base.AbstractPartial
    public int hashCode() {
        return ((((((((((((((3611 + this.iChronology.year().get(this.iLocalMillis)) * 23) + this.iChronology.year().getType().hashCode()) * 23) + this.iChronology.monthOfYear().get(this.iLocalMillis)) * 23) + this.iChronology.monthOfYear().getType().hashCode()) * 23) + this.iChronology.dayOfMonth().get(this.iLocalMillis)) * 23) + this.iChronology.dayOfMonth().getType().hashCode()) * 23) + this.iChronology.millisOfDay().get(this.iLocalMillis)) * 23) + this.iChronology.millisOfDay().getType().hashCode() + getChronology().hashCode();
    }

    @Override // org.joda.time.base.AbstractPartial, java.lang.Comparable
    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) readablePartial;
            if (this.iChronology.equals(localDateTime.iChronology)) {
                long j = this.iLocalMillis;
                long j2 = localDateTime.iLocalMillis;
                if (j < j2) {
                    return -1;
                }
                return j == j2 ? 0 : 1;
            }
        }
        return super.compareTo(readablePartial);
    }

    public DateTime toDateTime(DateTimeZone dateTimeZone) {
        return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), getHourOfDay(), getMinuteOfHour(), getSecondOfMinute(), getMillisOfSecond(), this.iChronology.withZone(DateTimeUtils.getZone(dateTimeZone)));
    }

    public LocalDate toLocalDate() {
        return new LocalDate(getLocalMillis(), getChronology());
    }

    public LocalTime toLocalTime() {
        return new LocalTime(getLocalMillis(), getChronology());
    }

    public int getEra() {
        return getChronology().era().get(getLocalMillis());
    }

    public int getCenturyOfEra() {
        return getChronology().centuryOfEra().get(getLocalMillis());
    }

    public int getYearOfEra() {
        return getChronology().yearOfEra().get(getLocalMillis());
    }

    public int getYearOfCentury() {
        return getChronology().yearOfCentury().get(getLocalMillis());
    }

    public int getYear() {
        return getChronology().year().get(getLocalMillis());
    }

    public int getWeekyear() {
        return getChronology().weekyear().get(getLocalMillis());
    }

    public int getMonthOfYear() {
        return getChronology().monthOfYear().get(getLocalMillis());
    }

    public int getWeekOfWeekyear() {
        return getChronology().weekOfWeekyear().get(getLocalMillis());
    }

    public int getDayOfYear() {
        return getChronology().dayOfYear().get(getLocalMillis());
    }

    public int getDayOfMonth() {
        return getChronology().dayOfMonth().get(getLocalMillis());
    }

    public int getDayOfWeek() {
        return getChronology().dayOfWeek().get(getLocalMillis());
    }

    public int getHourOfDay() {
        return getChronology().hourOfDay().get(getLocalMillis());
    }

    public int getMinuteOfHour() {
        return getChronology().minuteOfHour().get(getLocalMillis());
    }

    public int getSecondOfMinute() {
        return getChronology().secondOfMinute().get(getLocalMillis());
    }

    public int getMillisOfSecond() {
        return getChronology().millisOfSecond().get(getLocalMillis());
    }

    public int getMillisOfDay() {
        return getChronology().millisOfDay().get(getLocalMillis());
    }

    public Property year() {
        return new Property(this, getChronology().year());
    }

    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }

    public Property hourOfDay() {
        return new Property(this, getChronology().hourOfDay());
    }

    public Property minuteOfHour() {
        return new Property(this, getChronology().minuteOfHour());
    }

    public Property secondOfMinute() {
        return new Property(this, getChronology().secondOfMinute());
    }

    public Property millisOfSecond() {
        return new Property(this, getChronology().millisOfSecond());
    }

    @ToString
    public String toString() {
        return ISODateTimeFormat.dateTime().print(this);
    }

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private transient DateTimeField iField;
        private transient LocalDateTime iInstant;

        Property(LocalDateTime localDateTime, DateTimeField dateTimeField) {
            this.iInstant = localDateTime;
            this.iField = dateTimeField;
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        public DateTimeField getField() {
            return this.iField;
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected long getMillis() {
            return this.iInstant.getLocalMillis();
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected Chronology getChronology() {
            return this.iInstant.getChronology();
        }
    }
}
