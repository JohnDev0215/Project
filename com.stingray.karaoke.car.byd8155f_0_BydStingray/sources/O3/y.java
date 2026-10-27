package o3;

import S8.InterfaceC0725w;
import T.C0738e0;
import T.Q0;
import h8.AbstractC1386c;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n3.C1930E;
import n3.C1945j;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.x0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class y extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ x0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1930E f20514m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Map f20515n;
    public final /* synthetic */ Q0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2036i f20516p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(x0 x0Var, C1930E c1930e, Map map, Q0 q02, C2036i c2036i, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = x0Var;
        this.f20514m = c1930e;
        this.f20515n = map;
        this.o = q02;
        this.f20516p = c2036i;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new y(this.l, this.f20514m, this.f20515n, this.o, this.f20516p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        y yVar = (y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        yVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        x0 x0Var = this.l;
        Object objP0 = x0Var.f23661a.P0();
        C0738e0 c0738e0 = x0Var.d;
        if (A7.m.a(objP0, c0738e0.getValue())) {
            C1930E c1930e = this.f20514m;
            if (c1930e.h() == null || A7.m.a(c0738e0.getValue(), c1930e.h())) {
                Iterator it = ((List) this.o.getValue()).iterator();
                while (it.hasNext()) {
                    this.f20516p.b().b((C1945j) it.next());
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Map map = this.f20515n;
                for (Map.Entry entry : map.entrySet()) {
                    if (!A7.m.a(entry.getKey(), ((C1945j) c0738e0.getValue()).f19868q)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it2 = linkedHashMap.entrySet().iterator();
                while (it2.hasNext()) {
                    map.remove(((Map.Entry) it2.next()).getKey());
                }
            }
        }
        return m7.q.f19267a;
    }
}
