package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.y;
import kotlin.collections.z;
import kotlin.jvm.b.l;
import kotlin.jvm.b.m;
import kotlin.jvm.b.n;
import kotlin.jvm.b.o;
import kotlin.jvm.b.p;
import kotlin.jvm.b.q;
import kotlin.jvm.b.r;
import kotlin.jvm.b.s;
import kotlin.jvm.b.t;
import kotlin.jvm.b.u;
import kotlin.jvm.b.v;
import kotlin.jvm.b.w;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ClassReference.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d implements kotlin.reflect.c<Object>, c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<Class<?>, Integer> f4075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final HashMap<String, String> f4076c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final HashMap<String, String> f4077d;
    private static final HashMap<String, String> e;
    private static final Map<String, String> f;
    public static final a g = new a(null);
    private final Class<?> h;

    /* JADX INFO: compiled from: ClassReference.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int i = 0;
        List listF = kotlin.collections.k.f(kotlin.jvm.b.a.class, l.class, p.class, q.class, r.class, s.class, t.class, u.class, v.class, w.class, kotlin.jvm.b.b.class, kotlin.jvm.b.c.class, kotlin.jvm.b.d.class, kotlin.jvm.b.e.class, kotlin.jvm.b.f.class, kotlin.jvm.b.g.class, kotlin.jvm.b.h.class, kotlin.jvm.b.i.class, kotlin.jvm.b.j.class, kotlin.jvm.b.k.class, m.class, n.class, o.class);
        ArrayList arrayList = new ArrayList(kotlin.collections.l.i(listF, 10));
        for (Object obj : listF) {
            int i2 = i + 1;
            if (i < 0) {
                kotlin.collections.k.h();
            }
            arrayList.add(kotlin.h.a((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        f4075b = z.g(arrayList);
        HashMap<String, String> map = new HashMap<>();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        f4076c = map;
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        f4077d = map2;
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
        h.c(collectionValues, "primitiveFqNames.values");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            h.c(str, "kotlinName");
            sb.append(StringsKt__StringsKt.O(str, '.', null, 2, null));
            sb.append("CompanionObject");
            Pair pairA = kotlin.h.a(sb.toString(), str + ".Companion");
            map3.put(pairA.getFirst(), pairA.getSecond());
        }
        for (Map.Entry<Class<?>, Integer> entry : f4075b.entrySet()) {
            map3.put(entry.getKey().getName(), "kotlin.Function" + entry.getValue().intValue());
        }
        e = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(y.a(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), StringsKt__StringsKt.O((String) entry2.getValue(), '.', null, 2, null));
        }
        f = linkedHashMap;
    }

    public d(Class<?> cls) {
        h.d(cls, "jClass");
        this.h = cls;
    }

    @Override // kotlin.jvm.internal.c
    public Class<?> a() {
        return this.h;
    }

    public boolean equals(Object obj) {
        return (obj instanceof d) && h.a(kotlin.jvm.a.a(this), kotlin.jvm.a.a((kotlin.reflect.c) obj));
    }

    public int hashCode() {
        return kotlin.jvm.a.a(this).hashCode();
    }

    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
