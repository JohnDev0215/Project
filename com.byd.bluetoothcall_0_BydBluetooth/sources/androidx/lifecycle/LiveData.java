package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1476a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1477b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.b.a.b.b<n<? super T>, LiveData<T>.c> f1478c = new b.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1479d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f1480e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.c implements e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g f1481e;

        LifecycleBoundObserver(g gVar, n<? super T> nVar) {
            super(nVar);
            this.f1481e = gVar;
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, Lifecycle.Event event) {
            if (this.f1481e.getLifecycle().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.j(this.f1484a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.c
        void i() {
            this.f1481e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean j(g gVar) {
            return this.f1481e == gVar;
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean k() {
            return this.f1481e.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1477b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.f1476a;
            }
            LiveData.this.k(obj);
        }
    }

    private class b extends LiveData<T>.c {
        b(n<? super T> nVar) {
            super(nVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean k() {
            return true;
        }
    }

    private abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final n<? super T> f1484a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1485b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1486c = -1;

        c(n<? super T> nVar) {
            this.f1484a = nVar;
        }

        void h(boolean z) {
            if (z == this.f1485b) {
                return;
            }
            this.f1485b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f1479d;
            boolean z2 = i == 0;
            liveData.f1479d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.h();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1479d == 0 && !this.f1485b) {
                liveData2.i();
            }
            if (this.f1485b) {
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
        Object obj = f1476a;
        this.f1480e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new a();
    }

    private static void a(String str) {
        if (b.b.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(LiveData<T>.c cVar) {
        if (cVar.f1485b) {
            if (!cVar.k()) {
                cVar.h(false);
                return;
            }
            int i = cVar.f1486c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            cVar.f1486c = i2;
            cVar.f1484a.a((Object) this.f1480e);
        }
    }

    void c(LiveData<T>.c cVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (cVar != null) {
                b(cVar);
                cVar = null;
            } else {
                b.b.a.b.b<n<? super T>, LiveData<T>.c>.d dVarD = this.f1478c.d();
                while (dVarD.hasNext()) {
                    b((c) dVarD.next().getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    int d() {
        return this.g;
    }

    public boolean e() {
        return this.f1479d > 0;
    }

    public void f(g gVar, n<? super T> nVar) {
        a("observe");
        if (gVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, nVar);
        LiveData<T>.c cVarG = this.f1478c.g(nVar, lifecycleBoundObserver);
        if (cVarG != null && !cVarG.j(gVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarG != null) {
            return;
        }
        gVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void g(n<? super T> nVar) {
        a("observeForever");
        b bVar = new b(nVar);
        LiveData<T>.c cVarG = this.f1478c.g(nVar, bVar);
        if (cVarG != null && (cVarG instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarG != null) {
            return;
        }
        bVar.h(true);
    }

    protected void h() {
    }

    protected void i() {
    }

    public void j(n<? super T> nVar) {
        a("removeObserver");
        LiveData<T>.c cVarH = this.f1478c.h(nVar);
        if (cVarH == null) {
            return;
        }
        cVarH.i();
        cVarH.h(false);
    }

    protected void k(T t) {
        a("setValue");
        this.g++;
        this.f1480e = t;
        c(null);
    }
}
