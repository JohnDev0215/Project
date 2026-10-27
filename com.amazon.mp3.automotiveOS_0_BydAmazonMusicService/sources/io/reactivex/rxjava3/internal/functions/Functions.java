package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.LongConsumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes7.dex */
public final class Functions {
    static final Function<Object, Object> IDENTITY = new Identity();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer<Object> EMPTY_CONSUMER = new EmptyConsumer();
    public static final Consumer<Throwable> ERROR_CONSUMER = new ErrorConsumer();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    static final Predicate<Object> ALWAYS_TRUE = new TruePredicate();
    static final Predicate<Object> ALWAYS_FALSE = new FalsePredicate();
    static final Supplier<Object> NULL_SUPPLIER = new NullProvider();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(BiFunction<? super T1, ? super T2, ? extends R> f) {
        return new Array2Func(f);
    }

    public static <T> Function<T, T> identity() {
        return (Function<T, T>) IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) EMPTY_CONSUMER;
    }

    static final class JustValue<T, U> implements Callable<U>, Supplier<U>, Function<T, U> {
        final U value;

        JustValue(U value) {
            this.value = value;
        }

        @Override // java.util.concurrent.Callable
        public U call() {
            return this.value;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public U apply(T t) {
            return this.value;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public U get() {
            return this.value;
        }
    }

    public static <T> Supplier<T> justSupplier(T value) {
        return new JustValue(value);
    }

    public static <T, U> Function<T, U> justFunction(U value) {
        return new JustValue(value);
    }

    static final class CastToClass<T, U> implements Function<T, U> {
        final Class<U> clazz;

        CastToClass(Class<U> clazz) {
            this.clazz = clazz;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public U apply(T t) {
            return this.clazz.cast(t);
        }
    }

    public static <T, U> Function<T, U> castFunction(Class<U> target) {
        return new CastToClass(target);
    }

    static final class ArrayListCapacityCallable<T> implements Supplier<List<T>> {
        final int capacity;

        ArrayListCapacityCallable(int capacity) {
            this.capacity = capacity;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public List<T> get() {
            return new ArrayList(this.capacity);
        }
    }

    public static <T> Supplier<List<T>> createArrayList(int capacity) {
        return new ArrayListCapacityCallable(capacity);
    }

    static final class ToMapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, V>, T> {
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMapKeyValueSelector(Function<? super T, ? extends V> valueSelector, Function<? super T, ? extends K> keySelector) {
            this.valueSelector = valueSelector;
            this.keySelector = keySelector;
        }

        @Override // io.reactivex.rxjava3.functions.BiConsumer
        public void accept(Map<K, V> m, T t) throws Throwable {
            m.put(this.keySelector.apply(t), this.valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(final Function<? super T, ? extends K> keySelector, final Function<? super T, ? extends V> valueSelector) {
        return new ToMapKeyValueSelector(valueSelector, keySelector);
    }

    static final class Array2Func<T1, T2, R> implements Function<Object[], R> {
        final BiFunction<? super T1, ? super T2, ? extends R> f;

        Array2Func(BiFunction<? super T1, ? super T2, ? extends R> f) {
            this.f = f;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(Object[] a2) throws Throwable {
            if (a2.length != 2) {
                throw new IllegalArgumentException("Array of size 2 expected but got " + a2.length);
            }
            return this.f.apply(a2[0], a2[1]);
        }
    }

    static final class Identity implements Function<Object, Object> {
        @Override // io.reactivex.rxjava3.functions.Function
        public Object apply(Object v) {
            return v;
        }

        public String toString() {
            return "IdentityFunction";
        }

        Identity() {
        }
    }

    static final class EmptyRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        EmptyRunnable() {
        }
    }

    static final class EmptyAction implements Action {
        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }

        EmptyAction() {
        }
    }

    static final class EmptyConsumer implements Consumer<Object> {
        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Object v) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        EmptyConsumer() {
        }
    }

    static final class ErrorConsumer implements Consumer<Throwable> {
        ErrorConsumer() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable error) {
            RxJavaPlugins.onError(error);
        }
    }

    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable error) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(error));
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        EmptyLongConsumer() {
        }
    }

    static final class TruePredicate implements Predicate<Object> {
        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object o) {
            return true;
        }

        TruePredicate() {
        }
    }

    static final class FalsePredicate implements Predicate<Object> {
        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object o) {
            return false;
        }

        FalsePredicate() {
        }
    }

    static final class NullProvider implements Supplier<Object> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        public Object get() {
            return null;
        }

        NullProvider() {
        }
    }

    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Subscription t) {
            t.request(Long.MAX_VALUE);
        }
    }
}
