package kotlinx.coroutines.channels;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.d1;

/* JADX INFO: compiled from: Channels.common.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class q {
    public static final void a(c0<?> c0Var, Throwable th) {
        CancellationException cancellationExceptionA = null;
        if (th != null) {
            cancellationExceptionA = (CancellationException) (th instanceof CancellationException ? th : null);
            if (cancellationExceptionA == null) {
                cancellationExceptionA = d1.a("Channel was consumed, consumer had failed", th);
            }
        }
        c0Var.cancel(cancellationExceptionA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> Object b(c0<? extends E> c0Var, k5.d<? super E> dVar) {
        Objects.requireNonNull(c0Var, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E?>");
        return c0Var.receiveOrNull(dVar);
    }
}
