package io.sentry.vendor.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f16958a = TimeZone.getTimeZone("UTC");

    public static boolean a(String str, int i7, char c) {
        return i7 < str.length() && str.charAt(i7) == c;
    }

    public static void b(int i7, int i10, StringBuilder sb) {
        String string = Integer.toString(i7);
        for (int length = i10 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    public static Date c(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i7;
        int iD;
        int i10;
        int iD2;
        int length;
        char cCharAt;
        try {
            int index = parsePosition.getIndex();
            int i11 = index + 4;
            int iD3 = d(str, index, i11);
            if (a(str, i11, '-')) {
                i11 = index + 5;
            }
            int i12 = i11 + 2;
            int iD4 = d(str, i11, i12);
            if (a(str, i12, '-')) {
                i12 = i11 + 3;
            }
            int i13 = i12 + 2;
            int iD5 = d(str, i12, i13);
            boolean zA = a(str, i13, 'T');
            if (!zA && str.length() <= i13) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(iD3, iD4 - 1, iD5);
                parsePosition.setIndex(i13);
                return gregorianCalendar.getTime();
            }
            if (zA) {
                int i14 = i12 + 5;
                int iD6 = d(str, i12 + 3, i14);
                if (a(str, i14, ':')) {
                    i14 = i12 + 6;
                }
                int i15 = i14 + 2;
                int iD7 = d(str, i14, i15);
                if (a(str, i15, ':')) {
                    i15 = i14 + 3;
                }
                if (str.length() <= i15 || (cCharAt = str.charAt(i15)) == 'Z' || cCharAt == '+' || cCharAt == '-') {
                    i10 = iD7;
                    iD = 0;
                    iD2 = 0;
                    i13 = i15;
                    i7 = iD6;
                } else {
                    int i16 = i15 + 2;
                    iD2 = d(str, i15, i16);
                    if (iD2 > 59 && iD2 < 63) {
                        iD2 = 59;
                    }
                    if (a(str, i16, '.')) {
                        int i17 = i15 + 3;
                        int length2 = i15 + 4;
                        while (true) {
                            if (length2 >= str.length()) {
                                length2 = str.length();
                                break;
                            }
                            char cCharAt2 = str.charAt(length2);
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                break;
                            }
                            length2++;
                        }
                        int iMin = Math.min(length2, i15 + 6);
                        iD = d(str, i17, iMin);
                        int i18 = iMin - i17;
                        if (i18 == 1) {
                            iD *= 100;
                        } else if (i18 == 2) {
                            iD *= 10;
                        }
                        i7 = iD6;
                        i13 = length2;
                        i10 = iD7;
                    } else {
                        i7 = iD6;
                        i13 = i16;
                        i10 = iD7;
                        iD = 0;
                    }
                }
            } else {
                i7 = 0;
                iD = 0;
                i10 = 0;
                iD2 = 0;
            }
            if (str.length() <= i13) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            char cCharAt3 = str.charAt(i13);
            TimeZone timeZone = f16958a;
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
                if (!"+0000".equals(strSubstring) && !"+00:00".equals(strSubstring)) {
                    String str3 = "GMT" + strSubstring;
                    timeZone = TimeZone.getTimeZone(str3);
                    String id = timeZone.getID();
                    if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone.getID());
                    }
                }
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, iD3);
            gregorianCalendar2.set(2, iD4 - 1);
            gregorianCalendar2.set(5, iD5);
            gregorianCalendar2.set(11, i7);
            gregorianCalendar2.set(12, i10);
            gregorianCalendar2.set(13, iD2);
            gregorianCalendar2.set(14, iD);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException e10) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = "\"" + str + '\"';
            }
            String message = e10.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e10.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e10);
            throw parseException;
        }
    }

    public static int d(String str, int i7, int i10) {
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
