package V5;

import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2629d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f9528m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f9529n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o oVar, float f10, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f9528m = oVar;
        this.f9529n = f10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((n) create((InterfaceC2189c) obj)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new n(this.f9528m, this.f9529n, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2629d c2629d = this.f9528m.f9530a;
            Float f10 = new Float(((Number) c2629d.d()).floatValue() + this.f9529n);
            this.l = 1;
            if (c2629d.e(f10, this) == enumC2303a) {
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
