package defpackage;

import android.os.Handler;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.b;

/* JADX INFO: compiled from: ObservableSupplierImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class aq1<E> implements zp1<E> {
    public final Handler e;
    public E f;
    public final b<Callback<E>> g;

    public aq1() {
        Thread.currentThread();
        this.e = new Handler();
        this.g = new b<>();
    }

    @Override // defpackage.zp1
    public final void d(Callback<E> callback) {
        this.g.c(callback);
    }

    @Override // defpackage.nw2
    public final E get() {
        return this.f;
    }

    @Override // defpackage.zp1
    public final E p(Callback<E> callback) {
        this.g.a(callback);
        E e = this.f;
        if (e != null) {
            this.e.post(new b80(this, 1, e, callback));
        }
        return this.f;
    }

    public void r(E e) {
        if (e == this.f) {
            return;
        }
        this.f = e;
        Iterator<Callback<E>> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Callback) aVar.next()).onResult(this.f);
            }
        }
    }
}
