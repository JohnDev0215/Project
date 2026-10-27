package androidx.lifecycle;

import B5.RunnableC0079c;
import android.os.Handler;
import android.os.Looper;
import java.util.Map;
import s.C2324a;
import s.C2326c;
import t.C2419c;
import t.C2420d;
import t.C2422f;

/* JADX INFO: loaded from: classes.dex */
public class J {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Object f11925k = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f11926a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2422f f11927b;
    public int c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f11928e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f11929f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f11930g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f11931h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f11932i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final RunnableC0079c f11933j;

    public J(int i7) {
        this.f11926a = new Object();
        this.f11927b = new C2422f();
        this.c = 0;
        this.f11929f = f11925k;
        this.f11933j = new RunnableC0079c(this, 8);
        this.f11928e = null;
        this.f11930g = 0;
    }

    public static void a(String str) {
        if (!C2324a.w().x()) {
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.i("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(G g7) {
        if (g7.f11919m) {
            if (!g7.e()) {
                g7.a(false);
                return;
            }
            int i7 = g7.f11920n;
            int i10 = this.f11930g;
            if (i7 >= i10) {
                return;
            }
            g7.f11920n = i10;
            g7.l.a(this.f11928e);
        }
    }

    public final void c(G g7) {
        if (this.f11931h) {
            this.f11932i = true;
            return;
        }
        this.f11931h = true;
        do {
            this.f11932i = false;
            if (g7 != null) {
                b(g7);
                g7 = null;
            } else {
                C2422f c2422f = this.f11927b;
                c2422f.getClass();
                C2420d c2420d = new C2420d(c2422f);
                c2422f.f22424n.put(c2420d, Boolean.FALSE);
                while (c2420d.hasNext()) {
                    b((G) ((Map.Entry) c2420d.next()).getValue());
                    if (this.f11932i) {
                        break;
                    }
                }
            }
        } while (this.f11932i);
        this.f11931h = false;
    }

    public final void d(N5.a0 a0Var) {
        Object obj;
        a("observeForever");
        E e10 = new E(this, a0Var);
        C2422f c2422f = this.f11927b;
        C2419c c2419cA = c2422f.a(a0Var);
        if (c2419cA != null) {
            obj = c2419cA.f22419m;
        } else {
            C2419c c2419c = new C2419c(a0Var, e10);
            c2422f.o++;
            C2419c c2419c2 = c2422f.f22423m;
            if (c2419c2 == null) {
                c2422f.l = c2419c;
                c2422f.f22423m = c2419c;
            } else {
                c2419c2.f22420n = c2419c;
                c2419c.o = c2419c2;
                c2422f.f22423m = c2419c;
            }
            obj = null;
        }
        G g7 = (G) obj;
        if (g7 instanceof F) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (g7 != null) {
            return;
        }
        e10.a(true);
    }

    public void e() {
    }

    public void f() {
    }

    public final void g(Object obj) {
        boolean z6;
        synchronized (this.f11926a) {
            z6 = this.f11929f == f11925k;
            this.f11929f = obj;
        }
        if (z6) {
            C2324a c2324aW = C2324a.w();
            RunnableC0079c runnableC0079c = this.f11933j;
            C2326c c2326c = c2324aW.c;
            if (c2326c.f21953e == null) {
                synchronized (c2326c.c) {
                    try {
                        if (c2326c.f21953e == null) {
                            c2326c.f21953e = Handler.createAsync(Looper.getMainLooper());
                        }
                    } finally {
                    }
                }
            }
            c2326c.f21953e.post(runnableC0079c);
        }
    }

    public final void h(K k10) {
        a("removeObserver");
        G g7 = (G) this.f11927b.d(k10);
        if (g7 == null) {
            return;
        }
        g7.c();
        g7.a(false);
    }

    public final void i(Object obj) {
        a("setValue");
        this.f11930g++;
        this.f11928e = obj;
        c(null);
    }

    public J() {
        this.f11926a = new Object();
        this.f11927b = new C2422f();
        this.c = 0;
        Object obj = f11925k;
        this.f11929f = obj;
        this.f11933j = new RunnableC0079c(this, 8);
        this.f11928e = obj;
        this.f11930g = -1;
    }
}
