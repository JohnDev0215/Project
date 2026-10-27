package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class Predicates {
    public static <T> Predicate<T> and(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(predicate2);
        return Predicates$$Lambda$1.lambdaFactory$(predicate, predicate2);
    }

    static /* synthetic */ boolean lambda$and$0(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    public static <T> Predicate<T> negate(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return Predicates$$Lambda$2.lambdaFactory$(predicate);
    }

    static /* synthetic */ boolean lambda$negate$1(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static <T> Predicate<T> or(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(predicate2);
        return Predicates$$Lambda$3.lambdaFactory$(predicate, predicate2);
    }

    static /* synthetic */ boolean lambda$or$2(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }

    public static <T> Predicate<T> isEqual(Object obj) {
        return obj == null ? Predicates$$Lambda$4.instance : Predicates$$Lambda$5.lambdaFactory$(obj);
    }

    public static <T> Predicate<T> not(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return negate(predicate);
    }

    private Predicates() {
    }
}
