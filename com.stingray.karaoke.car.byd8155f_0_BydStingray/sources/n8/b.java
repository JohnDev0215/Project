package n8;

import A7.m;
import Q8.o;
import java.util.LinkedHashMap;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f20163a = AbstractC1998m.f0(AbstractC1999n.D('k', 'o', 't', 'l', 'i', 'n'), "", null, null, null, 62);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f20164b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listD = AbstractC1999n.D("Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D");
        int iN = z.n(0, listD.size() - 1, 2);
        if (iN >= 0) {
            int i7 = 0;
            while (true) {
                StringBuilder sb = new StringBuilder();
                String str = f20163a;
                sb.append(str);
                sb.append('/');
                sb.append((String) listD.get(i7));
                int i10 = i7 + 1;
                linkedHashMap.put(sb.toString(), listD.get(i10));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append('/');
                linkedHashMap.put(android.hardware.bydauto.ac.tsimpl.a.l(sb2, (String) listD.get(i7), "Array"), "[" + ((String) listD.get(i10)));
                if (i7 == iN) {
                    break;
                } else {
                    i7 += 2;
                }
            }
        }
        linkedHashMap.put(f20163a + "/Unit", "V");
        a(linkedHashMap, "Any", "java/lang/Object");
        a(linkedHashMap, "Nothing", "java/lang/Void");
        a(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : AbstractC1999n.D("String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum")) {
            a(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : AbstractC1999n.D("Iterator", "Collection", "List", "Set", "Map", "ListIterator")) {
            a(linkedHashMap, M5.b.g("collections/", str3), "java/util/" + str3);
            a(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        a(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        a(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i11 = 0; i11 < 23; i11++) {
            String strE = android.hardware.bydauto.ac.tsimpl.a.e(i11, "Function");
            StringBuilder sb3 = new StringBuilder();
            String str4 = f20163a;
            sb3.append(str4);
            sb3.append("/jvm/functions/Function");
            sb3.append(i11);
            a(linkedHashMap, strE, sb3.toString());
            a(linkedHashMap, "reflect/KFunction" + i11, str4 + "/reflect/KFunction");
        }
        for (String str5 : AbstractC1999n.D("Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum")) {
            a(linkedHashMap, android.hardware.bydauto.ac.tsimpl.a.h(str5, ".Companion"), f20163a + "/jvm/internal/" + str5 + "CompanionObject");
        }
        f20164b = linkedHashMap;
    }

    public static final void a(LinkedHashMap linkedHashMap, String str, String str2) {
        linkedHashMap.put(f20163a + '/' + str, "L" + str2 + ';');
    }

    public static final String b(String str) {
        m.f(str, "classId");
        String str2 = (String) f20164b.get(str);
        if (str2 != null) {
            return str2;
        }
        return "L" + o.w0(str, '.', '$') + ';';
    }
}
