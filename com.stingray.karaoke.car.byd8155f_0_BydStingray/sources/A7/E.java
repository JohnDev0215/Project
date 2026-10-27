package A7;

import K7.InterfaceC0381e;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import z7.InterfaceC2772a;
import z7.InterfaceC2773b;
import z7.InterfaceC2774c;
import z7.InterfaceC2775d;
import z7.InterfaceC2776e;
import z7.InterfaceC2777f;
import z7.InterfaceC2778g;
import z7.InterfaceC2779h;
import z7.InterfaceC2780i;
import z7.InterfaceC2781j;
import z7.InterfaceC2782k;
import z7.InterfaceC2783l;
import z7.InterfaceC2784m;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;
import z7.InterfaceC2787p;
import z7.InterfaceC2788q;
import z7.InterfaceC2789r;
import z7.InterfaceC2790s;
import z7.InterfaceC2791t;
import z7.InterfaceC2792u;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {
    public static Collection a(LinkedHashSet linkedHashSet) {
        if (!(linkedHashSet instanceof B7.a) || (linkedHashSet instanceof B7.b)) {
            return linkedHashSet;
        }
        g(linkedHashSet, "kotlin.collections.MutableCollection");
        throw null;
    }

    public static List b(Object obj) {
        if ((obj instanceof B7.a) && !(obj instanceof B7.c)) {
            g(obj, "kotlin.collections.MutableList");
            throw null;
        }
        try {
            return (List) obj;
        } catch (ClassCastException e10) {
            m.i(e10, E.class.getName());
            throw e10;
        }
    }

    public static Map c(Object obj) {
        if ((obj instanceof B7.a) && !(obj instanceof B7.e)) {
            g(obj, "kotlin.collections.MutableMap");
            throw null;
        }
        try {
            return (Map) obj;
        } catch (ClassCastException e10) {
            m.i(e10, E.class.getName());
            throw e10;
        }
    }

    public static Set d(Object obj) {
        if ((obj instanceof B7.a) && !(obj instanceof B7.f)) {
            g(obj, "kotlin.collections.MutableSet");
            throw null;
        }
        try {
            return (Set) obj;
        } catch (ClassCastException e10) {
            m.i(e10, E.class.getName());
            throw e10;
        }
    }

    public static Object e(int i7, Object obj) {
        if (obj == null || f(i7, obj)) {
            return obj;
        }
        g(obj, "kotlin.jvm.functions.Function" + i7);
        throw null;
    }

    public static boolean f(int i7, Object obj) {
        int arity;
        if (!(obj instanceof m7.c)) {
            return false;
        }
        if (obj instanceof InterfaceC0060h) {
            arity = ((InterfaceC0060h) obj).getArity();
        } else if (obj instanceof InterfaceC2772a) {
            arity = 0;
        } else if (obj instanceof InterfaceC2782k) {
            arity = 1;
        } else if (obj instanceof InterfaceC2785n) {
            arity = 2;
        } else if (obj instanceof InterfaceC2786o) {
            arity = 3;
        } else if (obj instanceof InterfaceC2787p) {
            arity = 4;
        } else if (obj instanceof InterfaceC2788q) {
            arity = 5;
        } else if (obj instanceof InterfaceC2789r) {
            arity = 6;
        } else if (obj instanceof InterfaceC2790s) {
            arity = 7;
        } else if (obj instanceof InterfaceC2791t) {
            arity = 8;
        } else if (obj instanceof InterfaceC2792u) {
            arity = 9;
        } else if (obj instanceof InterfaceC2773b) {
            arity = 10;
        } else if (obj instanceof InterfaceC2774c) {
            arity = 11;
        } else {
            boolean z6 = obj instanceof InterfaceC0381e;
            arity = z6 ? 12 : obj instanceof InterfaceC2775d ? 13 : obj instanceof InterfaceC2776e ? 14 : obj instanceof InterfaceC2777f ? 15 : obj instanceof InterfaceC2778g ? 16 : obj instanceof InterfaceC2779h ? 17 : obj instanceof InterfaceC2780i ? 18 : obj instanceof InterfaceC2781j ? 19 : obj instanceof InterfaceC2783l ? 20 : obj instanceof InterfaceC2784m ? 21 : z6 ? 22 : -1;
        }
        return arity == i7;
    }

    public static void g(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
        m.i(classCastException, E.class.getName());
        throw classCastException;
    }
}
