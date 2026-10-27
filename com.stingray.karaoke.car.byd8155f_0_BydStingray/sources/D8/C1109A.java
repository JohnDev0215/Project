package d8;

import Q7.InterfaceC0652e;
import java.util.Collection;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: d8.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1109A implements O8.b {
    public static final C1109A l = new C1109A();

    @Override // O8.b
    public final Iterable b(Object obj) {
        int i7 = C1111C.f13557p;
        Collection collectionO = ((InterfaceC0652e) obj).C().o();
        A7.m.e(collectionO, "it.typeConstructor.supertypes");
        return new P8.p(P8.m.h0(AbstractC1998m.S(collectionO), C1123j.f13597r), 0);
    }
}
