package com.fasterxml.jackson.datatype.joda.cfg;

import java.util.Arrays;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

/* JADX INFO: loaded from: classes6.dex */
public class FormatConfig {

    @Deprecated
    public static final JacksonJodaDateFormat DEFAULT_DATETIME_FORMAT;
    public static final JacksonJodaDateFormat DEFAULT_DATETIME_PRINTER;
    public static final JacksonJodaDateFormat DEFAULT_LOCAL_DATEONLY_FORMAT;
    public static final JacksonJodaDateFormat DEFAULT_LOCAL_DATETIME_PARSER;
    public static final JacksonJodaDateFormat DEFAULT_LOCAL_DATETIME_PRINTER;
    public static final JacksonJodaDateFormat DEFAULT_LOCAL_TIMEONLY_PARSER;
    public static final JacksonJodaDateFormat DEFAULT_LOCAL_TIMEONLY_PRINTER;
    public static final JacksonJodaDateFormat DEFAULT_MONTH_DAY_FORMAT;
    public static final JacksonJodaPeriodFormat DEFAULT_PERIOD_FORMAT;
    public static final JacksonJodaDateFormat DEFAULT_YEAR_MONTH_FORMAT;
    private static final DateTimeZone DEFAULT_TZ = DateTimeZone.getDefault();
    public static final JacksonJodaDateFormat DEFAULT_DATEONLY_FORMAT = createUTC(ISODateTimeFormat.date());
    public static final JacksonJodaDateFormat DEFAULT_TIMEONLY_FORMAT = createUTC(ISODateTimeFormat.time());
    public static final JacksonJodaDateFormat DEFAULT_DATETIME_PARSER = createUTC(ISODateTimeFormat.dateTimeParser());

    static {
        JacksonJodaDateFormat jacksonJodaDateFormatCreateUTC = createUTC(ISODateTimeFormat.dateTime());
        DEFAULT_DATETIME_PRINTER = jacksonJodaDateFormatCreateUTC;
        DEFAULT_DATETIME_FORMAT = jacksonJodaDateFormatCreateUTC;
        DEFAULT_LOCAL_DATEONLY_FORMAT = createDefaultTZ(ISODateTimeFormat.date());
        DEFAULT_LOCAL_TIMEONLY_PRINTER = createDefaultTZ(ISODateTimeFormat.time());
        DEFAULT_LOCAL_TIMEONLY_PARSER = createDefaultTZ(ISODateTimeFormat.localTimeParser());
        DEFAULT_LOCAL_DATETIME_PRINTER = createDefaultTZ(ISODateTimeFormat.dateTime());
        DEFAULT_LOCAL_DATETIME_PARSER = createDefaultTZ(ISODateTimeFormat.localDateOptionalTimeParser());
        DEFAULT_PERIOD_FORMAT = new JacksonJodaPeriodFormat(ISOPeriodFormat.standard());
        DEFAULT_YEAR_MONTH_FORMAT = new JacksonJodaDateFormat(ISODateTimeFormat.yearMonth());
        DEFAULT_MONTH_DAY_FORMAT = createMonthDayFormat();
    }

    private static final JacksonJodaDateFormat createUTC(DateTimeFormatter dateTimeFormatter) {
        return new JacksonJodaDateFormat(dateTimeFormatter.withZoneUTC());
    }

    private static final JacksonJodaDateFormat createDefaultTZ(DateTimeFormatter dateTimeFormatter) {
        return new JacksonJodaDateFormat(dateTimeFormatter.withZone(DEFAULT_TZ));
    }

    private static final JacksonJodaDateFormat createMonthDayFormat() {
        return new JacksonJodaDateFormat(ISODateTimeFormat.forFields(Arrays.asList(DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()), true, true));
    }
}
