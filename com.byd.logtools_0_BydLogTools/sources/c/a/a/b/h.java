package c.a.a.b;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: compiled from: TransCollection.java */
/* JADX INFO: loaded from: classes.dex */
public class h<F, T> extends AbstractCollection<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Collection<F> f2885b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Function<? super F, ? extends T> f2886c;

    public h(Collection<F> collection, Function<? super F, ? extends T> function) {
        this.f2885b = (Collection) c.a.a.c.d.i(collection);
        this.f2886c = (Function) c.a.a.c.d.i(function);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(Consumer consumer, Object obj) {
        consumer.accept(this.f2886c.apply(obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean d(Predicate predicate, Object obj) {
        return predicate.test(this.f2886c.apply(obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.f2885b.clear();
    }

    @Override // java.lang.Iterable
    public void forEach(final Consumer<? super T> consumer) {
        c.a.a.c.d.i(consumer);
        this.f2885b.forEach(new Consumer() { // from class: c.a.a.b.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f2877a.b(consumer, obj);
            }
        });
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f2885b.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return f.a(this.f2885b.iterator(), this.f2886c);
    }

    @Override // java.util.Collection
    public boolean removeIf(final Predicate<? super T> predicate) {
        c.a.a.c.d.i(predicate);
        return this.f2885b.removeIf(new Predicate() { // from class: c.a.a.b.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f2879a.d(predicate, obj);
            }
        });
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.f2885b.size();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<T> spliterator() {
        return g.a(this.f2885b.spliterator(), this.f2886c);
    }
}
