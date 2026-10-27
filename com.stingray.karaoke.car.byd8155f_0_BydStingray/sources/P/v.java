package P;

import S8.InterfaceC0725w;
import V8.S;
import androidx.compose.material.ripple.RippleNode;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class v extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5258m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ RippleNode f5259n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(RippleNode rippleNode, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5259n = rippleNode;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        v vVar = new v(this.f5259n, interfaceC2189c);
        vVar.f5258m = obj;
        return vVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((v) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            return m7.q.f19267a;
        }
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f5258m;
        RippleNode rippleNode = this.f5259n;
        S s6 = rippleNode.f11529y.f832a;
        C.f fVar = new C.f(rippleNode, 4, interfaceC0725w);
        this.l = 1;
        s6.b(fVar, this);
        return enumC2303a;
    }
}
