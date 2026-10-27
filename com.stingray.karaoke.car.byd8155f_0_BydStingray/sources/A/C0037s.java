package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0037s extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0043v f265n;
    public final /* synthetic */ InterfaceC2785n o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0037s(C0043v c0043v, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f265n = c0043v;
        this.o = interfaceC2785n;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0037s c0037s = new C0037s(this.f265n, this.o, interfaceC2189c);
        c0037s.f264m = obj;
        return c0037s;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0037s) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0043v c0043v = this.f265n;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                InterfaceC0033p0 interfaceC0033p0 = (InterfaceC0033p0) this.f264m;
                c0043v.d.setValue(Boolean.TRUE);
                InterfaceC2785n interfaceC2785n = this.o;
                this.l = 1;
                if (interfaceC2785n.invoke(interfaceC0033p0, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            c0043v.d.setValue(Boolean.FALSE);
            return m7.q.f19267a;
        } catch (Throwable th) {
            c0043v.d.setValue(Boolean.FALSE);
            throw th;
        }
    }
}
