package io.sentry.okhttp;

import B3.m;
import c9.H;
import c9.w;
import c9.x;
import g1.AbstractC1274a;
import h6.f;
import h8.AbstractC1387d;
import io.sentry.C;
import io.sentry.C1457d;
import io.sentry.C1489n1;
import io.sentry.C1523x;
import io.sentry.E;
import io.sentry.F1;
import io.sentry.J;
import java.util.Iterator;
import java.util.List;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class e implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J f16641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f16642b;
    public final List c;
    public final List d;

    public e() {
        E e10 = E.f15834a;
        List listJ = AbstractC1387d.j(new C());
        List listJ2 = AbstractC1387d.j(F1.DEFAULT_PROPAGATION_TARGETS);
        this.f16641a = e10;
        this.f16642b = true;
        this.c = listJ;
        this.d = listJ2;
        z.c("OkHttp");
        C1489n1.s().g("maven:io.sentry:sentry-okhttp");
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x014b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // c9.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final c9.H a(F.u r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.okhttp.e.a(F.u):c9.H");
    }

    public final void b(H0.b bVar, Integer num, H h10, long j3) {
        C1457d c1457dB = C1457d.b(((w) bVar.f2765m).f12624h, (String) bVar.f2766n);
        if (num != null) {
            c1457dB.c(num, "status_code");
        }
        AbstractC1274a abstractC1274a = (AbstractC1274a) bVar.f2767p;
        Long lValueOf = abstractC1274a != null ? Long.valueOf(abstractC1274a.B()) : null;
        if (lValueOf != null && lValueOf.longValue() != -1) {
            c1457dB.c(Long.valueOf(lValueOf.longValue()), "http.request_content_length");
        }
        C1523x c1523x = new C1523x();
        c1523x.c(bVar, "okHttp:request");
        if (h10 != null) {
            m mVar = h10.f12518r;
            Long lValueOf2 = mVar != null ? Long.valueOf(mVar.i()) : null;
            if (lValueOf2 != null && lValueOf2.longValue() != -1) {
                c1457dB.c(Long.valueOf(lValueOf2.longValue()), "http.response_content_length");
            }
            c1523x.c(h10, "okHttp:response");
        }
        c1457dB.c(Long.valueOf(j3), "http.start_timestamp");
        c1457dB.c(Long.valueOf(System.currentTimeMillis()), "http.end_timestamp");
        this.f16641a.k(c1457dB, c1523x);
    }

    public final boolean c(H0.b bVar, H h10) {
        if (this.f16642b) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((C) it.next()).getClass();
                int i7 = h10.o;
                if (i7 >= 500 && i7 <= 599) {
                    return f.e(((w) bVar.f2765m).f12624h, this.d);
                }
            }
        }
        return false;
    }
}
