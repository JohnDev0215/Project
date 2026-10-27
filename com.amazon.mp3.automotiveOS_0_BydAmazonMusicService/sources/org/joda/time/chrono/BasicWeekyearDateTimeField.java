package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

/* JADX INFO: loaded from: classes6.dex */
final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField {
    private final BasicChronology iChronology;

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public boolean isLenient() {
        return false;
    }

    BasicWeekyearDateTimeField(BasicChronology basicChronology) {
        super(DateTimeFieldType.weekyear(), basicChronology.getAverageMillisPerYear());
        this.iChronology = basicChronology;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return this.iChronology.getWeekyear(j);
    }

    @Override // org.joda.time.field.ImpreciseDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long add(long j, int i) {
        return i == 0 ? j : set(j, get(j) + i);
    }

    @Override // org.joda.time.field.ImpreciseDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long add(long j, long j2) {
        return add(j, FieldUtils.safeToInt(j2));
    }

    @Override // org.joda.time.field.ImpreciseDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long getDifferenceAsLong(long j, long j2) {
        if (j < j2) {
            return -getDifference(j2, j);
        }
        int i = get(j);
        int i2 = get(j2);
        long jRemainder = remainder(j);
        long jRemainder2 = remainder(j2);
        if (jRemainder2 >= 31449600000L && this.iChronology.getWeeksInYear(i) <= 52) {
            jRemainder2 -= 604800000;
        }
        int i3 = i - i2;
        if (jRemainder < jRemainder2) {
            i3--;
        }
        return i3;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long set(long j, int i) {
        FieldUtils.verifyValueBounds(this, Math.abs(i), this.iChronology.getMinYear(), this.iChronology.getMaxYear());
        int i2 = get(j);
        if (i2 == i) {
            return j;
        }
        int dayOfWeek = this.iChronology.getDayOfWeek(j);
        int weeksInYear = this.iChronology.getWeeksInYear(i2);
        int weeksInYear2 = this.iChronology.getWeeksInYear(i);
        if (weeksInYear2 < weeksInYear) {
            weeksInYear = weeksInYear2;
        }
        int weekOfWeekyear = this.iChronology.getWeekOfWeekyear(j);
        if (weekOfWeekyear <= weeksInYear) {
            weeksInYear = weekOfWeekyear;
        }
        long year = this.iChronology.setYear(j, i);
        int i3 = get(year);
        if (i3 < i) {
            year += 604800000;
        } else if (i3 > i) {
            year -= 604800000;
        }
        return this.iChronology.dayOfWeek().set(year + (((long) (weeksInYear - this.iChronology.getWeekOfWeekyear(year))) * 604800000), dayOfWeek);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public boolean isLeap(long j) {
        BasicChronology basicChronology = this.iChronology;
        return basicChronology.getWeeksInYear(basicChronology.getWeekyear(j)) > 52;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getLeapAmount(long j) {
        BasicChronology basicChronology = this.iChronology;
        return basicChronology.getWeeksInYear(basicChronology.getWeekyear(j)) - 52;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public DurationField getLeapDurationField() {
        return this.iChronology.weeks();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMinimumValue() {
        return this.iChronology.getMinYear();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return this.iChronology.getMaxYear();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long roundFloor(long j) {
        long jRoundFloor = this.iChronology.weekOfWeekyear().roundFloor(j);
        int weekOfWeekyear = this.iChronology.getWeekOfWeekyear(jRoundFloor);
        return weekOfWeekyear > 1 ? jRoundFloor - (((long) (weekOfWeekyear - 1)) * 604800000) : jRoundFloor;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public long remainder(long j) {
        return j - roundFloor(j);
    }
}
