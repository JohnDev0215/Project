package p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import kotlin.Pair;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class lw1 {
    public final List a;
    public final u11 b;
    public final ArrayList c;

    public lw1(String str) {
        v03.j(str, "spotifyId");
        EmptyList emptyList = EmptyList.c;
        u11 u11Var = new u11();
        v03.j(emptyList, "additionalPolicyInputs");
        this.a = emptyList;
        this.b = u11Var;
        this.c = new ArrayList();
        a(new f31(str));
    }

    public static LinkedHashMap c(ArrayList arrayList) {
        int iB = he1.B(lu.W(arrayList));
        if (iB < 16) {
            iB = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iB);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            kw1 kw1Var = (kw1) it.next();
            Pair pair = new Pair(kw1Var.getPolicyId(), kw1Var.b());
            linkedHashMap.put(pair.c(), pair.d());
        }
        return linkedHashMap;
    }

    public final void a(kw1 kw1Var) {
        this.c.add(kw1Var);
    }

    public final TreeMap b() {
        ArrayList arrayList = this.c;
        this.b.getClass();
        return new TreeMap(c(ou.k0(EmptyList.c, arrayList)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof lw1) {
            return v03.b(b(), ((lw1) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        ArrayList arrayList = this.c;
        this.b.getClass();
        ArrayList arrayListK0 = ou.k0(EmptyList.c, arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayListK0) {
            if (((kw1) obj).a()) {
                arrayList2.add(obj);
            }
        }
        return c(arrayList2).hashCode();
    }
}
