package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F0 extends s7.i implements InterfaceC2785n {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f10166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f10167n;
    public final /* synthetic */ G0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f10168p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ M5.d f10169q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F0(G0 g02, ActionOwner actionOwner, M5.d dVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = g02;
        this.f10168p = actionOwner;
        this.f10169q = dVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new F0(this.o, this.f10168p, this.f10169q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((F0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        long jCurrentTimeMillis;
        long j3;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f10167n;
        ActionOwner actionOwner = this.f10168p;
        G0 g02 = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            G0.a(g02, 4000L, actionOwner);
            jCurrentTimeMillis = System.currentTimeMillis();
            j3 = 4000;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            jCurrentTimeMillis = this.f10166m;
            j3 = this.l;
            AbstractC1386c.u(obj);
        }
        do {
            long jCurrentTimeMillis2 = j3 - (System.currentTimeMillis() - jCurrentTimeMillis);
            if (jCurrentTimeMillis2 <= 0) {
                this.f10169q.a();
                S8.k0 k0Var = g02.c;
                if (k0Var != null) {
                    k0Var.g(null);
                }
                g02.c = null;
                g02.f10171a.l(null);
                g02.d = null;
                return m7.q.f19267a;
            }
            G0.a(g02, jCurrentTimeMillis2, actionOwner);
            this.l = j3;
            this.f10166m = jCurrentTimeMillis;
            this.f10167n = 1;
        } while (AbstractC0727y.i(16L, this) != enumC2303a);
        return enumC2303a;
    }
}
