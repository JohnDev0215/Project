package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.l.d;
import b.l.f;
import b.l.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f527a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f528b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.c.a.b.b<l<? super T>, LiveData<T>.b> f529c = new b.c.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f530d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f531e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f532f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f533g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f534h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f535i;
    public final Runnable j;

    public class LifecycleBoundObserver extends LiveData<T>.b implements d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final f f536e;

        public LifecycleBoundObserver(f fVar, l<? super T> lVar) {
            super(lVar);
            this.f536e = fVar;
        }

        @Override // b.l.d
        public void d(f fVar, Lifecycle.Event event) {
            if (this.f536e.a().b() == Lifecycle.State.DESTROYED) {
                LiveData.this.g(this.f539a);
            } else {
                h(k());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public void i() {
            this.f536e.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean j(f fVar) {
            return this.f536e == fVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean k() {
            return this.f536e.a().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f528b) {
                obj = LiveData.this.f532f;
                LiveData.this.f532f = LiveData.f527a;
            }
            LiveData.this.h(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l<? super T> f539a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f540b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f541c = -1;

        public b(l<? super T> lVar) {
            this.f539a = lVar;
        }

        public void h(boolean z) {
            if (z == this.f540b) {
                return;
            }
            this.f540b = z;
            LiveData liveData = LiveData.this;
            int i2 = liveData.f530d;
            boolean z2 = i2 == 0;
            liveData.f530d = i2 + (z ? 1 : -1);
            if (z2 && z) {
                liveData.e();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f530d == 0 && !this.f540b) {
                liveData2.f();
            }
            if (this.f540b) {
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
        Object obj = f527a;
        this.f531e = obj;
        this.f532f = obj;
        this.f533g = -1;
        this.j = new a();
    }

    public static void a(String str) {
        if (b.c.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public final void b(LiveData<T>.b bVar) {
        if (bVar.f540b) {
            if (!bVar.k()) {
                bVar.h(false);
                return;
            }
            int i2 = bVar.f541c;
            int i3 = this.f533g;
            if (i2 >= i3) {
                return;
            }
            bVar.f541c = i3;
            bVar.f539a.a((Object) this.f531e);
        }
    }

    public void c(LiveData<T>.b bVar) {
        if (this.f534h) {
            this.f535i = true;
            return;
        }
        this.f534h = true;
        do {
            this.f535i = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                b.c.a.b.b<l<? super T>, LiveData<T>.b>.d dVarD = this.f529c.d();
                while (dVarD.hasNext()) {
                    b((b) dVarD.next().getValue());
                    if (this.f535i) {
                        break;
                    }
                }
            }
        } while (this.f535i);
        this.f534h = false;
    }

    public void d(f fVar, l<? super T> lVar) {
        a("observe");
        if (fVar.a().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(fVar, lVar);
        LiveData<T>.b bVarG = this.f529c.g(lVar, lifecycleBoundObserver);
        if (bVarG != null && !bVarG.j(fVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarG != null) {
            return;
        }
        fVar.a().a(lifecycleBoundObserver);
    }

    public void e() {
    }

    public void f() {
    }

    public void g(l<? super T> lVar) {
        a("removeObserver");
        LiveData<T>.b bVarH = this.f529c.h(lVar);
        if (bVarH == null) {
            return;
        }
        bVarH.i();
        bVarH.h(false);
    }

    public void h(T t) {
        a("setValue");
        this.f533g++;
        this.f531e = t;
        c(null);
    }
}
