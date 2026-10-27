package Z7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1985B;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;
import w8.EnumC2609d;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ArrayList f10913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ArrayList f10914b;
    public static final Object c;
    public static final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Set f10915e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Set f10916f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C f10917g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f10918h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final LinkedHashMap f10919i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final ArrayList f10920j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final LinkedHashMap f10921k;

    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v33, types: [java.lang.Object, java.util.Map] */
    static {
        Set<String> setC0 = AbstractC1996k.c0(new String[]{"containsAll", "removeAll", "retainAll"});
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(setC0, 10));
        for (String str : setC0) {
            String strC = EnumC2609d.BOOLEAN.c();
            A7.m.e(strC, "BOOLEAN.desc");
            arrayList.add(m.a("java/util/Collection", str, "Ljava/util/Collection;", strC));
        }
        f10913a = arrayList;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((C) it.next()).f10908b);
        }
        f10914b = arrayList2;
        ArrayList arrayList3 = f10913a;
        ArrayList arrayList4 = new ArrayList(AbstractC2000o.J(arrayList3, 10));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((C) it2.next()).f10907a.b());
        }
        String strConcat = "java/util/".concat("Collection");
        EnumC2609d enumC2609d = EnumC2609d.BOOLEAN;
        String strC2 = enumC2609d.c();
        A7.m.e(strC2, "BOOLEAN.desc");
        C cA = m.a(strConcat, "contains", "Ljava/lang/Object;", strC2);
        E e10 = E.o;
        m7.i iVar = new m7.i(cA, e10);
        String strConcat2 = "java/util/".concat("Collection");
        String strC3 = enumC2609d.c();
        A7.m.e(strC3, "BOOLEAN.desc");
        m7.i iVar2 = new m7.i(m.a(strConcat2, "remove", "Ljava/lang/Object;", strC3), e10);
        String strConcat3 = "java/util/".concat("Map");
        String strC4 = enumC2609d.c();
        A7.m.e(strC4, "BOOLEAN.desc");
        m7.i iVar3 = new m7.i(m.a(strConcat3, "containsKey", "Ljava/lang/Object;", strC4), e10);
        String strConcat4 = "java/util/".concat("Map");
        String strC5 = enumC2609d.c();
        A7.m.e(strC5, "BOOLEAN.desc");
        m7.i iVar4 = new m7.i(m.a(strConcat4, "containsValue", "Ljava/lang/Object;", strC5), e10);
        String strConcat5 = "java/util/".concat("Map");
        String strC6 = enumC2609d.c();
        A7.m.e(strC6, "BOOLEAN.desc");
        m7.i iVar5 = new m7.i(m.a(strConcat5, "remove", "Ljava/lang/Object;Ljava/lang/Object;", strC6), e10);
        m7.i iVar6 = new m7.i(m.a("java/util/".concat("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), E.f10911p);
        C cA2 = m.a("java/util/".concat("Map"), "get", "Ljava/lang/Object;", "Ljava/lang/Object;");
        E e11 = E.f10909m;
        m7.i iVar7 = new m7.i(cA2, e11);
        m7.i iVar8 = new m7.i(m.a("java/util/".concat("Map"), "remove", "Ljava/lang/Object;", "Ljava/lang/Object;"), e11);
        String strConcat6 = "java/util/".concat("List");
        EnumC2609d enumC2609d2 = EnumC2609d.INT;
        String strC7 = enumC2609d2.c();
        A7.m.e(strC7, "INT.desc");
        C cA3 = m.a(strConcat6, "indexOf", "Ljava/lang/Object;", strC7);
        E e12 = E.f10910n;
        m7.i iVar9 = new m7.i(cA3, e12);
        String strConcat7 = "java/util/".concat("List");
        String strC8 = enumC2609d2.c();
        A7.m.e(strC8, "INT.desc");
        Map mapS = n7.z.s(iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, new m7.i(m.a(strConcat7, "lastIndexOf", "Ljava/lang/Object;", strC8), e12));
        c = mapS;
        LinkedHashMap linkedHashMap = new LinkedHashMap(n7.z.q(mapS.size()));
        for (Map.Entry entry : mapS.entrySet()) {
            linkedHashMap.put(((C) entry.getKey()).f10908b, entry.getValue());
        }
        d = linkedHashMap;
        LinkedHashSet linkedHashSetQ = AbstractC1985B.q(c.keySet(), f10913a);
        ArrayList arrayList5 = new ArrayList(AbstractC2000o.J(linkedHashSetQ, 10));
        Iterator it3 = linkedHashSetQ.iterator();
        while (it3.hasNext()) {
            arrayList5.add(((C) it3.next()).f10907a);
        }
        f10915e = AbstractC1998m.D0(arrayList5);
        ArrayList arrayList6 = new ArrayList(AbstractC2000o.J(linkedHashSetQ, 10));
        Iterator it4 = linkedHashSetQ.iterator();
        while (it4.hasNext()) {
            arrayList6.add(((C) it4.next()).f10908b);
        }
        f10916f = AbstractC1998m.D0(arrayList6);
        EnumC2609d enumC2609d3 = EnumC2609d.INT;
        String strC9 = enumC2609d3.c();
        A7.m.e(strC9, "INT.desc");
        C cA4 = m.a("java/util/List", "removeAt", strC9, "Ljava/lang/Object;");
        f10917g = cA4;
        String strConcat8 = "java/lang/".concat("Number");
        String strC10 = EnumC2609d.BYTE.c();
        A7.m.e(strC10, "BYTE.desc");
        m7.i iVar10 = new m7.i(m.a(strConcat8, "toByte", "", strC10), C2061f.e("byteValue"));
        String strConcat9 = "java/lang/".concat("Number");
        String strC11 = EnumC2609d.SHORT.c();
        A7.m.e(strC11, "SHORT.desc");
        m7.i iVar11 = new m7.i(m.a(strConcat9, "toShort", "", strC11), C2061f.e("shortValue"));
        String strConcat10 = "java/lang/".concat("Number");
        String strC12 = enumC2609d3.c();
        A7.m.e(strC12, "INT.desc");
        m7.i iVar12 = new m7.i(m.a(strConcat10, "toInt", "", strC12), C2061f.e("intValue"));
        String strConcat11 = "java/lang/".concat("Number");
        String strC13 = EnumC2609d.LONG.c();
        A7.m.e(strC13, "LONG.desc");
        m7.i iVar13 = new m7.i(m.a(strConcat11, "toLong", "", strC13), C2061f.e("longValue"));
        String strConcat12 = "java/lang/".concat("Number");
        String strC14 = EnumC2609d.FLOAT.c();
        A7.m.e(strC14, "FLOAT.desc");
        m7.i iVar14 = new m7.i(m.a(strConcat12, "toFloat", "", strC14), C2061f.e("floatValue"));
        String strConcat13 = "java/lang/".concat("Number");
        String strC15 = EnumC2609d.DOUBLE.c();
        A7.m.e(strC15, "DOUBLE.desc");
        m7.i iVar15 = new m7.i(m.a(strConcat13, "toDouble", "", strC15), C2061f.e("doubleValue"));
        m7.i iVar16 = new m7.i(cA4, C2061f.e("remove"));
        String strConcat14 = "java/lang/".concat("CharSequence");
        String strC16 = enumC2609d3.c();
        A7.m.e(strC16, "INT.desc");
        String strC17 = EnumC2609d.CHAR.c();
        A7.m.e(strC17, "CHAR.desc");
        Map mapS2 = n7.z.s(iVar10, iVar11, iVar12, iVar13, iVar14, iVar15, iVar16, new m7.i(m.a(strConcat14, "get", strC16, strC17), C2061f.e("charAt")));
        f10918h = mapS2;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(n7.z.q(mapS2.size()));
        for (Map.Entry entry2 : mapS2.entrySet()) {
            linkedHashMap2.put(((C) entry2.getKey()).f10908b, entry2.getValue());
        }
        f10919i = linkedHashMap2;
        Set setKeySet = f10918h.keySet();
        ArrayList arrayList7 = new ArrayList(AbstractC2000o.J(setKeySet, 10));
        Iterator it5 = setKeySet.iterator();
        while (it5.hasNext()) {
            arrayList7.add(((C) it5.next()).f10907a);
        }
        f10920j = arrayList7;
        Set<Map.Entry> setEntrySet = f10918h.entrySet();
        ArrayList<m7.i> arrayList8 = new ArrayList(AbstractC2000o.J(setEntrySet, 10));
        for (Map.Entry entry3 : setEntrySet) {
            arrayList8.add(new m7.i(((C) entry3.getKey()).f10907a, entry3.getValue()));
        }
        int iQ = n7.z.q(AbstractC2000o.J(arrayList8, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(iQ);
        for (m7.i iVar17 : arrayList8) {
            linkedHashMap3.put((C2061f) iVar17.f19259m, (C2061f) iVar17.l);
        }
        f10921k = linkedHashMap3;
    }
}
