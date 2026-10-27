package com.google.common.base;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class Optional<T> implements Serializable {
    public abstract boolean equals(@CheckForNull Object obj);

    public abstract T get();

    public abstract boolean isPresent();

    public abstract T or(T t);

    @CheckForNull
    public abstract T orNull();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T t) {
        return new Present(Preconditions.checkNotNull(t));
    }

    public static <T> Optional<T> fromNullable(@CheckForNull T t) {
        return t == null ? absent() : new Present(t);
    }

    Optional() {
    }
}
