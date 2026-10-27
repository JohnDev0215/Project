package x;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0735d;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class w0 extends s7.i implements InterfaceC2785n {
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23653m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23654n;
    public final /* synthetic */ x0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(x0 x0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = x0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        w0 w0Var = new w0(this.o, interfaceC2189c);
        w0Var.f23654n = obj;
        return w0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((w0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        float fL;
        InterfaceC0725w interfaceC0725w;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f23653m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w2 = (InterfaceC0725w) this.f23654n;
            fL = AbstractC2631e.l(interfaceC0725w2.getCoroutineContext());
            interfaceC0725w = interfaceC0725w2;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            fL = this.l;
            interfaceC0725w = (InterfaceC0725w) this.f23654n;
            AbstractC1386c.u(obj);
        }
        while (AbstractC0727y.s(interfaceC0725w)) {
            v0 v0Var = new v0(this.o, fL);
            this.f23654n = interfaceC0725w;
            this.l = fL;
            this.f23653m = 1;
            if (C0735d.I(getContext()).o(v0Var, this) == enumC2303a) {
                return enumC2303a;
            }
        }
        return m7.q.f19267a;
    }
}
