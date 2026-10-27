package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.b;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.a f1489b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1488a = obj;
        this.f1489b = b.f1492a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1489b.a(gVar, event, this.f1488a);
    }
}
