package org.joda.time;

import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public final class DateMidnight extends BaseDateTime {
    public DateMidnight() {
    }

    public DateMidnight(long j) {
        super(j);
    }

    public DateMidnight(int i, int i2, int i3, DateTimeZone dateTimeZone) {
        super(i, i2, i3, 0, 0, 0, 0, dateTimeZone);
    }

    public DateMidnight(int i, int i2, int i3, Chronology chronology) {
        super(i, i2, i3, 0, 0, 0, 0, chronology);
    }

    @Override // org.joda.time.base.BaseDateTime
    protected long checkInstant(long j, Chronology chronology) {
        return chronology.dayOfMonth().roundFloor(j);
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

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private DateTimeField iField;
        private DateMidnight iInstant;

        Property(DateMidnight dateMidnight, DateTimeField dateTimeField) {
            this.iInstant = dateMidnight;
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
    }
}
