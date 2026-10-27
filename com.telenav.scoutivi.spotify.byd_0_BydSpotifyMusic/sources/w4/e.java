package w4;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SequentialDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends AtomicReference<t4.b> implements t4.b {
    private static final long serialVersionUID = -754898800686245608L;

    public e() {
    }

    public boolean a(t4.b bVar) {
        return b.replace(this, bVar);
    }

    @Override // t4.b
    public void dispose() {
        b.dispose(this);
    }

    @Override // t4.b
    public boolean isDisposed() {
        return b.isDisposed(get());
    }

    public e(t4.b bVar) {
        lazySet(bVar);
    }
}
