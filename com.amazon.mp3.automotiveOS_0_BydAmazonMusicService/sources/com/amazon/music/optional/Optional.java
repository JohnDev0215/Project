package com.amazon.music.optional;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;

    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional optional = (Optional) obj;
        T t = this.value;
        if (t == null) {
            return optional.value == null;
        }
        return t.equals(optional.value);
    }

    public int hashCode() {
        T t = this.value;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    private Optional() {
        this.value = null;
    }

    private Optional(T t) {
        t.getClass();
        this.value = t;
    }
}
