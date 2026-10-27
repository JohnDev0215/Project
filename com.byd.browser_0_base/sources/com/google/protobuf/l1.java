package com.google.protobuf;

import defpackage.lk;
import defpackage.nu;
import java.util.Arrays;

/* JADX INFO: compiled from: UnknownFieldSetLiteSchema.java */
/* JADX INFO: loaded from: classes.dex */
public final class l1 extends i1<k1, k1> {
    @Override // com.google.protobuf.i1
    public final void a(int i, int i2, Object obj) {
        ((k1) obj).b((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.protobuf.i1
    public final void b(long j, int i, Object obj) {
        ((k1) obj).b((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.protobuf.i1
    public final void c(k1 k1Var, int i, k1 k1Var2) {
        k1Var.b((i << 3) | 3, k1Var2);
    }

    @Override // com.google.protobuf.i1
    public final void d(k1 k1Var, int i, lk lkVar) {
        k1Var.b((i << 3) | 2, lkVar);
    }

    @Override // com.google.protobuf.i1
    public final void e(long j, int i, Object obj) {
        ((k1) obj).b((i << 3) | 0, Long.valueOf(j));
    }

    @Override // com.google.protobuf.i1
    public final k1 f(Object obj) {
        z zVar = (z) obj;
        k1 k1Var = zVar.unknownFields;
        if (k1Var != k1.f) {
            return k1Var;
        }
        k1 k1Var2 = new k1();
        zVar.unknownFields = k1Var2;
        return k1Var2;
    }

    @Override // com.google.protobuf.i1
    public final k1 g(Object obj) {
        return ((z) obj).unknownFields;
    }

    @Override // com.google.protobuf.i1
    public final int h(k1 k1Var) {
        return k1Var.a();
    }

    @Override // com.google.protobuf.i1
    public final int i(k1 k1Var) {
        k1 k1Var2 = k1Var;
        int i = k1Var2.d;
        if (i != -1) {
            return i;
        }
        int iK = 0;
        for (int i2 = 0; i2 < k1Var2.a; i2++) {
            int i3 = k1Var2.b[i2] >>> 3;
            iK += nu.K(3, (lk) k1Var2.c[i2]) + nu.j0(2, i3) + (nu.i0(1) * 2);
        }
        k1Var2.d = iK;
        return iK;
    }

    @Override // com.google.protobuf.i1
    public final void j(Object obj) {
        ((z) obj).unknownFields.e = false;
    }

    @Override // com.google.protobuf.i1
    public final k1 k(k1 k1Var, k1 k1Var2) {
        k1 k1Var3 = k1Var;
        k1 k1Var4 = k1Var2;
        if (k1Var4.equals(k1.f)) {
            return k1Var3;
        }
        int i = k1Var3.a + k1Var4.a;
        int[] iArrCopyOf = Arrays.copyOf(k1Var3.b, i);
        System.arraycopy(k1Var4.b, 0, iArrCopyOf, k1Var3.a, k1Var4.a);
        Object[] objArrCopyOf = Arrays.copyOf(k1Var3.c, i);
        System.arraycopy(k1Var4.c, 0, objArrCopyOf, k1Var3.a, k1Var4.a);
        return new k1(i, iArrCopyOf, objArrCopyOf, true);
    }

    @Override // com.google.protobuf.i1
    public final k1 m() {
        return new k1();
    }

    @Override // com.google.protobuf.i1
    public final void n(Object obj, k1 k1Var) {
        ((z) obj).unknownFields = k1Var;
    }

    @Override // com.google.protobuf.i1
    public final void o(Object obj, k1 k1Var) {
        ((z) obj).unknownFields = k1Var;
    }

    @Override // com.google.protobuf.i1
    public final boolean p(z0 z0Var) {
        return false;
    }

    @Override // com.google.protobuf.i1
    public final k1 q(k1 k1Var) {
        k1 k1Var2 = k1Var;
        k1Var2.e = false;
        return k1Var2;
    }

    @Override // com.google.protobuf.i1
    public final void r(Object obj, i iVar) {
        k1 k1Var = (k1) obj;
        k1Var.getClass();
        iVar.getClass();
        for (int i = 0; i < k1Var.a; i++) {
            iVar.o(k1Var.b[i] >>> 3, k1Var.c[i]);
        }
    }

    @Override // com.google.protobuf.i1
    public final void s(Object obj, i iVar) {
        ((k1) obj).c(iVar);
    }
}
