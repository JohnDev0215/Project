package com.amazon.avod.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AsinResolver {
    private static final Pattern ASIN_REGEX = Pattern.compile("([0-9][0-9]{8}[0-9X]|[A-Z][A-Z0-9]{9})");

    public static boolean isAsin(@Nullable String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = ASIN_REGEX.matcher(str);
        return matcher.matches() && matcher.groupCount() == 1;
    }
}
