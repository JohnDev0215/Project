package com.android.providers.telephony;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteTokenizer {
    private static boolean isAlpha(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_';
    }

    private static boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isAlNum(char c) {
        return isAlpha(c) || isNum(c);
    }

    private static boolean isAnyOf(char c, String str) {
        return str.indexOf(c) >= 0;
    }

    private static IllegalArgumentException genException(String str, String str2) {
        throw new IllegalArgumentException(str + " in '" + str2 + "'");
    }

    private static char peek(String str, int i) {
        if (i < str.length()) {
            return str.charAt(i);
        }
        return (char) 0;
    }

    public static void tokenize(String str, int i, Consumer<String> consumer) {
        if (str == null) {
            return;
        }
        int iIndexOf = 0;
        int length = str.length();
        while (iIndexOf < length) {
            char cPeek = peek(str, iIndexOf);
            if (isAlpha(cPeek)) {
                int i2 = iIndexOf + 1;
                while (isAlNum(peek(str, i2))) {
                    i2++;
                }
                consumer.accept(str.substring(iIndexOf, i2));
                iIndexOf = i2;
            } else if (isAnyOf(cPeek, "'\"`")) {
                int i3 = iIndexOf + 1;
                int i4 = i3;
                while (true) {
                    int iIndexOf2 = str.indexOf(cPeek, i4);
                    if (iIndexOf2 < 0) {
                        genException("Unterminated quote", str);
                        throw null;
                    }
                    int i5 = iIndexOf2 + 1;
                    if (peek(str, i5) != cPeek) {
                        if (cPeek != '\'') {
                            String strSubstring = str.substring(i3, iIndexOf2);
                            if (strSubstring.indexOf(cPeek) >= 0) {
                                strSubstring = strSubstring.replaceAll(String.valueOf(cPeek) + cPeek, String.valueOf(cPeek));
                            }
                            consumer.accept(strSubstring);
                        } else {
                            i &= 1;
                            if (i != 0) {
                                genException("Non-token detected", str);
                                throw null;
                            }
                        }
                        iIndexOf = i5;
                    } else {
                        i4 = iIndexOf2 + 2;
                    }
                }
            } else if (cPeek == '[') {
                int i6 = iIndexOf + 1;
                int iIndexOf3 = str.indexOf(93, i6);
                if (iIndexOf3 < 0) {
                    genException("Unterminated quote", str);
                    throw null;
                }
                consumer.accept(str.substring(i6, iIndexOf3));
                iIndexOf = iIndexOf3 + 1;
            } else {
                i &= 1;
                if (i != 0) {
                    genException("Non-token detected", str);
                    throw null;
                }
                if (cPeek == '-' && peek(str, iIndexOf + 1) == '-') {
                    iIndexOf = str.indexOf(10, iIndexOf + 2);
                    if (iIndexOf < 0) {
                        genException("Unterminated comment", str);
                        throw null;
                    }
                } else if (cPeek == '/' && peek(str, iIndexOf + 1) == '*') {
                    int iIndexOf4 = str.indexOf("*/", iIndexOf + 2);
                    if (iIndexOf4 < 0) {
                        genException("Unterminated comment", str);
                        throw null;
                    }
                    iIndexOf = iIndexOf4 + 2;
                } else if (cPeek == ';') {
                    genException("Semicolon is not allowed", str);
                    throw null;
                }
                iIndexOf++;
            }
        }
    }
}
