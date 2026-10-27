package P5;

import E.D;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5398m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f5399n;
    public final /* synthetic */ E.u o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(p pVar, Object obj, E.u uVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5398m = pVar;
        this.f5399n = obj;
        this.o = uVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new t(this.f5398m, this.f5399n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            p pVar = this.f5398m;
            InterfaceC2785n interfaceC2785n = pVar.c;
            if (interfaceC2785n != null) {
                Object obj2 = this.f5399n;
                g gVar = new g(pVar.k(obj2), pVar.l(obj2));
                E.u uVar = this.o;
                interfaceC2785n.invoke(gVar, new g(pVar.k(uVar), pVar.l(uVar)));
            }
            D d = pVar.o;
            int i10 = d.d.f1443b.i();
            int i11 = d.d.c.i();
            this.l = 1;
            Object objJ = d.j(i10, i11, this);
            if (objJ != enumC2303a) {
                objJ = qVar;
            }
            if (objJ == enumC2303a) {
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
