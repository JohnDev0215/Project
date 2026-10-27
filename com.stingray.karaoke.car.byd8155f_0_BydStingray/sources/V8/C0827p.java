package V8;

import S8.AbstractC0704a;
import S8.AbstractC0727y;
import S8.C0711h;
import S8.EnumC0726x;
import S8.InterfaceC0725w;
import U8.EnumC0810a;
import W8.AbstractC0843c;
import a9.C0931a;
import a9.C0933c;
import h8.AbstractC1386c;
import q7.C2195i;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x3.AbstractC2673a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: V8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0827p extends s7.i implements InterfaceC2786o {
    public A7.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public U8.z f9640m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9641n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f9642p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f9643q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f f9644r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0827p(long j3, InterfaceC0817f interfaceC0817f, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f9643q = j3;
        this.f9644r = interfaceC0817f;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0827p c0827p = new C0827p(this.f9643q, this.f9644r, (InterfaceC2189c) obj3);
        c0827p.o = (InterfaceC0725w) obj;
        c0827p.f9642p = (InterfaceC0818g) obj2;
        return c0827p.invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v1, types: [A7.j, z7.o] */
    /* JADX WARN: Type inference failed for: r18v3, types: [A7.j, z7.o] */
    /* JADX WARN: Type inference failed for: r19v1, types: [A7.j, z7.o] */
    /* JADX WARN: Type inference failed for: r19v3, types: [A7.j, z7.o] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [U8.z] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [U8.z, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ?? r22;
        InterfaceC0818g interfaceC0818g;
        A7.z zVar;
        ?? r72;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f9641n;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.o;
            InterfaceC0818g interfaceC0818g2 = (InterfaceC0818g) this.f9642p;
            C0826o c0826o = new C0826o(this.f9644r, null);
            C2195i c2195i = C2195i.l;
            EnumC0810a enumC0810a = EnumC0810a.l;
            EnumC0726x enumC0726x = EnumC0726x.l;
            AbstractC0704a wVar = new U8.w(AbstractC0727y.x(interfaceC0725w, c2195i), AbstractC2673a.a(-1, 4, enumC0810a));
            wVar.i0(enumC0726x, wVar, c0826o);
            A7.z zVar2 = new A7.z();
            C0822k c0822k = new C0822k(this.f9643q, null);
            AbstractC0704a wVar2 = new U8.w(AbstractC0727y.x(interfaceC0725w, c2195i), AbstractC2673a.a(0, 4, enumC0810a));
            wVar2.i0(enumC0726x, wVar2, c0822k);
            r22 = wVar2;
            interfaceC0818g = interfaceC0818g2;
            zVar = zVar2;
            r72 = wVar;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            U8.z zVar3 = this.f9640m;
            zVar = this.l;
            U8.z zVar4 = (U8.z) this.f9642p;
            interfaceC0818g = (InterfaceC0818g) this.o;
            AbstractC1386c.u(obj);
            r22 = zVar3;
            r72 = zVar4;
        }
        while (zVar.l != AbstractC0843c.d) {
            C0933c c0933c = new C0933c(getContext());
            B8.t tVarF = r72.f();
            c0933c.f(new C0931a(c0933c, (U8.l) tVarF.f808n, (A7.j) tVarF.o, (A7.j) tVarF.f809p, new C0823l(zVar, r22, null), (C0711h) tVarF.f807m), false);
            B8.t tVarE = r22.e();
            c0933c.f(new C0931a(c0933c, (U8.l) tVarE.f808n, (A7.j) tVarE.o, (A7.j) tVarE.f809p, new C0824m(zVar, interfaceC0818g, null), (C0711h) tVarE.f807m), false);
            this.o = interfaceC0818g;
            this.f9642p = r72;
            this.l = zVar;
            this.f9640m = r22;
            this.f9641n = 1;
            if ((C0933c.f11227q.get(c0933c) instanceof C0931a ? c0933c.c(this) : c0933c.d(this)) == enumC2303a) {
                return enumC2303a;
            }
        }
        return m7.q.f19267a;
    }
}
