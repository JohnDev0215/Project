package V8;

import W8.AbstractC0843c;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0823l extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.z f9632m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ U8.z f9633n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0823l(A7.z zVar, U8.z zVar2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9632m = zVar;
        this.f9633n = zVar2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0823l c0823l = new C0823l(this.f9632m, this.f9633n, interfaceC2189c);
        c0823l.l = obj;
        return c0823l;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0823l c0823l = (C0823l) create(new U8.s(((U8.s) obj).f9271a), (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0823l.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Object obj2 = ((U8.s) this.l).f9271a;
        boolean z6 = obj2 instanceof U8.r;
        A7.z zVar = this.f9632m;
        if (!z6) {
            zVar.l = obj2;
        }
        if (z6) {
            U8.q qVar = obj2 instanceof U8.q ? (U8.q) obj2 : null;
            Throwable th = qVar != null ? qVar.f9269a : null;
            if (th != null) {
                throw th;
            }
            this.f9633n.g(new W8.o());
            zVar.l = AbstractC0843c.d;
        }
        return m7.q.f19267a;
    }
}
