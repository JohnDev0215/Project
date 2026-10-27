package androidx.lifecycle;

import androidx.lifecycle.a;
import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1588a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0022a f1589b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1588a = obj;
        this.f1589b = a.f1594a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        this.f1589b.a(gVar, aVar, this.f1588a);
    }
}
