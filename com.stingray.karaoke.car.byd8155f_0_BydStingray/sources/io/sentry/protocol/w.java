package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import io.sentry.S1;
import io.sentry.T1;
import io.sentry.V1;
import io.sentry.W1;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class w implements InterfaceC1485m0 {
    public final Double l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Double f16779m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t f16780n;
    public final V1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final V1 f16781p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f16782q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f16783r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final W1 f16784s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final String f16785t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Map f16786u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Map f16787v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Map f16788w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Map f16789x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ConcurrentHashMap f16790y;

    public w(Double d, Double d10, t tVar, V1 v12, V1 v13, String str, String str2, W1 w1, String str3, Map map, Map map2, Map map3, Map map4) {
        this.l = d;
        this.f16779m = d10;
        this.f16780n = tVar;
        this.o = v12;
        this.f16781p = v13;
        this.f16782q = str;
        this.f16783r = str2;
        this.f16784s = w1;
        this.f16785t = str3;
        this.f16786u = map;
        this.f16788w = map2;
        this.f16789x = map3;
        this.f16787v = map4;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("start_timestamp");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(this.l.doubleValue());
        RoundingMode roundingMode = RoundingMode.DOWN;
        c1489n1.I(k10, bigDecimalValueOf.setScale(6, roundingMode));
        Double d = this.f16779m;
        if (d != null) {
            c1489n1.w("timestamp");
            c1489n1.I(k10, BigDecimal.valueOf(d.doubleValue()).setScale(6, roundingMode));
        }
        c1489n1.w("trace_id");
        c1489n1.I(k10, this.f16780n);
        c1489n1.w("span_id");
        c1489n1.I(k10, this.o);
        V1 v12 = this.f16781p;
        if (v12 != null) {
            c1489n1.w("parent_span_id");
            c1489n1.I(k10, v12);
        }
        c1489n1.w("op");
        c1489n1.L(this.f16782q);
        String str = this.f16783r;
        if (str != null) {
            c1489n1.w("description");
            c1489n1.L(str);
        }
        W1 w1 = this.f16784s;
        if (w1 != null) {
            c1489n1.w("status");
            c1489n1.I(k10, w1);
        }
        String str2 = this.f16785t;
        if (str2 != null) {
            c1489n1.w("origin");
            c1489n1.I(k10, str2);
        }
        Map map = this.f16786u;
        if (!map.isEmpty()) {
            c1489n1.w("tags");
            c1489n1.I(k10, map);
        }
        if (this.f16787v != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16787v);
        }
        Map map2 = this.f16788w;
        if (!map2.isEmpty()) {
            c1489n1.w("measurements");
            c1489n1.I(k10, map2);
        }
        Map map3 = this.f16789x;
        if (map3 != null && !map3.isEmpty()) {
            c1489n1.w("_metrics_summary");
            c1489n1.I(k10, map3);
        }
        ConcurrentHashMap concurrentHashMap = this.f16790y;
        if (concurrentHashMap != null) {
            for (String str3 : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16790y, str3, c1489n1, str3, k10);
            }
        }
        c1489n1.n();
    }

    public w(S1 s12) {
        ConcurrentHashMap concurrentHashMap = s12.f15951k;
        T1 t12 = s12.c;
        this.f16783r = t12.f15960q;
        this.f16782q = t12.f15959p;
        this.o = t12.f15957m;
        this.f16781p = t12.f15958n;
        this.f16780n = t12.l;
        this.f16784s = t12.f15961r;
        this.f16785t = t12.f15963t;
        ConcurrentHashMap concurrentHashMapP = h6.r.p(t12.f15962s);
        this.f16786u = concurrentHashMapP == null ? new ConcurrentHashMap() : concurrentHashMapP;
        ConcurrentHashMap concurrentHashMapP2 = h6.r.p(s12.l);
        this.f16788w = concurrentHashMapP2 == null ? new ConcurrentHashMap() : concurrentHashMapP2;
        this.f16779m = s12.f15944b == null ? null : Double.valueOf(s12.f15943a.c(r1) / 1.0E9d);
        this.l = Double.valueOf(s12.f15943a.d() / 1.0E9d);
        this.f16787v = concurrentHashMap;
        io.sentry.metrics.b bVar = (io.sentry.metrics.b) s12.f15952m.a();
        if (bVar != null) {
            this.f16789x = bVar.a();
        } else {
            this.f16789x = null;
        }
    }
}
