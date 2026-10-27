package n5;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: renamed from: n5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1959a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f19975a = TimeZone.getTimeZone("UTC");

    public static boolean a(String str, int i7, char c) {
        return i7 < str.length() && str.charAt(i7) == c;
    }

    public static Date b(String str, ParsePosition parsePosition) throws ParseException {
        int i7;
        int i10;
        int iC;
        int iC2;
        int length;
        char cCharAt;
        int length2;
        try {
            int index = parsePosition.getIndex();
            int i11 = index + 4;
            int iC3 = c(str, index, i11);
            if (a(str, i11, '-')) {
                i11 = index + 5;
            }
            int i12 = i11 + 2;
            int iC4 = c(str, i11, i12);
            if (a(str, i12, '-')) {
                i12 = i11 + 3;
            }
            int i13 = i12 + 2;
            int iC5 = c(str, i12, i13);
            boolean zA = a(str, i13, 'T');
            if (!zA && str.length() <= i13) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(iC3, iC4 - 1, iC5);
                gregorianCalendar.setLenient(false);
                parsePosition.setIndex(i13);
                return gregorianCalendar.getTime();
            }
            if (zA) {
                int i14 = i12 + 5;
                int iC6 = c(str, i12 + 3, i14);
                if (a(str, i14, ':')) {
                    i14 = i12 + 6;
                }
                int i15 = i14 + 2;
                int iC7 = c(str, i14, i15);
                if (a(str, i15, ':')) {
                    i15 = i14 + 3;
                }
                if (str.length() <= i15 || (cCharAt = str.charAt(i15)) == 'Z' || cCharAt == '+' || cCharAt == '-') {
                    i10 = iC7;
                    iC = 0;
                    iC2 = 0;
                    i13 = i15;
                    i7 = iC6;
                } else {
                    int i16 = i15 + 2;
                    iC2 = c(str, i15, i16);
                    if (iC2 > 59 && iC2 < 63) {
                        iC2 = 59;
                    }
                    if (a(str, i16, '.')) {
                        int i17 = i15 + 3;
                        for (int i18 = i15 + 4; i18 < str.length(); i18++) {
                            char cCharAt2 = str.charAt(i18);
                            if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                            }
                            length2 = i18;
                        }
                        length2 = str.length();
                        int iMin = Math.min(length2, i15 + 6);
                        iC = c(str, i17, iMin);
                        int i19 = iMin - i17;
                        if (i19 == 1) {
                            iC *= 100;
                        } else if (i19 == 2) {
                            iC *= 10;
                        }
                        i7 = iC6;
                        i13 = length2;
                        i10 = iC7;
                    } else {
                        i7 = iC6;
                        i13 = i16;
                        i10 = iC7;
                        iC = 0;
                    }
                }
            } else {
                i7 = 0;
                i10 = 0;
                iC = 0;
                iC2 = 0;
            }
            if (str.length() <= i13) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            char cCharAt3 = str.charAt(i13);
            TimeZone timeZone = f19975a;
            if (cCharAt3 == 'Z') {
                length = i13 + 1;
            } else {
                if (cCharAt3 != '+' && cCharAt3 != '-') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt3 + "'");
                }
                String strSubstring = str.substring(i13);
                if (strSubstring.length() < 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i13 + strSubstring.length();
                if (!strSubstring.equals("+0000") && !strSubstring.equals("+00:00")) {
                    String str2 = "GMT" + strSubstring;
                    timeZone = TimeZone.getTimeZone(str2);
                    String id = timeZone.getID();
                    if (!id.equals(str2) && !id.replace(":", "").equals(str2)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str2 + " given, resolves to " + timeZone.getID());
                    }
                }
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, iC3);
            gregorianCalendar2.set(2, iC4 - 1);
            gregorianCalendar2.set(5, iC5);
            gregorianCalendar2.set(11, i7);
            gregorianCalendar2.set(12, i10);
            gregorianCalendar2.set(13, iC2);
            gregorianCalendar2.set(14, iC);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException e10) {
            String str3 = str == null ? null : "\"" + str + '\"';
            String message = e10.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e10.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str3 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e10);
            throw parseException;
        }
    }

    public static int c(String str, int i7, int i10) {
        int i11;
        int i12;
        if (i7 < 0 || i10 > str.length() || i7 > i10) {
            throw new NumberFormatException(str);
        }
        if (i7 < i10) {
            i12 = i7 + 1;
            int iDigit = Character.digit(str.charAt(i7), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i10));
            }
            i11 = -iDigit;
        } else {
            i11 = 0;
            i12 = i7;
        }
        while (i12 < i10) {
            int i13 = i12 + 1;
            int iDigit2 = Character.digit(str.charAt(i12), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i10));
            }
            i11 = (i11 * 10) - iDigit2;
            i12 = i13;
        }
        return -i11;
    }
}
