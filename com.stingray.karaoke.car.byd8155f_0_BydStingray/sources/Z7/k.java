package Z7;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import o8.C2057b;
import o8.C2058c;
import o8.C2064i;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashMap f10944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f10945b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        f10944a = linkedHashMap;
        b(C2064i.f20592q, a("java.util.ArrayList", "java.util.LinkedList"));
        b(C2064i.f20593r, a("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        b(C2064i.f20594s, a("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        b(C2057b.j(new C2058c("java.util.function.Function")), a("java.util.function.UnaryOperator"));
        b(C2057b.j(new C2058c("java.util.function.BiFunction")), a("java.util.function.BinaryOperator"));
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new m7.i(((C2057b) entry.getKey()).b(), ((C2057b) entry.getValue()).b()));
        }
        f10945b = n7.z.x(arrayList);
    }

    public static ArrayList a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(C2057b.j(new C2058c(str)));
        }
        return arrayList;
    }

    public static void b(C2057b c2057b, ArrayList arrayList) {
        for (Object obj : arrayList) {
            f10944a.put(obj, c2057b);
        }
    }
}
