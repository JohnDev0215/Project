package R;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: R.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0679g extends s7.i implements InterfaceC2786o {
    public /* synthetic */ InterfaceC0725w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ float f7293m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ w f7294n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0679g(w wVar, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f7294n = wVar;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws Throwable {
        float fFloatValue = ((Number) obj2).floatValue();
        C0679g c0679g = new C0679g(this.f7294n, (InterfaceC2189c) obj3);
        c0679g.l = (InterfaceC0725w) obj;
        c0679g.f7293m = fFloatValue;
        m7.q qVar = m7.q.f19267a;
        c0679g.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        AbstractC0727y.w(this.l, null, null, new C0678f(this.f7294n, this.f7293m, null), 3);
        return m7.q.f19267a;
    }
}
