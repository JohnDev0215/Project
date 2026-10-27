package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

/* JADX INFO: compiled from: DetectionResult.java */
/* JADX INFO: loaded from: classes.dex */
final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f4464a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f[] f4465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f4466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4467d;

    e(a aVar, c cVar) {
        this.f4464a = aVar;
        int iA = aVar.a();
        this.f4467d = iA;
        this.f4466c = cVar;
        this.f4465b = new f[iA + 2];
    }

    private void a(f fVar) {
        if (fVar != null) {
            ((g) fVar).g(this.f4464a);
        }
    }

    private static boolean b(d dVar, d dVar2) {
        if (dVar2 == null || !dVar2.g() || dVar2.a() != dVar.a()) {
            return false;
        }
        dVar.i(dVar2.c());
        return true;
    }

    private static int c(int i, int i2, d dVar) {
        if (dVar == null || dVar.g()) {
            return i2;
        }
        if (!dVar.h(i)) {
            return i2 + 1;
        }
        dVar.i(i);
        return 0;
    }

    private int d() {
        int iF = f();
        if (iF == 0) {
            return 0;
        }
        for (int i = 1; i < this.f4467d + 1; i++) {
            d[] dVarArrD = this.f4465b[i].d();
            for (int i2 = 0; i2 < dVarArrD.length; i2++) {
                if (dVarArrD[i2] != null && !dVarArrD[i2].g()) {
                    e(i, i2, dVarArrD);
                }
            }
        }
        return iF;
    }

    private void e(int i, int i2, d[] dVarArr) {
        d dVar = dVarArr[i2];
        d[] dVarArrD = this.f4465b[i - 1].d();
        f[] fVarArr = this.f4465b;
        int i3 = i + 1;
        d[] dVarArrD2 = fVarArr[i3] != null ? fVarArr[i3].d() : dVarArrD;
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = dVarArrD[i2];
        dVarArr2[3] = dVarArrD2[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            dVarArr2[0] = dVarArr[i4];
            dVarArr2[4] = dVarArrD[i4];
            dVarArr2[5] = dVarArrD2[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            dVarArr2[8] = dVarArr[i5];
            dVarArr2[10] = dVarArrD[i5];
            dVarArr2[11] = dVarArrD2[i5];
        }
        if (i2 < dVarArr.length - 1) {
            int i6 = i2 + 1;
            dVarArr2[1] = dVarArr[i6];
            dVarArr2[6] = dVarArrD[i6];
            dVarArr2[7] = dVarArrD2[i6];
        }
        if (i2 < dVarArr.length - 2) {
            int i7 = i2 + 2;
            dVarArr2[9] = dVarArr[i7];
            dVarArr2[12] = dVarArrD[i7];
            dVarArr2[13] = dVarArrD2[i7];
        }
        for (int i8 = 0; i8 < 14 && !b(dVar, dVarArr2[i8]); i8++) {
        }
    }

    private int f() {
        g();
        return h() + i();
    }

    private void g() {
        f[] fVarArr = this.f4465b;
        if (fVarArr[0] == null || fVarArr[this.f4467d + 1] == null) {
            return;
        }
        d[] dVarArrD = fVarArr[0].d();
        d[] dVarArrD2 = this.f4465b[this.f4467d + 1].d();
        for (int i = 0; i < dVarArrD.length; i++) {
            if (dVarArrD[i] != null && dVarArrD2[i] != null && dVarArrD[i].c() == dVarArrD2[i].c()) {
                for (int i2 = 1; i2 <= this.f4467d; i2++) {
                    d dVar = this.f4465b[i2].d()[i];
                    if (dVar != null) {
                        dVar.i(dVarArrD[i].c());
                        if (!dVar.g()) {
                            this.f4465b[i2].d()[i] = null;
                        }
                    }
                }
            }
        }
    }

    private int h() {
        f[] fVarArr = this.f4465b;
        if (fVarArr[0] == null) {
            return 0;
        }
        d[] dVarArrD = fVarArr[0].d();
        int i = 0;
        for (int i2 = 0; i2 < dVarArrD.length; i2++) {
            if (dVarArrD[i2] != null) {
                int iC = dVarArrD[i2].c();
                int iC2 = 0;
                for (int i3 = 1; i3 < this.f4467d + 1 && iC2 < 2; i3++) {
                    d dVar = this.f4465b[i3].d()[i2];
                    if (dVar != null) {
                        iC2 = c(iC, iC2, dVar);
                        if (!dVar.g()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    private int i() {
        f[] fVarArr = this.f4465b;
        int i = this.f4467d;
        if (fVarArr[i + 1] == null) {
            return 0;
        }
        d[] dVarArrD = fVarArr[i + 1].d();
        int i2 = 0;
        for (int i3 = 0; i3 < dVarArrD.length; i3++) {
            if (dVarArrD[i3] != null) {
                int iC = dVarArrD[i3].c();
                int iC2 = 0;
                for (int i4 = this.f4467d + 1; i4 > 0 && iC2 < 2; i4--) {
                    d dVar = this.f4465b[i4].d()[i3];
                    if (dVar != null) {
                        iC2 = c(iC, iC2, dVar);
                        if (!dVar.g()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    int j() {
        return this.f4467d;
    }

    int k() {
        return this.f4464a.b();
    }

    int l() {
        return this.f4464a.c();
    }

    c m() {
        return this.f4466c;
    }

    f n(int i) {
        return this.f4465b[i];
    }

    f[] o() {
        a(this.f4465b[0]);
        a(this.f4465b[this.f4467d + 1]);
        int i = 928;
        while (true) {
            int iD = d();
            if (iD <= 0 || iD >= i) {
                break;
            }
            i = iD;
        }
        return this.f4465b;
    }

    void p(c cVar) {
        this.f4466c = cVar;
    }

    void q(int i, f fVar) {
        this.f4465b[i] = fVar;
    }

    public String toString() {
        f[] fVarArr = this.f4465b;
        f fVar = fVarArr[0];
        if (fVar == null) {
            fVar = fVarArr[this.f4467d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < fVar.d().length; i++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(i));
                for (int i2 = 0; i2 < this.f4467d + 2; i2++) {
                    f[] fVarArr2 = this.f4465b;
                    if (fVarArr2[i2] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        d dVar = fVarArr2[i2].d()[i];
                        if (dVar == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}
