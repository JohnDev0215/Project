package androidx.lifecycle;

import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1382a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1383b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.b.a.b.b<m<? super T>, LiveData<T>.b> f1384c = new a.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1385d = 0;
    private volatile Object e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.b implements e {
        final g e;

        LifecycleBoundObserver(g gVar, m<? super T> mVar) {
            super(mVar);
            this.e = gVar;
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, d.a aVar) {
            if (this.e.a().b() == d.b.DESTROYED) {
                LiveData.this.g(this.f1387a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        void i() {
            this.e.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean j(g gVar) {
            return this.e == gVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.e.a().b().a(d.b.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1383b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.f1382a;
            }
            LiveData.this.h(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final m<? super T> f1387a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1388b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1389c = -1;

        b(m<? super T> mVar) {
            this.f1387a = mVar;
        }

        void h(boolean z) {
            if (z == this.f1388b) {
                return;
            }
            this.f1388b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f1385d;
            boolean z2 = i == 0;
            liveData.f1385d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1385d == 0 && !this.f1388b) {
                liveData2.f();
            }
            if (this.f1388b) {
                LiveData.this.c(this);
            }
        }

        void i() {
        }

        boolean j(g gVar) {
            return false;
        }

        abstract boolean k();
    }

    public LiveData() {
        Object obj = f1382a;
        this.e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new a();
    }

    private static void a(String str) {
        if (a.b.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1388b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f1389c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.f1389c = i2;
            bVar.f1387a.a((Object) this.e);
        }
    }

    void c(LiveData<T>.b bVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                a.b.a.b.b<m<? super T>, LiveData<T>.b>.d dVarD = this.f1384c.d();
                while (dVarD.hasNext()) {
                    b((b) dVarD.next().getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public void d(g gVar, m<? super T> mVar) {
        a("observe");
        if (gVar.a().b() == d.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, mVar);
        LiveData<T>.b bVarG = this.f1384c.g(mVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(gVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        gVar.a().a(lifecycleBoundObserver);
    }

    protected void e() {
    }

    protected void f() {
    }

    public void g(m<? super T> mVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f1384c.h(mVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    protected void h(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        c(null);
    }
}
