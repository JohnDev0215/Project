package kotlin.jvm.internal;

import h5.g0;
import h5.h0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import r5.w;

/* JADX INFO: compiled from: ClassReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e implements w5.c<Object>, d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7495g = new a(null);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final Map<Class<? extends g5.c<?>>, Integer> f7496h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final HashMap<String, String> f7497i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final HashMap<String, String> f7498j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final HashMap<String, String> f7499k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final Map<String, String> f7500l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Class<?> f7501e;

    /* JADX INFO: compiled from: ClassReference.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int i7 = 0;
        List listJ = h5.p.j(r5.a.class, r5.l.class, r5.p.class, r5.q.class, r5.r.class, r5.s.class, r5.t.class, r5.u.class, r5.v.class, w.class, r5.b.class, r5.c.class, r5.d.class, r5.e.class, r5.f.class, r5.g.class, r5.h.class, r5.i.class, r5.j.class, r5.k.class, r5.m.class, r5.n.class, r5.o.class);
        ArrayList arrayList = new ArrayList(h5.q.p(listJ, 10));
        for (Object obj : listJ) {
            int i8 = i7 + 1;
            if (i7 < 0) {
                h5.p.o();
            }
            arrayList.add(g5.o.a((Class) obj, Integer.valueOf(i7)));
            i7 = i8;
        }
        f7496h = h0.i(arrayList);
        HashMap<String, String> map = new HashMap<>();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        f7497i = map;
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        f7498j = map2;
        HashMap<String, String> map3 = new HashMap<>();
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
        l.d(collectionValues, "primitiveFqNames.values");
        for (String kotlinName : collectionValues) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            l.d(kotlinName, "kotlinName");
            sb.append(y5.q.u0(kotlinName, '.', null, 2, null));
            sb.append("CompanionObject");
            g5.k kVarA = g5.o.a(sb.toString(), kotlinName + ".Companion");
            map3.put(kVarA.c(), kVarA.d());
        }
        for (Map.Entry<Class<? extends g5.c<?>>, Integer> entry : f7496h.entrySet()) {
            map3.put(entry.getKey().getName(), "kotlin.Function" + entry.getValue().intValue());
        }
        f7499k = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(g0.c(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), y5.q.u0((String) entry2.getValue(), '.', null, 2, null));
        }
        f7500l = linkedHashMap;
    }

    public e(Class<?> jClass) {
        l.e(jClass, "jClass");
        this.f7501e = jClass;
    }

    @Override // kotlin.jvm.internal.d
    public Class<?> a() {
        return this.f7501e;
    }

    public boolean equals(Object obj) {
        return (obj instanceof e) && l.a(q5.a.a(this), q5.a.a((w5.c) obj));
    }

    public int hashCode() {
        return q5.a.a(this).hashCode();
    }

    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
