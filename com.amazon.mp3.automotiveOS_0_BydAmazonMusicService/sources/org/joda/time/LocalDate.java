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
public final class LocalDate extends BaseLocal implements Serializable {
    private static final Set<DurationFieldType> DATE_DURATION_TYPES;
    private final Chronology iChronology;
    private transient int iHash;
    private final long iLocalMillis;

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return 3;
    }

    static {
        HashSet hashSet = new HashSet();
        DATE_DURATION_TYPES = hashSet;
        hashSet.add(DurationFieldType.days());
        hashSet.add(DurationFieldType.weeks());
        hashSet.add(DurationFieldType.months());
        hashSet.add(DurationFieldType.weekyears());
        hashSet.add(DurationFieldType.years());
        hashSet.add(DurationFieldType.centuries());
        hashSet.add(DurationFieldType.eras());
    }

    public LocalDate() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalDate(long j, DateTimeZone dateTimeZone) {
        this(j, ISOChronology.getInstance(dateTimeZone));
    }

    public LocalDate(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        long millisKeepLocal = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        Chronology chronologyWithUTC = chronology2.withUTC();
        this.iLocalMillis = chronologyWithUTC.dayOfMonth().roundFloor(millisKeepLocal);
        this.iChronology = chronologyWithUTC;
    }

    public LocalDate(int i, int i2, int i3) {
        this(i, i2, i3, ISOChronology.getInstanceUTC());
    }

    public LocalDate(int i, int i2, int i3, Chronology chronology) {
        Chronology chronologyWithUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = chronologyWithUTC.getDateTimeMillis(i, i2, i3, 0);
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
        if (dateTimeFieldType == null) {
            return false;
        }
        DurationFieldType durationType = dateTimeFieldType.getDurationType();
        if (DATE_DURATION_TYPES.contains(durationType) || durationType.getField(getChronology()).getUnitMillis() >= getChronology().days().getUnitMillis()) {
            return dateTimeFieldType.getField(getChronology()).isSupported();
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
        if (obj instanceof LocalDate) {
            LocalDate localDate = (LocalDate) obj;
            if (this.iChronology.equals(localDate.iChronology)) {
                return this.iLocalMillis == localDate.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    @Override // org.joda.time.base.AbstractPartial
    public int hashCode() {
        int i = this.iHash;
        if (i != 0) {
            return i;
        }
        int iHashCode = super.hashCode();
        this.iHash = iHashCode;
        return iHashCode;
    }

    @Override // org.joda.time.base.AbstractPartial, java.lang.Comparable
    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalDate) {
            LocalDate localDate = (LocalDate) readablePartial;
            if (this.iChronology.equals(localDate.iChronology)) {
                long j = this.iLocalMillis;
                long j2 = localDate.iLocalMillis;
                if (j < j2) {
                    return -1;
                }
                return j == j2 ? 0 : 1;
            }
        }
        return super.compareTo(readablePartial);
    }

    @Deprecated
    public DateMidnight toDateMidnight() {
        return toDateMidnight(null);
    }

    @Deprecated
    public DateMidnight toDateMidnight(DateTimeZone dateTimeZone) {
        return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), getChronology().withZone(DateTimeUtils.getZone(dateTimeZone)));
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

    public Property year() {
        return new Property(this, getChronology().year());
    }

    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }

    @ToString
    public String toString() {
        return ISODateTimeFormat.date().print(this);
    }

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private transient DateTimeField iField;
        private transient LocalDate iInstant;

        Property(LocalDate localDate, DateTimeField dateTimeField) {
            this.iInstant = localDate;
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
