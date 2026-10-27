package p;

/* JADX INFO: loaded from: classes.dex */
public final class g50 implements wy1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ h50 b;

    public /* synthetic */ g50(h50 h50Var, int i) {
        this.a = i;
        this.b = h50Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        h50 h50Var = this.b;
        switch (i) {
            case 0:
                return new i50(h50Var.b, 4, 0);
            case 1:
                return new i50(h50Var.b, 8, 0);
            case 2:
                return new i50(h50Var.b, 3, 0);
            case 3:
                return new i50(h50Var.b, 6, 0);
            case 4:
                return new i50(h50Var.b, 5, 0);
            case 5:
                return new i50(h50Var.b, 7, 0);
            case 6:
                return new i50(h50Var.b, 2, 0);
            case 7:
                return new i50(h50Var.b, 1, 0);
            default:
                return new i50(h50Var.b, 0, 0);
        }
    }
}
