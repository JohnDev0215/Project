package K;

import S8.InterfaceC0725w;
import T.C0735d;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0359q extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3371m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f3372n;
    public final /* synthetic */ T0.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ O.K f3373p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T0.m f3374q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0359q(C0331b0 c0331b0, T.X x8, T0.z zVar, O.K k10, T0.m mVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3371m = c0331b0;
        this.f3372n = x8;
        this.o = zVar;
        this.f3373p = k10;
        this.f3374q = mVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0359q(this.f3371m, this.f3372n, this.o, this.f3373p, this.f3374q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0359q) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0331b0 c0331b0 = this.f3371m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                C0819h c0819hW = C0735d.W(new E.n(this.f3372n, 4));
                C0358p c0358p = new C0358p(c0331b0, this.o, this.f3373p, this.f3374q, 0);
                this.l = 1;
                if (c0819hW.b(c0358p, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            X.i(c0331b0);
            return m7.q.f19267a;
        } catch (Throwable th) {
            X.i(c0331b0);
            throw th;
        }
    }
}
