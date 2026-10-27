package spi;

import java.util.ArrayDeque;
import java.util.Queue;
import spi.al;

/* JADX INFO: loaded from: classes.dex */
public abstract class g1<T extends al> {
    public final Queue<T> a;

    public g1() {
        char[] cArr = tu.a;
        this.a = new ArrayDeque(20);
    }

    public abstract T a();

    public final T b() {
        T t = (T) this.a.poll();
        return t == null ? (T) a() : t;
    }

    public final void c(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
