package com.amazon.music.arcus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ArcusUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ArcusUtils.class.getSimpleName());
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public static Date toDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return DATE_FORMATTER.parse(str);
        } catch (ParseException unused) {
            LOG.error("Date string failed to parse: " + str);
            return null;
        }
    }

    public static String toString(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static Date getMoreRecentDate(Date date, Date date2) {
        return date == null ? date2 : (date2 != null && date.compareTo(date2) <= 0) ? date2 : date;
    }
}
