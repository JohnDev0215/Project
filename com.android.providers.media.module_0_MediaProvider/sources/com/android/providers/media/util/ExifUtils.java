package com.android.providers.media.util;

import android.media.ExifInterface;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ExifUtils {
    private static final SimpleDateFormat sFormatter;
    private static final SimpleDateFormat sFormatterTz;
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss XXX");
        sFormatterTz = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static long getDateTime(ExifInterface exifInterface) {
        return parseDateTime(exifInterface.getAttribute("DateTime"), exifInterface.getAttribute("SubSecTime"), exifInterface.getAttribute("OffsetTime"));
    }

    public static long getDateTimeDigitized(ExifInterface exifInterface) {
        return parseDateTime(exifInterface.getAttribute("DateTimeDigitized"), exifInterface.getAttribute("SubSecTimeDigitized"), exifInterface.getAttribute("OffsetTimeDigitized"));
    }

    public static long getDateTimeOriginal(ExifInterface exifInterface) {
        return parseDateTime(exifInterface.getAttribute("DateTimeOriginal"), exifInterface.getAttribute("SubSecTimeOriginal"), exifInterface.getAttribute("OffsetTimeOriginal"));
    }

    public static long getGpsDateTime(ExifInterface exifInterface) {
        Date date;
        String attribute = exifInterface.getAttribute("GPSDateStamp");
        String attribute2 = exifInterface.getAttribute("GPSTimeStamp");
        if (attribute != null && attribute2 != null && (sNonZeroTimePattern.matcher(attribute).matches() || sNonZeroTimePattern.matcher(attribute2).matches())) {
            String str = attribute + ' ' + attribute2;
            ParsePosition parsePosition = new ParsePosition(0);
            try {
                synchronized (sFormatter) {
                    date = sFormatter.parse(str, parsePosition);
                }
                if (date == null) {
                    return -1L;
                }
                return date.getTime();
            } catch (IllegalArgumentException unused) {
            }
        }
        return -1L;
    }

    private static long parseDateTime(String str, String str2, String str3) {
        Date date;
        Date date2;
        if (str != null && sNonZeroTimePattern.matcher(str).matches()) {
            ParsePosition parsePosition = new ParsePosition(0);
            try {
                synchronized (sFormatter) {
                    date = sFormatter.parse(str, parsePosition);
                }
                if (str3 != null) {
                    String str4 = str + " " + str3;
                    ParsePosition parsePosition2 = new ParsePosition(0);
                    synchronized (sFormatterTz) {
                        date2 = sFormatterTz.parse(str4, parsePosition2);
                    }
                    date = date2;
                }
                if (date == null) {
                    return -1L;
                }
                long time = date.getTime();
                if (str2 == null) {
                    return time;
                }
                try {
                    long j = Long.parseLong(str2);
                    while (j > 1000) {
                        j /= 10;
                    }
                    return time + j;
                } catch (NumberFormatException unused) {
                    return time;
                }
            } catch (IllegalArgumentException unused2) {
            }
        }
        return -1L;
    }
}
