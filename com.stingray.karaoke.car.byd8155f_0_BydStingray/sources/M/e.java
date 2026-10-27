package M;

import F0.C0196c0;
import K.p0;
import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4134m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p0 f4135n;
    public final /* synthetic */ f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ v f4136p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(p0 p0Var, f fVar, v vVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4135n = p0Var;
        this.o = fVar;
        this.f4136p = vVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        e eVar = new e(this.f4135n, this.o, this.f4136p, interfaceC2189c);
        eVar.f4134m = obj;
        return eVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((e) create((C0196c0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0405d c0405d = new C0405d((C0196c0) this.f4134m, this.f4135n, this.o, this.f4136p, null);
            this.l = 1;
            if (AbstractC0727y.h(c0405d, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        throw new A9.k();
    }
}
