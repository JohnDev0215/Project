package kotlinx.coroutines.reactive;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.channels.LinkedListChannel;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u001c2\b\u0012\u0004\u0012\u00028\u00000\u001dB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\fJ\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\fJ\u0017\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001a¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/reactive/SubscriptionChannel;", "T", "", "request", "<init>", "(I)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "closed", "", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "onComplete", "()V", "", "e", "onError", "(Ljava/lang/Throwable;)V", "t", "onNext", "(Ljava/lang/Object;)V", "onReceiveDequeued", "onReceiveEnqueued", "Lorg/reactivestreams/Subscription;", "s", "onSubscribe", "(Lorg/reactivestreams/Subscription;)V", "I", "kotlinx-coroutines-reactive", "Lkotlinx/coroutines/channels/LinkedListChannel;", "Lorg/reactivestreams/Subscriber;"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class SubscriptionChannel<T> extends LinkedListChannel<T> implements Subscriber<T> {
    private volatile /* synthetic */ int _requested;
    private volatile /* synthetic */ Object _subscription;
    private final int request;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _subscription$FU = AtomicReferenceFieldUpdater.newUpdater(SubscriptionChannel.class, Object.class, "_subscription");
    private static final /* synthetic */ AtomicIntegerFieldUpdater _requested$FU = AtomicIntegerFieldUpdater.newUpdater(SubscriptionChannel.class, "_requested");

    public SubscriptionChannel(int i) {
        super(null);
        this.request = i;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Invalid request size: " + i).toString());
        }
        this._subscription = null;
        this._requested = 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onReceiveDequeued() {
        _requested$FU.incrementAndGet(this);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public void onClosedIdempotent(LockFreeLinkedListNode closed) {
        Subscription subscription = (Subscription) _subscription$FU.getAndSet(this, null);
        if (subscription != null) {
            subscription.cancel();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription s) {
        this._subscription = s;
        while (!isClosedForSend()) {
            int i = this._requested;
            int i2 = this.request;
            if (i >= i2) {
                return;
            }
            if (_requested$FU.compareAndSet(this, i, i2)) {
                s.request(this.request - i);
                return;
            }
        }
        s.cancel();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        _requested$FU.decrementAndGet(this);
        mo55trySendJP2dKIU(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        cancel(null);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable e) {
        cancel(e);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        r1.request(r5.request - r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        return;
     */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceiveEnqueued() {
        /*
            r5 = this;
        L0:
            int r0 = r5._requested
            java.lang.Object r1 = r5._subscription
            org.reactivestreams.Subscription r1 = (org.reactivestreams.Subscription) r1
            int r2 = r0 + (-1)
            if (r1 == 0) goto L21
            if (r2 >= 0) goto L21
            int r3 = r5.request
            if (r0 == r3) goto L19
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = kotlinx.coroutines.reactive.SubscriptionChannel._requested$FU
            boolean r0 = r4.compareAndSet(r5, r0, r3)
            if (r0 != 0) goto L19
            goto L0
        L19:
            int r0 = r5.request
            int r0 = r0 - r2
            long r2 = (long) r0
            r1.request(r2)
            return
        L21:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = kotlinx.coroutines.reactive.SubscriptionChannel._requested$FU
            boolean r0 = r1.compareAndSet(r5, r0, r2)
            if (r0 == 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.SubscriptionChannel.onReceiveEnqueued():void");
    }
}
