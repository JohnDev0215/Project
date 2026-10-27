package U5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0730a0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9152m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9153n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f9154p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f9155q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(boolean z6, int i7, InterfaceC2772a interfaceC2772a, C0730a0 c0730a0, C0730a0 c0730a02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9152m = z6;
        this.f9153n = i7;
        this.o = interfaceC2772a;
        this.f9154p = c0730a0;
        this.f9155q = c0730a02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l(this.f9152m, this.f9153n, this.o, this.f9154p, this.f9155q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((l) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0730a0 c0730a0 = this.f9155q;
        C0730a0 c0730a02 = this.f9154p;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (!this.f9152m) {
                c0730a02.j(1.0f);
                return m7.q.f19267a;
            }
            long j3 = this.f9153n;
            this.l = 1;
            if (AbstractC0727y.i(j3, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
                c0730a0.j(1.0f);
                return m7.q.f19267a;
            }
            AbstractC1386c.u(obj);
        }
        InterfaceC2772a interfaceC2772a = this.o;
        if (interfaceC2772a != null) {
            interfaceC2772a.a();
        }
        c0730a02.j(1.0f);
        c0730a0.j(1.3f);
        this.l = 2;
        if (AbstractC0727y.i(25L, this) == enumC2303a) {
            return enumC2303a;
        }
        c0730a0.j(1.0f);
        return m7.q.f19267a;
    }
}
