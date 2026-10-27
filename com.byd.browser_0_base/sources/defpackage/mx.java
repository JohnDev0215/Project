package defpackage;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SequencesJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public final class mx<T> implements li2<T> {
    public final AtomicReference<li2<T>> a;

    public mx(ri2 ri2Var) {
        this.a = new AtomicReference<>(ri2Var);
    }

    @Override // defpackage.li2
    public final Iterator<T> iterator() {
        li2<T> andSet = this.a.getAndSet(null);
        if (andSet != null) {
            return andSet.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
