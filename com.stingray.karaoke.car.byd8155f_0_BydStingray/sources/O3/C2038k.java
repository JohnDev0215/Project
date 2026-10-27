package o3;

import S8.InterfaceC0725w;
import T.X;
import V8.f0;
import e0.C1178r;
import h8.AbstractC1386c;
import java.util.List;
import java.util.Set;
import n3.C1945j;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: o3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2038k extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ X l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2042o f20472m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1178r f20473n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2038k(X x8, C2042o c2042o, C1178r c1178r, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = x8;
        this.f20472m = c2042o;
        this.f20473n = c1178r;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2038k(this.l, this.f20472m, this.f20473n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C2038k c2038k = (C2038k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c2038k.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        for (C1945j c1945j : (Set) this.l.getValue()) {
            C2042o c2042o = this.f20472m;
            if (!((List) ((f0) c2042o.b().f19880e.l).getValue()).contains(c1945j) && !this.f20473n.contains(c1945j)) {
                c2042o.b().b(c1945j);
            }
        }
        return m7.q.f19267a;
    }
}
