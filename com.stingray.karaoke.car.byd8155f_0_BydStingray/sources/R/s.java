package R;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class s extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7319m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0680h f7320n;
    public final /* synthetic */ w o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(C0680h c0680h, w wVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7320n = c0680h;
        this.o = wVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        s sVar = new s(this.f7320n, this.o, interfaceC2189c);
        sVar.f7319m = obj;
        return sVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((s) create((m7.i) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            m7.i iVar = (m7.i) this.f7319m;
            E e10 = (E) iVar.l;
            u uVar = this.o.f7335m;
            this.l = 1;
            if (this.f7320n.o(uVar, e10, iVar.f19259m, this) == enumC2303a) {
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
