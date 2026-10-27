package Z7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f10941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f10942b;
    public static final Set c;
    public static final Set d;

    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Object, java.util.Map] */
    static {
        C2060e c2060e = N7.n.f4853j;
        C2058c c2058cG = c2060e.b(C2061f.e("name")).g();
        A7.m.e(c2058cG, "child(Name.identifier(name)).toSafe()");
        m7.i iVar = new m7.i(c2058cG, C2061f.e("name"));
        C2058c c2058cG2 = c2060e.b(C2061f.e("ordinal")).g();
        A7.m.e(c2058cG2, "child(Name.identifier(name)).toSafe()");
        m7.i iVar2 = new m7.i(c2058cG2, C2061f.e("ordinal"));
        m7.i iVar3 = new m7.i(N7.n.B.c(C2061f.e("size")), C2061f.e("size"));
        C2058c c2058c = N7.n.f4822F;
        m7.i iVar4 = new m7.i(c2058c.c(C2061f.e("size")), C2061f.e("size"));
        C2058c c2058cG3 = N7.n.f4848e.b(C2061f.e("length")).g();
        A7.m.e(c2058cG3, "child(Name.identifier(name)).toSafe()");
        Map mapS = n7.z.s(iVar, iVar2, iVar3, iVar4, new m7.i(c2058cG3, C2061f.e("length")), new m7.i(c2058c.c(C2061f.e("keys")), C2061f.e("keySet")), new m7.i(c2058c.c(C2061f.e("values")), C2061f.e("values")), new m7.i(c2058c.c(C2061f.e("entries")), C2061f.e("entrySet")));
        f10941a = mapS;
        Set<Map.Entry> setEntrySet = mapS.entrySet();
        ArrayList<m7.i> arrayList = new ArrayList(AbstractC2000o.J(setEntrySet, 10));
        for (Map.Entry entry : setEntrySet) {
            arrayList.add(new m7.i(((C2058c) entry.getKey()).f(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (m7.i iVar5 : arrayList) {
            C2061f c2061f = (C2061f) iVar5.f19259m;
            Object arrayList2 = linkedHashMap.get(c2061f);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(c2061f, arrayList2);
            }
            ((List) arrayList2).add((C2061f) iVar5.l);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(n7.z.q(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), AbstractC1998m.U((Iterable) entry2.getValue()));
        }
        f10942b = linkedHashMap2;
        Set setKeySet = f10941a.keySet();
        c = setKeySet;
        Set set = setKeySet;
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(set, 10));
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList3.add(((C2058c) it.next()).f());
        }
        d = AbstractC1998m.D0(arrayList3);
    }
}
