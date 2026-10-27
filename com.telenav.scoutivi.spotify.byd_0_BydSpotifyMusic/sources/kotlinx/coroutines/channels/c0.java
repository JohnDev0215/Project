package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
public interface c0<E> {

    /* JADX INFO: compiled from: Channel.kt */
    public static final class a {
        public static /* synthetic */ void a(c0 c0Var, CancellationException cancellationException, int i7, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i7 & 1) != 0) {
                cancellationException = null;
            }
            c0Var.cancel(cancellationException);
        }
    }

    void cancel(CancellationException cancellationException);

    kotlinx.coroutines.selects.d<E> getOnReceive();

    kotlinx.coroutines.selects.d<j0<E>> getOnReceiveOrClosed();

    kotlinx.coroutines.selects.d<E> getOnReceiveOrNull();

    boolean isClosedForReceive();

    boolean isEmpty();

    n<E> iterator();

    E poll();

    Object receive(k5.d<? super E> dVar);

    /* JADX INFO: renamed from: receiveOrClosed-ZYPwvRU */
    Object mo1receiveOrClosedZYPwvRU(k5.d<? super j0<? extends E>> dVar);

    Object receiveOrNull(k5.d<? super E> dVar);
}
