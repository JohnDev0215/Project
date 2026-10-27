package android.text.format;

import android.content.res.Resources;
import com.android.internal.R;
import com.android.internal.accessibility.common.ShortcutConstants;
import java.nio.CharBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import libcore.icu.LocaleData;
import libcore.util.ZoneInfo;

/* JADX INFO: loaded from: classes3.dex */
class TimeFormatter {
    private static final int DAYSPERLYEAR = 366;
    private static final int DAYSPERNYEAR = 365;
    private static final int DAYSPERWEEK = 7;
    private static final int FORCE_LOWER_CASE = -1;
    private static final int HOURSPERDAY = 24;
    private static final int MINSPERHOUR = 60;
    private static final int MONSPERYEAR = 12;
    private static final int SECSPERMIN = 60;
    private static String sDateOnlyFormat;
    private static String sDateTimeFormat;
    private static Locale sLocale;
    private static LocaleData sLocaleData;
    private static String sTimeOnlyFormat;
    private final String dateOnlyFormat;
    private final String dateTimeFormat;
    private final LocaleData localeData;
    private java.util.Formatter numberFormatter;
    private StringBuilder outputBuilder;
    private final String timeOnlyFormat;

    private static boolean brokenIsLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    private static boolean brokenIsUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static char brokenToLower(char c) {
        return (c < 'A' || c > 'Z') ? c : (char) ((c - DateFormat.CAPITAL_AM_PM) + 97);
    }

    private static char brokenToUpper(char c) {
        return (c < 'a' || c > 'z') ? c : (char) ((c - DateFormat.AM_PM) + 65);
    }

    private static String getFormat(int i, String str, String str2, String str3, String str4) {
        return i != 45 ? i != 48 ? i != 95 ? str : str2 : str4 : str3;
    }

    public TimeFormatter() {
        synchronized (TimeFormatter.class) {
            Locale locale = Locale.getDefault();
            Locale locale2 = sLocale;
            if (locale2 == null || !locale.equals(locale2)) {
                sLocale = locale;
                sLocaleData = LocaleData.get(locale);
                Resources system = Resources.getSystem();
                sTimeOnlyFormat = system.getString(R.string.time_of_day);
                sDateOnlyFormat = system.getString(R.string.month_day_year);
                sDateTimeFormat = system.getString(R.string.date_and_time);
            }
            this.dateTimeFormat = sDateTimeFormat;
            this.timeOnlyFormat = sTimeOnlyFormat;
            this.dateOnlyFormat = sDateOnlyFormat;
            this.localeData = sLocaleData;
        }
    }

    String formatMillisWithFixedFormat(long j) {
        LocalDateTime localDateTimeOfInstant = LocalDateTime.ofInstant(Instant.ofEpochMilli(j), ZoneId.systemDefault());
        StringBuilder sb = new StringBuilder(19);
        sb.append(localDateTimeOfInstant.getYear());
        sb.append('-');
        append2DigitNumber(sb, localDateTimeOfInstant.getMonthValue());
        sb.append('-');
        append2DigitNumber(sb, localDateTimeOfInstant.getDayOfMonth());
        sb.append(' ');
        append2DigitNumber(sb, localDateTimeOfInstant.getHour());
        sb.append(ShortcutConstants.SERVICES_SEPARATOR);
        append2DigitNumber(sb, localDateTimeOfInstant.getMinute());
        sb.append(ShortcutConstants.SERVICES_SEPARATOR);
        append2DigitNumber(sb, localDateTimeOfInstant.getSecond());
        return localizeDigits(sb.toString());
    }

    private static void append2DigitNumber(StringBuilder sb, int i) {
        if (i < 10) {
            sb.append('0');
        }
        sb.append(i);
    }

    public String format(String str, ZoneInfo.WallTime wallTime, ZoneInfo zoneInfo) {
        try {
            StringBuilder sb = new StringBuilder();
            this.outputBuilder = sb;
            this.numberFormatter = new java.util.Formatter(sb, Locale.US);
            formatInternal(str, wallTime, zoneInfo);
            return localizeDigits(sb.toString());
        } finally {
            this.outputBuilder = null;
            this.numberFormatter = null;
        }
    }

    private String localizeDigits(String str) {
        if (this.localeData.zeroDigit == '0') {
            return str;
        }
        int length = str.length();
        int i = this.localeData.zeroDigit - '0';
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt >= '0' && cCharAt <= '9') {
                cCharAt = (char) (cCharAt + i);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private void formatInternal(String str, ZoneInfo.WallTime wallTime, ZoneInfo zoneInfo) {
        CharBuffer charBufferWrap = CharBuffer.wrap(str);
        while (charBufferWrap.remaining() > 0) {
            if (charBufferWrap.get(charBufferWrap.position()) == '%' ? handleToken(charBufferWrap, wallTime, zoneInfo) : true) {
                this.outputBuilder.append(charBufferWrap.get(charBufferWrap.position()));
            }
            charBufferWrap.position(charBufferWrap.position() + 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:156:0x02ed, code lost:
    
        if (r20.getMonth() < 0) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x02f3, code lost:
    
        if (r20.getMonth() < 12) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02f6, code lost:
    
        r17 = r18.localeData.shortMonthNames[r20.getMonth()];
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0300, code lost:
    
        modifyAndAppend(r17, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0305, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean handleToken(java.nio.CharBuffer r19, libcore.util.ZoneInfo.WallTime r20, libcore.util.ZoneInfo r21) {
        /*
            Method dump skipped, instruction units count: 1052
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.format.TimeFormatter.handleToken(java.nio.CharBuffer, libcore.util.ZoneInfo$WallTime, libcore.util.ZoneInfo):boolean");
    }

    private void modifyAndAppend(CharSequence charSequence, int i) {
        int i2 = 0;
        if (i == -1) {
            while (i2 < charSequence.length()) {
                this.outputBuilder.append(brokenToLower(charSequence.charAt(i2)));
                i2++;
            }
            return;
        }
        if (i != 35) {
            if (i == 94) {
                while (i2 < charSequence.length()) {
                    this.outputBuilder.append(brokenToUpper(charSequence.charAt(i2)));
                    i2++;
                }
                return;
            }
            this.outputBuilder.append(charSequence);
            return;
        }
        while (i2 < charSequence.length()) {
            char cCharAt = charSequence.charAt(i2);
            if (brokenIsUpper(cCharAt)) {
                cCharAt = brokenToLower(cCharAt);
            } else if (brokenIsLower(cCharAt)) {
                cCharAt = brokenToUpper(cCharAt);
            }
            this.outputBuilder.append(cCharAt);
            i2++;
        }
    }

    private void outputYear(int i, boolean z, boolean z2, int i2) {
        int i3 = i % 100;
        int i4 = (i / 100) + (i3 / 100);
        int i5 = i3 % 100;
        if (i5 < 0 && i4 > 0) {
            i5 += 100;
            i4--;
        } else if (i4 < 0 && i5 > 0) {
            i5 -= 100;
            i4++;
        }
        if (z) {
            if (i4 == 0 && i5 < 0) {
                this.outputBuilder.append("-0");
            } else {
                this.numberFormatter.format(getFormat(i2, "%02d", "%2d", "%d", "%02d"), Integer.valueOf(i4));
            }
        }
        if (z2) {
            if (i5 < 0) {
                i5 = -i5;
            }
            this.numberFormatter.format(getFormat(i2, "%02d", "%2d", "%d", "%02d"), Integer.valueOf(i5));
        }
    }

    private static boolean isLeap(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }
}
