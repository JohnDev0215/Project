package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r<E> extends f0 implements d0<E> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Throwable f7577i;

    public r(Throwable th) {
        this.f7577i = th;
    }

    @Override // kotlinx.coroutines.channels.f0
    public void Q() {
    }

    @Override // kotlinx.coroutines.channels.f0
    public void S(r<?> rVar) {
    }

    @Override // kotlinx.coroutines.channels.f0
    public kotlinx.coroutines.internal.c0 T(o.c cVar) {
        kotlinx.coroutines.internal.c0 c0Var = kotlinx.coroutines.l.f7702a;
        if (cVar != null) {
            cVar.d();
        }
        return c0Var;
    }

    @Override // kotlinx.coroutines.channels.d0
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public r<E> g() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.f0
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public r<E> R() {
        return this;
    }

    public final Throwable X() {
        Throwable th = this.f7577i;
        return th != null ? th : new s("Channel was closed");
    }

    public final Throwable Y() {
        Throwable th = this.f7577i;
        return th != null ? th : new t("Channel was closed");
    }

    @Override // kotlinx.coroutines.channels.d0
    public kotlinx.coroutines.internal.c0 l(E e7, o.c cVar) {
        kotlinx.coroutines.internal.c0 c0Var = kotlinx.coroutines.l.f7702a;
        if (cVar != null) {
            cVar.d();
        }
        return c0Var;
    }

    @Override // kotlinx.coroutines.channels.d0
    public void q(E e7) {
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "Closed@" + m0.b(this) + '[' + this.f7577i + ']';
    }
}
