package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1615a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1616b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.b.a.b.b<l<? super T>, LiveData<T>.b> f1617c = new c.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1618d = 0;
    private volatile Object e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.b implements d {
        final f e;

        LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.e = fVar;
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (this.e.a().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f1620a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        void i() {
            this.e.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean j(f fVar) {
            return this.e == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.e.a().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1616b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.f1615a;
            }
            LiveData.this.h(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final l<? super T> f1620a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1621b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1622c = -1;

        b(l<? super T> lVar) {
            this.f1620a = lVar;
        }

        void h(boolean z) {
            if (z == this.f1621b) {
                return;
            }
            this.f1621b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f1618d;
            boolean z2 = i == 0;
            liveData.f1618d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1618d == 0 && !this.f1621b) {
                liveData2.f();
            }
            if (this.f1621b) {
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
        Object obj = f1615a;
        this.e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new a();
    }

    private static void a(String str) {
        if (c.b.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1621b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f1622c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.f1622c = i2;
            bVar.f1620a.a((Object) this.e);
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
                c.b.a.b.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f1617c.d();
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

    public void d(f fVar, l<? super T> lVar) {
        a("observe");
        if (fVar.a().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f1617c.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.a().a(lifecycleBoundObserver);
    }

    protected void e() {
    }

    protected void f() {
    }

    public void g(l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f1617c.h(lVar);
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
