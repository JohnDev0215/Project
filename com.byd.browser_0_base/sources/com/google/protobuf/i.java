package com.google.protobuf;

import defpackage.lk;
import defpackage.nu;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: compiled from: CodedOutputStreamWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public final nu a;

    public i(nu nuVar) {
        Charset charset = c0.a;
        if (nuVar == null) {
            throw new NullPointerException("output");
        }
        this.a = nuVar;
        nuVar.k = this;
    }

    public final void a(int i, boolean z) {
        this.a.p0(i, z);
    }

    public final void b(int i, lk lkVar) {
        this.a.r0(i, lkVar);
    }

    public final void c(int i, double d) {
        nu nuVar = this.a;
        nuVar.getClass();
        nuVar.w0(i, Double.doubleToRawLongBits(d));
    }

    public final void d(int i, int i2) {
        this.a.z0(i, i2);
    }

    public final void e(int i, int i2) {
        this.a.u0(i, i2);
    }

    public final void f(int i, List<Integer> list, boolean z) {
        nu nuVar = this.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.u0(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).intValue();
            boolean z2 = nu.l;
            i3 += 4;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.v0(list.get(i2).intValue());
            i2++;
        }
    }

    public final void g(int i, long j) {
        this.a.w0(i, j);
    }

    public final void h(int i, List<Long> list, boolean z) {
        nu nuVar = this.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.w0(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).longValue();
            boolean z2 = nu.l;
            i3 += 8;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.x0(list.get(i2).longValue());
            i2++;
        }
    }

    public final void i(float f, int i) {
        nu nuVar = this.a;
        nuVar.getClass();
        nuVar.u0(i, Float.floatToRawIntBits(f));
    }

    public final void j(int i, b1 b1Var, Object obj) {
        nu nuVar = this.a;
        nuVar.M0(i, 3);
        b1Var.b((r0) obj, nuVar.k);
        nuVar.M0(i, 4);
    }

    public final void k(int i, int i2) {
        this.a.z0(i, i2);
    }

    public final void l(int i, long j) {
        this.a.P0(i, j);
    }

    public final void m(int i, List<Long> list, boolean z) {
        nu nuVar = this.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.P0(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iN0 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iN0 += nu.n0(list.get(i3).longValue());
        }
        nuVar.O0(iN0);
        while (i2 < list.size()) {
            nuVar.Q0(list.get(i2).longValue());
            i2++;
        }
    }

    public final void n(int i, b1 b1Var, Object obj) {
        this.a.E0(i, (r0) obj, b1Var);
    }

    public final void o(int i, Object obj) {
        boolean z = obj instanceof lk;
        nu nuVar = this.a;
        if (z) {
            nuVar.J0(i, (lk) obj);
        } else {
            nuVar.I0(i, (r0) obj);
        }
    }

    public final void p(int i, int i2) {
        this.a.u0(i, i2);
    }

    public final void q(int i, long j) {
        this.a.w0(i, j);
    }

    public final void r(int i, int i2) {
        nu nuVar = this.a;
        nuVar.N0(i, (i2 >> 31) ^ (i2 << 1));
    }

    public final void s(int i, long j) {
        nu nuVar = this.a;
        nuVar.P0(i, (j >> 63) ^ (j << 1));
    }

    public final void t(int i, int i2) {
        this.a.N0(i, i2);
    }

    public final void u(int i, long j) {
        this.a.P0(i, j);
    }
}
