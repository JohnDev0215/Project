package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import x0.a;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f1459d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a.C0141a f1460e;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1459d = obj;
        this.f1460e = a.f9048a.c(obj.getClass());
    }

    @Override // x0.d
    public void onStateChanged(f fVar, Lifecycle.Event event) {
        this.f1460e.a(fVar, event, this.f1459d);
    }
}
