package androidx.lifecycle;

import a.n.e;
import a.n.g;
import a.n.i;
import a.n.k;
import a.n.o;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e[] f1567a;

    public CompositeGeneratedAdaptersObserver(e[] eVarArr) {
        this.f1567a = eVarArr;
    }

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        o oVar = new o();
        for (e eVar : this.f1567a) {
            eVar.a(kVar, aVar, false, oVar);
        }
        for (e eVar2 : this.f1567a) {
            eVar2.a(kVar, aVar, true, oVar);
        }
    }
}
