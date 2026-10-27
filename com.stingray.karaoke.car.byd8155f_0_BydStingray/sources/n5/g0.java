package N5;

import h8.AbstractC1386c;
import java.util.ArrayList;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q0 f4695m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(q0 q0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4695m = q0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        g0 g0Var = new g0(this.f4695m, interfaceC2189c);
        g0Var.l = ((Boolean) obj).booleanValue();
        return g0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        g0 g0Var = (g0) create(bool, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        g0Var.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (this.l) {
            q0 q0Var = this.f4695m;
            synchronized (q0Var) {
                try {
                    ArrayList arrayList = q0Var.f4740h;
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        if (A7.m.a(((f0) obj2).f4691a.f13143j, Boolean.TRUE)) {
                            arrayList2.add(obj2);
                        }
                    }
                    q0Var.j(arrayList2);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return m7.q.f19267a;
    }
}
