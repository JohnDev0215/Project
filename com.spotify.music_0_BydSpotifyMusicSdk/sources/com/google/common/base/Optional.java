package com.google.common.base;

import java.io.Serializable;
import p.oz0;

/* JADX INFO: loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public static Optional a() {
        return Absent.c;
    }

    public static Optional b(Object obj) {
        return obj == null ? Absent.c : new Present(obj);
    }

    public static Optional e(Object obj) {
        obj.getClass();
        return new Present(obj);
    }

    public abstract Object c();

    public abstract boolean d();

    public abstract boolean equals(Object obj);

    public abstract Object f();

    public abstract Object g(Object obj);

    public abstract Object h();

    public abstract int hashCode();

    public abstract Optional i(oz0 oz0Var);

    public abstract String toString();
}
