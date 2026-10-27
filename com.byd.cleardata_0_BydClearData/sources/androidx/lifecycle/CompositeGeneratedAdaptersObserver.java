package androidx.lifecycle;

import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c[] f1377a;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f1377a = cVarArr;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        k kVar = new k();
        for (c cVar : this.f1377a) {
            cVar.a(gVar, aVar, false, kVar);
        }
        for (c cVar2 : this.f1377a) {
            cVar2.a(gVar, aVar, true, kVar);
        }
    }
}
