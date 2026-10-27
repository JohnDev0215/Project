package G2;

import A.C0024l;
import A7.m;
import B2.AbstractC0062b;
import B2.InterfaceC0068h;
import B2.L;
import B2.x;
import B5.RunnableC0079c;
import B5.u;
import H7.w;
import Y2.h;
import Y4.e;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.RemoteException;
import android.view.Surface;
import com.stingray.karaoke.car.MainActivity;
import d5.C1077F;
import d5.I;
import d5.d0;
import e0.AbstractC1174n;
import e2.K;
import e2.S;
import e2.U;
import g1.q;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.g;
import h2.l;
import h2.t;
import h3.y;
import h5.v;
import i2.p;
import io.sentry.F1;
import io.sentry.M0;
import io.sentry.N0;
import io.sentry.O0;
import io.sentry.Q;
import io.sentry.R1;
import io.sentry.T1;
import io.sentry.V;
import io.sentry.V1;
import io.sentry.android.core.internal.gestures.f;
import io.sentry.android.navigation.SentryNavigationListener;
import io.sentry.android.replay.capture.o;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import k3.B1;
import k3.C1;
import k3.C1627b;
import k3.C1628b0;
import k3.C1647i;
import k3.C1672v;
import k3.C1679y0;
import k3.E1;
import k3.H0;
import k3.I0;
import k3.InterfaceC1655m;
import k3.InterfaceC1656m0;
import k3.InterfaceC1677x0;
import k3.j1;
import k3.l1;
import k3.m1;
import k3.n1;
import k3.u1;
import k3.y1;
import k6.C1704l;
import n2.SurfaceHolderCallbackC1920u;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements InterfaceC0068h, g, p, O0, io.sentry.util.c, M0, InterfaceC1656m0, H0, l1, j1, l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2649m;

    public /* synthetic */ a(Object obj, int i7) {
        this.l = i7;
        this.f2649m = obj;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        v vVar = v.f15564m;
        if (!i02.h()) {
            ((j1) this.f2649m).f(i02.f17811t, c1679y0);
            m1.u1(c1679y0, i7, new C1(0));
        }
        return v.f15564m;
    }

    @Override // h2.g
    public void accept(Object obj) {
        switch (this.l) {
            case 3:
                Y2.a aVar = (Y2.a) obj;
                h hVar = (h) this.f2649m;
                hVar.getClass();
                Y2.g gVar = new Y2.g(aVar.f10630b, e.D(aVar.f10629a, aVar.c));
                hVar.c.add(gVar);
                long j3 = hVar.f10645j;
                if (j3 == -9223372036854775807L || aVar.f10630b >= j3) {
                    hVar.d(gVar);
                }
                break;
            case 4:
                ((C1077F) this.f2649m).a((Y2.a) obj);
                break;
            default:
                ((u1) obj).r((Surface) this.f2649m);
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        U u4 = (U) obj;
        switch (this.l) {
            case 25:
                u4.R((C1290c) this.f2649m);
                break;
            case 26:
                u4.F(((SurfaceHolderCallbackC1920u) this.f2649m).l.f19740O);
                break;
            case 27:
                u4.O((K) this.f2649m);
                break;
            default:
                u4.o((List) this.f2649m);
                break;
        }
    }

    @Override // k3.H0
    public void c(InterfaceC1677x0 interfaceC1677x0, int i7) {
        interfaceC1677x0.b(i7, (I) this.f2649m);
    }

    @Override // i2.p
    public void d(long j3, t tVar) {
        AbstractC0062b.d(j3, tVar, (L[]) ((y) this.f2649m).f15500n);
    }

    @Override // k3.InterfaceC1656m0
    public void e(C1628b0 c1628b0) {
        switch (this.l) {
            case 19:
                if (c1628b0.v() && c1628b0.f17912k.isEmpty()) {
                    B1 b12 = c1628b0.o.c;
                    long j3 = b12.c;
                    B1 b13 = (B1) this.f2649m;
                    if (j3 < b13.c && n1.d(b13, b12)) {
                        c1628b0.o = c1628b0.o.j(b13);
                        break;
                    }
                }
                break;
            default:
                C1647i c1647i = (C1647i) this.f2649m;
                InterfaceC1655m interfaceC1655m = c1628b0.f17899A;
                C1672v c1672v = c1628b0.f17904a;
                if (interfaceC1655m != null) {
                    AbstractC1346c.p("MCImplBase", "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
                    c1672v.a();
                } else {
                    c1628b0.f17899A = c1647i.c;
                    c1628b0.f17918s = c1647i.f17983e;
                    S s6 = c1647i.f17984f;
                    c1628b0.f17919t = s6;
                    S s10 = c1647i.f17985g;
                    c1628b0.f17920u = s10;
                    S sX0 = C1628b0.X0(s6, s10);
                    c1628b0.f17921v = sX0;
                    I i7 = c1647i.f17989k;
                    c1628b0.f17915p = i7;
                    I i10 = c1647i.l;
                    c1628b0.f17916q = i10;
                    c1628b0.f17917r = C1628b0.r1(i10, i7, c1628b0.f17918s, sX0);
                    u uVar = new u(6, (byte) 0);
                    int i11 = 0;
                    while (true) {
                        d0 d0Var = c1647i.f17991n;
                        if (i11 < d0Var.size()) {
                            C1627b c1627b = (C1627b) d0Var.get(i11);
                            y1 y1Var = c1627b.f17892a;
                            if (y1Var != null && y1Var.f18167a == 0) {
                                uVar.s(y1Var.f18168b, c1627b);
                            }
                            i11++;
                        } else {
                            uVar.g();
                            c1628b0.o = c1647i.f17988j;
                            E1 e12 = c1628b0.f17906e;
                            MediaSession.Token tokenT = c1647i.f17990m;
                            if (tokenT == null) {
                                tokenT = e12.f17758a.t();
                            }
                            MediaSession.Token token = tokenT;
                            if (token != null) {
                                c1628b0.B = new MediaController(c1628b0.d, token);
                            }
                            try {
                                c1647i.c.asBinder().linkToDeath(c1628b0.f17908g, 0);
                                c1628b0.l = new E1(e12.f17758a.a(), c1647i.f17981a, c1647i.f17982b, e12.f17758a.getPackageName(), c1647i.c, c1647i.f17986h, token);
                                c1672v.v();
                            } catch (RemoteException unused) {
                                c1672v.a();
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override // k3.j1
    public void f(u1 u1Var, C1679y0 c1679y0) {
        ((g) this.f2649m).accept(u1Var);
    }

    @Override // io.sentry.util.c
    public Object g() {
        return ((io.sentry.cache.c) this.f2649m).l.getSerializer();
    }

    @Override // B2.InterfaceC0068h
    public long h(long j3) {
        x xVar = (x) this.f2649m;
        return AbstractC1343A.j((j3 * ((long) xVar.f605e)) / 1000000, 0L, xVar.f610j - 1);
    }

    @Override // io.sentry.O0
    public void i(N0 n02) {
        R1 r12;
        switch (this.l) {
            case 10:
                io.sentry.android.core.L l = (io.sentry.android.core.L) this.f2649m;
                if (l.l.get() != 0 || (r12 = n02.f15901k) == null) {
                    return;
                }
                Date date = r12.l;
                if ((date == null ? null : (Date) date.clone()) != null) {
                    AtomicLong atomicLong = l.l;
                    Date date2 = r12.l;
                    atomicLong.set((date2 != null ? (Date) date2.clone() : null).getTime());
                    l.f16063m.set(true);
                    return;
                }
                return;
            case 11:
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.f2649m;
                R1 r13 = n02.f15901k;
                if (r13 != null) {
                    Date date3 = r13.l;
                    if ((date3 == null ? null : (Date) date3.clone()) != null) {
                        atomicBoolean.set(true);
                        return;
                    }
                    return;
                }
                return;
            case 12:
                f fVar = (f) this.f2649m;
                fVar.getClass();
                synchronized (n02.f15902m) {
                    if (n02.f15893a == fVar.f16185p) {
                        n02.a();
                    }
                    break;
                }
                return;
            case 13:
                V v10 = (V) this.f2649m;
                m.f(v10, "$transaction");
                m.f(n02, "scope");
                synchronized (n02.f15902m) {
                    if (n02.f15893a == null) {
                        n02.e(v10);
                    }
                    break;
                }
                return;
            case 14:
                SentryNavigationListener sentryNavigationListener = (SentryNavigationListener) this.f2649m;
                m.f(sentryNavigationListener, "this$0");
                m.f(n02, "scope");
                synchronized (n02.f15902m) {
                    if (m.a(n02.f15893a, sentryNavigationListener.f16308g)) {
                        n02.a();
                    }
                    break;
                }
                return;
            case 15:
                io.sentry.android.replay.capture.f fVar2 = (io.sentry.android.replay.capture.f) this.f2649m;
                m.f(fVar2, "this$0");
                m.f(n02, "it");
                n02.b(fVar2.i());
                return;
            default:
                o oVar = (o) this.f2649m;
                m.f(oVar, "this$0");
                m.f(n02, "it");
                n02.b(oVar.i());
                String str = n02.c;
                String strA1 = str != null ? Q8.h.a1('.', str, str) : null;
                w wVar = io.sentry.android.replay.capture.c.f16347r[2];
                io.sentry.android.replay.capture.b bVar = oVar.l;
                bVar.getClass();
                m.f(wVar, "property");
                Object andSet = bVar.f16345a.getAndSet(strA1);
                if (m.a(andSet, strA1)) {
                    return;
                }
                C0024l c0024l = new C0024l(andSet, strA1, bVar.c, 17);
                io.sentry.android.replay.capture.c cVar = bVar.f16346b;
                F1 f12 = cVar.f16348a;
                if (f12.getMainThreadChecker().a()) {
                    AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 25));
                    return;
                } else {
                    c0024l.a();
                    return;
                }
        }
    }

    @Override // io.sentry.M0
    public void j(y yVar) {
        y yVar2 = new y(2);
        N0 n02 = (N0) this.f2649m;
        n02.f15905q = yVar2;
        T1 t12 = new T1((io.sentry.protocol.t) yVar2.f15499m, (V1) yVar2.f15500n, "default", null, null);
        t12.f15963t = "auto";
        Iterator<Q> it = n02.f15900j.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().f(t12, n02);
        }
    }

    public void k() {
        A7.o oVar = (A7.o) this.f2649m;
        synchronized (AbstractC1174n.f13721b) {
            AbstractC1174n.f13724g = AbstractC1998m.k0((List) AbstractC1174n.f13724g, oVar);
        }
    }

    public void l(Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i7 = MainActivity.f12974j0;
        MainActivity mainActivity = (MainActivity) this.f2649m;
        if (!zBooleanValue) {
            mainActivity.w();
            return;
        }
        C1704l c1704l = mainActivity.f12992W;
        if (c1704l != null) {
            c1704l.a();
        } else {
            m.j("driveModeManager");
            throw null;
        }
    }

    public float m(float f10) {
        return ((q) this.f2649m).f14753k.getDensity() * f10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ a(InterfaceC2785n interfaceC2785n) {
        this.l = 7;
        this.f2649m = (A7.o) interfaceC2785n;
    }
}
