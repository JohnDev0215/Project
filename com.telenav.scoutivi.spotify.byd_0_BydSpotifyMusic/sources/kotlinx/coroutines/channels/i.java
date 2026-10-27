package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.m1;
import kotlinx.coroutines.s1;

/* JADX INFO: compiled from: Broadcast.kt */
/* JADX INFO: loaded from: classes.dex */
class i<E> extends kotlinx.coroutines.a<g5.r> implements a0<E>, g<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final g<E> f7568e;

    public i(k5.g gVar, g<E> gVar2, boolean z6) {
        super(gVar, z6);
        this.f7568e = gVar2;
    }

    static /* synthetic */ Object g(i iVar, Object obj, k5.d dVar) {
        return iVar.f7568e.send(obj, dVar);
    }

    @Override // kotlinx.coroutines.channels.g
    public c0<E> c() {
        return this.f7568e.c();
    }

    @Override // kotlinx.coroutines.s1
    public final /* synthetic */ boolean cancel(Throwable th) {
        if (th == null) {
            th = new m1(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(th);
        return true;
    }

    @Override // kotlinx.coroutines.s1
    public void cancelInternal(Throwable th) {
        CancellationException cancellationException$default = s1.toCancellationException$default(this, th, null, 1, null);
        this.f7568e.cancel(cancellationException$default);
        cancelCoroutine(cancellationException$default);
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean close(Throwable th) {
        boolean zClose = this.f7568e.close(th);
        start();
        return zClose;
    }

    protected final g<E> f() {
        return this.f7568e;
    }

    @Override // kotlinx.coroutines.channels.a0
    public g0<E> getChannel() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.g0
    public void invokeOnClose(r5.l<? super Throwable, g5.r> lVar) {
        this.f7568e.invokeOnClose(lVar);
    }

    @Override // kotlinx.coroutines.a, kotlinx.coroutines.s1, kotlinx.coroutines.l1
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean isClosedForSend() {
        return this.f7568e.isClosedForSend();
    }

    @Override // kotlinx.coroutines.a
    protected void onCancelled(Throwable th, boolean z6) {
        if (this.f7568e.close(th) || z6) {
            return;
        }
        kotlinx.coroutines.g0.a(getContext(), th);
    }

    @Override // kotlinx.coroutines.channels.g0
    public Object send(E e7, k5.d<? super g5.r> dVar) {
        return g(this, e7, dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.a
    public void onCompleted(g5.r rVar) {
        g0.a.a(this.f7568e, null, 1, null);
    }

    @Override // kotlinx.coroutines.s1, kotlinx.coroutines.l1, kotlinx.coroutines.channels.g
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new m1(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }
}
