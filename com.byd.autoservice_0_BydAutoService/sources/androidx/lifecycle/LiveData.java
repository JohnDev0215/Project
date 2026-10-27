package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import t0.d;
import t0.f;
import t0.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1352a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1353b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public q.b<l<? super T>, LiveData<T>.b> f1354c = new q.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1355d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f1356e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f1357f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1358g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1359h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1360i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Runnable f1361j;

    public class LifecycleBoundObserver extends LiveData<T>.b implements d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final f f1362e;

        public LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.f1362e = fVar;
        }

        @Override // t0.d
        public void d(f fVar, Lifecycle.Event event) {
            if (this.f1362e.getLifecycle().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f1365a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public void i() {
            this.f1362e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean j(f fVar) {
            return this.f1362e == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean k() {
            return this.f1362e.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1353b) {
                obj = LiveData.this.f1357f;
                LiveData.this.f1357f = LiveData.f1352a;
            }
            LiveData.this.h(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l<? super T> f1365a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1366b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1367c = -1;

        public b(l<? super T> lVar) {
            this.f1365a = lVar;
        }

        public void h(boolean z3) {
            if (z3 == this.f1366b) {
                return;
            }
            this.f1366b = z3;
            LiveData liveData = LiveData.this;
            int i3 = liveData.f1355d;
            boolean z4 = i3 == 0;
            liveData.f1355d = i3 + (z3 ? 1 : -1);
            if (z4 && z3) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f1355d == 0 && !this.f1366b) {
                liveData2.f();
            }
            if (this.f1366b) {
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
        Object obj = f1352a;
        this.f1356e = obj;
        this.f1357f = obj;
        this.f1358g = -1;
        this.f1361j = new a();
    }

    public static void a(String str) {
        if (p.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void b(LiveData<T>.b bVar) {
        if (bVar.f1366b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i3 = bVar.f1367c;
            int i4 = this.f1358g;
            if (i3 >= i4) {
                return;
            }
            bVar.f1367c = i4;
            bVar.f1365a.a((Object) this.f1356e);
        }
    }

    public void c(LiveData<T>.b bVar) {
        if (this.f1359h) {
            this.f1360i = true;
            return;
        }
        this.f1359h = true;
        do {
            this.f1360i = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                q.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f1354c.d();
                while (dVarD.hasNext()) {
                    b((b) dVarD.next().getValue());
                    if (this.f1360i) {
                        break;
                    }
                }
            }
        } while (this.f1360i);
        this.f1359h = false;
    }

    public void d(f fVar, l<? super T> lVar) {
        a("observe");
        if (fVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f1354c.g(lVar, lifecycleBoundObserver);
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
        LiveData<T>.b bVarH = this.f1354c.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    public void h(T t3) {
        a("setValue");
        this.f1358g++;
        this.f1356e = t3;
        c(null);
    }
}
