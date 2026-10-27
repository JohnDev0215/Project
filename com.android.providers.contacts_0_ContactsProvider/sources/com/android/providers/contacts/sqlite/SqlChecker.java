package com.android.providers.contacts.sqlite;

import android.util.ArraySet;
import android.util.Log;
import com.android.providers.contacts.AbstractContactsProvider;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SqlChecker {
    static final int OPTION_NONE = 0;
    static final int OPTION_TOKEN_ONLY = 1;
    private static final boolean VERBOSE_LOGGING = AbstractContactsProvider.VERBOSE_LOGGING;
    private final ArraySet<String> mInvalidTokens;

    private static boolean isAlpha(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_';
    }

    private static boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    public SqlChecker(List<String> list) {
        this.mInvalidTokens = new ArraySet<>(list.size());
        for (int size = list.size() - 1; size >= 0; size--) {
            this.mInvalidTokens.add(list.get(size).toLowerCase());
        }
        if (VERBOSE_LOGGING) {
            Log.d("SqlChecker", "Initialized with invalid tokens: " + list);
        }
    }

    private static boolean isAlNum(char c) {
        return isAlpha(c) || isNum(c);
    }

    private static boolean isAnyOf(char c, String str) {
        return str.indexOf(c) >= 0;
    }

    public static final class InvalidSqlException extends IllegalArgumentException {
        public InvalidSqlException(String str) {
            super(str);
        }
    }

    private static InvalidSqlException genException(String str, String str2) {
        throw new InvalidSqlException(str + " in '" + str2 + "'");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: throwIfContainsToken, reason: merged with bridge method [inline-methods] */
    public void lambda$ensureNoInvalidTokens$0$SqlChecker(String str, String str2) {
        String lowerCase = str.toLowerCase();
        if (this.mInvalidTokens.contains(lowerCase) || lowerCase.startsWith("x_")) {
            genException("Detected disallowed token: " + str, str2);
            throw null;
        }
    }

    public void ensureNoInvalidTokens(final String str) {
        findTokens(str, OPTION_NONE, new Consumer() { // from class: com.android.providers.contacts.sqlite.-$$Lambda$SqlChecker$17ylUN_C3H5h-emBM_Jfu0UiV_Q
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$ensureNoInvalidTokens$0$SqlChecker(str, (String) obj);
            }
        });
    }

    public void ensureSingleTokenOnly(final String str) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        findTokens(str, OPTION_TOKEN_ONLY, new Consumer() { // from class: com.android.providers.contacts.sqlite.-$$Lambda$SqlChecker$Fnfikt5w2dEHBYV6KVXiNGb9o_8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$ensureSingleTokenOnly$1$SqlChecker(atomicBoolean, str, (String) obj);
            }
        });
        if (atomicBoolean.get()) {
            return;
        }
        genException("Token not found", str);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$ensureSingleTokenOnly$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$ensureSingleTokenOnly$1$SqlChecker(AtomicBoolean atomicBoolean, String str, String str2) {
        if (atomicBoolean.get()) {
            genException("Multiple tokens detected", str);
            throw null;
        }
        atomicBoolean.set(true);
        lambda$ensureNoInvalidTokens$0(str2, str);
    }

    private static char peek(String str, int i) {
        if (i < str.length()) {
            return str.charAt(i);
        }
        return (char) 0;
    }

    static void findTokens(String str, int i, Consumer<String> consumer) {
        if (str == null) {
            return;
        }
        int iIndexOf = OPTION_NONE;
        int length = str.length();
        while (iIndexOf < length) {
            char cPeek = peek(str, iIndexOf);
            if (isAlpha(cPeek)) {
                int i2 = iIndexOf + OPTION_TOKEN_ONLY;
                while (isAlNum(peek(str, i2))) {
                    i2 += OPTION_TOKEN_ONLY;
                }
                consumer.accept(str.substring(iIndexOf, i2));
                iIndexOf = i2;
            } else if (isAnyOf(cPeek, "'\"`")) {
                int i3 = iIndexOf + OPTION_TOKEN_ONLY;
                int i4 = i3;
                while (true) {
                    int iIndexOf2 = str.indexOf(cPeek, i4);
                    if (iIndexOf2 < 0) {
                        genException("Unterminated quote", str);
                        throw null;
                    }
                    int i5 = iIndexOf2 + OPTION_TOKEN_ONLY;
                    if (peek(str, i5) != cPeek) {
                        if (cPeek != '\'') {
                            String strSubstring = str.substring(i3, iIndexOf2);
                            if (strSubstring.indexOf(cPeek) >= 0) {
                                strSubstring = strSubstring.replaceAll(String.valueOf(cPeek) + cPeek, String.valueOf(cPeek));
                            }
                            consumer.accept(strSubstring);
                        } else {
                            i &= OPTION_TOKEN_ONLY;
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
                int i6 = iIndexOf + OPTION_TOKEN_ONLY;
                int iIndexOf3 = str.indexOf(93, i6);
                if (iIndexOf3 < 0) {
                    genException("Unterminated quote", str);
                    throw null;
                }
                int i7 = iIndexOf3 + OPTION_TOKEN_ONLY;
                consumer.accept(str.substring(i6, iIndexOf3));
                iIndexOf = i7;
            } else {
                i &= OPTION_TOKEN_ONLY;
                if (i != 0) {
                    genException("Non-token detected", str);
                    throw null;
                }
                if (cPeek == '-' && peek(str, iIndexOf + OPTION_TOKEN_ONLY) == '-') {
                    iIndexOf = str.indexOf(10, iIndexOf + 2);
                    if (iIndexOf < 0) {
                        genException("Unterminated comment", str);
                        throw null;
                    }
                } else if (cPeek == '/' && peek(str, iIndexOf + OPTION_TOKEN_ONLY) == '*') {
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
                iIndexOf += OPTION_TOKEN_ONLY;
            }
        }
    }
}
