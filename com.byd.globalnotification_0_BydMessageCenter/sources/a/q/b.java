package a.q;

import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.q.a;
import android.os.Bundle;
import androidx.savedstate.Recreator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1276b = new a();

    public b(c cVar) {
        this.f1275a = cVar;
    }

    public void a(Bundle bundle) {
        g gVarA = this.f1275a.a();
        if (((l) gVarA).f1094b != g.b.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        gVarA.a(new Recreator(this.f1275a));
        final a aVar = this.f1276b;
        if (aVar.f1272c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            aVar.f1271b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        gVarA.a(new i() { // from class: androidx.savedstate.SavedStateRegistry$1
            @Override // a.n.i
            public void g(k kVar, g.a aVar2) {
                a aVar3;
                boolean z;
                if (aVar2 == g.a.ON_START) {
                    aVar3 = aVar;
                    z = true;
                } else {
                    if (aVar2 != g.a.ON_STOP) {
                        return;
                    }
                    aVar3 = aVar;
                    z = false;
                }
                aVar3.f1274e = z;
            }
        });
        aVar.f1272c = true;
    }

    public void b(Bundle bundle) {
        a aVar = this.f1276b;
        if (aVar == null) {
            throw null;
        }
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = aVar.f1271b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        a.c.a.b.b<String, a.b>.d dVarD = aVar.f1270a.d();
        while (dVarD.hasNext()) {
            Map.Entry entry = (Map.Entry) dVarD.next();
            bundle2.putBundle((String) entry.getKey(), ((a.b) entry.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
