package b6;

import A7.v;
import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import V8.N;
import h8.AbstractC1386c;
import k6.C1705m;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1705m f12231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f12232n;
    public final /* synthetic */ f o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(C1705m c1705m, v vVar, f fVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f12231m = c1705m;
        this.f12232n = vVar;
        this.o = fVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f12231m, this.f12232n, this.o, interfaceC2189c);
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
            N n10 = this.f12231m.d;
            c cVar = new c(this.f12232n, this.o, null);
            this.l = 1;
            if (AbstractC0828q.g(n10, cVar, this) == enumC2303a) {
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
