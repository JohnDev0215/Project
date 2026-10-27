package b.a.d.d;

import b.a.d.d.e;

/* JADX INFO: compiled from: XView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface f<P extends e> extends b<P> {
    void a(P p);

    P b();

    int c();

    default void d() {
    }

    default P e() {
        P p = (P) b();
        if (p != null) {
            return p;
        }
        P p2 = (P) f();
        a(p2);
        if (p2 != null) {
            p2.a(this);
        }
        return p2;
    }

    P f();
}
