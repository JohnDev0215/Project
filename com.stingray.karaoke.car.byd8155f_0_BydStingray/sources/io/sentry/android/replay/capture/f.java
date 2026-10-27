package io.sentry.android.replay.capture;

import A.C0044v0;
import S0.p;
import T.C0741g;
import android.view.MotionEvent;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.G1;
import io.sentry.T0;
import io.sentry.android.core.RunnableC1447x;
import io.sentry.android.replay.u;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final F1 f16370s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E f16371t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.transport.d f16372u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final io.sentry.util.g f16373v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f16374w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(F1 f12, E e10, io.sentry.transport.d dVar, io.sentry.util.g gVar) {
        super(f12, e10, dVar, null, null);
        A7.m.f(f12, "options");
        A7.m.f(dVar, "dateProvider");
        A7.m.f(gVar, "random");
        this.f16370s = f12;
        this.f16371t = e10;
        this.f16372u = dVar;
        this.f16373v = gVar;
        this.f16374w = new ArrayList();
    }

    @Override // io.sentry.android.replay.capture.l
    public final void a(boolean z6, p pVar) {
        F1 f12 = this.f16370s;
        Double d = f12.getExperimental().f16913a.f15881b;
        io.sentry.util.g gVar = this.f16373v;
        A7.m.f(gVar, "<this>");
        if (!(d != null && d.doubleValue() >= gVar.b())) {
            f12.getLogger().k(EnumC1495p1.INFO, "Replay wasn't sampled by onErrorSampleRate, not capturing for event", new Object[0]);
            return;
        }
        E e10 = this.f16371t;
        if (e10 != null) {
            e10.l(new G2.a(this, 15));
        }
        if (!z6) {
            q("capture_replay", new C0741g(this, 15, pVar));
        } else {
            this.f16352g.set(true);
            f12.getLogger().k(EnumC1495p1.DEBUG, "Not capturing replay for crashed event, will be captured on next launch", new Object[0]);
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public final void b() {
        q("pause", new e(this, 1));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void c(C0044v0 c0044v0) {
        this.f16372u.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        AbstractC1985B.u(m(), this.f16370s, "BufferCaptureStrategy.add_frame", new RunnableC1447x(this, c0044v0, jCurrentTimeMillis, 1));
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void d(MotionEvent motionEvent) {
        super.d(motionEvent);
        this.f16372u.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f16370s.getExperimental().f16913a.f15884g;
        io.sentry.android.replay.util.a aVar = this.f16359p;
        A7.m.f(aVar, "events");
        synchronized (h.f16375a) {
            for (io.sentry.rrweb.b bVar = (io.sentry.rrweb.b) aVar.peek(); bVar != null; bVar = (io.sentry.rrweb.b) aVar.peek()) {
                if (bVar.f16836m >= jCurrentTimeMillis) {
                    break;
                }
                aVar.remove();
            }
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public final void e(u uVar) {
        q("configuration_changed", new e(this, 0));
        o(uVar);
    }

    @Override // io.sentry.android.replay.capture.l
    public final l g() {
        if (this.f16352g.get()) {
            this.f16370s.getLogger().k(EnumC1495p1.DEBUG, "Not converting to session mode, because the process is about to terminate", new Object[0]);
            return this;
        }
        ScheduledExecutorService scheduledExecutorServiceM = m();
        o oVar = new o(this.f16370s, this.f16371t, this.f16372u, scheduledExecutorServiceM, 16);
        oVar.f(l(), j(), i(), G1.BUFFER);
        return oVar;
    }

    public final void q(String str, InterfaceC2782k interfaceC2782k) {
        Date dateG;
        ArrayList arrayList;
        F1 f12 = this.f16370s;
        long j3 = f12.getExperimental().f16913a.f15884g;
        this.f16372u.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        io.sentry.android.replay.i iVar = this.f16353h;
        if (iVar == null || (arrayList = iVar.f16409s) == null || !(!arrayList.isEmpty())) {
            dateG = AbstractC1985B.g(jCurrentTimeMillis - j3);
        } else {
            io.sentry.android.replay.i iVar2 = this.f16353h;
            A7.m.c(iVar2);
            dateG = AbstractC1985B.g(((io.sentry.android.replay.j) AbstractC1998m.Y(iVar2.f16409s)).f16413b);
        }
        Date date = dateG;
        A7.m.e(date, "if (cache?.frames?.isNot…ReplayDuration)\n        }");
        AbstractC1985B.u(m(), f12, "BufferCaptureStrategy.".concat(str), new d(this, jCurrentTimeMillis - date.getTime(), date, i(), j(), l().f16436b, l().f16435a, interfaceC2782k));
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void stop() {
        io.sentry.android.replay.i iVar = this.f16353h;
        AbstractC1985B.u(m(), this.f16370s, "BufferCaptureStrategy.stop", new T0(iVar != null ? iVar.g() : null, 1));
        super.stop();
    }
}
