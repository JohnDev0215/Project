package x5;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SequencesJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<d<T>> f9853a;

    public a(d<? extends T> sequence) {
        kotlin.jvm.internal.l.e(sequence, "sequence");
        this.f9853a = new AtomicReference<>(sequence);
    }

    @Override // x5.d
    public Iterator<T> iterator() {
        d<T> andSet = this.f9853a.getAndSet(null);
        if (andSet != null) {
            return andSet.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
