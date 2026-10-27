package androidx.lifecycle;

import androidx.lifecycle.i;

/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final g[] f2493e;

    CompositeGeneratedAdaptersObserver(g[] gVarArr) {
        this.f2493e = gVarArr;
    }

    @Override // androidx.lifecycle.m
    public void d(o oVar, i.b bVar) {
        s sVar = new s();
        for (g gVar : this.f2493e) {
            gVar.a(oVar, bVar, false, sVar);
        }
        for (g gVar2 : this.f2493e) {
            gVar2.a(oVar, bVar, true, sVar);
        }
    }
}
