package c.a.a.b;

import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: compiled from: TransIter.java */
/* JADX INFO: loaded from: classes.dex */
public class i<F, T> implements Iterator<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Iterator<? extends F> f3030b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Function<? super F, ? extends T> f3031c;

    public i(Iterator<? extends F> it, Function<? super F, ? extends T> function) {
        this.f3030b = (Iterator) c.a.a.d.d.i(it);
        this.f3031c = (Function) c.a.a.d.d.i(function);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f3030b.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return this.f3031c.apply(this.f3030b.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f3030b.remove();
    }
}
