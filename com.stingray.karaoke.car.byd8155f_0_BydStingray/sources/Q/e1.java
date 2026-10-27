package Q;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2629d;
import x.InterfaceC2647m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g1 f5878m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f5879n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(g1 g1Var, float f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5878m = g1Var;
        this.f5879n = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new e1(this.f5878m, this.f5879n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            g1 g1Var = this.f5878m;
            C2629d c2629d = g1Var.B;
            if (c2629d != null) {
                Float f10 = new Float(this.f5879n);
                InterfaceC2647m interfaceC2647m = g1Var.f5894A ? androidx.compose.material3.d.f11544f : androidx.compose.material3.d.f11545g;
                this.l = 1;
                obj = C2629d.c(c2629d, f10, interfaceC2647m, null, this, 12);
                if (obj == enumC2303a) {
                    return enumC2303a;
                }
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        return m7.q.f19267a;
    }
}
