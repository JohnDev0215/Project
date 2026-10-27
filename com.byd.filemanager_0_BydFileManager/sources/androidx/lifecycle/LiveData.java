package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Object f1227a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object f1228b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.b.a.b.b<m<? super T>, LiveData<T>.b> f1229c = new b.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1230d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f1231e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends LiveData<T>.b implements e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g f1232e;

        LifecycleBoundObserver(g gVar, m<? super T> mVar) {
            super(mVar);
            this.f1232e = gVar;
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, Lifecycle.Event event) {
            if (this.f1232e.a().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f1234a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        void i() {
            this.f1232e.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean j(g gVar) {
            return this.f1232e == gVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.f1232e.a().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1228b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.f1227a;
            }
            LiveData.this.h(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final m<? super T> f1234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1235b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1236c = -1;

        b(m<? super T> mVar) {
            this.f1234a = mVar;
        }

        void h(boolean z) {
            if (z == this.f1235b) {
                return;
            }
            this.f1235b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f1230d;
            boolean z2 = i == 0;
            liveData.f1230d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1230d == 0 && !this.f1235b) {
                liveData2.f();
            }
            if (this.f1235b) {
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
        Object obj = f1227a;
        this.f1231e = obj;
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

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1235b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f1236c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.f1236c = i2;
            bVar.f1234a.a((Object) this.f1231e);
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
                b.b.a.b.b<m<? super T>, LiveData<T>.b>.d dVarD = this.f1229c.d();
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
        if (gVar.a().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, mVar);
        LiveData<T>.b bVarG = this.f1229c.g(mVar, lifecycleBoundObserver);
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
        LiveData<T>.b bVarH = this.f1229c.h(mVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    protected void h(T t) {
        a("setValue");
        this.g++;
        this.f1231e = t;
        c(null);
    }
}
