package T0;

import A.C0022k;
import K.C0365x;
import N0.H;
import N0.K;
import android.graphics.Rect;
import android.view.Choreographer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.compose.ui.platform.AndroidComposeView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class B implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f8411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P f8412b;
    public final Executor c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public A7.o f8413e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public A7.o f8414f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public y f8415g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f8416h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f8417i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f8418j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Rect f8419k;
    public final C0779e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final V.d f8420m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public A2.l f8421n;

    public B(View view, AndroidComposeView androidComposeView) {
        P p10 = new P(view);
        C c = new C(Choreographer.getInstance(), 0);
        this.f8411a = view;
        this.f8412b = p10;
        this.c = c;
        this.f8413e = C0778d.f8431p;
        this.f8414f = C0778d.f8432q;
        this.f8415g = new y("", K.f4356b, 4);
        this.f8416h = m.f8459g;
        this.f8417i = new ArrayList();
        this.f8418j = h6.r.n(m7.g.f19257m, new K7.K(this, 20));
        this.l = new C0779e(androidComposeView, p10);
        this.f8420m = new V.d(new A[16]);
    }

    @Override // T0.t
    public final void a(m0.d dVar) {
        Rect rect;
        this.f8419k = new Rect(C7.a.W(dVar.f18882a), C7.a.W(dVar.f18883b), C7.a.W(dVar.c), C7.a.W(dVar.d));
        if (!this.f8417i.isEmpty() || (rect = this.f8419k) == null) {
            return;
        }
        this.f8411a.requestRectangleOnScreen(new Rect(rect));
    }

    @Override // T0.t
    public final void b() {
        i(A.l);
    }

    @Override // T0.t
    public final void c() {
        i(A.f8409n);
    }

    @Override // T0.t
    public final void d(y yVar, s sVar, H h10, A.F f10, m0.d dVar, m0.d dVar2) {
        C0779e c0779e = this.l;
        synchronized (c0779e.c) {
            try {
                c0779e.f8443j = yVar;
                c0779e.l = sVar;
                c0779e.f8444k = h10;
                c0779e.f8445m = f10;
                c0779e.f8446n = dVar;
                c0779e.o = dVar2;
                if (c0779e.f8438e || c0779e.d) {
                    c0779e.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // T0.t
    public final void e() {
        i(A.o);
    }

    @Override // T0.t
    public final void f() {
        this.d = false;
        this.f8413e = C0778d.f8433r;
        this.f8414f = C0778d.f8434s;
        this.f8419k = null;
        i(A.f8408m);
    }

    /* JADX WARN: Type inference failed for: r10v7, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Object, m7.f] */
    @Override // T0.t
    public final void g(y yVar, y yVar2) {
        boolean z6 = (K.a(this.f8415g.f8482b, yVar2.f8482b) && A7.m.a(this.f8415g.c, yVar2.c)) ? false : true;
        this.f8415g = yVar2;
        int size = this.f8417i.size();
        for (int i7 = 0; i7 < size; i7++) {
            u uVar = (u) ((WeakReference) this.f8417i.get(i7)).get();
            if (uVar != null) {
                uVar.d = yVar2;
            }
        }
        C0779e c0779e = this.l;
        synchronized (c0779e.c) {
            c0779e.f8443j = null;
            c0779e.l = null;
            c0779e.f8444k = null;
            c0779e.f8445m = C0778d.f8430n;
            c0779e.f8446n = null;
            c0779e.o = null;
        }
        if (A7.m.a(yVar, yVar2)) {
            if (z6) {
                P p10 = this.f8412b;
                int iE = K.e(yVar2.f8482b);
                int iD = K.d(yVar2.f8482b);
                K k10 = this.f8415g.c;
                int iE2 = k10 != null ? K.e(k10.f4357a) : -1;
                K k11 = this.f8415g.c;
                ((InputMethodManager) p10.f18538n.getValue()).updateSelection((View) p10.f18537m, iE, iD, iE2, k11 != null ? K.d(k11.f4357a) : -1);
                return;
            }
            return;
        }
        if (yVar != null && (!A7.m.a(yVar.f8481a.l, yVar2.f8481a.l) || (K.a(yVar.f8482b, yVar2.f8482b) && !A7.m.a(yVar.c, yVar2.c)))) {
            P p11 = this.f8412b;
            ((InputMethodManager) p11.f18538n.getValue()).restartInput((View) p11.f18537m);
            return;
        }
        int size2 = this.f8417i.size();
        for (int i10 = 0; i10 < size2; i10++) {
            u uVar2 = (u) ((WeakReference) this.f8417i.get(i10)).get();
            if (uVar2 != null) {
                y yVar3 = this.f8415g;
                P p12 = this.f8412b;
                if (uVar2.f8474h) {
                    uVar2.d = yVar3;
                    if (uVar2.f8472f) {
                        ((InputMethodManager) p12.f18538n.getValue()).updateExtractedText((View) p12.f18537m, uVar2.f8471e, P8.n.V(yVar3));
                    }
                    K k12 = yVar3.c;
                    int iE3 = k12 != null ? K.e(k12.f4357a) : -1;
                    K k13 = yVar3.c;
                    int iD2 = k13 != null ? K.d(k13.f4357a) : -1;
                    long j3 = yVar3.f8482b;
                    ((InputMethodManager) p12.f18538n.getValue()).updateSelection((View) p12.f18537m, K.e(j3), K.d(j3), iE3, iD2);
                }
            }
        }
    }

    @Override // T0.t
    public final void h(y yVar, m mVar, C0022k c0022k, C0365x c0365x) {
        this.d = true;
        this.f8415g = yVar;
        this.f8416h = mVar;
        this.f8413e = c0022k;
        this.f8414f = c0365x;
        i(A.l);
    }

    public final void i(A a10) {
        this.f8420m.b(a10);
        if (this.f8421n == null) {
            A2.l lVar = new A2.l(this, 7);
            this.c.execute(lVar);
            this.f8421n = lVar;
        }
    }
}
