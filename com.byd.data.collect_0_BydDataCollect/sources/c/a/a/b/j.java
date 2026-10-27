package c.a.a.b;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: compiled from: TransSpliterator.java */
/* JADX INFO: loaded from: classes.dex */
public class j<F, T> implements Spliterator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Spliterator<F> f3032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Function<? super F, ? extends T> f3033b;

    public j(Spliterator<F> spliterator, Function<? super F, ? extends T> function) {
        this.f3032a = spliterator;
        this.f3033b = function;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(Consumer consumer, Object obj) {
        consumer.accept(this.f3033b.apply(obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(Consumer consumer, Object obj) {
        consumer.accept(this.f3033b.apply(obj));
    }

    @Override // java.util.Spliterator
    public int characteristics() {
        return this.f3032a.characteristics() & (-262);
    }

    @Override // java.util.Spliterator
    public long estimateSize() {
        return this.f3032a.estimateSize();
    }

    @Override // java.util.Spliterator
    public void forEachRemaining(final Consumer<? super T> consumer) {
        this.f3032a.forEachRemaining(new Consumer() { // from class: c.a.a.b.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3024a.b(consumer, obj);
            }
        });
    }

    @Override // java.util.Spliterator
    public boolean tryAdvance(final Consumer<? super T> consumer) {
        return this.f3032a.tryAdvance(new Consumer() { // from class: c.a.a.b.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3026a.d(consumer, obj);
            }
        });
    }

    @Override // java.util.Spliterator
    public Spliterator<T> trySplit() {
        Spliterator<F> spliteratorTrySplit = this.f3032a.trySplit();
        if (spliteratorTrySplit != null) {
            return new j(spliteratorTrySplit, this.f3033b);
        }
        return null;
    }
}
