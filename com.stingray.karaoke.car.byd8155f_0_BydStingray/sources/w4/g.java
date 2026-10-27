package w4;

import A4.k;
import A4.q;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import g4.l;
import g4.p;
import g4.t;
import g4.x;
import h6.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import l3.P;
import y4.C2741a;

/* JADX INFO: loaded from: classes.dex */
public final class g implements c, x4.b {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final boolean f23296C = Log.isLoggable("GlideRequest", 2);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final RuntimeException f23297A;
    public int B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f23298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B4.e f23299b;
    public final Object c;
    public final e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f23300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final com.bumptech.glide.e f23301f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f23302g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Class f23303h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f23304i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f23305j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f23306k;
    public final com.bumptech.glide.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x4.c f23307m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f23308n;
    public final C2741a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Executor f23309p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public x f23310q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public P f23311r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f23312s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile l f23313t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Drawable f23314u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f23315v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Drawable f23316w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f23317x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f23318y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f23319z;

    public g(Context context, com.bumptech.glide.e eVar, Object obj, Object obj2, Class cls, a aVar, int i7, int i10, com.bumptech.glide.f fVar, x4.c cVar, e eVar2, ArrayList arrayList, d dVar, l lVar, C2741a c2741a, Executor executor) {
        this.f23298a = f23296C ? String.valueOf(hashCode()) : null;
        this.f23299b = new B4.e();
        this.c = obj;
        this.f23301f = eVar;
        this.f23302g = obj2;
        this.f23303h = cls;
        this.f23304i = aVar;
        this.f23305j = i7;
        this.f23306k = i10;
        this.l = fVar;
        this.f23307m = cVar;
        this.d = eVar2;
        this.f23308n = arrayList;
        this.f23300e = dVar;
        this.f23313t = lVar;
        this.o = c2741a;
        this.f23309p = executor;
        this.B = 1;
        if (this.f23297A == null && eVar.f12681h.f8072a.containsKey(com.bumptech.glide.d.class)) {
            this.f23297A = new RuntimeException("Glide request origin trace");
        }
    }

    @Override // w4.c
    public final boolean a() {
        boolean z6;
        synchronized (this.c) {
            z6 = this.B == 4;
        }
        return z6;
    }

    public final void b() {
        if (this.f23319z) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
        this.f23299b.a();
        this.f23307m.u(this);
        P p10 = this.f23311r;
        if (p10 != null) {
            synchronized (((l) p10.o)) {
                ((p) p10.f18537m).h((g) p10.f18538n);
            }
            this.f23311r = null;
        }
    }

    public final Drawable c() {
        if (this.f23315v == null) {
            this.f23304i.getClass();
            this.f23315v = null;
        }
        return this.f23315v;
    }

    @Override // w4.c
    public final void clear() {
        synchronized (this.c) {
            try {
                if (this.f23319z) {
                    throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
                }
                this.f23299b.a();
                if (this.B == 6) {
                    return;
                }
                b();
                x xVar = this.f23310q;
                if (xVar != null) {
                    this.f23310q = null;
                } else {
                    xVar = null;
                }
                d dVar = this.f23300e;
                if (dVar == null || dVar.b(this)) {
                    this.f23307m.E(c());
                }
                this.B = 6;
                if (xVar != null) {
                    this.f23313t.getClass();
                    l.f(xVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // w4.c
    public final boolean d() {
        boolean z6;
        synchronized (this.c) {
            z6 = this.B == 6;
        }
        return z6;
    }

    public final boolean e() {
        d dVar = this.f23300e;
        return dVar == null || !dVar.getRoot().a();
    }

    @Override // w4.c
    public final boolean f(c cVar) {
        int i7;
        int i10;
        Object obj;
        Class cls;
        a aVar;
        com.bumptech.glide.f fVar;
        int size;
        int i11;
        int i12;
        Object obj2;
        Class cls2;
        a aVar2;
        com.bumptech.glide.f fVar2;
        int size2;
        if (!(cVar instanceof g)) {
            return false;
        }
        synchronized (this.c) {
            try {
                i7 = this.f23305j;
                i10 = this.f23306k;
                obj = this.f23302g;
                cls = this.f23303h;
                aVar = this.f23304i;
                fVar = this.l;
                ArrayList arrayList = this.f23308n;
                size = arrayList != null ? arrayList.size() : 0;
            } finally {
            }
        }
        g gVar = (g) cVar;
        synchronized (gVar.c) {
            try {
                i11 = gVar.f23305j;
                i12 = gVar.f23306k;
                obj2 = gVar.f23302g;
                cls2 = gVar.f23303h;
                aVar2 = gVar.f23304i;
                fVar2 = gVar.l;
                ArrayList arrayList2 = gVar.f23308n;
                size2 = arrayList2 != null ? arrayList2.size() : 0;
            } finally {
            }
        }
        if (i7 != i11 || i10 != i12) {
            return false;
        }
        char[] cArr = q.f432a;
        if ((obj == null ? obj2 == null : obj.equals(obj2)) && cls.equals(cls2)) {
            return (aVar == null ? aVar2 == null : aVar.e(aVar2)) && fVar == fVar2 && size == size2;
        }
        return false;
    }

    @Override // w4.c
    public final void g() {
        synchronized (this.c) {
            try {
                if (this.f23319z) {
                    throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
                }
                this.f23299b.a();
                int i7 = k.f423b;
                this.f23312s = SystemClock.elapsedRealtimeNanos();
                if (this.f23302g == null) {
                    if (q.i(this.f23305j, this.f23306k)) {
                        this.f23317x = this.f23305j;
                        this.f23318y = this.f23306k;
                    }
                    if (this.f23316w == null) {
                        this.f23304i.getClass();
                        this.f23316w = null;
                    }
                    j(new t("Received null model"), this.f23316w == null ? 5 : 3);
                    return;
                }
                int i10 = this.B;
                if (i10 == 2) {
                    throw new IllegalArgumentException("Cannot restart a running request");
                }
                if (i10 == 4) {
                    k(this.f23310q, 5, false);
                    return;
                }
                ArrayList<e> arrayList = this.f23308n;
                if (arrayList != null) {
                    for (e eVar : arrayList) {
                    }
                }
                this.B = 3;
                if (q.i(this.f23305j, this.f23306k)) {
                    m(this.f23305j, this.f23306k);
                } else {
                    this.f23307m.g(this);
                }
                int i11 = this.B;
                if (i11 == 2 || i11 == 3) {
                    d dVar = this.f23300e;
                    if (dVar == null || dVar.i(this)) {
                        this.f23307m.t(c());
                    }
                }
                if (f23296C) {
                    i("finished run method in " + k.a(this.f23312s));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // w4.c
    public final boolean h() {
        boolean z6;
        synchronized (this.c) {
            z6 = this.B == 4;
        }
        return z6;
    }

    public final void i(String str) {
        StringBuilder sbL = A7.k.l(str, " this: ");
        sbL.append(this.f23298a);
        Log.v("GlideRequest", sbL.toString());
    }

    @Override // w4.c
    public final boolean isRunning() {
        boolean z6;
        synchronized (this.c) {
            int i7 = this.B;
            z6 = i7 == 2 || i7 == 3;
        }
        return z6;
    }

    public final void j(t tVar, int i7) {
        Drawable drawableC;
        this.f23299b.a();
        synchronized (this.c) {
            try {
                tVar.getClass();
                int i10 = this.f23301f.f12682i;
                if (i10 <= i7) {
                    n.u("Glide", "Load failed for [" + this.f23302g + "] with dimensions [" + this.f23317x + "x" + this.f23318y + "]", tVar);
                    if (i10 <= 4) {
                        tVar.d();
                    }
                }
                this.f23311r = null;
                this.B = 5;
                d dVar = this.f23300e;
                if (dVar != null) {
                    dVar.c(this);
                }
                boolean z6 = true;
                this.f23319z = true;
                try {
                    ArrayList<e> arrayList = this.f23308n;
                    if (arrayList != null) {
                        for (e eVar : arrayList) {
                            e();
                            eVar.c(tVar);
                        }
                    }
                    e eVar2 = this.d;
                    if (eVar2 != null) {
                        e();
                        eVar2.c(tVar);
                    }
                    d dVar2 = this.f23300e;
                    if (dVar2 != null && !dVar2.i(this)) {
                        z6 = false;
                    }
                    if (z6) {
                        if (this.f23302g == null) {
                            if (this.f23316w == null) {
                                this.f23304i.getClass();
                                this.f23316w = null;
                            }
                            drawableC = this.f23316w;
                        } else {
                            drawableC = null;
                        }
                        if (drawableC == null) {
                            if (this.f23314u == null) {
                                this.f23304i.getClass();
                                this.f23314u = null;
                            }
                            drawableC = this.f23314u;
                        }
                        if (drawableC == null) {
                            drawableC = c();
                        }
                        this.f23307m.i(drawableC);
                    }
                } finally {
                    this.f23319z = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void k(x xVar, int i7, boolean z6) {
        this.f23299b.a();
        x xVar2 = null;
        try {
            synchronized (this.c) {
                try {
                    this.f23311r = null;
                    if (xVar == null) {
                        j(new t("Expected to receive a Resource<R> with an object of " + this.f23303h + " inside, but instead got null."), 5);
                        return;
                    }
                    Object obj = xVar.get();
                    try {
                        if (obj != null && this.f23303h.isAssignableFrom(obj.getClass())) {
                            d dVar = this.f23300e;
                            if (dVar == null || dVar.e(this)) {
                                l(xVar, obj, i7);
                                return;
                            }
                            this.f23310q = null;
                            this.B = 4;
                            this.f23313t.getClass();
                            l.f(xVar);
                            return;
                        }
                        this.f23310q = null;
                        StringBuilder sb = new StringBuilder("Expected to receive an object of ");
                        sb.append(this.f23303h);
                        sb.append(" but instead got ");
                        sb.append(obj != null ? obj.getClass() : "");
                        sb.append("{");
                        sb.append(obj);
                        sb.append("} inside Resource{");
                        sb.append(xVar);
                        sb.append("}.");
                        sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                        j(new t(sb.toString()), 5);
                        this.f23313t.getClass();
                        l.f(xVar);
                    } catch (Throwable th) {
                        xVar2 = xVar;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (xVar2 != null) {
                this.f23313t.getClass();
                l.f(xVar2);
            }
            throw th3;
        }
    }

    public final void l(x xVar, Object obj, int i7) {
        e();
        this.B = 4;
        this.f23310q = xVar;
        if (this.f23301f.f12682i <= 3) {
            Log.d("Glide", "Finished loading " + obj.getClass().getSimpleName() + " from " + android.hardware.bydauto.ac.tsimpl.a.w(i7) + " for " + this.f23302g + " with size [" + this.f23317x + "x" + this.f23318y + "] in " + k.a(this.f23312s) + " ms");
        }
        d dVar = this.f23300e;
        if (dVar != null) {
            dVar.j(this);
        }
        this.f23319z = true;
        try {
            ArrayList arrayList = this.f23308n;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).d(obj);
                }
            }
            e eVar = this.d;
            if (eVar != null) {
                eVar.d(obj);
            }
            this.o.getClass();
            this.f23307m.F(obj);
            this.f23319z = false;
        } catch (Throwable th) {
            this.f23319z = false;
            throw th;
        }
    }

    public final void m(int i7, int i10) {
        int iRound = i7;
        this.f23299b.a();
        synchronized (this.c) {
            try {
                try {
                    boolean z6 = f23296C;
                    if (z6) {
                        i("Got onSizeReady in " + k.a(this.f23312s));
                    }
                    if (this.B != 3) {
                        return;
                    }
                    this.B = 2;
                    this.f23304i.getClass();
                    if (iRound != Integer.MIN_VALUE) {
                        iRound = Math.round(iRound * 1.0f);
                    }
                    this.f23317x = iRound;
                    this.f23318y = i10 == Integer.MIN_VALUE ? i10 : Math.round(1.0f * i10);
                    if (z6) {
                        i("finished setup for calling load in " + k.a(this.f23312s));
                    }
                    l lVar = this.f23313t;
                    com.bumptech.glide.e eVar = this.f23301f;
                    Object obj = this.f23302g;
                    a aVar = this.f23304i;
                    this.f23311r = lVar.a(eVar, obj, aVar.f23279r, this.f23317x, this.f23318y, aVar.f23283v, this.f23303h, this.l, aVar.f23275m, aVar.f23282u, aVar.f23280s, aVar.f23287z, aVar.f23281t, aVar.o, aVar.f23274A, this, this.f23309p);
                    if (this.B != 2) {
                        this.f23311r = null;
                    }
                    if (z6) {
                        i("finished onSizeReady in " + k.a(this.f23312s));
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public final String toString() {
        Object obj;
        Class cls;
        synchronized (this.c) {
            obj = this.f23302g;
            cls = this.f23303h;
        }
        return super.toString() + "[model=" + obj + ", transcodeClass=" + cls + "]";
    }
}
