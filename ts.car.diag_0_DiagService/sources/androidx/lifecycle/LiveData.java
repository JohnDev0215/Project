package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    static final Object j = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f754a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.arch.core.b.b<l<? super T>, LiveData<T>.b> f755b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f756c;
    private boolean d;
    private volatile Object e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;

    /* JADX INFO: compiled from: caradapter-bug */
    class LifecycleBoundObserver extends LiveData<T>.b implements d {

        @NonNull
        final f g;

        LifecycleBoundObserver(@NonNull f fVar, l<? super T> lVar) {
            super(lVar);
            this.g = fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        void c() {
            this.g.a().c(this);
        }

        @Override // androidx.lifecycle.d
        public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
            Lifecycle.State stateB = this.g.a().b();
            if (stateB == Lifecycle.State.DESTROYED) {
                LiveData.this.k(this.f757c);
                return;
            }
            Lifecycle.State state = null;
            while (state != stateB) {
                a(k());
                state = stateB;
                stateB = this.g.a().b();
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean g(f fVar) {
            return this.g == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return this.g.a().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class a extends LiveData<T>.b {
        a(LiveData liveData, l<? super T> lVar) {
            super(lVar);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean k() {
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private abstract class b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final l<? super T> f757c;
        boolean d;
        int e = -1;

        b(l<? super T> lVar) {
            this.f757c = lVar;
        }

        void a(boolean z) {
            if (z == this.d) {
                return;
            }
            this.d = z;
            LiveData.this.b(z ? 1 : -1);
            if (this.d) {
                LiveData.this.d(this);
            }
        }

        void c() {
        }

        boolean g(f fVar) {
            return false;
        }

        abstract boolean k();
    }

    public LiveData() {
        Object obj = j;
        this.f754a = new Object();
        this.f755b = new androidx.arch.core.b.b<>();
        this.f756c = 0;
        this.f = obj;
        this.e = obj;
        this.g = -1;
    }

    static void a(String str) {
        if (androidx.arch.core.a.a.b().a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void c(LiveData<T>.b bVar) {
        if (bVar.d) {
            if (!bVar.k()) {
                bVar.a(false);
                return;
            }
            int i = bVar.e;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.e = i2;
            bVar.f757c.a((Object) this.e);
        }
    }

    @MainThread
    void b(int i) {
        int i2 = this.f756c;
        this.f756c = i + i2;
        if (this.d) {
            return;
        }
        this.d = true;
        while (true) {
            try {
                if (i2 == this.f756c) {
                    return;
                }
                boolean z = i2 == 0 && this.f756c > 0;
                boolean z2 = i2 > 0 && this.f756c == 0;
                int i3 = this.f756c;
                if (z) {
                    i();
                } else if (z2) {
                    j();
                }
                i2 = i3;
            } finally {
                this.d = false;
            }
        }
    }

    void d(@Nullable LiveData<T>.b bVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (bVar != null) {
                c(bVar);
                bVar = null;
            } else {
                androidx.arch.core.b.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f755b.d();
                while (dVarD.hasNext()) {
                    c((b) dVarD.next().getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    @Nullable
    public T e() {
        T t = (T) this.e;
        if (t != j) {
            return t;
        }
        return null;
    }

    public boolean f() {
        return this.f756c > 0;
    }

    @MainThread
    public void g(@NonNull f fVar, @NonNull l<? super T> lVar) {
        a("observe");
        if (fVar.a().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f755b.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.g(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.a().a(lifecycleBoundObserver);
    }

    @MainThread
    public void h(@NonNull l<? super T> lVar) {
        a("observeForever");
        a aVar = new a(this, lVar);
        LiveData<T>.b bVarG = this.f755b.g(lVar, aVar);
        if (bVarG instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        aVar.a(true);
    }

    protected void i() {
    }

    protected void j() {
    }

    @MainThread
    public void k(@NonNull l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f755b.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.c();
        bVarH.a(false);
    }

    @MainThread
    protected void l(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        d(null);
    }
}
