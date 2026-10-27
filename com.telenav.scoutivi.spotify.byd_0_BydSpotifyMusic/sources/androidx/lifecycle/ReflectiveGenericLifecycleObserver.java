package androidx.lifecycle;

import androidx.lifecycle.c;
import androidx.lifecycle.i;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Object f2520e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c.a f2521g;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f2520e = obj;
        this.f2521g = c.f2536c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.m
    public void d(o oVar, i.b bVar) {
        this.f2521g.a(oVar, bVar, this.f2520e);
    }
}
