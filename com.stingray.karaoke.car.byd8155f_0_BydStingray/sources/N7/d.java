package N7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f4790a;

    static {
        Set<k> set = k.f4801p;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(set, 10));
        for (k kVar : set) {
            A7.m.f(kVar, "primitiveType");
            arrayList.add(o.f4875j.c(kVar.l));
        }
        C2058c c2058cG = n.f4849f.g();
        A7.m.e(c2058cG, "string.toSafe()");
        ArrayList arrayListO0 = AbstractC1998m.o0(arrayList, c2058cG);
        C2058c c2058cG2 = n.f4851h.g();
        A7.m.e(c2058cG2, "_boolean.toSafe()");
        ArrayList arrayListO02 = AbstractC1998m.o0(arrayListO0, c2058cG2);
        C2058c c2058cG3 = n.f4853j.g();
        A7.m.e(c2058cG3, "_enum.toSafe()");
        ArrayList arrayListO03 = AbstractC1998m.o0(arrayListO02, c2058cG3);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = arrayListO03.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(C2057b.j((C2058c) it.next()));
        }
        f4790a = linkedHashSet;
    }
}
