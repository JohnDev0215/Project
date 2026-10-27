package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bs implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ nc2 f;

    public /* synthetic */ bs(nc2 nc2Var, int i) {
        this.e = i;
        this.f = nc2Var;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        nc2 nc2Var = this.f;
        switch (i) {
            case 0:
                return nc2Var.v;
            case 1:
                return nc2Var.B;
            default:
                return nc2Var.D;
        }
    }
}
