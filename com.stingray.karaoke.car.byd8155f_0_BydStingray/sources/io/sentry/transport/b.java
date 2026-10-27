package io.sentry.transport;

import h6.q;
import h8.AbstractC1387d;
import io.sentry.AbstractC1453b1;
import io.sentry.C1459d1;
import io.sentry.C1489n1;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.IOException;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final C1489n1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1523x f16889m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.cache.d f16890n;
    public final o o = new o(-1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ c f16891p;

    public b(c cVar, C1489n1 c1489n1, C1523x c1523x, io.sentry.cache.d dVar) {
        this.f16891p = cVar;
        h6.e.q(c1489n1, "Envelope is required.");
        this.l = c1489n1;
        this.f16889m = c1523x;
        h6.e.q(dVar, "EnvelopeCache is required.");
        this.f16890n = dVar;
    }

    public static /* synthetic */ void a(b bVar, q qVar, io.sentry.hints.j jVar) {
        bVar.f16891p.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Marking envelope submission result: %s", Boolean.valueOf(qVar.v()));
        jVar.b(qVar.v());
    }

    public final q b() {
        C1489n1 c1489n1 = this.l;
        ((C1459d1) c1489n1.f16624m).o = null;
        io.sentry.cache.d dVar = this.f16890n;
        C1523x c1523x = this.f16889m;
        dVar.g(c1489n1, c1523x);
        Object objG = AbstractC1387d.g(c1523x);
        boolean zIsInstance = io.sentry.hints.c.class.isInstance(AbstractC1387d.g(c1523x));
        c cVar = this.f16891p;
        if (zIsInstance && objG != null) {
            io.sentry.hints.c cVar2 = (io.sentry.hints.c) objG;
            if (cVar2.f(((C1459d1) c1489n1.f16624m).l)) {
                cVar2.l.countDown();
                cVar.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Disk flush envelope fired", new Object[0]);
            } else {
                cVar.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Not firing envelope flush as there's an ongoing transaction", new Object[0]);
            }
        }
        boolean zV = cVar.f16894p.v();
        F1 f12 = cVar.f16893n;
        if (!zV) {
            Object objG2 = AbstractC1387d.g(c1523x);
            if (!io.sentry.hints.g.class.isInstance(AbstractC1387d.g(c1523x)) || objG2 == null) {
                AbstractC1985B.o(io.sentry.hints.g.class, objG2, f12.getLogger());
                f12.getClientReportRecorder().A(io.sentry.clientreport.d.NETWORK_ERROR, c1489n1);
            } else {
                ((io.sentry.hints.g) objG2).e(true);
            }
            return this.o;
        }
        C1489n1 c1489n1F = f12.getClientReportRecorder().f(c1489n1);
        try {
            AbstractC1453b1 abstractC1453b1A = f12.getDateProvider().a();
            ((C1459d1) c1489n1F.f16624m).o = AbstractC1985B.g(Double.valueOf(abstractC1453b1A.d() / 1000000.0d).longValue());
            q qVarD = cVar.f16895q.d(c1489n1F);
            if (qVarD.v()) {
                dVar.p(c1489n1);
                return qVarD;
            }
            String str = "The transport failed to send the envelope with response code " + qVarD.u();
            f12.getLogger().k(EnumC1495p1.ERROR, str, new Object[0]);
            if (qVarD.u() >= 400 && qVarD.u() != 429) {
                Object objG3 = AbstractC1387d.g(c1523x);
                if (!io.sentry.hints.g.class.isInstance(AbstractC1387d.g(c1523x)) || objG3 == null) {
                    f12.getClientReportRecorder().A(io.sentry.clientreport.d.NETWORK_ERROR, c1489n1F);
                }
            }
            throw new IllegalStateException(str);
        } catch (IOException e10) {
            Object objG4 = AbstractC1387d.g(c1523x);
            if (!io.sentry.hints.g.class.isInstance(AbstractC1387d.g(c1523x)) || objG4 == null) {
                AbstractC1985B.o(io.sentry.hints.g.class, objG4, f12.getLogger());
                f12.getClientReportRecorder().A(io.sentry.clientreport.d.NETWORK_ERROR, c1489n1F);
            } else {
                ((io.sentry.hints.g) objG4).e(true);
            }
            throw new IllegalStateException("Sending the event failed.", e10);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f16891p.f16896r = this;
        q qVarB = this.o;
        try {
            qVarB = b();
            this.f16891p.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Envelope flushed", new Object[0]);
        } catch (Throwable th) {
            try {
                this.f16891p.f16893n.getLogger().p(EnumC1495p1.ERROR, th, "Envelope submission failed", new Object[0]);
                throw th;
            } finally {
                C1523x c1523x = this.f16889m;
                Object objG = AbstractC1387d.g(c1523x);
                if (io.sentry.hints.j.class.isInstance(AbstractC1387d.g(c1523x)) && objG != null) {
                    a(this, qVarB, (io.sentry.hints.j) objG);
                }
                this.f16891p.f16896r = null;
            }
        }
    }
}
