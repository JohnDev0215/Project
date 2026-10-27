package androidx.databinding;

import java.util.List;

/* JADX INFO: compiled from: ObservableList.java */
/* JADX INFO: loaded from: classes.dex */
public interface m<T> extends List<T> {

    /* JADX INFO: compiled from: ObservableList.java */
    public static abstract class a<T extends m> {
        public abstract void c(T t6);

        public abstract void e(T t6, int i7, int i8);

        public abstract void f(T t6, int i7, int i8);

        public abstract void g(T t6, int i7, int i8, int i9);

        public abstract void h(T t6, int i7, int i8);
    }

    void a(a<? extends m<T>> aVar);

    void b(a<? extends m<T>> aVar);
}
