package e6;

import A7.m;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g f14440m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14440m = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f14440m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                g gVar = this.f14440m;
                this.l = 1;
                if (g.o(gVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
        } catch (Throwable th) {
            String str = "Error refreshing data: " + th;
            m.f(str, "error");
            B9.a aVar = Timber.f22577a;
            aVar.e("");
            aVar.b(str, new Object[0]);
        }
        return q.f19267a;
    }
}
