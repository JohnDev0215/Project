package com.google.common.base;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract T get();

    public abstract boolean isPresent();

    public abstract T or(T t);

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T t) {
        Preconditions.checkNotNull(t);
        return new Present(t);
    }

    public static <T> Optional<T> fromNullable(T t) {
        return t == null ? absent() : new Present(t);
    }

    Optional() {
    }
}
