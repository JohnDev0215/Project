package c.a.a.b;

import java.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: compiled from: SpliteratorUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static <F, T> Spliterator<T> a(Spliterator<F> spliterator, Function<? super F, ? extends T> function) {
        return new j(spliterator, function);
    }
}
