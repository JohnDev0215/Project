package j6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r6.C2301b;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f17039n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(n nVar, String str, int i7, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17038m = nVar;
        this.f17039n = str;
        this.o = i7;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f17038m, this.f17039n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str = this.f17039n;
        n nVar = this.f17038m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                C2301b.b((C2301b) nVar.f17057a.f3093f.getValue(), str, this.o);
                this.l = 1;
                Object objF = nVar.d.f(new p(str), this);
                if (objF != enumC2303a) {
                    objF = qVar;
                }
                if (objF == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
        } catch (Exception e10) {
            B9.a aVar = Timber.f22577a;
            aVar.e("ProfileManager");
            aVar.a(e10);
        }
        return qVar;
    }
}
