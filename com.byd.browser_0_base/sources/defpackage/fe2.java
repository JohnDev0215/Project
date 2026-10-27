package defpackage;

import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.savedstate.Recreator;
import androidx.savedstate.a;
import defpackage.kd2;
import java.util.Map;

/* JADX INFO: compiled from: SavedStateRegistryController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class fe2 {
    public final ge2 a;
    public final a b = new a();
    public boolean c;

    public fe2(ge2 ge2Var) {
        this.a = ge2Var;
    }

    public final void a() {
        ge2 ge2Var = this.a;
        g gVarJ1 = ge2Var.j1();
        if (!(gVarJ1.c == d.b.INITIALIZED)) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        gVarJ1.a(new Recreator(ge2Var));
        final a aVar = this.b;
        aVar.getClass();
        if (!(!aVar.b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        gVarJ1.a(new f() { // from class: ee2
            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar2) {
                a aVar3 = aVar;
                i21.e(aVar3, "this$0");
                if (aVar2 == d.a.ON_START) {
                    aVar3.getClass();
                } else if (aVar2 == d.a.ON_STOP) {
                    aVar3.getClass();
                }
            }
        });
        aVar.b = true;
        this.c = true;
    }

    public final void b(Bundle bundle) {
        if (!this.c) {
            a();
        }
        g gVarJ1 = this.a.j1();
        if (!(!(gVarJ1.c.compareTo(d.b.STARTED) >= 0))) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + gVarJ1.c).toString());
        }
        a aVar = this.b;
        if (!aVar.b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!aVar.d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        aVar.c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        aVar.d = true;
    }

    public final void c(Bundle bundle) {
        i21.e(bundle, "outBundle");
        a aVar = this.b;
        aVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = aVar.c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        kd2<String, a.b> kd2Var = aVar.a;
        kd2Var.getClass();
        kd2.d dVar = new kd2.d();
        kd2Var.g.put(dVar, Boolean.FALSE);
        while (dVar.hasNext()) {
            Map.Entry entry = (Map.Entry) dVar.next();
            bundle2.putBundle((String) entry.getKey(), ((a.b) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
