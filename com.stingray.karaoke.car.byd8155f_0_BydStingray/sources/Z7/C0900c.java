package Z7;

import Q7.InterfaceC0652e;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1998m;
import n7.AbstractC2004s;
import o8.C2058c;
import o8.C2061f;
import t8.C2449b;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: Z7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0900c {
    public static final LinkedHashMap c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f10932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f10933b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (EnumC0898a enumC0898a : EnumC0898a.values()) {
            String str = enumC0898a.l;
            if (linkedHashMap.get(str) == null) {
                linkedHashMap.put(str, enumC0898a);
            }
        }
        c = linkedHashMap;
    }

    public C0900c(t tVar) {
        A7.m.f(tVar, "javaTypeEnhancementState");
        this.f10932a = tVar;
        this.f10933b = new ConcurrentHashMap();
    }

    public static ArrayList a(Object obj, boolean z6) {
        R7.b bVar = (R7.b) obj;
        A7.m.f(bVar, "<this>");
        Map mapC = bVar.c();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : mapC.entrySet()) {
            AbstractC2004s.L(arrayList, (!z6 || A7.m.a((C2061f) entry.getKey(), x.f10964b)) ? j((t8.g) entry.getValue()) : n7.u.l);
        }
        return arrayList;
    }

    public static Object c(Object obj, C2058c c2058c) {
        for (Object obj2 : e(obj)) {
            if (A7.m.a(d(obj2), c2058c)) {
                return obj2;
            }
        }
        return null;
    }

    public static C2058c d(Object obj) {
        R7.b bVar = (R7.b) obj;
        A7.m.f(bVar, "<this>");
        return bVar.a();
    }

    public static Iterable e(Object obj) {
        R7.h hVarK;
        R7.b bVar = (R7.b) obj;
        A7.m.f(bVar, "<this>");
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d(bVar);
        return (interfaceC0652eD == null || (hVarK = interfaceC0652eD.k()) == null) ? n7.u.l : hVarK;
    }

    public static boolean f(Object obj, C2058c c2058c) {
        Iterable iterableE = e(obj);
        if ((iterableE instanceof Collection) && ((Collection) iterableE).isEmpty()) {
            return false;
        }
        Iterator it = iterableE.iterator();
        while (it.hasNext()) {
            if (A7.m.a(d(it.next()), c2058c)) {
                return true;
            }
        }
        return false;
    }

    public static List j(t8.g gVar) {
        if (!(gVar instanceof C2449b)) {
            return gVar instanceof t8.i ? AbstractC1387d.j(((t8.i) gVar).c.c()) : n7.u.l;
        }
        Iterable iterable = (Iterable) ((C2449b) gVar).f22499a;
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(arrayList, j((t8.g) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0126  */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.lang.Object, java.util.Map] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final Z7.u b(Z7.u r14, R7.h r15) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z7.C0900c.b(Z7.u, R7.h):Z7.u");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0084, code lost:
    
        if (r9.equals("ALWAYS") != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
    
        if (r9.equals("UNKNOWN") == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
    
        if (r9.equals("NEVER") == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009f, code lost:
    
        if (r9.equals("MAYBE") == false) goto L45;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final g8.C1319i g(java.lang.Object r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z7.C0900c.g(java.lang.Object, boolean):g8.i");
    }

    public final B h(Object obj) {
        String str;
        t tVar = this.f10932a;
        B b10 = (B) tVar.f10956a.c.get(d(obj));
        if (b10 != null) {
            return b10;
        }
        Object objC = c(obj, AbstractC0899b.d);
        if (objC == null || (str = (String) AbstractC1998m.Z(a(objC, false))) == null) {
            return null;
        }
        B b11 = tVar.f10956a.f10960b;
        if (b11 != null) {
            return b11;
        }
        int iHashCode = str.hashCode();
        if (iHashCode == -2137067054) {
            if (str.equals("IGNORE")) {
                return B.IGNORE;
            }
            return null;
        }
        if (iHashCode == -1838656823) {
            if (str.equals("STRICT")) {
                return B.STRICT;
            }
            return null;
        }
        if (iHashCode == 2656902 && str.equals("WARN")) {
            return B.WARN;
        }
        return null;
    }

    public final Object i(Object obj) {
        Object objI;
        A7.m.f(obj, "annotation");
        if (this.f10932a.f10956a.d) {
            return null;
        }
        if (AbstractC1998m.T(AbstractC0899b.f10931g, d(obj)) || f(obj, AbstractC0899b.f10928b)) {
            return obj;
        }
        if (!f(obj, AbstractC0899b.f10927a)) {
            return null;
        }
        ConcurrentHashMap concurrentHashMap = this.f10933b;
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d((R7.b) obj);
        A7.m.c(interfaceC0652eD);
        Object obj2 = concurrentHashMap.get(interfaceC0652eD);
        if (obj2 != null) {
            return obj2;
        }
        Iterator it = e(obj).iterator();
        while (true) {
            if (!it.hasNext()) {
                objI = null;
                break;
            }
            objI = i(it.next());
            if (objI != null) {
                break;
            }
        }
        if (objI == null) {
            return null;
        }
        Object objPutIfAbsent = concurrentHashMap.putIfAbsent(interfaceC0652eD, objI);
        return objPutIfAbsent == null ? objI : objPutIfAbsent;
    }
}
