package p;

/* JADX INFO: loaded from: classes.dex */
public final class h03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;
    public final i13 l;

    public /* synthetic */ h03(w23 w23Var, i13 i13Var, i13 i13Var2, int i) {
        this.c = i;
        this.e = w23Var;
        this.k = i13Var;
        this.l = i13Var2;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.l;
        i13 i13Var2 = this.k;
        i13 i13Var3 = this.e;
        switch (i) {
            case 0:
                return new com.google.android.play.core.assetpacks.a(((w23) i13Var3).b(), (g13) i13Var2.a(), (a23) i13Var.a());
            default:
                d33 d33Var = y3.c(((w23) i13Var3).b()) == null ? (d33) ((h13) h13.b(i13Var2)).a() : (d33) ((h13) h13.b(i13Var)).a();
                ci1.M(d33Var);
                return d33Var;
        }
    }
}
