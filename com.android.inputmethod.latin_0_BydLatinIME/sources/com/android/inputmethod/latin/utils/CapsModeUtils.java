package com.android.inputmethod.latin.utils;

import android.text.TextUtils;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class CapsModeUtils {
    public static boolean isAutoCapsMode(final int mode) {
        return 5 == mode || 7 == mode;
    }

    private CapsModeUtils() {
    }

    public static String applyAutoCapsMode(final String s, final int capitalizeMode, final Locale locale) {
        if (7 == capitalizeMode) {
            return s.toUpperCase(locale);
        }
        return 5 == capitalizeMode ? StringUtils.capitalizeFirstCodePoint(s, locale) : s;
    }

    private static boolean isStartPunctuation(final int codePoint) {
        return codePoint == 34 || codePoint == 39 || codePoint == 191 || codePoint == 161 || Character.getType(codePoint) == 21;
    }

    public static int getCapsMode(final CharSequence cs, final int reqModes, final SpacingAndPunctuations spacingAndPunctuations, final boolean hasSpaceBefore) {
        int length;
        if ((reqModes & 24576) == 0) {
            return reqModes & 4096;
        }
        if (hasSpaceBefore) {
            length = cs.length() + 1;
        } else {
            length = cs.length();
            while (length > 0 && isStartPunctuation(cs.charAt(length - 1))) {
                length--;
            }
        }
        char cCharAt = ' ';
        int i = hasSpaceBefore ? length - 1 : length;
        while (i > 0) {
            cCharAt = cs.charAt(i - 1);
            if (!Character.isSpaceChar(cCharAt) && cCharAt != '\t') {
                break;
            }
            i--;
        }
        char c = 0;
        if (i <= 0 || Character.isWhitespace(cCharAt)) {
            if (spacingAndPunctuations.mUsesGermanRules) {
                while (true) {
                    i--;
                    if (i < 0 || !Character.isWhitespace(cCharAt)) {
                        break;
                    }
                    if ('\n' == cCharAt) {
                        c = 1;
                    }
                    cCharAt = cs.charAt(i);
                }
                if (',' == cCharAt && c != 0) {
                    return reqModes & 12288;
                }
            }
            return reqModes & 28672;
        }
        if (length == i) {
            return reqModes & 4096;
        }
        if ((reqModes & 16384) == 0) {
            return reqModes & 12288;
        }
        if (spacingAndPunctuations.mUsesAmericanTypography) {
            while (i > 0) {
                char cCharAt2 = cs.charAt(i - 1);
                if (cCharAt2 != '\"' && cCharAt2 != '\'' && Character.getType(cCharAt2) != 22) {
                    break;
                }
                i--;
            }
        }
        if (i <= 0) {
            return reqModes & 4096;
        }
        int i2 = i - 1;
        char cCharAt3 = cs.charAt(i2);
        if (spacingAndPunctuations.isSentenceTerminator(cCharAt3) && !spacingAndPunctuations.isAbbreviationMarker(cCharAt3)) {
            return reqModes & 28672;
        }
        if (!spacingAndPunctuations.isSentenceSeparator(cCharAt3) || i2 <= 0) {
            return reqModes & 12288;
        }
        int i3 = reqModes & 28672;
        int i4 = reqModes & 12288;
        while (i2 > 0) {
            i2--;
            char cCharAt4 = cs.charAt(i2);
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c != 3) {
                            if (c != 4) {
                                continue;
                            } else if (Character.isLetter(cCharAt4)) {
                                c = 1;
                            } else {
                                if (!Character.isDigit(cCharAt4)) {
                                    return i4;
                                }
                                c = 4;
                            }
                        } else if (!Character.isLetter(cCharAt4)) {
                            if (!spacingAndPunctuations.isSentenceSeparator(cCharAt4)) {
                                return i4;
                            }
                            c = 2;
                        }
                    } else if (!Character.isLetter(cCharAt4)) {
                        return i3;
                    }
                    c = 3;
                } else if (Character.isLetter(cCharAt4)) {
                    c = 1;
                } else {
                    if (!spacingAndPunctuations.isSentenceSeparator(cCharAt4)) {
                        return i3;
                    }
                    c = 2;
                }
            } else if (Character.isLetter(cCharAt4)) {
                c = 1;
            } else {
                if (Character.isWhitespace(cCharAt4)) {
                    return i4;
                }
                if (!Character.isDigit(cCharAt4) || !spacingAndPunctuations.mUsesGermanRules) {
                    return i3;
                }
                c = 4;
            }
        }
        return (c == 0 || 3 == c) ? i4 : i3;
    }

    public static String flagsToString(final int capsFlags) {
        if ((capsFlags & (-28673)) != 0) {
            return "unknown<0x" + Integer.toHexString(capsFlags) + ">";
        }
        ArrayList arrayList = new ArrayList();
        if ((capsFlags & 4096) != 0) {
            arrayList.add("characters");
        }
        if ((capsFlags & 8192) != 0) {
            arrayList.add("words");
        }
        if ((capsFlags & 16384) != 0) {
            arrayList.add("sentences");
        }
        return arrayList.isEmpty() ? "none" : TextUtils.join("|", arrayList);
    }
}
