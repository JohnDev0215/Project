package a.k;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface n<T> extends List<T> {

    public static abstract class a<T extends n> {
        public abstract void d(T t);

        public abstract void e(T t, int i, int i2);

        public abstract void f(T t, int i, int i2);

        public abstract void g(T t, int i, int i2, int i3);

        public abstract void h(T t, int i, int i2);
    }

    void a(a<? extends n<T>> aVar);

    void b(a<? extends n<T>> aVar);
}
