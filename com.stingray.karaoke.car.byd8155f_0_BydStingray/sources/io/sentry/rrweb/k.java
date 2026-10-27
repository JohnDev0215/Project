package io.sentry.rrweb;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k extends b implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16854n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16855p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public double f16856q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public double f16857r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f16858s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public HashMap f16859t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f16860u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f16861v;

    public k() {
        super(c.Custom);
        this.f16854n = "performanceSpan";
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.I(k10, this.l);
        c1489n1.w("timestamp");
        c1489n1.H(this.f16836m);
        c1489n1.w("data");
        c1489n1.i();
        c1489n1.w("tag");
        c1489n1.L(this.f16854n);
        c1489n1.w("payload");
        c1489n1.i();
        if (this.o != null) {
            c1489n1.w("op");
            c1489n1.L(this.o);
        }
        if (this.f16855p != null) {
            c1489n1.w("description");
            c1489n1.L(this.f16855p);
        }
        c1489n1.w("startTimestamp");
        c1489n1.I(k10, BigDecimal.valueOf(this.f16856q));
        c1489n1.w("endTimestamp");
        c1489n1.I(k10, BigDecimal.valueOf(this.f16857r));
        if (this.f16858s != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16858s);
        }
        ConcurrentHashMap concurrentHashMap = this.f16860u;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16860u, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        ConcurrentHashMap concurrentHashMap2 = this.f16861v;
        if (concurrentHashMap2 != null) {
            for (String str2 : concurrentHashMap2.keySet()) {
                AbstractC1347d.t(this.f16861v, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
        HashMap map = this.f16859t;
        if (map != null) {
            for (String str3 : map.keySet()) {
                AbstractC1347d.s(this.f16859t, str3, c1489n1, str3, k10);
            }
        }
        c1489n1.n();
    }
}
