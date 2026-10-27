package androidx.lifecycle;

import p.p91;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements p91 {
    public boolean c;

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
            this.c = false;
            s91Var.l().c(this);
        }
    }
}
