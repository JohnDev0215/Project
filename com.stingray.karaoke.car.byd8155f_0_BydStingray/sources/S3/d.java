package S3;

import S8.AbstractC0727y;
import S8.g0;
import T.C0738e0;
import h8.AbstractC1386c;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h f7887m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7888n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f7889p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f7890q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ O3.a f7891r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ float f7892s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f7893t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f7894u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ l f7895v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(h hVar, int i7, int i10, boolean z6, float f10, O3.a aVar, float f11, boolean z10, boolean z11, l lVar, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f7887m = hVar;
        this.f7888n = i7;
        this.o = i10;
        this.f7889p = z6;
        this.f7890q = f10;
        this.f7891r = aVar;
        this.f7892s = f11;
        this.f7893t = z10;
        this.f7894u = z11;
        this.f7895v = lVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((d) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new d(this.f7887m, this.f7888n, this.o, this.f7889p, this.f7890q, this.f7891r, this.f7892s, this.f7893t, this.f7894u, this.f7895v, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        InterfaceC2194h interfaceC2194h;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        h hVar = this.f7887m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                hVar.g(this.f7888n);
                int i10 = this.o;
                hVar.f7904n.setValue(Integer.valueOf(i10));
                hVar.o.setValue(Boolean.valueOf(this.f7889p));
                float f10 = this.f7890q;
                hVar.f7906q.setValue(Float.valueOf(f10));
                hVar.f7905p.setValue(null);
                C0738e0 c0738e0 = hVar.f7909t;
                O3.a aVar = this.f7891r;
                c0738e0.setValue(aVar);
                hVar.h(this.f7892s);
                hVar.f7907r.setValue(Boolean.valueOf(this.f7893t));
                if (!this.f7894u) {
                    hVar.f7912w.setValue(Long.MIN_VALUE);
                }
                C0738e0 c0738e02 = hVar.l;
                if (aVar == null) {
                    c0738e02.setValue(Boolean.FALSE);
                    return qVar;
                }
                if (Float.isInfinite(f10)) {
                    hVar.h(hVar.d());
                    c0738e02.setValue(Boolean.FALSE);
                    hVar.g(i10);
                    return qVar;
                }
                c0738e02.setValue(Boolean.TRUE);
                int iOrdinal = this.f7895v.ordinal();
                if (iOrdinal == 0) {
                    interfaceC2194h = C2195i.l;
                } else {
                    if (iOrdinal != 1) {
                        throw new A9.k();
                    }
                    interfaceC2194h = g0.f8113m;
                }
                c cVar = new c(this.f7895v, AbstractC0727y.o(getContext()), this.o, this.f7888n, this.f7887m, null);
                this.l = 1;
                if (AbstractC0727y.F(interfaceC2194h, cVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            AbstractC0727y.k(getContext());
            return qVar;
        } finally {
            h.c(hVar, false);
        }
    }
}
