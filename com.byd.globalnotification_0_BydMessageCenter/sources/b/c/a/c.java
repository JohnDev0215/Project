package b.c.a;

import a.n.k;
import a.n.p;
import a.n.q;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class c<T> extends p<T> {
    public final AtomicBoolean k = new AtomicBoolean(false);

    public class a implements q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ q f1728a;

        public a(q qVar) {
            this.f1728a = qVar;
        }

        @Override // a.n.q
        public void a(T t) {
            if (c.this.k.compareAndSet(true, false)) {
                this.f1728a.a(t);
            }
        }
    }

    @Override // androidx.lifecycle.LiveData
    public void d(k kVar, q<? super T> qVar) {
        if (this.f1572c > 0) {
            Log.w("SingleLiveEvent", "Multiple observers registered but only one will be notified of changes.");
        }
        super.d(kVar, new a(qVar));
    }

    @Override // a.n.p, androidx.lifecycle.LiveData
    public void h(T t) {
        this.k.set(true);
        super.h(t);
    }

    public void j() {
        h(null);
    }
}
