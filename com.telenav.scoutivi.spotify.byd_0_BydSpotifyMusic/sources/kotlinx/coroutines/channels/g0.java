package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
public interface g0<E> {

    /* JADX INFO: compiled from: Channel.kt */
    public static final class a {
        public static /* synthetic */ boolean a(g0 g0Var, Throwable th, int i7, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
            }
            if ((i7 & 1) != 0) {
                th = null;
            }
            return g0Var.close(th);
        }
    }

    boolean close(Throwable th);

    kotlinx.coroutines.selects.e<E, g0<E>> getOnSend();

    void invokeOnClose(r5.l<? super Throwable, g5.r> lVar);

    boolean isClosedForSend();

    boolean isFull();

    boolean offer(E e7);

    Object send(E e7, k5.d<? super g5.r> dVar);
}
