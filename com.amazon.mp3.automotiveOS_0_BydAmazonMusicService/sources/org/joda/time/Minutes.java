package org.joda.time;

import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/* JADX INFO: loaded from: classes6.dex */
public final class Minutes extends BaseSingleFieldPeriod {
    public static final Minutes ZERO = new Minutes(0);
    public static final Minutes ONE = new Minutes(1);
    public static final Minutes TWO = new Minutes(2);
    public static final Minutes THREE = new Minutes(3);
    public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
    public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());

    public static Minutes minutes(int i) {
        if (i == Integer.MIN_VALUE) {
            return MIN_VALUE;
        }
        if (i == Integer.MAX_VALUE) {
            return MAX_VALUE;
        }
        if (i == 0) {
            return ZERO;
        }
        if (i == 1) {
            return ONE;
        }
        if (i == 2) {
            return TWO;
        }
        if (i == 3) {
            return THREE;
        }
        return new Minutes(i);
    }

    private Minutes(int i) {
        super(i);
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod
    public DurationFieldType getFieldType() {
        return DurationFieldType.minutes();
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod, org.joda.time.ReadablePeriod
    public PeriodType getPeriodType() {
        return PeriodType.minutes();
    }

    public int getMinutes() {
        return getValue();
    }

    @ToString
    public String toString() {
        return "PT" + String.valueOf(getValue()) + "M";
    }
}
