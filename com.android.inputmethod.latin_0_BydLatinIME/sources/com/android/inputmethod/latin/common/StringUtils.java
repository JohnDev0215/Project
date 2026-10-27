package com.android.inputmethod.latin.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public final class StringUtils {
    public static final int CAPITALIZE_ALL = 2;
    public static final int CAPITALIZE_FIRST = 1;
    public static final int CAPITALIZE_NONE = 0;
    private static final char CHAR_CARRIAGE_RETURN = '\r';
    private static final char CHAR_FORM_FEED = '\f';
    private static final char CHAR_LINE_FEED = '\n';
    private static final char CHAR_LINE_SEPARATOR = 8232;
    private static final char CHAR_NEXT_LINE = 133;
    private static final char CHAR_PARAGRAPH_SEPARATOR = 8233;
    private static final char CHAR_VERTICAL_TAB = 11;

    @Nonnull
    private static final int[] EMPTY_CODEPOINTS = new int[0];

    @Nonnull
    private static final String EMPTY_STRING = "";
    private static final String LANGUAGE_GREEK = "el";

    @Nonnull
    private static final String SEPARATOR_FOR_COMMA_SPLITTABLE_TEXT = ",";

    private StringUtils() {
    }

    public static boolean isEmpty(@Nullable final CharSequence str) {
        return str == null || str.length() == 0;
    }

    @Nonnull
    public static String join(@Nonnull final CharSequence delimiter, @Nonnull final Iterable<?> tokens) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : tokens) {
            if (z) {
                z = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    public static boolean equals(@Nullable final CharSequence a, @Nullable final CharSequence b) {
        int length;
        if (a == b) {
            return true;
        }
        if (a == null || b == null || (length = a.length()) != b.length()) {
            return false;
        }
        if ((a instanceof String) && (b instanceof String)) {
            return a.equals(b);
        }
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int codePointCount(@Nullable final CharSequence text) {
        if (isEmpty(text)) {
            return 0;
        }
        return Character.codePointCount(text, 0, text.length());
    }

    @Nonnull
    public static String newSingleCodePointString(final int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf((char) codePoint);
        }
        return new String(Character.toChars(codePoint));
    }

    public static boolean containsInArray(@Nonnull final String text, @Nonnull final String[] array) {
        for (String str : array) {
            if (text.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsInCommaSplittableText(@Nonnull final String text, @Nullable final String extraValues) {
        if (isEmpty(extraValues)) {
            return false;
        }
        return containsInArray(text, extraValues.split(SEPARATOR_FOR_COMMA_SPLITTABLE_TEXT));
    }

    @Nonnull
    public static String removeFromCommaSplittableTextIfExists(@Nonnull final String text, @Nullable final String extraValues) {
        if (isEmpty(extraValues)) {
            return "";
        }
        String[] strArrSplit = extraValues.split(SEPARATOR_FOR_COMMA_SPLITTABLE_TEXT);
        if (!containsInArray(text, strArrSplit)) {
            return extraValues;
        }
        ArrayList arrayList = new ArrayList(strArrSplit.length - 1);
        for (String str : strArrSplit) {
            if (!text.equals(str)) {
                arrayList.add(str);
            }
        }
        return join(SEPARATOR_FOR_COMMA_SPLITTABLE_TEXT, arrayList);
    }

    public static void removeDupes(@Nonnull final ArrayList<String> suggestions) {
        if (suggestions.size() < 2) {
            return;
        }
        int i = 1;
        while (i < suggestions.size()) {
            String str = suggestions.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    break;
                }
                if (equals(str, suggestions.get(i2))) {
                    suggestions.remove(i);
                    i--;
                    break;
                }
                i2++;
            }
            i++;
        }
    }

    @Nonnull
    public static String capitalizeFirstCodePoint(@Nonnull final String s, @Nonnull final Locale locale) {
        if (s.length() <= 1) {
            return s.toUpperCase(getLocaleUsedForToTitleCase(locale));
        }
        int iOffsetByCodePoints = s.offsetByCodePoints(0, 1);
        return s.substring(0, iOffsetByCodePoints).toUpperCase(getLocaleUsedForToTitleCase(locale)) + s.substring(iOffsetByCodePoints);
    }

    @Nonnull
    public static String capitalizeFirstAndDowncaseRest(@Nonnull final String s, @Nonnull final Locale locale) {
        if (s.length() <= 1) {
            return s.toUpperCase(getLocaleUsedForToTitleCase(locale));
        }
        int iOffsetByCodePoints = s.offsetByCodePoints(0, 1);
        return s.substring(0, iOffsetByCodePoints).toUpperCase(getLocaleUsedForToTitleCase(locale)) + s.substring(iOffsetByCodePoints).toLowerCase(locale);
    }

    @Nonnull
    public static int[] toCodePointArray(@Nonnull final CharSequence charSequence) {
        return toCodePointArray(charSequence, 0, charSequence.length());
    }

    @Nonnull
    public static int[] toCodePointArray(@Nonnull final CharSequence charSequence, final int startIndex, final int endIndex) {
        if (charSequence.length() <= 0) {
            return EMPTY_CODEPOINTS;
        }
        int[] iArr = new int[Character.codePointCount(charSequence, startIndex, endIndex)];
        copyCodePointsAndReturnCodePointCount(iArr, charSequence, startIndex, endIndex, false);
        return iArr;
    }

    public static int copyCodePointsAndReturnCodePointCount(@Nonnull final int[] destination, @Nonnull final CharSequence charSequence, final int startIndex, final int endIndex, final boolean downCase) {
        int i = 0;
        while (startIndex < endIndex) {
            int iCodePointAt = Character.codePointAt(charSequence, startIndex);
            if (downCase) {
                iCodePointAt = Character.toLowerCase(iCodePointAt);
            }
            destination[i] = iCodePointAt;
            i++;
            startIndex = Character.offsetByCodePoints(charSequence, startIndex, 1);
        }
        return i;
    }

    @Nonnull
    public static int[] toSortedCodePointArray(@Nonnull final String string) {
        int[] codePointArray = toCodePointArray(string);
        Arrays.sort(codePointArray);
        return codePointArray;
    }

    @Nonnull
    public static String getStringFromNullTerminatedCodePointArray(@Nonnull final int[] codePoints) {
        int length = codePoints.length;
        int i = 0;
        while (true) {
            if (i >= codePoints.length) {
                break;
            }
            if (codePoints[i] == 0) {
                length = i;
                break;
            }
            i++;
        }
        return new String(codePoints, 0, length);
    }

    public static int getCapitalizationType(@Nonnull final String text) {
        int length = text.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length && !Character.isLetter(text.codePointAt(iOffsetByCodePoints))) {
            iOffsetByCodePoints = text.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        if (iOffsetByCodePoints == length || !Character.isUpperCase(text.codePointAt(iOffsetByCodePoints))) {
            return 0;
        }
        int iOffsetByCodePoints2 = text.offsetByCodePoints(iOffsetByCodePoints, 1);
        int i = 1;
        int i2 = 1;
        while (iOffsetByCodePoints2 < length && (1 == i || i2 == i)) {
            int iCodePointAt = text.codePointAt(iOffsetByCodePoints2);
            if (Character.isUpperCase(iCodePointAt)) {
                i++;
            } else {
                if (Character.isLetter(iCodePointAt)) {
                }
                iOffsetByCodePoints2 = text.offsetByCodePoints(iOffsetByCodePoints2, 1);
            }
            i2++;
            iOffsetByCodePoints2 = text.offsetByCodePoints(iOffsetByCodePoints2, 1);
        }
        if (1 == i) {
            return 1;
        }
        return i2 == i ? 2 : 0;
    }

    public static boolean isIdenticalAfterUpcase(@Nonnull final String text) {
        int length = text.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = text.codePointAt(iCharCount);
            if (Character.isLetter(iCodePointAt) && !Character.isUpperCase(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public static boolean isIdenticalAfterDowncase(@Nonnull final String text) {
        int length = text.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = text.codePointAt(iCharCount);
            if (Character.isLetter(iCodePointAt) && !Character.isLowerCase(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public static boolean isIdenticalAfterCapitalizeEachWord(@Nonnull final String text, @Nonnull final int[] sortedSeparators) {
        int length = text.length();
        int iOffsetByCodePoints = 0;
        boolean z = true;
        while (iOffsetByCodePoints < length) {
            int iCodePointAt = text.codePointAt(iOffsetByCodePoints);
            if (Character.isLetter(iCodePointAt) && ((z && !Character.isUpperCase(iCodePointAt)) || (!z && !Character.isLowerCase(iCodePointAt)))) {
                return false;
            }
            z = Arrays.binarySearch(sortedSeparators, iCodePointAt) >= 0;
            iOffsetByCodePoints = text.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        return true;
    }

    @Nonnull
    public static String capitalizeEachWord(@Nonnull final String text, @Nonnull final int[] sortedSeparators, @Nonnull final Locale locale) {
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        int iOffsetByCodePoints = 0;
        boolean z = true;
        while (iOffsetByCodePoints < length) {
            String strSubstring = text.substring(iOffsetByCodePoints, text.offsetByCodePoints(iOffsetByCodePoints, 1));
            if (z) {
                sb.append(strSubstring.toUpperCase(locale));
            } else {
                sb.append(strSubstring.toLowerCase(locale));
            }
            z = Arrays.binarySearch(sortedSeparators, strSubstring.codePointAt(0)) >= 0;
            iOffsetByCodePoints = text.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        return sb.toString();
    }

    public static boolean lastPartLooksLikeURL(@Nonnull final CharSequence text) {
        int length = text.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int iCodePointBefore = 0;
        boolean z2 = false;
        while (length > 0 && (iCodePointBefore = Character.codePointBefore(text, length)) >= 46 && iCodePointBefore <= 122) {
            if (46 == iCodePointBefore) {
                z = true;
            }
            if (47 == iCodePointBefore) {
                i2++;
                if (2 == i2) {
                    return true;
                }
                z2 = true;
            } else {
                i2 = 0;
            }
            i = 119 == iCodePointBefore ? i + 1 : 0;
            length = Character.offsetByCodePoints(text, length, -1);
        }
        if (i >= 3 && z) {
            return true;
        }
        if (1 == i2 && (length == 0 || Character.isWhitespace(iCodePointBefore))) {
            return true;
        }
        return z && z2;
    }

    public static boolean isInsideDoubleQuoteOrAfterDigit(@Nonnull final CharSequence text) {
        int length = text.length();
        if (length == 0) {
            return false;
        }
        int iCodePointBefore = Character.codePointBefore(text, length);
        if (Character.isDigit(iCodePointBefore)) {
            return true;
        }
        int i = 0;
        while (length > 0) {
            iCodePointBefore = Character.codePointBefore(text, length);
            if (34 == iCodePointBefore && Character.isWhitespace(i)) {
                return false;
            }
            if (Character.isWhitespace(iCodePointBefore) && 34 == i) {
                return true;
            }
            length -= Character.charCount(iCodePointBefore);
            i = iCodePointBefore;
        }
        return 34 == iCodePointBefore;
    }

    public static boolean isEmptyStringOrWhiteSpaces(@Nonnull final String s) {
        int iCodePointCount = codePointCount(s);
        for (int i = 0; i < iCodePointCount; i++) {
            if (!Character.isWhitespace(s.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Nonnull
    public static String byteArrayToHexString(@Nullable final byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", Integer.valueOf(b & UByte.MAX_VALUE)));
        }
        return sb.toString();
    }

    @Nullable
    public static byte[] hexStringToByteArray(@Nullable final String hexString) {
        if (isEmpty(hexString)) {
            return null;
        }
        int length = hexString.length();
        if (length % 2 != 0) {
            throw new NumberFormatException("Input hex string length must be an even number. Length = " + length);
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bArr;
    }

    @Nonnull
    private static Locale getLocaleUsedForToTitleCase(@Nonnull final Locale locale) {
        return LANGUAGE_GREEK.equals(locale.getLanguage()) ? Locale.ROOT : locale;
    }

    @Nullable
    public static String toTitleCaseOfKeyLabel(@Nullable final String label, @Nonnull final Locale locale) {
        return label == null ? label : label.toUpperCase(getLocaleUsedForToTitleCase(locale));
    }

    public static int toTitleCaseOfKeyCode(final int code, @Nonnull final Locale locale) {
        if (!Constants.isLetterCode(code)) {
            return code;
        }
        String titleCaseOfKeyLabel = toTitleCaseOfKeyLabel(newSingleCodePointString(code), locale);
        if (codePointCount(titleCaseOfKeyLabel) == 1) {
            return titleCaseOfKeyLabel.codePointAt(0);
        }
        return -15;
    }

    public static int getTrailingSingleQuotesCount(@Nonnull final CharSequence charSequence) {
        int length = charSequence.length() - 1;
        int i = length;
        while (i >= 0 && charSequence.charAt(i) == '\'') {
            i--;
        }
        return length - i;
    }

    public static class Stringizer<E> {

        @Nonnull
        private static final String[] EMPTY_STRING_ARRAY = new String[0];

        @Nonnull
        public String stringize(@Nullable final E element) {
            return element == null ? "null" : element.toString();
        }

        @Nonnull
        public final String join(@Nullable final E[] array) {
            return joinStringArray(toStringArray(array), null);
        }

        public final String join(@Nullable final E[] array, @Nullable final String delimiter) {
            return joinStringArray(toStringArray(array), delimiter);
        }

        @Nonnull
        protected String[] toStringArray(@Nullable final E[] array) {
            if (array == null) {
                return EMPTY_STRING_ARRAY;
            }
            String[] strArr = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                strArr[i] = stringize(array[i]);
            }
            return strArr;
        }

        @Nonnull
        protected String joinStringArray(@Nonnull final String[] stringArray, @Nullable final String delimiter) {
            if (delimiter == null) {
                return Arrays.toString(stringArray);
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < stringArray.length) {
                sb.append(i == 0 ? "[" : delimiter);
                sb.append(stringArray[i]);
                i++;
            }
            return ((Object) sb) + "]";
        }
    }

    public static boolean hasLineBreakCharacter(@Nullable final String text) {
        if (isEmpty(text)) {
            return false;
        }
        for (int length = text.length() - 1; length >= 0; length--) {
            char cCharAt = text.charAt(length);
            if (cCharAt != 133 && cCharAt != 8232 && cCharAt != 8233) {
                switch (cCharAt) {
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
        return false;
    }
}
