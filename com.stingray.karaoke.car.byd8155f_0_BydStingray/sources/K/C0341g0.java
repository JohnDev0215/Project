package K;

import S8.AbstractC0727y;
import S8.EnumC0726x;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0341g0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2722B f3267m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3268n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0341g0(C2722B c2722b, InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3267m = c2722b;
        this.f3268n = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0341g0 c0341g0 = new C0341g0(this.f3267m, this.f3268n, interfaceC2189c);
        c0341g0.l = obj;
        return c0341g0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0341g0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        EnumC0726x enumC0726x = EnumC0726x.o;
        C2722B c2722b = this.f3267m;
        InterfaceC0355n0 interfaceC0355n0 = this.f3268n;
        AbstractC0727y.w(interfaceC0725w, null, enumC0726x, new C0337e0(c2722b, interfaceC0355n0, null), 1);
        return AbstractC0727y.w(interfaceC0725w, null, enumC0726x, new C0339f0(c2722b, interfaceC0355n0, null), 1);
    }
}
