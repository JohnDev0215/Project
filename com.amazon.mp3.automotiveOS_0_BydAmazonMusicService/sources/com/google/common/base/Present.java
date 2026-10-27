package com.google.common.base;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class Present<T> extends Optional<T> {
    private final T reference;

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    Present(T t) {
        this.reference = t;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T or(T t) {
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    @Override // com.google.common.base.Optional
    public boolean equals(@CheckForNull Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.reference);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }
}
