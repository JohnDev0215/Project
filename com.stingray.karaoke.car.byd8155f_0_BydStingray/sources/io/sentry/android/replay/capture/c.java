package io.sentry.android.replay.capture;

import A.C0024l;
import A7.A;
import A7.B;
import A7.p;
import B5.RunnableC0079c;
import G.i0;
import H7.w;
import e0.C1183w;
import h6.r;
import io.sentry.E;
import io.sentry.F1;
import io.sentry.G1;
import io.sentry.android.replay.u;
import io.sentry.protocol.t;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import n7.AbstractC1985B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements l {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ w[] f16347r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f16348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E f16349b;
    public final io.sentry.transport.d c;
    public final InterfaceC2785n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m7.n f16350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i0 f16351f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f16352g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public io.sentry.android.replay.i f16353h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f16354i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f16355j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicLong f16356k;
    public final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f16357m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f16358n;
    public final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.android.replay.util.a f16359p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final m7.n f16360q;

    static {
        p pVar = new p(c.class, "recorderConfig", "getRecorderConfig()Lio/sentry/android/replay/ScreenshotRecorderConfig;", 0);
        B b10 = A.f443a;
        f16347r = new w[]{b10.f(pVar), A7.k.a(c.class, "segmentTimestamp", "getSegmentTimestamp()Ljava/util/Date;", 0, b10), A7.k.a(c.class, "screenAtStart", "getScreenAtStart()Ljava/lang/String;", 0, b10), A7.k.a(c.class, "currentReplayId", "getCurrentReplayId()Lio/sentry/protocol/SentryId;", 0, b10), A7.k.a(c.class, "currentSegment", "getCurrentSegment()I", 0, b10), A7.k.a(c.class, "replayType", "getReplayType()Lio/sentry/SentryReplayEvent$ReplayType;", 0, b10)};
    }

    public c(F1 f12, E e10, io.sentry.transport.d dVar, ScheduledExecutorService scheduledExecutorService, InterfaceC2785n interfaceC2785n) {
        A7.m.f(f12, "options");
        A7.m.f(dVar, "dateProvider");
        this.f16348a = f12;
        this.f16349b = e10;
        this.c = dVar;
        this.d = interfaceC2785n;
        this.f16350e = r.o(a.f16344m);
        A7.m.f(dVar, "dateProvider");
        i0 i0Var = new i0();
        i0Var.f2584n = dVar;
        i0Var.o = new LinkedHashMap(10);
        this.f16351f = i0Var;
        this.f16352g = new AtomicBoolean(false);
        this.f16354i = new b(this, this, 0);
        this.f16355j = new b(this, this, 4);
        this.f16356k = new AtomicLong();
        this.l = new b(this, this, 5);
        this.f16357m = new b(t.f16774m, this, this);
        this.f16358n = new b(this, this, 2);
        this.o = new b(this, this, 3);
        this.f16359p = new io.sentry.android.replay.util.a(f12, k(), new C1183w(this, 2));
        this.f16360q = r.o(new C1183w(scheduledExecutorService, 3));
    }

    public static k h(c cVar, long j3, Date date, t tVar, int i7, int i10, int i11) {
        b bVar = cVar.o;
        w[] wVarArr = f16347r;
        w wVar = wVarArr[5];
        bVar.getClass();
        A7.m.f(wVar, "property");
        G1 g12 = (G1) bVar.f16345a.get();
        io.sentry.android.replay.i iVar = cVar.f16353h;
        int i12 = cVar.l().f16437e;
        b bVar2 = cVar.l;
        w wVar2 = wVarArr[2];
        bVar2.getClass();
        A7.m.f(wVar2, "property");
        String str = (String) bVar2.f16345a.get();
        io.sentry.android.replay.util.a aVar = cVar.f16359p;
        cVar.getClass();
        A7.m.f(tVar, "replayId");
        A7.m.f(g12, "replayType");
        A7.m.f(aVar, "events");
        return h.a(cVar.f16349b, cVar.f16348a, j3, date, tVar, i7, i10, i11, g12, iVar, i12, str, null, aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01a6  */
    @Override // io.sentry.android.replay.capture.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.capture.c.d(android.view.MotionEvent):void");
    }

    @Override // io.sentry.android.replay.capture.l
    public void f(u uVar, int i7, t tVar, G1 g12) {
        io.sentry.android.replay.i iVar;
        A7.m.f(uVar, "recorderConfig");
        A7.m.f(tVar, "replayId");
        InterfaceC2785n interfaceC2785n = this.d;
        if (interfaceC2785n == null || (iVar = (io.sentry.android.replay.i) interfaceC2785n.invoke(tVar, uVar)) == null) {
            iVar = new io.sentry.android.replay.i(this.f16348a, tVar, uVar);
        }
        this.f16353h = iVar;
        w[] wVarArr = f16347r;
        w wVar = wVarArr[3];
        b bVar = this.f16357m;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Object andSet = bVar.f16345a.getAndSet(tVar);
        if (!A7.m.a(andSet, tVar)) {
            C0024l c0024l = new C0024l(andSet, tVar, bVar.c, 13);
            c cVar = bVar.f16346b;
            F1 f12 = cVar.f16348a;
            if (f12.getMainThreadChecker().a()) {
                AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 21));
            } else {
                c0024l.a();
            }
        }
        n(i7);
        if (g12 == null) {
            g12 = this instanceof o ? G1.SESSION : G1.BUFFER;
        }
        A7.m.f(g12, "<set-?>");
        w wVar2 = wVarArr[5];
        b bVar2 = this.o;
        bVar2.getClass();
        A7.m.f(wVar2, "property");
        Object andSet2 = bVar2.f16345a.getAndSet(g12);
        if (!A7.m.a(andSet2, g12)) {
            C0024l c0024l2 = new C0024l(andSet2, g12, bVar2.c, 15);
            c cVar2 = bVar2.f16346b;
            F1 f13 = cVar2.f16348a;
            if (f13.getMainThreadChecker().a()) {
                AbstractC1985B.u(cVar2.k(), f13, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l2, 23));
            } else {
                c0024l2.a();
            }
        }
        o(uVar);
        p(AbstractC1985B.f());
        AtomicLong atomicLong = this.f16356k;
        this.c.getClass();
        atomicLong.set(System.currentTimeMillis());
    }

    public final t i() {
        w wVar = f16347r[3];
        b bVar = this.f16357m;
        bVar.getClass();
        A7.m.f(wVar, "property");
        return (t) bVar.f16345a.get();
    }

    public final int j() {
        w wVar = f16347r[4];
        b bVar = this.f16358n;
        bVar.getClass();
        A7.m.f(wVar, "property");
        return ((Number) bVar.f16345a.get()).intValue();
    }

    public final ScheduledExecutorService k() {
        Object value = this.f16350e.getValue();
        A7.m.e(value, "<get-persistingExecutor>(...)");
        return (ScheduledExecutorService) value;
    }

    public final u l() {
        w wVar = f16347r[0];
        b bVar = this.f16354i;
        bVar.getClass();
        A7.m.f(wVar, "property");
        return (u) bVar.f16345a.get();
    }

    public final ScheduledExecutorService m() {
        Object value = this.f16360q.getValue();
        A7.m.e(value, "<get-replayExecutor>(...)");
        return (ScheduledExecutorService) value;
    }

    public final void n(int i7) {
        w wVar = f16347r[4];
        Integer numValueOf = Integer.valueOf(i7);
        b bVar = this.f16358n;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Object andSet = bVar.f16345a.getAndSet(numValueOf);
        if (A7.m.a(andSet, numValueOf)) {
            return;
        }
        C0024l c0024l = new C0024l(andSet, numValueOf, bVar.c, 14);
        c cVar = bVar.f16346b;
        F1 f12 = cVar.f16348a;
        if (f12.getMainThreadChecker().a()) {
            AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 22));
        } else {
            c0024l.a();
        }
    }

    public final void o(u uVar) {
        A7.m.f(uVar, "<set-?>");
        w wVar = f16347r[0];
        b bVar = this.f16354i;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Object andSet = bVar.f16345a.getAndSet(uVar);
        if (A7.m.a(andSet, uVar)) {
            return;
        }
        C0024l c0024l = new C0024l(andSet, uVar, bVar.c, 12);
        c cVar = bVar.f16346b;
        F1 f12 = cVar.f16348a;
        if (f12.getMainThreadChecker().a()) {
            AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 20));
        } else {
            c0024l.a();
        }
    }

    public final void p(Date date) {
        w wVar = f16347r[1];
        b bVar = this.f16355j;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Object andSet = bVar.f16345a.getAndSet(date);
        if (A7.m.a(andSet, date)) {
            return;
        }
        C0024l c0024l = new C0024l(andSet, date, bVar.c, 16);
        c cVar = bVar.f16346b;
        F1 f12 = cVar.f16348a;
        if (f12.getMainThreadChecker().a()) {
            AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 24));
        } else {
            c0024l.a();
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public void stop() {
        io.sentry.android.replay.i iVar = this.f16353h;
        if (iVar != null) {
            iVar.close();
        }
        n(-1);
        this.f16356k.set(0L);
        p(null);
        t tVar = t.f16774m;
        A7.m.e(tVar, "EMPTY_ID");
        w wVar = f16347r[3];
        b bVar = this.f16357m;
        bVar.getClass();
        A7.m.f(wVar, "property");
        Object andSet = bVar.f16345a.getAndSet(tVar);
        if (A7.m.a(andSet, tVar)) {
            return;
        }
        C0024l c0024l = new C0024l(andSet, tVar, bVar.c, 13);
        c cVar = bVar.f16346b;
        F1 f12 = cVar.f16348a;
        if (f12.getMainThreadChecker().a()) {
            AbstractC1985B.u(cVar.k(), f12, "CaptureStrategy.runInBackground", new RunnableC0079c(c0024l, 21));
        } else {
            c0024l.a();
        }
    }
}
