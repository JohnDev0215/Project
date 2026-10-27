package androidx.lifecycle;

import androidx.lifecycle.a;
import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0043a f1392b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1391a = obj;
        this.f1392b = a.f1394a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        this.f1392b.a(gVar, aVar, this.f1391a);
    }
}
