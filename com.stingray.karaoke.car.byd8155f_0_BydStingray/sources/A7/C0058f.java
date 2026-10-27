package A7;

import H7.InterfaceC0318c;
import K7.InterfaceC0381e;
import androidx.lifecycle.f0;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
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

/* JADX INFO: renamed from: A7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0058f implements InterfaceC0318c, InterfaceC0057e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Map f459m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final HashMap f460n;
    public static final LinkedHashMap o;
    public final Class l;

    static {
        List listD = AbstractC1999n.D(InterfaceC2772a.class, InterfaceC2782k.class, InterfaceC2785n.class, InterfaceC2786o.class, InterfaceC2787p.class, InterfaceC2788q.class, InterfaceC2789r.class, InterfaceC2790s.class, InterfaceC2791t.class, InterfaceC2792u.class, InterfaceC2773b.class, InterfaceC2774c.class, InterfaceC0381e.class, InterfaceC2775d.class, InterfaceC2776e.class, InterfaceC2777f.class, InterfaceC2778g.class, InterfaceC2779h.class, InterfaceC2780i.class, InterfaceC2781j.class, InterfaceC2783l.class, InterfaceC2784m.class, InterfaceC0381e.class);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listD, 10));
        int i7 = 0;
        for (Object obj : listD) {
            int i10 = i7 + 1;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            arrayList.add(new m7.i((Class) obj, Integer.valueOf(i7)));
            i7 = i10;
        }
        f459m = n7.z.x(arrayList);
        HashMap map = new HashMap();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        HashMap map2 = new HashMap();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        HashMap map3 = new HashMap();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        m.e(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            m.c(str);
            sb.append(Q8.h.a1('.', str, str));
            sb.append("CompanionObject");
            map3.put(sb.toString(), str.concat(".Companion"));
        }
        for (Map.Entry entry : f459m.entrySet()) {
            Class cls = (Class) entry.getKey();
            int iIntValue = ((Number) entry.getValue()).intValue();
            map3.put(cls.getName(), "kotlin.Function" + iIntValue);
        }
        f460n = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(n7.z.q(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            Object key = entry2.getKey();
            String str2 = (String) entry2.getValue();
            m.c(str2);
            linkedHashMap.put(key, Q8.h.a1('.', str2, str2));
        }
        o = linkedHashMap;
    }

    public C0058f(Class cls) {
        m.f(cls, "jClass");
        this.l = cls;
    }

    @Override // H7.InterfaceC0318c
    public final String a() {
        String str;
        Class cls = this.l;
        m.f(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        boolean zIsArray = cls.isArray();
        HashMap map = f460n;
        if (!zIsArray) {
            String str2 = (String) map.get(cls.getName());
            return str2 == null ? cls.getCanonicalName() : str2;
        }
        Class<?> componentType = cls.getComponentType();
        if (componentType.isPrimitive() && (str = (String) map.get(componentType.getName())) != null) {
            strConcat = str.concat("Array");
        }
        return strConcat == null ? "kotlin.Array" : strConcat;
    }

    @Override // H7.InterfaceC0318c
    public final String b() {
        String str;
        Class cls = this.l;
        m.f(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            boolean zIsArray = cls.isArray();
            LinkedHashMap linkedHashMap = o;
            if (!zIsArray) {
                String str2 = (String) linkedHashMap.get(cls.getName());
                return str2 == null ? cls.getSimpleName() : str2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) linkedHashMap.get(componentType.getName())) != null) {
                strConcat = str.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return Q8.h.Y0(simpleName, enclosingMethod.getName() + '$', simpleName);
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor == null) {
            return Q8.h.Z0(simpleName);
        }
        return Q8.h.Y0(simpleName, enclosingConstructor.getName() + '$', simpleName);
    }

    @Override // H7.InterfaceC0318c
    public final boolean c(f0 f0Var) {
        Class clsJ = this.l;
        m.f(clsJ, "jClass");
        Map map = f459m;
        m.d(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Integer num = (Integer) map.get(clsJ);
        if (num != null) {
            return E.f(num.intValue(), f0Var);
        }
        if (clsJ.isPrimitive()) {
            clsJ = n7.z.j(n7.z.l(clsJ));
        }
        return clsJ.isInstance(f0Var);
    }

    @Override // A7.InterfaceC0057e
    public final Class d() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0058f) && n7.z.j(this).equals(n7.z.j((InterfaceC0318c) obj));
    }

    public final int hashCode() {
        return n7.z.j(this).hashCode();
    }

    public final String toString() {
        return this.l + " (Kotlin reflection is not available)";
    }
}
