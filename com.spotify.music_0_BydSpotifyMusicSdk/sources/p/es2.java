package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class es2 implements d00 {
    public final /* synthetic */ int c;
    public final /* synthetic */ bj2 e;

    public /* synthetic */ es2(bj2 bj2Var, int i) {
        this.c = i;
        this.e = bj2Var;
    }

    @Override // p.d00
    public final Object j(aj2 aj2Var) {
        int i = this.c;
        bj2 bj2Var = this.e;
        switch (i) {
            case 0:
                if (aj2Var.d()) {
                    bj2Var.b(aj2Var.c());
                } else if (aj2Var.b() != null) {
                    bj2Var.a(aj2Var.b());
                }
                break;
            case 1:
                if (aj2Var.d()) {
                    bj2Var.a.g(aj2Var.c());
                } else if (aj2Var.b() != null) {
                    bj2Var.a.f(aj2Var.b());
                }
                break;
            default:
                if (aj2Var.d()) {
                    bj2Var.b(aj2Var.c());
                } else if (aj2Var.b() != null) {
                    bj2Var.a(aj2Var.b());
                }
                break;
        }
        return null;
    }
}
