package com.google.protobuf;

import defpackage.lk;

/* JADX INFO: compiled from: UnknownFieldSchema.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i1<T, B> {
    public abstract void a(int i, int i2, Object obj);

    public abstract void b(long j, int i, Object obj);

    public abstract void c(B b, int i, T t);

    public abstract void d(B b, int i, lk lkVar);

    public abstract void e(long j, int i, Object obj);

    public abstract B f(Object obj);

    public abstract T g(Object obj);

    public abstract int h(T t);

    public abstract int i(T t);

    public abstract void j(Object obj);

    public abstract T k(T t, T t2);

    public final boolean l(B b, z0 z0Var) throws d0 {
        int tag = z0Var.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            e(z0Var.L(), i, b);
            return true;
        }
        if (i2 == 1) {
            b(z0Var.c(), i, b);
            return true;
        }
        if (i2 == 2) {
            d(b, i, z0Var.D());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw d0.d();
            }
            a(i, z0Var.h(), b);
            return true;
        }
        B bM = m();
        int i3 = 4 | (i << 3);
        while (z0Var.y() != Integer.MAX_VALUE && l(bM, z0Var)) {
        }
        if (i3 != z0Var.getTag()) {
            throw d0.a();
        }
        c(b, i, q(bM));
        return true;
    }

    public abstract B m();

    public abstract void n(Object obj, B b);

    public abstract void o(Object obj, T t);

    public abstract boolean p(z0 z0Var);

    public abstract T q(B b);

    public abstract void r(Object obj, i iVar);

    public abstract void s(Object obj, i iVar);
}
