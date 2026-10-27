package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.n.e;
import b.n.g;
import b.n.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1018a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1019b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.c.a.b.b<m<? super T>, LiveData<T>.c> f1020c = new b.c.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1021d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1022e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f1023f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile Object f1024g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1025h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1026i;
    public boolean j;
    public final Runnable k;

    public class LifecycleBoundObserver extends LiveData<T>.c implements e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final g f1027e;

        public LifecycleBoundObserver(g gVar, m<? super T> mVar) {
            super(mVar);
            this.f1027e = gVar;
        }

        @Override // b.n.e
        public void d(g gVar, Lifecycle.Event event) {
            Lifecycle.State stateB = this.f1027e.getLifecycle().b();
            if (stateB == Lifecycle.State.DESTROYED) {
                LiveData.this.i(this.f1031a);
                return;
            }
            Lifecycle.State state = null;
            while (state != stateB) {
                h(k());
                state = stateB;
                stateB = this.f1027e.getLifecycle().b();
            }
        }

        @Override // androidx.lifecycle.LiveData.c
        public void i() {
            this.f1027e.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean j(g gVar) {
            return this.f1027e == gVar;
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean k() {
            return this.f1027e.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1019b) {
                obj = LiveData.this.f1024g;
                LiveData.this.f1024g = LiveData.f1018a;
            }
            LiveData.this.j(obj);
        }
    }

    public class b extends LiveData<T>.c {
        public b(m<? super T> mVar) {
            super(mVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        public boolean k() {
            return true;
        }
    }

    public abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m<? super T> f1031a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1032b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1033c = -1;

        public c(m<? super T> mVar) {
            this.f1031a = mVar;
        }

        public void h(boolean z) {
            if (z == this.f1032b) {
                return;
            }
            this.f1032b = z;
            LiveData.this.b(z ? 1 : -1);
            if (this.f1032b) {
                LiveData.this.d(this);
            }
        }

        public void i() {
        }

        public boolean j(g gVar) {
            return false;
        }

        public abstract boolean k();
    }

    public LiveData() {
        Object obj = f1018a;
        this.f1024g = obj;
        this.k = new a();
        this.f1023f = obj;
        this.f1025h = -1;
    }

    public static void a(String str) {
        if (b.c.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public void b(int i2) {
        int i3 = this.f1021d;
        this.f1021d = i2 + i3;
        if (this.f1022e) {
            return;
        }
        this.f1022e = true;
        while (true) {
            try {
                int i4 = this.f1021d;
                if (i3 == i4) {
                    return;
                }
                boolean z = i3 == 0 && i4 > 0;
                boolean z2 = i3 > 0 && i4 == 0;
                if (z) {
                    g();
                } else if (z2) {
                    h();
                }
                i3 = i4;
            } finally {
                this.f1022e = false;
            }
        }
    }

    public final void c(LiveData<T>.c cVar) {
        if (cVar.f1032b) {
            if (!cVar.k()) {
                cVar.h(false);
                return;
            }
            int i2 = cVar.f1033c;
            int i3 = this.f1025h;
            if (i2 >= i3) {
                return;
            }
            cVar.f1033c = i3;
            cVar.f1031a.a((Object) this.f1023f);
        }
    }

    public void d(LiveData<T>.c cVar) {
        if (this.f1026i) {
            this.j = true;
            return;
        }
        this.f1026i = true;
        do {
            this.j = false;
            if (cVar != null) {
                c(cVar);
                cVar = null;
            } else {
                b.c.a.b.b<m<? super T>, LiveData<T>.c>.d dVarD = this.f1020c.d();
                while (dVarD.hasNext()) {
                    c((c) dVarD.next().getValue());
                    if (this.j) {
                        break;
                    }
                }
            }
        } while (this.j);
        this.f1026i = false;
    }

    public void e(g gVar, m<? super T> mVar) {
        a("observe");
        if (gVar.getLifecycle().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, mVar);
        LiveData<T>.c cVarG = this.f1020c.g(mVar, lifecycleBoundObserver);
        if (cVarG != null && !cVarG.j(gVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarG != null) {
            return;
        }
        gVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void f(m<? super T> mVar) {
        a("observeForever");
        b bVar = new b(mVar);
        LiveData<T>.c cVarG = this.f1020c.g(mVar, bVar);
        if (cVarG instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVarG != null) {
            return;
        }
        bVar.h(true);
    }

    public void g() {
    }

    public void h() {
    }

    public void i(m<? super T> mVar) {
        a("removeObserver");
        LiveData<T>.c cVarH = this.f1020c.h(mVar);
        if (cVarH == null) {
            return;
        }
        cVarH.i();
        cVarH.h(false);
    }

    public void j(T t) {
        a("setValue");
        this.f1025h++;
        this.f1023f = t;
        d(null);
    }
}
