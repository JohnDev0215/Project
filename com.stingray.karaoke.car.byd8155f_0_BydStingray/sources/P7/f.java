package P7;

import Q7.InterfaceC0671y;
import T7.w;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f5428m = new f(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC0671y interfaceC0671y = (InterfaceC0671y) obj;
        A7.m.f(interfaceC0671y, "module");
        List list = (List) C7.a.N(((w) interfaceC0671y.n0(g.f5430f)).f8914q, w.f8912t[0]);
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            if (obj2 instanceof C8.c) {
                arrayList.add(obj2);
            }
        }
        return (C8.c) AbstractC1998m.Y(arrayList);
    }
}
