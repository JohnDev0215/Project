package androidx.lifecycle;

import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1572a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1573b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.b.a.b.b<m<? super T>, LiveData<T>.b> f1574c = new b.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1575d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f1576e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile Object f1577f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1578g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1579h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1580i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.b implements e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g f1581e;

        LifecycleBoundObserver(g gVar, m<? super T> mVar) {
            super(mVar);
            this.f1581e = gVar;
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, d.a aVar) {
            if (this.f1581e.a().b() == d.b.DESTROYED) {
                LiveData.this.g(this.f1584a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        void i() {
            this.f1581e.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean j(g gVar) {
            return this.f1581e == gVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.f1581e.a().b().a(d.b.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1573b) {
                obj = LiveData.this.f1577f;
                LiveData.this.f1577f = LiveData.f1572a;
            }
            LiveData.this.h(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final m<? super T> f1584a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1585b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1586c = -1;

        b(m<? super T> mVar) {
            this.f1584a = mVar;
        }

        void h(boolean z) {
            if (z == this.f1585b) {
                return;
            }
            this.f1585b = z;
            LiveData liveData = LiveData.this;
            int i2 = liveData.f1575d;
            boolean z2 = i2 == 0;
            liveData.f1575d = i2 + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1575d == 0 && !this.f1585b) {
                liveData2.f();
            }
            if (this.f1585b) {
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
        Object obj = f1572a;
        this.f1577f = obj;
        this.j = new a();
        this.f1576e = obj;
        this.f1578g = -1;
    }

    static void a(String str) {
        if (b.b.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1585b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i2 = bVar.f1586c;
            int i3 = this.f1578g;
            if (i2 >= i3) {
                return;
            }
            bVar.f1586c = i3;
            bVar.f1584a.a((Object) this.f1576e);
        }
    }

    void c(LiveData<T>.b bVar) {
        if (this.f1579h) {
            this.f1580i = true;
            return;
        }
        this.f1579h = true;
        do {
            this.f1580i = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                b.b.a.b.b<m<? super T>, LiveData<T>.b>.d dVarD = this.f1574c.d();
                while (dVarD.hasNext()) {
                    b((b) dVarD.next().getValue());
                    if (this.f1580i) {
                        break;
                    }
                }
            }
        } while (this.f1580i);
        this.f1579h = false;
    }

    public void d(g gVar, m<? super T> mVar) {
        a("observe");
        if (gVar.a().b() == d.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, mVar);
        LiveData<T>.b bVarG = this.f1574c.g(mVar, lifecycleBoundObserver);
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
        LiveData<T>.b bVarH = this.f1574c.h(mVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    protected void h(T t) {
        a("setValue");
        this.f1578g++;
        this.f1576e = t;
        c(null);
    }
}
