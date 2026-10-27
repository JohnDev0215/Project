package com.google.common.base;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();

    @Override // com.google.common.base.Optional
    public boolean equals(@CheckForNull Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    @CheckForNull
    public T orNull() {
        return null;
    }

    public String toString() {
        return "Optional.absent()";
    }

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    @Override // com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public T or(T t) {
        return (T) Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        Preconditions.checkNotNull(function);
        return Optional.absent();
    }
}
