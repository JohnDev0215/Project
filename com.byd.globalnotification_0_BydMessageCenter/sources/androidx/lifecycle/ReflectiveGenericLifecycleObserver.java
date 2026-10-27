package androidx.lifecycle;

import a.n.b;
import a.n.g;
import a.n.i;
import a.n.k;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.a f1584b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1583a = obj;
        this.f1584b = b.f1078c.b(obj.getClass());
    }

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        b.a aVar2 = this.f1584b;
        Object obj = this.f1583a;
        b.a.a(aVar2.f1081a.get(aVar), kVar, aVar, obj);
        b.a.a(aVar2.f1081a.get(g.a.ON_ANY), kVar, aVar, obj);
    }
}
