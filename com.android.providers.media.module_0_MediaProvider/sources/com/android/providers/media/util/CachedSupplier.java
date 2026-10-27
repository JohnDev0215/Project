package com.android.providers.media.util;

import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class CachedSupplier<T> implements Supplier<T> {
    private volatile T cache;
    private final Supplier<T> wrapped;

    public CachedSupplier(Supplier<T> supplier) {
        this.wrapped = supplier;
    }

    @Override // java.util.function.Supplier
    public T get() {
        if (this.cache == null) {
            this.cache = this.wrapped.get();
        }
        return this.cache;
    }
}
