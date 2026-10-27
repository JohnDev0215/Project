package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1632a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1633b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.b.a.b.b<l<? super T>, LiveData<T>.b> f1634c = new b.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1635d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f1636e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile Object f1637f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1638g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1639h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.b implements d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final f f1640e;

        LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.f1640e = fVar;
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (this.f1640e.getLifecycle().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f1643a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        void i() {
            this.f1640e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean j(f fVar) {
            return this.f1640e == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.f1640e.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1633b) {
                obj = LiveData.this.f1637f;
                LiveData.this.f1637f = LiveData.f1632a;
            }
            LiveData.this.h(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final l<? super T> f1643a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1644b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1645c = -1;

        b(l<? super T> lVar) {
            this.f1643a = lVar;
        }

        void h(boolean z) {
            if (z == this.f1644b) {
                return;
            }
            this.f1644b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f1635d;
            boolean z2 = i == 0;
            liveData.f1635d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1635d == 0 && !this.f1644b) {
                liveData2.f();
            }
            if (this.f1644b) {
                LiveData.this.c(this);
            }
        }

        void i() {
        }

        boolean j(f fVar) {
            return false;
        }

        abstract boolean k();
    }

    public LiveData() {
        Object obj = f1632a;
        this.f1636e = obj;
        this.f1637f = obj;
        this.f1638g = -1;
        this.j = new a();
    }

    private static void a(String str) {
        if (b.b.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1644b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f1645c;
            int i2 = this.f1638g;
            if (i >= i2) {
                return;
            }
            bVar.f1645c = i2;
            bVar.f1643a.a((Object) this.f1636e);
        }
    }

    void c(LiveData<T>.b bVar) {
        if (this.f1639h) {
            this.i = true;
            return;
        }
        this.f1639h = true;
        do {
            this.i = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                b.b.a.b.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f1634c.d();
                while (dVarD.hasNext()) {
                    b((b) dVarD.next().getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.f1639h = false;
    }

    public void d(f fVar, l<? super T> lVar) {
        a("observe");
        if (fVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f1634c.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.getLifecycle().a(lifecycleBoundObserver);
    }

    protected void e() {
    }

    protected void f() {
    }

    public void g(l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f1634c.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    protected void h(T t) {
        a("setValue");
        this.f1638g++;
        this.f1636e = t;
        c(null);
    }
}
