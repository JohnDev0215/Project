package K7;

import W7.AbstractC0839c;
import h8.AbstractC1387d;
import h8.C1389f;
import i8.C1417b;
import i8.EnumC1416a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1985B;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import o8.C2057b;
import o8.C2058c;
import p8.C2142q;
import w8.C2608c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ S f3745n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Q(S s6, int i7) {
        super(0);
        this.f3744m = i7;
        this.f3745n = s6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Iterable] */
    @Override // z7.InterfaceC2772a
    public final Object a() throws C2142q {
        String[] strArr;
        ?? J6;
        switch (this.f3744m) {
            case 0:
                S s6 = this.f3745n;
                s6.getClass();
                H7.w wVar = S.f3746g[0];
                V7.c cVar = (V7.c) s6.c.a();
                if (cVar == null) {
                    return null;
                }
                C1417b c1417b = cVar.f9549b;
                String[] strArr2 = (String[]) c1417b.f15785e;
                if (strArr2 == null || (strArr = (String[]) c1417b.f15787g) == null) {
                    return null;
                }
                m7.i iVarH = n8.h.h(strArr2, strArr);
                return new m7.o((n8.g) iVarH.l, (j8.C) iVarH.f19259m, (n8.f) c1417b.d);
            default:
                S s10 = this.f3745n;
                s10.getClass();
                H7.w wVar2 = S.f3746g[0];
                V7.c cVar2 = (V7.c) s10.c.a();
                if (cVar2 == null) {
                    return y8.m.f24213b;
                }
                H7.w wVar3 = B.f3719b[0];
                Object objA = s10.f3720a.a();
                A7.m.e(objA, "<get-moduleData>(...)");
                l3.P p10 = ((V7.f) objA).f9553b;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) p10.o;
                Class cls = cVar2.f9548a;
                C2057b c2057bA = AbstractC0839c.a(cls);
                Object obj = concurrentHashMap.get(c2057bA);
                if (obj == null) {
                    C2058c c2058cG = AbstractC0839c.a(cls).g();
                    A7.m.e(c2058cG, "fileClass.classId.packageFqName");
                    C1417b c1417b2 = cVar2.f9549b;
                    EnumC1416a enumC1416a = EnumC1416a.MULTIFILE_CLASS;
                    C1389f c1389f = (C1389f) p10.f18537m;
                    EnumC1416a enumC1416a2 = (EnumC1416a) c1417b2.c;
                    if (enumC1416a2 == enumC1416a) {
                        String[] strArr3 = enumC1416a2 == enumC1416a ? (String[]) c1417b2.f15785e : null;
                        List listX = strArr3 != null ? AbstractC1996k.x(strArr3) : null;
                        if (listX == null) {
                            listX = n7.u.l;
                        }
                        J6 = new ArrayList();
                        Iterator it = listX.iterator();
                        while (it.hasNext()) {
                            C2057b c2057bJ = C2057b.j(new C2058c(C2608c.d((String) it.next()).f23346a.replace('/', '.')));
                            A7.m.f(c1389f.c().c, "<this>");
                            V7.c cVarF = n7.z.f((V7.b) p10.f18538n, c2057bJ, n8.f.f20166g);
                            if (cVarF != null) {
                                J6.add(cVarF);
                            }
                        }
                    } else {
                        J6 = AbstractC1387d.j(cVar2);
                    }
                    P7.k kVar = new P7.k(c1389f.c().f763b, c2058cG, 1);
                    ArrayList arrayList = new ArrayList();
                    Iterator it2 = J6.iterator();
                    while (it2.hasNext()) {
                        D8.s sVarA = c1389f.a(kVar, (V7.c) it2.next());
                        if (sVarA != null) {
                            arrayList.add(sVarA);
                        }
                    }
                    y8.n nVarD = AbstractC1985B.d("package " + c2058cG + " (" + cVar2 + ')', AbstractC1998m.z0(arrayList));
                    Object objPutIfAbsent = concurrentHashMap.putIfAbsent(c2057bA, nVarD);
                    obj = objPutIfAbsent == null ? nVarD : objPutIfAbsent;
                }
                A7.m.e(obj, "cache.getOrPut(fileClass…ileClass)\", scopes)\n    }");
                return (y8.n) obj;
        }
    }
}
