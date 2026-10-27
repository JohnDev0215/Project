package defpackage;

/* JADX INFO: compiled from: PublicKeyCredentialDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class i72 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public int b;
    public byte[] c;
    public int[] d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public i72(int i) {
        super(32);
    }

    public static i72 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            i72 i72Var = new i72(e70Var.c(e).b);
            int iP = e70Var.p(8);
            i72Var.b = iP;
            if (!(iP >= 0 && iP <= 0)) {
                throw new q80("Invalid enum value.");
            }
            i72Var.b = iP;
            i72Var.c = e70Var.f(16, 0, -1);
            i72Var.d = e70Var.r(24, 0);
            int i = 0;
            while (true) {
                int[] iArr = i72Var.d;
                if (i >= iArr.length) {
                    return i72Var;
                }
                int i2 = iArr[i];
                if (!(i2 >= 0 && i2 <= 4)) {
                    throw new q80("Invalid enum value.");
                }
                i++;
            }
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.b(this.b, 8);
        qh0VarT.c(16, 0, -1, this.c);
        qh0VarT.o(this.d, 24, 0);
    }
}
