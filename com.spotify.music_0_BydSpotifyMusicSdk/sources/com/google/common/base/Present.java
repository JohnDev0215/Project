package com.google.common.base;

import p.oz0;

/* JADX INFO: loaded from: classes.dex */
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T reference;

    /* JADX WARN: Multi-variable type inference failed */
    public Present(Object obj) {
        this.reference = obj;
    }

    @Override // com.google.common.base.Optional
    public final Object c() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final boolean d() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public final boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public final Object f() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final Object g(Object obj) {
        if (obj != null) {
            return this.reference;
        }
        throw new NullPointerException("use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.common.base.Optional
    public final Object h() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public final Optional i(oz0 oz0Var) {
        Object objApply = oz0Var.apply(this.reference);
        if (objApply != null) {
            return new Present(objApply);
        }
        throw new NullPointerException("the Function passed to Optional.transform() must not return null.");
    }

    @Override // com.google.common.base.Optional
    public final String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
