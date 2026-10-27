package s;

import java.util.ArrayList;
import r.e;

/* JADX INFO: compiled from: Grouping.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static o a(r.e eVar, int i7, ArrayList<o> arrayList, o oVar) {
        int iV1;
        int i8 = i7 == 0 ? eVar.R0 : eVar.S0;
        if (i8 != -1 && (oVar == null || i8 != oVar.f9439b)) {
            int i9 = 0;
            while (true) {
                if (i9 >= arrayList.size()) {
                    break;
                }
                o oVar2 = arrayList.get(i9);
                if (oVar2.c() == i8) {
                    if (oVar != null) {
                        oVar.g(i7, oVar2);
                        arrayList.remove(oVar);
                    }
                    oVar = oVar2;
                } else {
                    i9++;
                }
            }
        } else if (i8 != -1) {
            return oVar;
        }
        if (oVar == null) {
            if ((eVar instanceof r.j) && (iV1 = ((r.j) eVar).v1(i7)) != -1) {
                int i10 = 0;
                while (true) {
                    if (i10 >= arrayList.size()) {
                        break;
                    }
                    o oVar3 = arrayList.get(i10);
                    if (oVar3.c() == iV1) {
                        oVar = oVar3;
                        break;
                    }
                    i10++;
                }
            }
            if (oVar == null) {
                oVar = new o(i7);
            }
            arrayList.add(oVar);
        }
        if (oVar.a(eVar)) {
            if (eVar instanceof r.h) {
                r.h hVar = (r.h) eVar;
                hVar.u1().c(hVar.v1() == 0 ? 1 : 0, arrayList, oVar);
            }
            if (i7 == 0) {
                eVar.R0 = oVar.c();
                eVar.Q.c(i7, arrayList, oVar);
                eVar.S.c(i7, arrayList, oVar);
            } else {
                eVar.S0 = oVar.c();
                eVar.R.c(i7, arrayList, oVar);
                eVar.U.c(i7, arrayList, oVar);
                eVar.T.c(i7, arrayList, oVar);
            }
            eVar.X.c(i7, arrayList, oVar);
        }
        return oVar;
    }

    private static o b(ArrayList<o> arrayList, int i7) {
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            o oVar = arrayList.get(i8);
            if (i7 == oVar.f9439b) {
                return oVar;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x034b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(r.f r16, s.b.InterfaceC0143b r17) {
        /*
            Method dump skipped, instruction units count: 924
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s.i.c(r.f, s.b$b):boolean");
    }

    public static boolean d(e.b bVar, e.b bVar2, e.b bVar3, e.b bVar4) {
        e.b bVar5;
        e.b bVar6;
        e.b bVar7 = e.b.FIXED;
        return (bVar3 == bVar7 || bVar3 == (bVar6 = e.b.WRAP_CONTENT) || (bVar3 == e.b.MATCH_PARENT && bVar != bVar6)) || (bVar4 == bVar7 || bVar4 == (bVar5 = e.b.WRAP_CONTENT) || (bVar4 == e.b.MATCH_PARENT && bVar2 != bVar5));
    }
}
