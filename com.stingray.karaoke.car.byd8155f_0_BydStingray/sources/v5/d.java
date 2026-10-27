package V5;

import Q5.C0602o0;
import S8.InterfaceC0725w;
import T.X;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f9496m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9497n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f9498p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(o oVar, int i7, float f10, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9496m = oVar;
        this.f9497n = i7;
        this.o = f10;
        this.f9498p = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f9496m, this.f9497n, this.o, this.f9498p, interfaceC2189c);
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
            h hVar = g.f9506a;
            X x8 = this.f9498p;
            float fFloatValue = ((Number) x8.getValue()).floatValue();
            float f10 = this.f9496m.b() ? this.f9497n + this.o : 0.0f;
            C0602o0 c0602o0 = new C0602o0(x8, 1);
            this.l = 1;
            if (AbstractC2631e.e(fFloatValue, f10, null, c0602o0, this, 12) == enumC2303a) {
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
