package com.android.providers.media.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteTokenizer {
    public static final int OPTION_NONE = 0;
    public static final int OPTION_TOKEN_ONLY = 1;

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

    public static List<String> tokenize(String str, int i) {
        final ArrayList arrayList = new ArrayList();
        tokenize(str, i, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$I1A8dniBuRdoMGKE4fgVMPvl9YM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((String) obj);
            }
        });
        return arrayList;
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
                        throw genException("Unterminated quote", str);
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
                                throw genException("Non-token detected", str);
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
                    throw genException("Unterminated quote", str);
                }
                consumer.accept(str.substring(i6, iIndexOf3));
                iIndexOf = iIndexOf3 + 1;
            } else {
                i &= 1;
                if (i != 0) {
                    throw genException("Non-token detected", str);
                }
                if (cPeek == '-' && peek(str, iIndexOf + 1) == '-') {
                    iIndexOf = str.indexOf(10, iIndexOf + 2);
                    if (iIndexOf < 0) {
                        throw genException("Unterminated comment", str);
                    }
                } else if (cPeek == '/' && peek(str, iIndexOf + 1) == '*') {
                    int iIndexOf4 = str.indexOf("*/", iIndexOf + 2);
                    if (iIndexOf4 < 0) {
                        throw genException("Unterminated comment", str);
                    }
                    iIndexOf = iIndexOf4 + 2;
                } else if (cPeek == ';') {
                    throw genException("Semicolon is not allowed", str);
                }
                iIndexOf++;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:434:0x06b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isKeyword(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 2586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.SQLiteTokenizer.isKeyword(java.lang.String):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:104:0x018c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isFunction(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.SQLiteTokenizer.isFunction(java.lang.String):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isType(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.SQLiteTokenizer.isType(java.lang.String):boolean");
    }
}
