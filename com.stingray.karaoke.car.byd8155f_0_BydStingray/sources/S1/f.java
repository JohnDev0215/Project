package S1;

import h8.AbstractC1386c;
import m7.q;
import n7.z;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7869m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i f7870n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public f(InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7870n = (i) interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        f fVar = new f(this.f7870n, interfaceC2189c);
        fVar.f7869m = obj;
        return fVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((b) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            b bVar = (b) this.f7869m;
            AbstractC1386c.u(obj);
            return bVar;
        }
        AbstractC1386c.u(obj);
        b bVar2 = new b(z.z(((b) this.f7869m).a()), false);
        this.f7869m = bVar2;
        this.l = 1;
        return this.f7870n.invoke(bVar2, this) == enumC2303a ? enumC2303a : bVar2;
    }
}
