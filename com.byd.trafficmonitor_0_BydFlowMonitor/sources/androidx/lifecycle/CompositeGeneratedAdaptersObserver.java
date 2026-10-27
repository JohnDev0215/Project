package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.n.d;
import b.n.e;
import b.n.g;
import b.n.k;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d[] f1009a;

    public CompositeGeneratedAdaptersObserver(d[] dVarArr) {
        this.f1009a = dVarArr;
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        k kVar = new k();
        for (d dVar : this.f1009a) {
            dVar.a(gVar, event, false, kVar);
        }
        for (d dVar2 : this.f1009a) {
            dVar2.a(gVar, event, true, kVar);
        }
    }
}
