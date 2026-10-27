package org.joda.time;

import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/* JADX INFO: loaded from: classes6.dex */
public final class Weeks extends BaseSingleFieldPeriod {
    public static final Weeks ZERO = new Weeks(0);
    public static final Weeks ONE = new Weeks(1);
    public static final Weeks TWO = new Weeks(2);
    public static final Weeks THREE = new Weeks(3);
    public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
    public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());

    public static Weeks weeks(int i) {
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
        return new Weeks(i);
    }

    private Weeks(int i) {
        super(i);
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod
    public DurationFieldType getFieldType() {
        return DurationFieldType.weeks();
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod, org.joda.time.ReadablePeriod
    public PeriodType getPeriodType() {
        return PeriodType.weeks();
    }

    public int getWeeks() {
        return getValue();
    }

    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + "W";
    }
}
