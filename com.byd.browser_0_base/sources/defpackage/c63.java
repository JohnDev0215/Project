package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c63 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ g63 f;

    public /* synthetic */ c63(g63 g63Var, int i) {
        this.e = i;
        this.f = g63Var;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        g63 g63Var = this.f;
        switch (i) {
            case 0:
                return g63Var.B;
            default:
                return new wo1(g63Var.g, g63Var.j0.get());
        }
    }
}
