package p;

/* JADX INFO: loaded from: classes.dex */
public final class c13 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;

    public /* synthetic */ c13(i13 i13Var, int i) {
        this.c = i;
        this.e = i13Var;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.e;
        switch (i) {
            case 0:
                return new b13(((w23) i13Var).b());
            default:
                return new com.google.android.play.core.assetpacks.r((com.google.android.play.core.assetpacks.c) i13Var.a());
        }
    }
}
