package K;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0366y extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ H.c f3456m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T0.y f3457n;
    public final /* synthetic */ C0331b0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ G0 f3458p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T0.s f3459q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0366y(H.c cVar, T0.y yVar, C0331b0 c0331b0, G0 g02, T0.s sVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3456m = cVar;
        this.f3457n = yVar;
        this.o = c0331b0;
        this.f3458p = g02;
        this.f3459q = sVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0366y(this.f3456m, this.f3457n, this.o, this.f3458p, this.f3459q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0366y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        m0.d dVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0353m0 c0353m0 = this.o.f3220a;
            N0.H h10 = this.f3458p.f3161a;
            this.l = 1;
            int iB = this.f3459q.b(N0.K.d(this.f3457n.f8482b));
            if (iB < h10.f4348a.f4340a.l.length()) {
                dVar = h10.b(iB);
            } else if (iB != 0) {
                dVar = h10.b(iB - 1);
            } else {
                dVar = new m0.d(0.0f, 0.0f, 1.0f, (int) (s0.a(c0353m0.f3339b, c0353m0.f3342g, c0353m0.f3343h, s0.f3382a, 1) & 4294967295L));
            }
            Object objA = this.f3456m.a(dVar, this);
            if (objA != enumC2303a) {
                objA = qVar;
            }
            if (objA == enumC2303a) {
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
