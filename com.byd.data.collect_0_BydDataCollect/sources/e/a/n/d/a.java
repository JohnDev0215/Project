package e.a.n.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: BasicIntQueueDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends AtomicInteger implements e.a.n.c.a<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // e.a.n.c.c
    public final boolean e(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
