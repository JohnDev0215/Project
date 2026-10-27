package y;

import A.C0023k0;
import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: y.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2718x extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f24022m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ C0023k0 f24023n;
    public /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ AbstractC2705j f24024p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2718x(AbstractC2705j abstractC2705j, InterfaceC2189c interfaceC2189c, int i7) {
        super(3, interfaceC2189c);
        this.l = i7;
        this.f24024p = abstractC2705j;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0023k0 c0023k0 = (C0023k0) obj;
        m0.c cVar = (m0.c) obj2;
        switch (this.l) {
            case 0:
                long j3 = cVar.f18880a;
                C2718x c2718x = new C2718x((C2719y) this.f24024p, (InterfaceC2189c) obj3, 0);
                c2718x.f24023n = c0023k0;
                c2718x.o = j3;
                return c2718x.invokeSuspend(m7.q.f19267a);
            default:
                long j10 = cVar.f18880a;
                C2718x c2718x2 = new C2718x((C2682C) this.f24024p, (InterfaceC2189c) obj3, 1);
                c2718x2.f24023n = c0023k0;
                c2718x2.o = j10;
                return c2718x2.invokeSuspend(m7.q.f19267a);
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objH;
        Object objH2;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                int i7 = this.f24022m;
                m7.q qVar = m7.q.f19267a;
                if (i7 == 0) {
                    AbstractC1386c.u(obj);
                    C0023k0 c0023k0 = this.f24023n;
                    long j3 = this.o;
                    C2719y c2719y = (C2719y) this.f24024p;
                    if (c2719y.f23929E) {
                        this.f24022m = 1;
                        C.k kVar = c2719y.f23926A;
                        if (kVar == null || (objH = AbstractC0727y.h(new C2699d(c0023k0, j3, kVar, c2719y, null), this)) != enumC2303a) {
                            objH = qVar;
                        }
                        if (objH == enumC2303a) {
                            return enumC2303a;
                        }
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    AbstractC1386c.u(obj);
                }
                return qVar;
            default:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                int i10 = this.f24022m;
                m7.q qVar2 = m7.q.f19267a;
                if (i10 == 0) {
                    AbstractC1386c.u(obj);
                    C0023k0 c0023k02 = this.f24023n;
                    long j10 = this.o;
                    C2682C c2682c = (C2682C) this.f24024p;
                    if (c2682c.f23929E) {
                        this.f24022m = 1;
                        C.k kVar2 = c2682c.f23926A;
                        if (kVar2 == null || (objH2 = AbstractC0727y.h(new C2699d(c0023k02, j10, kVar2, c2682c, null), this)) != enumC2303a2) {
                            objH2 = qVar2;
                        }
                        if (objH2 == enumC2303a2) {
                            return enumC2303a2;
                        }
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    AbstractC1386c.u(obj);
                }
                return qVar2;
        }
    }
}
