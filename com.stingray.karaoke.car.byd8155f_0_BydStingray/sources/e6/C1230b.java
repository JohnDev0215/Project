package e6;

import A7.v;
import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import V8.N;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: e6.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1230b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g f14437m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f14438n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1230b(v vVar, g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14437m = gVar;
        this.f14438n = vVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1230b(this.f14438n, this.f14437m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1230b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            g gVar = this.f14437m;
            N n10 = gVar.f14448u.f18294e;
            C1229a c1229a = new C1229a(this.f14438n, gVar, null);
            this.l = 1;
            if (AbstractC0828q.g(n10, c1229a, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
