package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.a;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0020a f1099b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1098a = obj;
        this.f1099b = a.f1102a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1099b.a(fVar, event, this.f1098a);
    }
}
