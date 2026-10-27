package kotlinx.coroutines.channels;

import g5.l;
import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public class h0<E> extends f0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final E f7566i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final kotlinx.coroutines.i<g5.r> f7567j;

    /* JADX WARN: Multi-variable type inference failed */
    public h0(E e7, kotlinx.coroutines.i<? super g5.r> iVar) {
        this.f7566i = e7;
        this.f7567j = iVar;
    }

    @Override // kotlinx.coroutines.channels.f0
    public void Q() {
        this.f7567j.v(kotlinx.coroutines.l.f7702a);
    }

    @Override // kotlinx.coroutines.channels.f0
    public E R() {
        return this.f7566i;
    }

    @Override // kotlinx.coroutines.channels.f0
    public void S(r<?> rVar) {
        kotlinx.coroutines.i<g5.r> iVar = this.f7567j;
        Throwable thY = rVar.Y();
        l.a aVar = g5.l.f6557e;
        iVar.resumeWith(g5.l.a(g5.m.a(thY)));
    }

    @Override // kotlinx.coroutines.channels.f0
    public kotlinx.coroutines.internal.c0 T(o.c cVar) {
        if (this.f7567j.e(g5.r.f6563a, cVar != null ? cVar.f7663c : null) == null) {
            return null;
        }
        if (cVar != null) {
            cVar.d();
        }
        return kotlinx.coroutines.l.f7702a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return m0.a(this) + '@' + m0.b(this) + '(' + R() + ')';
    }
}
