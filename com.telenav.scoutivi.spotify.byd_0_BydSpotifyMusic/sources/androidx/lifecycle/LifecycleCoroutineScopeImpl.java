package androidx.lifecycle;

import androidx.lifecycle.i;
import kotlinx.coroutines.q1;

/* JADX INFO: compiled from: Lifecycle.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl extends k implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final i f2498e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final k5.g f2499g;

    @Override // androidx.lifecycle.m
    public void d(o source, i.b event) {
        kotlin.jvm.internal.l.f(source, "source");
        kotlin.jvm.internal.l.f(event, "event");
        if (h().b().compareTo(i.c.DESTROYED) <= 0) {
            h().c(this);
            q1.d(getCoroutineContext(), null, 1, null);
        }
    }

    @Override // kotlinx.coroutines.h0
    public k5.g getCoroutineContext() {
        return this.f2499g;
    }

    public i h() {
        return this.f2498e;
    }
}
