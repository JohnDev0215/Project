package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import t0.a;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1369a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.C0061a f1370b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1369a = obj;
        this.f1370b = a.f4990a.c(obj.getClass());
    }

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1370b.a(fVar, event, this.f1369a);
    }
}
