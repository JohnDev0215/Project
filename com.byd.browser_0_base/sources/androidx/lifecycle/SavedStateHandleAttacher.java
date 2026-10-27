package androidx.lifecycle;

import androidx.lifecycle.d;
import defpackage.b71;
import defpackage.ce2;

/* JADX INFO: compiled from: SavedStateHandleSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleAttacher implements f {
    public final ce2 e;

    public SavedStateHandleAttacher(ce2 ce2Var) {
        this.e = ce2Var;
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        if (!(aVar == d.a.ON_CREATE)) {
            throw new IllegalStateException(("Next event must be ON_CREATE, it was " + aVar).toString());
        }
        b71Var.j1().b(this);
        ce2 ce2Var = this.e;
        if (ce2Var.b) {
            return;
        }
        ce2Var.c = ce2Var.a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
        ce2Var.b = true;
    }
}
