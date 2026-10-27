package android.text.format;

import android.app.blob.XmlTags;
import android.content.Context;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import com.android.internal.content.NativeLibraryHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import libcore.icu.ICU;
import libcore.icu.LocaleData;

/* JADX INFO: loaded from: classes3.dex */
public class DateFormat {

    @Deprecated
    public static final char AM_PM = 'a';

    @Deprecated
    public static final char CAPITAL_AM_PM = 'A';

    @Deprecated
    public static final char DATE = 'd';

    @Deprecated
    public static final char DAY = 'E';

    @Deprecated
    public static final char HOUR = 'h';

    @Deprecated
    public static final char HOUR_OF_DAY = 'k';

    @Deprecated
    public static final char MINUTE = 'm';

    @Deprecated
    public static final char MONTH = 'M';

    @Deprecated
    public static final char QUOTE = '\'';

    @Deprecated
    public static final char SECONDS = 's';

    @Deprecated
    public static final char STANDALONE_MONTH = 'L';

    @Deprecated
    public static final char TIME_ZONE = 'z';

    @Deprecated
    public static final char YEAR = 'y';
    private static boolean sIs24Hour;
    private static Locale sIs24HourLocale;
    private static final Object sLocaleLock = new Object();

    public static boolean is24HourFormat(Context context) {
        return is24HourFormat(context, context.getUserId());
    }

    public static boolean is24HourFormat(Context context, int i) {
        String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "time_12_24", i);
        if (stringForUser != null) {
            return stringForUser.equals(TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR);
        }
        return is24HourLocale(context.getResources().getConfiguration().locale);
    }

    public static boolean is24HourLocale(Locale locale) {
        Object obj = sLocaleLock;
        synchronized (obj) {
            Locale locale2 = sIs24HourLocale;
            if (locale2 != null && locale2.equals(locale)) {
                return sIs24Hour;
            }
            java.text.DateFormat timeInstance = java.text.DateFormat.getTimeInstance(1, locale);
            boolean zHasDesignator = timeInstance instanceof SimpleDateFormat ? hasDesignator(((SimpleDateFormat) timeInstance).toPattern(), 'H') : false;
            synchronized (obj) {
                sIs24HourLocale = locale;
                sIs24Hour = zHasDesignator;
            }
            return zHasDesignator;
        }
    }

    public static String getBestDateTimePattern(Locale locale, String str) {
        return ICU.getBestDateTimePattern(str, locale);
    }

    public static java.text.DateFormat getTimeFormat(Context context) {
        return new SimpleDateFormat(getTimeFormatString(context), context.getResources().getConfiguration().locale);
    }

    public static String getTimeFormatString(Context context) {
        return getTimeFormatString(context, context.getUserId());
    }

    public static String getTimeFormatString(Context context, int i) {
        LocaleData localeData = LocaleData.get(context.getResources().getConfiguration().locale);
        return is24HourFormat(context, i) ? localeData.timeFormat_Hm : localeData.timeFormat_hm;
    }

    public static java.text.DateFormat getDateFormat(Context context) {
        return java.text.DateFormat.getDateInstance(3, context.getResources().getConfiguration().locale);
    }

    public static java.text.DateFormat getLongDateFormat(Context context) {
        return java.text.DateFormat.getDateInstance(1, context.getResources().getConfiguration().locale);
    }

    public static java.text.DateFormat getMediumDateFormat(Context context) {
        return java.text.DateFormat.getDateInstance(2, context.getResources().getConfiguration().locale);
    }

    public static char[] getDateFormatOrder(Context context) {
        return ICU.getDateFormatOrder(getDateFormatString(context));
    }

    private static String getDateFormatString(Context context) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(3, context.getResources().getConfiguration().locale);
        if (dateInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) dateInstance).toPattern();
        }
        throw new AssertionError("!(df instanceof SimpleDateFormat)");
    }

    public static CharSequence format(CharSequence charSequence, long j) {
        return format(charSequence, new Date(j));
    }

    public static CharSequence format(CharSequence charSequence, Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return format(charSequence, gregorianCalendar);
    }

    public static boolean hasSeconds(CharSequence charSequence) {
        return hasDesignator(charSequence, 's');
    }

    public static boolean hasDesignator(CharSequence charSequence, char c) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '\'') {
                z = !z;
            } else if (!z && cCharAt == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.CharSequence format(java.lang.CharSequence r12, java.util.Calendar r13) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.format.DateFormat.format(java.lang.CharSequence, java.util.Calendar):java.lang.CharSequence");
    }

    private static String getDayOfWeekString(LocaleData localeData, int i, int i2, int i3) {
        boolean z = i3 == 99;
        return i2 == 5 ? z ? localeData.tinyStandAloneWeekdayNames[i] : localeData.tinyWeekdayNames[i] : i2 == 4 ? z ? localeData.longStandAloneWeekdayNames[i] : localeData.longWeekdayNames[i] : z ? localeData.shortStandAloneWeekdayNames[i] : localeData.shortWeekdayNames[i];
    }

    private static String getMonthString(LocaleData localeData, int i, int i2, int i3) {
        boolean z = i3 == 76;
        if (i2 == 5) {
            return z ? localeData.tinyStandAloneMonthNames[i] : localeData.tinyMonthNames[i];
        }
        if (i2 == 4) {
            return z ? localeData.longStandAloneMonthNames[i] : localeData.longMonthNames[i];
        }
        if (i2 == 3) {
            return z ? localeData.shortStandAloneMonthNames[i] : localeData.shortMonthNames[i];
        }
        return zeroPad(i + 1, i2);
    }

    private static String getTimeZoneString(Calendar calendar, int i) {
        TimeZone timeZone = calendar.getTimeZone();
        if (i < 2) {
            return formatZoneOffset(calendar.get(16) + calendar.get(15), i);
        }
        return timeZone.getDisplayName(calendar.get(16) != 0, 0);
    }

    private static String formatZoneOffset(int i, int i2) {
        int i3 = i / 1000;
        StringBuilder sb = new StringBuilder();
        if (i3 < 0) {
            sb.insert(0, NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            i3 = -i3;
        } else {
            sb.insert(0, "+");
        }
        sb.append(zeroPad(i3 / 3600, 2));
        sb.append(zeroPad((i3 % 3600) / 60, 2));
        return sb.toString();
    }

    private static String getYearString(int i, int i2) {
        return i2 <= 2 ? zeroPad(i % 100, 2) : String.format(Locale.getDefault(), "%d", Integer.valueOf(i));
    }

    public static int appendQuotedText(SpannableStringBuilder spannableStringBuilder, int i) {
        int length = spannableStringBuilder.length();
        int i2 = i + 1;
        if (i2 < length && spannableStringBuilder.charAt(i2) == '\'') {
            spannableStringBuilder.delete(i, i2);
            return 1;
        }
        int i3 = 0;
        spannableStringBuilder.delete(i, i2);
        int i4 = length - 1;
        while (i < i4) {
            if (spannableStringBuilder.charAt(i) == '\'') {
                int i5 = i + 1;
                if (i5 < i4 && spannableStringBuilder.charAt(i5) == '\'') {
                    spannableStringBuilder.delete(i, i5);
                    i4--;
                    i3++;
                    i = i5;
                } else {
                    spannableStringBuilder.delete(i, i5);
                    break;
                }
            } else {
                i++;
                i3++;
            }
        }
        return i3;
    }

    private static String zeroPad(int i, int i2) {
        return String.format(Locale.getDefault(), "%0" + i2 + XmlTags.ATTR_DESCRIPTION, Integer.valueOf(i));
    }
}
