package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements oz0 {
    public final /* synthetic */ int c;
    public final /* synthetic */ v4 e;

    public /* synthetic */ o(v4 v4Var, int i) {
        this.c = i;
        this.e = v4Var;
    }

    @Override // p.oz0
    public final Object apply(Object obj) {
        int i = this.c;
        v4 v4Var = this.e;
        switch (i) {
            case 0:
                return ((ju) v4Var.e).a((if2) obj, true);
            default:
                return ((ju) v4Var.e).a((if2) obj, false);
        }
    }
}
