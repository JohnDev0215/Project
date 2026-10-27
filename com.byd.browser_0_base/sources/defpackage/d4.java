package defpackage;

/* JADX INFO: compiled from: AddressErrors.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d4 extends hv2 {
    public static final t50[] l;
    public static final t50 m;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;

    static {
        t50 t50Var = new t50(88, 0);
        l = new t50[]{t50Var};
        m = t50Var;
    }

    public d4(int i) {
        super(88);
    }

    public static d4 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            d4 d4Var = new d4(e70Var.c(l).b);
            d4Var.b = e70Var.w(8, false);
            d4Var.c = e70Var.w(16, false);
            d4Var.d = e70Var.w(24, false);
            d4Var.e = e70Var.w(32, false);
            d4Var.f = e70Var.w(40, false);
            d4Var.g = e70Var.w(48, false);
            d4Var.h = e70Var.w(56, false);
            d4Var.i = e70Var.w(64, false);
            d4Var.j = e70Var.w(72, false);
            d4Var.k = e70Var.w(80, false);
            return d4Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(m);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.m(this.d, 24, false);
        qh0VarT.m(this.e, 32, false);
        qh0VarT.m(this.f, 40, false);
        qh0VarT.m(this.g, 48, false);
        qh0VarT.m(this.h, 56, false);
        qh0VarT.m(this.i, 64, false);
        qh0VarT.m(this.j, 72, false);
        qh0VarT.m(this.k, 80, false);
    }
}
