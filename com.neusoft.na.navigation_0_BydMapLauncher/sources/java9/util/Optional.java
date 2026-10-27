package java9.util;

import java.util.NoSuchElementException;
import java9.util.function.Consumer;
import java9.util.function.Function;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.stream.RefStreams;
import java9.util.stream.Stream;

/* JADX INFO: loaded from: classes3.dex */
public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>(null);
    private final T value;

    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    private Optional(T t) {
        this.value = t;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<>(Objects.requireNonNull(t));
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? (Optional<T>) EMPTY : new Optional<>(t);
    }

    public T get() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public boolean isEmpty() {
        return this.value == null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        T t = this.value;
        if (t != null) {
            consumer.accept(t);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable runnable) {
        T t = this.value;
        if (t != null) {
            consumer.accept(t);
        } else {
            runnable.run();
        }
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isPresent() && !predicate.test(this.value)) {
            return empty();
        }
        return this;
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        if (!isPresent()) {
            return empty();
        }
        return ofNullable(function.apply(this.value));
    }

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> function) {
        Objects.requireNonNull(function);
        if (!isPresent()) {
            return empty();
        }
        return (Optional) Objects.requireNonNull(function.apply(this.value));
    }

    public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier) {
        Objects.requireNonNull(supplier);
        return isPresent() ? this : (Optional) Objects.requireNonNull(supplier.get());
    }

    public Stream<T> stream() {
        if (!isPresent()) {
            return RefStreams.empty();
        }
        return RefStreams.of(this.value);
    }

    public T orElse(T t) {
        T t2 = this.value;
        return t2 != null ? t2 : t;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        T t = this.value;
        return t != null ? t : supplier.get();
    }

    public T orElseThrow() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            return Objects.equals(this.value, ((Optional) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        T t = this.value;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
