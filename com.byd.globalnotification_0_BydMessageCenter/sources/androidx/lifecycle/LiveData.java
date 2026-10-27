package androidx.lifecycle;

import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.n.q;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object j = new Object();
    public boolean g;
    public boolean h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1570a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.c.a.b.b<q<? super T>, LiveData<T>.b> f1571b = new a.c.a.b.b<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1572c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f1574e = j;
    public final Runnable i = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Object f1573d = j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1575f = -1;

    public class LifecycleBoundObserver extends LiveData<T>.b implements i {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final k f1576e;

        public LifecycleBoundObserver(k kVar, q<? super T> qVar) {
            super(qVar);
            this.f1576e = kVar;
        }

        @Override // a.n.i
        public void g(k kVar, g.a aVar) {
            if (((l) this.f1576e.a()).f1094b == g.b.DESTROYED) {
                LiveData.this.g(this.f1579a);
            } else {
                h(i());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean i() {
            return ((l) this.f1576e.a()).f1094b.compareTo(g.b.STARTED) >= 0;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1570a) {
                obj = LiveData.this.f1574e;
                LiveData.this.f1574e = LiveData.j;
            }
            LiveData.this.h(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q<? super T> f1579a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1581c = -1;

        public b(q<? super T> qVar) {
            this.f1579a = qVar;
        }

        public void h(boolean z) {
            if (z == this.f1580b) {
                return;
            }
            this.f1580b = z;
            boolean z2 = LiveData.this.f1572c == 0;
            LiveData.this.f1572c += this.f1580b ? 1 : -1;
            if (z2 && this.f1580b) {
                LiveData.this.e();
            }
            LiveData liveData = LiveData.this;
            if (liveData.f1572c == 0 && !this.f1580b) {
                liveData.f();
            }
            if (this.f1580b) {
                LiveData.this.c(this);
            }
        }

        public abstract boolean i();
    }

    public static void a(String str) {
        if (a.c.a.a.a.c().f418a.a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void b(LiveData<T>.b bVar) {
        if (bVar.f1580b) {
            if (!bVar.i()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f1581c;
            int i2 = this.f1575f;
            if (i >= i2) {
                return;
            }
            bVar.f1581c = i2;
            bVar.f1579a.a((Object) this.f1573d);
        }
    }

    public void c(LiveData<T>.b bVar) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                a.c.a.b.b<q<? super T>, LiveData<T>.b>.d dVarD = this.f1571b.d();
                while (dVarD.hasNext()) {
                    b((b) ((Map.Entry) dVarD.next()).getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
        } while (this.h);
        this.g = false;
    }

    public void d(k kVar, q<? super T> qVar) {
        a("observe");
        if (((l) kVar.a()).f1094b == g.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(kVar, qVar);
        LiveData<T>.b bVarF = this.f1571b.f(qVar, lifecycleBoundObserver);
        if (bVarF != null) {
            if (!(((LifecycleBoundObserver) bVarF).f1576e == kVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
        }
        if (bVarF != null) {
            return;
        }
        kVar.a().a(lifecycleBoundObserver);
    }

    public void e() {
    }

    public void f() {
    }

    public void g(q<? super T> qVar) {
        a("removeObserver");
        LiveData<T>.b bVarG = this.f1571b.g(qVar);
        if (bVarG == null) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = (LifecycleBoundObserver) bVarG;
        ((l) lifecycleBoundObserver.f1576e.a()).f1093a.g(lifecycleBoundObserver);
        bVarG.h(false);
    }

    public abstract void h(T t);
}
