package j8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17265n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public b0 f17266p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17267q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17268r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public c0 f17269s;

    public static a0 g() {
        a0 a0Var = new a0();
        a0Var.f17266p = b0.ERROR;
        a0Var.f17269s = c0.LANGUAGE_VERSION;
        return a0Var;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        d0 d0VarF = f();
        if (d0VarF.b()) {
            return d0VarF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        a0 a0VarG = g();
        a0VarG.h(f());
        return a0VarG;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r2, p8.C2132g r3) throws java.lang.Throwable {
        /*
            r1 = this;
            r3 = 0
            j8.a r0 = j8.d0.f17315w     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.d0 r0 = new j8.d0     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            p8.a r0 = r2.l     // Catch: java.lang.Throwable -> Lf
            j8.d0 r0 = (j8.d0) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.h(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.a0.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((d0) abstractC2139n);
        return this;
    }

    public final d0 f() {
        d0 d0Var = new d0(this);
        int i7 = this.f17264m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        d0Var.f17317n = this.f17265n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        d0Var.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        d0Var.f17318p = this.f17266p;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        d0Var.f17319q = this.f17267q;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        d0Var.f17320r = this.f17268r;
        if ((i7 & 32) == 32) {
            i10 |= 32;
        }
        d0Var.f17321s = this.f17269s;
        d0Var.f17316m = i10;
        return d0Var;
    }

    public final void h(d0 d0Var) {
        if (d0Var == d0.f17314v) {
            return;
        }
        int i7 = d0Var.f17316m;
        if ((i7 & 1) == 1) {
            int i10 = d0Var.f17317n;
            this.f17264m = 1 | this.f17264m;
            this.f17265n = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = d0Var.o;
            this.f17264m = 2 | this.f17264m;
            this.o = i11;
        }
        if ((i7 & 4) == 4) {
            b0 b0Var = d0Var.f17318p;
            b0Var.getClass();
            this.f17264m = 4 | this.f17264m;
            this.f17266p = b0Var;
        }
        int i12 = d0Var.f17316m;
        if ((i12 & 8) == 8) {
            int i13 = d0Var.f17319q;
            this.f17264m = 8 | this.f17264m;
            this.f17267q = i13;
        }
        if ((i12 & 16) == 16) {
            int i14 = d0Var.f17320r;
            this.f17264m = 16 | this.f17264m;
            this.f17268r = i14;
        }
        if ((i12 & 32) == 32) {
            c0 c0Var = d0Var.f17321s;
            c0Var.getClass();
            this.f17264m = 32 | this.f17264m;
            this.f17269s = c0Var;
        }
        this.l = this.l.d(d0Var.l);
    }
}
