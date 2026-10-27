package com.amplifyframework.util;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class Empty {
    private Empty() {
    }

    public static boolean check(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean check(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean check(@Nullable String str) {
        return str == null || str.length() == 0;
    }
}
