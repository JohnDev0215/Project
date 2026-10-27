package io.sentry;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import h2.AbstractC1343A;
import h5.C1373E;
import h8.AbstractC1387d;
import io.sentry.android.core.ActivityLifecycleIntegration;
import io.sentry.android.core.C1427c;
import io.sentry.android.core.C1431g;
import io.sentry.android.core.RunnableC1426b;
import io.sentry.android.core.SentryAndroidOptions;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import k3.C1628b0;
import k3.C1679y0;
import k3.C1681z0;
import k3.InterfaceC1655m;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Y0 implements U1, k3.X, h2.l, k3.P0, h5.q {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f15993m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f15994n;
    public final /* synthetic */ Object o;

    public /* synthetic */ Y0(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f15993m = obj;
        this.f15994n = obj2;
        this.o = obj3;
    }

    @Override // io.sentry.U1
    public void a(S1 s12) {
        C1427c c1427cT;
        int i7;
        P1 p12 = (P1) this.f15993m;
        U1 u12 = (U1) this.f15994n;
        AtomicReference atomicReference = (AtomicReference) this.o;
        if (u12 != null) {
            p12.getClass();
            u12.a(s12);
        }
        C1431g c1431g = p12.f15923r.f16487r;
        if (c1431g != null) {
            ActivityLifecycleIntegration activityLifecycleIntegration = c1431g.f16167a;
            WeakReference weakReference = c1431g.f16168b;
            String str = c1431g.c;
            activityLifecycleIntegration.getClass();
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                N0.q qVar = activityLifecycleIntegration.B;
                io.sentry.protocol.t tVar = p12.f15910a;
                synchronized (qVar) {
                    if (qVar.H()) {
                        C1427c c1427c = null;
                        qVar.Q(new RunnableC1426b(qVar, activity, 1), null);
                        C1427c c1427c2 = (C1427c) ((WeakHashMap) qVar.f4401p).remove(activity);
                        if (c1427c2 != null && (c1427cT = qVar.t()) != null) {
                            c1427c = new C1427c(c1427cT.f16152a - c1427c2.f16152a, c1427cT.f16153b - c1427c2.f16153b, c1427cT.c - c1427c2.c);
                        }
                        if (c1427c != null && ((i7 = c1427c.f16152a) != 0 || c1427c.f16153b != 0 || c1427c.c != 0)) {
                            io.sentry.protocol.i iVar = new io.sentry.protocol.i(Integer.valueOf(i7), "none");
                            io.sentry.protocol.i iVar2 = new io.sentry.protocol.i(Integer.valueOf(c1427c.f16153b), "none");
                            io.sentry.protocol.i iVar3 = new io.sentry.protocol.i(Integer.valueOf(c1427c.c), "none");
                            HashMap map = new HashMap();
                            map.put("frames_total", iVar);
                            map.put("frames_slow", iVar2);
                            map.put("frames_frozen", iVar3);
                            ((ConcurrentHashMap) qVar.o).put(tVar, map);
                        }
                    }
                }
            } else {
                SentryAndroidOptions sentryAndroidOptions = activityLifecycleIntegration.o;
                if (sentryAndroidOptions != null) {
                    sentryAndroidOptions.getLogger().k(EnumC1495p1.WARNING, "Unable to track activity frames as the Activity %s has been destroyed.", str);
                }
            }
        }
        d2 d2Var = p12.f15922q;
        if (d2Var != null) {
            atomicReference.set(d2Var.i(p12));
        }
    }

    @Override // h5.q
    public h5.y apply(Object obj) {
        Object obj2 = this.f15994n;
        Object obj3 = this.o;
        Object obj4 = this.f15993m;
        switch (this.l) {
            case 5:
                k3.I0 i02 = (k3.I0) obj4;
                Handler handler = i02.l;
                C1679y0 c1679y0 = (C1679y0) obj2;
                k3.C0 c02 = new k3.C0(i02, c1679y0, new T1.k(i02, (k3.X0) obj3, (C1681z0) obj, 12));
                k3.C1 c1 = new k3.C1(0);
                int i7 = AbstractC1343A.f15195a;
                C1373E c1373e = new C1373E();
                AbstractC1343A.K(handler, new T1.k(c1373e, c02, c1, 3));
                return c1373e;
            default:
                k3.I0 i03 = (k3.I0) obj4;
                Handler handler2 = i03.l;
                C1679y0 c1679y02 = (C1679y0) obj2;
                k3.C0 c03 = new k3.C0(i03, c1679y02, new io.sentry.android.replay.p(i03, (k3.k1) obj3, c1679y02, (List) obj, 8));
                k3.C1 c12 = new k3.C1(0);
                int i10 = AbstractC1343A.f15195a;
                C1373E c1373e2 = new C1373E();
                AbstractC1343A.K(handler2, new T1.k(c1373e2, c03, c12, 3));
                return c1373e2;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        ((e2.U) obj).t(((Integer) this.o).intValue(), ((k3.q1) ((H0.b) this.f15993m).f2765m).c.f17732a, ((k3.q1) ((H0.b) this.f15994n).f2765m).c.f17732a);
    }

    public void c(R1 r12) {
        ConcurrentHashMap concurrentHashMap;
        B8.t tVar = (B8.t) this.f15993m;
        if (r12 == null) {
            ((F1) tVar.f808n).getLogger().k(EnumC1495p1.INFO, "Session is null on scope.withSession", new Object[0]);
            return;
        }
        tVar.getClass();
        C1480k1 c1480k1 = (C1480k1) this.f15994n;
        String strE = null;
        Q1 q12 = c1480k1.b() != null ? Q1.Crashed : null;
        boolean z6 = Q1.Crashed == q12 || c1480k1.c();
        io.sentry.protocol.n nVar = c1480k1.o;
        String str = (nVar == null || (concurrentHashMap = nVar.f16752q) == null || !concurrentHashMap.containsKey("user-agent")) ? null : (String) c1480k1.o.f16752q.get("user-agent");
        Object objG = AbstractC1387d.g((C1523x) this.o);
        if (objG instanceof io.sentry.hints.a) {
            strE = ((io.sentry.hints.a) objG).e();
            q12 = Q1.Abnormal;
        }
        if (!r12.c(q12, str, z6, strE) || r12.f15933r == Q1.Ok) {
            return;
        }
        r12.b(AbstractC1985B.f());
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        k3.Q0 q02 = (k3.Q0) this.f15993m;
        Bundle bundle = (Bundle) this.o;
        if (bundle == null) {
            q02.getClass();
            bundle = Bundle.EMPTY;
        }
        q02.f17843f.l(c1679y0, (k3.y1) this.f15994n, bundle);
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        C1628b0 c1628b0 = (C1628b0) this.f15993m;
        c1628b0.getClass();
        interfaceC1655m.F0(c1628b0.c, i7, ((k3.y1) this.f15994n).b(), (Bundle) this.o);
    }
}
