package p;

/* JADX INFO: loaded from: classes.dex */
public final class u03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;
    public final i13 l;
    public final i13 m;

    public /* synthetic */ u03(i13 i13Var, i13 i13Var2, i13 i13Var3, i13 i13Var4, int i) {
        this.c = i;
        this.e = i13Var;
        this.k = i13Var2;
        this.l = i13Var3;
        this.m = i13Var4;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.m;
        i13 i13Var2 = this.l;
        i13 i13Var3 = this.k;
        i13 i13Var4 = this.e;
        switch (i) {
            case 0:
                return new j03(((w23) i13Var4).b(), (com.google.android.play.core.assetpacks.c) i13Var3.a(), (m23) i13Var2.a(), (b13) i13Var.a());
            case 1:
                Object objA = i13Var4.a();
                f13 f13VarB = h13.b(i13Var3);
                Object objA2 = i13Var2.a();
                h13.b(i13Var);
                return new com.google.android.play.core.assetpacks.j((com.google.android.play.core.assetpacks.c) objA, f13VarB, (g13) objA2);
            default:
                Object objA3 = i13Var4.a();
                Object objA4 = i13Var3.a();
                return new com.google.android.play.core.assetpacks.k((com.google.android.play.core.assetpacks.j) objA3, (com.google.android.play.core.assetpacks.c) objA4, (com.google.android.play.core.assetpacks.e) i13Var2.a(), (pz2) i13Var.a());
        }
    }
}
