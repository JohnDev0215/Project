package c.a.a.b;

import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: compiled from: TransIter.java */
/* JADX INFO: loaded from: classes.dex */
public class i<F, T> implements Iterator<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Iterator<? extends F> f2887b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Function<? super F, ? extends T> f2888c;

    public i(Iterator<? extends F> it, Function<? super F, ? extends T> function) {
        this.f2887b = (Iterator) c.a.a.c.d.i(it);
        this.f2888c = (Function) c.a.a.c.d.i(function);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2887b.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return this.f2888c.apply(this.f2887b.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f2887b.remove();
    }
}
