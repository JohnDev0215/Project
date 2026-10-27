package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c[] f1625a;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f1625a = cVarArr;
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f1625a) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f1625a) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}
