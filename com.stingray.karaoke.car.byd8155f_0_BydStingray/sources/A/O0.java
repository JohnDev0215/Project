package A;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O0 extends s7.i implements InterfaceC2785n {
    public S0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.y f80m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f81n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f82p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ S0 f83q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ A7.y f84r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ long f85s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O0(S0 s02, A7.y yVar, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f83q = s02;
        this.f84r = yVar;
        this.f85s = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        O0 o02 = new O0(this.f83q, this.f84r, this.f85s, interfaceC2189c);
        o02.f82p = obj;
        return o02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((O0) create((P0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        S0 s02;
        A7.y yVar;
        S0 s03;
        long j3;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.o;
        EnumC0017h0 enumC0017h0 = EnumC0017h0.f181m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            P0 p02 = (P0) this.f82p;
            s02 = this.f83q;
            N0 n02 = new N0(s02, p02);
            r rVar = s02.c;
            yVar = this.f84r;
            long j10 = yVar.l;
            EnumC0017h0 enumC0017h02 = s02.d;
            long j11 = this.f85s;
            float fC = s02.c(enumC0017h02 == enumC0017h0 ? a1.o.b(j11) : a1.o.c(j11));
            this.f82p = s02;
            this.l = s02;
            this.f80m = yVar;
            this.f81n = j10;
            this.o = 1;
            rVar.getClass();
            obj = AbstractC0727y.F(rVar.f262b, new C0034q(fC, rVar, n02, null), this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            s03 = s02;
            j3 = j10;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j3 = this.f81n;
            yVar = this.f80m;
            s02 = this.l;
            s03 = (S0) this.f82p;
            AbstractC1386c.u(obj);
        }
        float fC2 = s03.c(((Number) obj).floatValue());
        yVar.l = s02.d == enumC0017h0 ? a1.o.a(j3, fC2, 0.0f, 2) : a1.o.a(j3, 0.0f, fC2, 1);
        return m7.q.f19267a;
    }
}
