package androidx.lifecycle;

import androidx.lifecycle.i;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: LifecycleController.kt */
/* JADX INFO: loaded from: classes.dex */
final class LifecycleController$observer$1 implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ l1 f2497e;

    @Override // androidx.lifecycle.m
    public final void d(o source, i.b bVar) {
        kotlin.jvm.internal.l.f(source, "source");
        kotlin.jvm.internal.l.f(bVar, "<anonymous parameter 1>");
        i iVarA = source.a();
        kotlin.jvm.internal.l.b(iVarA, "source.lifecycle");
        if (iVarA.b() == i.c.DESTROYED) {
            l1.a.b(this.f2497e, null, 1, null);
            throw null;
        }
        i iVarA2 = source.a();
        kotlin.jvm.internal.l.b(iVarA2, "source.lifecycle");
        if (iVarA2.b().compareTo(j.b(null)) < 0) {
            j.a(null);
            throw null;
        }
        j.a(null);
        throw null;
    }
}
