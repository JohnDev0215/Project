package k6;

import F0.k1;
import N1.C0420a;
import N1.InterfaceC0428i;
import S8.InterfaceC0725w;
import V8.InterfaceC0817f;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: k6.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1697e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1699g f18260m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1697e(C1699g c1699g, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f18260m = c1699g;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1697e(this.f18260m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1697e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C1699g c1699g = this.f18260m;
            K5.t tVar = c1699g.f18264b;
            InterfaceC0817f interfaceC0817fD = ((InterfaceC0428i) ((C0420a) tVar.a(tVar.f3657a)).l).d();
            k1 k1Var = new k1(c1699g, 10);
            this.l = 1;
            Object objB = interfaceC0817fD.b(new A3.m(k1Var, 3), this);
            if (objB != enumC2303a) {
                objB = qVar;
            }
            if (objB == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
