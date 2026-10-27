package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class nr implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ y13 f;

    public /* synthetic */ nr(int i, y13 y13Var) {
        this.e = i;
        this.f = y13Var;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        y13 y13Var = this.f;
        switch (i) {
            case 0:
                return y13Var.i();
            default:
                return Boolean.valueOf(y13Var.r());
        }
    }
}
