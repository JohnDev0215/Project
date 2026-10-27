package R;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: R.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0688p extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7313m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f7314n;
    public final /* synthetic */ w o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0688p(v vVar, w wVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7314n = vVar;
        this.o = wVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0688p c0688p = new C0688p(this.f7314n, this.o, interfaceC2189c);
        c0688p.f7313m = obj;
        return c0688p;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0688p) create((E) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            E e10 = (E) this.f7313m;
            u uVar = this.o.f7335m;
            this.l = 1;
            if (this.f7314n.g(uVar, e10, this) == enumC2303a) {
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
