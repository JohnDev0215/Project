package r1;

import java.util.Queue;
import r1.l;

/* JADX INFO: compiled from: BaseKeyPool.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c<T extends l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Queue<T> f9332a = k2.k.e(20);

    c() {
    }

    abstract T a();

    T b() {
        T tPoll = this.f9332a.poll();
        return tPoll == null ? (T) a() : tPoll;
    }

    public void c(T t6) {
        if (this.f9332a.size() < 20) {
            this.f9332a.offer(t6);
        }
    }
}
