package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import io.sentry.P1;
import io.sentry.S1;
import io.sentry.T1;
import io.sentry.X0;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class A extends X0 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f16647A;
    public Double B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Double f16648C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final ArrayList f16649D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final HashMap f16650E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Map f16651F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public B f16652G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ConcurrentHashMap f16653H;

    public A(ArrayList arrayList, HashMap map, B b10) {
        Double dValueOf = Double.valueOf(0.0d);
        ArrayList arrayList2 = new ArrayList();
        this.f16649D = arrayList2;
        HashMap map2 = new HashMap();
        this.f16650E = map2;
        this.f16647A = "";
        this.B = dValueOf;
        this.f16648C = null;
        arrayList2.addAll(arrayList);
        map2.putAll(map);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f16650E.putAll(((w) it.next()).f16788w);
        }
        this.f16652G = b10;
        this.f16651F = null;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.f16647A != null) {
            c1489n1.w("transaction");
            c1489n1.L(this.f16647A);
        }
        c1489n1.w("start_timestamp");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(this.B.doubleValue());
        RoundingMode roundingMode = RoundingMode.DOWN;
        c1489n1.I(k10, bigDecimalValueOf.setScale(6, roundingMode));
        if (this.f16648C != null) {
            c1489n1.w("timestamp");
            c1489n1.I(k10, BigDecimal.valueOf(this.f16648C.doubleValue()).setScale(6, roundingMode));
        }
        ArrayList arrayList = this.f16649D;
        if (!arrayList.isEmpty()) {
            c1489n1.w("spans");
            c1489n1.I(k10, arrayList);
        }
        c1489n1.w("type");
        c1489n1.L("transaction");
        HashMap map = this.f16650E;
        if (!map.isEmpty()) {
            c1489n1.w("measurements");
            c1489n1.I(k10, map);
        }
        Map map2 = this.f16651F;
        if (map2 != null && !map2.isEmpty()) {
            c1489n1.w("_metrics_summary");
            c1489n1.I(k10, this.f16651F);
        }
        c1489n1.w("transaction_info");
        c1489n1.I(k10, this.f16652G);
        h6.f.t(this, c1489n1, k10);
        ConcurrentHashMap concurrentHashMap = this.f16653H;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16653H, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }

    public A(P1 p12) {
        super(p12.f15910a);
        this.f16649D = new ArrayList();
        this.f16650E = new HashMap();
        S1 s12 = p12.f15911b;
        this.B = Double.valueOf(s12.f15943a.d() / 1.0E9d);
        this.f16648C = Double.valueOf(s12.f15943a.c(s12.f15944b) / 1.0E9d);
        this.f16647A = p12.f15912e;
        Iterator it = p12.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            S1 s13 = (S1) it.next();
            Boolean bool = Boolean.TRUE;
            B8.t tVar = s13.c.o;
            if (bool.equals(tVar != null ? (Boolean) tVar.f808n : null)) {
                this.f16649D.add(new w(s13));
            }
        }
        C1498c c1498c = this.f15978m;
        c1498c.putAll(p12.f15921p);
        T1 t12 = s12.c;
        c1498c.e(new T1(t12.l, t12.f15957m, t12.f15958n, t12.f15959p, t12.f15960q, t12.o, t12.f15961r, t12.f15963t));
        for (Map.Entry entry : t12.f15962s.entrySet()) {
            a((String) entry.getKey(), (String) entry.getValue());
        }
        ConcurrentHashMap concurrentHashMap = s12.f15951k;
        if (concurrentHashMap != null) {
            for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                String str = (String) entry2.getKey();
                Object value = entry2.getValue();
                if (this.f15990z == null) {
                    this.f15990z = new HashMap();
                }
                this.f15990z.put(str, value);
            }
        }
        this.f16652G = new B(p12.f15920n.apiName());
        io.sentry.metrics.b bVar = (io.sentry.metrics.b) s12.f15952m.a();
        if (bVar != null) {
            this.f16651F = bVar.a();
        } else {
            this.f16651F = null;
        }
    }
}
