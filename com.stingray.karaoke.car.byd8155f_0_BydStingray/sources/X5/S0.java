package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import g6.C1307e;
import g6.C1310h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V0 f10199m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10200n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S0(V0 v02, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10199m = v02;
        this.f10200n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new S0(this.f10199m, this.f10200n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((S0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        V0 v02 = this.f10199m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                C1310h c1310h = v02.c;
                Object value = ((V8.f0) v02.f10206a.f4637P.l).getValue();
                A7.m.c(value);
                String str = ((N5.f0) value).f4691a.f13136a;
                A7.m.c(str);
                this.l = 1;
                c1310h.getClass();
                Z8.e eVar = S8.E.f8084a;
                obj = AbstractC0727y.F(Z8.d.f11010n, new C1307e(c1310h, str, null), this);
                if (obj == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            t6.h hVar = (t6.h) obj;
            v02.f10212i = null;
            v02.f10210g.l(hVar);
            v02.f10206a.f4623A = Math.min(hVar.f22492a, hVar.f22493b);
        } catch (Exception unused) {
            v02.b(this.f10200n);
        }
        return m7.q.f19267a;
    }
}
