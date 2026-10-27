package com.facebook.infer.annotation;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Assertions {
    public static <T> T assumeNotNull(@Nullable T t) {
        return t;
    }

    public static <T> T assertNotNull(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new AssertionError(str);
    }

    public static <T> T assertNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static void assertCondition(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public static void assertCondition(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
