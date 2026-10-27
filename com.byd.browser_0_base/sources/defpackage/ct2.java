package defpackage;

import defpackage.l61;

/* JADX INFO: compiled from: StatusBarColorController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ct2 implements l61.a {
    public final /* synthetic */ dt2 e;

    public ct2(dt2 dt2Var) {
        this.e = dt2Var;
    }

    @Override // l61.a
    public final void f(int i) {
        if (i == 2 || i == 16) {
            dt2 dt2Var = this.e;
            dt2Var.n = false;
            dt2Var.g();
        }
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        if (i == 2 || i == 16) {
            dt2 dt2Var = this.e;
            dt2Var.n = true;
            if (pr1.b()) {
                return;
            }
            dt2Var.g();
        }
    }
}
