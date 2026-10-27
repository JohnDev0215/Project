package g4;

import A.C0050y0;
import B8.D;
import android.os.SystemClock;
import android.util.Log;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f14909h = Log.isLoggable("Engine", 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final S6.c f14910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C8.d f14911b;
    public final i4.e c;
    public final D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0050y0 f14912e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final B5.u f14913f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final B8.t f14914g;

    public l(i4.e eVar, e7.f fVar, j4.e eVar2, j4.e eVar3, j4.e eVar4, j4.e eVar5) {
        this.c = eVar;
        A4.i iVar = new A4.i(fVar);
        B8.t tVar = new B8.t(13);
        this.f14914g = tVar;
        synchronized (this) {
            synchronized (tVar) {
                tVar.f807m = this;
            }
        }
        this.f14911b = new C8.d(21);
        this.f14910a = new S6.c(4);
        this.d = new D(eVar2, eVar3, eVar4, eVar5, this, this);
        this.f14913f = new B5.u(iVar);
        this.f14912e = new C0050y0(6);
        eVar.d = this;
    }

    public static void c(String str, long j3, q qVar) {
        StringBuilder sbL = A7.k.l(str, " in ");
        sbL.append(A4.k.a(j3));
        sbL.append("ms, key: ");
        sbL.append(qVar);
        Log.v("Engine", sbL.toString());
    }

    public static void f(x xVar) {
        if (!(xVar instanceof r)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((r) xVar).c();
    }

    public final P a(com.bumptech.glide.e eVar, Object obj, e4.e eVar2, int i7, int i10, Class cls, Class cls2, com.bumptech.glide.f fVar, k kVar, A4.d dVar, boolean z6, boolean z10, e4.h hVar, boolean z11, boolean z12, w4.g gVar, Executor executor) {
        long jElapsedRealtimeNanos;
        if (f14909h) {
            int i11 = A4.k.f423b;
            jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        } else {
            jElapsedRealtimeNanos = 0;
        }
        long j3 = jElapsedRealtimeNanos;
        this.f14911b.getClass();
        q qVar = new q(obj, eVar2, i7, i10, dVar, cls, cls2, hVar);
        synchronized (this) {
            try {
                r rVarB = b(qVar, z11, j3);
                if (rVarB == null) {
                    return g(eVar, obj, eVar2, i7, i10, cls, cls2, fVar, kVar, dVar, z6, z10, hVar, z11, z12, gVar, executor, qVar, j3);
                }
                gVar.k(rVarB, 5, false);
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final r b(q qVar, boolean z6, long j3) {
        r rVar;
        Object obj;
        if (!z6) {
            return null;
        }
        B8.t tVar = this.f14914g;
        synchronized (tVar) {
            C1298b c1298b = (C1298b) ((HashMap) tVar.o).get(qVar);
            if (c1298b == null) {
                rVar = null;
            } else {
                rVar = (r) c1298b.get();
                if (rVar == null) {
                    tVar.I(c1298b);
                }
            }
        }
        if (rVar != null) {
            rVar.a();
        }
        if (rVar != null) {
            if (f14909h) {
                c("Loaded resource from active resources", j3, qVar);
            }
            return rVar;
        }
        i4.e eVar = this.c;
        synchronized (eVar) {
            A4.l lVar = (A4.l) ((LinkedHashMap) eVar.c).remove(qVar);
            if (lVar == null) {
                obj = null;
            } else {
                eVar.f427b -= (long) lVar.f425b;
                obj = lVar.f424a;
            }
        }
        x xVar = (x) obj;
        r rVar2 = xVar == null ? null : xVar instanceof r ? (r) xVar : new r(xVar, true, true, qVar, this);
        if (rVar2 != null) {
            rVar2.a();
            this.f14914g.u(qVar, rVar2);
        }
        if (rVar2 == null) {
            return null;
        }
        if (f14909h) {
            c("Loaded resource from cache", j3, qVar);
        }
        return rVar2;
    }

    public final synchronized void d(p pVar, q qVar, r rVar) {
        if (rVar != null) {
            try {
                if (rVar.l) {
                    this.f14914g.u(qVar, rVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        S6.c cVar = this.f14910a;
        cVar.getClass();
        pVar.getClass();
        HashMap map = cVar.f8070a;
        if (pVar.equals(map.get(qVar))) {
            map.remove(qVar);
        }
    }

    public final void e(q qVar, r rVar) {
        B8.t tVar = this.f14914g;
        synchronized (tVar) {
            C1298b c1298b = (C1298b) ((HashMap) tVar.o).remove(qVar);
            if (c1298b != null) {
                c1298b.c = null;
                c1298b.clear();
            }
        }
        if (rVar.l) {
        } else {
            this.f14912e.p(rVar, false);
        }
    }

    public final P g(com.bumptech.glide.e eVar, Object obj, e4.e eVar2, int i7, int i10, Class cls, Class cls2, com.bumptech.glide.f fVar, k kVar, A4.d dVar, boolean z6, boolean z10, e4.h hVar, boolean z11, boolean z12, w4.g gVar, Executor executor, q qVar, long j3) {
        p pVar = (p) this.f14910a.f8070a.get(qVar);
        if (pVar != null) {
            pVar.a(gVar, executor);
            if (f14909h) {
                c("Added to existing load", j3, qVar);
            }
            return new P(this, gVar, pVar);
        }
        p pVar2 = (p) ((P) this.d.f752s).i();
        synchronized (pVar2) {
            pVar2.f14934v = qVar;
            pVar2.f14935w = z11;
            pVar2.f14936x = z12;
        }
        B5.u uVar = this.f14913f;
        i iVar = (i) ((P) uVar.o).i();
        int i11 = uVar.f735m;
        uVar.f735m = i11 + 1;
        h hVar2 = iVar.l;
        hVar2.c = eVar;
        hVar2.d = obj;
        hVar2.f14872n = eVar2;
        hVar2.f14864e = i7;
        hVar2.f14865f = i10;
        hVar2.f14873p = kVar;
        hVar2.f14866g = cls;
        hVar2.f14867h = iVar.o;
        hVar2.f14870k = cls2;
        hVar2.o = fVar;
        hVar2.f14868i = hVar;
        hVar2.f14869j = dVar;
        hVar2.f14874q = z6;
        hVar2.f14875r = z10;
        iVar.f14896s = eVar;
        iVar.f14897t = eVar2;
        iVar.f14898u = fVar;
        iVar.f14899v = qVar;
        iVar.f14900w = i7;
        iVar.f14901x = i10;
        iVar.f14902y = kVar;
        iVar.f14903z = hVar;
        iVar.f14876A = pVar2;
        iVar.B = i11;
        iVar.f14889O = 1;
        iVar.f14878D = obj;
        S6.c cVar = this.f14910a;
        cVar.getClass();
        cVar.f8070a.put(qVar, pVar2);
        pVar2.a(gVar, executor);
        synchronized (pVar2) {
            pVar2.f14923E = iVar;
            int iH = iVar.h(1);
            ((iH == 2 || iH == 3) ? pVar2.f14930r : pVar2.f14936x ? pVar2.f14932t : pVar2.f14931s).execute(iVar);
        }
        if (f14909h) {
            c("Started new load", j3, qVar);
        }
        return new P(this, gVar, pVar2);
    }
}
