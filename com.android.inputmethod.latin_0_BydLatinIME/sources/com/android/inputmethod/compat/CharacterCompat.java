package com.android.inputmethod.compat;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class CharacterCompat {
    private static final Method METHOD_isAlphabetic = CompatUtils.getMethod(Character.class, "isAlphabetic", Integer.TYPE);

    private CharacterCompat() {
    }

    public static boolean isAlphabetic(final int code) {
        Method method = METHOD_isAlphabetic;
        if (method != null) {
            return ((Boolean) CompatUtils.invoke(null, false, method, Integer.valueOf(code))).booleanValue();
        }
        int type = Character.getType(code);
        return type == 1 || type == 2 || type == 3 || type == 4 || type == 5 || type == 10;
    }
}
