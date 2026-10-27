package p;

/* JADX INFO: loaded from: classes.dex */
public final class m03 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;
    public final i13 k;

    public /* synthetic */ m03(i13 i13Var, i13 i13Var2, int i) {
        this.c = i;
        this.e = i13Var;
        this.k = i13Var2;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.k;
        i13 i13Var2 = this.e;
        switch (i) {
            case 0:
                return new com.google.android.play.core.assetpacks.c(((w23) i13Var2).b(), (b23) i13Var.a());
            default:
                return new com.google.android.play.core.assetpacks.n((com.google.android.play.core.assetpacks.c) i13Var2.a(), h13.b(i13Var));
        }
    }
}
