package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.latin.common.StringUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class KeySpecParser {
    private static final char BACKSLASH = '\\';
    private static final String PREFIX_HEX = "0x";
    private static final char VERTICAL_BAR = '|';

    private KeySpecParser() {
    }

    private static boolean hasIcon(@Nonnull final String keySpec) {
        return keySpec.startsWith(KeyboardIconsSet.PREFIX_ICON);
    }

    private static boolean hasCode(@Nonnull final String keySpec, final int labelEnd) {
        int i;
        return labelEnd > 0 && (i = labelEnd + 1) < keySpec.length() && (keySpec.startsWith(KeyboardCodesSet.PREFIX_CODE, i) || keySpec.startsWith(PREFIX_HEX, i));
    }

    @Nonnull
    private static String parseEscape(@Nonnull final String text) {
        int i;
        if (text.indexOf(92) < 0) {
            return text;
        }
        int length = text.length();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = text.charAt(i2);
            if (cCharAt == '\\' && (i = i2 + 1) < length) {
                sb.append(text.charAt(i));
                i2 = i;
            } else {
                sb.append(cCharAt);
            }
            i2++;
        }
        return sb.toString();
    }

    private static int indexOfLabelEnd(@Nonnull final String keySpec) {
        int i;
        int length = keySpec.length();
        if (keySpec.indexOf(92) < 0) {
            int iIndexOf = keySpec.indexOf(124);
            if (iIndexOf != 0) {
                return iIndexOf;
            }
            if (length == 1) {
                return -1;
            }
            throw new KeySpecParserError("Empty label");
        }
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = keySpec.charAt(i2);
            if (cCharAt == '\\' && (i = i2 + 1) < length) {
                i2 = i;
            } else if (cCharAt == '|') {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Nonnull
    private static String getBeforeLabelEnd(@Nonnull final String keySpec, final int labelEnd) {
        return labelEnd < 0 ? keySpec : keySpec.substring(0, labelEnd);
    }

    @Nonnull
    private static String getAfterLabelEnd(@Nonnull final String keySpec, final int labelEnd) {
        return keySpec.substring(labelEnd + 1);
    }

    private static void checkDoubleLabelEnd(@Nonnull final String keySpec, final int labelEnd) {
        if (indexOfLabelEnd(getAfterLabelEnd(keySpec, labelEnd)) >= 0) {
            throw new KeySpecParserError("Multiple |: " + keySpec);
        }
    }

    @Nullable
    public static String getLabel(@Nullable final String keySpec) {
        if (keySpec == null || hasIcon(keySpec)) {
            return null;
        }
        String escape = parseEscape(getBeforeLabelEnd(keySpec, indexOfLabelEnd(keySpec)));
        if (escape.isEmpty()) {
            throw new KeySpecParserError("Empty label: " + keySpec);
        }
        return escape;
    }

    @Nullable
    private static String getOutputTextInternal(@Nonnull final String keySpec, final int labelEnd) {
        if (labelEnd <= 0) {
            return null;
        }
        checkDoubleLabelEnd(keySpec, labelEnd);
        return parseEscape(getAfterLabelEnd(keySpec, labelEnd));
    }

    @Nullable
    public static String getOutputText(@Nullable final String keySpec) {
        if (keySpec == null) {
            return null;
        }
        int iIndexOfLabelEnd = indexOfLabelEnd(keySpec);
        if (hasCode(keySpec, iIndexOfLabelEnd)) {
            return null;
        }
        String outputTextInternal = getOutputTextInternal(keySpec, iIndexOfLabelEnd);
        if (outputTextInternal != null) {
            if (StringUtils.codePointCount(outputTextInternal) == 1) {
                return null;
            }
            if (outputTextInternal.isEmpty()) {
                throw new KeySpecParserError("Empty outputText: " + keySpec);
            }
            return outputTextInternal;
        }
        String label = getLabel(keySpec);
        if (label == null) {
            throw new KeySpecParserError("Empty label: " + keySpec);
        }
        if (StringUtils.codePointCount(label) == 1) {
            return null;
        }
        return label;
    }

    public static int getCode(@Nullable final String keySpec) {
        if (keySpec == null) {
            return -15;
        }
        int iIndexOfLabelEnd = indexOfLabelEnd(keySpec);
        if (hasCode(keySpec, iIndexOfLabelEnd)) {
            checkDoubleLabelEnd(keySpec, iIndexOfLabelEnd);
            return parseCode(getAfterLabelEnd(keySpec, iIndexOfLabelEnd), -15);
        }
        String outputTextInternal = getOutputTextInternal(keySpec, iIndexOfLabelEnd);
        if (outputTextInternal != null) {
            if (StringUtils.codePointCount(outputTextInternal) == 1) {
                return outputTextInternal.codePointAt(0);
            }
            return -4;
        }
        String label = getLabel(keySpec);
        if (label == null) {
            throw new KeySpecParserError("Empty label: " + keySpec);
        }
        if (StringUtils.codePointCount(label) == 1) {
            return label.codePointAt(0);
        }
        return -4;
    }

    public static int parseCode(@Nullable final String text, final int defaultCode) {
        if (text == null) {
            return defaultCode;
        }
        if (text.startsWith(KeyboardCodesSet.PREFIX_CODE)) {
            return KeyboardCodesSet.getCode(text.substring(6));
        }
        return text.startsWith(PREFIX_HEX) ? Integer.parseInt(text.substring(2), 16) : defaultCode;
    }

    public static int getIconId(@Nullable final String keySpec) {
        if (keySpec != null && hasIcon(keySpec)) {
            return KeyboardIconsSet.getIconId(getBeforeLabelEnd(keySpec, indexOfLabelEnd(keySpec)).substring(6));
        }
        return 0;
    }

    public static final class KeySpecParserError extends RuntimeException {
        public KeySpecParserError(final String message) {
            super(message);
        }
    }
}
