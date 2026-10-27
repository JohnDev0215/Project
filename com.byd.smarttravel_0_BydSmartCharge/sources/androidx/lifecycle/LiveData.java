package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.m.d;
import b.m.f;
import b.m.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f404a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f405b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.c.a.b.b<l<? super T>, LiveData<T>.b> f406c = new b.c.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f407d = 0;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final Runnable j;

    public class LifecycleBoundObserver extends LiveData<T>.b implements d {
        public final f e;

        public LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.e = fVar;
        }

        @Override // b.m.d
        public void d(f fVar, Lifecycle.Event event) {
            if (this.e.getLifecycle().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f409a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public void i() {
            this.e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean j(f fVar) {
            return this.e == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean k() {
            return this.e.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f405b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.f404a;
            }
            LiveData.this.h(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l<? super T> f409a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f410b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f411c = -1;

        public b(l<? super T> lVar) {
            this.f409a = lVar;
        }

        public void h(boolean z) {
            if (z == this.f410b) {
                return;
            }
            this.f410b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.f407d;
            boolean z2 = i == 0;
            liveData.f407d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f407d == 0 && !this.f410b) {
                liveData2.f();
            }
            if (this.f410b) {
                LiveData.this.c(this);
            }
        }

        public void i() {
        }

        public boolean j(f fVar) {
            return false;
        }

        public abstract boolean k();
    }

    public LiveData() {
        Object obj = f404a;
        this.e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new a();
    }

    public static void a(String str) {
        if (b.c.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void b(LiveData<T>.b bVar) {
        if (bVar.f410b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.f411c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.f411c = i2;
            bVar.f409a.a((Object) this.e);
        }
    }

    public void c(LiveData<T>.b bVar) {
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
                b.c.a.b.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f406c.d();
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
        if (fVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f406c.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void e() {
    }

    public void f() {
    }

    public void g(l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f406c.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    public void h(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        c(null);
    }
}
