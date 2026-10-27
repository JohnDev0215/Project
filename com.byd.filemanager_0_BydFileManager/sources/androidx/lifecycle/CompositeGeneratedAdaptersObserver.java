package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d[] f1220a;

    CompositeGeneratedAdaptersObserver(d[] dVarArr) {
        this.f1220a = dVarArr;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        k kVar = new k();
        for (d dVar : this.f1220a) {
            dVar.a(gVar, event, false, kVar);
        }
        for (d dVar2 : this.f1220a) {
            dVar2.a(gVar, event, true, kVar);
        }
    }
}
