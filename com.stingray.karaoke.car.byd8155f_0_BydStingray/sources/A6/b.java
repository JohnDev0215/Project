package A6;

import S8.InterfaceC0725w;
import V8.f0;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import m7.q;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b extends i implements InterfaceC2785n {
    public final /* synthetic */ c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f434m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ HashMap f435n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, String str, HashMap map, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = cVar;
        this.f434m = str;
        this.f435n = map;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new b(this.l, this.f434m, this.f435n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        b bVar = (b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        bVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object value;
        ArrayList arrayListA0;
        f0 f0Var;
        Object value2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        c cVar = this.l;
        f0 f0Var2 = cVar.f437b;
        do {
            value = f0Var2.getValue();
            arrayListA0 = AbstractC1998m.A0((List) value);
            arrayListA0.add(this.f434m);
        } while (!f0Var2.k(value, AbstractC1998m.w0(10, arrayListA0)));
        do {
            f0Var = cVar.d;
            value2 = f0Var.getValue();
        } while (!f0Var.k(value2, this.f435n));
        return q.f19267a;
    }
}
