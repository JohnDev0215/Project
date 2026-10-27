package N1;

import Q.C0523o0;
import S8.k0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: N1.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0435p extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4550m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0435p(int i7, Object obj, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.l = i7;
        this.f4550m = obj;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws Throwable {
        switch (this.l) {
            case 0:
                int i7 = 0;
                C0435p c0435p = new C0435p(i7, (k0) this.f4550m, (InterfaceC2189c) obj3);
                m7.q qVar = m7.q.f19267a;
                c0435p.invokeSuspend(qVar);
                return qVar;
            case 1:
                ((Number) obj2).floatValue();
                int i10 = 1;
                C0435p c0435p2 = new C0435p(i10, (C0523o0) this.f4550m, (InterfaceC2189c) obj3);
                m7.q qVar2 = m7.q.f19267a;
                c0435p2.invokeSuspend(qVar2);
                return qVar2;
            default:
                int i11 = 2;
                C0435p c0435p3 = new C0435p(i11, (A7.v) this.f4550m, (InterfaceC2189c) obj3);
                m7.q qVar3 = m7.q.f19267a;
                c0435p3.invokeSuspend(qVar3);
                return qVar3;
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        m7.q qVar = m7.q.f19267a;
        Object obj2 = this.f4550m;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                AbstractC1386c.u(obj);
                ((k0) obj2).g(null);
                break;
            case 1:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                ((C0523o0) obj2).f6012w.a();
                break;
            default:
                EnumC2303a enumC2303a3 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                ((A7.v) obj2).l = true;
                break;
        }
        return qVar;
    }
}
