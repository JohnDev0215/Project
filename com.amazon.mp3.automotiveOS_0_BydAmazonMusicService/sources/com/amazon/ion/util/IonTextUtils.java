package com.amazon.ion.util;

import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.impl._Private_IonTextAppender;
import com.amazon.ion.impl._Private_IonTextWriterBuilder;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class IonTextUtils {

    private enum EscapeMode {
        JSON,
        ION_SYMBOL,
        ION_STRING,
        ION_LONG_STRING
    }

    public enum SymbolVariant {
        IDENTIFIER,
        OPERATOR,
        QUOTED
    }

    public static boolean isNumericStop(int i) {
        if (i == -1 || i == 13 || i == 32 || i == 34 || i == 44 || i == 91 || i == 93 || i == 123 || i == 125 || i == 9 || i == 10) {
            return true;
        }
        switch (i) {
            case 39:
            case 40:
            case 41:
                return true;
            default:
                return false;
        }
    }

    public static boolean isWhitespace(int i) {
        return i == 9 || i == 10 || i == 13 || i == 32;
    }

    public static boolean isAllWhitespace(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!isWhitespace(Character.codePointAt(charSequence, i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIdentifierStart(int i) {
        return _Private_IonTextAppender.isIdentifierStart(i);
    }

    public static boolean isIdentifierPart(int i) {
        return _Private_IonTextAppender.isIdentifierPart(i);
    }

    public static boolean isOperatorPart(int i) {
        return _Private_IonTextAppender.isOperatorPart(i);
    }

    public static SymbolVariant symbolVariant(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0 || _Private_IonTextAppender.isIdentifierKeyword(charSequence)) {
            return SymbolVariant.QUOTED;
        }
        int i = 0;
        char cCharAt = charSequence.charAt(0);
        if (isIdentifierStart(cCharAt)) {
            while (i < length) {
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt2 == '\'' || cCharAt2 < ' ' || cCharAt2 > '~' || !isIdentifierPart(cCharAt2)) {
                    return SymbolVariant.QUOTED;
                }
                i++;
            }
            return SymbolVariant.IDENTIFIER;
        }
        if (isOperatorPart(cCharAt)) {
            while (i < length) {
                if (!isOperatorPart(charSequence.charAt(i))) {
                    return SymbolVariant.QUOTED;
                }
                i++;
            }
            return SymbolVariant.OPERATOR;
        }
        return SymbolVariant.QUOTED;
    }

    public static void printStringCodePoint(Appendable appendable, int i) throws IOException {
        printCodePoint(appendable, i, EscapeMode.ION_STRING);
    }

    public static void printJsonCodePoint(Appendable appendable, int i) throws IOException {
        printCodePoint(appendable, i, EscapeMode.JSON);
    }

    private static void printCodePoint(Appendable appendable, int i, EscapeMode escapeMode) throws IOException {
        if (i == 0) {
            appendable.append(escapeMode == EscapeMode.JSON ? "\\u0000" : "\\0");
            return;
        }
        if (i == 34) {
            if (escapeMode == EscapeMode.JSON || escapeMode == EscapeMode.ION_STRING) {
                appendable.append("\\\"");
                return;
            }
        } else {
            if (i != 39) {
                if (i != 92) {
                    switch (i) {
                        case 7:
                            appendable.append(escapeMode == EscapeMode.JSON ? "\\u0007" : "\\a");
                            break;
                        case 8:
                            appendable.append("\\b");
                            break;
                        case 9:
                            appendable.append("\\t");
                            break;
                        case 10:
                            if (escapeMode == EscapeMode.ION_LONG_STRING) {
                                appendable.append('\n');
                            } else {
                                appendable.append("\\n");
                            }
                            break;
                        case 11:
                            appendable.append(escapeMode == EscapeMode.JSON ? "\\u000b" : "\\v");
                            break;
                        case 12:
                            appendable.append("\\f");
                            break;
                        case 13:
                            appendable.append("\\r");
                            break;
                    }
                    return;
                }
                appendable.append("\\\\");
                return;
            }
            if (escapeMode == EscapeMode.ION_SYMBOL || escapeMode == EscapeMode.ION_LONG_STRING) {
                appendable.append("\\'");
                return;
            }
        }
        if (i < 32) {
            if (escapeMode == EscapeMode.JSON) {
                printCodePointAsFourHexDigits(appendable, i);
                return;
            } else {
                printCodePointAsTwoHexDigits(appendable, i);
                return;
            }
        }
        if (i < 127) {
            appendable.append((char) i);
            return;
        }
        if (i <= 255) {
            if (escapeMode == EscapeMode.JSON) {
                printCodePointAsFourHexDigits(appendable, i);
                return;
            } else {
                printCodePointAsTwoHexDigits(appendable, i);
                return;
            }
        }
        if (i <= 65535) {
            printCodePointAsFourHexDigits(appendable, i);
        } else if (escapeMode == EscapeMode.JSON) {
            printCodePointAsSurrogatePairHexDigits(appendable, i);
        } else {
            printCodePointAsEightHexDigits(appendable, i);
        }
    }

    private static void printCodePointAsTwoHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append("\\x");
        if (hexString.length() < 2) {
            appendable.append(_Private_IonTextAppender.ZERO_PADDING[2 - hexString.length()]);
        }
        appendable.append(hexString);
    }

    private static void printCodePointAsFourHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append("\\u");
        appendable.append(_Private_IonTextAppender.ZERO_PADDING[4 - hexString.length()]);
        appendable.append(hexString);
    }

    private static void printCodePointAsEightHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append("\\U");
        appendable.append(_Private_IonTextAppender.ZERO_PADDING[8 - hexString.length()]);
        appendable.append(hexString);
    }

    private static void printCodePointAsSurrogatePairHexDigits(Appendable appendable, int i) throws IOException {
        for (char c : Character.toChars(i)) {
            printCodePointAsFourHexDigits(appendable, c);
        }
    }

    public static void printString(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.string");
            return;
        }
        appendable.append('\"');
        printCodePoints(appendable, charSequence, EscapeMode.ION_STRING);
        appendable.append('\"');
    }

    public static void printJsonString(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null");
            return;
        }
        appendable.append('\"');
        printCodePoints(appendable, charSequence, EscapeMode.JSON);
        appendable.append('\"');
    }

    public static String printString(CharSequence charSequence) {
        if (charSequence == null) {
            return "null.string";
        }
        if (charSequence.length() == 0) {
            return "\"\"";
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 2);
        try {
            printString(sb, charSequence);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printCodePointAsString(int i) {
        StringBuilder sb = new StringBuilder(12);
        sb.append('\"');
        try {
            printStringCodePoint(sb, i);
            sb.append('\"');
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static void printSymbol(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.symbol");
        } else if (_Private_IonTextAppender.symbolNeedsQuoting(charSequence, true)) {
            printQuotedSymbol(appendable, charSequence);
        } else {
            appendable.append(charSequence);
        }
    }

    public static void printQuotedSymbol(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.symbol");
            return;
        }
        appendable.append('\'');
        printCodePoints(appendable, charSequence, EscapeMode.ION_SYMBOL);
        appendable.append('\'');
    }

    private static void printCodePoints(Appendable appendable, CharSequence charSequence, EscapeMode escapeMode) throws IOException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iCharAt = charSequence.charAt(i);
            if (_Private_IonConstants.isHighSurrogate(iCharAt)) {
                i++;
                if (i < length) {
                    char cCharAt = charSequence.charAt(i);
                    if (_Private_IonConstants.isLowSurrogate(cCharAt)) {
                        iCharAt = _Private_IonConstants.makeUnicodeScalar(iCharAt, cCharAt);
                    }
                }
                throw new IllegalArgumentException("text is invalid UTF-16. It contains an unmatched high surrogate 0x" + Integer.toHexString(iCharAt) + " at index " + i);
            }
            if (_Private_IonConstants.isLowSurrogate(iCharAt)) {
                throw new IllegalArgumentException("text is invalid UTF-16. It contains an unmatched low surrogate 0x" + Integer.toHexString(iCharAt) + " at index " + i);
            }
            printCodePoint(appendable, iCharAt, escapeMode);
            i++;
        }
    }

    public static void printFloat(Appendable appendable, double d) throws IOException {
        _Private_IonTextAppender.forAppendable(appendable).printFloat(_Private_IonTextWriterBuilder.STANDARD, d);
    }
}
