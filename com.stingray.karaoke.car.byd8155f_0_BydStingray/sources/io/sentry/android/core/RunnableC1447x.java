package io.sentry.android.core;

import A.C0024l;
import A.C0044v0;
import B5.RunnableC0079c;
import android.content.res.Configuration;
import h2.AbstractC1343A;
import io.sentry.C1457d;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.protocol.EnumC1500e;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import k3.X0;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import n7.AbstractC1985B;
import n7.AbstractC1999n;
import n7.AbstractC2004s;
import o2.C2024a;
import o2.C2026c;

/* JADX INFO: renamed from: io.sentry.android.core.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1447x implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f16298m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16299n;
    public final /* synthetic */ Object o;

    public /* synthetic */ RunnableC1447x(AppComponentsBreadcrumbsIntegration appComponentsBreadcrumbsIntegration, long j3, Configuration configuration) {
        this.l = 0;
        this.f16299n = appComponentsBreadcrumbsIntegration;
        this.f16298m = j3;
        this.o = configuration;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        EnumC1500e enumC1500e = null;
        Object obj = this.o;
        long j3 = this.f16298m;
        Object obj2 = this.f16299n;
        switch (this.l) {
            case 0:
                AppComponentsBreadcrumbsIntegration appComponentsBreadcrumbsIntegration = (AppComponentsBreadcrumbsIntegration) obj2;
                if (appComponentsBreadcrumbsIntegration.f16037m != null) {
                    int i7 = appComponentsBreadcrumbsIntegration.l.getResources().getConfiguration().orientation;
                    if (i7 == 1) {
                        enumC1500e = EnumC1500e.PORTRAIT;
                    } else if (i7 == 2) {
                        enumC1500e = EnumC1500e.LANDSCAPE;
                    }
                    String lowerCase = enumC1500e != null ? enumC1500e.name().toLowerCase(Locale.ROOT) : "undefined";
                    C1457d c1457d = new C1457d(j3);
                    c1457d.o = "navigation";
                    c1457d.f16515q = "device.orientation";
                    c1457d.c(lowerCase, "position");
                    c1457d.f16517s = EnumC1495p1.INFO;
                    C1523x c1523x = new C1523x();
                    c1523x.c((Configuration) obj, "android:configuration");
                    appComponentsBreadcrumbsIntegration.f16037m.k(c1457d, c1523x);
                    return;
                }
                return;
            case 1:
                io.sentry.android.replay.capture.f fVar = (io.sentry.android.replay.capture.f) obj2;
                A7.m.f(fVar, "this$0");
                C0044v0 c0044v0 = (C0044v0) obj;
                io.sentry.android.replay.i iVar = fVar.f16353h;
                if (iVar != null) {
                    c0044v0.invoke(iVar, Long.valueOf(j3));
                }
                fVar.f16372u.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis() - fVar.f16370s.getExperimental().f16913a.f15884g;
                io.sentry.android.replay.i iVar2 = fVar.f16353h;
                if (iVar2 != null) {
                    A7.z zVar = new A7.z();
                    AbstractC2004s.M(iVar2.f16409s, new io.sentry.android.replay.h(jCurrentTimeMillis, iVar2, zVar, 0));
                    str = (String) zVar.l;
                } else {
                    str = null;
                }
                H7.w wVar = io.sentry.android.replay.capture.c.f16347r[2];
                io.sentry.android.replay.capture.b bVar = fVar.l;
                bVar.getClass();
                A7.m.f(wVar, "property");
                Object andSet = bVar.f16345a.getAndSet(str);
                if (!A7.m.a(andSet, str)) {
                    C0024l c0024l = new C0024l(andSet, str, bVar.c, 17);
                    io.sentry.android.replay.capture.c cVar = bVar.f16346b;
                    F1 f12 = cVar.f16348a;
                    if (f12.getMainThreadChecker().a()) {
                        AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 25));
                    } else {
                        c0024l.a();
                    }
                }
                ArrayList arrayList = fVar.f16374w;
                A7.v vVar = new A7.v();
                AbstractC2004s.M(arrayList, new io.sentry.android.replay.h(jCurrentTimeMillis, fVar, vVar, 1));
                if (vVar.l) {
                    int i10 = 0;
                    for (Object obj3 : arrayList) {
                        int i11 = i10 + 1;
                        if (i10 < 0) {
                            AbstractC1999n.I();
                            throw null;
                        }
                        io.sentry.android.replay.capture.i iVar3 = (io.sentry.android.replay.capture.i) obj3;
                        iVar3.f16376a.f15873E = i10;
                        List<io.sentry.rrweb.b> list = iVar3.f16377b.f15890m;
                        if (list != null) {
                            for (io.sentry.rrweb.b bVar2 : list) {
                                if (bVar2 instanceof io.sentry.rrweb.l) {
                                    ((io.sentry.rrweb.l) bVar2).o = i10;
                                }
                            }
                        }
                        i10 = i11;
                    }
                    return;
                }
                return;
            default:
                m2.b bVar3 = (m2.b) obj2;
                bVar3.getClass();
                int i12 = AbstractC1343A.f15195a;
                C1923x c1923x = ((SurfaceHolderCallbackC1920u) bVar3.f18945m).l;
                o2.e eVar = c1923x.f19776s;
                C2024a c2024aY = eVar.Y();
                eVar.Z(c2024aY, 26, new C2026c(c2024aY, obj, j3));
                if (c1923x.f19742Q == obj) {
                    c1923x.f19771m.f(26, new X0(23));
                    return;
                }
                return;
        }
    }

    public /* synthetic */ RunnableC1447x(Object obj, Object obj2, long j3, int i7) {
        this.l = i7;
        this.f16299n = obj;
        this.o = obj2;
        this.f16298m = j3;
    }
}
