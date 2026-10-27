package p;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class ve1 implements a81 {
    public Object c;
    public boolean e;
    public final ArrayList k = new ArrayList();

    public static Object b(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj2 == null) {
            return obj;
        }
        if (obj instanceof List) {
            if (!(obj2 instanceof List)) {
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            List list = (List) obj;
            List list2 = (List) obj2;
            if (list.size() != list2.size()) {
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            i51 i51Var = new i51(0, ((Collection) obj).size() - 1, 1);
            ArrayList arrayList = new ArrayList(lu.W(i51Var));
            Iterator it = i51Var.iterator();
            while (((h51) it).k) {
                int iB = ((h51) it).b();
                arrayList.add(b(list.get(iB), list2.get(iB)));
            }
            return arrayList;
        }
        if (!(obj instanceof Map)) {
            if (v03.b(obj, obj2)) {
                return obj;
            }
            throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
        }
        if (!(obj2 instanceof Map)) {
            throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
        }
        Map map = (Map) obj;
        Map map2 = (Map) obj2;
        Set setKeySet = map.keySet();
        Set setKeySet2 = map2.keySet();
        v03.j(setKeySet, "<this>");
        v03.j(setKeySet2, "elements");
        Integer numValueOf = Integer.valueOf(setKeySet2.size());
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet(he1.B(numValueOf != null ? setKeySet.size() + numValueOf.intValue() : setKeySet.size() * 2));
        linkedHashSet.addAll(setKeySet);
        nu.X(setKeySet2, linkedHashSet);
        ArrayList arrayList2 = new ArrayList(lu.W(linkedHashSet));
        for (String str : linkedHashSet) {
            arrayList2.add(new Pair(str, b(map.get(str), map2.get(str))));
        }
        return kotlin.collections.c.k0(arrayList2);
    }

    @Override // p.a81
    public final a81 A(int i) {
        i(Integer.valueOf(i));
        return this;
    }

    @Override // p.a81
    public final a81 G(double d) {
        i(Double.valueOf(d));
        return this;
    }

    @Override // p.a81
    public final a81 N(String str) {
        v03.j(str, "value");
        i(str);
        return this;
    }

    public final Object c() {
        if (this.e) {
            return this.c;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.a81
    public final a81 f() {
        this.k.add(new te1(new LinkedHashMap()));
        return this;
    }

    @Override // p.a81
    public final a81 h() {
        ue1 ue1Var = (ue1) this.k.remove(r0.size() - 1);
        if (!(ue1Var instanceof se1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        i(((se1) ue1Var).a);
        return this;
    }

    public final void i(Object obj) {
        ArrayList arrayList = this.k;
        v03.j(arrayList, "<this>");
        ue1 ue1Var = (ue1) (arrayList.isEmpty() ? null : arrayList.get(arrayList.size() - 1));
        if (!(ue1Var instanceof te1)) {
            if (ue1Var instanceof se1) {
                ((se1) ue1Var).a.add(obj);
                return;
            } else {
                this.c = obj;
                this.e = true;
                return;
            }
        }
        te1 te1Var = (te1) ue1Var;
        String str = te1Var.b;
        if (str == null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Map map = te1Var.a;
        if (map.containsKey(str)) {
            map.put(str, b(map.get(str), obj));
        } else {
            map.put(str, obj);
        }
        te1Var.b = null;
    }

    @Override // p.a81
    public final a81 i0() {
        i(null);
        return this;
    }

    @Override // p.a81
    public final a81 j() {
        this.k.add(new se1(new ArrayList()));
        return this;
    }

    @Override // p.a81
    public final a81 k() {
        ue1 ue1Var = (ue1) this.k.remove(r0.size() - 1);
        if (!(ue1Var instanceof te1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        i(((te1) ue1Var).a);
        return this;
    }

    @Override // p.a81
    public final a81 l0(String str) {
        ue1 ue1Var = (ue1) ou.i0(this.k);
        if (!(ue1Var instanceof te1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        te1 te1Var = (te1) ue1Var;
        if (te1Var.b != null) {
            throw new IllegalStateException("Check failed.".toString());
        }
        te1Var.b = str;
        return this;
    }

    @Override // p.a81
    public final a81 m0(o71 o71Var) {
        v03.j(o71Var, "value");
        i(o71Var);
        return this;
    }

    @Override // p.a81
    public final a81 n0(boolean z) {
        i(Boolean.valueOf(z));
        return this;
    }

    @Override // p.a81
    public final a81 z(long j) {
        i(Long.valueOf(j));
        return this;
    }
}
