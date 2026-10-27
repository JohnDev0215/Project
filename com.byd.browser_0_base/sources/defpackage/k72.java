package defpackage;

/* JADX INFO: compiled from: PublicKeyCredentialRequestOptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class k72 extends hv2 {
    public static final t50[] r;
    public static final t50 s;
    public boolean b;
    public byte[] c;
    public qa3 d;
    public String e;
    public i72[] f;
    public int g;
    public String h;
    public nk[] i;
    public boolean j;
    public boolean k;
    public s32[] l;
    public boolean m;
    public byte[] n;
    public boolean o;
    public na2 p;
    public m90 q;

    static {
        t50 t50Var = new t50(96, 0);
        r = new t50[]{t50Var};
        s = t50Var;
    }

    public k72(int i) {
        super(96);
    }

    public static k72 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            k72 k72Var = new k72(e70Var.c(r).b);
            k72Var.b = e70Var.d(8, 0);
            k72Var.j = e70Var.d(8, 1);
            k72Var.k = e70Var.d(8, 2);
            k72Var.m = e70Var.d(8, 3);
            k72Var.o = e70Var.d(8, 4);
            int iP = e70Var.p(12);
            k72Var.g = iP;
            if (!(iP >= 0 && iP <= 2)) {
                throw new q80("Invalid enum value.");
            }
            k72Var.g = iP;
            k72Var.c = e70Var.f(16, 0, -1);
            k72Var.d = qa3.d(e70Var.t(24, true));
            k72Var.e = e70Var.w(32, false);
            e70 e70VarT = e70Var.t(40, false);
            int i = e70VarT.k(-1).b;
            k72Var.f = new i72[i];
            for (int i2 = 0; i2 < i; i2++) {
                k72Var.f[i2] = i72.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            k72Var.h = e70Var.w(48, true);
            e70 e70VarT2 = e70Var.t(56, false);
            int i3 = e70VarT2.k(-1).b;
            k72Var.i = new nk[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                k72Var.i[i4] = nk.d(C0173a.g(i4, 8, 8, e70VarT2, false));
            }
            e70 e70VarT3 = e70Var.t(64, false);
            int i5 = e70VarT3.k(-1).b;
            k72Var.l = new s32[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                k72Var.l[i6] = s32.d(C0173a.g(i6, 8, 8, e70VarT3, false));
            }
            k72Var.n = e70Var.f(72, 1, -1);
            k72Var.p = na2.d(e70Var.t(80, true));
            k72Var.q = m90.d(e70Var.t(88, true));
            return k72Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(s);
        qh0VarT.d(8, 0, this.b);
        qh0VarT.d(8, 1, this.j);
        qh0VarT.d(8, 2, this.k);
        qh0VarT.d(8, 3, this.m);
        qh0VarT.d(8, 4, this.o);
        qh0VarT.b(this.g, 12);
        qh0VarT.c(16, 0, -1, this.c);
        qh0VarT.k(this.d, 24, true);
        qh0VarT.m(this.e, 32, false);
        i72[] i72VarArr = this.f;
        if (i72VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(i72VarArr.length, 40);
            int i = 0;
            while (true) {
                i72[] i72VarArr2 = this.f;
                if (i >= i72VarArr2.length) {
                    break;
                }
                qh0VarR.k(i72VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(40, false);
        }
        qh0VarT.m(this.h, 48, true);
        nk[] nkVarArr = this.i;
        if (nkVarArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(nkVarArr.length, 56);
            int i2 = 0;
            while (true) {
                nk[] nkVarArr2 = this.i;
                if (i2 >= nkVarArr2.length) {
                    break;
                }
                qh0VarR2.k(nkVarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(56, false);
        }
        s32[] s32VarArr = this.l;
        if (s32VarArr != null) {
            qh0 qh0VarR3 = qh0VarT.r(s32VarArr.length, 64);
            int i3 = 0;
            while (true) {
                s32[] s32VarArr2 = this.l;
                if (i3 >= s32VarArr2.length) {
                    break;
                }
                qh0VarR3.k(s32VarArr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            qh0VarT.q(64, false);
        }
        qh0VarT.c(72, 1, -1, this.n);
        qh0VarT.k(this.p, 80, true);
        qh0VarT.k(this.q, 88, true);
    }
}
