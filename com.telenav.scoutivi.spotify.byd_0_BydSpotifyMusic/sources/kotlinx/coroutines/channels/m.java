package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.m1;
import kotlinx.coroutines.s1;

/* JADX INFO: compiled from: ChannelCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
public class m<E> extends kotlinx.coroutines.a<g5.r> implements l<E> {
    private final l<E> _channel;

    public m(k5.g gVar, l<E> lVar, boolean z6) {
        super(gVar, z6);
        this._channel = lVar;
    }

    static /* synthetic */ Object receive$suspendImpl(m mVar, k5.d dVar) {
        return mVar._channel.receive(dVar);
    }

    /* JADX INFO: renamed from: receiveOrClosed-ZYPwvRU$suspendImpl, reason: not valid java name */
    static /* synthetic */ Object m2receiveOrClosedZYPwvRU$suspendImpl(m mVar, k5.d dVar) {
        return mVar._channel.mo1receiveOrClosedZYPwvRU(dVar);
    }

    static /* synthetic */ Object receiveOrNull$suspendImpl(m mVar, k5.d dVar) {
        return mVar._channel.receiveOrNull(dVar);
    }

    static /* synthetic */ Object send$suspendImpl(m mVar, Object obj, k5.d dVar) {
        return mVar._channel.send(obj, dVar);
    }

    @Override // kotlinx.coroutines.s1
    public /* synthetic */ void cancel() {
        cancelInternal(new m1(cancellationExceptionMessage(), null, this));
    }

    @Override // kotlinx.coroutines.s1
    public void cancelInternal(Throwable th) {
        CancellationException cancellationException$default = s1.toCancellationException$default(this, th, null, 1, null);
        this._channel.cancel(cancellationException$default);
        cancelCoroutine(cancellationException$default);
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean close(Throwable th) {
        return this._channel.close(th);
    }

    public final l<E> getChannel() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.c0
    public kotlinx.coroutines.selects.d<E> getOnReceive() {
        return this._channel.getOnReceive();
    }

    @Override // kotlinx.coroutines.channels.c0
    public kotlinx.coroutines.selects.d<j0<E>> getOnReceiveOrClosed() {
        return this._channel.getOnReceiveOrClosed();
    }

    @Override // kotlinx.coroutines.channels.c0
    public kotlinx.coroutines.selects.d<E> getOnReceiveOrNull() {
        return this._channel.getOnReceiveOrNull();
    }

    @Override // kotlinx.coroutines.channels.g0
    public kotlinx.coroutines.selects.e<E, g0<E>> getOnSend() {
        return this._channel.getOnSend();
    }

    protected final l<E> get_channel() {
        return this._channel;
    }

    @Override // kotlinx.coroutines.channels.g0
    public void invokeOnClose(r5.l<? super Throwable, g5.r> lVar) {
        this._channel.invokeOnClose(lVar);
    }

    @Override // kotlinx.coroutines.channels.c0
    public boolean isClosedForReceive() {
        return this._channel.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.c0
    public boolean isEmpty() {
        return this._channel.isEmpty();
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean isFull() {
        return this._channel.isFull();
    }

    @Override // kotlinx.coroutines.channels.c0
    public n<E> iterator() {
        return this._channel.iterator();
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean offer(E e7) {
        return this._channel.offer(e7);
    }

    @Override // kotlinx.coroutines.channels.c0
    public E poll() {
        return this._channel.poll();
    }

    @Override // kotlinx.coroutines.channels.c0
    public Object receive(k5.d<? super E> dVar) {
        return receive$suspendImpl(this, dVar);
    }

    @Override // kotlinx.coroutines.channels.c0
    /* JADX INFO: renamed from: receiveOrClosed-ZYPwvRU */
    public Object mo1receiveOrClosedZYPwvRU(k5.d<? super j0<? extends E>> dVar) {
        return m2receiveOrClosedZYPwvRU$suspendImpl(this, dVar);
    }

    @Override // kotlinx.coroutines.channels.c0
    public Object receiveOrNull(k5.d<? super E> dVar) {
        return receiveOrNull$suspendImpl(this, dVar);
    }

    @Override // kotlinx.coroutines.channels.g0
    public Object send(E e7, k5.d<? super g5.r> dVar) {
        return send$suspendImpl(this, e7, dVar);
    }

    @Override // kotlinx.coroutines.s1
    public final /* synthetic */ boolean cancel(Throwable th) {
        cancelInternal(new m1(cancellationExceptionMessage(), null, this));
        return true;
    }

    @Override // kotlinx.coroutines.s1, kotlinx.coroutines.l1, kotlinx.coroutines.channels.g
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new m1(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }
}
