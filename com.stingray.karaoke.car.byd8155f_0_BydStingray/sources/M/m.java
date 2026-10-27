package M;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import S8.W;
import S8.k0;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f4145m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(n nVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4145m = nVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m mVar = new m(this.f4145m, interfaceC2189c);
        mVar.l = obj;
        return mVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        boolean z6;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        n nVar = this.f4145m;
        W w10 = (W) nVar.f4146a.getAndSet(null);
        AtomicReference atomicReference = nVar.f4146a;
        k0 k0VarW = AbstractC0727y.w(interfaceC0725w, null, null, new l(w10, nVar, null), 3);
        while (true) {
            if (atomicReference.compareAndSet(null, k0VarW)) {
                z6 = true;
                break;
            }
            if (atomicReference.get() != null) {
                z6 = false;
                break;
            }
        }
        return Boolean.valueOf(z6);
    }
}
