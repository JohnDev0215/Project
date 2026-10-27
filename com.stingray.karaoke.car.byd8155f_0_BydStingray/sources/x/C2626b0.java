package x;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2626b0 extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f23485m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f23486n;
    public final /* synthetic */ C2634f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ x0 f23487p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f23488q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2626b0(Object obj, Object obj2, C2634f0 c2634f0, x0 x0Var, float f10, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f23485m = obj;
        this.f23486n = obj2;
        this.o = c2634f0;
        this.f23487p = x0Var;
        this.f23488q = f10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C2626b0) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C2626b0(this.f23485m, this.f23486n, this.o, this.f23487p, this.f23488q, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2624a0 c2624a0 = new C2624a0(this.f23485m, this.f23486n, this.o, this.f23487p, this.f23488q, null);
            this.l = 1;
            if (AbstractC0727y.h(c2624a0, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
