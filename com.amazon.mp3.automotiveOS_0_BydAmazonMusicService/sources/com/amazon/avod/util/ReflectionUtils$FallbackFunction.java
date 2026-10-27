package com.amazon.avod.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ReflectionUtils$FallbackFunction<F, T> {
    @Nullable
    T run(@Nonnull Object obj, @Nullable F f);
}
