package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.l.a;
import b.l.d;
import b.l.f;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.C0036a f544b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f543a = obj;
        this.f544b = a.f1963a.c(obj.getClass());
    }

    @Override // b.l.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f544b.a(fVar, event, this.f543a);
    }
}
