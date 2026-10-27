package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.n.b;
import b.n.e;
import b.n.g;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1035a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.a f1036b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1035a = obj;
        this.f1036b = b.f2937a.c(obj.getClass());
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1036b.a(gVar, event, this.f1035a);
    }
}
