package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import defpackage.cc;
import defpackage.ec;
import defpackage.kc;
import defpackage.o3;
import defpackage.s3;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object a = new Object();
    public final Object b = new Object();
    public s3<kc<? super T>, LiveData<T>.b> c = new s3<>();
    public int d = 0;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final Runnable j;

    public class LifecycleBoundObserver extends LiveData<T>.b implements cc {
        public final ec e;

        public LifecycleBoundObserver(ec ecVar, kc<? super T> kcVar) {
            super(kcVar);
            this.e = ecVar;
        }

        @Override // defpackage.cc
        public void d(ec ecVar, Lifecycle.Event event) {
            if (this.e.getLifecycle().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public void i() {
            this.e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean j(ec ecVar) {
            return this.e == ecVar;
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
            synchronized (LiveData.this.b) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.a;
            }
            LiveData.this.h(obj);
        }
    }

    public abstract class b {
        public final kc<? super T> a;
        public boolean b;
        public int c = -1;

        public b(kc<? super T> kcVar) {
            this.a = kcVar;
        }

        public void h(boolean z) {
            if (z == this.b) {
                return;
            }
            this.b = z;
            LiveData liveData = LiveData.this;
            int i = liveData.d;
            boolean z2 = i == 0;
            liveData.d = i + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.d == 0 && !this.b) {
                liveData2.f();
            }
            if (this.b) {
                LiveData.this.c(this);
            }
        }

        public void i() {
        }

        public boolean j(ec ecVar) {
            return false;
        }

        public abstract boolean k();
    }

    public LiveData() {
        Object obj = a;
        this.e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new a();
    }

    public static void a(String str) {
        if (o3.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void b(LiveData<T>.b bVar) {
        if (bVar.b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i = bVar.c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            bVar.c = i2;
            bVar.a.a((Object) this.e);
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
                s3<kc<? super T>, LiveData<T>.b>.d dVarD = this.c.d();
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

    public void d(ec ecVar, kc<? super T> kcVar) {
        a("observe");
        if (ecVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(ecVar, kcVar);
        LiveData<T>.b bVarG = this.c.g(kcVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(ecVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        ecVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void e() {
    }

    public void f() {
    }

    public void g(kc<? super T> kcVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.c.h(kcVar);
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
