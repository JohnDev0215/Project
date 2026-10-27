package com.google.common.base;

import p.oz0;

/* JADX INFO: loaded from: classes.dex */
final class Absent<T> extends Optional<T> {
    public static final Absent c = new Absent();
    private static final long serialVersionUID = 0;

    private Absent() {
    }

    private Object readResolve() {
        return c;
    }

    @Override // com.google.common.base.Optional
    public final Object c() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public final boolean d() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.google.common.base.Optional
    public final Object f() {
        Double dValueOf = Double.valueOf(-1.0d);
        if (dValueOf != null) {
            return dValueOf;
        }
        throw new NullPointerException("use Optional.orNull() instead of a Supplier that returns null");
    }

    @Override // com.google.common.base.Optional
    public final Object g(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.common.base.Optional
    public final Object h() {
        return null;
    }

    @Override // com.google.common.base.Optional
    public final int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public final Optional i(oz0 oz0Var) {
        return c;
    }

    @Override // com.google.common.base.Optional
    public final String toString() {
        return "Optional.absent()";
    }
}
