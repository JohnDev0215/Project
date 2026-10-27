package c.a.a.b;

import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: compiled from: IterUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static <F, T> Iterator<T> a(Iterator<F> it, Function<? super F, ? extends T> function) {
        return new i(it, function);
    }
}
