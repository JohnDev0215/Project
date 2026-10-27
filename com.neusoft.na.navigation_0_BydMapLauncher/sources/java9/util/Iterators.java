package java9.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java9.util.PrimitiveIterator;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;

/* JADX INFO: loaded from: classes3.dex */
public final class Iterators {
    public static <E> void forEachRemaining(Iterator<E> it, Consumer<? super E> consumer) {
        Objects.requireNonNull(it);
        Objects.requireNonNull(consumer);
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    public static void forEachRemaining(PrimitiveIterator.OfInt ofInt, IntConsumer intConsumer) {
        Objects.requireNonNull(ofInt);
        Objects.requireNonNull(intConsumer);
        while (ofInt.hasNext()) {
            intConsumer.accept(ofInt.nextInt());
        }
    }

    public static void forEachRemaining(PrimitiveIterator.OfLong ofLong, LongConsumer longConsumer) {
        Objects.requireNonNull(ofLong);
        Objects.requireNonNull(longConsumer);
        while (ofLong.hasNext()) {
            longConsumer.accept(ofLong.nextLong());
        }
    }

    public static void forEachRemaining(PrimitiveIterator.OfDouble ofDouble, DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(ofDouble);
        Objects.requireNonNull(doubleConsumer);
        while (ofDouble.hasNext()) {
            doubleConsumer.accept(ofDouble.nextDouble());
        }
    }

    public static <E> Iterator<E> asIterator(final Enumeration<E> enumeration) {
        Objects.requireNonNull(enumeration);
        return new ImmutableIt<E>() { // from class: java9.util.Iterators.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override // java.util.Iterator
            public E next() {
                return (E) enumeration.nextElement();
            }
        };
    }

    public static <T> Iterator<T> emptyIterator() {
        return EmptyIt.EMPTY_ITERATOR;
    }

    static <E> Iterator<E> singletonIterator(final E e) {
        return new ImmutableIt<E>() { // from class: java9.util.Iterators.2
            private boolean hasNext = true;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.hasNext) {
                    this.hasNext = false;
                    return (E) e;
                }
                throw new NoSuchElementException();
            }
        };
    }

    static final class EmptyIt<E> extends ImmutableIt<E> {
        static final EmptyIt<Object> EMPTY_ITERATOR = new EmptyIt<>();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        EmptyIt() {
        }

        @Override // java.util.Iterator
        public E next() {
            throw new NoSuchElementException();
        }
    }

    static abstract class ImmutableIt<T> implements Iterator<T> {
        ImmutableIt() {
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private Iterators() {
    }
}
