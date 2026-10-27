package defpackage;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import java.util.Iterator;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: BindingManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class re implements ComponentCallbacks2 {
    public static Boolean k;
    public final q8 e = new q8(0);
    public final int f;
    public final Iterable<qn> g;
    public final se h;
    public qn i;
    public int j;

    /* JADX INFO: compiled from: BindingManager.java */
    public class a implements Runnable {
        public final /* synthetic */ int e;

        public a(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.e;
            Integer numValueOf = Integer.valueOf(i);
            re reVar = re.this;
            fa1.h("BindingManager", "onTrimMemory: level=%d, size=%d", numValueOf, Integer.valueOf(reVar.e.g));
            q8 q8Var = reVar.e;
            if (q8Var.isEmpty()) {
                return;
            }
            if (i <= 5) {
                re.a(reVar, 0.25f);
            } else if (i <= 10) {
                re.a(reVar, 0.5f);
            } else {
                if (i == 20) {
                    return;
                }
                reVar.e(q8Var.g);
            }
        }
    }

    /* JADX INFO: compiled from: BindingManager.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            re reVar = re.this;
            fa1.g("BindingManager", "onLowMemory: evict %d bindings", Integer.valueOf(reVar.e.g));
            reVar.e(reVar.e.g);
        }
    }

    public re(Context context, int i, xn xnVar) {
        fa1.g("BindingManager", "Visible binding enabled: maxSize=%d", Integer.valueOf(i));
        this.f = i;
        this.g = xnVar;
        if (i <= 0 && i != -1) {
            throw new IllegalArgumentException(gq2.e("maxSize must be a positive integer or NO_MAX_SIZE. Was ", i));
        }
        this.h = new se(this);
        context.registerComponentCallbacks(this);
    }

    public static void a(re reVar, float f) {
        int i = reVar.e.g;
        int i2 = (int) ((1.0f - f) * i);
        fa1.h("BindingManager", "Reduce connections from %d to %d", Integer.valueOf(i), Integer.valueOf(i2));
        reVar.e(i - i2);
        reVar.c();
    }

    public static void b(qn qnVar) {
        if (!f()) {
            qnVar.b();
            return;
        }
        if (!qnVar.g()) {
            fa1.k("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(qnVar.o));
            return;
        }
        if (qnVar.A == 0) {
            qnVar.w.a();
            qnVar.m();
        }
        qnVar.A++;
    }

    public static void d(qn qnVar) {
        if (f()) {
            if (qnVar.g()) {
                int i = qnVar.A - 1;
                qnVar.A = i;
                if (i == 0) {
                    qnVar.w.b();
                    qnVar.m();
                    return;
                }
                return;
            }
            return;
        }
        if (qnVar.g()) {
            int i2 = qnVar.z - 1;
            qnVar.z = i2;
            if (i2 == 0) {
                qnVar.v.b();
                qnVar.m();
            }
        }
    }

    public static boolean f() {
        if (k == null) {
            k = Boolean.TRUE;
        }
        return k.booleanValue();
    }

    public final void c() {
        qn next;
        qn qnVar;
        Iterator<qn> it = this.g.iterator();
        if (it.hasNext() && (next = it.next()) != (qnVar = this.i)) {
            if (qnVar != null) {
                b(qnVar);
                this.i = null;
            }
            if (this.e.contains(next)) {
                d(next);
                this.i = next;
            }
        }
    }

    public final void e(int i) {
        int i2 = 0;
        for (qn qnVar : this.g) {
            q8 q8Var = this.e;
            if (q8Var.contains(qnVar)) {
                if (qnVar == this.i) {
                    this.i = null;
                } else {
                    d(qnVar);
                }
                q8Var.remove(qnVar);
                i2++;
                if (i2 == i) {
                    return;
                }
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        LauncherThread.a(new b());
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        LauncherThread.a(new a(i));
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }
}
