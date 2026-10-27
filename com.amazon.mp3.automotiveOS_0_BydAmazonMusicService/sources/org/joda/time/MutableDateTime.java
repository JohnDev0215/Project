package org.joda.time;

import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

/* JADX INFO: loaded from: classes6.dex */
public class MutableDateTime extends BaseDateTime implements ReadWritableDateTime, Cloneable {
    private DateTimeField iRoundingField;
    private int iRoundingMode;

    public MutableDateTime() {
    }

    public MutableDateTime(long j, DateTimeZone dateTimeZone) {
        super(j, dateTimeZone);
    }

    public int getRoundingMode() {
        return this.iRoundingMode;
    }

    @Override // org.joda.time.base.BaseDateTime
    public void setMillis(long j) {
        int i = this.iRoundingMode;
        if (i == 1) {
            j = this.iRoundingField.roundFloor(j);
        } else if (i == 2) {
            j = this.iRoundingField.roundCeiling(j);
        } else if (i == 3) {
            j = this.iRoundingField.roundHalfFloor(j);
        } else if (i == 4) {
            j = this.iRoundingField.roundHalfCeiling(j);
        } else if (i == 5) {
            j = this.iRoundingField.roundHalfEven(j);
        }
        super.setMillis(j);
    }

    @Override // org.joda.time.base.BaseDateTime
    public void setChronology(Chronology chronology) {
        super.setChronology(chronology);
    }

    public void setZoneRetainFields(DateTimeZone dateTimeZone) {
        DateTimeZone zone = DateTimeUtils.getZone(dateTimeZone);
        DateTimeZone zone2 = DateTimeUtils.getZone(getZone());
        if (zone == zone2) {
            return;
        }
        long millisKeepLocal = zone2.getMillisKeepLocal(zone, getMillis());
        setChronology(getChronology().withZone(zone));
        setMillis(millisKeepLocal);
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setYear(int i) {
        setMillis(getChronology().year().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setWeekyear(int i) {
        setMillis(getChronology().weekyear().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setMonthOfYear(int i) {
        setMillis(getChronology().monthOfYear().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setWeekOfWeekyear(int i) {
        setMillis(getChronology().weekOfWeekyear().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setDayOfYear(int i) {
        setMillis(getChronology().dayOfYear().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setDayOfMonth(int i) {
        setMillis(getChronology().dayOfMonth().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setDayOfWeek(int i) {
        setMillis(getChronology().dayOfWeek().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setHourOfDay(int i) {
        setMillis(getChronology().hourOfDay().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setMinuteOfDay(int i) {
        setMillis(getChronology().minuteOfDay().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setMinuteOfHour(int i) {
        setMillis(getChronology().minuteOfHour().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setSecondOfDay(int i) {
        setMillis(getChronology().secondOfDay().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setSecondOfMinute(int i) {
        setMillis(getChronology().secondOfMinute().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setMillisOfDay(int i) {
        setMillis(getChronology().millisOfDay().set(getMillis(), i));
    }

    @Override // org.joda.time.ReadWritableDateTime
    public void setMillisOfSecond(int i) {
        setMillis(getChronology().millisOfSecond().set(getMillis(), i));
    }

    public Property property(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        DateTimeField field = dateTimeFieldType.getField(getChronology());
        if (!field.isSupported()) {
            throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
        }
        return new Property(this, field);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError("Clone error");
        }
    }

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private DateTimeField iField;
        private MutableDateTime iInstant;

        Property(MutableDateTime mutableDateTime, DateTimeField dateTimeField) {
            this.iInstant = mutableDateTime;
            this.iField = dateTimeField;
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        public DateTimeField getField() {
            return this.iField;
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected long getMillis() {
            return this.iInstant.getMillis();
        }

        @Override // org.joda.time.field.AbstractReadableInstantFieldProperty
        protected Chronology getChronology() {
            return this.iInstant.getChronology();
        }

        public MutableDateTime set(int i) {
            this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), i));
            return this.iInstant;
        }
    }
}
