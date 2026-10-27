package io.sentry.okhttp;

import A7.m;
import K.C0330b;
import Q5.C0558d0;
import S0.p;
import T.C0741g;
import c9.D;
import c9.H;
import c9.q;
import c9.t;
import c9.w;
import g9.j;
import io.sentry.AbstractC1453b1;
import io.sentry.C1457d;
import io.sentry.EnumC1495p1;
import io.sentry.U;
import io.sentry.U0;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class d extends q {
    public static final ConcurrentHashMap d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f16640b;
    public q c;

    public d(G2.a aVar) {
        m.f(aVar, "originalEventListenerFactory");
        this.f16640b = new p(aVar, 23);
    }

    @Override // c9.q
    public final void A(j jVar, H h10) {
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.A(jVar, h10);
        }
    }

    @Override // c9.q
    public final void B(j jVar, t tVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.B(jVar, tVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("secure_connect", null);
        }
    }

    @Override // c9.q
    public final void C(j jVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.C(jVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("secure_connect");
        }
    }

    public final boolean D() {
        q qVar = this.c;
        if (!(qVar instanceof d)) {
            if (!"io.sentry.android.okhttp.SentryOkHttpEventListener".equals(qVar != null ? qVar.getClass().getName() : null)) {
                return true;
            }
        }
        return false;
    }

    @Override // c9.q
    public final void a(j jVar, H h10) {
        m.f(jVar, "call");
        m.f(h10, "cachedResponse");
        q qVar = this.c;
        if (qVar != null) {
            qVar.a(jVar, h10);
        }
    }

    @Override // c9.q
    public final void b(j jVar, H h10) {
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.b(jVar, h10);
        }
    }

    @Override // c9.q
    public final void c(j jVar) {
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.c(jVar);
        }
    }

    @Override // c9.q
    public final void d(j jVar) {
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.d(jVar);
        }
        a aVar = (a) d.remove(jVar);
        if (aVar == null) {
            return;
        }
        a.b(aVar, null, null, 3);
    }

    @Override // c9.q
    public final void e(j jVar, IOException iOException) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.e(jVar, iOException);
        }
        if (D() && (aVar = (a) d.remove(jVar)) != null) {
            aVar.e(iOException.getMessage());
            a.b(aVar, null, new b(0, iOException), 1);
        }
    }

    @Override // c9.q
    public final void f(j jVar) {
        m.f(jVar, "call");
        q qVar = (q) this.f16640b.b(jVar);
        this.c = qVar;
        qVar.f(jVar);
        if (D()) {
            d.put(jVar, new a(jVar.f15060m));
        }
    }

    @Override // c9.q
    public final void g(j jVar) {
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.g(jVar);
        }
    }

    @Override // c9.q
    public final void h(j jVar, InetSocketAddress inetSocketAddress, Proxy proxy, D d10) {
        a aVar;
        m.f(jVar, "call");
        m.f(inetSocketAddress, "inetSocketAddress");
        q qVar = this.c;
        if (qVar != null) {
            qVar.h(jVar, inetSocketAddress, proxy, d10);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            String strName = d10 != null ? d10.name() : null;
            if (strName != null) {
                aVar.c.c(strName, "protocol");
                U u4 = aVar.d;
                if (u4 != null) {
                    u4.A(strName, "protocol");
                }
            }
            aVar.c("connect", null);
        }
    }

    @Override // c9.q
    public final void i(j jVar, InetSocketAddress inetSocketAddress, Proxy proxy, IOException iOException) {
        a aVar;
        m.f(jVar, "call");
        m.f(inetSocketAddress, "inetSocketAddress");
        q qVar = this.c;
        if (qVar != null) {
            qVar.i(jVar, inetSocketAddress, proxy, iOException);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.e(iOException.getMessage());
            aVar.c("connect", new b(1, iOException));
        }
    }

    @Override // c9.q
    public final void j(j jVar, InetSocketAddress inetSocketAddress, Proxy proxy) {
        a aVar;
        m.f(jVar, "call");
        m.f(inetSocketAddress, "inetSocketAddress");
        q qVar = this.c;
        if (qVar != null) {
            qVar.j(jVar, inetSocketAddress, proxy);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("connect");
        }
    }

    @Override // c9.q
    public final void k(j jVar, g9.m mVar) {
        a aVar;
        m.f(jVar, "call");
        m.f(mVar, "connection");
        q qVar = this.c;
        if (qVar != null) {
            qVar.k(jVar, mVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("connection");
        }
    }

    @Override // c9.q
    public final void l(j jVar, g9.m mVar) {
        a aVar;
        m.f(jVar, "call");
        m.f(mVar, "connection");
        q qVar = this.c;
        if (qVar != null) {
            qVar.l(jVar, mVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("connection", null);
        }
    }

    @Override // c9.q
    public final void m(j jVar, String str, List list) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.m(jVar, str, list);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("dns", new C0741g(str, 17, list));
        }
    }

    @Override // c9.q
    public final void n(j jVar, String str) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.n(jVar, str);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("dns");
        }
    }

    @Override // c9.q
    public final void o(j jVar, w wVar, List list) {
        a aVar;
        m.f(jVar, "call");
        m.f(wVar, "url");
        q qVar = this.c;
        if (qVar != null) {
            qVar.o(jVar, wVar, list);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("proxy_select", new C0558d0(7, list));
        }
    }

    @Override // c9.q
    public final void p(j jVar, w wVar) {
        a aVar;
        m.f(jVar, "call");
        m.f(wVar, "url");
        q qVar = this.c;
        if (qVar != null) {
            qVar.p(jVar, wVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("proxy_select");
        }
    }

    @Override // c9.q
    public final void q(j jVar, long j3) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.q(jVar, j3);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("request_body", new C0330b(j3, 2));
            if (j3 > -1) {
                aVar.c.c(Long.valueOf(j3), "request_content_length");
                U u4 = aVar.d;
                if (u4 != null) {
                    u4.A(Long.valueOf(j3), "http.request_content_length");
                }
            }
        }
    }

    @Override // c9.q
    public final void r(j jVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.r(jVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("request_body");
        }
    }

    @Override // c9.q
    public final void s(j jVar, IOException iOException) {
        a aVar;
        m.f(jVar, "call");
        m.f(iOException, "ioe");
        q qVar = this.c;
        if (qVar != null) {
            qVar.s(jVar, iOException);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.e(iOException.getMessage());
            aVar.c("request_headers", new b(2, iOException));
            aVar.c("request_body", new b(3, iOException));
        }
    }

    @Override // c9.q
    public final void t(j jVar, H0.b bVar) {
        a aVar;
        m.f(jVar, "call");
        m.f(bVar, "request");
        q qVar = this.c;
        if (qVar != null) {
            qVar.t(jVar, bVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.c("request_headers", null);
        }
    }

    @Override // c9.q
    public final void u(j jVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.u(jVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("request_headers");
        }
    }

    @Override // c9.q
    public final void v(j jVar, long j3) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.v(jVar, j3);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            if (j3 > -1) {
                aVar.c.c(Long.valueOf(j3), "response_content_length");
                U u4 = aVar.d;
                if (u4 != null) {
                    u4.A(Long.valueOf(j3), "http.response_content_length");
                }
            }
            aVar.c("response_body", new C0330b(j3, 3));
        }
    }

    @Override // c9.q
    public final void w(j jVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.w(jVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("response_body");
        }
    }

    @Override // c9.q
    public final void x(j jVar, IOException iOException) {
        a aVar;
        m.f(jVar, "call");
        m.f(iOException, "ioe");
        q qVar = this.c;
        if (qVar != null) {
            qVar.x(jVar, iOException);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.e(iOException.getMessage());
            aVar.c("response_headers", new b(4, iOException));
            aVar.c("response_body", new b(5, iOException));
        }
    }

    @Override // c9.q
    public final void y(j jVar, H h10) {
        a aVar;
        AbstractC1453b1 abstractC1453b1A;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.y(jVar, h10);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f16630e = h10;
            D d10 = h10.f12514m;
            String strName = d10.name();
            C1457d c1457d = aVar.c;
            c1457d.c(strName, "protocol");
            int i7 = h10.o;
            c1457d.c(Integer.valueOf(i7), "status_code");
            U u4 = aVar.d;
            if (u4 != null) {
                u4.A(d10.name(), "protocol");
            }
            if (u4 != null) {
                u4.A(Integer.valueOf(i7), "http.response.status_code");
            }
            U uC = aVar.c("response_headers", new p(h10, 24));
            if (uC == null || (abstractC1453b1A = uC.t()) == null) {
                abstractC1453b1A = U0.b().q().getDateProvider().a();
            }
            m.e(abstractC1453b1A, "responseHeadersSpan?.fin…ptions.dateProvider.now()");
            try {
                U0.b().q().getExecutorService().r(new A2.m(aVar, 19, abstractC1453b1A), 800L);
            } catch (RejectedExecutionException e10) {
                U0.b().q().getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. OkHttp span will not be finished automatically. Did you call Sentry.close()?", e10);
            }
        }
    }

    @Override // c9.q
    public final void z(j jVar) {
        a aVar;
        m.f(jVar, "call");
        q qVar = this.c;
        if (qVar != null) {
            qVar.z(jVar);
        }
        if (D() && (aVar = (a) d.get(jVar)) != null) {
            aVar.f("response_headers");
        }
    }
}
