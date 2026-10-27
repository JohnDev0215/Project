package T;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0760p0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f8288m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0765s0 f8289n;
    public final /* synthetic */ T o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0760p0(C0765s0 c0765s0, T t10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8289n = c0765s0;
        this.o = t10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0760p0 c0760p0 = new C0760p0(this.f8289n, this.o, interfaceC2189c);
        c0760p0.f8288m = obj;
        return c0760p0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0760p0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f8288m;
        this.l = 1;
        this.f8289n.g(interfaceC0725w, this.o, this);
        return enumC2303a;
    }
}
