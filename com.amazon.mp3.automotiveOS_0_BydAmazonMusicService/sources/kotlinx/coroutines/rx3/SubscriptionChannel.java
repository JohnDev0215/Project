package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.channels.LinkedListChannel;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* JADX INFO: compiled from: RxChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00172\b\u0012\u0004\u0012\u00028\u00000\u00182\b\u0012\u0004\u0012\u00028\u00000\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\u0003J\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0010¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/rx3/SubscriptionChannel;", "T", "<init>", "()V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "closed", "", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "onComplete", "", "e", "onError", "(Ljava/lang/Throwable;)V", "t", "onNext", "(Ljava/lang/Object;)V", "Lio/reactivex/rxjava3/disposables/Disposable;", "sub", "onSubscribe", "(Lio/reactivex/rxjava3/disposables/Disposable;)V", "onSuccess", "kotlinx-coroutines-rx3", "Lkotlinx/coroutines/channels/LinkedListChannel;", "Lio/reactivex/rxjava3/core/Observer;", "Lio/reactivex/rxjava3/core/MaybeObserver;"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class SubscriptionChannel<T> extends LinkedListChannel<T> implements Observer<T>, MaybeObserver<T> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _subscription$FU = AtomicReferenceFieldUpdater.newUpdater(SubscriptionChannel.class, Object.class, "_subscription");
    private volatile /* synthetic */ Object _subscription;

    public SubscriptionChannel() {
        super(null);
        this._subscription = null;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public void onClosedIdempotent(LockFreeLinkedListNode closed) {
        Disposable disposable = (Disposable) _subscription$FU.getAndSet(this, null);
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable sub) {
        this._subscription = sub;
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public void onSuccess(T t) {
        mo55trySendJP2dKIU(t);
        cancel(null);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        mo55trySendJP2dKIU(t);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        cancel(null);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable e) {
        cancel(e);
    }
}
