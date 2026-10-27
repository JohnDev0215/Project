package W8;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: W8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0846f extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9914m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f9915n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0846f(g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9915n = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0846f c0846f = new C0846f(this.f9915n, interfaceC2189c);
        c0846f.f9914m = obj;
        return c0846f;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0846f) create((U8.x) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            U8.x xVar = (U8.x) this.f9914m;
            this.l = 1;
            if (this.f9915n.d(xVar, this) == enumC2303a) {
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
