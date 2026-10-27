package org.joda.time;

import java.util.ArrayList;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public final class MonthDay extends BasePartial {
    private static final DateTimeFieldType[] FIELD_TYPES = {DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()};
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendOptional(ISODateTimeFormat.localDateParser().getParser()).appendOptional(DateTimeFormat.forPattern("--MM-dd").getParser()).toFormatter();

    @Override // org.joda.time.ReadablePartial
    public int size() {
        return 2;
    }

    public static MonthDay parse(String str, DateTimeFormatter dateTimeFormatter) {
        LocalDate localDate = dateTimeFormatter.parseLocalDate(str);
        return new MonthDay(localDate.getMonthOfYear(), localDate.getDayOfMonth());
    }

    public MonthDay() {
    }

    public MonthDay(int i, int i2) {
        this(i, i2, null);
    }

    public MonthDay(int i, int i2, Chronology chronology) {
        super(new int[]{i, i2}, chronology);
    }

    @Override // org.joda.time.base.AbstractPartial
    protected DateTimeField getField(int i, Chronology chronology) {
        if (i == 0) {
            return chronology.monthOfYear();
        }
        if (i == 1) {
            return chronology.dayOfMonth();
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override // org.joda.time.base.AbstractPartial, org.joda.time.ReadablePartial
    public DateTimeFieldType getFieldType(int i) {
        return FIELD_TYPES[i];
    }

    public int getMonthOfYear() {
        return getValue(0);
    }

    public int getDayOfMonth() {
        return getValue(1);
    }

    @ToString
    public String toString() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DateTimeFieldType.monthOfYear());
        arrayList.add(DateTimeFieldType.dayOfMonth());
        return ISODateTimeFormat.forFields(arrayList, true, true).print(this);
    }
}
