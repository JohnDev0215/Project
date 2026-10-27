package io.sentry.protocol;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import io.sentry.T1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.protocol.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1498c extends ConcurrentHashMap implements InterfaceC1485m0 {
    public final Object l = new Object();

    public C1498c() {
    }

    public final T1 b() {
        return (T1) f(T1.class, "trace");
    }

    public final void c(C1496a c1496a) {
        put("app", c1496a);
    }

    public final void d(p pVar) {
        synchronized (this.l) {
            put("response", pVar);
        }
    }

    public final void e(T1 t12) {
        h6.e.q(t12, "traceContext is required");
        put("trace", t12);
    }

    public final Object f(Class cls, String str) {
        Object obj = get(str);
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        ArrayList<String> list = Collections.list(keys());
        Collections.sort(list);
        for (String str : list) {
            Object obj = get(str);
            if (obj != null) {
                c1489n1.w(str);
                c1489n1.I(k10, obj);
            }
        }
        c1489n1.n();
    }

    public C1498c(C1498c c1498c) {
        Iterator it = c1498c.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry != null) {
                Object value = entry.getValue();
                if ("app".equals(entry.getKey()) && (value instanceof C1496a)) {
                    C1496a c1496a = (C1496a) value;
                    C1496a c1496a2 = new C1496a();
                    c1496a2.f16679r = c1496a.f16679r;
                    c1496a2.l = c1496a.l;
                    c1496a2.f16677p = c1496a.f16677p;
                    c1496a2.f16675m = c1496a.f16675m;
                    c1496a2.f16678q = c1496a.f16678q;
                    c1496a2.o = c1496a.o;
                    c1496a2.f16676n = c1496a.f16676n;
                    c1496a2.f16680s = h6.r.p(c1496a.f16680s);
                    c1496a2.f16683v = c1496a.f16683v;
                    List list = c1496a.f16681t;
                    c1496a2.f16681t = list != null ? new ArrayList(list) : null;
                    c1496a2.f16682u = c1496a.f16682u;
                    c1496a2.f16684w = h6.r.p(c1496a.f16684w);
                    c(c1496a2);
                } else if ("browser".equals(entry.getKey()) && (value instanceof C1497b)) {
                    C1497b c1497b = (C1497b) value;
                    C1497b c1497b2 = new C1497b();
                    c1497b2.l = c1497b.l;
                    c1497b2.f16685m = c1497b.f16685m;
                    c1497b2.f16686n = h6.r.p(c1497b.f16686n);
                    put("browser", c1497b2);
                } else if ("device".equals(entry.getKey()) && (value instanceof C1501f)) {
                    C1501f c1501f = (C1501f) value;
                    C1501f c1501f2 = new C1501f();
                    c1501f2.l = c1501f.l;
                    c1501f2.f16708m = c1501f.f16708m;
                    c1501f2.f16709n = c1501f.f16709n;
                    c1501f2.o = c1501f.o;
                    c1501f2.f16710p = c1501f.f16710p;
                    c1501f2.f16711q = c1501f.f16711q;
                    c1501f2.f16714t = c1501f.f16714t;
                    c1501f2.f16715u = c1501f.f16715u;
                    c1501f2.f16716v = c1501f.f16716v;
                    c1501f2.f16717w = c1501f.f16717w;
                    c1501f2.f16718x = c1501f.f16718x;
                    c1501f2.f16719y = c1501f.f16719y;
                    c1501f2.f16720z = c1501f.f16720z;
                    c1501f2.f16689A = c1501f.f16689A;
                    c1501f2.B = c1501f.B;
                    c1501f2.f16690C = c1501f.f16690C;
                    c1501f2.f16691D = c1501f.f16691D;
                    c1501f2.f16692E = c1501f.f16692E;
                    c1501f2.f16693F = c1501f.f16693F;
                    c1501f2.f16694G = c1501f.f16694G;
                    c1501f2.f16695H = c1501f.f16695H;
                    c1501f2.f16696I = c1501f.f16696I;
                    c1501f2.f16697J = c1501f.f16697J;
                    c1501f2.f16699L = c1501f.f16699L;
                    c1501f2.f16700M = c1501f.f16700M;
                    c1501f2.f16702O = c1501f.f16702O;
                    c1501f2.f16703P = c1501f.f16703P;
                    c1501f2.f16713s = c1501f.f16713s;
                    String[] strArr = c1501f.f16712r;
                    c1501f2.f16712r = strArr != null ? (String[]) strArr.clone() : null;
                    c1501f2.f16701N = c1501f.f16701N;
                    TimeZone timeZone = c1501f.f16698K;
                    c1501f2.f16698K = timeZone != null ? (TimeZone) timeZone.clone() : null;
                    c1501f2.f16704Q = c1501f.f16704Q;
                    c1501f2.f16705R = c1501f.f16705R;
                    c1501f2.f16706S = c1501f.f16706S;
                    c1501f2.f16707T = h6.r.p(c1501f.f16707T);
                    put("device", c1501f2);
                } else if ("os".equals(entry.getKey()) && (value instanceof m)) {
                    m mVar = (m) value;
                    m mVar2 = new m();
                    mVar2.l = mVar.l;
                    mVar2.f16744m = mVar.f16744m;
                    mVar2.f16745n = mVar.f16745n;
                    mVar2.o = mVar.o;
                    mVar2.f16746p = mVar.f16746p;
                    mVar2.f16747q = mVar.f16747q;
                    mVar2.f16748r = h6.r.p(mVar.f16748r);
                    put("os", mVar2);
                } else if ("runtime".equals(entry.getKey()) && (value instanceof v)) {
                    v vVar = (v) value;
                    v vVar2 = new v();
                    vVar2.l = vVar.l;
                    vVar2.f16777m = vVar.f16777m;
                    vVar2.f16778n = vVar.f16778n;
                    vVar2.o = h6.r.p(vVar.o);
                    put("runtime", vVar2);
                } else if ("gpu".equals(entry.getKey()) && (value instanceof h)) {
                    h hVar = (h) value;
                    h hVar2 = new h();
                    hVar2.l = hVar.l;
                    hVar2.f16723m = hVar.f16723m;
                    hVar2.f16724n = hVar.f16724n;
                    hVar2.o = hVar.o;
                    hVar2.f16725p = hVar.f16725p;
                    hVar2.f16726q = hVar.f16726q;
                    hVar2.f16727r = hVar.f16727r;
                    hVar2.f16728s = hVar.f16728s;
                    hVar2.f16729t = hVar.f16729t;
                    hVar2.f16730u = h6.r.p(hVar.f16730u);
                    put("gpu", hVar2);
                } else if ("trace".equals(entry.getKey()) && (value instanceof T1)) {
                    e(new T1((T1) value));
                } else if ("response".equals(entry.getKey()) && (value instanceof p)) {
                    p pVar = (p) value;
                    p pVar2 = new p();
                    pVar2.l = pVar.l;
                    pVar2.f16759m = h6.r.p(pVar.f16759m);
                    pVar2.f16762q = h6.r.p(pVar.f16762q);
                    pVar2.f16760n = pVar.f16760n;
                    pVar2.o = pVar.o;
                    pVar2.f16761p = pVar.f16761p;
                    d(pVar2);
                } else {
                    put((String) entry.getKey(), value);
                }
            }
        }
    }
}
