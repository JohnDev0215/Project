package g4;

import android.os.SystemClock;
import android.util.Log;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Collections;
import l3.P;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class i implements f, Runnable, Comparable, B4.b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public p f14876A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f14877C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Object f14878D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Thread f14879E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public e4.e f14880F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public e4.e f14881G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Object f14882H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public com.bumptech.glide.load.data.e f14883I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public volatile g f14884J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public volatile boolean f14885K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public volatile boolean f14886L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f14887M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f14888N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f14889O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f14890P;
    public final A4.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final P f14893p;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public com.bumptech.glide.e f14896s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public e4.e f14897t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public com.bumptech.glide.f f14898u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public q f14899v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f14900w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f14901x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public k f14902y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public e4.h f14903z;
    public final h l = new h();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f14891m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final B4.e f14892n = new B4.e();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final P f14894q = new P(27, false);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final V2.c f14895r = new V2.c();

    public i(A4.i iVar, P p10) {
        this.o = iVar;
        this.f14893p = p10;
    }

    @Override // g4.f
    public final void a(e4.e eVar, Exception exc, com.bumptech.glide.load.data.e eVar2, int i7) {
        eVar2.b();
        t tVar = new t("Fetching data failed", Collections.singletonList(exc));
        Class clsA = eVar2.a();
        tVar.f14953m = eVar;
        tVar.f14954n = i7;
        tVar.o = clsA;
        this.f14891m.add(tVar);
        if (Thread.currentThread() != this.f14879E) {
            l(2);
        } else {
            m();
        }
    }

    @Override // g4.f
    public final void b(e4.e eVar, Object obj, com.bumptech.glide.load.data.e eVar2, int i7, e4.e eVar3) {
        this.f14880F = eVar;
        this.f14882H = obj;
        this.f14883I = eVar2;
        this.f14890P = i7;
        this.f14881G = eVar3;
        this.f14887M = eVar != this.l.a().get(0);
        if (Thread.currentThread() != this.f14879E) {
            l(3);
        } else {
            f();
        }
    }

    @Override // B4.b
    public final B4.e c() {
        return this.f14892n;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        i iVar = (i) obj;
        int iOrdinal = this.f14898u.ordinal() - iVar.f14898u.ordinal();
        return iOrdinal == 0 ? this.B - iVar.B : iOrdinal;
    }

    public final x d(com.bumptech.glide.load.data.e eVar, Object obj, int i7) {
        if (obj == null) {
            return null;
        }
        try {
            int i10 = A4.k.f423b;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            x xVarE = e(i7, obj);
            if (Log.isLoggable("DecodeJob", 2)) {
                i("Decoded result " + xVarE, jElapsedRealtimeNanos, null);
            }
            return xVarE;
        } finally {
            eVar.b();
        }
    }

    public final x e(int i7, Object obj) {
        Class<?> cls = obj.getClass();
        h hVar = this.l;
        v vVarC = hVar.c(cls);
        e4.h hVar2 = this.f14903z;
        boolean z6 = i7 == 4 || hVar.f14875r;
        e4.g gVar = n4.p.f19956i;
        Boolean bool = (Boolean) hVar2.c(gVar);
        if (bool == null || (bool.booleanValue() && !z6)) {
            hVar2 = new e4.h();
            e4.h hVar3 = this.f14903z;
            A4.d dVar = hVar2.f14434b;
            dVar.h(hVar3.f14434b);
            dVar.put(gVar, Boolean.valueOf(z6));
        }
        e4.h hVar4 = hVar2;
        com.bumptech.glide.load.data.g gVarG = this.f14896s.a().g(obj);
        try {
            return vVarC.a(this.f14900w, this.f14901x, new C1.j(this, i7), gVarG, hVar4);
        } finally {
            gVarG.b();
        }
    }

    public final void f() {
        x xVarD;
        boolean zB;
        if (Log.isLoggable("DecodeJob", 2)) {
            i("Retrieved data", this.f14877C, "data: " + this.f14882H + ", cache key: " + this.f14880F + ", fetcher: " + this.f14883I);
        }
        w wVar = null;
        try {
            xVarD = d(this.f14883I, this.f14882H, this.f14890P);
        } catch (t e10) {
            e4.e eVar = this.f14881G;
            int i7 = this.f14890P;
            e10.f14953m = eVar;
            e10.f14954n = i7;
            e10.o = null;
            this.f14891m.add(e10);
            xVarD = null;
        }
        if (xVarD == null) {
            m();
            return;
        }
        int i10 = this.f14890P;
        boolean z6 = this.f14887M;
        if (xVarD instanceof u) {
            ((u) xVarD).a();
        }
        if (((w) this.f14894q.o) != null) {
            wVar = (w) w.f14958p.i();
            wVar.o = false;
            wVar.f14960n = true;
            wVar.f14959m = xVarD;
            xVarD = wVar;
        }
        o();
        p pVar = this.f14876A;
        synchronized (pVar) {
            pVar.f14937y = xVarD;
            pVar.f14938z = i10;
            pVar.f14925G = z6;
        }
        synchronized (pVar) {
            try {
                pVar.f14926m.a();
                if (pVar.f14924F) {
                    pVar.f14937y.e();
                    pVar.g();
                } else {
                    if (pVar.l.l.isEmpty()) {
                        throw new IllegalStateException("Received a resource without any callbacks to notify");
                    }
                    if (pVar.f14920A) {
                        throw new IllegalStateException("Already have resource");
                    }
                    io.sentry.hints.i iVar = pVar.f14928p;
                    x xVar = pVar.f14937y;
                    boolean z10 = pVar.f14935w;
                    q qVar = pVar.f14934v;
                    l lVar = pVar.f14927n;
                    iVar.getClass();
                    pVar.f14922D = new r(xVar, z10, true, qVar, lVar);
                    pVar.f14920A = true;
                    o oVar = pVar.l;
                    oVar.getClass();
                    ArrayList<n> arrayList = new ArrayList(oVar.l);
                    pVar.e(arrayList.size() + 1);
                    pVar.f14929q.d(pVar, pVar.f14934v, pVar.f14922D);
                    for (n nVar : arrayList) {
                        nVar.f14918b.execute(new m(pVar, nVar.f14917a, 1));
                    }
                    pVar.d();
                }
            } finally {
            }
        }
        this.f14888N = 5;
        try {
            P p10 = this.f14894q;
            if (((w) p10.o) != null) {
                A4.i iVar2 = this.o;
                e4.h hVar = this.f14903z;
                p10.getClass();
                try {
                    iVar2.a().l((e4.e) p10.f18537m, new P((e4.k) p10.f18538n, (w) p10.o, hVar, 26));
                    ((w) p10.o).a();
                } catch (Throwable th) {
                    ((w) p10.o).a();
                    throw th;
                }
            }
            V2.c cVar = this.f14895r;
            synchronized (cVar) {
                cVar.f9335b = true;
                zB = cVar.b();
            }
            if (zB) {
                k();
            }
        } finally {
            if (wVar != null) {
                wVar.a();
            }
        }
    }

    public final g g() {
        int iC = AbstractC2641j.c(this.f14888N);
        h hVar = this.l;
        if (iC == 1) {
            return new y(hVar, this);
        }
        if (iC == 2) {
            return new d(hVar.a(), hVar, this);
        }
        if (iC == 3) {
            return new C1296B(hVar, this);
        }
        if (iC == 5) {
            return null;
        }
        throw new IllegalStateException("Unrecognized stage: ".concat(android.hardware.bydauto.ac.tsimpl.a.x(this.f14888N)));
    }

    public final int h(int i7) {
        boolean z6;
        boolean z10;
        int iC = AbstractC2641j.c(i7);
        if (iC == 0) {
            switch (this.f14902y.f14908a) {
                case 0:
                case 1:
                    z6 = false;
                    break;
                default:
                    z6 = true;
                    break;
            }
            if (z6) {
                return 2;
            }
            return h(2);
        }
        if (iC != 1) {
            if (iC == 2) {
                return 4;
            }
            if (iC == 3 || iC == 5) {
                return 6;
            }
            throw new IllegalArgumentException("Unrecognized stage: ".concat(android.hardware.bydauto.ac.tsimpl.a.x(i7)));
        }
        switch (this.f14902y.f14908a) {
            case 0:
                z10 = false;
                break;
            case 1:
            default:
                z10 = true;
                break;
        }
        if (z10) {
            return 3;
        }
        return h(3);
    }

    public final void i(String str, long j3, String str2) {
        StringBuilder sbL = A7.k.l(str, " in ");
        sbL.append(A4.k.a(j3));
        sbL.append(", load key: ");
        sbL.append(this.f14899v);
        sbL.append(str2 != null ? ", ".concat(str2) : "");
        sbL.append(", thread: ");
        sbL.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sbL.toString());
    }

    public final void j() {
        boolean zB;
        o();
        t tVar = new t("Failed to load resource", new ArrayList(this.f14891m));
        p pVar = this.f14876A;
        synchronized (pVar) {
            pVar.B = tVar;
        }
        synchronized (pVar) {
            try {
                pVar.f14926m.a();
                if (pVar.f14924F) {
                    pVar.g();
                } else {
                    if (pVar.l.l.isEmpty()) {
                        throw new IllegalStateException("Received an exception without any callbacks to notify");
                    }
                    if (pVar.f14921C) {
                        throw new IllegalStateException("Already failed once");
                    }
                    pVar.f14921C = true;
                    q qVar = pVar.f14934v;
                    o oVar = pVar.l;
                    oVar.getClass();
                    ArrayList<n> arrayList = new ArrayList(oVar.l);
                    pVar.e(arrayList.size() + 1);
                    pVar.f14929q.d(pVar, qVar, null);
                    for (n nVar : arrayList) {
                        nVar.f14918b.execute(new m(pVar, nVar.f14917a, 0));
                    }
                    pVar.d();
                }
            } finally {
            }
        }
        V2.c cVar = this.f14895r;
        synchronized (cVar) {
            cVar.c = true;
            zB = cVar.b();
        }
        if (zB) {
            k();
        }
    }

    public final void k() {
        V2.c cVar = this.f14895r;
        synchronized (cVar) {
            cVar.f9335b = false;
            cVar.f9334a = false;
            cVar.c = false;
        }
        P p10 = this.f14894q;
        p10.f18537m = null;
        p10.f18538n = null;
        p10.o = null;
        h hVar = this.l;
        hVar.c = null;
        hVar.d = null;
        hVar.f14872n = null;
        hVar.f14866g = null;
        hVar.f14870k = null;
        hVar.f14868i = null;
        hVar.o = null;
        hVar.f14869j = null;
        hVar.f14873p = null;
        hVar.f14862a.clear();
        hVar.l = false;
        hVar.f14863b.clear();
        hVar.f14871m = false;
        this.f14885K = false;
        this.f14896s = null;
        this.f14897t = null;
        this.f14903z = null;
        this.f14898u = null;
        this.f14899v = null;
        this.f14876A = null;
        this.f14888N = 0;
        this.f14884J = null;
        this.f14879E = null;
        this.f14880F = null;
        this.f14882H = null;
        this.f14890P = 0;
        this.f14883I = null;
        this.f14877C = 0L;
        this.f14886L = false;
        this.f14891m.clear();
        this.f14893p.B(this);
    }

    public final void l(int i7) {
        this.f14889O = i7;
        p pVar = this.f14876A;
        (pVar.f14936x ? pVar.f14932t : pVar.f14931s).execute(this);
    }

    public final void m() {
        this.f14879E = Thread.currentThread();
        int i7 = A4.k.f423b;
        this.f14877C = SystemClock.elapsedRealtimeNanos();
        boolean zE = false;
        while (!this.f14886L && this.f14884J != null && !(zE = this.f14884J.e())) {
            this.f14888N = h(this.f14888N);
            this.f14884J = g();
            if (this.f14888N == 4) {
                l(2);
                return;
            }
        }
        if ((this.f14888N == 6 || this.f14886L) && !zE) {
            j();
        }
    }

    public final void n() {
        int iC = AbstractC2641j.c(this.f14889O);
        if (iC == 0) {
            this.f14888N = h(1);
            this.f14884J = g();
            m();
        } else if (iC == 1) {
            m();
        } else if (iC == 2) {
            f();
        } else {
            int i7 = this.f14889O;
            throw new IllegalStateException("Unrecognized run reason: ".concat(i7 != 1 ? i7 != 2 ? i7 != 3 ? "null" : "DECODE_DATA" : "SWITCH_TO_SOURCE_SERVICE" : "INITIALIZE"));
        }
    }

    public final void o() {
        this.f14892n.a();
        if (this.f14885K) {
            throw new IllegalStateException("Already notified", this.f14891m.isEmpty() ? null : (Throwable) AbstractC1347d.e(1, this.f14891m));
        }
        this.f14885K = true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.bumptech.glide.load.data.e eVar = this.f14883I;
        try {
            try {
                if (this.f14886L) {
                    j();
                    if (eVar != null) {
                        eVar.b();
                        return;
                    }
                    return;
                }
                n();
                if (eVar != null) {
                    eVar.b();
                }
            } catch (Throwable th) {
                if (eVar != null) {
                    eVar.b();
                }
                throw th;
            }
        } catch (C1299c e10) {
            throw e10;
        } catch (Throwable th2) {
            if (Log.isLoggable("DecodeJob", 3)) {
                Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.f14886L + ", stage: " + android.hardware.bydauto.ac.tsimpl.a.x(this.f14888N), th2);
            }
            if (this.f14888N != 5) {
                this.f14891m.add(th2);
                j();
            }
            if (!this.f14886L) {
                throw th2;
            }
            throw th2;
        }
    }
}
