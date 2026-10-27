package org.apache.tools.ant.util;

import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class DateUtils {
    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO8601_TIME_PATTERN = "HH:mm:ss";
    public static final DateFormat DATE_HEADER_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ", Locale.US);
    private static final MessageFormat MINUTE_SECONDS = new MessageFormat("{0}{1}");
    private static final double[] LIMITS = {0.0d, 1.0d, 2.0d};
    private static final String[] MINUTES_PART = {"", "1 minute ", "{0,number} minutes "};
    private static final String[] SECONDS_PART = {"0 seconds", "1 second", "{1,number} seconds"};
    private static final ChoiceFormat MINUTES_FORMAT = new ChoiceFormat(LIMITS, MINUTES_PART);
    private static final ChoiceFormat SECONDS_FORMAT = new ChoiceFormat(LIMITS, SECONDS_PART);

    static {
        MINUTE_SECONDS.setFormat(0, MINUTES_FORMAT);
        MINUTE_SECONDS.setFormat(1, SECONDS_FORMAT);
    }

    private DateUtils() {
    }

    public static String format(long date, String pattern) {
        return format(new Date(date), pattern);
    }

    public static String format(Date date, String pattern) {
        DateFormat df = createDateFormat(pattern);
        return df.format(date);
    }

    public static String formatElapsedTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        Object[] args = {new Long(minutes), new Long(seconds % 60)};
        return MINUTE_SECONDS.format(args);
    }

    private static DateFormat createDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        sdf.setTimeZone(gmt);
        sdf.setLenient(true);
        return sdf;
    }

    public static int getPhaseOfMoon(Calendar cal) {
        int dayOfTheYear = cal.get(6);
        int yearInMetonicCycle = ((cal.get(1) - 1900) % 19) + 1;
        int epact = ((yearInMetonicCycle * 11) + 18) % 30;
        if ((epact == 25 && yearInMetonicCycle > 11) || epact == 24) {
            epact++;
        }
        return (((((dayOfTheYear + epact) * 6) + 11) % 177) / 22) & 7;
    }

    public static String getDateForHeader() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        int offset = tz.getOffset(cal.get(0), cal.get(1), cal.get(2), cal.get(5), cal.get(7), cal.get(14));
        StringBuffer tzMarker = new StringBuffer(offset < 0 ? "-" : "+");
        int offset2 = Math.abs(offset);
        int hours = offset2 / 3600000;
        int minutes = (offset2 / 60000) - (hours * 60);
        if (hours < 10) {
            tzMarker.append("0");
        }
        tzMarker.append(hours);
        if (minutes < 10) {
            tzMarker.append("0");
        }
        tzMarker.append(minutes);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DATE_HEADER_FORMAT.format(cal.getTime()));
        stringBuffer.append(tzMarker.toString());
        return stringBuffer.toString();
    }

    public static Date parseIso8601DateTime(String datestr) throws ParseException {
        return new SimpleDateFormat(ISO8601_DATETIME_PATTERN).parse(datestr);
    }

    public static Date parseIso8601Date(String datestr) throws ParseException {
        return new SimpleDateFormat(ISO8601_DATE_PATTERN).parse(datestr);
    }

    public static Date parseIso8601DateTimeOrDate(String datestr) throws ParseException {
        try {
            return parseIso8601DateTime(datestr);
        } catch (ParseException e) {
            return parseIso8601Date(datestr);
        }
    }
}
