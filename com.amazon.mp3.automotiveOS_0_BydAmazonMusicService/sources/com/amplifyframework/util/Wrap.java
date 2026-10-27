package com.amplifyframework.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.ModelIdentifier;

/* JADX INFO: loaded from: classes5.dex */
public final class Wrap {
    private Wrap() {
    }

    @Nullable
    public static String inBackticks(@Nullable String str) {
        if (Empty.check(str)) {
            return str;
        }
        return "`" + str + "`";
    }

    @Nullable
    public static String inSingleQuotes(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return "'" + str + "'";
    }

    @Nullable
    public static String inDoubleQuotes(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + str + ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR;
    }

    @Nullable
    public static String inBraces(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return "{" + str + "}";
    }

    public static String inPrettyBraces(@Nullable String str, @NonNull String str2, @NonNull String str3) {
        if (str == null) {
            return null;
        }
        return " " + inBraces("\n" + str2 + str3 + str + "\n" + str2);
    }

    @Nullable
    public static String inParentheses(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return "(" + str + ")";
    }
}
