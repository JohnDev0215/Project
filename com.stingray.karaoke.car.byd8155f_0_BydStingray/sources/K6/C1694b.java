package k6;

import N1.C0420a;
import N1.InterfaceC0428i;
import S8.InterfaceC0725w;
import V8.InterfaceC0817f;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: k6.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1694b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1696d f18242m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1694b(C1696d c1696d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f18242m = c1696d;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1694b(this.f18242m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1694b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        C1696d c1696d = this.f18242m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            K5.t tVar = c1696d.c;
            InterfaceC0817f interfaceC0817fD = ((InterfaceC0428i) ((C0420a) tVar.a(tVar.f3657a)).l).d();
            C1693a c1693a = new C1693a(c1696d, 0);
            this.l = 1;
            Object objB = interfaceC0817fD.b(new A3.m(c1693a, 4), this);
            if (objB != enumC2303a) {
                objB = qVar;
            }
            if (objB == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 == 2) {
                    AbstractC1386c.u(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        K5.t tVar2 = c1696d.c;
        InterfaceC0817f interfaceC0817fD2 = ((InterfaceC0428i) ((C0420a) tVar2.a(tVar2.f3657a)).l).d();
        C1693a c1693a2 = new C1693a(c1696d, 1);
        this.l = 2;
        Object objB2 = interfaceC0817fD2.b(new A3.m(c1693a2, 2), this);
        if (objB2 != enumC2303a) {
            objB2 = qVar;
        }
        return objB2 == enumC2303a ? enumC2303a : qVar;
    }
}
