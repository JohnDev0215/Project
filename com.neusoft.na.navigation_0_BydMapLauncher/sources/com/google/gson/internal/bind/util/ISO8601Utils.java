package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes2.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = Soundex.SILENT_MARKER;
        sb.append(Soundex.SILENT_MARKER);
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append(Soundex.SILENT_MARKER);
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int iAbs = Math.abs(i / 60);
            int iAbs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, iAbs, 2);
            sb.append(':');
            padInt(sb, iAbs2, 2);
        } else {
            sb.append(Matrix.MATRIX_TYPE_ZERO);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d6 A[Catch: IllegalArgumentException -> 0x01d2, NumberFormatException -> 0x01d5, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d7, TRY_LEAVE, TryCatch #4 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d7, blocks: (B:3:0x000c, B:5:0x001e, B:6:0x0020, B:8:0x002c, B:9:0x002e, B:11:0x003d, B:13:0x0043, B:17:0x0057, B:19:0x0067, B:20:0x0069, B:22:0x0075, B:23:0x0077, B:25:0x007d, B:29:0x0087, B:34:0x0097, B:36:0x009f, B:49:0x00d0, B:51:0x00d6, B:41:0x00b9, B:42:0x00bc), top: B:116:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c3 A[Catch: IllegalArgumentException -> 0x01cc, NumberFormatException -> 0x01ce, IndexOutOfBoundsException -> 0x01d0, TryCatch #5 {IllegalArgumentException -> 0x01cc, IndexOutOfBoundsException -> 0x01d0, NumberFormatException -> 0x01ce, blocks: (B:88:0x01bb, B:90:0x01c3, B:91:0x01cb), top: B:115:0x00d4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) throws java.text.ParseException {
        /*
            Method dump skipped, instruction units count: 575
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -iDigit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int iDigit2 = Character.digit(str.charAt(i4), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - iDigit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String string = Integer.toString(i);
        for (int length = i2 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
