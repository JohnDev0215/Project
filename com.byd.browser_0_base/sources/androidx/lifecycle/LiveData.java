package androidx.lifecycle;

import android.os.Looper;
import androidx.lifecycle.d;
import defpackage.b71;
import defpackage.bq1;
import defpackage.g8;
import defpackage.gq2;
import defpackage.kd2;
import defpackage.ld2;
import defpackage.r90;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object j = new Object();
    public final Object a = new Object();
    public final kd2<bq1<? super T>, LiveData<T>.c> b = new kd2<>();
    public int c = 0;
    public boolean d;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;

    public class LifecycleBoundObserver extends LiveData<T>.c implements f {
        public final b71 i;

        public LifecycleBoundObserver(b71 b71Var, ld2 ld2Var) {
            super(ld2Var);
            this.i = b71Var;
        }

        @Override // androidx.lifecycle.f
        public final void a(b71 b71Var, d.a aVar) {
            b71 b71Var2 = this.i;
            d.b bVar = b71Var2.j1().c;
            if (bVar == d.b.DESTROYED) {
                LiveData.this.h(this.e);
                return;
            }
            d.b bVar2 = null;
            while (bVar2 != bVar) {
                b(e());
                bVar2 = bVar;
                bVar = b71Var2.j1().c;
            }
        }

        @Override // androidx.lifecycle.LiveData.c
        public final void c() {
            this.i.j1().b(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        public final boolean d(b71 b71Var) {
            return this.i == b71Var;
        }

        @Override // androidx.lifecycle.LiveData.c
        public final boolean e() {
            return this.i.j1().c.compareTo(d.b.STARTED) >= 0;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            Object obj;
            synchronized (LiveData.this.a) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.j;
            }
            LiveData.this.i(obj);
        }
    }

    public class b extends LiveData<T>.c {
        public b(LiveData liveData, r90.d dVar) {
            super(dVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        public final boolean e() {
            return true;
        }
    }

    public LiveData() {
        Object obj = j;
        this.f = obj;
        new a();
        this.e = obj;
        this.g = -1;
    }

    public static void a(String str) {
        g8.J().k.getClass();
        if (!(Looper.getMainLooper().getThread() == Thread.currentThread())) {
            throw new IllegalStateException(gq2.g("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(LiveData<T>.c cVar) {
        if (cVar.f) {
            if (!cVar.e()) {
                cVar.b(false);
                return;
            }
            int i = cVar.g;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            cVar.g = i2;
            cVar.e.a((Object) this.e);
        }
    }

    public final void c(LiveData<T>.c cVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (cVar != null) {
                b(cVar);
                cVar = null;
            } else {
                kd2<bq1<? super T>, LiveData<T>.c> kd2Var = this.b;
                kd2Var.getClass();
                kd2.d dVar = new kd2.d();
                kd2Var.g.put(dVar, Boolean.FALSE);
                while (dVar.hasNext()) {
                    b((c) ((Map.Entry) dVar.next()).getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public final void d(b71 b71Var, ld2 ld2Var) {
        LiveData<T>.c cVar;
        a("observe");
        if (b71Var.j1().c == d.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(b71Var, ld2Var);
        kd2<bq1<? super T>, LiveData<T>.c> kd2Var = this.b;
        kd2.c<bq1<? super T>, LiveData<T>.c> cVarA = kd2Var.a(ld2Var);
        if (cVarA != null) {
            cVar = cVarA.f;
        } else {
            kd2.c<K, V> cVar2 = new kd2.c<>(ld2Var, lifecycleBoundObserver);
            kd2Var.h++;
            kd2.c<bq1<? super T>, LiveData<T>.c> cVar3 = kd2Var.f;
            if (cVar3 == 0) {
                kd2Var.e = cVar2;
                kd2Var.f = cVar2;
            } else {
                cVar3.g = cVar2;
                cVar2.h = cVar3;
                kd2Var.f = cVar2;
            }
            cVar = null;
        }
        LiveData<T>.c cVar4 = cVar;
        if (cVar4 != null && !cVar4.d(b71Var)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar4 != null) {
            return;
        }
        b71Var.j1().a(lifecycleBoundObserver);
    }

    public final void e(r90.d dVar) {
        LiveData<T>.c cVar;
        a("observeForever");
        b bVar = new b(this, dVar);
        kd2<bq1<? super T>, LiveData<T>.c> kd2Var = this.b;
        kd2.c<bq1<? super T>, LiveData<T>.c> cVarA = kd2Var.a(dVar);
        if (cVarA != null) {
            cVar = cVarA.f;
        } else {
            kd2.c<K, V> cVar2 = new kd2.c<>(dVar, bVar);
            kd2Var.h++;
            kd2.c<bq1<? super T>, LiveData<T>.c> cVar3 = kd2Var.f;
            if (cVar3 == 0) {
                kd2Var.e = cVar2;
                kd2Var.f = cVar2;
            } else {
                cVar3.g = cVar2;
                cVar2.h = cVar3;
                kd2Var.f = cVar2;
            }
            cVar = null;
        }
        LiveData<T>.c cVar4 = cVar;
        if (cVar4 instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar4 != null) {
            return;
        }
        bVar.b(true);
    }

    public void h(bq1<? super T> bq1Var) {
        a("removeObserver");
        LiveData<T>.c cVarB = this.b.b(bq1Var);
        if (cVarB == null) {
            return;
        }
        cVarB.c();
        cVarB.b(false);
    }

    public abstract void i(T t);

    public abstract class c {
        public final bq1<? super T> e;
        public boolean f;
        public int g = -1;

        public c(bq1<? super T> bq1Var) {
            this.e = bq1Var;
        }

        public final void b(boolean z) {
            if (z == this.f) {
                return;
            }
            this.f = z;
            int i = z ? 1 : -1;
            LiveData liveData = LiveData.this;
            int i2 = liveData.c;
            liveData.c = i + i2;
            if (!liveData.d) {
                liveData.d = true;
                while (true) {
                    try {
                        int i3 = liveData.c;
                        if (i2 == i3) {
                            break;
                        }
                        boolean z2 = i2 == 0 && i3 > 0;
                        boolean z3 = i2 > 0 && i3 == 0;
                        if (z2) {
                            liveData.f();
                        } else if (z3) {
                            liveData.g();
                        }
                        i2 = i3;
                    } finally {
                        liveData.d = false;
                    }
                }
            }
            if (this.f) {
                liveData.c(this);
            }
        }

        public boolean d(b71 b71Var) {
            return false;
        }

        public abstract boolean e();

        public void c() {
        }
    }

    public void f() {
    }

    public void g() {
    }
}
