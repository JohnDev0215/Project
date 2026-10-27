package V8;

import W8.AbstractC0843c;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0824m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.z f9634m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0818g f9635n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0824m(A7.z zVar, InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9634m = zVar;
        this.f9635n = interfaceC0818g;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0824m(this.f9634m, this.f9635n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0824m) create((m7.q) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            A7.z zVar = this.f9634m;
            Object obj2 = zVar.l;
            if (obj2 == null) {
                return qVar;
            }
            zVar.l = null;
            if (obj2 == AbstractC0843c.f9911b) {
                obj2 = null;
            }
            this.l = 1;
            if (this.f9635n.f(obj2, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
