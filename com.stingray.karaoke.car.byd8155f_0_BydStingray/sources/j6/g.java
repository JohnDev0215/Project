package j6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.Map;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17043m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f17044n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(n nVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17043m = nVar;
        this.f17044n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f17043m, this.f17044n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str = this.f17044n;
        n nVar = this.f17043m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                Integer num = r6.d.e(nVar.f17057a.b(), str, 13).f13081a;
                if (num == null || num.intValue() == 0) {
                    String str2 = "Failed to clear from top scores: " + str;
                    A7.m.f(str2, "error");
                    B9.a aVar = Timber.f22577a;
                    aVar.e("ProfileManager");
                    aVar.b(str2, new Object[0]);
                } else {
                    ((Map) nVar.f17060f.getValue()).remove(str);
                    this.l = 1;
                    if (n.b(nVar, str, this) == enumC2303a) {
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
