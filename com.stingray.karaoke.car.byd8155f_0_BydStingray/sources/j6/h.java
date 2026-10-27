package j6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.Map;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17045m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(n nVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17045m = nVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new h(this.f17045m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        n nVar = this.f17045m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                Integer num = r6.d.e(nVar.f17057a.b(), null, 15).f13081a;
                if (num == null || num.intValue() == 0) {
                    B9.a aVar = Timber.f22577a;
                    aVar.e("ProfileManager");
                    aVar.b("Failed to clear from top scores", new Object[0]);
                } else {
                    ((Map) nVar.f17060f.getValue()).clear();
                    this.l = 1;
                    if (n.b(nVar, "", this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
        } catch (Exception e10) {
            B9.a aVar2 = Timber.f22577a;
            aVar2.e("ProfileManager");
            aVar2.a(e10);
        }
        return m7.q.f19267a;
    }
}
