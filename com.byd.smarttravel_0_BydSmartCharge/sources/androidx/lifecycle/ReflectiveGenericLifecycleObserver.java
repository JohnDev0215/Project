package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.m.a;
import b.m.d;
import b.m.f;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.C0032a f414b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f413a = obj;
        this.f414b = a.f1303a.c(obj.getClass());
    }

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f414b.a(fVar, event, this.f413a);
    }
}
