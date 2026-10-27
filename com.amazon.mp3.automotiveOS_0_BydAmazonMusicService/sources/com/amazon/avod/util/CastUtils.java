package com.amazon.avod.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class CastUtils {
    private CastUtils() {
    }

    @Nullable
    public static <T> T castTo(@Nullable Object obj, @Nonnull Class<T> cls) {
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }
}
