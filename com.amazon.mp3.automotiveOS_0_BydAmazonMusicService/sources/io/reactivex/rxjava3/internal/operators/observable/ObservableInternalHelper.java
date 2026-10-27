package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public final class ObservableInternalHelper {
    private ObservableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    static final class FlatMapWithCombinerInner<U, R, T> implements Function<U, R> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final T t;

        FlatMapWithCombinerInner(BiFunction<? super T, ? super U, ? extends R> combiner, T t) {
            this.combiner = combiner;
            this.t = t;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(U u) throws Throwable {
            return this.combiner.apply(this.t, u);
        }
    }

    static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, ObservableSource<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends ObservableSource<? extends U>> mapper;

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> combiner, Function<? super T, ? extends ObservableSource<? extends U>> mapper) {
            this.combiner = combiner;
            this.mapper = mapper;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public ObservableSource<R> apply(final T t) throws Throwable {
            ObservableSource<? extends U> observableSourceApply = this.mapper.apply(t);
            Objects.requireNonNull(observableSourceApply, "The mapper returned a null ObservableSource");
            return new ObservableMap(observableSourceApply, new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    public static <T, U, R> Function<T, ObservableSource<R>> flatMapWithCombiner(final Function<? super T, ? extends ObservableSource<? extends U>> mapper, final BiFunction<? super T, ? super U, ? extends R> combiner) {
        return new FlatMapWithCombinerOuter(combiner, mapper);
    }
}
