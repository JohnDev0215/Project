package io.reactivex.internal.functions;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Comparator;
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
    static final Callable<Object> NULL_SUPPLIER = new NullCallable();
    static final Comparator<Object> NATURAL_COMPARATOR = new NaturalObjectComparator();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) EMPTY_CONSUMER;
    }

    static final class JustValue<T, U> implements Callable<U>, Function<T, U> {
        final U value;

        JustValue(U u) {
            this.value = u;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.value;
        }

        @Override // io.reactivex.functions.Function
        public U apply(T t) throws Exception {
            return this.value;
        }
    }

    public static <T> Callable<T> justCallable(T t) {
        return new JustValue(t);
    }

    static final class Identity implements Function<Object, Object> {
        @Override // io.reactivex.functions.Function
        public Object apply(Object obj) {
            return obj;
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
        @Override // io.reactivex.functions.Action
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }

        EmptyAction() {
        }
    }

    static final class EmptyConsumer implements Consumer<Object> {
        @Override // io.reactivex.functions.Consumer
        public void accept(Object obj) {
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

        @Override // io.reactivex.functions.Consumer
        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        EmptyLongConsumer() {
        }
    }

    static final class TruePredicate implements Predicate<Object> {
        TruePredicate() {
        }
    }

    static final class FalsePredicate implements Predicate<Object> {
        FalsePredicate() {
        }
    }

    static final class NullCallable implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }

        NullCallable() {
        }
    }

    static final class NaturalObjectComparator implements Comparator<Object> {
        NaturalObjectComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }
}
