package androidx.lifecycle;

import androidx.lifecycle.i;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final Object f2501k = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f2502a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private l.b<u<? super T>, LiveData<T>.c> f2503b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2504c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2505d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f2506e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile Object f2507f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2508g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2509h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2510i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Runnable f2511j;

    class LifecycleBoundObserver extends LiveData<T>.c implements m {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final o f2512j;

        LifecycleBoundObserver(o oVar, u<? super T> uVar) {
            super(uVar);
            this.f2512j = oVar;
        }

        @Override // androidx.lifecycle.m
        public void d(o oVar, i.b bVar) {
            i.c cVarB = this.f2512j.a().b();
            if (cVarB == i.c.DESTROYED) {
                LiveData.this.k(this.f2516e);
                return;
            }
            i.c cVar = null;
            while (cVar != cVarB) {
                h(k());
                cVar = cVarB;
                cVarB = this.f2512j.a().b();
            }
        }

        @Override // androidx.lifecycle.LiveData.c
        void i() {
            this.f2512j.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean j(o oVar) {
            return this.f2512j == oVar;
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean k() {
            return this.f2512j.a().b().isAtLeast(i.c.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f2502a) {
                obj = LiveData.this.f2507f;
                LiveData.this.f2507f = LiveData.f2501k;
            }
            LiveData.this.m(obj);
        }
    }

    private class b extends LiveData<T>.c {
        b(u<? super T> uVar) {
            super(uVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean k() {
            return true;
        }
    }

    private abstract class c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final u<? super T> f2516e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f2517g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f2518h = -1;

        c(u<? super T> uVar) {
            this.f2516e = uVar;
        }

        void h(boolean z6) {
            if (z6 == this.f2517g) {
                return;
            }
            this.f2517g = z6;
            LiveData.this.b(z6 ? 1 : -1);
            if (this.f2517g) {
                LiveData.this.d(this);
            }
        }

        void i() {
        }

        boolean j(o oVar) {
            return false;
        }

        abstract boolean k();
    }

    public LiveData(T t6) {
        this.f2502a = new Object();
        this.f2503b = new l.b<>();
        this.f2504c = 0;
        this.f2507f = f2501k;
        this.f2511j = new a();
        this.f2506e = t6;
        this.f2508g = 0;
    }

    static void a(String str) {
        if (k.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void c(LiveData<T>.c cVar) {
        if (cVar.f2517g) {
            if (!cVar.k()) {
                cVar.h(false);
                return;
            }
            int i7 = cVar.f2518h;
            int i8 = this.f2508g;
            if (i7 >= i8) {
                return;
            }
            cVar.f2518h = i8;
            cVar.f2516e.c((Object) this.f2506e);
        }
    }

    void b(int i7) {
        int i8 = this.f2504c;
        this.f2504c = i7 + i8;
        if (this.f2505d) {
            return;
        }
        this.f2505d = true;
        while (true) {
            try {
                int i9 = this.f2504c;
                if (i8 == i9) {
                    return;
                }
                boolean z6 = i8 == 0 && i9 > 0;
                boolean z7 = i8 > 0 && i9 == 0;
                if (z6) {
                    h();
                } else if (z7) {
                    i();
                }
                i8 = i9;
            } finally {
                this.f2505d = false;
            }
        }
    }

    void d(LiveData<T>.c cVar) {
        if (this.f2509h) {
            this.f2510i = true;
            return;
        }
        this.f2509h = true;
        do {
            this.f2510i = false;
            if (cVar != null) {
                c(cVar);
                cVar = null;
            } else {
                l.b<u<? super T>, LiveData<T>.c>.d dVarF = this.f2503b.f();
                while (dVarF.hasNext()) {
                    c((c) dVarF.next().getValue());
                    if (this.f2510i) {
                        break;
                    }
                }
            }
        } while (this.f2510i);
        this.f2509h = false;
    }

    public T e() {
        T t6 = (T) this.f2506e;
        if (t6 != f2501k) {
            return t6;
        }
        return null;
    }

    public void f(o oVar, u<? super T> uVar) {
        a("observe");
        if (oVar.a().b() == i.c.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(oVar, uVar);
        LiveData<T>.c cVarI = this.f2503b.i(uVar, lifecycleBoundObserver);
        if (cVarI != null && !cVarI.j(oVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarI != null) {
            return;
        }
        oVar.a().a(lifecycleBoundObserver);
    }

    public void g(u<? super T> uVar) {
        a("observeForever");
        b bVar = new b(uVar);
        LiveData<T>.c cVarI = this.f2503b.i(uVar, bVar);
        if (cVarI instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarI != null) {
            return;
        }
        bVar.h(true);
    }

    protected void h() {
    }

    protected void i() {
    }

    protected void j(T t6) {
        boolean z6;
        synchronized (this.f2502a) {
            z6 = this.f2507f == f2501k;
            this.f2507f = t6;
        }
        if (z6) {
            k.a.e().c(this.f2511j);
        }
    }

    public void k(u<? super T> uVar) {
        a("removeObserver");
        LiveData<T>.c cVarJ = this.f2503b.j(uVar);
        if (cVarJ == null) {
            return;
        }
        cVarJ.i();
        cVarJ.h(false);
    }

    public void l(o oVar) {
        a("removeObservers");
        for (Map.Entry<u<? super T>, LiveData<T>.c> entry : this.f2503b) {
            if (entry.getValue().j(oVar)) {
                k(entry.getKey());
            }
        }
    }

    protected void m(T t6) {
        a("setValue");
        this.f2508g++;
        this.f2506e = t6;
        d(null);
    }

    public LiveData() {
        this.f2502a = new Object();
        this.f2503b = new l.b<>();
        this.f2504c = 0;
        Object obj = f2501k;
        this.f2507f = obj;
        this.f2511j = new a();
        this.f2506e = obj;
        this.f2508g = -1;
    }
}
