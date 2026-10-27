package F3;

import A7.z;
import E3.m;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z3.C2764b;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h f2376m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f2377n;
    public final /* synthetic */ z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ K3.i f2378p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f2379q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ z f2380r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ z3.c f2381s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(h hVar, z zVar, z zVar2, K3.i iVar, Object obj, z zVar3, z3.c cVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2376m = hVar;
        this.f2377n = zVar;
        this.o = zVar2;
        this.f2378p = iVar;
        this.f2379q = obj;
        this.f2380r = zVar3;
        this.f2381s = cVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f2376m, this.f2377n, this.o, this.f2378p, this.f2379q, this.f2380r, this.f2381s, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            m mVar = (m) this.f2377n.l;
            C2764b c2764b = (C2764b) this.o.l;
            K3.m mVar2 = (K3.m) this.f2380r.l;
            this.l = 1;
            obj = h.a(this.f2376m, mVar, c2764b, this.f2378p, this.f2379q, mVar2, this.f2381s, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
