package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import x0.d;
import x0.f;
import x0.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1441a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1442b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t.b<l<? super T>, LiveData<T>.b> f1443c = new t.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1444d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1445e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f1446f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile Object f1447g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1448h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1449i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1450j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Runnable f1451k;

    public class LifecycleBoundObserver extends LiveData<T>.b implements d {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final f f1452h;

        public LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.f1452h = fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public void e() {
            this.f1452h.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean f(f fVar) {
            return this.f1452h == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean g() {
            return this.f1452h.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }

        @Override // x0.d
        public void onStateChanged(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateB = this.f1452h.getLifecycle().b();
            if (stateB == Lifecycle.State.DESTROYED) {
                LiveData.this.h(this.f1455d);
                return;
            }
            Lifecycle.State state = null;
            while (state != stateB) {
                d(g());
                state = stateB;
                stateB = this.f1452h.getLifecycle().b();
            }
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1442b) {
                obj = LiveData.this.f1447g;
                LiveData.this.f1447g = LiveData.f1441a;
            }
            LiveData.this.i(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l<? super T> f1455d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1456e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1457f = -1;

        public b(l<? super T> lVar) {
            this.f1455d = lVar;
        }

        public void d(boolean z7) {
            if (z7 == this.f1456e) {
                return;
            }
            this.f1456e = z7;
            LiveData.this.b(z7 ? 1 : -1);
            if (this.f1456e) {
                LiveData.this.d(this);
            }
        }

        public void e() {
        }

        public boolean f(f fVar) {
            return false;
        }

        public abstract boolean g();
    }

    public LiveData() {
        Object obj = f1441a;
        this.f1447g = obj;
        this.f1451k = new a();
        this.f1446f = obj;
        this.f1448h = -1;
    }

    public static void a(String str) {
        if (s.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public void b(int i8) {
        int i9 = this.f1444d;
        this.f1444d = i8 + i9;
        if (this.f1445e) {
            return;
        }
        this.f1445e = true;
        while (true) {
            try {
                int i10 = this.f1444d;
                if (i9 == i10) {
                    return;
                }
                boolean z7 = i9 == 0 && i10 > 0;
                boolean z8 = i9 > 0 && i10 == 0;
                if (z7) {
                    f();
                } else if (z8) {
                    g();
                }
                i9 = i10;
            } finally {
                this.f1445e = false;
            }
        }
    }

    public final void c(LiveData<T>.b bVar) {
        if (bVar.f1456e) {
            if (!bVar.g()) {
                bVar.d(false);
                return;
            }
            int i8 = bVar.f1457f;
            int i9 = this.f1448h;
            if (i8 >= i9) {
                return;
            }
            bVar.f1457f = i9;
            bVar.f1455d.a((Object) this.f1446f);
        }
    }

    public void d(LiveData<T>.b bVar) {
        if (this.f1449i) {
            this.f1450j = true;
            return;
        }
        this.f1449i = true;
        do {
            this.f1450j = false;
            if (bVar != null) {
                c(bVar);
                bVar = null;
            } else {
                t.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f1443c.d();
                while (dVarD.hasNext()) {
                    c((b) dVarD.next().getValue());
                    if (this.f1450j) {
                        break;
                    }
                }
            }
        } while (this.f1450j);
        this.f1449i = false;
    }

    public void e(f fVar, l<? super T> lVar) {
        a("observe");
        if (fVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f1443c.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.f(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void f() {
    }

    public void g() {
    }

    public void h(l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f1443c.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.e();
        bVarH.d(false);
    }

    public void i(T t7) {
        a("setValue");
        this.f1448h++;
        this.f1446f = t7;
        d(null);
    }
}
