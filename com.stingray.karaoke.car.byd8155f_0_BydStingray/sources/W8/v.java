package W8;

import S8.InterfaceC0725w;
import V8.C0827p;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class v extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9942m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0827p f9943n;
    public final /* synthetic */ InterfaceC0818g o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(C0827p c0827p, InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9943n = c0827p;
        this.o = interfaceC0818g;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        v vVar = new v(this.f9943n, this.o, interfaceC2189c);
        vVar.f9942m = obj;
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
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f9942m;
            this.l = 1;
            if (this.f9943n.g(interfaceC0725w, this.o, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
