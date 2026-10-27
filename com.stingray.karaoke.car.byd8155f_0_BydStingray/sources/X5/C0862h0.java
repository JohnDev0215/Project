package X5;

import h8.AbstractC1386c;
import java.util.List;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: X5.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0862h0 extends s7.i implements InterfaceC2787p {
    public /* synthetic */ List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ int f10264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ N5.f0 f10265n;

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        List list = this.l;
        return Boolean.valueOf(!list.isEmpty() && (!A7.m.a(AbstractC1998m.i0(list), this.f10265n) || this.f10264m == 2));
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int iIntValue = ((Number) obj2).intValue();
        C0862h0 c0862h0 = new C0862h0(4, (InterfaceC2189c) obj4);
        c0862h0.l = (List) obj;
        c0862h0.f10264m = iIntValue;
        c0862h0.f10265n = (N5.f0) obj3;
        return c0862h0.invokeSuspend(m7.q.f19267a);
    }
}
