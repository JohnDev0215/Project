package d6;

import A7.m;
import S8.AbstractC0727y;
import S8.E;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import j6.n;
import m7.q;
import n6.C1966G;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13471m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f13472n;
    public final /* synthetic */ j o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(j jVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f13472n = str;
        this.o = jVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.o, this.f13472n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objF;
        j jVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f13471m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            j jVar2 = this.o;
            if (m.a(this.f13472n, jVar2.f12250s)) {
                String str = jVar2.f12250s;
                this.l = jVar2;
                this.f13471m = 1;
                if (str == null) {
                    objF = null;
                } else {
                    n nVar = jVar2.l;
                    nVar.getClass();
                    Z8.e eVar = E.f8084a;
                    objF = AbstractC0727y.F(Z8.d.f11010n, new j6.j(nVar, str, null), this);
                }
                if (objF == enumC2303a) {
                    return enumC2303a;
                }
                obj = objF;
                jVar = jVar2;
            }
            return q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        jVar = this.l;
        AbstractC1386c.u(obj);
        jVar.f13478C.setValue((C1966G) obj);
        return q.f19267a;
    }
}
