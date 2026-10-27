package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0570g0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E.D f6811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f6812n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6813p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6814q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0570g0(E.D d, float f10, int i7, int i10, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6811m = d;
        this.f6812n = f10;
        this.o = i7;
        this.f6813p = i10;
        this.f6814q = interfaceC2782k;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0570g0(this.f6811m, this.f6812n, this.o, this.f6813p, this.f6814q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0570g0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        int i10 = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            float f10 = this.f6812n * (i10 - this.f6813p);
            x.C0 c0Q = AbstractC2631e.q(500, 0, null, 6);
            this.l = 1;
            if (H7.H.p(this.f6811m, f10, c0Q, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.f6814q.b(new Integer(i10));
        return m7.q.f19267a;
    }
}
