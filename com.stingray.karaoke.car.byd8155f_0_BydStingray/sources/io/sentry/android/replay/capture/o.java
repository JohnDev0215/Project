package io.sentry.android.replay.capture;

import A.C0044v0;
import H7.w;
import S0.p;
import T.C0741g;
import h3.C1350a;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.G1;
import io.sentry.android.replay.u;
import io.sentry.protocol.t;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import n7.AbstractC1985B;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o extends c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final F1 f16384s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E f16385t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.transport.d f16386u;

    /* JADX WARN: Illegal instructions before constructor call */
    public o(F1 f12, E e10, io.sentry.transport.d dVar, ScheduledExecutorService scheduledExecutorService, int i7) {
        scheduledExecutorService = (i7 & 8) != 0 ? null : scheduledExecutorService;
        A7.m.f(f12, "options");
        A7.m.f(dVar, "dateProvider");
        super(f12, e10, dVar, scheduledExecutorService, null);
        this.f16384s = f12;
        this.f16385t = e10;
        this.f16386u = dVar;
    }

    @Override // io.sentry.android.replay.capture.l
    public final void a(boolean z6, p pVar) {
        this.f16384s.getLogger().k(EnumC1495p1.DEBUG, "Replay is already running in 'session' mode, not capturing for event", new Object[0]);
        this.f16352g.set(z6);
    }

    @Override // io.sentry.android.replay.capture.l
    public final void b() {
        q("pause", new n(this, 1));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void c(final C0044v0 c0044v0) {
        this.f16386u.getClass();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final int i7 = l().f16436b;
        final int i10 = l().f16435a;
        AbstractC1985B.u(m(), this.f16384s, "SessionCaptureStrategy.add_frame", new Runnable() { // from class: io.sentry.android.replay.capture.m
            @Override // java.lang.Runnable
            public final void run() {
                o oVar = this.l;
                A7.m.f(oVar, "this$0");
                C0044v0 c0044v02 = c0044v0;
                io.sentry.android.replay.i iVar = oVar.f16353h;
                if (iVar != null) {
                    c0044v02.invoke(iVar, Long.valueOf(jCurrentTimeMillis));
                }
                w wVar = c.f16347r[1];
                b bVar = oVar.f16355j;
                bVar.getClass();
                A7.m.f(wVar, "property");
                Date date = (Date) bVar.f16345a.get();
                F1 f12 = oVar.f16384s;
                if (date == null) {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Segment timestamp is not set, not recording frame", new Object[0]);
                    return;
                }
                if (oVar.f16352g.get()) {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Not capturing segment, because the app is terminating, will be captured on next launch", new Object[0]);
                    return;
                }
                oVar.f16386u.getClass();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (jCurrentTimeMillis2 - date.getTime() >= f12.getExperimental().f16913a.f15885h) {
                    k kVarH = c.h(oVar, f12.getExperimental().f16913a.f15885h, date, oVar.i(), oVar.j(), i7, i10);
                    if (kVarH instanceof i) {
                        i iVar2 = (i) kVarH;
                        i.a(iVar2, oVar.f16385t);
                        oVar.n(oVar.j() + 1);
                        oVar.p(iVar2.f16376a.f15874F);
                    }
                }
                if (jCurrentTimeMillis2 - oVar.f16356k.get() >= f12.getExperimental().f16913a.f15886i) {
                    f12.getReplayController().stop();
                    f12.getLogger().k(EnumC1495p1.INFO, "Session replay deadline exceeded (1h), stopping recording", new Object[0]);
                }
            }
        });
    }

    @Override // io.sentry.android.replay.capture.l
    public final void e(u uVar) {
        q("onConfigurationChanged", new n(this, 0));
        o(uVar);
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void f(u uVar, int i7, t tVar, G1 g12) {
        A7.m.f(uVar, "recorderConfig");
        A7.m.f(tVar, "replayId");
        super.f(uVar, i7, tVar, g12);
        E e10 = this.f16385t;
        if (e10 != null) {
            e10.l(new G2.a(this, 16));
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public final l g() {
        return this;
    }

    public final void q(String str, InterfaceC2782k interfaceC2782k) {
        this.f16386u.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        w wVar = c.f16347r[1];
        b bVar = this.f16355j;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Date date = (Date) bVar.f16345a.get();
        if (date == null) {
            return;
        }
        int iJ = j();
        long time = jCurrentTimeMillis - date.getTime();
        t tVarI = i();
        int i7 = l().f16436b;
        int i10 = l().f16435a;
        AbstractC1985B.u(m(), this.f16384s, "SessionCaptureStrategy.".concat(str), new d(this, time, date, tVarI, iJ, i7, i10, interfaceC2782k));
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void stop() {
        io.sentry.android.replay.i iVar = this.f16353h;
        q("stop", new C0741g(this, 16, iVar != null ? iVar.g() : null));
        E e10 = this.f16385t;
        if (e10 != null) {
            e10.l(new C1350a(12));
        }
        super.stop();
    }
}
