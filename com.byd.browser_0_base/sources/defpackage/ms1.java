package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: OneShotCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class ms1<E> {
    public final a a;
    public final WeakReference<zp1<E>> b;
    public final Callback<E> c;

    /* JADX INFO: compiled from: OneShotCallback.java */
    public class a implements Callback<E> {
        public a() {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(E e) {
            ms1 ms1Var = ms1.this;
            ms1Var.c.onResult(e);
            ms1Var.b.get().d(ms1Var.a);
        }
    }

    public ms1(zp1<E> zp1Var, Callback<E> callback) {
        a aVar = new a();
        this.a = aVar;
        this.b = new WeakReference<>(zp1Var);
        this.c = callback;
        zp1Var.p(aVar);
    }
}
