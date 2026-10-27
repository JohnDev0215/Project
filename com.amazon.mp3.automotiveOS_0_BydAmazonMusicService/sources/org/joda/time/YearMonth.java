package org.joda.time;

import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public final class YearMonth extends BasePartial {
    private static final DateTimeFieldType[] FIELD_TYPES = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear()};

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return 2;
    }

    public static YearMonth parse(String str, DateTimeFormatter dateTimeFormatter) {
        LocalDate localDate = dateTimeFormatter.parseLocalDate(str);
        return new YearMonth(localDate.getYear(), localDate.getMonthOfYear());
    }

    public YearMonth() {
    }

    public YearMonth(int i, int i2) {
        this(i, i2, null);
    }

    public YearMonth(int i, int i2, Chronology chronology) {
        super(new int[]{i, i2}, chronology);
    }

    @Override // org.joda.time.base.AbstractPartial
    protected DateTimeField getField(int i, Chronology chronology) {
        if (i == 0) {
            return chronology.year();
        }
        if (i == 1) {
            return chronology.monthOfYear();
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public DateTimeFieldType getFieldType(int i) {
        return FIELD_TYPES[i];
    }

    public int getYear() {
        return getValue(0);
    }

    public int getMonthOfYear() {
        return getValue(1);
    }

    @ToString
    public String toString() {
        return ISODateTimeFormat.yearMonth().print(this);
    }
}
