package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0027m0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f209n;
    public final /* synthetic */ x.C0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.w f210p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0027m0(float f10, x.C0 c02, A7.w wVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f209n = f10;
        this.o = c02;
        this.f210p = wVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0027m0 c0027m0 = new C0027m0(this.f209n, this.o, this.f210p, interfaceC2189c);
        c0027m0.f208m = obj;
        return c0027m0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0027m0) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            L l = new L(this.f210p, 1, (InterfaceC0033p0) this.f208m);
            this.l = 1;
            if (AbstractC2631e.e(0.0f, this.f209n, this.o, l, this, 4) == enumC2303a) {
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
