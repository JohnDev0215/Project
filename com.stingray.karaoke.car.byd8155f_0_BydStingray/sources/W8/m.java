package W8;

import K.C0358p;
import S8.InterfaceC0725w;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9925m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f9926n;
    public final /* synthetic */ InterfaceC0818g o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(n nVar, InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9926n = nVar;
        this.o = interfaceC0818g;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m mVar = new m(this.f9926n, this.o, interfaceC2189c);
        mVar.f9925m = obj;
        return mVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f9925m;
            A7.z zVar = new A7.z();
            n nVar = this.f9926n;
            InterfaceC0817f interfaceC0817f = nVar.o;
            C0358p c0358p = new C0358p(zVar, interfaceC0725w, nVar, this.o, 1);
            this.l = 1;
            if (interfaceC0817f.b(c0358p, this) == enumC2303a) {
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
