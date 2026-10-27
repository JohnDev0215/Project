package p;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import androidx.savedstate.Recreator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class w62 {
    public final x62 a;
    public final v62 b = new v62();
    public boolean c;

    public w62(x62 x62Var) {
        this.a = x62Var;
    }

    public final void a() {
        x62 x62Var = this.a;
        l91 l91VarL = x62Var.l();
        if (l91VarL.b() != Lifecycle$State.e) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        l91VarL.a(new Recreator(x62Var));
        final v62 v62Var = this.b;
        v62Var.getClass();
        if (!(!v62Var.b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        l91VarL.a(new p91() { // from class: p.s62
            @Override // p.p91
            public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
                v62 v62Var2 = v62Var;
                v03.j(v62Var2, "this$0");
                if (lifecycle$Event == Lifecycle$Event.ON_START) {
                    v62Var2.f = true;
                } else if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                    v62Var2.f = false;
                }
            }
        });
        v62Var.b = true;
        this.c = true;
    }

    public final void b(Bundle bundle) {
        if (!this.c) {
            a();
        }
        l91 l91VarL = this.a.l();
        if (!(!(l91VarL.b().compareTo(Lifecycle$State.l) >= 0))) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + l91VarL.b()).toString());
        }
        v62 v62Var = this.b;
        if (!v62Var.b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!v62Var.d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        v62Var.c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        v62Var.d = true;
    }

    public final void c(Bundle bundle) {
        v03.j(bundle, "outBundle");
        v62 v62Var = this.b;
        v62Var.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = v62Var.c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        q62 q62Var = v62Var.a;
        q62Var.getClass();
        n62 n62Var = new n62(q62Var);
        q62Var.k.put(n62Var, Boolean.FALSE);
        while (n62Var.hasNext()) {
            Map.Entry entry = (Map.Entry) n62Var.next();
            bundle2.putBundle((String) entry.getKey(), ((u62) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
