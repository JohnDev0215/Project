package p;

/* JADX INFO: loaded from: classes.dex */
public final class c23 implements i13 {
    public final /* synthetic */ int c;
    public final i13 e;

    public /* synthetic */ c23(w23 w23Var, int i) {
        this.c = i;
        this.e = w23Var;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        i13 i13Var = this.e;
        switch (i) {
            case 0:
                return new b23(((w23) i13Var).b());
            default:
                return y3.c(((w23) i13Var).b());
        }
    }
}
