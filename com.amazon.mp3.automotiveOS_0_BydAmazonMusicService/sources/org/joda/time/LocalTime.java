package org.joda.time;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.joda.convert.ToString;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public final class LocalTime extends BaseLocal implements Serializable {
    public static final LocalTime MIDNIGHT = new LocalTime(0, 0, 0, 0);
    private static final Set<DurationFieldType> TIME_DURATION_TYPES;
    private final Chronology iChronology;
    private final long iLocalMillis;

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return 4;
    }

    static {
        HashSet hashSet = new HashSet();
        TIME_DURATION_TYPES = hashSet;
        hashSet.add(DurationFieldType.millis());
        hashSet.add(DurationFieldType.seconds());
        hashSet.add(DurationFieldType.minutes());
        hashSet.add(DurationFieldType.hours());
    }

    public LocalTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalTime(long j) {
        this(j, ISOChronology.getInstance());
    }

    public LocalTime(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        long millisKeepLocal = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        Chronology chronologyWithUTC = chronology2.withUTC();
        this.iLocalMillis = chronologyWithUTC.millisOfDay().get(millisKeepLocal);
        this.iChronology = chronologyWithUTC;
    }

    public LocalTime(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, ISOChronology.getInstanceUTC());
    }

    public LocalTime(int i, int i2, int i3, int i4, Chronology chronology) {
        Chronology chronologyWithUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = chronologyWithUTC.getDateTimeMillis(0L, i, i2, i3, i4);
        this.iChronology = chronologyWithUTC;
        this.iLocalMillis = dateTimeMillis;
    }

    @Override // org.joda.time.base.AbstractPartial
    protected DateTimeField getField(int i, Chronology chronology) {
        if (i == 0) {
            return chronology.hourOfDay();
        }
        if (i == 1) {
            return chronology.minuteOfHour();
        }
        if (i == 2) {
            return chronology.secondOfMinute();
        }
        if (i == 3) {
            return chronology.millisOfSecond();
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.ReadablePartial
    public int getValue(int i) {
        if (i == 0) {
            return getChronology().hourOfDay().get(getLocalMillis());
        }
        if (i == 1) {
            return getChronology().minuteOfHour().get(getLocalMillis());
        }
        if (i == 2) {
            return getChronology().secondOfMinute().get(getLocalMillis());
        }
        if (i == 3) {
            return getChronology().millisOfSecond().get(getLocalMillis());
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public int get(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        if (!isSupported(dateTimeFieldType)) {
            throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
        }
        return dateTimeFieldType.getField(getChronology()).get(getLocalMillis());
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null || !isSupported(dateTimeFieldType.getDurationType())) {
            return false;
        }
        DurationFieldType rangeDurationType = dateTimeFieldType.getRangeDurationType();
        return isSupported(rangeDurationType) || rangeDurationType == DurationFieldType.days();
    }

    public boolean isSupported(DurationFieldType durationFieldType) {
        if (durationFieldType == null) {
            return false;
        }
        DurationField field = durationFieldType.getField(getChronology());
        if (TIME_DURATION_TYPES.contains(durationFieldType) || field.getUnitMillis() < getChronology().days().getUnitMillis()) {
            return field.isSupported();
        }
        return false;
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
        if (obj instanceof LocalTime) {
            LocalTime localTime = (LocalTime) obj;
            if (this.iChronology.equals(localTime.iChronology)) {
                return this.iLocalMillis == localTime.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    @Override // org.joda.time.base.AbstractPartial
    public int hashCode() {
        return ((((((((((((((3611 + this.iChronology.hourOfDay().get(this.iLocalMillis)) * 23) + this.iChronology.hourOfDay().getType().hashCode()) * 23) + this.iChronology.minuteOfHour().get(this.iLocalMillis)) * 23) + this.iChronology.minuteOfHour().getType().hashCode()) * 23) + this.iChronology.secondOfMinute().get(this.iLocalMillis)) * 23) + this.iChronology.secondOfMinute().getType().hashCode()) * 23) + this.iChronology.millisOfSecond().get(this.iLocalMillis)) * 23) + this.iChronology.millisOfSecond().getType().hashCode() + getChronology().hashCode();
    }

    @Override // org.joda.time.base.AbstractPartial, java.lang.Comparable
    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalTime) {
            LocalTime localTime = (LocalTime) readablePartial;
            if (this.iChronology.equals(localTime.iChronology)) {
                long j = this.iLocalMillis;
                long j2 = localTime.iLocalMillis;
                if (j < j2) {
                    return -1;
                }
                return j == j2 ? 0 : 1;
            }
        }
        return super.compareTo(readablePartial);
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
        return ISODateTimeFormat.time().print(this);
    }

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private transient DateTimeField iField;
        private transient LocalTime iInstant;

        Property(LocalTime localTime, DateTimeField dateTimeField) {
            this.iInstant = localTime;
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
