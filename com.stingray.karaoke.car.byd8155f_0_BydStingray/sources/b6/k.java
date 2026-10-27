package b6;

import h8.AbstractC1386c;
import java.util.List;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2787p {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ List f12256m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ List f12257n;

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        boolean z6 = this.l;
        List list = this.f12256m;
        List list2 = this.f12257n;
        boolean z10 = true;
        if (!z6 && list.size() > 1 && list2 != null && !list2.isEmpty()) {
            z10 = false;
        }
        return Boolean.valueOf(z10);
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        k kVar = new k(4, (InterfaceC2189c) obj4);
        kVar.l = zBooleanValue;
        kVar.f12256m = (List) obj2;
        kVar.f12257n = (List) obj3;
        return kVar.invokeSuspend(q.f19267a);
    }
}
