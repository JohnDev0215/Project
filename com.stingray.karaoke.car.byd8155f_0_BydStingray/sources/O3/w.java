package o3;

import T.Q0;
import T.X;
import java.util.List;
import java.util.Map;
import n3.C1945j;
import w.C2573d;
import w.C2579j;
import w.F;
import w.G;
import w.M;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class w extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Map f20505m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2036i f20506n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20507p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20508q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Q0 f20509r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ X f20510s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(Map map, C2036i c2036i, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, InterfaceC2782k interfaceC2782k3, Q0 q02, X x8) {
        super(1);
        this.f20505m = map;
        this.f20506n = c2036i;
        this.o = interfaceC2782k;
        this.f20507p = interfaceC2782k2;
        this.f20508q = interfaceC2782k3;
        this.f20509r = q02;
        this.f20510s = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C2579j c2579j = (C2579j) obj;
        float fFloatValue = 0.0f;
        if (!((List) this.f20509r.getValue()).contains(c2579j.a())) {
            return new w.u(F.f23066b, G.f23068b, 0.0f, new M(true, C2573d.f23096n));
        }
        String str = ((C1945j) c2579j.a()).f19868q;
        Map map = this.f20505m;
        Float f10 = (Float) map.get(str);
        if (f10 != null) {
            fFloatValue = f10.floatValue();
        } else {
            map.put(((C1945j) c2579j.a()).f19868q, Float.valueOf(0.0f));
        }
        if (!A7.m.a(((C1945j) c2579j.c()).f19868q, ((C1945j) c2579j.a()).f19868q)) {
            fFloatValue = (((Boolean) this.f20506n.c.getValue()).booleanValue() || ((Boolean) this.f20510s.getValue()).booleanValue()) ? fFloatValue - 1.0f : fFloatValue + 1.0f;
        }
        map.put(((C1945j) c2579j.c()).f19868q, Float.valueOf(fFloatValue));
        return new w.u((F) this.o.b(c2579j), (G) this.f20507p.b(c2579j), fFloatValue, (M) this.f20508q.b(c2579j));
    }
}
