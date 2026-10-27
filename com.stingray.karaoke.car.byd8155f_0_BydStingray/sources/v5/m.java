package V5;

import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2629d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f9527m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(o oVar, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f9527m = oVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((m) create((InterfaceC2189c) obj)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new m(this.f9527m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            o oVar = this.f9527m;
            Float f10 = new Float(0.0f);
            this.l = 1;
            obj = C2629d.c(oVar.f9530a, f10, null, null, this, 14);
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
