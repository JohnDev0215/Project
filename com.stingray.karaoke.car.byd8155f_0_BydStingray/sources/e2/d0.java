package e2;

import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends f0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d5.d0 f14064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d5.d0 f14065f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f14066g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int[] f14067h;

    public d0(d5.d0 d0Var, d5.d0 d0Var2, int[] iArr) {
        AbstractC1346c.c(d0Var.o == iArr.length);
        this.f14064e = d0Var;
        this.f14065f = d0Var2;
        this.f14066g = iArr;
        this.f14067h = new int[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            this.f14067h[iArr[i7]] = i7;
        }
    }

    @Override // e2.f0
    public final int a(boolean z6) {
        if (p()) {
            return -1;
        }
        if (z6) {
            return this.f14066g[0];
        }
        return 0;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // e2.f0
    public final int c(boolean z6) {
        if (p()) {
            return -1;
        }
        d5.d0 d0Var = this.f14064e;
        if (!z6) {
            return d0Var.o - 1;
        }
        return this.f14066g[d0Var.o - 1];
    }

    @Override // e2.f0
    public final int e(int i7, int i10, boolean z6) {
        if (i10 == 1) {
            return i7;
        }
        if (i7 == c(z6)) {
            if (i10 == 2) {
                return a(z6);
            }
            return -1;
        }
        if (!z6) {
            return i7 + 1;
        }
        return this.f14066g[this.f14067h[i7] + 1];
    }

    @Override // e2.f0
    public final c0 f(int i7, c0 c0Var, boolean z6) {
        c0 c0Var2 = (c0) this.f14065f.get(i7);
        c0Var.i(c0Var2.f14059a, c0Var2.f14060b, c0Var2.c, c0Var2.d, c0Var2.f14061e, c0Var2.f14063g, c0Var2.f14062f);
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return this.f14065f.o;
    }

    @Override // e2.f0
    public final int k(int i7, int i10, boolean z6) {
        if (i10 == 1) {
            return i7;
        }
        if (i7 == a(z6)) {
            if (i10 == 2) {
                return c(z6);
            }
            return -1;
        }
        if (!z6) {
            return i7 - 1;
        }
        return this.f14066g[this.f14067h[i7] - 1];
    }

    @Override // e2.f0
    public final Object l(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // e2.f0
    public final e0 m(int i7, e0 e0Var, long j3) {
        e0 e0Var2 = (e0) this.f14064e.get(i7);
        e0Var.b(e0Var2.f14085a, e0Var2.c, e0Var2.d, e0Var2.f14087e, e0Var2.f14088f, e0Var2.f14089g, e0Var2.f14090h, e0Var2.f14091i, e0Var2.f14092j, e0Var2.l, e0Var2.f14094m, e0Var2.f14095n, e0Var2.o, e0Var2.f14096p);
        e0Var.f14093k = e0Var2.f14093k;
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return this.f14064e.o;
    }
}
