package p;

/* JADX INFO: loaded from: classes.dex */
public final class y03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;
    public final i13 l;
    public final i13 m;
    public final i13 n;

    public /* synthetic */ y03(i13 i13Var, w03 w03Var, i13 i13Var2, i13 i13Var3, i13 i13Var4, int i) {
        this.c = i;
        this.e = i13Var;
        this.k = w03Var;
        this.l = i13Var2;
        this.m = i13Var3;
        this.n = i13Var4;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.n;
        i13 i13Var2 = this.m;
        i13 i13Var3 = this.l;
        i13 i13Var4 = this.k;
        i13 i13Var5 = this.e;
        switch (i) {
            case 0:
                Object objA = i13Var5.a();
                return new com.google.android.play.core.assetpacks.g((com.google.android.play.core.assetpacks.c) objA, h13.b(i13Var4), h13.b(i13Var3), (g13) i13Var2.a(), (a23) i13Var.a());
            default:
                Object objA2 = i13Var5.a();
                return new com.google.android.play.core.assetpacks.o((com.google.android.play.core.assetpacks.c) objA2, h13.b(i13Var4), (com.google.android.play.core.assetpacks.j) i13Var3.a(), h13.b(i13Var2), (g13) i13Var.a());
        }
    }
}
