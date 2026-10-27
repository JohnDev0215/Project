package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

/* JADX INFO: compiled from: DetectionResultColumn.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f4468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d[] f4469b;

    f(c cVar) {
        this.f4468a = new c(cVar);
        this.f4469b = new d[(cVar.e() - cVar.g()) + 1];
    }

    final c a() {
        return this.f4468a;
    }

    final d b(int i) {
        return this.f4469b[e(i)];
    }

    final d c(int i) {
        d dVar;
        d dVar2;
        d dVarB = b(i);
        if (dVarB != null) {
            return dVarB;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int iE = e(i) - i2;
            if (iE >= 0 && (dVar2 = this.f4469b[iE]) != null) {
                return dVar2;
            }
            int iE2 = e(i) + i2;
            d[] dVarArr = this.f4469b;
            if (iE2 < dVarArr.length && (dVar = dVarArr[iE2]) != null) {
                return dVar;
            }
        }
        return null;
    }

    final d[] d() {
        return this.f4469b;
    }

    final int e(int i) {
        return i - this.f4468a.g();
    }

    final void f(int i, d dVar) {
        this.f4469b[e(i)] = dVar;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        try {
            int i = 0;
            for (d dVar : this.f4469b) {
                if (dVar == null) {
                    formatter.format("%3d:    |   %n", Integer.valueOf(i));
                    i++;
                } else {
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                    i++;
                }
            }
            String string = formatter.toString();
            formatter.close();
            return string;
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
}
