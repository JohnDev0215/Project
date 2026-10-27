package com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
public interface Supplier<T> {
    @CanIgnoreReturnValue
    T get();
}
