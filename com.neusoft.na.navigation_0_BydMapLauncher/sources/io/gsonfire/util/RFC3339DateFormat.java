package io.gsonfire.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
public final class RFC3339DateFormat extends DateFormat {
    private final SimpleDateFormat rfc3339Formatter;
    private final SimpleDateFormat rfc3339Parser;
    private final boolean serializeTime;
    private static final Pattern TIMEZONE_PATTERN = Pattern.compile("(.*)([+-][0-9][0-9])\\:?([0-9][0-9])$");
    private static final Pattern MILLISECONDS_PATTERN = Pattern.compile("(.*)\\.([0-9]+)(.*)");
    private static final Pattern DATE_ONLY_PATTERN = Pattern.compile("^[0-9]{1,4}-[0-1][0-9]-[0-3][0-9]$");

    public RFC3339DateFormat(TimeZone timeZone, boolean z) {
        this.rfc3339Parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (z) {
            this.rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        } else {
            this.rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        this.serializeTime = z;
        this.rfc3339Formatter.setTimeZone(timeZone);
    }

    public RFC3339DateFormat(TimeZone timeZone) {
        this(timeZone, true);
    }

    public RFC3339DateFormat(boolean z) {
        this(TimeZone.getTimeZone("UTC"), z);
    }

    public RFC3339DateFormat() {
        this(true);
    }

    private String generateTimezone(long j, TimeZone timeZone) {
        if (timeZone.getOffset(j) == 0) {
            return "Z";
        }
        int offset = (int) (((long) timeZone.getOffset(j)) / 1000);
        int i = offset / 3600;
        return (i >= 0 ? Marker.ANY_NON_NULL_MARKER : "-") + String.format("%02d", Integer.valueOf(Math.abs(i))) + ":" + String.format("%02d", Integer.valueOf(Math.abs((offset - (i * 3600)) / 60)));
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.rfc3339Formatter.format(date).toString());
        if (this.serializeTime) {
            long time = date.getTime();
            long j = time % 1000;
            if (j != 0) {
                stringBuffer2.append("." + Long.toString(j));
            }
            stringBuffer2.append(generateTimezone(time, this.rfc3339Formatter.getTimeZone()));
        }
        return stringBuffer2;
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        long j;
        String str2;
        if (DATE_ONLY_PATTERN.matcher(str).matches()) {
            str = str + "T00:00:00-0000";
        } else if (str.charAt(10) == 't') {
            str = str.substring(0, 10) + "T" + str.substring(12);
        }
        if (str.contains(".")) {
            Matcher matcher = MILLISECONDS_PATTERN.matcher(str);
            j = Long.parseLong(matcher.replaceAll("$2"));
            str = matcher.replaceAll("$1") + matcher.replaceAll("$3");
        } else {
            j = 0;
        }
        if (str.endsWith("Z") || str.endsWith("z")) {
            str2 = str.substring(0, str.length() - 1) + "-0000";
        } else {
            Matcher matcher2 = TIMEZONE_PATTERN.matcher(str);
            if (matcher2.matches()) {
                str2 = matcher2.replaceAll("$1") + matcher2.replaceAll("$2") + matcher2.replaceAll("$3");
            } else {
                str2 = str + "-0000";
            }
        }
        try {
            Date date = this.rfc3339Parser.parse(str2);
            if (j > 0) {
                date = new Date(date.getTime() + j);
            }
            parsePosition.setIndex(str2.length());
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
