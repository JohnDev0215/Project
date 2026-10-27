package org.joda.time;

/* JADX INFO: loaded from: classes6.dex */
public interface ReadablePeriod {
    int get(DurationFieldType durationFieldType);

    DurationFieldType getFieldType(int i);

    PeriodType getPeriodType();

    int getValue(int i);

    int size();

    Period toPeriod();
}
