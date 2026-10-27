package com.amazon.podcast.views;

import android.content.res.Resources;
import android.text.Html;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$string;
import com.amazonaws.util.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class Strings {
    private static final Logger logger = LoggerFactory.getLogger("Strings");

    public static String checkNullString(final String original) {
        return original == null ? "" : original;
    }

    public static String getDurationString(final Resources resources, final long durationInMilliseconds) {
        String string;
        String string2;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(durationInMilliseconds);
        long millis = durationInMilliseconds - TimeUnit.HOURS.toMillis(hours);
        long minutes = timeUnit.toMinutes(millis);
        long seconds = timeUnit.toSeconds(millis - TimeUnit.MINUTES.toMillis(minutes));
        if (hours <= 0 && minutes <= 0) {
            if (seconds == 1) {
                return resources.getString(R$string.podcast_one_second);
            }
            return String.format(resources.getString(R$string.podcast_number_of_seconds), Long.toString(seconds));
        }
        if (hours == 1) {
            string = resources.getString(R$string.podcast_one_hour);
        } else {
            string = String.format(resources.getString(R$string.podcast_number_of_hours), Long.toString(hours));
        }
        if (minutes == 1) {
            string2 = resources.getString(R$string.podcast_one_minute);
        } else {
            string2 = String.format(resources.getString(R$string.podcast_number_of_minutes), Long.toString(minutes));
        }
        if (hours == 0) {
            return string2;
        }
        if (minutes == 0) {
            return string;
        }
        return string + " " + string2;
    }

    public static String getRemainingDurationString(final Resources resources, final long durationInMilliseconds) {
        String string;
        String string2;
        String string3;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(durationInMilliseconds);
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(durationInMilliseconds - timeUnit2.toMillis(hours));
        long millis = durationInMilliseconds - timeUnit2.toMillis(hours);
        TimeUnit.MINUTES.toMillis(minutes);
        long seconds = timeUnit.toSeconds(millis);
        if (hours <= 0 && minutes <= 0) {
            if (seconds == 1) {
                string3 = resources.getString(R$string.podcast_one_second);
            } else {
                string3 = String.format(resources.getString(R$string.podcast_number_of_seconds), Long.toString(seconds));
            }
            return String.format(resources.getString(R$string.podcast_time_remaining), string3);
        }
        if (hours == 1) {
            string = resources.getString(R$string.podcast_one_hour);
        } else {
            string = String.format(resources.getString(R$string.podcast_number_of_hours), Long.toString(hours));
        }
        if (minutes == 1) {
            string2 = resources.getString(R$string.podcast_one_minute);
        } else {
            string2 = String.format(resources.getString(R$string.podcast_number_of_minutes), Long.toString(minutes));
        }
        if (hours == 0) {
            return String.format(resources.getString(R$string.podcast_time_remaining), string2);
        }
        if (minutes == 0) {
            return String.format(resources.getString(R$string.podcast_time_remaining), string);
        }
        return String.format(resources.getString(R$string.podcast_time_remaining), string + " " + string2);
    }

    public static String addBulletForString(final String str) {
        return "  •  " + str;
    }

    public static List<String> convertStringToList(final String string) throws IOException {
        return string.isEmpty() ? new ArrayList() : (List) new ObjectMapper().readValue(string, new TypeReference<List<String>>() { // from class: com.amazon.podcast.views.Strings.1
        });
    }

    public static String convertListToString(final List<String> list) throws IOException {
        return list == null ? "" : new ObjectMapper().writeValueAsString(list);
    }

    public static String checkNullString(final Integer original) {
        return original == null ? "" : String.valueOf(original);
    }

    public static String convertUUIDToString(final UUID original) {
        return original == null ? "" : original.toString();
    }

    public static String getTextWithTotalDuration(final Resources resources, final Long duration, final TextView textView) {
        if (StringUtils.isBlank(textView.getText())) {
            return getDurationString(resources, duration.longValue());
        }
        return addBulletForString(getDurationString(resources, duration.longValue()));
    }

    public static String getTextWithRemainingDuration(final Resources resources, final Long leftDuration, final TextView textView) {
        if (StringUtils.isBlank(textView.getText())) {
            return getRemainingDurationString(resources, leftDuration.longValue());
        }
        return addBulletForString(getRemainingDurationString(resources, leftDuration.longValue()));
    }

    public static Spannable convertToSpannable(final String text, final AppCompatTextView textView) {
        Spannable spannableNewSpannable = Spannable.Factory.getInstance().newSpannable(Html.fromHtml(text, 0, new PicassoImageGetter(textView), null));
        for (URLSpan uRLSpan : (URLSpan[]) spannableNewSpannable.getSpans(0, spannableNewSpannable.length(), URLSpan.class)) {
            int spanStart = spannableNewSpannable.getSpanStart(uRLSpan);
            int spanEnd = spannableNewSpannable.getSpanEnd(uRLSpan);
            int spanFlags = spannableNewSpannable.getSpanFlags(uRLSpan);
            spannableNewSpannable.removeSpan(uRLSpan);
            spannableNewSpannable.setSpan(new URLSpanNoUnderline(uRLSpan.getURL(), textView.getResources()), spanStart, spanEnd, spanFlags);
            textView.setText(spannableNewSpannable);
        }
        return spannableNewSpannable;
    }

    public static final String getReadablePublishDate(String date, final Resources resources) {
        long jConvert;
        try {
            if (isDateInISO8601DateFormat(date)) {
                date = convertDateToPattern(date);
            }
            jConvert = TimeUnit.DAYS.convert(getTodayDate().getTime() - new SimpleDateFormat(Character.isAlphabetic(date.charAt(0)) ? "MMM d yyyy" : "dd-MM-yyyy").parse(date).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("Error in formatting publish date", (Throwable) e);
        }
        if (jConvert == 0) {
            return resources.getString(R$string.podcast_today);
        }
        if (jConvert == 1) {
            return resources.getString(R$string.podcast_yesterday);
        }
        if (jConvert > 1 && jConvert < 7) {
            return String.format(resources.getString(R$string.podcast_xx_days_ago), Long.toString(jConvert));
        }
        if (jConvert == 7) {
            return resources.getString(R$string.podcast_a_week_ago);
        }
        return date;
    }

    public static final Date convertStringToDate(final String publishDate) throws Exception {
        return new SimpleDateFormat(Character.isAlphabetic(publishDate.charAt(0)) ? "MMM d yyyy" : "dd-MM-yyyy").parse(publishDate);
    }

    public static String getISOFormatString(final String date) {
        if (date == null) {
            return null;
        }
        try {
            Locale locale = Locale.ENGLISH;
            return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, locale).format(new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", locale).parse(date));
        } catch (ParseException e) {
            logger.error("Error in parsing publish date sent by Tenzing ", (Throwable) e);
            return null;
        }
    }

    public static final String convertDateToPattern(final String date) throws ParseException {
        return new SimpleDateFormat(Podcast.getDeviceInfoProvider().getLocales().get(0).contains("US") ? "MMM d yyyy" : "dd-MM-yyyy").format(new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).parse(date));
    }

    private static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar.getTime();
    }

    private static boolean isDateInISO8601DateFormat(final String date) {
        try {
            new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).parse(date);
            return true;
        } catch (ParseException e) {
            logger.warn("Error in parsing publish date send by Brush: ", (Throwable) e);
            return false;
        }
    }
}
