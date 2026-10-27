package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

/* JADX INFO: loaded from: classes6.dex */
final class GJDayOfWeekDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumValue() {
        return 7;
    }

    @Override // org.joda.time.field.PreciseDurationDateTimeField, org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMinimumValue() {
        return 1;
    }

    GJDayOfWeekDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.dayOfWeek(), durationField);
        this.iChronology = basicChronology;
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int get(long j) {
        return this.iChronology.getDayOfWeek(j);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public String getAsText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToText(i);
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public String getAsShortText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToShortText(i);
    }

    @Override // org.joda.time.field.BaseDateTimeField
    protected int convertText(String str, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekTextToValue(str);
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getRangeDurationField() {
        return this.iChronology.weeks();
    }

    @Override // org.joda.time.field.BaseDateTimeField, org.joda.time.DateTimeField
    public int getMaximumTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxTextLength();
    }
}
