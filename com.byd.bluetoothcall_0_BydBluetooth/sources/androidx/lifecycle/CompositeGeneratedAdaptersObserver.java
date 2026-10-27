package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d[] f1469a;

    CompositeGeneratedAdaptersObserver(d[] dVarArr) {
        this.f1469a = dVarArr;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        l lVar = new l();
        for (d dVar : this.f1469a) {
            dVar.a(gVar, event, false, lVar);
        }
        for (d dVar2 : this.f1469a) {
            dVar2.a(gVar, event, true, lVar);
        }
    }
}
