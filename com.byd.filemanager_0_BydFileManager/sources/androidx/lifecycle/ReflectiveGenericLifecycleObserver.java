package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.b;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.a f1239b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1238a = obj;
        this.f1239b = b.f1243a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1239b.a(gVar, event, this.f1238a);
    }
}
