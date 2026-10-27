package androidx.lifecycle;

import android.os.Bundle;
import p.p91;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleAttacher implements p91 {
    public final c c;

    public SavedStateHandleAttacher(c cVar) {
        this.c = cVar;
    }

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
            throw new IllegalStateException(("Next event must be ON_CREATE, it was " + lifecycle$Event).toString());
        }
        s91Var.l().c(this);
        c cVar = this.c;
        if (cVar.b) {
            return;
        }
        Bundle bundleA = cVar.a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = cVar.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (bundleA != null) {
            bundle.putAll(bundleA);
        }
        cVar.c = bundle;
        cVar.b = true;
    }
}
