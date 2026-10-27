package N1;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N1.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0439u extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ G f4555m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0439u(G g7, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4555m = g7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0439u) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0439u(this.f4555m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            obj = this.f4555m.b(this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
