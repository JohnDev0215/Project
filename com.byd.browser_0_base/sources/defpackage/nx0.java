package defpackage;

import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ImmutableWeakReference.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nx0<T> extends WeakReference<T> {
    public nx0(T t) {
        super(t);
    }

    @Override // java.lang.ref.Reference
    public final void clear() {
        throw new UnsupportedOperationException("clear WeakReference banned");
    }
}
