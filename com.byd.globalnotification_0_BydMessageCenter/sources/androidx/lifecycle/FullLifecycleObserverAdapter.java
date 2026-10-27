package androidx.lifecycle;

import a.n.d;
import a.n.g;
import a.n.i;
import a.n.k;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f1568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f1569b;

    public FullLifecycleObserverAdapter(d dVar, i iVar) {
        this.f1568a = dVar;
        this.f1569b = iVar;
    }

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        switch (aVar) {
            case ON_CREATE:
                this.f1568a.e(kVar);
                break;
            case ON_START:
                this.f1568a.f(kVar);
                break;
            case ON_RESUME:
                this.f1568a.a(kVar);
                break;
            case ON_PAUSE:
                this.f1568a.b(kVar);
                break;
            case ON_STOP:
                this.f1568a.d(kVar);
                break;
            case ON_DESTROY:
                this.f1568a.c(kVar);
                break;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        i iVar = this.f1569b;
        if (iVar != null) {
            iVar.g(kVar, aVar);
        }
    }
}
