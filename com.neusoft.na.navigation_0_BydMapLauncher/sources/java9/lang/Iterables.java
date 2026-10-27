package java9.lang;

import java.util.Collection;
import java.util.Iterator;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.Consumer;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public final class Iterables {
    public static <T> void forEach(Iterable<? extends T> iterable, Consumer<? super T> consumer) {
        Objects.requireNonNull(iterable);
        Objects.requireNonNull(consumer);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    public static <T> boolean removeIf(Iterable<? extends T> iterable, Predicate<? super T> predicate) {
        Objects.requireNonNull(iterable);
        Objects.requireNonNull(predicate);
        Iterator<? extends T> it = iterable.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> Spliterator<T> spliterator(Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return Spliterators.spliterator((Collection) iterable);
        }
        return Spliterators.spliteratorUnknownSize(iterable.iterator(), 0);
    }

    private Iterables() {
    }
}
