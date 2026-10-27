package defpackage;

import defpackage.l61;

/* JADX INFO: compiled from: UndoRefocusHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class pi3 implements l61.a {
    public final /* synthetic */ qi3 e;

    public pi3(qi3 qi3Var) {
        this.e = qi3Var;
    }

    @Override // l61.a
    public final void f(int i) {
        if (i == 2 || i == 16) {
            this.e.l = false;
        }
    }

    @Override // l61.a
    public final void h(int i) {
        if (i == 2 || i == 16) {
            this.e.l = true;
        }
    }
}
