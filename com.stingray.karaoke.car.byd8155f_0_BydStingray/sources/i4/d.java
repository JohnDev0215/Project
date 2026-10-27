package i4;

import N1.C0420a;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import d5.M;
import e2.C1192A;
import e2.C1195c;
import e2.C1202j;
import e2.F;
import e2.I;
import e2.S;
import e2.W;
import e2.e0;
import e2.f0;
import e4.InterfaceC1226b;
import e4.h;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h5.s;
import h5.t;
import h5.y;
import h6.n;
import io.sentry.C1489n1;
import io.sentry.android.replay.p;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import k3.AbstractC1663q;
import k3.B0;
import k3.B1;
import k3.ExecutorC1662p0;
import k3.I0;
import k3.InterfaceC1677x0;
import k3.Q0;
import k3.s1;
import k3.u1;
import l3.C1736A;
import l3.InterfaceC1748d;
import l3.L;
import l3.P;
import l9.l;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC1408a, s, InterfaceC1677x0 {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f15750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f15751n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f15752p;

    @Override // k3.InterfaceC1677x0
    public void a() {
    }

    @Override // k3.InterfaceC1677x0
    public void b(int i7, List list) {
        Q0 q02 = (Q0) this.f15752p;
        q02.L(q02.f17843f.f17811t);
    }

    @Override // i4.InterfaceC1408a
    public File d(e4.e eVar) {
        String strL = ((io.sentry.internal.debugmeta.c) this.f15750m).L(eVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strL + " for for Key: " + eVar);
        }
        try {
            C0420a c0420aM = m().m(strL);
            if (c0420aM != null) {
                return ((File[]) c0420aM.l)[0];
            }
            return null;
        } catch (IOException e10) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            n.u("DiskLruCacheWrapper", "Unable to get from disk cache", e10);
            return null;
        }
    }

    @Override // k3.InterfaceC1677x0
    public void e(int i7, B1 b12, boolean z6, boolean z10, int i10) {
        Q0 q02 = (Q0) this.f15752p;
        q02.L(q02.f17843f.f17811t);
    }

    @Override // h5.s
    public void h(Throwable th) {
        if (this != ((Q0) ((d) this.f15752p).f15752p).o) {
            return;
        }
        AbstractC1346c.z("MediaSessionLegacyStub", "Failed to load bitmap: " + th.getMessage());
    }

    @Override // h5.s
    public void i(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        d dVar = (d) this.f15752p;
        Q0 q02 = (Q0) dVar.f15752p;
        if (this != q02.o) {
            return;
        }
        Q0.C(q02.f17847j, AbstractC1663q.l((I) this.f15750m, (String) this.f15751n, (Uri) this.o, this.l, bitmap));
        I0 i02 = ((Q0) dVar.f15752p).f17843f;
        AbstractC1343A.K(i02.o, new B0(i02, 1));
    }

    @Override // k3.InterfaceC1677x0
    public void k(int i7, S s6) {
        Q0 q02 = (Q0) this.f15752p;
        u1 u1Var = q02.f17843f.f17811t;
        Q0.D(q02, u1Var);
        q02.L(u1Var);
    }

    @Override // i4.InterfaceC1408a
    public void l(e4.e eVar, P p10) {
        C1409b c1409b;
        c4.c cVarM;
        boolean z6;
        String strL = ((io.sentry.internal.debugmeta.c) this.f15750m).L(eVar);
        C1489n1 c1489n1 = (C1489n1) this.o;
        synchronized (c1489n1) {
            c1409b = (C1409b) ((HashMap) c1489n1.f16624m).get(strL);
            if (c1409b == null) {
                C1410c c1410c = (C1410c) c1489n1.f16625n;
                synchronized (c1410c.f15749a) {
                    c1409b = (C1409b) c1410c.f15749a.poll();
                }
                if (c1409b == null) {
                    c1409b = new C1409b();
                }
                ((HashMap) c1489n1.f16624m).put(strL, c1409b);
            }
            c1409b.f15748b++;
        }
        c1409b.f15747a.lock();
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strL + " for for Key: " + eVar);
            }
            try {
                cVarM = m();
            } catch (IOException e10) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    n.u("DiskLruCacheWrapper", "Unable to put to disk cache", e10);
                }
            }
            if (cVarM.m(strL) != null) {
                return;
            }
            A2.c cVarJ = cVarM.j(strL);
            if (cVarJ == null) {
                throw new IllegalStateException("Had two simultaneous puts for: ".concat(strL));
            }
            try {
                if (((InterfaceC1226b) p10.f18537m).q(p10.f18538n, cVarJ.l(), (h) p10.o)) {
                    c4.c.b((c4.c) cVarJ.f312e, cVarJ, true);
                    cVarJ.f311b = true;
                }
                if (!z6) {
                    try {
                        cVarJ.a();
                    } catch (IOException unused) {
                    }
                }
            } finally {
                if (!cVarJ.f311b) {
                    try {
                        cVarJ.a();
                    } catch (IOException unused2) {
                    }
                }
            }
        } finally {
            ((C1489n1) this.o).E(strL);
        }
    }

    public synchronized c4.c m() {
        try {
            if (((c4.c) this.f15752p) == null) {
                this.f15752p = c4.c.q((File) this.f15751n, this.l);
            }
        } catch (Throwable th) {
            throw th;
        }
        return (c4.c) this.f15752p;
    }

    public void n(C1195c c1195c) {
        Q0 q02 = (Q0) this.f15752p;
        if (q02.f17843f.f17811t.d0().f14127a == 0) {
            int iU = AbstractC1663q.u(c1195c);
            L l = (L) q02.f17847j.f18537m;
            l.getClass();
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(iU);
            l.f18523a.setPlaybackToLocal(builder.build());
        }
    }

    public void o() {
        int i7;
        s1 s1Var;
        Q0 q02 = (Q0) this.f15752p;
        u1 u1Var = q02.f17843f.f17811t;
        if (u1Var.d0().f14127a == 0) {
            s1Var = null;
        } else {
            S sA = u1Var.A();
            if (sA.f14000a.a(26, 34)) {
                i7 = sA.f14000a.a(25, 33) ? 2 : 1;
            } else {
                i7 = 0;
            }
            Handler handler = new Handler(((W) u1Var.f14069b).T0());
            int iO = u1Var.R0(23) ? u1Var.o() : 0;
            C1202j c1202jD0 = u1Var.d0();
            s1Var = new s1(u1Var, i7, c1202jD0.c, iO, c1202jD0.d, handler);
        }
        q02.f17849m = s1Var;
        P p10 = q02.f17847j;
        if (s1Var != null) {
            L l = (L) p10.f18537m;
            l.getClass();
            l.f18523a.setPlaybackToRemote(s1Var.a());
        } else {
            int iU = AbstractC1663q.u(u1Var.R0(21) ? u1Var.Z() : C1195c.f14046g);
            L l10 = (L) p10.f18537m;
            l10.getClass();
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(iU);
            l10.f18523a.setPlaybackToLocal(builder.build());
        }
    }

    public void p(F f10) {
        v();
        Q0 q02 = (Q0) this.f15752p;
        if (f10 == null) {
            ((L) q02.f17847j.f18537m).f18523a.setRatingType(0);
        } else {
            P p10 = q02.f17847j;
            ((L) p10.f18537m).f18523a.setRatingType(AbstractC1663q.v(f10.d.f13965i));
        }
        q02.L(q02.f17843f.f17811t);
    }

    public void q(int i7, u1 u1Var) {
        u(u1Var.e1());
        r(u1Var.R0(18) ? u1Var.N() : I.f13914K);
        u1Var.f1();
        v();
        t(u1Var.x0());
        s(u1Var.i());
        u1Var.d0();
        o();
        Q0.D((Q0) this.f15752p, u1Var);
        p(u1Var.d1());
    }

    public void r(I i7) {
        Q0 q02 = (Q0) this.f15752p;
        CharSequence queueTitle = ((C1736A) ((io.sentry.internal.debugmeta.c) q02.f17847j.f18538n).f16569m).f18506a.getQueueTitle();
        CharSequence charSequence = i7.f13959a;
        if (TextUtils.equals(queueTitle, charSequence)) {
            return;
        }
        u1 u1Var = q02.f17843f.f17811t;
        if (!u1Var.f18134g.a(17) || !u1Var.A().a(17)) {
            charSequence = null;
        }
        ((L) q02.f17847j.f18537m).f18523a.setQueueTitle(charSequence);
    }

    public void s(int i7) {
        P p10 = ((Q0) this.f15752p).f17847j;
        int iN = AbstractC1663q.n(i7);
        L l = (L) p10.f18537m;
        if (l.f18530j != iN) {
            l.f18530j = iN;
            synchronized (l.d) {
                for (int iBeginBroadcast = l.f18526f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((InterfaceC1748d) l.f18526f.getBroadcastItem(iBeginBroadcast)).d(iN);
                    } catch (RemoteException unused) {
                    }
                }
                l.f18526f.finishBroadcast();
            }
        }
    }

    public void t(boolean z6) {
        P p10 = ((Q0) this.f15752p).f17847j;
        M m10 = AbstractC1663q.f18029a;
        L l = (L) p10.f18537m;
        if (l.f18531k != z6) {
            l.f18531k = z6 ? 1 : 0;
            synchronized (l.d) {
                for (int iBeginBroadcast = l.f18526f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((InterfaceC1748d) l.f18526f.getBroadcastItem(iBeginBroadcast)).X(z6 ? 1 : 0);
                    } catch (RemoteException unused) {
                    }
                }
                l.f18526f.finishBroadcast();
            }
        }
    }

    public void u(f0 f0Var) {
        w(f0Var);
        v();
    }

    public void v() {
        Bitmap bitmap;
        C1192A c1192a;
        Q0 q02 = (Q0) this.f15752p;
        u1 u1Var = q02.f17843f.f17811t;
        F fD1 = u1Var.d1();
        I iF1 = u1Var.f1();
        long jS0 = -9223372036854775807L;
        if ((!u1Var.R0(16) || !u1Var.V0()) && u1Var.R0(16)) {
            jS0 = u1Var.s0();
        }
        String str = fD1 != null ? fD1.f13878a : "";
        Uri uri = (fD1 == null || (c1192a = fD1.f13879b) == null) ? null : c1192a.f13845a;
        if (Objects.equals((I) this.f15750m, iF1) && Objects.equals((String) this.f15751n, str) && Objects.equals((Uri) this.o, uri) && this.l == jS0) {
            return;
        }
        this.f15751n = str;
        this.o = uri;
        this.f15750m = iF1;
        this.l = jS0;
        I0 i02 = q02.f17843f;
        y yVarJ = i02.f17805m.j(iF1);
        if (yVarJ != null) {
            q02.o = null;
            if (yVarJ.isDone()) {
                try {
                    bitmap = (Bitmap) l.N(yVarJ);
                } catch (CancellationException | ExecutionException e10) {
                    AbstractC1346c.z("MediaSessionLegacyStub", "Failed to load bitmap: " + e10.getMessage());
                    bitmap = null;
                }
            } else {
                d dVar = new d();
                dVar.f15752p = this;
                dVar.f15750m = iF1;
                dVar.f15751n = str;
                dVar.o = uri;
                dVar.l = jS0;
                q02.o = dVar;
                Handler handler = i02.l;
                Objects.requireNonNull(handler);
                yVarJ.b(new t(yVarJ, 0, dVar), new ExecutorC1662p0(handler, 1));
            }
            bitmap = null;
        } else {
            bitmap = null;
        }
        Q0.C(q02.f17847j, AbstractC1663q.l(iF1, str, uri, jS0, bitmap));
    }

    public void w(f0 f0Var) {
        Q0 q02 = (Q0) this.f15752p;
        u1 u1Var = q02.f17843f.f17811t;
        if (!(u1Var.f18134g.a(17) && u1Var.A().a(17)) || f0Var.p()) {
            Q0.E(null, q02.f17847j);
            return;
        }
        M m10 = AbstractC1663q.f18029a;
        ArrayList arrayList = new ArrayList();
        e0 e0Var = new e0();
        for (int i7 = 0; i7 < f0Var.o(); i7++) {
            arrayList.add(f0Var.m(i7, e0Var, 0L).c);
        }
        ArrayList arrayList2 = new ArrayList();
        p pVar = new p(this, new AtomicInteger(0), arrayList, arrayList2, 4);
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            byte[] bArr = ((F) arrayList.get(i10)).d.f13967k;
            if (bArr == null) {
                arrayList2.add(null);
                pVar.run();
            } else {
                I0 i02 = q02.f17843f;
                y yVarP = i02.f17805m.p(bArr);
                arrayList2.add(yVarP);
                Handler handler = i02.l;
                Objects.requireNonNull(handler);
                yVarP.b(pVar, new ExecutorC1662p0(handler, 1));
            }
        }
    }
}
