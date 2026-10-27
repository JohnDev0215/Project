package defpackage;

/* JADX INFO: compiled from: UrlResponseHead.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ak3 extends hv2 {
    public static final t50[] n;
    public static final t50 o;
    public oa3 b;
    public oa3 c;
    public wv0 d;
    public String e;
    public String f;
    public int g;
    public long h;
    public long i;
    public int j;
    public int k;
    public int l;
    public boolean m;

    static {
        t50 t50Var = new t50(248, 0);
        n = new t50[]{t50Var};
        o = t50Var;
    }

    public ak3(int i) {
        super(248);
        this.h = -1L;
        this.i = -1L;
        this.j = 3;
        this.k = 3;
        this.l = 2;
        this.m = true;
    }

    public static void d(e70 e70Var) {
        wv0 wv0Var;
        if (e70Var == null) {
            return;
        }
        e70Var.b();
        try {
            ak3 ak3Var = new ak3(e70Var.c(n).b);
            ak3Var.b = oa3.d(e70Var.t(8, false));
            ak3Var.c = oa3.d(e70Var.t(16, false));
            e70 e70VarT = e70Var.t(24, false);
            if (e70VarT == null) {
                wv0Var = null;
            } else {
                e70VarT.b();
                try {
                    wv0 wv0Var2 = new wv0(e70VarT.c(wv0.b).b);
                    e70VarT.a();
                    wv0Var = wv0Var2;
                } catch (Throwable th) {
                    e70VarT.a();
                    throw th;
                }
            }
            ak3Var.d = wv0Var;
            ak3Var.e = e70Var.w(32, false);
            ak3Var.f = e70Var.w(40, false);
            ak3Var.g = e70Var.p(48);
            throw new q80("Invalid enum value.");
        } catch (Throwable th2) {
            e70Var.a();
            throw th2;
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(o);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.k(this.d, 24, false);
        qh0VarT.m(this.e, 32, false);
        qh0VarT.m(this.f, 40, false);
        qh0VarT.b(this.g, 48);
        qh0VarT.d(54, 3, this.m);
        qh0VarT.f(56, this.h);
        qh0VarT.f(64, this.i);
        qh0VarT.k(null, 72, true);
        qh0VarT.k(null, 80, false);
        qh0VarT.b(0, 88);
        qh0VarT.b(0, 92);
        qh0VarT.m(null, 96, false);
        qh0VarT.k(null, 104, false);
        qh0VarT.b(this.j, 112);
        qh0VarT.b(this.k, 116);
        qh0VarT.b(0, 120);
        qh0VarT.b(0, 124);
        qh0VarT.k(null, 128, false);
        qh0VarT.q(136, false);
        qh0VarT.b(this.l, 144);
        qh0VarT.b(0, 148);
        qh0VarT.f(152, 0L);
        qh0VarT.m(null, 160, false);
        qh0VarT.k(null, 168, true);
        qh0VarT.q(176, false);
        qh0VarT.k(null, 184, true);
        qh0VarT.k(null, 192, false);
        qh0VarT.k(null, 200, false);
        qh0VarT.k(null, 208, true);
        qh0VarT.k(null, 216, true);
        qh0VarT.q(224, false);
        qh0VarT.k(null, 232, false);
        qh0VarT.k(null, 240, true);
    }
}
