package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: BroadcastChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public interface g<E> extends g0<E> {
    c0<E> c();

    void cancel(CancellationException cancellationException);
}
