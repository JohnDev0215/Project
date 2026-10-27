package F0;

import S8.AbstractC0721s;
import android.os.Handler;
import android.view.Choreographer;
import java.util.ArrayList;
import n7.C1995j;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: F0.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0204g0 extends AbstractC0721s {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final m7.n f2194x = h6.r.o(U.f2133t);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final E7.b f2195y = new E7.b(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Choreographer f2196n;
    public final Handler o;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2201t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f2202u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0208i0 f2204w;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f2197p = new Object();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1995j f2198q = new C1995j();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f2199r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList f2200s = new ArrayList();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ChoreographerFrameCallbackC0202f0 f2203v = new ChoreographerFrameCallbackC0202f0(this);

    public C0204g0(Choreographer choreographer, Handler handler) {
        this.f2196n = choreographer;
        this.o = handler;
        this.f2204w = new C0208i0(choreographer, this);
    }

    public static final void P(C0204g0 c0204g0) {
        Runnable runnable;
        boolean z6;
        do {
            synchronized (c0204g0.f2197p) {
                C1995j c1995j = c0204g0.f2198q;
                runnable = (Runnable) (c1995j.isEmpty() ? null : c1995j.removeFirst());
            }
            while (runnable != null) {
                runnable.run();
                synchronized (c0204g0.f2197p) {
                    C1995j c1995j2 = c0204g0.f2198q;
                    runnable = (Runnable) (c1995j2.isEmpty() ? null : c1995j2.removeFirst());
                }
            }
            synchronized (c0204g0.f2197p) {
                if (c0204g0.f2198q.isEmpty()) {
                    z6 = false;
                    c0204g0.f2201t = false;
                } else {
                    z6 = true;
                }
            }
        } while (z6);
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        synchronized (this.f2197p) {
            this.f2198q.addLast(runnable);
            if (!this.f2201t) {
                this.f2201t = true;
                this.o.post(this.f2203v);
                if (!this.f2202u) {
                    this.f2202u = true;
                    this.f2196n.postFrameCallback(this.f2203v);
                }
            }
        }
    }
}
